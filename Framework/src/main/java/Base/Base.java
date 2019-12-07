package Base;

import ExcelReader.ExcelReader;
import APIObject.APIObject;
import Selenium.SeleniumDriver;

import java.io.IOException;


public class Base {

    protected ExcelReader reader;
    protected APIObject apiObject;
    protected SeleniumDriver selenium;



   protected void seleniumClasses() {
        this.reader = new ExcelReader();
        this.selenium = new SeleniumDriver();

    }

    protected void apiClasses(){
        this.reader = new ExcelReader();
        this.apiObject = new APIObject();
    }



}

