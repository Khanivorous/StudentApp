package com.khanivrous.app;

import com.khanivrous.app.models.Student;
import io.reactivex.Observable;

import java.util.List;

import static com.khanivrous.app.StudentServiceGenerator.createService;

public class PrintStudentInformation {
    public static void main(String[] args) {
        StudentService service = createService(StudentService.class);
        printStudentDetailsById("1",service);
        printAllStudentsDetails(service);
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

