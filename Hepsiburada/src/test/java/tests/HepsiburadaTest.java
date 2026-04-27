package tests;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import pages.SearchPage;
import pages.ProductPage;
import pages.CartPage;
import java.util.Collections;
public class HepsiburadaTest {


    WebDriver driver;

    @BeforeMethod
    public void setup() {





        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.hepsiburada.com");

    }

    @Test
    public void hepsiburadatest() {
        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // 1. Arama ve Filtreleme

        searchPage.closePopupIfVisible();
        searchPage.searchFor("laptop");
        searchPage.getResultSummary();
        String sonuc = searchPage.getResultSummary();
        System.out.println(sonuc);
        searchPage.applyFilter();
        searchPage.selectLastFilter();
        String targetProduct = "Asus TUF Gaming FX607VU-RL017";

        // 2. Ürün Seçimi ve Sekme Değiştirme
        productPage.selectProduct(targetProduct);


        // Sekme değiştirme
        String originalWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!originalWindow.equals(handle)) driver.switchTo().window(handle);
        }

        // 3. Sepete Ekle ve Sepete Git
        productPage.verifyProductUrl(targetProduct);
        productPage.addToCartAndGo();

        // 4. Sepet ürününü Doğrulama
        String finalTitle = cartPage.getValidatedProductName();
        Assert.assertTrue(finalTitle.contains("asus"), "HATA: Ürün sepette bulunamadı!");
    }
}
