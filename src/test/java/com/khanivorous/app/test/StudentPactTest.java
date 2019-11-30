package com.khanivorous.app.test;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.khanivrous.app.StudentApiService;
import com.khanivrous.app.models.Student;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentPactTest {

    private StudentApiService service;
    private String ID_1 = "{\"id\": 1,\"name\": \"Barry\",\"age\": 20}";

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("StudentsApi", "localhost", 9090, this);

    @Before
    public void setUp() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:9090")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient).build();
        service = retrofit.create(StudentApiService.class);
    }

    @Pact(provider = "StudentsApi", consumer = "javaClient")
    public RequestResponsePact createPact(@NotNull PactDslWithProvider builder) {
        return builder
                .given("Student data is not empty")
                .uponReceiving("A request for a student by id")
                .path("/students/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(ID_1)
                .toPact();
    }

    @Test
    @PactVerification({"StudentsApi"})
    public void shouldProcessStudentPayload() {
        Student student = service.getStudentById("1").blockingFirst();
        assertEquals("Barry", student.getName());
        assertEquals(20, student.getAge());
    }
}
