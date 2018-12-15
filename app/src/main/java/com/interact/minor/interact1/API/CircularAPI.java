package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.CircularModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CircularAPI {
    @GET("/api/extras/circular_notice/?search=circular")
    Call<CircularModel> getCircular();
}
