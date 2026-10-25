import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        // Initialize ChromeDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    public WebElement addProduct(String productName) {
        return driver.findElement(By.xpath("//h4[contains(text(), '" + productName + "')]/following-sibling::div[@class='product-action']/button"));
    }

    @Test
    public void testGoogleSearchForIndia() {
        try {
            // Step 1: Navigate to Google
            driver.navigate().to("https://www.google.com/");
            System.out.println("Navigated to Google.com");

            // Wait for page to load
            Thread.sleep(2000);

            // Step 2: Find the search input element
            WebElement searchBox = driver.findElement(By.name("q"));
            System.out.println("Found search box");

            // Step 3: Enter "India" in the search box
            searchBox.sendKeys("India");
            System.out.println("Typed 'India' in search box");

            // Step 4: Press Enter to search
            searchBox.submit();
            System.out.println("Submitted search");

            // Wait for search results to load
            Thread.sleep(3000);

            // Step 5: Verify search results
            String pageTitle = driver.getTitle();
            System.out.println("Page Title: " + pageTitle);

            // Verify the page contains search results for India
            Assert.assertTrue(pageTitle.contains("India"), "Search results page should contain 'India' in title");
            System.out.println("Test Passed: Search results page contains 'India'");

            // Step 6: Print current URL
            String currentURL = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentURL);
            Assert.assertTrue(currentURL.contains("q=India"), "URL should contain search parameter 'q=India'");

        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.fail("Test interrupted: " + e.getMessage());
        }
    }

    @Test
    public void adToCartTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        List<String> productNames = new ArrayList<>();
        productNames.add("Brocolli");
        productNames.add("Cauliflower");
        HashMap<String, Integer> hs = new HashMap<>();
        hs.put("Prasad", 23);
        hs.put("Aditi", 25);

        for (String key : hs.keySet()) {
            System.out.println("Key value is :" + key + " And value is :" + hs.get(key));
        }

//        for (String productName : productNames) {
//            addProduct(productName).click();
//        }
//
//        driver.findElement(By.cssSelector(".cart-icon")).click();
//        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
//        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
//
//        Select dropdown = new Select(driver.findElement(By.xpath("//label[text()='Choose Country']/parent::div/div/select")));
//        dropdown.selectByValue("India");
//        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
//        driver.findElement(By.cssSelector(".errorAlert b")).click();

    }

    @Test
    public void deutscheBankProblem() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String input = "Name := Tim Watson, Checkbox := standard, Type := Sports, Comment := Monica Singh - Email id";
        String[] values = input.split(",");
        HashMap<String, String> hs = new HashMap<>();
        for (String value : values) {
            String[] keyvalues = value.split(":=");
            hs.put(keyvalues[0].trim(), keyvalues[1].trim());
        }
        String name = hs.get("Name");
        String checkbox = hs.get("Checkbox");
        String type = hs.get("Type");
        System.out.println("name is " + name);


        driver.get("https://www.dezlearn.com/webtable-example/");
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
//        WebElement close = driver.findElement(By.xpath("(//button[@aria-label='Close'])[1]"));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@aria-label='Close'])[1]")));
//        driver.findElement(By.xpath("(//button[@aria-label='Close'])[1]")).click();

        WebElement baseLocator = driver.findElement(By.xpath("//td[text()='" + name + "']/ancestor::tr"));
        List<WebElement> checkboxes = baseLocator.findElements(By.xpath(".//input[@type='checkbox']"));
        if (checkbox.equalsIgnoreCase("premium")) {
            checkboxes.get(1).click();
        } else if (checkbox.equalsIgnoreCase("standard")) {
            checkboxes.get(0).click();
        }

        WebElement selectDropDown = baseLocator.findElement(By.xpath(".//select"));
        Select dropDown = new Select(selectDropDown);
        dropDown.selectByValue(type);
        String email = baseLocator.findElement(By.xpath(".//td[2]")).getText();
        baseLocator.findElement(By.xpath(".//input[@type='text']")).sendKeys(name + " - " + email);
    }

    @Test
    public void rahulShettyPractice() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement radio1 = driver.findElement(By.cssSelector("input[value='radio1']"));
        WebElement radio2 = driver.findElement(By.cssSelector("input[value='radio2']"));
        WebElement autoSuggestive = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        By country = By.xpath("//div[text()='India']");
        WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
        WebElement option2 = driver.findElement(By.id("checkBoxOption2"));
        WebElement dialogBox = driver.findElement(By.id("name"));
        WebElement alertButton = driver.findElement(By.id("alertbtn"));

        String nameVal = "Prasad";
        String expectedText = "Hello " + nameVal + ", share this practice page and share your knowledge";
        String tableName = "Ben";

        radio1.click();
        boolean isRadioSelected = radio1.isSelected();
        boolean isRadio2Selected = radio2.isSelected();
        Assert.assertTrue(isRadioSelected);
        Assert.assertFalse(isRadio2Selected);

        autoSuggestive.sendKeys("Ind");
        driver.findElement(country).click();

        Select dp = new Select(dropdown);
        dp.selectByValue("option2");
        option2.click();
        boolean isSelected = option2.isSelected();
        Assert.assertTrue(isSelected);

        dialogBox.sendKeys(nameVal);
        alertButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        System.out.println(message);
        alert.accept();
        Assert.assertEquals(message, expectedText);

        WebElement baseLocator = driver.findElement(By.xpath("//td[text()='" + tableName + "']/parent::tr"));
        String position = baseLocator.findElement(By.xpath(".//td[2]")).getText();
        System.out.println("position is " + position);

        List<WebElement> amounts = driver.findElements(By.xpath("//table[@id='product']/tbody/tr/td[4]"));
        int sum = 0;
        for (WebElement amount : amounts) {
            int amountVal = Integer.parseInt(amount.getText());
            sum +=  amountVal;
        }
        String expectedAmount = driver.findElement(By.className("totalAmount")).getText().split(":")[1].trim();
        String actualAmount = String.valueOf(sum);
        Assert.assertEquals(actualAmount, expectedAmount);
    }


    @AfterTest
    public void tearDown() {
        // Close the browser
        if (driver != null) {
//            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }
}

