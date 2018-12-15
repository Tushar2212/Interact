package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.EventModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventAPI
{
    @GET("/api/extras/events/")
    Call<ArrayList<EventModel>> getEvents();

}
