import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExercicioTestesSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void iniciardriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @AfterEach
    public void fechardriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Test Case 3: Login User with incorrect email and password
    // Feito por Italo Ferreira
    // Parametros:
    @ParameterizedTest
    @CsvSource({
            "naocadastrado123@teste.com, senha123",
            "emailsemarroba.com, senha123",
            "email@semdominio, senha123",
            "'', senha123",
            "naocadastrado@teste.com, ''"
    })
    public void testecase3(String email, String password) {
        HomePage homePage = new HomePage(driver, wait);
        assertTrue(homePage.isLogoDisplayed());

        LoginPage loginPage = homePage.clickLogin();
        assertEquals("Login to your account", loginPage.getLoginHeaderText());
        loginPage.loginWith(email, password);
    }

    // Test Case 1: Register User
    // Feito por Lucas Sodré
    @Test
    public void testecase_registrarusuario() {
        HomePage homePage = new HomePage(driver, wait);
        assertTrue(homePage.isLogoDisplayed());

        LoginPage loginPage = homePage.clickLogin();
        assertEquals("New User Signup!", loginPage.getSignupHeaderText());

        String uniqueEmail = "qa_user_123@test.com";
        String userName = "QA Tester Aut";
        String password = "SenhaForte123!";

        SignupPage signupPage = loginPage.signupNewUser(userName, uniqueEmail);
        signupPage.selectTitleMr();
        signupPage.enterPassword(password);
        signupPage.selectBirthDate("15", "8", "1990");
        signupPage.selectNewsletterAndOptin();
        signupPage.fillAddressInformation(
                "QA",
                "Tester",
                "Tech QA",
                "Rua dos Testes, 123",
                "Apto 404",
                "United States",
                "California",
                "San Francisco",
                "94105",
                "+15551234567"
        );

        signupPage.clickCreateAccount();
        assertEquals("ACCOUNT CREATED!", signupPage.getAccountCreatedText());

        signupPage.continueAfterAccountCreated();
        assertTrue(signupPage.getLoggedInAsText().contains(userName));

        signupPage.deleteAccount();
        assertEquals("ACCOUNT DELETED!", signupPage.getAccountDeletedText());
        signupPage.continueAfterAccountDeleted();
    }
}
