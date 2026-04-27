package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class SearchPage {
  private  WebDriver driver;
  private  WebDriverWait wait;

    // Locators
    private By popupCloseBtn = By.cssSelector(".initialComponent-hk7c_9tvgJ8ELzRuGJwC");
    private By searchBox = By.xpath("//input[contains(@class, 'searchBarContent-Ufv')]");
    private By resultText = By.xpath("//h1[contains(@class, 'searchResultSummary')]");
    private By filters = By.cssSelector("[data-test-id='filterbox-item-display-name']");
    private By filterButton = By.xpath("//button[contains(@class, 'moria-PillButton')]");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void closePopupIfVisible() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(popupCloseBtn));
        driver.findElement(popupCloseBtn).click();
    }
    public void searchFor(String text) {

        driver.findElement(searchBox).sendKeys(text + Keys.ENTER);
    }
    public void applyFilter() {
        // 1. Elementi bekle ve bul
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", filter);
        js.executeScript("arguments[0].click();", filter);
        System.out.println(filter.getText());
        driver.navigate().refresh();
    }
    public String getResultSummary() {
        WebElement result=wait.until(ExpectedConditions.visibilityOfElementLocated(resultText));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = (String) js.executeScript("return arguments[0].innerText;", result);
        return text;

    }

    public void selectLastFilter() {
        List<WebElement> allFilters = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filters));
        allFilters.get(allFilters.size() - 1).click();
    }
}
