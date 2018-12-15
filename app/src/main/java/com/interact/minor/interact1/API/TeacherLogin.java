package com.interact.minor.interact1.API;

import com.interact.minor.interact1.Model.TeacherModel;
import com.interact.minor.interact1.Model.TeacherModelJava;
import com.interact.minor.interact1.Model.TeacherResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TeacherLogin {

    @Headers("content-type: application/json")
    @POST("login/")
   /* @FormUrlEncoded
    Call<TeacherModelJava> getTeach(@Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String password);*/

   Call<TeacherModel> getTeach( @Body TeacherResponse teacherResponse);
}

