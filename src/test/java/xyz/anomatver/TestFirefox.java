package xyz.anomatver;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFirefox {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = Runner.forFirefox();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }


    @Test
    public void testCardsMasterText() {
        driver.get("https://www.banki.ru/card-master/");
        PageFactory.initElements(driver, this);
        String text = driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div[1]/div[1]/div[1]/span/a")).getText();
        assert Objects.equals(text, "Подобрать карту");
    }

    public TestFirefox goToCalculator() throws InterruptedException {
        driver.get("https://www.banki.ru/card-master/");
        PageFactory.initElements(driver, this);
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div[1]/div[1]/div[1]/span/a")).click();
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return this;
    }

    public TestFirefox goToStepTwo() throws InterruptedException {
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]/div[1]/div/div/label")).click();

        driver.findElement(new By.ByXPath("        /html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[2]/div/div[1]/div/div/label")).click();
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div/div/button")).click();
        Thread.sleep(2000);
        return this;
    }

    public TestFirefox goBack() throws InterruptedException {
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div[1]/button")).click();
        Thread.sleep(2000);
        return this;
    }

    public TestFirefox goToStepThree() throws InterruptedException {
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div/div[1]/div/label")).click();
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div[2]/div/button")).click();
        Thread.sleep(2000);
        return this;
    }

    public TestFirefox goToStepFour() throws InterruptedException {
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]/div[1]/div/div/label")).click();
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[2]/div/div[1]/div/div/label")).click();
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div[2]/div/button")).click();
        Thread.sleep(2000);
        return this;
    }


    public TestFirefox goToFinish() throws InterruptedException {
        driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div[2]/div/button")).click();
        Thread.sleep(2000);
        return this;
    }

    @ParameterizedTest()
    @ValueSource(strings = {"Кредитная", "Дебетовая"})
    public void testSelectCardType(String text) throws InterruptedException {
        goToCalculator();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement textBlock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]")));
        assert textBlock.getText().contains(text);
    }


    @ParameterizedTest()
    @ValueSource(strings = {"Совершать покупки", "Для получения наличных", "Получать кэшбэки и бонусы"})
    public void testPurposeForCredit(String text) throws InterruptedException {
        goToCalculator();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]/div[1]/div/div/label/span[1]")));
        radioButton.click();
        WebElement textBlock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[2]/div")));
        assert textBlock.getText().contains(text);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"Совершать покупки", "Получать доход на остаток", "Получать кэшбэки и бонусы", "Для переводов денег"})
    public void testPurposeForDebit(String text) throws InterruptedException {
        goToCalculator();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]/div[2]/div/div/label")));
        radioButton.click();
        WebElement textBlock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[2]/div")));
        assert textBlock.getText().contains(text);
    }

    @Test
    public void testNoCheckboxClicked() throws InterruptedException {
        goToCalculator();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[2]/div/div/div/div/button/span")));
        button.click();
        WebElement warning = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div/div[2]/div/div/div")));
        assert warning.getText().contains("Обязательное поле");
    }


    @ParameterizedTest()
    @ValueSource(strings = {"Процентная ставка по кредиту", "Стоимость годового обслуживания"})
    public void testPriorities(String text) throws InterruptedException {
        goToCalculator().goToStepTwo();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement textBlock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[1]")));
        assert textBlock.getText().contains(text);
    }

    @ParameterizedTest()
    @ValueSource(strings = {"Подойдет любой", "Чем больше, тем лучше"})
    public void testPeriod(String text) throws InterruptedException {
        goToCalculator().goToStepTwo();
        WebElement textBlock = driver.findElement(new By.ByXPath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[2]"));
        assert textBlock.getText().contains(text);
    }

    @Test
    public void testInvalidSizeLower15k() throws InterruptedException {
        goToCalculator().goToStepTwo();

        // Совершать покупки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement block = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[3]/div/div/div/div[1]/div[1]/div/input")));
        block.clear();
        block.sendKeys("value", "0");

        WebElement textBlock = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div/div/form/div/div[1]/div/div[3]/div/div/div[2]")));
        assert textBlock.getText().contains("Сумма кредита не может быть менее 15000 руб.");
    }

    @Test
    public void testSavesStepsInformation() throws InterruptedException {
        goToCalculator().goToStepTwo().goBack();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement radioButtonCredit = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(@class, 'Radio__sc-18evnr0-0') and .//span[contains(@class, 'RadioWithValue__StyledText-sc-rmfhfe-0') and text()='Кредитная']]//input[@type='radio']")));
        boolean isRadioSelected = radioButtonCredit.isSelected();
        assert  isRadioSelected;

        WebElement checkboxPurchase = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(@class, 'Checkbox__sc-9ttf87-1') and contains(., 'Совершать покупки')]//input[@type='checkbox']")));
        boolean isCheckboxSelected = checkboxPurchase.isSelected();

        assert isCheckboxSelected;
    }
}






