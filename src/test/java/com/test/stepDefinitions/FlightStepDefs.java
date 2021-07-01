package com.test.stepDefinitions;

//import com.test.Hooks.hooks;
import com.test.Pages.*;
//import com.cucumber.listener.Reporter;
import com.google.inject.Inject;
import com.test.utils.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.test.utils.BrowserFactory.getBrowserDriver;

public class FlightStepDefs extends BaseFlight {
    Scenario scenario;



    @Inject
    AllFlightsPage allFlightsPage;
    @Inject
    UserDetailsPage userDetailsPage;
    @Inject
    FlightConfirmationPage flightConfirmationPage;

    @Inject
    public WelcomePage welcomePage;

    @Inject
    public BrowserFactory BrowserFactory;

    @Before
    public void setUp(){
        String browserName=prop.getProperty("browser");

        WebDriver driver =  getBrowserDriver(browserName);
//
//        if(browserName.equals("chrome")){
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//            driver= new ChromeDriver();
//            log.info("chrome browser opened");
//        }
//        else if(browserName.equals("firefox")){
//            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/firefoxdriver.exe");
//            driver= new FirefoxDriver();
//            log.info("firefox browser opened");
//        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scn){
//        if(scn.isFailed()){
//            TakesScreenshot scnShot=(TakesScreenshot)driver;
//            byte[] data=scnShot.getScreenshotAs(OutputType.BYTES);
//            scn.attach(data, "image/JPEG","failedScn");
//        }
        driver.quit();
    }



    @AfterStep
    public void addScreenshot(Scenario scenario){
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");

        }

    }





    @Given("User navigates to flight booking application")
    public void user_navigates_to_flight_booking_application() {
        String title= welcomePage.validateWelcomePageTitle();
        Assert.assertEquals("valid page title","BlazeDemo",title);
    }

    @When("user selects {string} and {string} city")
    public void userFromAndToCity(String departure, String destination) {
        welcomePage.selectDepartureCity(departure);
        welcomePage.selectDestinationCity(destination);
    }
    @And("click on Find Flights button")
    public void clickOnFindFlightsButton() {
        welcomePage.clickFindFlights();
    }
    @And("user choose flight to reserve ticket")
    public void userChooseFlightToReserveTicket() {
        allFlightsPage.clickChooseThisFlightButton();
    }

    @And("user enters all passenger details")
    public void userEntersAllPassengerDetailsAndClicksOnPurchaseFlightButton() {
        userDetailsPage.enterFirstName("Adamshafi");
        userDetailsPage.enterAddress("123 street");
        userDetailsPage.enterCity("Bangalore");
        userDetailsPage.enterCCNumber("12345");
        userDetailsPage.enterCCMonth("12");
        userDetailsPage.enterCCYear("2021");
        userDetailsPage.enterZipCode("12344");
        userDetailsPage.enterNameOnCard("Shafi");
        userDetailsPage.enterState("Karnataka");
    }

    @And("clicks on Purchase Flight button")
    public void clicksOnPurchaseFlightButton() {
        userDetailsPage.clickPurchaseFlight();
    }

    @Then("user validates purchase details")
    public void userValidatesPurchaseDetails() {
        String pid=flightConfirmationPage.getPurchaseID();
        Assert.assertTrue("purchase ID generated= "+pid,pid!=null);
        String msg=flightConfirmationPage.getThankuMsg();
        String expectedMsg="Thankk you for your purchase today!";
        Assert.assertTrue("Msg is valid",msg.equalsIgnoreCase(expectedMsg));

    }

    @Then("User validates {string} and {string} cities available")
    public void userValidatesAndCitiesAvailable(String fromCiti, String toCity) {
        List<String> fromOptions=welcomePage.getAllOptionsFrom();
        String[] optionsFrom= fromCiti.split(",");
        for (String option:optionsFrom) {
            if(fromOptions.contains(option)){
                Assert.assertTrue("all options available",true);
            }
            else Assert.assertTrue(option+" options NOT available",false);
        }
        List<String> toOptions=welcomePage.getAllOptionsTo();
        String[] optionsTo= toCity.split(",");
        for (String option:optionsTo) {
            if(toOptions.contains(option)){
                Assert.assertTrue("all options available",true);
            }
            else Assert.assertTrue(option+" options NOT available",false);
        }
    }


    @Then("user validates flight header message {string} and {string} city")
    public void userValidatesFlightHeaderMessageAndCity(String departure, String destination) {
        String msg=welcomePage.getFlightHeaderMsg();
        Assert.assertTrue("valid msg is displayed",msg.contains(departure));
        Assert.assertTrue("valid msg is displayed",msg.contains(destination));
    }
}
