package com.khanivrous.app;

import static com.khanivrous.app.StudentServiceGenerator.createService;

public class PrintStudentInformation {
    public static void main(String[] args) {
        StudentService service = createService(StudentService.class);
        StudentServiceGenerator.printStudentDetailsById("1",service);
        StudentServiceGenerator.printAllStudentsDetails(service);
    }

}

