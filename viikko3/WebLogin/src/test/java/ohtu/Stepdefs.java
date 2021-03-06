package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("back to home is selected")
    public void backToHomeIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userWithUsernameWithPasswordIsSuccessfullyCreated(String username, String password) {
        registerNewUserIsSelected();
        newUserFormIsFilledWithValidInformation(username, password);
    }

    @Given("command new user is selected")
    public void registerNewUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        registerNewUserIsSelected();
        newUserFormIsFilledWithValidInformation(username, password);
    }

    @When("user tries to log in with username {string} and password {string}")
    public void userTriesToLogIn(String username, String password) {
        loginIsSelected();
        logInWith(username, password);
    }


    @When("valid username {string} and password {string} and matching password confirmation are entered")
    public void newUserFormIsFilledWithValidInformation(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);

        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    @When("valid username {string} and password {string} and non-matching password confirmation {string} are entered")
    public void newUserFormIsFilledWithNonMatchingPasswords(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);

        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    @Then("new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("user is not created and error message shown about too short username")
    public void newUserIsNotCreatedForTooShortUsername() {
        pageHasContent("Create username and give password");
        pageHasContent("username should have at least 3 characters");
    }

    @Then("user is not created and error message shown about password length")
    public void newUserIsNotCreatedForTooShortPassword() {
        pageHasContent("Create username and give password");
        pageHasContent("username should have at least 3 characters");
    }

    @Then("user is not created and error password and password confirmation do not match is reported")
    public void newUserIsNotCreatedForNonmatchingPasswords() {
        pageHasContent("Create username and give password");
        pageHasContent("password and password confirmation do not match");
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("incorrect username {string} and incorrect password {string} are given")
    public void incorrectUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
