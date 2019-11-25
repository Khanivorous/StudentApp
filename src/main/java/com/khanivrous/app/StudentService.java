package com.khanivrous.app;

import com.khanivrous.app.models.StudentModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentService {

    @GET("students/{id}")
    Call<StudentModel> getStudentById(@Path("id") String path);
}
