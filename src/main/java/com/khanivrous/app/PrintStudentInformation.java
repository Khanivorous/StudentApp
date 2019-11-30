package com.khanivrous.app;

import static com.khanivrous.app.StudentApi.*;
import static com.khanivrous.app.StudentApi.createService;

public class PrintStudentInformation {
    public static void main(String[] args) {
        StudentApiService service = createService(StudentApiService.class);
        System.out.println(getStudentById("1",service));
        System.out.println(getAllStudents(service));
    }

}

