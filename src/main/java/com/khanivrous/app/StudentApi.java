package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import io.reactivex.Observable;
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
        Observable<Student> studentCall = service.getStudentById(id);
        Student student = studentCall.blockingFirst();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        return studentResponseString;
    }

    public static String getAllStudents(StudentApiService service) {
        Observable<List<Student>> studentCall = service.getAllStudents();
        String responseString = "";
        List<Student> students = studentCall.blockingFirst();
        for (int i = 0; i < students.size(); i++) {
            responseString += "Name: "+students.get(i).getName() + ", Age: " +students.get(i).getAge()+"\n";
        }
        return responseString;
    }
}
