package Reporter;

import Base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


import java.io.IOException;


public  class  TestListener extends Base {

    private ExtentTest test;
    private ExtentTest node;
    private Reporter testsuites;
    private String testCase;


    public boolean testsuite(String testCases) {
       this.testCase = testCases;
       testsuites = new Reporter(testCase);
       test = testsuites.report.createTest("We have"+testCase);
       node = test;
       return true;
    }

    public void testcaseCreation(String testcaseName){
        test = testsuites.report.createTest(testcaseName);
    }


   public void Testcomplete(String reasonP_F, boolean results){
        if(results){
            this.test.pass(reasonP_F);
        }else{
            this.test.fail(reasonP_F);
        }
    }

   public void TestcompleteWithScreenShot(String reasonP_F, boolean results) throws IOException {
       try {
           String value = selenium.takeScreenshot(results,testsuites.getReportDirectory());
           //test.pass(reasonP_F, MediaEntityBuilder.createScreenCaptureFromPath()).build());
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void testStep(String step,String description){
        this.node = test.createNode(step,description);
    }
   public void flush(){
         testsuites.report.flush();
    }

}
