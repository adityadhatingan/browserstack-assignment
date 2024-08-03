package stepdefinition.hook;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.Settings;

public class ApplicationHooks {
	private DriverFactory driverFactory;
	private WebDriver driver;
	private Settings currentSettings;
	public static ThreadLocal<Scenario> currentScenario = new ThreadLocal<>();

	@Before(order = 0)
	public void launchBrowser(Scenario scenario) {
		currentSettings = Settings.getSettings();
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(scenario.getName());
		
		// set the Scenario for current executing thread in ThreadLocal variable
		currentScenario.set(scenario);
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		//driver.quit();
	}
}
