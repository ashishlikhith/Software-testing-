import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.webdriver.common.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.io.File;

public class popup {
    public static void main(String[] args) throws InterruptedException {
        try {
            // Initialize ChromeDriver
            WebDriver driver = new ChromeDriver();
            
            // Set timeouts
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Get the correct path to popup.html
            String htmlPath = getHtmlFilePath();
            System.out.println("Loading HTML file from: " + htmlPath);
            driver.get("file:///" + htmlPath.replace("\\", "/"));
            
            System.out.println("Page title: " + driver.getTitle());

            // Find and click the Simple Alert button
            WebElement simpleAlertBtn = driver.findElement(By.xpath(
                    "//button[contains(text(), 'Simple Alert')]"));
            
            System.out.println("Clicking Simple Alert button...");
            simpleAlertBtn.click();

            // Wait for and handle the alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            
            Thread.sleep(2000);
            
            alert.accept(); // Clicks on OK
            
            System.out.println("Alert accepted successfully!");
            
            // Close the browser
            driver.quit();
            System.out.println("Test completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static String getHtmlFilePath() {
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            File projectRoot = currentDir.getParentFile();
            File htmlFile = new File(projectRoot, "html" + File.separator + "popup.html");
            
            if (htmlFile.exists()) {
                return htmlFile.getAbsolutePath();
            } else {
                throw new RuntimeException("HTML file not found at: " + htmlFile.getAbsolutePath());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not locate HTML file: " + e.getMessage());
        }
    }
}