package com.test.Pages;

//import com.test.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmationPage extends BaseFlight {
    @FindBy(xpath="//td[text()='Id']//following-sibling::td")
    WebElement purchaseID;

    @FindBy(xpath="//td[text()='Status']//following-sibling::td")
    WebElement status;

    @FindBy(xpath="//h1[text()='Thank you for your purchase today!']")
    WebElement thankuMsg;

    public FlightConfirmationPage(){
        PageFactory.initElements(driver,this);
    }
    public String validateWelcomePageTitle(){
        return driver.getTitle();
    }
    public String getPurchaseID(){
        String pid=purchaseID.getText();
        return pid;
    }

    public String getThankuMsg(){
        String msg=thankuMsg.getText();
        return msg;
    }
}
