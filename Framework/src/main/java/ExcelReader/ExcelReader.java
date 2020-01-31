package ExcelReader;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class ExcelReader {



    private Recordset recordset;
    private Connection excelconnection;

    public void pathreader(String file_name) throws  FilloException {
        Fillo file = new Fillo();
        File excelfile = new File(file_name);
        this.excelconnection = file.getConnection(excelfile.getPath());

    }


    public Recordset getRecordset() {
        return recordset;
    }

    public void setRecordsetQuery(String query) throws FilloException {
        this.recordset = excelconnection.executeQuery(query);
    }

    public ArrayList <String> excelreader () throws FilloException {
        ArrayList <String> values = new ArrayList<>();
        while(this.recordset.next()){
            String execute = this.recordset.getField("Execute").toLowerCase();
            String address = this.recordset.getField("Address");
            if(execute.equals("true")) {
                values.add(address);
            }
        }
        this.recordset.close();
        return values;
    }

    public HashMap<String,String[]> seleniumreader () throws FilloException {
        HashMap <String,String[]> values = new HashMap<>();



        while(this.recordset.next()){
            String [] element = new String[2];
            String atrributes = this.recordset.getField("Attributes");


            element[0] = this.recordset.getField("Values");
            element[1] = this.recordset.getField("Type");
            values.put(atrributes,element);
        }
        this.recordset.close();


        return values;
    }

  public void closeFillo(){
        this.excelconnection.close();

    }






}
