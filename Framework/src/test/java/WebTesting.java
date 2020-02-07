import Base.Base;
import com.codoid.products.exception.FilloException;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;

public class WebTesting  extends Base {

    @BeforeTest
    @Parameters({"excelPath","sheet","browser","url"})
    private void WebTesting(String excelPath, String sheet, String browser, String url, ITestContext testName) throws FilloException {
        seleniumClasses();
        selenium.seleniumBrowser(browser);
        selenium.navigation(url);
        reader.pathreader(excelPath);
        reporter.testsuite(testName.getName());

    }

    @Test
    @Parameters({"sheet"})
    private void test(String sheet) {

        try {
            this.reader.setRecordsetQuery(sheet);

        HashMap<String,String[]> webElement = this.reader.seleniumreader();
        selenium.enterText(webElement.get("googleTextfield"),"YEA man it worked");
        reporter.TestcompleteWithScreenShot("Step In ", selenium.takeScreenshot(true,this.getReportDirecty()));

        } catch (FilloException | IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void endTest(){
        selenium.shutDown();
        reader.closeFillo();
        this.reporter.flush();
    }
}
