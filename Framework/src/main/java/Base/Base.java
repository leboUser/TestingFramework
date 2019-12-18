package Base;

import ExcelReader.ExcelReader;
import APIObject.APIObject;
import Reporter.TestListener;
import Selenium.SeleniumDriver;

import java.io.IOException;


public class Base {

    protected ExcelReader reader;
    protected APIObject apiObject;
    protected SeleniumDriver selenium;
    protected TestListener reporter;



   protected void seleniumClasses() {
        this.reader = new ExcelReader();
        this.selenium = new SeleniumDriver();
        this.reporter = new TestListener();

    }

    protected void apiClasses(){
        this.reader = new ExcelReader();
        this.apiObject = new APIObject();
        this.reporter = new TestListener();
    }



}

