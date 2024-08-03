package utils;

import java.util.Arrays;
import java.util.List;

public class BrowserUtil {

    public static Settings currentSettings;
    public static String browser;
    private static final List<String> VALID_BROWSERS = Arrays.asList("chrome", "firefox");

    /**
     * Checks whether the browser entered is valid
     *
     * @param browser The browser to check
     *
     * @throws IllegalArgumentException If the browser is invalid
     */
    public static void checkBrowser(String browser) throws IllegalArgumentException {
        if (!VALID_BROWSERS.contains(browser)) {
            throw new IllegalArgumentException("Invalid Browser value: " + browser);
        }
    }

    // This method gets the browser from command line or from the yml file.
    public static String setBrowser() {
        browser = System.getProperty("browser");
        if (browser == null) {
            browser = Settings.getSettings().getBrowser();
        }
        Settings.getSettings().setBrowser(browser);
        BrowserUtil.checkBrowser(browser);
        return browser;
    }
}
