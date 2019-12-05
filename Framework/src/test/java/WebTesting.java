import Base.Base;
import com.codoid.products.exception.FilloException;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WebTesting  extends Base {

    @BeforeTest
    @Parameters({"excelPath","sheet","browser","url"})
    private void instaniled(String excelPath,String sheet,String browser,String url) throws FilloException {
        intziled();
        this.selenium.seleniumBrowser(browser);
        this.selenium.navigation(url);
        this.reader.pathreader(excelPath);

    }

    @Test
    @Parameters({"sheet"})
    private void test(String sheet) throws FilloException {

        this.reader.setRecordsetQuery(sheet);
        HashMap<String,String[]> webElement = this.reader.seleniumreader();
        this.selenium.enterText(webElement.get("googleTextfield"),"YEA man it worked");

        selenium.shutDown();


    }

    private void endTest(){
        selenium.shutDown();
    }
}
