package in.v2solutions.hybrid.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import in.v2solutions.hybrid.util.Constants;
import in.v2solutions.hybrid.util.ExtentManager;
import in.v2solutions.hybrid.util.Keywords;
import in.v2solutions.hybrid.util.TestUtil;

public class LG_BORROWER_03_Verify_ApproveFullVOIE extends Constants {
String TCName = "LG_BORROWER_03_Verify_ApproveFullVOIE";
String lastTestCaseName = "LG_BORROWER_03_Verify_ApproveFullVOIE";
String as = ": Last Test Case Quit";
int runModecounter = Keywords.xls.getCellRowNum("Test Data","DDTCIDWithRunMode",TCName)+2;


@Parameters({ "Suite-Name" })
@BeforeTest
public void beforeTest(@Optional String Suitename) {

String Actsuitename = Suitename;
extent = ExtentManager.GetExtent();
	if (Actsuitename != null) 
	{
		Keywords.tsName = Actsuitename;
		Keywords.tcName = TCName;
	}
	else 
	{
		Keywords.tcName = TCName;
	}
test = extent.createTest(TCName);
}


@Test(dataProvider = "getTestData")
public void verify_ApproveFullVOIE(Hashtable<String, String> data)throws Exception {
	if (!TestUtil.isTestCaseExecutable(TCName,Keywords.xls))
		{
		test.log(Status.SKIP,"This Test Script is Skipped as it's RunMode is Marked as NO");
		throw new SkipException("This Test Script is Skipped as it's RunMode is Marked as NO");
		}
	{
	if(getTestData().length > 1) {
		String YorN = Keywords.xls.getCellData("Test Data",0,runModecounter);
 		System.out.println(YorN); 
	if (YorN.equals("N")){
		runModecounter = runModecounter+1;
		test.log(Status.SKIP,"This DDT Test Script is Skipped as it's RunMode is Marked as NO");
		throw new SkipException("This DDT Test Script is Skipped as it's RunMode is Marked as NO");
		}
		runModecounter = runModecounter+1;
	}
	Keywords k = Keywords.getKeywordsInstance();
	k.executeKeywords(TCName, data);
	}
}

@AfterTest
public void afterTest() throws ATUTestRecorderException {
	extent.flush();  
	if(TCName.equals(lastTestCaseName))
		{ System.out.println(as);
	try{  
	Constants.driver.close();
 } catch(Exception e){
  	Constants.driver = null;
	}
	Constants.driver = null;
	}
}

@DataProvider
public Object[][] getTestData() {
	return TestUtil.getData(TCName, Keywords.xls);
	}
}
