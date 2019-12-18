package Reporter;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporter  {

    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extend.html");
    ExtentReports report =  new ExtentReports();

    public Reporter(){
        report.attachReporter(this.htmlReporter);
    }





}