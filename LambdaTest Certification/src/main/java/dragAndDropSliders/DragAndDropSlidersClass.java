package dragAndDropSliders;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class DragAndDropSlidersClass {

    private WebDriver driver; 
    private String Status = "passed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest"); 

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "sjayachandran1507");
        ltOptions.put("accessKey", "fH7FXiCyrRWKGvYjxGQeBjx8oCJyqRp7bERNttjrA2ZMzayefT");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("build", "SeleniumBuild");
        ltOptions.put("project", "Selenium");
//        ltOptions.put("tunnel", false);
        ltOptions.put("selenium_version", "4.6.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
    }

    @Test
    public void TestScenario2() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Click on "Drag & Drop Sliders"
        WebElement dragAndDropLink = driver.findElement(
                By.xpath("(//a[contains(text(),'Drag & Drop Sliders')])[1]"));
        dragAndDropLink.click();
        Thread.sleep(2000);

        // Locate the slider and output
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));
        WebElement output = driver.findElement(By.xpath("//output")); 

        Actions actions = new Actions(driver);

        int targetValue = 95;
        int maxTries = 15;
        int moveBy = 5;
        int currentValue = Integer.parseInt(output.getText());

        while (currentValue != targetValue && maxTries > 0) {
            if (currentValue < targetValue) {
                actions.clickAndHold(slider).moveByOffset(moveBy, 0).release().perform();
            } else {
                actions.clickAndHold(slider).moveByOffset(-moveBy, 0).release().perform();
            }

            Thread.sleep(2000);
            currentValue = Integer.parseInt(output.getText());
            maxTries--;
        }

        // Verify the actual range
        System.out.println("Slider moved to: " + currentValue);
        if (currentValue == targetValue) {
            System.out.println("Range is matched!");
        } else {
            System.out.println("Range is NOT matched! Expected: " + targetValue + ", but got: " + currentValue);
        }
    }

    @AfterMethod
    public void tearDown() {
//        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}