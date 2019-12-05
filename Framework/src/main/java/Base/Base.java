package Base;

import ExcelReader.ExcelReader;
import APIObject.APIObject;
import Selenium.SeleniumDriver;

import java.io.IOException;


public abstract class Base {

    public ExcelReader reader;
    public APIObject apiObject;
    public SeleniumDriver selenium;



   public void intziled() {
        this.reader = new ExcelReader();
        this.apiObject =new APIObject();
        this.selenium = new SeleniumDriver();

    }



}

