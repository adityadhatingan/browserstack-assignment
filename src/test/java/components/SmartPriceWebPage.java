package components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import components.constants.SmartPriceWebPageConstants;

public class SmartPriceWebPage {
    private final WebDriver driver;
    private final List<Double> prices = new ArrayList<>();
    private final List<Double> mspScores = new ArrayList<>();

    public SmartPriceWebPage(WebDriver driver) {
        this.driver = driver;
    }

    public void gotoSmartPriceWebPage() {
        this.driver.get("https://www.mysmartprice.com/");
    }

    public void hoverOverMobileDropDown() {
        Actions actions = new Actions(driver);
        WebElement mobileDropDown = this.driver.findElement(SmartPriceWebPageConstants.MOBILE_DROPDOWN);
        actions.moveToElement(mobileDropDown).perform();
    }

    public void clickCompareMobileOption() {
        this.driver.findElement(SmartPriceWebPageConstants.COMPARE_MOBILES_BUTTON).click();
    }

    public void selectMobileForComparison(int inputNumber, String mobileName) throws InterruptedException {
        String xpath = "//input[@placeholder='Add a mobile']";
        List<WebElement> inputFields = driver.findElements(By.xpath(xpath));

        for (WebElement inputField : inputFields) {
            try {
                Thread.sleep(5000);
                if (inputField.isDisplayed() && inputField.isEnabled()) {
                    inputField.click();
                    Thread.sleep(5000);
                    inputField.sendKeys(mobileName);
                    Thread.sleep(5000);
                    Actions actions = new Actions(driver);
                    actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
                    break;
                }
            } catch (StaleElementReferenceException | NoSuchElementException | ElementNotInteractableException e) {
                System.out.println("Element interaction failed: " + e.getMessage());
            }
        }
    }

    public void fetchMspScoreAndPrice() {
        List<WebElement> priceElements = driver.findElements(By.xpath("null"));
        List<WebElement> mspScoreElements = driver.findElements(By.xpath("null"));

        for (WebElement priceElement : priceElements) {
            try {
                double price = Double.parseDouble(priceElement.getText().replace("$", "").trim());
                prices.add(price);
            } catch (NumberFormatException e) {
                prices.add(Double.NaN);
            }
        }

        for (WebElement mspScoreElement : mspScoreElements) {
            try {
                double mspScore = Double.parseDouble(mspScoreElement.getText().trim());
                mspScores.add(mspScore);
            } catch (NumberFormatException e) {
                mspScores.add(Double.NaN);
            }
        }
    }

    public double getLowestPriceWithMspScoreAbove(double mspThreshold) {
        double lowestPrice = Double.MAX_VALUE;

        for (int i = 0; i < prices.size(); i++) {
            double price = prices.get(i);
            double mspScore = mspScores.get(i);

            if (!Double.isNaN(price) && !Double.isNaN(mspScore) && mspScore > mspThreshold) {
                if (price < lowestPrice) {
                    lowestPrice = price;
                }
            }
        }

        return lowestPrice == Double.MAX_VALUE ? 0 : lowestPrice;
    }

    public boolean priceEmpty() {
        return prices.isEmpty();
    }

    public boolean mspScoreEmpty() {
        return mspScores.isEmpty();
    }
}
