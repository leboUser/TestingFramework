package Selenium;



import Base.Base;
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
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SeleniumDriver extends Base {

    protected WebDriver driver;
    private String currentBrowser;
    public int screenCounter = 0;

    public void seleniumBrowser(String browser) {
        this.currentBrowser = browser.toUpperCase();
        launchDriver();
    }

    private boolean launchDriver() {
        switch (this.currentBrowser) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                this.driver = new ChromeDriver();
                break;
            case "IE":
                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
            case "FIREFOX":
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
    private boolean waitElement(String value, String type ){
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
            WebElement element ;
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            switch (type[1]) {

                case "id":
                    waitElement(type[0],type[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                     element = this.driver.findElement(By.id(type[0]));
                    element.click();
                    break;
                case "class":
                    waitElement(type[0],type[1]);
                     wait = new WebDriverWait(this.driver, 1);
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                     element = this.driver.findElement(By.className(type[0]));
                    element.click();
                    break;
                case "name":
                    waitElement(type[0],type[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = this.driver.findElement(By.name(type[0]));
                    element.click();
                    break;
                case "xpath":
                    waitElement(type[0],type[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                    element = this.driver.findElement(By.xpath(type[0]));
                    element.click();
                    break;
                case "css":
                    waitElement(type[0],type[1]);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                    element = this.driver.findElement(By.cssSelector(type[0]));
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
            Select element;
            WebDriverWait wait =  new WebDriverWait(this.driver, 1);
            boolean found =false;


            switch(type[1]) {
                case "id":
                    waitElement(type[0], type[1]);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.id(type[0]))));
                     element = new Select(this.driver.findElement(By.id(type[0])));
                    element.selectByValue(value);
                    found =  true;
                break;
                case "name":
                    waitElement(type[0], type[1]);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.name(type[0]))));
                    element = new Select(this.driver.findElement(By.name(type[0])));
                    element.selectByValue(value);
                    found =  true;
                    break;
                case "xpath":
                    waitElement(type[0], type[1]);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath(type[0]))));
                    element = new Select(this.driver.findElement(By.xpath(type[0])));
                    element.selectByValue(value);
                    found =  true;
                    break;
                case "class":
                    waitElement(type[0], type[1]);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.className(type[0]))));
                    element = new Select(this.driver.findElement(By.className(type[0])));
                    element.selectByValue(value);
                    found =  true;
                    break;
                case "css":
                    waitElement(type[0],type[1]);
                    wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.cssSelector(type[0]))));
                    element = new Select(this.driver.findElement(By.className(type[0])));
                    element.selectByValue(value);
                    found =  true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);


            }
            return found;

        }catch (Exception e){
            return false;
        }
    }

    public Boolean MovetoElement(String[] type){
        try{
            waitElement(type[0], type[1]);
            WebElement element;
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            Actions actions = new Actions(this.driver);
            boolean found =false;

            switch(type[1]) {
                case "id":
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                    element = this.driver.findElement(By.id(type[0]));
                    actions.moveToElement(element);
                    actions.perform();
                    found =true;
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                    element = this.driver.findElement(By.xpath(type[0]));
                    actions.moveToElement(element);
                    actions.perform();
                    found = true;
                    break;
                case "class":
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                    element = this.driver.findElement(By.className(type[0]));
                    actions.moveToElement(element);
                    actions.perform();
                    found=true;
                    break;
                case "name":
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = this.driver.findElement(By.name(type[0]));
                    actions.moveToElement(element);
                    actions.perform();
                    found= true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);

            }

            return found;
        }catch(Exception e){
            return false;
        }
    }
    public boolean selectElementByIndex(String[] type,int value){
        try {

            waitElement(type[0], type[1]);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            Select element ;
            boolean found ;
            switch (type[1]) {

                case "id":
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                    element = new Select(this.driver.findElement(By.id(type[0])));
                    element.selectByIndex(value);
                    found = true;
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                    element = new Select(this.driver.findElement(By.xpath(type[0])));
                    element.selectByIndex(value);
                    found = true;
                    break;
                case "name":
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = new Select(this.driver.findElement(By.name(type[0])));
                    element.selectByIndex(value);
                    found = true;
                    break;
                case "class":
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                    element = new Select(this.driver.findElement(By.className(type[0])));
                    element.selectByIndex(value);
                    found = true;
                    break;
                case "css":
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                    element = new Select(this.driver.findElement(By.cssSelector(type[0])));
                    element.selectByIndex(value);
                    found = true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);
            }
                return found;

        }catch (Exception e){
            return false;
        }
    }

    public boolean enterText(String[] type, String textToEnter){
        try {

            waitElement(type[0], type[1]);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            WebElement element ;
            boolean found ;

             switch(type[1]){
                case "id":
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                    element = this.driver.findElement(By.id(type[0]));
                    element.sendKeys(textToEnter);
                    found =true;
                    break;
                case "name":
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = this.driver.findElement(By.name(type[0]));
                    element.sendKeys(textToEnter);
                    found =true;
                    break;
                case "class":
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                    element = this.driver.findElement(By.className(type[0]));
                    element.sendKeys(textToEnter);
                    found = true;
                    break;
                case "css":
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                    element = this.driver.findElement(By.cssSelector(type[0]));
                    element.sendKeys(textToEnter);
                    found = true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);

                    }


            return found;

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
            waitElement(type[0], type[1]);
            WebDriverWait wait = new WebDriverWait(this.driver, 1);
            WebElement element;


                switch(type[1]) {
                    case "xpath":
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                        element = this.driver.findElement(By.xpath(type[0]));

                    break;
                    case "id":
                        wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                        element = this.driver.findElement(By.id(type[0]));
                    break;
                    case "name":
                        wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                        element = this.driver.findElement(By.name(type[0]));
                    break;
                    case "class":
                        wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                        element = this.driver.findElement(By.className(type[0]));
                    break;
                    case "css":
                        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                        element = this.driver.findElement(By.cssSelector(type[0]));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid type " + type[0]);
                }

            return element.getText();



        }catch (Exception e){
            return "";
        }
    }

    public boolean validateElementText(String[] type,String textToValidate){
        try {

            waitElement(type[0], type[1]);
            WebDriverWait wait = new WebDriverWait(this.driver,1);
            WebElement element ;
            switch(type[1]) {
                case"id":
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(type[0])));
                     element = this.driver.findElement(By.id(type[0]));
                    break;
                case "name":
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(type[0])));
                    element = this.driver.findElement(By.name(type[0]));
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(type[0])));
                    element = this.driver.findElement(By.xpath(type[0]));
                    break;
                case "class":
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(type[0])));
                    element = this.driver.findElement(By.className(type[0]));
                    break;
                case "css":
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(type[0])));
                    element = this.driver.findElement(By.cssSelector(type[0]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type " + type[0]);

            }
            return element.getText().equals(textToValidate);

        }catch (Exception e){
            return false;
        }
    }

    public String takeScreenshot(Boolean status, String getReportDirectory) throws IOException {
        screenCounter++;
        StringBuilder imagePath = new StringBuilder();
        StringBuilder relativePath = new StringBuilder();

            imagePath.append(getReportDirectory);
            relativePath.append("Screenshots\\");
            new File(imagePath.toString() + (relativePath).toString()).mkdirs();

            relativePath.append(screenCounter + "_");
            if (status) {
                relativePath.append("PASSED");
            } else {
                relativePath.append("FAILED");
            }
            relativePath.append(".png");

             System.out.println(imagePath.append(relativePath).toString());
            File screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotBase64,new File(imagePath.append(relativePath).toString()));

            return relativePath.toString();

    }

    private void pause(int millis) {
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




