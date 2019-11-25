package com.khanivorous.app.test;

import com.khanivrous.app.models.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentModelTest {

    @Test
    public void testStudentConstructor() {
        Student student = new Student();
        student.setId(3);
        student.setAge(32);
        student.setName("George");
        assertEquals("George",student.getName());
        assertEquals(32, (int) student.getAge());
        assertEquals(3, (int) student.getId());
    }
}
