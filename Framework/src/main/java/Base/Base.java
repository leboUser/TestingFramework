package Base;

import ExcelReader.ExcelReader;
import APIObject.APIObject;
import Reporter.TestListener;
import Selenium.SeleniumDriver;

import java.io.IOException;


public abstract class Base {

    protected TestListener reporter;
    protected ExcelReader reader;
    protected APIObject apiObject;
    protected SeleniumDriver selenium;


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
    //Please use this Directory  to store screenshot
    public String getReportDirecty(){
      return this.reporter.getDic();
    }



}

