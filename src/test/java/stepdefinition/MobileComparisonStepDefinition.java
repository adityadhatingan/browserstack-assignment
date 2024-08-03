package stepdefinition;

import components.SmartPriceWebPage;

import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class MobileComparisonStepDefinition {
    private final WebDriver driver;
    private final SmartPriceWebPage smartPriceWebPage;

    public MobileComparisonStepDefinition() {
        driver = DriverFactory.getDriver();
        smartPriceWebPage = new SmartPriceWebPage(driver);
    }

    @Given("user is on mysmartprice website")
    public void userIsOnMysmartpriceWebsite() {
        smartPriceWebPage.gotoSmartPriceWebPage();
    }

    @When("user clicks on compare mobile inside mobiles tab")
    public void userClicksOnCompareMobileInsideMobilesTab() throws InterruptedException {
        smartPriceWebPage.hoverOverMobileDropDown();
        smartPriceWebPage.clickCompareMobileOption();
    }

    @And("user selects {string} mobile {string} for comparison")
    public void userSelectsMobileForComparison(String mobileCount, String mobileName) throws InterruptedException {
        smartPriceWebPage.selectMobileForComparison(Integer.parseInt(mobileCount), mobileName);
    }

    @Then("user gets the msp score and price of each mobile")
    public void userGetsTheMspScoreAndPriceOfEachMobile() {
        //smartPriceWebPage.fetchMspScoreAndPrice();
        Assert.assertFalse(smartPriceWebPage.priceEmpty(), "Prices not found");
        Assert.assertFalse(smartPriceWebPage.mspScoreEmpty(), "MSP scores not found");
    }

    @Then("we get the lowest price with msp score above {double}")
    public void weGetTheLowestPriceWithMspScoreAbove(String mspThreshold) {
        //double lowestPrice = smartPriceWebPage.getLowestPriceWithMspScoreAbove(Double.parseDouble(mspThreshold));
        //Assert.assertTrue(lowestPrice > 0, "No mobile found with MSP score above " + mspThreshold);
        //System.out.println("Lowest price with MSP score above " + mspThreshold + ": " + lowestPrice);
    }
}
