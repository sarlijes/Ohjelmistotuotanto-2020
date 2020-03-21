package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);

        testValidCredentials(driver);
        logoutUser(driver);
        testInvalidCredentials(driver);

        WebElement element = driver.findElement(By.linkText("back to home"));
        sleep(1);
        element.click();

        testCreateNewUser(driver);
        logoutUser(driver);

        driver.quit();

    }

    private static void logoutUser(WebDriver driver) {

        try {
            WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
            sleep(1);
            element.click();
        } catch (Exception e) {
        }

        WebElement logOutElement = driver.findElement(By.linkText("logout"));
        sleep(1);
        logOutElement.click();
    }

    private static void testCreateNewUser(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        inputCredentials("", "salaisuus123", driver, true, element);
    }

    private static void inputCredentials(String username, String password, WebDriver driver, boolean creatingNewUser, WebElement element) {

        sleep(1);

        if (creatingNewUser) {
            Random r = new Random();
            element = driver.findElement(By.name("username"));
            element.sendKeys("arto" + r.nextInt(100000));
        } else {
            element = driver.findElement(By.name("username"));
            element.sendKeys(username);
        }

        element = driver.findElement(By.name("password"));
        element.sendKeys(password);

        if (creatingNewUser) {
            element = driver.findElement(By.name("passwordConfirmation"));
            element.sendKeys(password);
            element = driver.findElement(By.name("signup"));
        } else {
            element = driver.findElement(By.name("login"));

        }

        sleep(1);
        element.submit();
    }

    private static void testInvalidCredentials(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        inputCredentials("pekka", "macarenabest525", driver, false, element);
        sleep(2);
    }

    private static void testValidCredentials(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        inputCredentials("pekka", "akkep", driver, false, element);
        sleep(2);
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
