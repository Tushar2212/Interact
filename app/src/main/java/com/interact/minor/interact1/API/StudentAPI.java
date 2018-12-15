package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.StudentModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentAPI {
    @GET("api/main/student/{enroll_no}")
    Call<StudentModel> getByID(@Path("enroll_no") String enroll_no);
}
