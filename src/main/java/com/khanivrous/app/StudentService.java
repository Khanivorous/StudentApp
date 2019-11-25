package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface StudentService {

    @GET("students/{id}")
    Call<Student> getStudentById(@Path("id") String path);

    @GET("students")
    Call<List<Student>> getAllStudents();
}
