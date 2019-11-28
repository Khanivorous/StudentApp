package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.khanivrous.app.StudentServiceGenerator.*;

public class PrintStudentInformation {
    public static void main(String[] args) {
        StudentService service = createService(StudentService.class);
        printStudentDetailsById("1",service);
        printAllStudentsDetails(service);
    }

    public static String printStudentDetailsById(String id, StudentService service) {
        Call<Student> studentModelCall = service.getStudentById(id);

        Response<Student> response = null;
        try {
            response = studentModelCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student student = response.body();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        System.out.println(studentResponseString);
        return studentResponseString;
    }

    public static String printAllStudentsDetails(StudentService service) {

        Call<List<Student>> call = service.getAllStudents();
        Response<List<Student>> response = null;
        try {
              response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseString = "";
        List<Student> students = response.body();

        for (int i = 0; i < students.size(); i++) {
            responseString += "Name: "+students.get(i).getName() + ", Age: " +students.get(i).getAge()+"\n";
        }
        System.out.println(responseString);
        return responseString;
    }
}

