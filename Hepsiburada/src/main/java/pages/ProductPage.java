package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;


    private By addToCartBtn = By.xpath("//button[@data-test-id='addToCart']");
    private By goToCartPopupBtn = By.xpath("//div[contains(@class, 'checkoutui-ProductOnBasketHeader')]/button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public void verifyProductUrl(String expectedProductName) {

        String formattedName = expectedProductName.toLowerCase().replace(" ", "-");


        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains(formattedName)) {
            System.out.println("DOĞRULAMA BAŞARILI: Ürününüzün sayfasındasınız. URL: " + currentUrl);
        } else {
            System.err.println("DOĞRULAMA HATASI: URL beklenen ismi içermiyor!");
            System.err.println("Beklenen: " + formattedName);
            System.err.println("Mevcut: " + currentUrl);
        }
    }

    public void selectProduct(String titlePart) {
        WebElement product = driver.findElement(By.xpath("//a[contains(@title, '" + titlePart + "')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        js.executeScript("arguments[0].click();", product);
    }

    public void addToCartAndGo() {
        WebDriverWait cart = new WebDriverWait(driver, Duration.ofSeconds(15));
        cart.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test-id='addToCart']")));
        WebElement addBtn = driver.findElement(addToCartBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addBtn);
        js.executeScript("arguments[0].click();", addBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartPopupBtn)).click();
    }
}
