package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class StudentServiceGenerator {

    private static final String BASE_URL = "http://localhost:3000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build()).build();


    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static String printStudentDetailsById(String id, StudentService service) {
        Observable<Student> studentCall = service.getStudentById(id);
        Student student = (Student) studentCall.blockingSingle();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        System.out.println(studentResponseString);
        return studentResponseString;
    }

    public static String printAllStudentsDetails(StudentService service) {
        Observable<List<Student>> studentCall = service.getAllStudents();
        String responseString = "";
        List<Student> students = studentCall.blockingSingle();
        for (int i = 0; i < students.size(); i++) {
            responseString += "Name: "+students.get(i).getName() + ", Age: " +students.get(i).getAge()+"\n";
        }
        System.out.println(responseString);
        return responseString;
    }
}
