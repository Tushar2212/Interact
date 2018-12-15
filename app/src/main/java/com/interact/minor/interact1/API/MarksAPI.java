package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.MarksModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MarksAPI {
    @GET("/api/main/student/int/{enroll_no}/")
    Call<MarksModel> getMarksById(@Path("enroll_no") String enroll_no);
}
