package com.ninestartsvinaytask.PaginationModel;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ninestartsvinaytask.R;
import com.ninestartsvinaytask.adapter.PaginationAdapter;
import com.ninestartsvinaytask.api.ApiClient;
import com.ninestartsvinaytask.api.ApiInterface;
import com.ninestartsvinaytask.extra.PaginationScrollListener;
import com.ninestartsvinaytask.jsonResponse.MoviesModel.DataResult;
import com.ninestartsvinaytask.jsonResponse.MoviesModel.TopRatedMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaginationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    LinearLayoutManager linearLayoutManager;
    PaginationAdapter paginationAdapter;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        paginationAdapter = new PaginationAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(paginationAdapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        loadFirstPage();
    }

    private void loadNextPage() {
System.out.println("LoadNextPage***"+currentPage);
        Call<TopRatedMovies> call = apiInterface.getTopRatedMovies("ec01f8c2eb6ac402f2ca026dc2d9b8fd","en_US", currentPage);
        call.enqueue(new Callback<TopRatedMovies>() {
            @Override
            public void onResponse(Call<TopRatedMovies> call, Response<TopRatedMovies> response) {
                System.out.println("LoadNextPage***URL"+response.raw().request().url());
               // paginationAdapter.removeLoadingFooter();
                isLoading = false;

                List<DataResult> results = fetchResults(response);
                System.out.println("LoadNextPage***"+results.size());
                // progressBar.setVisibility(View.GONE);
                paginationAdapter.addAll(results);

                if (currentPage != TOTAL_PAGES) paginationAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<TopRatedMovies> call, Throwable t) {

            }
        });
    }

    private void loadFirstPage() {
        System.out.println("LoadFirstPage***"+currentPage);
        Call<TopRatedMovies> call = apiInterface.getTopRatedMovies("ec01f8c2eb6ac402f2ca026dc2d9b8fd","en_US", currentPage);
        call.enqueue(new Callback<TopRatedMovies>() {
            @Override
            public void onResponse(Call<TopRatedMovies> call, Response<TopRatedMovies> response) {

                List<DataResult> results = fetchResults(response);
                System.out.println("LoadFirstPage***"+results.size());
               // progressBar.setVisibility(View.GONE);
                paginationAdapter.addAll(results);

                if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<TopRatedMovies> call, Throwable t) {

            }
        });
    }

    private List<DataResult> fetchResults(Response<TopRatedMovies> response) {
        TopRatedMovies topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }
}
