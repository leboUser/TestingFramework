package ExcelReader;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.IOException;
import java.util.ArrayList;


public class ExcelReader {



    private Recordset recordset;
    private Connection excelconnection;

    public void pathreader(String file_name) throws  FilloException {
        Fillo file = new Fillo();
        this.excelconnection = file.getConnection(file_name);

    }


    public Recordset getRecordset() {
        return recordset;
    }

    public void setRecordsetQuery(String query) throws FilloException {
        this.recordset = excelconnection.executeQuery(query);
    }

    public ArrayList <String> excelreader () throws FilloException {
        ArrayList <String> values = new ArrayList<String>();
        while(this.recordset.next()){
            String execute = this.recordset.getField("Execute").toLowerCase();
            String address = this.recordset.getField("Address");
            if(execute.equals("true")) {
                values.add(address);
            }
        }

        this.recordset.close();
        this.excelconnection.close();

        return values;
    }






}
