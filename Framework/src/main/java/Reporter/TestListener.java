package Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.gherkin.model.Feature;


public  class  TestListener extends Reporter {


    private ExtentTest test = null;
    private ExtentTest node = null;

   public boolean testcase(String testcaseName){
       test = super.report.createTest(testcaseName);
       return true;
    }

    boolean statusIndetails(String status,String detail){

        return true;
    }

   public void testcase(String reasonP_F,boolean results){

        if(results){
            this.test.pass(reasonP_F);
        }else{
            this.test.fail(reasonP_F);
        }
    }

   public void testcaseWithScreenShot(String reasonP_F,boolean results){
        if(results){
            test.pass(reasonP_F);
        }else{
           // this.test.fail(reasonP_F, MediaEntityModelProvider(""));
        }
    }


   public void testStep(String step,String description){
        this.node = test.createNode(step,description);
    }

  public  void MultiStep(String reasonP_F,boolean results){
        if(results){
            this.node.pass(reasonP_F);
        }else{
            this.node.fail(reasonP_F);
        }
    }






     public void flush(){
        super.report.flush();
    }





}
