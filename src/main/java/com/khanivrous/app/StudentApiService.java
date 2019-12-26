package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;


public interface StudentApiService {

    @GET("students/{id}")
    Single<Student> getStudentById(@Path("id") String path);

    @GET("students")
    Single<List<Student>> getAllStudents();
}
