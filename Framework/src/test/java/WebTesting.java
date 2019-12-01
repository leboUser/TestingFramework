import Base.Base;
import com.codoid.products.exception.FilloException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class WebTesting  extends Base {

    @BeforeTest
    @Parameters({"excelPath","sheet"})
    private void instaniled(String excelPath,String sheet) throws FilloException {
        intziled();
        this.reader.pathreader(excelPath);

    }

    @Test
    @Parameters({"sheet"})
    private void test(String sheet) throws FilloException {

        this.reader.setRecordsetQuery(sheet);
        HashMap<String,String[]> webElement = this.reader.seleniumreader();


    }
}
