package cucumber;

import com.khanivrous.app.StudentService;
import com.khanivrous.app.StudentServiceGenerator;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.mockwebserver.MockWebServer;

public class StepDefinitions {

    private MockWebServer mockWebServer;

    @Before


    @Given("I send a GET request to the students api at path {string}")
    public void i_send_a_GET_request_to_the_students_api_at_path(String string) {
        StudentService service = StudentServiceGenerator.createService(StudentService.class);
        service.getStudentById(string);
    }

    @When("I receive the response")
    public void i_receive_the_response() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I print the students Barry and {int} to the command line")
    public void i_print_the_students_Barry_and_to_the_command_line(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I print the students Sheila and {int} to the command line")
    public void i_print_the_students_Sheila_and_to_the_command_line(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("I send a GET request to the students api")
    public void i_send_a_GET_request_to_the_students_api() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I all the students Barry and {int}")
    public void i_all_the_students_Barry_and(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("I all the students Sheila and {int}")
    public void i_all_the_students_Sheila_and(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
