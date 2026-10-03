import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By titleMr = By.id("id_gender1");
    private final By passwordInput = By.cssSelector("input[data-qa='password']");
    private final By daysSelect = By.id("days");
    private final By monthsSelect = By.id("months");
    private final By yearsSelect = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By optin = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");
    private final By accountCreatedHeader = By.xpath("//h2[@data-qa='account-created']/b");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");
    private final By loggedInAs = By.xpath("//li/a[contains(text(), 'Logged in as')]");
    private final By deleteAccountLink = By.xpath("//a[contains(@href, '/delete_account')]");
    private final By accountDeletedHeader = By.xpath("//h2[@data-qa='account-deleted']/b");

    public SignupPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectTitleMr() {
        wait.until(ExpectedConditions.elementToBeClickable(titleMr)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    public void selectBirthDate(String day, String month, String year) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(daysSelect))).selectByValue(day);
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(monthsSelect))).selectByValue(month);
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(yearsSelect))).selectByValue(year);
    }

    public void selectNewsletterAndOptin() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletter)).click();
        wait.until(ExpectedConditions.elementToBeClickable(optin)).click();
    }

    public void fillAddressInformation(
            String firstNameValue,
            String lastNameValue,
            String companyValue,
            String address1Value,
            String address2Value,
            String countryValue,
            String stateValue,
            String cityValue,
            String zipcodeValue,
            String mobileNumberValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(firstNameValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lastNameValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(company)).sendKeys(companyValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address1)).sendKeys(address1Value);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address2)).sendKeys(address2Value);
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(country))).selectByVisibleText(countryValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(state)).sendKeys(stateValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(city)).sendKeys(cityValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipcode)).sendKeys(zipcodeValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumber)).sendKeys(mobileNumberValue);
    }

    public void clickCreateAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public String getAccountCreatedText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedHeader)).getText();
    }

    public void continueAfterAccountCreated() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getLoggedInAsText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs)).getText();
    }

    public void deleteAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
    }

    public String getAccountDeletedText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedHeader)).getText();
    }

    public void continueAfterAccountDeleted() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
