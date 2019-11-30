package cucumber;

import com.khanivrous.app.StudentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static com.khanivrous.app.StudentServiceGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private StudentService service;
    private String ID_1 = "{\"id\": 1,\"name\": \"Barry\",\"age\": 20}";
    private String ID_2 = "{\"id\": 2,\"name\": \"Sheila\",\"age\": 19}";

    private String STUDENT_LIST = "[{\"id\": 1,\"name\": \"Barry\",\"age\": 20},{\"id\": 2,\"name\": \"Sheila\",\"age\": 19}]";

    @Before
    public void setUp() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void tearDown() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.shutdown();
    }

    @Given("I send a successful GET request to the students api at path {string}")
    public void i_send_a_successful_GET_request_to_the_students_api_at_path(String string) throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(StudentService.class);
        switch (string){
            case "1":
                mockWebServer.enqueue(new MockResponse()
                        .setResponseCode(200)
                        .setBody(ID_1));
                break;
            case "2":
                mockWebServer.enqueue(new MockResponse()
                        .setResponseCode(200)
                        .setBody(ID_2));
        }
    }


    @Then("I print the students Barry and {int} to the command line")
    public void i_print_the_students_Barry_and_to_the_command_line(Integer int1) throws IOException {
        String expected = "Name: Barry, Age: "+int1.toString();
        assertEquals(expected, printStudentDetailsById("1",service));
    }

    @Then("I print the students Sheila and {int} to the command line")
    public void i_print_the_students_Sheila_and_to_the_command_line(Integer int1) throws IOException {
        String expected = "Name: Sheila, Age: "+int1.toString();
        assertEquals(expected, printStudentDetailsById("2",service));
    }

    @Given("I send a successful GET request to the students api")
    public void i_send_a_successful_GET_request_to_the_students_api() {
        MockWebServer mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(StudentService.class);
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(STUDENT_LIST));
    }

    @Then("I print all the students Barry and {int}")
    public void i_print_all_the_students_Barry_and(Integer int1) {
        String expected = "Name: Barry, Age: "+int1.toString();
        assertTrue(printAllStudentsDetails(service).contains(expected));
    }

    @Then("I print all the students Sheila and {int}")
    public void i_print_all_the_students_Sheila_and(Integer int1) {
        String expected = "Name: Sheila, Age: "+int1.toString();
        assertTrue(printAllStudentsDetails(service).contains(expected));
    }
}
