package Selenium;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SeleniumDriver  {

    private WebDriver driver;
    public enum BrowserType{CHROME,IE,FIREFOX}
    private BrowserType currentBrowser;
    public int screenCounter = 0;

    public SeleniumDriver(BrowserType browser) {
        this.currentBrowser = browser;
        launchDriver();
    }

    public boolean launchDriver() {
        switch (this.currentBrowser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case IE:
                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
            default:
                break;
        }

        this.driver.manage().window().maximize();

        return true;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public boolean shutDown(){
        try {

            driver.close();
            driver.quit();
            return true;



        }catch (Exception e){
            return false;
        }
    }

    //Interacting Element
    public boolean WaitElement(String value,String type ){
        boolean found = false;
        int counter = 0;

        try {
            while(!found && counter < 30){
                try{
                    WebDriverWait wait = new WebDriverWait(this.driver, 1);
                    switch(type) {
                        case "id":
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(value)));
                            found = true;
                            break;
                        case "xpath":
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(value)));
                            found = true;
                            break;
                        case "name":
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(value)));
                            found = true;
                            break;
                        case "class":
                            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(value)));
                            found = true;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid type " + value);

                    }

                }catch(Exception e){
                    counter ++;
                    pause(1000);

                }

            }

            return found;

        }catch (Exception e){
            return false;
        }
    }

    public boolean clickElement(String[] type){
        try {
            WebElement element = null;
            WebDriverWait wait = null;
            switch (type[1]) {

                case "id":
                    WaitElement(type[0],type[1]);
                     wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                     element = this.driver.findElement(By.id(type[0]));
                    element.click();
                    break;
                case "class":
                    WaitElement(type[0],type[1]);
                     wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                     element = this.driver.findElement(By.className(type[0]));
                    element.click();
                    break;
                case "name":
                    WaitElement(type[0],type[1]);
                     wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                     element = this.driver.findElement(By.name(type[0]));
                    element.click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);

            }

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean selectElementByValue(String[] type,String value){
        try {
            WebDriverWait wait = null;
            Select element = null;


            switch(type[1]) {
                case "id":
                    WaitElement(type[0], type[1]);
                     wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.id(type[0]))));
                     element = new Select(this.driver.findElement(By.id(type[0])));
                    element.selectByValue(value);
                break;
                case "name":
                    WaitElement(type[0], type[1]);
                    wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.name(type[0]))));
                    element = new Select(this.driver.findElement(By.name(type[0])));
                    element.selectByValue(value);
                    break;
                case "xpath":
                    WaitElement(type[0], type[1]);
                    wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath(type[0]))));
                    element = new Select(this.driver.findElement(By.xpath(type[0])));
                    element.selectByValue(value);
                    break;
                case "class":
                    WaitElement(type[0], type[1]);
                    wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.className(type[0]))));
                    element = new Select(this.driver.findElement(By.className(type[0])));
                    element.selectByValue(value);
                    break;
                return true;

            }

        }catch (Exception e){
            return false;
        }
    }
/*
    public Boolean MovetoElement(String[] type){
        try{
            WaitElement(type[0], type[1]);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            wait.until(ExpectedConditions.elementToBeClickable(type[]));
            WebElement element = this.driver.findElement(selector);
            Actions actions = new Actions(this.driver);
            actions.moveToElement(element);
            actions.perform();

            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean selectElementByIndex(By selector,int value){
        try {

            WaitElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            Select element = new Select(this.driver.findElement(selector));
            element.selectByIndex(value);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean TAB(By selector){
        try {

            WaitElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement element = this.driver.findElement(selector);
            element.sendKeys(Keys.TAB);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean enterText(String[] type, String textToEnter){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            WebElement element = null;

             switch(type[1]){
                case "id":
                    WaitElement(By.id(type[0]));
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                    element = this.driver.findElement(By.id(type[0]));
                    element.sendKeys(textToEnter);
                    break;
                case "name":
                    WaitElement(By.id(type[0]));
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = this.driver.findElement(By.name(type[0]));
                    element.sendKeys(textToEnter);
                    break;
                case "class":
                    WaitElement(By.className(type[0]));
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                    element = this.driver.findElement(By.className(type[0]));
                    element.sendKeys(textToEnter);
                    break;
                case "css":
                    WaitElement(By.cssSelector(type[0]));
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                    element = this.driver.findElement(By.cssSelector(type[0]));
                    element.sendKeys(textToEnter);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);

                    }


            return true;

        }catch (Exception e){
            return false;
        }
    }

    public boolean navigation(String url){
        try {

            this.driver.navigate().to(url);

            return true;

        }catch (Exception e){
            return false;
        }
    }

    public Alert alert(){
        return this. driver.switchTo().alert();
    }

    public String getTextFromElement(String[] type){
        try {
            WebDriverWait wait = null;
            WebElement element = null;


                switch(type[1]) {
                    case "xpath":
                        WaitElement(By.xpath(type[0]));
                        wait = new WebDriverWait(this.driver, 1);
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                        element = this.driver.findElement(By.xpath(type[0]));

                    break;
                    case "id":
                        WaitElement(By.id(type[0]));
                        wait = new WebDriverWait(this.driver, 1);
                        wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                        element = this.driver.findElement(By.id(type[0]));
                    break;
                    case "name":
                        WaitElement(By.name(type[0]));
                        wait = new WebDriverWait(this.driver, 1);
                        wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                        element = this.driver.findElement(By.name(type[0]));
                    break;
                    case "class":
                        WaitElement(By.className(type[0]));
                        wait = new WebDriverWait(this.driver, 1);
                        wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                        element = this.driver.findElement(By.className(type[0]));
                    break;
                    default:
                        throw new IllegalArgumentException("Invalid type " + type[0]);
                }

            return element.getText();



        }catch (Exception e){
            return "";
        }
    }

    public boolean validateElementText(By selector,String textToValidate){
        try {

            WaitElement(selector);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
            WebElement element = this.driver.findElement(selector);

            return element.getText().equals(textToValidate);

        }catch (Exception e){
            return false;
        }
    }
/*
    public String takeScreenshot(boolean status) {
        StringBuilder imagePathBuilder = new StringBuilder();
        StringBuilder relativePathBuilder = new StringBuilder();


        try {
            imagePathBuilder.append(getReportDirectory());
            relativePathBuilder.append("Screenshot\\");
            new File(imagePathBuilder.toString() + (relativePathBuilder).toString()).mkdir();
            relativePathBuilder.append(screenCounter + "_");

            screenCounter++;
            if (status) {
                relativePathBuilder.append("PASSED");

            } else {
                relativePathBuilder.append("FAILED");
            }
            relativePathBuilder.append(".png");
            File  screenshot =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File(imagePathBuilder.append(relativePathBuilder).toString()));
            return "./"+relativePathBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
*/
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTimeStamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }


}




