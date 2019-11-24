package com.khanivrous.app;

import com.khanivrous.app.models.StudentModel;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class PrintStudentInformation {
    public static void main(String[] args) {
        System.out.println("This is a skeleton project right now");
    }

    public static String printStudentDetailsById(String id) throws IOException {
        StudentService service = StudentServiceGenerator.createService(StudentService.class);
        Call<StudentModel> studentModelCall = service.getStudentById(id);

        Response<StudentModel> response = studentModelCall.execute();
        StudentModel student = response.body();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        System.out.println(studentResponseString);
        return studentResponseString;
    }

//        studentModelCall.enqueue(new Callback<StudentModel>() {
//            @Override
//            public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
//                String studentResponseString = "";
//                StudentModel student = response.body();
//                studentResponseString += "Name: "+student.getName()+", Age: "+student.getAge();
//                System.out.println(studentResponseString);
//            }
//
//            @Override
//            public void onFailure(Call<StudentModel> call, Throwable t) {
//                System.out.println(t);
//            }
//        });
//
//    }
}

