package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationSteps {
    WebDriver driver;


    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yamex\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void fillBirthDate(String birthDate) {
        try {
            // Hitta födelsedatumfältet
            WebElement dateField = driver.findElement(By.id("dp"));

            // Använd JavascriptExecutor för att rensa fältet, sätta värdet och trigga events
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript(
                    "arguments[0].value = ''; " +  // Rensa fältet först
                            "arguments[0].value = arguments[1]; " +  // Sätt det nya värdet
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    dateField,
                    birthDate
            );

            System.out.println("Filled in birth date: " + birthDate);
        } catch (NoSuchElementException e) {
            System.out.println("Could not find the birth date field.");
        }
    }

    @When("I fill in the registration form with valid data")
    public void iFillInTheRegistrationFormWithValidData() {
        try {

            fillBirthDate("01/01/2000");

            // Förnamn
            WebElement firstNameField = driver.findElement(By.id("member_firstname"));
            firstNameField.sendKeys("John");

            // Efternamn
            WebElement lastNameField = driver.findElement(By.id("member_lastname"));
            lastNameField.sendKeys("Doe");

            // Email
            WebElement emailField = driver.findElement(By.id("member_emailaddress"));
            emailField.sendKeys("test@example.com");

            // Bekräfta email
            WebElement confirmEmailField = driver.findElement(By.id("member_confirmemailaddress"));
            confirmEmailField.sendKeys("test@example.com");



            // Lösenord
            WebElement passwordField = driver.findElement(By.id("signupunlicenced_password"));
            passwordField.sendKeys("SecurePassword123!");

            // Bekräfta lösenord
            WebElement confirmPasswordField = driver.findElement(By.id("signupunlicenced_confirmpassword"));
            confirmPasswordField.sendKeys("SecurePassword123!");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('sign_up_25').click();");
            js.executeScript("document.getElementById('sign_up_26').click();");
            js.executeScript("document.getElementById('fanmembersignup_agreetocodeofethicsandconduct').click();");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @When("I fill in the registration form without a last name")
    public void iFillInTheRegistrationFormWithoutALastName() {
        try {

            fillBirthDate("01/01/2000");

            // Förnamn
            WebElement firstNameField = driver.findElement(By.id("member_firstname"));
            firstNameField.sendKeys("John");

            // Email
            WebElement emailField = driver.findElement(By.id("member_emailaddress"));
            emailField.sendKeys("test@example.com");

            // Bekräfta email
            WebElement confirmEmailField = driver.findElement(By.id("member_confirmemailaddress"));
            confirmEmailField.sendKeys("test@example.com");



            // Lösenord
            WebElement passwordField = driver.findElement(By.id("signupunlicenced_password"));
            passwordField.sendKeys("SecurePassword123!");

            // Bekräfta lösenord
            WebElement confirmPasswordField = driver.findElement(By.id("signupunlicenced_confirmpassword"));
            confirmPasswordField.sendKeys("SecurePassword123!");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('sign_up_25').click();");
            js.executeScript("document.getElementById('sign_up_26').click();");
            js.executeScript("document.getElementById('fanmembersignup_agreetocodeofethicsandconduct').click();");
        } catch (NoSuchElementException e) {
            System.out.println("Could not find an element: " + e.getMessage());
        }
    }


    @When("I enter valid information except the passwords do not match")
    public void iEnterValidInformationExceptThePasswordsDoNotMatch() {
        try {


            WebElement firstNameField = driver.findElement(By.id("member_firstname"));
            firstNameField.sendKeys("John");

            WebElement lastNameField = driver.findElement(By.id("member_lastname"));
            lastNameField.sendKeys("Doe");

            WebElement emailField = driver.findElement(By.id("member_emailaddress"));
            emailField.sendKeys("john.doe@example.com");

            WebElement confirmEmailField = driver.findElement(By.id("member_confirmemailaddress"));
            confirmEmailField.sendKeys("john.doe@example.com");

            fillBirthDate("01/01/2000");

            // Ange olika lösenord
            WebElement passwordField = driver.findElement(By.id("signupunlicenced_password"));
            WebElement confirmPasswordField = driver.findElement(By.id("signupunlicenced_confirmpassword"));
            passwordField.sendKeys("Password123");
            confirmPasswordField.sendKeys("Mismatch123");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('sign_up_25').click();");
            js.executeScript("document.getElementById('sign_up_26').click();");
            js.executeScript("document.getElementById('fanmembersignup_agreetocodeofethicsandconduct').click();");
        } catch (NoSuchElementException e) {
            System.out.println("An element was not found while entering information!");
        }
    }

    @When("I fill in the registration form without accepting terms and conditions")
    public void iFillInTheRegistrationFormWithoutAcceptingTermsAndConditions() {
        try {

            fillBirthDate("01/01/2000");

            WebElement firstNameField = driver.findElement(By.id("member_firstname"));
            firstNameField.sendKeys("John");

            WebElement lastNameField = driver.findElement(By.id("member_lastname"));
            lastNameField.sendKeys("Doe");

            WebElement emailField = driver.findElement(By.id("member_emailaddress"));
            emailField.sendKeys("johndoe@example.com");

            WebElement confirmEmailField = driver.findElement(By.id("member_confirmemailaddress"));
            confirmEmailField.sendKeys("johndoe@example.com");

            WebElement passwordField = driver.findElement(By.id("signupunlicenced_password"));
            passwordField.sendKeys("Test1234!");

            WebElement confirmPasswordField = driver.findElement(By.id("signupunlicenced_confirmpassword"));
            confirmPasswordField.sendKeys("Test1234!");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('sign_up_26').click();");
            js.executeScript("document.getElementById('fanmembersignup_agreetocodeofethicsandconduct').click();");


        } catch (Exception e) {
            System.out.println("Error filling form: " + e.getMessage());
        }
    }



    @Then("I should see an error message indicating the passwords do not match")
    public void iShouldSeeAnErrorMessageIndicatingThePasswordsDoNotMatch() {
        // Leta efter felmeddelandet
        WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'Passwords do not match')]"));

        // Kontrollera om meddelandet finns och logga resultatet
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message displayed: Passwords do not match.");
        } else {
            System.out.println("Error message not displayed.");
        }
    }

    @Then("I should see an error message indicating that the last name is required")
    public void iShouldSeeAnErrorMessageForLastName() {
        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message"));
        String actualMessage = errorMessage.getText();
        Assert.assertEquals("Last name is required", actualMessage);
    }

    @Then("I should see an error message indicating terms and conditions must be accepted")
    public void iShouldSeeAnErrorMessageIndicatingTermsAndConditionsMustBeAccepted() {
        try {
            WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(), 'You must accept the terms and conditions')]"));

            if (errorMessage.isDisplayed()) {
                System.out.println("Error message displayed: You must accept the terms and conditions.");
            } else {
                System.out.println("Error message not displayed.");
            }
        } catch (Exception e) {
            System.out.println("Error message not found: " + e.getMessage());
        }
    }


    @When("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        WebElement confirmButton = driver.findElement(By.xpath("//input[@type='submit' and @name='join' and @value='CONFIRM AND JOIN']"));
        confirmButton.click();
    }



}
