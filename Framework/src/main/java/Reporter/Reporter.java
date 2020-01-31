package Reporter;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Base.Base;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Reporter extends Base {

    private static final String formatStr = "%n%-24s %-20s %-60s %-25s";
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports report =  new ExtentReports();

    public String reportDirctory;



    Reporter(String testname) {
         htmlReporter = new ExtentHtmlReporter( setReportDirectory(testname) +"extend.html");
        // htmlReporter.config().setAutoCreateRelativePathMedia(true);
         report.attachReporter(this.htmlReporter);
    }

    private static String getCurTime(){
         Date date = new Date();
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
         return format.format(date);
    }
    public String getReportDirectory() {
        return reportDirctory;
    }

    public String setReportDirectory(String testname) {
       return reportDirctory = System.getProperty("user.dir") + "\\Reports\\" +  testname+ "\\" + getCurTime()+"\\";
    }






}