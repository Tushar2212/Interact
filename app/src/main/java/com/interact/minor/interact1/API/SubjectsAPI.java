package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.SubjectModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SubjectsAPI {
    @GET("subjects/?search=")
    Call<ArrayList<SubjectModel>> getSubjects(@Query("id") int id);
}
