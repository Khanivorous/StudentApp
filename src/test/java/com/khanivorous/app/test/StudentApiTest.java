package com.khanivorous.app.test;

import com.khanivrous.app.StudentApiService;
import org.junit.jupiter.api.Test;

import static com.khanivrous.app.StudentApi.createService;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

class StudentApiTest {

    @Test
    public void testCreateService() {
        StudentApiService service = createService(StudentApiService.class);
        assertThat(service,instanceOf(StudentApiService.class));
    }
}