package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class StudentApi {

    private static final String BASE_URL = "http://localhost:3000";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build()).build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static String getStudentById(String id, StudentApiService service) {
        Single<Student> studentCall = service.getStudentById(id);
        Student student = studentCall.blockingGet();
        return "Name: " + student.getName() + ", Age: " + student.getAge();
    }

    public static String getAllStudents(StudentApiService service) {
        Single<List<Student>> studentCall = service.getAllStudents();
        StringBuilder responseString = new StringBuilder();
        List<Student> students = studentCall.blockingGet();
        for (Student student : students) {
            responseString.append("Name: ").append(student.getName()).append(", Age: ").append(student.getAge()).append("\n");
        }
        return responseString.toString();
    }
}
