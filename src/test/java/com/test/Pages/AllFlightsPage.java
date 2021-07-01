package com.test.Pages;

//import com.test.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllFlightsPage  extends BaseFlight {
    @FindBy(xpath="//input[@type='submit']")
    WebElement chooseThisFlight;

    public AllFlightsPage(){
        PageFactory.initElements(driver,this);
    }

    public String validateWelcomePageTitle(){
        return driver.getTitle();
    }
    public AllFlightsPage selectFromAndTo(){
        return new AllFlightsPage();
    }

    public void clickChooseThisFlightButton(){
        chooseThisFlight.click();
    }
}
