package cucumber;

import com.khanivrous.app.PrintStudentInformation;
import com.khanivrous.app.models.StudentModel;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    private MockWebServer server;
    private Call<StudentModel> student;
    private String ID_1 = "{\"id\": 1,\"name\": \"Barry\",\"age\": 20}";
    private String ID_2 = "{\"id\": 2,\"name\": \"Sheila\",\"age\": 19}";

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        HttpUrl baseUrl = server.url("https://localhost:9292/");
    }

    @Given("I send a successful GET request to the students api at path {string}")
    public void i_send_a_successful_GET_request_to_the_students_api_at_path(String string) throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(ID_1));
        PrintStudentInformation.printStudentDetailsById(string);
    }


    @Then("I print the students Barry and {int} to the command line")
    public void i_print_the_students_Barry_and_to_the_command_line(Integer int1) throws IOException {
        String expected = "Name: Barry, Age: "+int1.toString();
        server.enqueue(new MockResponse()
                    .setResponseCode(200)
                    .setBody(ID_1));
        assertEquals(expected,PrintStudentInformation.printStudentDetailsById("1"));
    }

    @Then("I print the students Sheila and {int} to the command line")
    public void i_print_the_students_Sheila_and_to_the_command_line(Integer int1) throws IOException {
        String expected = "Name: Sheila, Age: "+int1.toString();
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(ID_2));
        assertEquals(expected,PrintStudentInformation.printStudentDetailsById("2"));
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
