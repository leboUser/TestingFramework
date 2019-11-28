import com.codoid.products.exception.FilloException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Base.Base;

import java.io.IOException;
import java.util.Iterator;

public class APITest extends Base {

    String Path ="";

    @BeforeTest
    @Parameters({"excelPath","sheet"})
    private void instaniled(String excelPath,String sheet) throws IOException, FilloException {
       intziled();
        this.reader.pathreader(excelPath);

    }


    @Test
    @Parameters({"sheet"})
    private void test(String sheet){
        try {
            this.reader.setRecordsetQuery(sheet);
           this.reader.reader();
        } catch (FilloException e) {
            e.printStackTrace();
        }


    }


}
