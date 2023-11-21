package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){
        // Click on sign link
        driver.findElement(By.linkText("Sign In")).click();

        //Verify the text 'Welcome Back!'
        String expectedText = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[@class='page__heading']"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Welcome Back!",expectedText,actualText);
    }
    @Test
    public void verifyTheErrorMessage() throws InterruptedException {
        // Click on sign link
        driver.findElement(By.linkText("Sign In")).click();
        //Enter invalid username
        driver.findElement(By.id("user[email]")).sendKeys("hello");
        //Enter invalid password
        driver.findElement(By.id("user[password]")).sendKeys("hello12");
        //click on login button
        driver.findElement(By.xpath("//button[@type='submit'or@class='button button-primary g-recaptcha']")).click();
        Thread.sleep(5000);
        //verify the error message
        String expectedText = "Please enter a valid email address";
        WebElement actualTextElement = driver.findElement(By.id("user[email]-error"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Invalid email or password",expectedText,actualText);
    }

    @After
    public void closeBrowser(){
        driver.close();
    }

}
