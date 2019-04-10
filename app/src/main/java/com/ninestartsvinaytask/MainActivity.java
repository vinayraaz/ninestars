package com.ninestartsvinaytask;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ninestartsvinaytask.adapter.PhotoListAdapter;
import com.ninestartsvinaytask.api.ApiClient;
import com.ninestartsvinaytask.api.ApiInterface;
import com.ninestartsvinaytask.jsonResponse.PhotoResponseModel;
import com.ninestartsvinaytask.model.DatabaseModel;
import com.ninestartsvinaytask.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PhotoModel> photoModels = new ArrayList<>();
    PhotoListAdapter photoListAdapter;
    GridLayoutManager gridLayoutManager;
    ApiInterface apiInterface;
    DataBaseAdapter db;
    ArrayList<DatabaseModel> databaseModelsDB;
    Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseAdapter(MainActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        db.open();
         //db.deleteAllData();
        databaseModelsDB = db.getcartPro();
        int cartsize = databaseModelsDB.size();
        if (cartsize > 0) {
            Log.i("cartsize Size**", "" + cartsize);
            for (int i = 0; i < databaseModelsDB.size(); i++) {
                photoModels.add(new PhotoModel(databaseModelsDB.get(i).getAlbumId(), databaseModelsDB.get(i).getId(), databaseModelsDB.get(i).getTitle(),
                        databaseModelsDB.get(i).getUrl(), databaseModelsDB.get(i).getThumbnailUrl()));
            }
            photoListLoad(photoModels);
            db.close();
        } else {


            init();
        }
    }


    private void init() {
        utilities.displayProgressDialog(MainActivity.this, "Loading...", false);
        photoModels.clear();
        Call<List<PhotoResponseModel>> call = apiInterface.getPhotoList();
        call.enqueue(new Callback<List<PhotoResponseModel>>() {
            @Override
            public void onResponse(Call<List<PhotoResponseModel>> call, Response<List<PhotoResponseModel>> response) {
                utilities.cancelProgressDialog();
                for (int i = 0; i < response.body().size(); i++) {

                    photoModels.add(new PhotoModel(
                            response.body().get(i).getAlbumId(),
                            response.body().get(i).getId(),
                            response.body().get(i).getTitle(),
                            response.body().get(i).getUrl(),
                            response.body().get(i).getThumbnailUrl()));

                    db.open();
                    db.insertData(String.valueOf(photoModels.get(i).getAlbumId()), String.valueOf(photoModels.get(i).getId()), photoModels.get(i).getTitle(),
                            photoModels.get(i).getUrl(), photoModels.get(i).getThumbnailUrl());

                    db.close();

                }
                photoListLoad(photoModels);

            }

            @Override
            public void onFailure(Call<List<PhotoResponseModel>> call, Throwable t) {
                Log.i("ERROR", t.toString());
                utilities.cancelProgressDialog();

            }
        });

    }

    private void photoListLoad(List<PhotoModel> photoModels) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        photoListAdapter = new PhotoListAdapter(this, photoModels);
        recyclerView.setAdapter(photoListAdapter);
    }
}
