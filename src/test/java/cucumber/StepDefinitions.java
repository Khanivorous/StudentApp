package cucumber;

import com.khanivrous.app.PrintStudentInformation;
import com.khanivrous.app.StudentService;
import com.khanivrous.app.models.StudentModel;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private StudentService service;
    private String ID_1 = "{\"id\": 1,\"name\": \"Barry\",\"age\": 20}";
    private String ID_2 = "{\"id\": 2,\"name\": \"Sheila\",\"age\": 19}";

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(mockWebServer.url("/")).addConverterFactory(GsonConverterFactory.create()).build();
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
//        Call<StudentModel> studentModelCall = service.getStudentById(string);
    }


    @Then("I print the students Barry and {int} to the command line")
    public void i_print_the_students_Barry_and_to_the_command_line(Integer int1) throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        String expected = "Name: Barry, Age: "+int1.toString();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(ID_1));
        Call<StudentModel> studentModelCall = service.getStudentById("1");
        Response<StudentModel> response = studentModelCall.execute();
        StudentModel student = response.body();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        System.out.println(studentResponseString);
        assertEquals(expected,studentResponseString);
    }

    @Then("I print the students Sheila and {int} to the command line")
    public void i_print_the_students_Sheila_and_to_the_command_line(Integer int1) throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        String expected = "Name: Sheila, Age: "+int1.toString();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(ID_2));
        Call<StudentModel> studentModelCall = service.getStudentById("2");
        Response<StudentModel> response = studentModelCall.execute();
        StudentModel student = response.body();
        String studentResponseString = "Name: " + student.getName() + ", Age: " + student.getAge();
        System.out.println(studentResponseString);
        assertEquals(expected,studentResponseString);
    }

    @Given("I send a successful GET request to the students api")
    public void i_send_a_successful_GET_request_to_the_students_api() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I print all the students Barry and {int}")
    public void i_print_all_the_students_Barry_and(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I print all the students Sheila and {int}")
    public void i_print_all_the_students_Sheila_and(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
