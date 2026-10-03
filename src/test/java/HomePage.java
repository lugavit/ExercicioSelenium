import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By logo = By.cssSelector("div.logo.pull-left");
    private final By loginLink = By.xpath("//a[contains(@href, '/login')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo)).isDisplayed();
    }

    public LoginPage clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver, wait);
    }
}
