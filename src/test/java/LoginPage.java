import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By loginHeader = By.xpath("//div[@class='login-form']/h2");
    private final By signupHeader = By.xpath("//div[@class='signup-form']/h2");
    private final By emailInput = By.cssSelector("input[data-qa='login-email']");
    private final By passwordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getLoginHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).getText();
    }

    public void loginWith(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getSignupHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signupHeader)).getText();
    }

    public SignupPage signupNewUser(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupNameInput)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupEmailInput)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
        return new SignupPage(driver, wait);
    }
}
