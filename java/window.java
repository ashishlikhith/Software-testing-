import java.nio.file.Paths;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String htmlPath = Paths.get("html/window.html").toAbsolutePath().toUri().toString();
        driver.get(htmlPath);

        WebElement btn = driver.findElement(By.xpath("//button[text()='Open New Tab']"));

        btn.click();

        String mainWindow = driver.getWindowHandle();
        System.out.println("tabId: " + mainWindow);

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
            }
        }

        System.out.println(driver.getTitle());
        Thread.sleep(3000);
        driver.close();
        Thread.sleep(3000);
        driver.switchTo().window(mainWindow);
        driver.quit();
    }
}
