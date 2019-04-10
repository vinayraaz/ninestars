package com.ninestartsvinaytask.api;

import com.ninestartsvinaytask.jsonResponse.PhotoResponseModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("photos")
    Call<List<PhotoResponseModel>> getPhotoList();
// https://jsonplaceholder.typicode.com/photos





}


