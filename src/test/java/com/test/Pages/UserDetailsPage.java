package com.test.Pages;

//import com.test.utils.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserDetailsPage extends BaseFlight {
    @FindBy(id="inputName")
    WebElement firstName;

    @FindBy(name="address")
    WebElement address;

    @FindBy(name="city")
    WebElement city;

    @FindBy(name="state")
    WebElement state;

    @FindBy(name="zipCode")
    WebElement zipCode;

    @FindBy(name="creditCardNumber")
    WebElement creditCardNumber;

    @FindBy(name="creditCardMonth")
    WebElement creditCardMonth;

    @FindBy(name="creditCardYear")
    WebElement creditCardYear;

    @FindBy(name="nameOnCard")
    WebElement nameOnCard;

    @FindBy(xpath="//input[@type='submit']")
    WebElement purchaseFlight;

    public UserDetailsPage(){
        PageFactory.initElements(driver,this);
    }

    public String validateWelcomePageTitle(){
        return driver.getTitle();
    }

    public void enterFirstName(String fName){
        WebDriverWait wait = new WebDriverWait (driver, 15);
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys(fName);
    }

    public void enterAddress(String addr){
        address.sendKeys(addr);
    }

    public void enterCity(String City){
        city.sendKeys(City);
    }

    public void enterState(String State){
        state.sendKeys(State);
    }

    public void enterZipCode(String ZipCode){
        zipCode.sendKeys(ZipCode);
    }

    public void enterCCNumber(String CCNumber){
        creditCardNumber.sendKeys(CCNumber);
    }

    public void enterCCMonth(String CCMonth){
        creditCardMonth.clear();
        creditCardMonth.sendKeys(CCMonth);
    }

    public void enterCCYear(String CCYear){
        creditCardYear.clear();
        creditCardYear.sendKeys(CCYear);
    }
    public void enterNameOnCard(String NameOnCard){
        nameOnCard.sendKeys(NameOnCard);
    }

    public void clickPurchaseFlight(){
        purchaseFlight.click();
    }
}
