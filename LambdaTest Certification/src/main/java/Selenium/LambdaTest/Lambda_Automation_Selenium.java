package Selenium.LambdaTest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Lambda_Automation_Selenium {
    public String username = "sjayachandran1507";
    public String accesskey = "LT_fH7FXiCyrRWKGvYjxGQeBjx8oCJyqRp7bERNttjrA2ZMzayefT";
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    
    // ThreadLocal for parallel execution
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    @Parameters({"browser", "version", "platform"})
    @BeforeClass
    public void setUp(String browser, String version, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);

        try {
            driver.set(new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities));
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(priority = 1)
    public void Test_Scenario1() throws InterruptedException {
        getDriver().manage().deleteAllCookies();
        getDriver().get("https://www.lambdatest.com/selenium-playground");

        WebElement Demo = getDriver().findElement(By.xpath("//*[@id='__next']/div[1]/section[2]/div/div/div[1]/div[1]/ul/li[1]/a"));
        Thread.sleep(1500);
        Demo.click();

        String expectedURL = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
        Assert.assertEquals(getDriver().getCurrentUrl(), expectedURL);

        String Welcome = "Welcome to LambdaTest";
        WebElement Enter_Text = getDriver().findElement(By.xpath("//*[@id='user-message']"));
        Enter_Text.sendKeys(Welcome);
        Thread.sleep(2000);

        WebElement checked = getDriver().findElement(By.xpath("//*[@id='showInput']"));
        checked.click();
        Thread.sleep(2000);

        WebElement my_msg = getDriver().findElement(By.xpath("//*[@id='message']"));
        Assert.assertEquals(my_msg.getText(), Welcome);
    }

    
    
    @Test(priority = 3)
    public void Test_Scenario3() throws InterruptedException {
        getDriver().manage().deleteAllCookies();
        getDriver().get("https://www.lambdatest.com/selenium-playground/input-form-demo");

        WebElement submit = getDriver().findElement(By.cssSelector("#seleniumform > div.text-right.mt-20 > button"));
        submit.click();
        WebElement Name = getDriver().findElement(By.id("name"));

        String pleasefilloutthisform = Name.getAttribute("required");
        Assert.assertTrue(true, pleasefilloutthisform);

        String errormsg = "Please fill out this field.";
        Assert.assertTrue(true, errormsg);
        Thread.sleep(5000);

        WebElement country = getDriver().findElement(By.xpath("//*[@id='seleniumform']/div[3]/div[1]/select"));
        country.click();
        getDriver().findElement(By.cssSelector("#seleniumform > div:nth-child(3) > div.form-group.w-6\\/12.smtablet\\:w-full.pr-20.smtablet\\:pr-0 > select > option:nth-child(238)")).click();

        getDriver().findElement(By.xpath("//*[@id='name']")).sendKeys("Test Lambda");
        Thread.sleep(1000);
        getDriver().findElement(By.cssSelector("#inputEmail4")).sendKeys("TestData@gmail.com");
        Thread.sleep(1000);
        getDriver().findElement(By.id("inputPassword4")).sendKeys("Test_Lamda#@123");
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//*[@id='company']")).sendKeys("Test_Lamda pvt ltd.");
        Thread.sleep(1000);
        getDriver().findElement(By.cssSelector("#websitename")).sendKeys("www.Test_Lamda.com");
        Thread.sleep(1000);
        getDriver().findElement(By.id("inputCity")).sendKeys("Earth");
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//*[@id='inputAddress1']")).sendKeys("Earth_Air");
        Thread.sleep(1000);
        getDriver().findElement(By.cssSelector("#inputAddress2")).sendKeys("Earth Water");
        Thread.sleep(1000);
        getDriver().findElement(By.id("inputState")).sendKeys("Test_Lamda State");
        Thread.sleep(1000);
        getDriver().findElement(By.xpath("//*[@id='inputZip']")).sendKeys("111111");
        Thread.sleep(1000);
        submit.click();

        String submit_msg = "Thanks for contacting us, we will get back to you shortly.";
        String actual_submit = getDriver().findElement(By.xpath("//*[@id='__next']/div[1]/section[3]/div/div/div[2]/div/p")).getText();
        Assert.assertEquals(actual_submit, submit_msg);
        Thread.sleep(5000);

        getDriver().quit();
    }
}
