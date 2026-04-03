import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

public class AlertTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // open the html file
        driver.get("file:///C:/Users/Nishchal%20Gupta/Desktop/selenium-project-demo/selenium-project-demo/alerts.html");

        WebElement simpleAlertBtn = driver.findElement(By.xpath(
                "//button[text()='Simple Alert']"));

        simpleAlertBtn.click();

        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());
        Thread.sleep(2000);

        alert.accept();// Clicks on OK

    }
}