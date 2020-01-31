import Reporter.TestListener;
import com.codoid.products.exception.FilloException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class APITest extends Base {

    String Path ="";

    @BeforeTest
    @Parameters({"excelPath","sheet"})
    private void instaniled(String excelPath, String sheet, ITestContext testName) throws FilloException {
       apiClasses();
        this.reader.pathreader(excelPath);
        this.reporter.testsuite(testName.getName());

    }


    @Test
    @Parameters({"sheet"})
    private void test(String sheet) throws ParseException {
        try {
            this.reader.setRecordsetQuery(sheet);
            ArrayList<String> addressLinks = this.reader.excelreader();

            for (String address : addressLinks) {
                JSONParser parser = new JSONParser();
                this.apiObject.setPath(address);
                this.apiObject.setRequest();
                //System.out.println(this.apiObject.setResponseCode());
               // reporter.testStep("Receive 200", "Testing the connection");

                Assert.assertEquals("200", this.apiObject.setResponseCode());

                JSONObject jsonObject = (JSONObject) parser.parse(this.apiObject.setResponseBody().charStream());
                JSONObject message = (JSONObject) jsonObject.get("message");

                JSONArray value = (JSONArray) message.get("hound");

                System.out.println(value);
                for (Object objectValues : value) {
                    System.out.println(objectValues);
                }

            }

        } catch (FilloException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        @Test
        @Parameters({"sheet"})
        private void test2(String sheet) throws ParseException, FilloException, IOException {
                this.reader.setRecordsetQuery(sheet);
                ArrayList<String> addressLinks = this.reader.excelreader();
                reporter.testcaseCreation("tryout");
               // reporter.testStep("STep tryout","Working ");
                reporter.Testcomplete("STep tryout",true);


        }

    @AfterTest
    public void compilingTestcase(){
        reader.closeFillo();
        this.reporter.flush();
    }


}
