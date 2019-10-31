import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITest {

    String Path ="";

    @Parameters({"excelPath"})
    @Test
    private void test(String excelPath){
        System.out.println(excelPath);
    }
}
