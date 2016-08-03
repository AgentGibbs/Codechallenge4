package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import skiUtahPageClasses.SkiUtahHomePage;

public class TestNGTests {

	public String homeUrl;
	public SkiUtahHomePage page;
	public WebDriver browserDriver;
	public Reporter reporter;

	@BeforeSuite
	public void Setup()
	{
		reporter = new Reporter();
		browserDriver = new ChromeDriver();
		browserDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		SkiUtahHomePage page = new SkiUtahHomePage(browserDriver);
	}

	/**
	 * Passes a valid argument with spaces and verifies that correct information is found and returned.
	 */
	@Test
	public void verifyMinutesFromAirport1() {
		page = new SkiUtahHomePage(browserDriver);
		page.openComparisonMap();

		String minutes = page.timeFromAirport("Alta");
		String minutesLowerCase = minutes.toLowerCase();
		Assert.assertEquals(minutesLowerCase, "41 minutes from nearest airport");
	}

	/**
	 * Passes a valid argument with spaces and verifies that correct information is found and returned.
	 */
	@Test
	public void verifyMinutesFromAirport2() {
		page = new SkiUtahHomePage(browserDriver);
		page.openComparisonMap();
		String minutes = page.timeFromAirport("Beaver Mountain");
		String minutesLowerCase = minutes.toLowerCase();
		Assert.assertEquals(minutesLowerCase, "120 minutes from nearest airport");

	}
/**
 * passes an invalid argument and verifies "graceful failure."
 */
	@Test
	public void verifyMinutesFromAirport3() {
		page = new SkiUtahHomePage(browserDriver);
		page.openComparisonMap();
		String minutes = page.timeFromAirport("Matt Chiang's private resort");
		String minutesLowerCase = minutes.toLowerCase();
		Assert.assertEquals(minutesLowerCase, "unable to locate resort matt-chiang's-private-resort");

	}

	@AfterTest
	public void closeBrowser() {
		browserDriver.quit();
	}
}
