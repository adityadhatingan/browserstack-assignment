package components.constants;

import org.openqa.selenium.By;

public class SmartPriceWebPageConstants {

    public static final By MOBILE_DROPDOWN = By.xpath("//a[@data-label='Mobiles']");
    public static final By COMPARE_MOBILES_BUTTON = By.xpath("//a[@data-label='Mobiles::Compare Mobiles']");
    public static final String INPUT_MOBILE_NAME = "(//div[@class='list-info']//div[@id='compare_prd']//div[@class='compare-table compare-mobile-top']//div[@class='compare-row']//div[@class='block']//div[@class='compare-suggested']//ul[@class='compAttr']//li//a[@href='#PriceList'][normalize-space()='Check All Prices']//following::input)[1]";
    public static final By INPUT_MOBILE_1 = By.cssSelector("#searchField_0");
    public static final By INPUT_MOBILE_2 = By.cssSelector("#searchField_1");
    public static final By INPUT_MOBILE_3 = By.cssSelector("#searchField_2");
    public static final By INPUT_MOBILE_4 = By.cssSelector("#searchField_3");
    public static final String SELECT_MOBILE_FROM_DROPDOWN = "//li[@class='ui-menu-item']//div[@class='pro_name']//div[contains(text(), '%s')]";
}
