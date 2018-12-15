package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.AttendanceModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AttendanceAPI {
    @GET("/api/main/student/att/{enroll_no}/")
    Call<AttendanceModel> getAttById(@Path("enroll_no") String enroll);
}
