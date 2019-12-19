package Reporter;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

 class Reporter  {

    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extend.html");
    ExtentReports report =  new ExtentReports();

     Reporter(){
        report.attachReporter(this.htmlReporter);
    }


}