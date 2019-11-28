package ExcelReader;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.IOException;



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

    public void reader() throws FilloException {
        while(this.recordset.next()){
            System.out.println(this.recordset.getField("Mones"));

        }

        this.recordset.close();
        this.excelconnection.close();
    }






}
