package com.test.Pages;

import com.google.inject.Inject;
//import com.test.Hooks.hooks;
//import com.test.utils.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WelcomePage extends BaseFlight {

//    @Inject
//    private BaseClass base;


    public WelcomePage(){
        PageFactory.initElements(driver,this);
    }
//
//    public WelcomePage(BaseClass base) {
//        this.base = base;
//    }

    @FindBy(name="fromPort")
    WebElement departure;

    @FindBy(name="toPort")
    WebElement destination;

    @FindBy(xpath="//input[@type='submit']")
    WebElement findFlightsButton;

    @FindBy(xpath="//*[contains(text(),'Flights from')]")
    WebElement flightHeaderMsg;


    Select dropdown;





    public String validateWelcomePageTitle(){
        return driver.getTitle();
    }

    public void selectDepartureCity(String fromCity){
        dropdown=new Select(departure);
       // List<WebElement> options=dropdown.getOptions();
        dropdown.selectByVisibleText(fromCity);
    }

    public void selectDestinationCity(String toCity){
        dropdown=new Select(destination);
        //List<WebElement> options=dropdown.getOptions();
        dropdown.selectByVisibleText(toCity);
    }
    public void clickFindFlights(){
        findFlightsButton.click();
    }
    public List<String> getAllOptionsFrom(){
        dropdown=new Select(departure);
        List<WebElement> options=dropdown.getOptions();
        List<String> optionList=new LinkedList<>();
        for (WebElement option: options) {
            optionList.add(option.getText());
        }
        return optionList;
    }

    public List<String> getAllOptionsTo(){
        dropdown=new Select(destination);
        List<WebElement> options=dropdown.getOptions();
        List<String> optionList=new LinkedList<>();
        for (WebElement option: options) {
            optionList.add(option.getText());
        }
        return optionList;
    }

    public String getFlightHeaderMsg(){
        return flightHeaderMsg.getText();
    }
}
