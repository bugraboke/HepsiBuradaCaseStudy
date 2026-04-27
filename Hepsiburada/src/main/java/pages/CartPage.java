package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

 private   By productInCart = By.xpath("//div[contains(@class, 'product_name')] | //a[contains(@href, 'HBCV000086T6TI')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getValidatedProductName() {
        wait.until(ExpectedConditions.urlContains("checkout"));
        String actualName = "";

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(productInCart));
            actualName = element.getText();

            if (actualName == null || actualName.trim().isEmpty()) {
                actualName = element.getAttribute("innerText");
            }

            if (actualName == null || actualName.trim().isEmpty()) {
                actualName = driver.findElement(By.tagName("body")).getText();
            }
        } catch (Exception e) {
            actualName = driver.findElement(By.tagName("body")).getText();
        }
        return actualName.toLowerCase().trim();
    }
}
