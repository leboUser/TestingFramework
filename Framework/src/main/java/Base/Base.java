package Base;

import ExcelReader.ExcelReader;
import APIObject.APIObject;

import java.io.IOException;


public abstract class Base {

    public ExcelReader reader;
    public APIObject apiObject;



   public void intziled() {
        this.reader = new ExcelReader();
        this.apiObject =new APIObject();

    }



}

