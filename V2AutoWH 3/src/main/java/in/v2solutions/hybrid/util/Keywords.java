package in.v2solutions.hybrid.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.paulhammant.ngwebdriver.NgWebDriver;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.lightbody.bmp.BrowserMobProxy;



public class Keywords extends Constants {
	/*
	 * @HELP
	 * 
	 * @class: Keywords
	 * 
	 * @Singleton Class: Keywords getKeywordsInstance()
	 * 
	 * @ constructor: Keywords()
	 * 
	 * @methods: Navigate(String), NavigateTo(String), clearTextField(String), Click(String), CloseBrowser(), DeleteFile(String), deleteFilesFromFolder(String), GetAttributeValue(String), GetText(String), getKeywordsInstance(), getLastTestCaseName(), InputText(String, String, String), InputTextDirectly(String, String),
	 * InputTextWithAutoAddress(String, String), InputNumber(String, String), isElementPresentBy(By), IsEmpty(String, String, String), Login(), MouseHover(String), MouseHoverAndClick(String, String), OpenBrowser(String), PageRefresh(String), QuitBrowser(), readFile(String),	SelectRadioButton(String), SelectTomorrowDate
	 * (String, String, String), SelectUnselectCheckbox(String, String), SelectValueFromDropDown(String, String), SelectMonitorStatusAndVerifyColumnData(String, String, String), SelectValueFromDropDownWithAnchorTags(String, String), ScrollElementIntoView(String),TestCaseEnds(), TestCaseFail(), UnSelectUserRoles(String),
	 * uploadThroughAutoIT(), UploadFile(String), VerifyAdminModules(String), VerifyAdminRoles(String), VerifyAlerts(String, String, String), VerifyAlertisPresence(String, String), VerifyButtonIsDisable(String), VerifyButtonIsEnable(String), VerifyColumnData(String, String), VerifyDefaultAttributeValue(String, String), 
	 * VerifyFileIsDownloaded(), VerifyFileDownload(String), VerifyFileIsExportedAndSizeIsNotZero(String, String), VerifyHostActions(String, String), VerifyFieldsOnEditUserPage(String), VerifyHostStatusisUP(String, String), VerifyInvoiceData(String, String), VerifyIsDeleted(String, String), VerifyModifyByDateColumnData
	 * (String, String), VerifyPermalinkContent(String), VerifyPrefilledDataFromInputField(String, String), VerifyTableData(String, String, String), VerifyTableDataResult(String, String), VerifyText(String, String, String), VerifyTextDDTdata(String, String, String), VerifyTitle(String, String), VerifyTextContains
	 * (String, String, String), VerifyTextAttributeValue(String, String, String), VerifyToolTip(String, String), VerifyTextDDTdataContains(String, String, String), VerifyTitleContains(String), Wait(String), WaitTillElementAppears(String), WaitWhileElementPresent(String), VerifyCheckBoxIsEnabled(String), VerifyAccountByEmail(String)
	 * 
	 * @parameter: Different parameters are passed as per the method declaration
	 * 
	 * @notes: Keyword Drives and Executes the framework interacting with the MasterTSModule xlsx file
	 * 
	 * @returns: All respective methods have there return types
	 * 
	 * @END
	 */

	@SuppressWarnings("rawtypes")
	public static Map<String, String> getTextOrValues = new HashMap<String, String>();
	// Generating Dynamic Log File
	public String FILE_NAME = System.setProperty("filename", tsName + tcName + " - " + getCurrentTime());
	public static long start;
	static Keywords keywords = null;
	public boolean Fail = false;
	public boolean highlight = false;
	public boolean captureScreenShot = false;
	public String failedResult = "";
	public static int count = 0;
	public static String scriptTableFirstRowData = "";
	static Properties props;
	public static Connection connection = null;
	public static Statement statement = null;
	public String parentWindowID;
	public String GTestName = null;
	String StrGet = null;
	String StrPost = null;
	public BrowserMobProxy proxy;
	public String interGlobal;
	StringBuilder sb = new StringBuilder(100);
	Pattern patternDigit = Pattern.compile("([0-9]+)");
	Matcher matcher;
	int allOfferCount = 0;
	BufferedWriter bw = null;
	FileWriter fw = null;
	BufferedReader br = null;
	FileReader fr = null;
	LogEntries logEntries;
	NgWebDriver ngdriver;
	public String ActualText = null;
	public static boolean flag = false;
	public List<String> filename = new ArrayList<String>();
	public String fileName = null;
	public String GExpectedValue = null;
	public String GActualValue = null;
	public int ValidationCount = 0;
	public int ReadydownloadCount = 0;
	public int pendingValidationCount=0;
	public String systemTime=null;
	public int NumberofPagesinPDF = 0;
	public static long startTimer;
	public static long endTimer;
	public static long totalTime;
	public static String totalTimeLoad;
	public String firstName = null;
	public String lastName = null;
	public Hashtable<String, String> DDTText = null;
	public static String businessOwner;
	public static String serviceProvider;
	public static String loanId;
	public static String businessName;
	public static String winHandleBefore;
	public static String encryptloanId;
	public static String updatedDate;
	public static String homeURL;
	public static String configKey;
	public static String borrowerProfile;
	public static String adminLoanID;
	public static String refCode;
	public static String resourceName;
	public static String requestedAmount;

	private Keywords() throws IOException {

		ngdriver = new NgWebDriver((JavascriptExecutor) driver);
		props = new Properties();
		props.load(new FileInputStream(new File(orPath + "OR.properties/")));

		System.out.println("INFO:=> Initializing keywords");
		APP_LOGS.debug("INFO:=> Initializing keywords");
		try {
			getConfigDetails();
			OR = new Properties();
			fs = new FileInputStream(orPath + "OR.properties/");
			OR.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void executeKeywords(String testName, Hashtable<String, String> data) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: executeKeywords()
		 * 
		 * @parameter: String testName, Hashtable<String, String> data
		 * 
		 * @notes: Executes the Keywords as defined in the Master Xslx "Test Steps" Sheet and takes screenshots for any Test Step failure. The test case execution is asserted for any failure in actions and the script execution continues of at all there are some failures in
		 * verifications.
		 * 
		 * @returns: No return type
		 * 
		 * @END
		 */
		
	    DDTText=data;
	    	    
		System.out.println(": =========================================================");
		APP_LOGS.debug(": =========================================================");
		System.out.println(": Executing---" + testName + " Test Case");
		APP_LOGS.debug(": Executing---" + testName + " Test Case");
	
		SuiteRunTimeStamp = strActDate;
		startAfter = SuiteRunTimeStamp; 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh-mm-ss_aaa(zzz)");
		Date date = dateFormat.parse(startAfter); 
		suiteStartTimeInSeconds = (date.getTime() /1000);
		  
		String keyword = null;
		String objectKeyFirst = null;
		String objectKeySecond = null;
		String dataColVal = null;
		GTestName = testName;
		String links_highlight_true = null;
		String links_highlight_false = null;
		String links_on_action = null;

		for (int rNum = 2; rNum <= xls.getRowCount("Test Steps"); rNum++) {
			if (testName.equals(xls.getCellData("Test Steps", "TCID", rNum))) {

				keyword = xls.getCellData("Test Steps", "Keyword", rNum);
				objectKeyFirst = xls.getCellData("Test Steps", "FirstObject", rNum);
				objectKeySecond = xls.getCellData("Test Steps", "SecondObject", rNum);
				dataColVal = xls.getCellData("Test Steps", "Data", rNum);
				String result = null;

				if (keyword.equals("OpenBrowser"))// It is not a keyword, it is a supportive method
					result = OpenBrowser(dataColVal);

				else if (keyword.equals("Navigate"))
					result = Navigate(dataColVal);

				else if (keyword.equals("NavigateTo"))
					result = NavigateTo(dataColVal);

				else if (keyword.equals("InputText"))
					result = InputText(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("InputTextDirectly"))
					result = InputTextDirectly(objectKeyFirst, dataColVal);

				else if (keyword.equals("InputNumber"))
					result = InputNumber(objectKeyFirst, dataColVal);

				else if (keyword.equals("Click"))
					result = Click(objectKeyFirst);

				else if (keyword.equals("SelectValueFromDropDown"))
					result = SelectValueFromDropDown(objectKeyFirst, dataColVal);

				else if (keyword.equals("SendingDatatoInputAndVerifyColumnData"))
					result = SendingDatatoInputAndVerifyColumnData(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("SelectRadioButton"))
					result = SelectRadioButton(objectKeyFirst);

				else if (keyword.equals("SelectUnselectCheckbox"))
					result = SelectUnselectCheckbox(objectKeyFirst, dataColVal);

				else if (keyword.equals("Wait"))
					result = Wait(dataColVal);

				else if (keyword.equals("GetText"))
					result = GetText(objectKeyFirst);
				
				else if (keyword.equals("VerifyText"))
					result = VerifyText(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("VerifyTextDDTdata"))
					result = VerifyTextDDTdata(objectKeyFirst, objectKeySecond, data.get(dataColVal));

				else if (keyword.equals("VerifyTitle"))
					result = VerifyTitle(actTitle, dataColVal);

				else if (keyword.equals("CloseBrowser"))
					result = CloseBrowser();

				else if (keyword.equals("QuitBrowser"))
					result = QuitBrowser();

				else if (keyword.equals("MouseHover"))
					result = MouseHover(objectKeyFirst);

				else if (keyword.equals("TestCaseEnds"))
					result = TestCaseEnds();

				else if (keyword.equals("ClearTextField"))
					result = clearTextField(objectKeyFirst);

				else if (keyword.equals("VerifyColumnData"))
					result = VerifyColumnData(objectKeyFirst, dataColVal);

				else if (keyword.equals("VerifyTextContains"))
					result = VerifyTextContains(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("VerifyTextAttributeValue"))
					result = VerifyTextAttributeValue(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("VerifyToolTip"))
					result = VerifyToolTip(objectKeyFirst, dataColVal);

				else if (keyword.equals("VerifyTextDDTdataContains"))
					result = VerifyTextDDTdataContains(objectKeyFirst, objectKeySecond, data.get(dataColVal));

				else if (keyword.equals("VerifyTitleContains"))
					result = VerifyTitleContains(dataColVal);

				else if (keyword.equals("uploadThroughAutoIT"))
					result = uploadThroughAutoIT(dataColVal);

				else if (keyword.equals("VerifyTableData"))
					result = VerifyTableData(objectKeyFirst, objectKeySecond, dataColVal);

				else if (keyword.equals("UploadFile"))
					result = UploadFile(dataColVal);

				else if (keyword.equals("VerifyFileDownload"))
					result = VerifyFileDownload(dataColVal);

				else if (keyword.equals("DeleteFile"))
					result = DeleteFile(dataColVal);

				else if (keyword.equals("VerifyFileIsDownloaded"))
					result = VerifyFileIsDownloaded();

				else if (keyword.equals("deleteFilesFromFolder"))
					result = deleteFilesFromFolder(dataColVal);

				else if (keyword.equals("GetColumnDataLink"))
					result = GetColumnDataLink(objectKeyFirst, dataColVal);

				else if (keyword.equals("VerifyButtonIsDisable"))
					result = VerifyButtonIsDisable(objectKeyFirst);

				else if (keyword.equals("VerifyButtonIsEnable"))
					result = VerifyButtonIsEnable(objectKeyFirst);

				else if (keyword.equals("VerifyCheckBoxIsEnabled"))
					result = VerifyCheckBoxIsEnabled(objectKeyFirst);

				else if (keyword.equals("WaitWhileElementPresent"))
					result = WaitWhileElementPresent(objectKeyFirst);

				else if (keyword.equals("WaitTillElementAppears"))
					result = WaitTillElementAppears(objectKeyFirst);

				else if (keyword.equals("VerifyPrefilledDataFromInputField"))
					result = VerifyPrefilledDataFromInputField(objectKeyFirst, dataColVal);

				else if (keyword.equals("VerifyFileIsExportedAndSizeIsNotZero"))
					result = VerifyFileIsExportedAndSizeIsNotZero(objectKeySecond, dataColVal);

				else if (keyword.equals("SelectTomorrowDate"))
					result = SelectTomorrowDate(objectKeyFirst);

				else if (keyword.equals("TestCaseFail"))
					result = TestCaseFail();

				else if (keyword.equals("GetFileNameFromDownloadFolder"))
					result = GetFileNameFromDownloadFolder(dataColVal);

				else if (keyword.equals("PageRefresh"))
					result = PageRefresh();

				else if (keyword.equals("GetAttributeValue"))
					result = GetAttributeValue(objectKeyFirst);

				else if (keyword.equals("VerifyDefaultAttributeValue"))
					result = VerifyDefaultAttributeValue(objectKeyFirst, dataColVal);

				else if (keyword.equals("VerifyPermalinkContent"))
					result = VerifyPermalinkContent(dataColVal);

				else if (keyword.equals("CloseNewWindow"))
					result = CloseNewWindow();

				else if (keyword.equals("LogintoAdmin"))
					result = LogintoAdmin(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("LogintoMerchant"))
					result = LogintoMerchant(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("waitUntilCountChanges"))
					result = waitUntilCountChanges(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyLoanApplyDate"))
					result = VerifyLoanApplyDate(objectKeyFirst, dataColVal);

				else if (keyword.equals("Logout"))
					result = Logout(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyUserIsDeleted"))
					result = VerifyUserIsDeleted(objectKeyFirst);
				
				else if (keyword.equals("VerifyColor"))
					result = VerifyColor(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("CalculateExecutionTime"))
					result = CalculateExecutionTime();
				
				else if (keyword.equals("StartTimer"))
					result = StartTimer();
				
				else if (keyword.equals("EndTimer"))
					result = EndTimer(dataColVal);
				
				else if (keyword.equals("ScrollElementIntoView"))
					result = ScrollElementIntoView(objectKeyFirst);
				
				else if (keyword.equals("InputDDTdata"))
					result = InputDDTdata(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("InputTextWithAutoAddress"))
					result = InputTextWithAutoAddress(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyFirstName"))
					result = VerifyFirstName(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("VerifyLastName"))
					result = VerifyLastName(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("VerifyElementPresent"))
					result = VerifyElementPresent(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyElementNotPresent"))
					result = VerifyElementNotPresent(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyOffers"))
					result = VerifyOffers(objectKeyFirst);
				
				else if (keyword.equals("VerifyAddressFieldNotContainValidation"))
					result = VerifyAddressFieldNotContainValidation(objectKeyFirst);
				
				else if (keyword.equals("VerifyReportsStatusbyFilter"))
					result = VerifyReportsStatusbyFilter(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyFontType"))
					result = VerifyFontType(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyServiceProviderQuotedAmountisAutoPopulated"))
					result = VerifyServiceProviderQuotedAmountisAutoPopulated(objectKeyFirst);
				
				else if (keyword.equals("VerifyTextContainsDot"))
					result = VerifyTextContainsDot(objectKeyFirst);
				
				else if (keyword.equals("VerifyNameIsEmpty"))
					result = VerifyNameIsEmpty(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyBoxesShouldHaveDifferentValue"))
					result = VerifyBoxesShouldHaveDifferentValue(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("RegisterBusinessService"))
					result = RegisterBusinessService(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("RegisterIndirectBODDT"))
					result = RegisterIndirectBODDT(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("RegisterIndirectBO"))
					result = RegisterIndirectBO(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("RegisterBusinessOwner"))
					result = RegisterBusinessOwner(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("VerifyTransactions"))
					result = VerifyTransactions(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("ExecuteAndVerifyDBQuery"))
					result = ExecuteAndVerifyDBQuery(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("OpenDBConnection"))
					result = OpenDBConnection();
				
				else if (keyword.equals("VerifySigIn"))
					result = VerifySigIn(objectKeyFirst);
				
				else if (keyword.equals("SelectValueFromBootStrapDropDown"))
					result = SelectValueFromBootStrapDropDown(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("SelectValueFromDDTBootStrapDropDown"))
					result = SelectValueFromDDTBootStrapDropDown(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("GetName"))
					result = GetName(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("VerifyUserAcessNotAvailableFromDropDown"))
					result = VerifyUserAcessNotAvailableFromDropDown(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("GetBusinessOwner"))
					result = GetBusinessOwner();
				
				else if (keyword.equals("GetServiceProvider"))
					result = GetServiceProvider();
				
				else if (keyword.equals("VerifyMenuShouldNotDisplayed"))
					result = VerifyMenuShouldNotDisplayed(objectKeyFirst);
				
				else if (keyword.equals("HighlightElement"))
					result = HighlightElement(objectKeyFirst);
				
				else if (keyword.equals("VerifyTwoDecimalLength"))
					result = VerifyTwoDecimalLength(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyPercentageOnSidebySidePage"))
					result = VerifyPercentageOnSidebySidePage(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyPercentageDecimalPlaces"))
					result = VerifyPercentageDecimalPlaces(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("GetLoanId"))
					result = GetLoanId(objectKeyFirst);
				
				else if (keyword.equals("GetBusinessName"))
					result = GetBusinessName(objectKeyFirst);
				
				else if (keyword.equals("CloseDBConnection"))
					result = CloseDBConnection();
				
				else if (keyword.equals("VerifyDecimalLengthSidebySidepage"))
					result = VerifyDecimalLengthSidebySidepage(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("SwitchToNewWindow"))
					result = SwitchToNewWindow();
				
				else if (keyword.equals("ExecuteAndVerifyMultiColumnDBQuery"))
					result = ExecuteAndVerifyMultiColumnDBQuery(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("AddExtensionToChrome"))
					result = AddExtensionToChrome(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("GetBrowser"))
					result = GetBrowser();
				
				else if (keyword.equals("GetUpdatedDate"))
					result = GetUpdatedDate(objectKeyFirst);
				
				else if (keyword.equals("VerifyRejectedBO"))
					result = VerifyRejectedBO(objectKeyFirst);
				
				else if (keyword.equals("VerifyZoomOut"))
					result = VerifyZoomOut();
				
				else if (keyword.equals("VerifyListItemsUnique"))
					result = VerifyListItemsUnique(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("SelectMultiValueFromDropDown"))
					result = SelectMultiValueFromDropDown(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("SelectDateFromCalendar"))
					result = SelectDateFromCalendar(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyColumnDataWithPagination"))
					result = VerifyColumnDataWithPagination(objectKeyFirst, objectKeySecond, dataColVal);
				
				else if (keyword.equals("VerifyDisbursalAmountIsEmpty"))
					result = VerifyDisbursalAmountIsEmpty(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("DoubleClick"))
					result = DoubleClick(objectKeyFirst);
				
				else if (keyword.equals("VerifyPostRequest"))
					result = VerifyPostRequest(dataColVal);
				
				else if (keyword.equals("ExecuteAndVerifyDBQueryDDT"))
					result = ExecuteAndVerifyDBQueryDDT(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyFicoUsecases"))
					result = VerifyFicoUsecases(objectKeyFirst);
				
				else if (keyword.equals("InputTextDDTWithAutoAddress"))
					result = InputTextDDTWithAutoAddress(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("VerifyPaginationAndClick"))
					result = VerifyPaginationAndClick(objectKeyFirst);
				
				else if (keyword.equals("VerifyUpgradeOffersCalculation"))
					result = VerifyUpgradeOffersCalculation(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyUpgradeOffers"))
					result = VerifyUpgradeOffers(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyLightStreamOffers"))
					result = VerifyLightStreamOffers(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("NavigateDDTTo"))
					result = NavigateDDTTo(dataColVal);
				
				else if (keyword.equals("NavigateToURL"))
					result = NavigateToURL(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("ValidateOffers"))
					result = ValidateOffers(objectKeyFirst);
				
				else if (keyword.equals("JSEInputText"))
					result =JSEInputText(objectKeyFirst, dataColVal);
				
				else if (keyword.equals("SwitchToFrame"))
					result = SwitchToFrame(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("ValidateCart"))
					result = ValidateCart(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyFallBackToDirectOffers"))
					result = VerifyFallBackToDirectOffers(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyFallBackToDirectFlagEnable"))
					result = VerifyFallBackToDirectFlagEnable(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyFallBackToDirectFlagDisable"))
					result = VerifyFallBackToDirectFlagDisable(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyBusinessIsActive"))
					result = VerifyBusinessIsActive(objectKeyFirst, objectKeySecond);
				
				else if (keyword.equals("VerifyLoanPlanIsDisplayed"))
					result = VerifyLoanPlanIsDisplayed(objectKeyFirst, dataColVal);
				
				else if(keyword.equals("VerifyZiboUpgradeOffersCalculation"))
					result = VerifyZiboUpgradeOffersCalculation(objectKeyFirst, objectKeySecond);
				
				else if(keyword.equals("VerifyZiboUpgradeOffers"))
					result = VerifyZiboUpgradeOffers(objectKeyFirst, objectKeySecond);
				 
				else if(keyword.equals("VerifyZiboLightStreamOffers"))
					result = VerifyZiboLightStreamOffers(objectKeyFirst, objectKeySecond);
				
				
				System.out.println(": " + result);
				APP_LOGS.debug(": " + result);
				test.info(": " + result);
				
				File scrFile = null;
				String screeshotNameArray1[] = testName.split("_");
				String shortTcName = screeshotNameArray1[0];
				String shortTcName1 = screeshotNameArray1[1];
				String shortTcName2 = screeshotNameArray1[2];
				shortTcName = shortTcName + "_" + shortTcName1 + "_" +shortTcName2;
				
								
				//// ========================== FOR VERIFY KEYWORDS=======================
				if (keyword.contains("Verify")) {
					//// ============================ IF RESULT IS FAIL=======================
					if (!result.equals("PASS")) {
						if (highlight == true && captureScreenShot == true) // For UI Test cases Verification Fail														 
						{
							try {
								highlightElement(returnElementIfPresent(objectKeyFirst));
								scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
								scrFileName = shortTcName + "--Failed_AT-" + keyword + "-" + objectKeyFirst + "-" + getCurrentTimeForScreenShot() + ".png";
								links_highlight_true = " , For Error Screenshot please refer to this link  : " + "<a href=" + "'" + scrFileName + "'" + ">" + scrFileName + "</a>";
								String filename = SRC_FOLDER2 + Forwardslash + failedDataInText;
								FileWriter fw = new FileWriter(filename, true);
								String tempStr;
								tempStr = shortTcName + "__" + objectKeyFirst + "__" + actText + "__" + globalExpText + "__" + scrFileName;
								fw.write(tempStr + "\r\n");
								fw.close();
								unhighlightElement(returnElementIfPresent(objectKeyFirst));
							} catch (Exception e) {
								Fail = true;
								failedResult = failedResult.concat(result + links_highlight_true + " && ");
							}
							try {
								FileUtils.copyFile(scrFile, new File(screenshotPath + scrFileName));
								test.log(Status.FAIL, xls.getCellData("Test Steps", "Keyword", rNum) + " - 1 - keyowrd got failed. -> Actual value : " + GActualValue + " and Expected value :" + GExpectedValue + ", For Error Screenshot please refer to this link  : " + "<a href="
										+ "'" + System.getProperty("user.dir") + "/temp/screenshots/" + scrFileName + "'" + ">" + scrFileName + "</a>").addScreenCaptureFromPath(screenshotPath + scrFileName);
								System.out.println(": Verification failed. Please refer " + scrFileName);
								APP_LOGS.debug(": Verification failed. Please refer " + scrFileName);
								Fail = true;
								failedResult = failedResult.concat(result + links_highlight_true + " && ");
								System.out.println(": On Verification when highlight is True Failed");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						else if (highlight == false && captureScreenShot == true) // For UI Test cases Verification Fail because of Element Not found
						{
							scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // error when highlight is not set in VerifyCompleteGetResponse 
							scrFileName = shortTcName + "--Failed_AT-" + keyword + "-" + objectKeyFirst + "-" + getCurrentTimeForScreenShot() + ".png";
							links_highlight_false = " , For Error Screenshot please refer to this link  : " + "<a href=" + "'" + scrFileName + "'" + ">" + scrFileName + "</a>";
							String filename = SRC_FOLDER2 + Forwardslash + failedDataInText;
							FileWriter fw = new FileWriter(filename, true);
							String tempStr;
							tempStr = testName + "__" + objectKeyFirst + "__" + objectKeyFirst + " Not able to read text. Please check and modify Object Repository or  wait time" + "__" + "" + "__" + scrFileName;
							fw.write(tempStr + "\r\n");
							fw.close();
							Thread.sleep(500);
							FileUtils.copyFile(scrFile, new File(screenshotPath + scrFileName));
							test.log(Status.ERROR, "ERROR: " + xls.getCellData("Test Steps", "Keyword", rNum) + "-  2 - keyowrd got failed. For Error Screenshot please refer to this link  : " + "<a href=" + "'" + System.getProperty("user.dir") + "/temp/screenshots/" + scrFileName
									+ "'" + ">" + scrFileName + "</a>").addScreenCaptureFromPath(screenshotPath + scrFileName);
							System.out.println(": Unable to Verify, as Web Element Not Found. Please refer " + scrFileName);
							APP_LOGS.debug(": Unable to Verify, as Web Element Not Found. Please refer " + scrFileName);
							Fail = true;
							failedResult = failedResult.concat(result + links_highlight_false + " && ");
						}

						/*
						 * For HAR DB and API Test cases. We don't need to highlight and take screenshot
						 */
						else if (highlight == false && captureScreenShot == false) {

							String filename = SRC_FOLDER2 + Forwardslash + failedDataInText;
							FileWriter fw = new FileWriter(filename, true);
							String tempStr;
							tempStr = testName + "__" + objectKeyFirst + "__" + actText + "__" + globalExpText;
							test.log(Status.FAIL, xls.getCellData("Test Steps", "Keyword", rNum) + " -  3-  keyowrd got failed");
							fw.write(tempStr + "\r\n");
							fw.close();
							System.out.println(": VERIFICATION failed for HAR, DB or API call .");
							APP_LOGS.debug(": VERIFICATION failed for HAR, DB or API call.");
							Fail = true;
							failedResult = failedResult.concat(result + " && ");

						}
					}
					///// =============================== Creating HTML VERIFICATION NOTEPAD
					String filename = SRC_FOLDER2 + Forwardslash + verificationSummaryText;
					try {
						FileWriter fw = new FileWriter(filename, true);
						String tempStr = GTestName;
						if (result.equals("PASS")) {
							tempStr += " " + "__" + objectKeyFirst + "__" + keyword + "__" + "Y" + "__" + "-";
						} else {
							tempStr += " " + "__" + objectKeyFirst + "__" + keyword + "__" + "-" + "__" + "Y";
						}
						count++;

						fw.write(tempStr + "\r\n");
						fw.close();
					} catch (Exception e) {
						System.out.println("Error in count of the verification points..");
						e.printStackTrace();
					}
				}

				/////// ================================= FOR ACTION=========================
				else {
					if (!result.equals("PASS")) {
						if (highlight == false && captureScreenShot == true) // UI Action Fail
						{
							scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
							scrFileName = shortTcName + "--Failed_AT-" + keyword + "-" + objectKeyFirst + "-" + getCurrentTimeForScreenShot() + ".png";
							links_on_action = " , For Error Screenshot please refer to this link  : " + "<a href=" + "'" + scrFileName + "'" + ">" + scrFileName + "</a>";
							String filename = SRC_FOLDER2 + Forwardslash + failedDataInText;
							FileWriter fw = new FileWriter(filename, true);
							String tempStr;
							tempStr = shortTcName + "__" + objectKeyFirst + "__" + objectKeyFirst + " Did not appeared after waiting " + waitTime + " seconds. Please check the application status or modify Object Repository, Wait time." + "__" + "" + "__" + scrFileName;
							fw.write(tempStr + "\r\n");
							fw.close();
							try {
								FileUtils.copyFile(scrFile, new File(screenshotPath + scrFileName));
								test.log(Status.ERROR, "ERROR: " + xls.getCellData("Test Steps", "Keyword", rNum) + "-  4 - keyowrd got failed. For Error Screenshot please refer to this link  : " + "<a href=" + "'" + System.getProperty("user.dir") + "/temp/screenshots/"
										+ scrFileName + "'" + ">" + scrFileName + "</a>").addScreenCaptureFromPath(screenshotPath + scrFileName);
								System.out.println(": ACATION failed for UI because of Object Not Found Issue. Please refer " + scrFileName);
								APP_LOGS.debug(": ACTION failed for UI because of Object Not Found Issue. Please refer " + scrFileName);
								Fail = true;
								failedResult = failedResult.concat(result + links_on_action + " && ");
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
							APP_LOGS.debug(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
							Fail = false;
							QuitBrowser();
							driver = null;
							String failedResult1 = failedResult;
							failedResult = "";
							Assert.assertTrue(false, failedResult1);
						} else if (highlight == false && captureScreenShot == false) // DB,API Action Fail
						{
							String filename = SRC_FOLDER2 + Forwardslash + failedDataInText;
							test.log(Status.FAIL, xls.getCellData("Test Steps", "Keyword", rNum) + " -  5 -  keyowrd got failed");
							FileWriter fw = new FileWriter(filename, true);
							String tempStr;
							tempStr = shortTcName + "__" + objectKeyFirst + "__" + actText + "__" + globalExpText;
							fw.write(tempStr + "\r\n");
							fw.close();
							System.out.println(": ACTION failed for DB or API call .");
							APP_LOGS.debug(": ACTION failed for DB or API call.");
							Fail = true;
							failedResult = failedResult.concat(result + " && ");
						}
					} // last if is closing
				} // first Else is closing. it is of inner IF's
			} // outer If loop is closing
		} // outer For loop is closing t
	}
	
	// *****************Keywords Definitions*******************************************************************************************************
		
	public String Navigate(String URLKey) throws ATUTestRecorderException, ParseException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Navigate ()
		 * 
		 * @parameter: String URLKey
		 * 
		 * @notes: Navigate opened Browser to specific URL as mentioned in the config details.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		failedResult = "";
		System.out.println(": Navigating to (" + SUTUrl + ") Site");
		APP_LOGS.debug(": Navigating to (" + SUTUrl + ") Site");
		test.info(" Navigating to (" + SUTUrl + ") Site");
		try {
			if (captureVideoRecording.equals("Yes")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
				Date date = new Date();
				recorder = new ATUTestRecorder(suitrunvideoPath, "RECVideo-" + Keywords.tcName + dateFormat.format(date), false);
				System.out.println(": Video Recording Started ");
				APP_LOGS.debug(": Video Recording Started ");
				test.info(" Video Recording Started ");
				recorder.start();
			}
			if (driver != null) {
				Constants.driver.quit();
				OpenBrowser(bType);
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				driver.get(SUTUrl);

			} else {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				OpenBrowser(bType);
				driver.get(SUTUrl);
			}

		} catch (Exception e) {
			System.out.println(": Alert Exception getMessage: " + e.getMessage());
			if (e.getMessage().contains("Expected condition failed")) {
				System.out.println(": Alert hasn't Appeared");
				return "PASS";
			} else {
				System.out.println(": Alert Exception: " + e.getLocalizedMessage());
				test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
				return "FAIL - Not able to Navigate " + SUTUrl + " Site" + e.getMessage();
			}
		}
		test.pass("Pass");
		return "PASS";
	}

	public String NavigateTo(String URLKey) throws InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: NavigateTo ()
		 * 
		 * @parameter: String URLKey
		 * 
		 * @notes: Navigate to specific URL as metioned in the Data Coulmn in "Test Steps" Sheet.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		failedResult = "";
		
		if(URLKey.equals("https://apply-qa.loanglide.com/?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-qa.loanglide.com/?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-stage.loanglide.com/?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply.loanglide.com/?Business=&ServiceProvider="))
		{
			URLKey= "https://apply.loanglide.com/?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/requests?loan="))
		{
			URLKey= "https://apply-qa.loanglide.com/requests?loan="+encryptloanId;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/requests?loan="))
		{
			URLKey= "https://apply-stage.loanglide.com/requests?loan="+loanId;
		}
		else if(URLKey.equals("https://apply.loanglide.com/requests?loan="))
		{
			URLKey = "https://apply.loanglide.com/requests?loan="+loanId;
		}
		else if(URLKey.equals("http://localhost:4000/encrypt?text="))
		{
			URLKey = "http://localhost:4000/encrypt?text="+loanId;
		}
		else if (URLKey.equals("https://api-qa.loanglide.com/api/v1/lenders/1111/files/"))
		{
			URLKey = "https://api-qa.loanglide.com/api/v1/lenders/1111/files/"+updatedDate;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/direct?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-qa.loanglide.com/direct?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/business"))
		{
			URLKey= "https://apply-qa.loanglide.com/business/"+homeURL;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/business"))
		{
			URLKey= "https://apply-stage.loanglide.com/business/"+homeURL;
		}
		else
		{
			URLKey = URLKey;
		}
		
		Thread.sleep(3000);
		System.out.println(": Navigating to (" + URLKey + ") Site");
		APP_LOGS.debug(": Navigating to (" + URLKey + ") Site");
		test.info(": Navigating to (" + URLKey + ") Site");
		try {
			if (captureVideoRecording.equals("Yes")) {
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
				Date date = new Date();
				recorder = new ATUTestRecorder(suitrunvideoPath, "RECVideo-" + Keywords.tcName + dateFormat.format(date), false);
				System.out.println(": Video Recording Started ");
				APP_LOGS.debug(": Video Recording Started ");
				recorder.start();
			}
			if (driver != null) {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": Browser is Already Opened and same will be used for this TestScript execution");
				APP_LOGS.debug(": Browser is Already Opened and same will be used for this TestScript execution");
				driver.get(URLKey);
			} else {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				OpenBrowser(bType);
				driver.get(URLKey);

			}
		} catch (Exception e) {
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Navigate " + URLKey + " Site";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String NavigateToURL(String firstXpathKey, String URLKey) throws InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: NavigateToURL ()
		 * 
		 * @parameter: String firstXpathKey, String URLKey
		 * 
		 * @notes: Navigate to specific URL as metioned in the Data Coulmn in "Test Steps" Sheet.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		failedResult = "";
		
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		String object = OR.getProperty(firstXpathKey);
		objectArray = object.split("__");
		objectIdentifierValue = objectArray[1].trim();
		URLKey = objectIdentifierValue;
		
		if(URLKey.equals("https://apply-qa.loanglide.com/business"))
		{
			URLKey= "https://apply-qa.loanglide.com/business/"+homeURL;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/business"))
		{
			URLKey= "https://apply-stage.loanglide.com/business/"+homeURL;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/requests?loan="))
		{
			URLKey= "https://apply-qa.loanglide.com/requests?loan="+encryptloanId;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/requests?loan="))
		{
			URLKey= "https://apply-stage.loanglide.com/requests?loan="+loanId;
		}
		else if(URLKey.equals("http://localhost:4000/encrypt?text="))
		{
			URLKey = "http://localhost:4000/encrypt?text="+loanId;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/v2/direct?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-qa.loanglide.com/v2/direct?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/direct?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-qa.loanglide.com/direct?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-qa.loanglide.com/?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-qa.loanglide.com/?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else if(URLKey.equals("https://apply-stage.loanglide.com/?Business=&ServiceProvider="))
		{
			URLKey= "https://apply-stage.loanglide.com/?Business="+businessOwner+"&ServiceProvider="+serviceProvider;
		}
		else
		{
			URLKey = URLKey;
		}
		Thread.sleep(3000);
		System.out.println(": Navigating to (" + URLKey + ") Site");
		APP_LOGS.debug(": Navigating to (" + URLKey + ") Site");
		test.info(": Navigating to (" + URLKey + ") Site");
		try {
			if (captureVideoRecording.equals("Yes")) {
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
				Date date = new Date();
				recorder = new ATUTestRecorder(suitrunvideoPath, "RECVideo-" + Keywords.tcName + dateFormat.format(date), false);
				System.out.println(": Video Recording Started ");
				APP_LOGS.debug(": Video Recording Started ");
				recorder.start();
			}
			if (driver != null) {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": Browser is Already Opened and same will be used for this TestScript execution");
				APP_LOGS.debug(": Browser is Already Opened and same will be used for this TestScript execution");
				driver.get(URLKey);
			} else {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				OpenBrowser(bType);
				driver.get(URLKey);

			}
		} catch (Exception e) {
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Navigate " + URLKey + " Site";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	
	public String NavigateDDTTo(String inputData) throws InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: NavigateDDTTo ()
		 * 
		 * @parameter: String URLKey
		 * 
		 * @notes: Navigate to specific URL as metioned in the Data Coulmn in "Test Steps" Sheet.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		failedResult = "";
		inputData = (String) DDTText.get(inputData);
		Thread.sleep(3000);
		System.out.println(": Navigating to"+ inputData+" Site");
		APP_LOGS.debug(": Navigating to"+ inputData+" Site");
		test.info(": Navigating to"+ inputData+" Site");
		try {
			if (captureVideoRecording.equals("Yes")) {
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
				Date date = new Date();
				recorder = new ATUTestRecorder(suitrunvideoPath, "RECVideo-" + Keywords.tcName + dateFormat.format(date), false);
				System.out.println(": Video Recording Started ");
				APP_LOGS.debug(": Video Recording Started ");
				recorder.start();
			}
			if (driver != null) {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": Browser is Already Opened and same will be used for this TestScript execution");
				APP_LOGS.debug(": Browser is Already Opened and same will be used for this TestScript execution");
				driver.get(inputData);
			} else {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				OpenBrowser(bType);
				driver.get(inputData);

			}
		} catch (Exception e) {
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Navigate " + inputData + " Site";
		}
		test.pass("Pass");
		return "PASS";
	}

	
	public String AddExtensionToChrome(String firstXpathKey, String URLKey) throws InterruptedException, AWTException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: AddExtensionToChrome ()
		 * 
		 * @parameter:String firstXpathKey, String URLKey
		 * 
		 * @notes: Navigate to specific URL as metioned in the Data Coulmn in "Test Steps" Sheet and added to the extension
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		failedResult = "";
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
	//	NavigateTo("https://anjireddy.pothireddy@v2solutions.com:mail_123@www.google.com");
		driver.get("https://chrome.google.com/webstore/search/postmanrest client?hl=en-US");
		Thread.sleep(5000);
		
	//	returnElementIfPresent(firstXpathKey);
		
	//	driver.findElement(By.xpath("//div[text()='Tabbed Postman - REST Client']/../..//following-sibling::div[@class='a-na-d-K-ea']//div[@role='button']")).click();
		Click(firstXpathKey);
		Thread.sleep(10000);
	
		
		
	//	uploadThroughAutoIT("C:/V2AutoWH/testdata/ExtensionClick");

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(500);
        
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);       
        
		
        WaitTillElementAppears("RATEIT_BUTTON");
//		driver.get("chrome-extension://coohjcphdfgbiolnekdpbcijmhambjff/index.html");
			
		Thread.sleep(3000);
		System.out.println(": Navigating to (" + URLKey + ") Site");
		APP_LOGS.debug(": Navigating to (" + URLKey + ") Site");
		test.info(": Navigating to (" + URLKey + ") Site");
		try {
			if (captureVideoRecording.equals("Yes")) {
				DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");
				Date date = new Date();
				recorder = new ATUTestRecorder(suitrunvideoPath, "RECVideo-" + Keywords.tcName + dateFormat.format(date), false);
				System.out.println(": Video Recording Started ");
				APP_LOGS.debug(": Video Recording Started ");
				recorder.start();
			}
			if (driver != null) {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": Browser is Already Opened and same will be used for this TestScript execution");
				APP_LOGS.debug(": Browser is Already Opened and same will be used for this TestScript execution");
				driver.get(URLKey);
			} else {
				System.out.println(": Driver Handle: " + driver);
				System.out.println(": No Opened Browser Available, Opening New one");
				APP_LOGS.debug(": No Opened Browser Available, Opening New one");
				OpenBrowser(bType);
				driver.get(URLKey);

			}			
			
		} catch (Exception e) {
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Navigate " + URLKey + " Site";
		}
		test.pass("PASS");
		return "PASS";
	}

	public static String CalculateExecutionTime()
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: CalculateExecutionTime ()
		 * 
		 * @parameter: 
		 * 
		 * @notes: Calculate total execution time of the suite.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		try {
			java.util.Date endDate = new java.util.Date();
			suiteEndTimeInSeconds = (endDate.getTime() / 1000);
			suiteExecutionTimeInSeconds = suiteEndTimeInSeconds-suiteStartTimeInSeconds;
			int hours = (int) suiteExecutionTimeInSeconds / 3600;
			int remainder = (int) suiteExecutionTimeInSeconds - hours * 3600;
			int mins = remainder / 60;
			remainder = remainder - mins * 60;
			int secs = remainder;
			int[] Time = { hours, mins, secs };
			totalExecutionTime = "Execution Time: " + Time[0] + " hour(s) " + Time[1] + " minute(s) " + Time[2] + " second(s)";
			System.out.println(": INFO:=> " + totalExecutionTime);
	
		} catch (Exception e) {
			test.log(Status.ERROR, "ERROR : Unable to calculate time");
			return "FAIL - Not able to calculate time";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String clearTextField(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: clearTextField ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Clearing Text Field.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			System.out.println(": Clearing Text Field");
			APP_LOGS.debug(": Clearing Text Field");
			test.info(": Clearing Text Field");
			Thread.sleep(1000);
			returnElementIfPresent(firstXpathKey).clear();
		} catch (InterruptedException e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not Able to perform clearTextField: " + e.getLocalizedMessage());
			System.out.println("Not Able to perform clearTextField");
		}
		test.pass("Pass");
		return "PASS";

	}

	public String Click(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Click ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Performs Click action on link, Hyperlink, selections or buttons of a web page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		System.out.println(": Performing Click action on " + firstXpathKey);
		APP_LOGS.debug(": Performing Click action on " + firstXpathKey);
		test.info(" Performing Click action on " + firstXpathKey);
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try {
			Thread.sleep(1000);
			if (GTestName.contains("LG")) {
				if (bType.equals("Edge")) {
					System.out.println(": In Edge if for Click ");
					returnElementIfPresent(firstXpathKey).click();
					Thread.sleep(1000);
				} else {
					if (firstXpathKey.equals("SEARCH_BUTTON")||firstXpathKey.equals("LOANGLIDE_BACK_BUTTON")||firstXpathKey.equals("LOANSTATUS_DROPDOWN")||firstXpathKey.equals("SERVICEPROVIDER_DROPDOWN")
							|| firstXpathKey.equals("BUSINESSTYPE_DROPDOWN") || firstXpathKey.equals("OWNERSHIPTYPE_DROPDOWN") || firstXpathKey.equals("ACTIVATE_BUTTON") || firstXpathKey.equals("LENDERNAME_TEXT")
							|| firstXpathKey.equals("INVITE_LINK") || firstXpathKey.equals("SENDFEEDBACK_BUTTON")|| firstXpathKey.equals("MERCHANT_MENUBAR") 
							|| firstXpathKey.equals("UPDATE_BUTTON") || firstXpathKey.equals("CONTINUE_BUTTON") || firstXpathKey.equals("CHANGEPWD_LINK") || firstXpathKey.equals("SENDINVITE_BUTTON")
							|| firstXpathKey.equals("PYMNTESTIMATOR_X_BUTTON") || firstXpathKey.equals("AVGANNUALINCOME_DROPDOWN") || firstXpathKey.equals("BANKACCOUNTTYPE_DROPDOWN") || firstXpathKey.equals("BANKINFO_EDITBUTTON")
							|| firstXpathKey.equals("LOANPURPOSE_INDIRECT_OPTION") || firstXpathKey.equals("LENDER_UPDATE_BUTTON") || firstXpathKey.equals("DEMO_DOB_TXTFIELD") || firstXpathKey.equals("SPID_LABEL") || firstXpathKey.equals("SP_LOANID_LABEL")
							|| firstXpathKey.equals("SERVICEPROVIDER_LABEL") || firstXpathKey.equals("SPROVIDER_LABEL") || firstXpathKey.equals("EXISTBO_BUSNAME_X_BUTTON") || firstXpathKey.equals("BUSINESSINFO_EDIT_BUTTON") || firstXpathKey.equals("EDITBANKINFO_BUTTON")
							|| firstXpathKey.equals("LOANAGREEMENT_CLOSE_BUTTON") || firstXpathKey.equals("LOANAGREEMENT_BACK_BUTTON")|| firstXpathKey.equals("LOANAGREEMENT_X_BUTTON") || firstXpathKey.equals("APPROVE_CHECKBOX") 
							|| firstXpathKey.equals("APPROVE_BUTTON") || firstXpathKey.equals("PLANSLIDER")) {
						returnElementIfPresent(firstXpathKey).click();
					}
					
					else if (firstXpathKey.equals("BO_ACTIVATE_BUTTON")|| firstXpathKey.equals("ACCESSONLY_ACTION_LINK") )
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + businessName + "')]//preceding-sibling::td[1]";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					
					else if (firstXpathKey.equals("CONFIG_UPDATE_BUTTON"))
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + configKey + "')]//following-sibling::td//a//button";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					
					else if (firstXpathKey.equals("CONFIG_DELETE_BUTTON"))
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + configKey + "')]//following-sibling::td/div[@class='flex-column all-center']//button//span[text()='Delete']/..";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					else if (firstXpathKey.equals("LOANIDS_CHECKBOX"))
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + loanId + "']//preceding-sibling::td/span";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					else if (firstXpathKey.equals("BRCODE_UPDATE_BUTTON"))
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + 	refCode + "']//following-sibling::td[5]";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					else if (firstXpathKey.equals("RESOURCE_UPDATE_BUTTON"))
					{
							String object = OR.getProperty(firstXpathKey);
							objectArray = object.split("__");
							objectIdentifierValue = objectArray[1].trim();
							objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']//following-sibling::td[7]/div/a[1]";
							driver.findElement(By.xpath(objectIdentifierValue)).click();
					}
					else 
					{
						wait = new WebDriverWait(driver, Duration.ofSeconds(20));
						wait.until(ExpectedConditions.elementToBeClickable(returnElementIfPresent(firstXpathKey)));
						executor.executeScript("arguments[0].click();", returnElementIfPresent(firstXpathKey));
						Thread.sleep(1000);
					}
				}
			} else {
				returnElementIfPresent(firstXpathKey).click();
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Unable to locate " + firstXpathKey + " element, This can be because new code/content deployemnt on AUT. Please check and update OR file");
			return "FAIL - Not able to click on -- " + firstXpathKey + e.getMessage();
		}
		test.pass("PASS");
		return "PASS";
	}

	public String CloseDBConnection() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: CloseDBConnection ()
		 * 
		 * @parameter: 
		 * 
		 * @notes: Close the database connection.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Closing "+databaseType+" database");
		APP_LOGS.debug(": Closing "+databaseType+" database");
		test.info(": Closing "+databaseType+" database");
		highlight=false;
		captureScreenShot=false;
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println(": "+e.getMessage());
			return "FAIL";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String CloseNewWindow() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: CloseNewWindow ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Close new window page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		System.out.println(": Closing new window");
		APP_LOGS.debug(": Closing new window");
		test.info(": Closing new window");

		try {
			Thread.sleep(2000);
			driver.close();
			Thread.sleep(3000);
			driver.switchTo().window(winHandleBefore);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to close New Window");
			return "FAIL - Close New Window";
		}

		test.pass("PASS");
		return "PASS";
	}

	public String CloseBrowser() throws ATUTestRecorderException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: CloseBrowser ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Closing the opened Browser after the Test Case Execution.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		// getTextOrValues.clear();
		scriptTableFirstRowData = "";
		System.out.println(": Closing the Browser");
		APP_LOGS.debug(": Closing the Browser");
		test.info(" Closing the Browser");
		try {
			driver.close();
			driver = null;
			  if (captureVideoRecording.equals("Yes")) {
			  System.out.println(": Video Recording Stopped ");
			  APP_LOGS.debug(": Video Recording Stopped ");
			  test.info(" Video Recording Stopped "); recorder.stop();
			  Thread.sleep(SYNC_WAIT); }
		} catch (Exception e) {
			test.log(Status.ERROR, "ERROR : Not able to Close Browser");
			return "FAIL - Not able to Close Browser";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String DeleteFile(String path) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: DeleteFile ()
		 * 
		 * @parameter: String path
		 * 
		 * @notes: Deletes file mentions in parameter.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			File file = new File(path);

			if (file.exists()) {
				file.delete();
			}
			Thread.sleep(2000);
			System.out.println(": Deleted a file ");
			APP_LOGS.debug(": Deleted a file ");
			test.info(" Deleted a file ");

		} catch (RuntimeException exception) {
			captureScreenShot = true;
			System.out.println("Error in deleting a file: " + exception.getMessage());
			test.log(Status.ERROR, "ERROR : Error in deleting a file");
			return "FAIL - Error in deleting a file";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String DoubleClick(String firstXpathKey) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: DoubleClick ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: DoubleClick on the WebElement
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			
			System.out.println(": Performing DoubleClick action on " + firstXpathKey);
			APP_LOGS.debug(": Performing DoubleClick action on " + firstXpathKey);
			test.info(" Performing DoubleClick action on " + firstXpathKey);
			
			Actions actions = new Actions(driver);
			WebElement elementLocator = returnElementIfPresent(firstXpathKey);
	//		actions.doubleClick(elementLocator).perform();
			
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLocator);
			actions.doubleClick(elementLocator).build().perform();
			
			
		} catch (RuntimeException e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Unable to locate " + firstXpathKey + " element, This can be because new code/content deployemnt on AUT. Please check and update OR file");
			return "FAIL - Not able to click on -- " + firstXpathKey + e.getMessage();
		}
		test.pass("PASS");
		return "PASS";
	}

	public String deleteFilesFromFolder(String filePath) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: deleteFilesFromFolder (filePath)
		 * 
		 * @parameter: String filePath
		 * 
		 * @notes: Verifying able to delete files from mentioned folder
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Deleting Files from Folder");
		APP_LOGS.debug(": Deleting Files from Folder");
		test.info(" Deleting Files from Folder");
		try {
			String user = System.getProperty("user.name");
			if (filePath.contains("C:\\Users\\{username}\\Downloads")) {
				filePath = filePath.replace("{username}", user);
				System.out.println(filePath);
			}
			File directory = new File(filePath);
			if (directory.isDirectory()) {

				for (int i = 0; i < directory.list().length; i++) {
					File file = new File(directory + "\\" + directory.list()[i]);
					file.delete();
				}

			} else {
				System.out.println("Parent Directory has not anything.");
			}
			System.out.println("Successfully deleted directory : " + filePath);
			APP_LOGS.debug("Successfully deleted directory : " + filePath);
		} catch (Exception ex) {
			System.out.println("Error in deleting contents of the directory : " + filePath + " with exception " + ex.getMessage());
			test.log(Status.ERROR, "ERROR : Error in deleting contents of the directory : " + filePath);
			return "FAIL - Error in deleting contents of the directory : " + filePath;
		}
		test.pass("PASS");
		return "PASS";
	}

	public String EndTimer(String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: EndTimer ()
		 * 
		 * @parameter:
		 * 
		 * @notes: It will calculate the loading time.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": End the timer");
		APP_LOGS.debug(": End the timer");
		test.info(": End the timer");
		try{
			endTimer = System.currentTimeMillis()/1000;
			totalTime = endTimer - startTimer;
			int hours = (int) totalTime / 3600;
			int remainder = (int) totalTime - hours * 3600;
			int mins = remainder / 60;
			remainder = remainder - mins * 60;
			int secs = remainder;
			int[] Time = { hours, mins, secs };
			totalTimeLoad = " : " + Time[0] + " hour(s) " + Time[1] + " minute(s) " + Time[2] + " second(s)";
			System.out.println(": "+ inputData + " page loading time"+totalTimeLoad);
			APP_LOGS.debug(": "+ inputData + " page loading time"+totalTimeLoad);
			test.info(": "+ inputData + " page loading time"+totalTimeLoad);
		}catch (Exception e){
			test.log(Status.ERROR, "ERROR : Unable to end the timer");
			return "FAIL - Unable to end the timer";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String ExecuteAndVerifyDBQuery(String firstXpathKey, String expData) throws SQLException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ExecuteAndVerifyDBQuery ()
		 * 
		 * @parameter: 
		 * 
		 * @notes: Fetches the data from database and verifies the data with expected.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight=false;
		captureScreenShot=false;
		System.out.println(": Fetching the data from "+databaseType+" database");
		APP_LOGS.debug(": Fetching the data from "+databaseType+" database");
		test.info(": Fetching the data from "+databaseType+" database");
		String selectQuery = OR.getProperty(firstXpathKey);
		if(selectQuery.equals("SELECT BusinessReferralCode FROM loanglide.business where BusinessName"))
		{
			selectQuery = selectQuery+" = "+ "'"+businessName+"';"; 
		}
		else if(selectQuery.equals("SELECT LoanTransactionStatusID FROM loanglide.loantransaction where LoanApplicationId"))
		{
			selectQuery = selectQuery+" = "+ "'"+loanId+"';";
		}
		else if(selectQuery.equals("SELECT FICO FROM loanglide.consumercreditinfo where BorrowerProfileID"))
		{
			System.out.println(selectQuery);
			selectQuery = selectQuery+" = "+ "'"+borrowerProfile+"';";
			System.out.println(selectQuery);
		}
		else
		{
			selectQuery = selectQuery;
		}
		ArrayList<String> actList=new ArrayList<String>();
		ArrayList<String> expList=new ArrayList<String>();
		expList.add(expData);
		
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectQuery);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int tableColumnCount = rsMetaData.getColumnCount();
			while (rs.next()) {
				for(int i=1; i<=tableColumnCount; i++){
					actList.add(rs.getString(i));			
				}
			}
			
			if (actList.toString().contains(expList.toString())) {
				System.out.println(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
				APP_LOGS.debug(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
			} else {
				System.out.println(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
				return "FAIL - Actual list is -> " + actList + " AND Expected list is-> " + expList;
			}
		} catch (SQLException e) {
			System.out.println(": "+e.getMessage());
			return "FAIL";
		}
		test.pass("PASS");
		return "PASS";
	}
	public String ExecuteAndVerifyDBQueryDDT(String firstXpathKey, String inputData) throws SQLException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ExecuteAndVerifyDBQueryDDT ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Fetches the data from database and verifies the data with expected.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight=false;
		captureScreenShot=false;
		String actData = null;
		String expData = null;
		System.out.println(": Fetching the data from "+databaseType+" database");
		APP_LOGS.debug(": Fetching the data from "+databaseType+" database");
		test.info(": Fetching the data from "+databaseType+" database");
		String selectQuery = OR.getProperty(firstXpathKey);
		if(selectQuery.equals("SELECT FICO FROM loanglide.consumercreditinfo where BorrowerProfileID"))
		{
			selectQuery = selectQuery+" = "+ "'"+borrowerProfile+"';";
		}
		else
		{
			selectQuery = selectQuery;
		}
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectQuery);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int tableColumnCount = rsMetaData.getColumnCount();
			while (rs.next()) {
				for(int i=1; i<=tableColumnCount; i++){
					actData = rs.getString(i);			
				}
			}
			expData = (String) DDTText.get(inputData);
			if (actData.equals(expData)) {
				System.out.println(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
				APP_LOGS.debug(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
				test.pass(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
			} else {
				System.out.println(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
				APP_LOGS.debug(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
				test.fail(": Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData);
				return "FAIL - Actual FICO value for loanid " + adminLoanID + " is -> " + actData + " AND Expected FICO value for loanid " + adminLoanID + " is-> " + expData;
			}
		} catch (SQLException e) {
			System.out.println(": "+e.getMessage());
			return "FAIL";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String ExecuteAndVerifyMultiColumnDBQuery(String firstXpathKey, String expData) throws SQLException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ExecuteAndVerifyMultiColumnDBQuery ()
		 * 
		 * @parameter: String firstXpathKey, String expData
		 * 
		 * @notes: Fetches the multiple column data from database and verifies the data with expected.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight=false;
		captureScreenShot=false;
		System.out.println(": Fetching the data from "+databaseType+" database");
		APP_LOGS.debug(": Fetching the data from "+databaseType+" database");
		test.info(": Fetching the data from "+databaseType+" database");
		String selectQuery = OR.getProperty(firstXpathKey);
		selectQuery = selectQuery+"="+"'"+loanId+"'";
		ArrayList actList=new ArrayList();
		ArrayList expList=new ArrayList();
		expList.add(expData);
		
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(selectQuery);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int tableColumnCount = rsMetaData.getColumnCount();
			while (rs.next()) {
				
				
				System.out.println(rs .getDouble(1) + "  " + rs.getInt(2) + "  " + rs.getString(3));
				actList.add(rs .getDouble(1));
				actList.add(rs.getInt(2));
				actList.add(rs.getString(3));
	
			}
			
			System.out.println(" ActList ="+actList);
			System.out.println(" ExpList ="+expList);
			
			
			if (actList.equals(expList)) {
				System.out.println(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
				APP_LOGS.debug(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
			} else {
				System.out.println(": Actual list is -> " + actList + " AND Expected list is-> " + expList);
				return "FAIL - Actual list is -> " + actList + " AND Expected list is-> " + expList;
			}
		} catch (SQLException e) {
			System.out.println(": "+e.getMessage());
			return "FAIL";
		}
		test.pass("PASS");
		return "PASS";
	}

	
	public String GetAttributeValue(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetAttributeValue ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the attribute value of the web element of the passed "firstXpathKey" and stores it into a global Hash map "getTextOrValues".
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Getting " + firstXpathKey + " Attribute Value from the Page");
		APP_LOGS.debug(": Getting " + firstXpathKey + " Attribute Value from the Page");
		test.info(" Getting " + firstXpathKey + " Attribute Value from the Page");
		highlight = false;
		captureScreenShot = false;
		try {
			if(firstXpathKey.equals("HOMEURL_TEXTFIELD"))
			{
				homeURL = returnElementIfPresent(firstXpathKey).getAttribute("Value").trim();
				System.out.println(homeURL);
			}
			else if(firstXpathKey.equals("ENTERCONFIGKEY_TEXTFIELD"))
			{
				configKey = returnElementIfPresent(firstXpathKey).getAttribute("Value").trim();
			}
			else if(firstXpathKey.equals("MERCHANT_AMOUNT_TEXTFIELD"))
			{
				String Amount = returnElementIfPresent(firstXpathKey).getAttribute("Value").trim();
				DecimalFormat formatter = new DecimalFormat("#,###.00");
				int i =Integer.parseInt(Amount);
				Amount = formatter.format(i);
				requestedAmount = Amount.toString();
			}
			else
			{
				getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getAttribute("Value").trim());
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR: Not able to read text from " + firstXpathKey);
			return "FAIL - Not able to read text from " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";

	}

	public String GetBusinessName(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetBusinessName ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the business name
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try
		{
			Thread.sleep(3000);
			System.out.println(": Getting the business name:");
			APP_LOGS.debug(": Getting the business name:");
			test.info(": Getting the business name:");
			businessName = (String) getTextOrValues.get(firstXpathKey);
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get business owner");
			APP_LOGS.debug("FAIL - Not Able to get business owner");
		}
		test.pass("PASS");
		return "PASS";
	}
	public String GetMonthlyPayment(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetMonthlyPayment ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the monthly payment
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try
		{
			Thread.sleep(3000);
			System.out.println(": Getting the monthly payment");
			APP_LOGS.debug(": Getting the monthly payment");
			test.info(": Getting the monthly payment");
			
			String payment = returnElementIfPresent(firstXpathKey).getText().trim();
			System.out.println(": Monthly payment ="+payment);
			payment = payment +"/month";
			String monthlyPayment = payment;
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get monthly payment");
			APP_LOGS.debug("FAIL - Not Able to get monthly payment");
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String GetTermMonths(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetTermMonths ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the term months
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try
		{
			Thread.sleep(3000);
			System.out.println(": Getting the term months");
			APP_LOGS.debug(": Getting the term months");
			test.info(": Getting the term months");
			
			String payment = returnElementIfPresent(firstXpathKey).getText().trim();
			System.out.println(": Monthly payment ="+payment);
			payment = payment +"/month";
			String monthlyPayment = payment;
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get monthly payment");
			APP_LOGS.debug("FAIL - Not Able to get monthly payment");
		}
		test.pass("PASS");
		return "PASS";
	}
	public String GetBusinessOwner() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetBusinessOwner ()
		 * 
		 * @parameter: 
		 * 
		 * @notes: Get the business owner
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try
		{
			Thread.sleep(3000);
			String actURL = driver.getCurrentUrl();
			System.out.println(": Getting the business owner:");
			APP_LOGS.debug(": Getting the business owner:");
			test.info(": Getting the business owner:");
			String[] actURLContent = actURL.split("/");
			businessOwner = actURLContent[actURLContent.length - 1];
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get business owner");
			APP_LOGS.debug("FAIL - Not Able to get business owner");
		}
		test.pass("PASS");
		return "PASS";
	}

	public String GetText(String firstXpathKey) throws IOException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetText ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the text of the web element of the passed "firstXpathKey" and stores it into a global Hash map "getTextOrValues".
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Getting " + firstXpathKey + " Text from the Page");
		APP_LOGS.debug(": Getting " + firstXpathKey + " Text from the Page");
		test.info(" Getting " + firstXpathKey + " Text from the Page");
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		
		try {
			if(firstXpathKey.equals("AGREEMENT_LOANID"))
			{
				String loanId = returnElementIfPresent(firstXpathKey).getText().trim();
				String[] inputData = loanId.split(":");
				loanId = inputData[1];
				getTextOrValues.put(firstXpathKey, loanId.trim());
			}
			else if(firstXpathKey.equals("LOANID_ENCRYPT_TEXT"))
			{
				encryptloanId = returnElementIfPresent(firstXpathKey).getText().trim();
			}
			else if(firstXpathKey.equals("BOREGISTRATION_STATUS"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + businessName + "')]//following-sibling::td[7]";
				String status = driver.findElement(By.xpath(objectIdentifierValue)).getText();
				getTextOrValues.put(firstXpathKey, status.trim());
			}
			else if(firstXpathKey.equals("FICO_LOANID"))
			{
				adminLoanID = returnElementIfPresent(firstXpathKey).getText().trim();
				borrowerProfile= adminLoanID.substring(1,adminLoanID.length());
			}
			else if(firstXpathKey.equals("FICO_LOANID"))
			{
				adminLoanID = returnElementIfPresent(firstXpathKey).getText().trim();
				borrowerProfile= adminLoanID.substring(1,adminLoanID.length());
			}
			
			else
			{
				getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getText().trim());
			}
			
			
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR: Not able to read text from " + firstXpathKey);
			return "FAIL - Not able to read text from " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String GetUpdatedDate(String firstXpathKey) throws IOException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetUpdatedDate ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Get the text of the web element of the passed "firstXpathKey" and stores it into a global Hash map "getTextOrValues".
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Getting updated date from the database");
		APP_LOGS.debug(": Getting updated date from the database");
		test.info(": Getting updated date from the database");
		highlight = false;
		captureScreenShot = false;
		try {
			
			String selectQuery = OR.getProperty(firstXpathKey);
			
			if(selectQuery.equals("SELECT UpdatedDate FROM loanglide.loantransaction where LoanApplicationID"))
			{
				selectQuery = selectQuery+" = "+ "10435;";
				System.out.println(selectQuery);
			}
			else if(selectQuery.equals("SELECT UploadID FROM loanglide.filexfrhistory where UpdatedDate"))
			{
				selectQuery = selectQuery+" >= "+ "2020-05-28 12:42:21.0;";
				System.out.println(selectQuery);
			}
			else
			{
				selectQuery = selectQuery;
			}
			ArrayList<String> actList=new ArrayList<String>();
			ArrayList<String> expList=new ArrayList<String>();
			
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(selectQuery);
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int tableColumnCount = rsMetaData.getColumnCount();
				while (rs.next()) 
				{
					for(int i=1; i<=tableColumnCount; i++)
					{
				//		actList.add(rs.getString(i));
						updatedDate = rs.getString(i);
					}
					
					System.out.println("Updated Date="+updatedDate);
				}
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR: Not able to read text from " + firstXpathKey);
			return "FAIL - Not able to read text from " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String GetName(String firstXpathKey, String secondXpathKey, String inputData) throws IOException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetName ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Get the text of the web element of the passed "firstXpathKey", "secondXpathKey" and stores it into a global Hash map "getTextOrValues".
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Getting name from the Page");
		APP_LOGS.debug(": Getting name from the Page");
		test.info(": Getting name from the Page");
		highlight = false;
		captureScreenShot = false;
		try {
			
			Thread.sleep(3000);
			String fname = returnElementIfPresent(firstXpathKey).getText().trim();
			String lname = returnElementIfPresent(secondXpathKey).getText().trim();
			inputData = fname+" "+lname;
			getTextOrValues.put("USERS_NAME_TEXT", inputData.trim());
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR: Not able to read text from " + firstXpathKey);
			return "FAIL - Not able to read text from " + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	public String getColumnData(String firstXpathKey, String secondXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: getColumnData ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Get the column data
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		String data = "";
		WebElement table = returnElementIfPresent(firstXpathKey);
		List<WebElement> th = table.findElements(By.tagName("th"));
		System.out.println(th.get(1).getText());
		int col_position = 0;
		System.out.println(": Getting " + secondXpathKey + " Column Data from the Table");
		APP_LOGS.debug(": Getting " + secondXpathKey + " Column Data from the Table");
		for (int i = 0; i < th.size(); i++) {
			if ((returnElementIfPresent(secondXpathKey).getText()).equalsIgnoreCase(th.get(i).getText())) {
				col_position = i + 1;
				break;
			}
		}
		List<WebElement> FirstColumns = table.findElements(By.xpath("//tr/td[" + col_position + "]"));
		for (WebElement e : FirstColumns) {
			data = data + e.getText() + ",";
		}
		return data;
	}

	public String GetColumnDataLink(String firstXpathKey, String secondXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetColumnDataLink ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Get the column data
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		String data = "";
		WebElement table = returnElementIfPresent(firstXpathKey);
		System.out.println("Table Tag Name : " + table.getTagName());
		List<WebElement> ul = table.findElements(By.tagName("th"));
		int col_position = 0;
		for (int i = 0; i < ul.size(); i++) {
			System.out.println(": Getting " + secondXpathKey + " Column Data from the Table");
			APP_LOGS.debug(": Getting " + firstXpathKey + " Column Data from the Table");

			if ((returnElementIfPresent(secondXpathKey).getText()).equalsIgnoreCase(ul.get(i).getText())) {
				col_position = i + 1;
				break;
			}
		}

		List<WebElement> FirstColumns = table.findElements(By.xpath("//tr/td[" + col_position + "]"));
		for (WebElement e : FirstColumns) {
			data = data + e.getText() + ",";
		}

		return data;
	}

	public String GetFileNameFromDownloadFolder(String filePath) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetFileNameFromDownloadFolder ()
		 * 
		 * @parameter: String filePath
		 * 
		 * @notes: Get the filenames from Folder
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;

		System.out.println(": Getting FileName from Folder");
		APP_LOGS.debug(": Getting FileName from Folder");
		try {
			String user = System.getProperty("user.name");
			if (filePath.contains("C:\\Users\\{username}\\Downloads\\")) {
				filePath = filePath.replace("{username}", user);
				System.out.println(filePath);
			}
			File[] files = new File(filePath).listFiles();
			for (File file : files) {
				if (file.isFile()) {
					filename.add(file.getName());
					System.out.println(filename);
					fileName = filename.toString();
					fileName = fileName.replaceAll("[\\[\\]]", "");
					System.out.println(fileName);
				}
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to get Filname from Folder";
		}
		return "PASS";
	}

	public static Keywords getKeywordsInstance() throws IOException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: getKeywordsInstance ()
		 * 
		 * @returns: Get the keyword instance 
		 * 
		 * @END
		 */
		
		if (keywords == null) {
			keywords = new Keywords();
		}
		return keywords;
	}

	public String getLastTestCaseName() {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: getLastTestCaseName ()
		 * 
		 * @returns: returns last test case name from Master.xlsx file which have runmode Y into any combination
		 * 
		 * @END
		 */
		Xls_Reader x = new Xls_Reader(mastertsmodulePath + "/MasterTSModule.xlsx");
		String suiteType = suitetype;

		if (!suiteType.contains("_") && !suiteType.equalsIgnoreCase("Regression")) {
			int totalRows = x.getRowCount("Test Cases");
			String lastTestCaseName = null;
			String tcType = null;
			String runMode = null;
			for (int i = 1; i <= totalRows; i++) {
				tcType = x.getCellData("Test Cases", 1, i);
				if (tcType.contains(suiteType)) {
					runMode = x.getCellData("Test Cases", 2, i);
					if (runMode.contains("Y")) {
						lastTestCaseName = x.getCellData("Test Cases", 0, i);
					}
				}
			}
			System.out.println("INFO:=> Last Test Case Name is: " + lastTestCaseName);
			return lastTestCaseName;

		} else if (suiteType.contains("_")) {
			String splitArray[] = suiteType.split("_");
			int totalRows = x.getRowCount("Test Cases");
			String lastTestCaseName = null;
			String tcType = null;
			String runMode = null;
			for (int i = 1; i <= totalRows; i++) {
				tcType = x.getCellData("Test Cases", 1, i);
				if (tcType.contains(splitArray[0]) || tcType.contains(splitArray[1])) {
					runMode = x.getCellData("Test Cases", 2, i);
					if (runMode.contains("Y")) {
						lastTestCaseName = x.getCellData("Test Cases", 0, i);
					}
				}
			}
			System.out.println("INFO:=> Last Test Case Name is: " + lastTestCaseName);
			return lastTestCaseName;

		} else {
			System.out.println("INFO:=> This suiteType is " + suiteType);
			int totalRows = x.getRowCount("Test Cases");
			String lastTestCaseName = null;
			String runMode = null;
			for (int i = 1; i <= totalRows; i++) {
				runMode = x.getCellData("Test Cases", 2, i);
				if (runMode.equalsIgnoreCase("Y")) {
					lastTestCaseName = x.getCellData("Test Cases", 0, i);
				}
			}
			System.out.println("INFO:=> Last Test Case Name is: " + lastTestCaseName);
			return lastTestCaseName;
		}
	}

	public String GetLoanId(String firstXpathkey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetLoanId ()
		 * 
		 * @parameter:String firstXpathkey
		 * 
		 * @notes: Get the loan Id.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Getting the loan Id");
		APP_LOGS.debug(": Getting the loan Id");
		test.info(": Getting the loan Id");
		try
		{
			
				loanId = returnElementIfPresent(firstXpathkey).getText();
				getTextOrValues.put(firstXpathkey, loanId.trim());
			
			
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get the loan id");
			APP_LOGS.debug("FAIL - Not Able to get the loan id");
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String GetServiceProvider() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetServiceProvider ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Get the service provider
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try
		{
			Thread.sleep(3000);
			String actURL = driver.getCurrentUrl();
			System.out.println(": Getting the service provider:");
			APP_LOGS.debug(": Getting the service provider:");
			test.info(": Getting the service provider:");
			String[] actURLContent = actURL.split("/");
			serviceProvider = actURLContent[actURLContent.length - 1];
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get the service provider");
			APP_LOGS.debug("FAIL - Not Able to get the service provider");
		}
		test.pass("PASS");
		return "PASS";
	}

	public String GetBrowser() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: GetBrowser ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Get the browser
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		String rootPath = System.getProperty("user.dir");
		try
		{
			Thread.sleep(3000);
			System.out.println(": Getting the browser:");
			APP_LOGS.debug(": Getting the browser:");
			test.info(": Getting the browser:");
			
			System.setProperty("webdriver.chrome.driver", rootPath+"/sysfiles/browserdrivers/chromedriver/chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://anjireddy.pothireddy@v2solutions.com:mail_123@www.google.com");
			driver.manage().window().maximize();
			Thread.sleep(5000);
		
			
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to get the service provider");
			APP_LOGS.debug("FAIL - Not Able to get the service provider");
		}
		test.pass("PASS");
		return "PASS";
	}

	
	public String HighlightElement(String firstXpathkey) throws InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Constants
		 * 
		 * @method: HighlightElement ()
		 * 
		 * @parameter: WebElement element
		 * 
		 * @notes: Highlights the Web Objects for verifications
		 * 
		 * @returns: No return type
		 * 
		 * @END
		 */
		System.out.println(": Highlighting the "+firstXpathkey);
		APP_LOGS.debug(": Highlighting the "+firstXpathkey);
		test.info(": Highlighting the "+firstXpathkey);
		try {
			Thread.sleep(2000);
			WebElement element = returnElementIfPresent(firstXpathkey);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: green; border: 3px solid green;");
			Thread.sleep(1000);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color:''");
			
		}
		catch(Exception e)
		{
			captureScreenShot = true;
			System.out.println("FAIL - Not able to highlight the "+firstXpathkey);
			APP_LOGS.debug("FAIL - Not able to highlight the "+firstXpathkey);
			test.fail("FAIL - Not able to highlight the "+firstXpathkey);
		}
		test.pass("PASS");
		return "PASS";
	}

	public String InputDDTdata(String firstXpathKey, String secondXpathKey, String inputData) throws Exception 
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: InputDDTdata ()
		 * 
		 * @parameter: String firstXpathKey & String inputData
		 * 
		 * @notes: Inputs the value in any edit box. Value will be a multiple test data obtained from the "Test Data" sheet of the master xlsx. Data driven testing
		 * is achieved using the keyword InputDDTdata.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed
		 * because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */ 
		String numRegex1 = "[0-9].[0-9]";
		String numRegex2 = "[0-9][0-9].[0-9]";
		highlight=false;
		captureScreenShot=false;		
		inputData = (String) DDTText.get(inputData);			
		
		if (inputData.matches(numRegex1)) {
			NumberFormat nf = NumberFormat.getInstance();
			Number number = nf.parse(inputData);
			long lnputValue = number.longValue();
			inputData = String.valueOf(lnputValue);
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			try {
				returnElementIfPresent(firstXpathKey).clear();
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			} catch (Exception e) {
				return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
			}
		} else if (inputData.matches(numRegex2)) {
			NumberFormat nf = NumberFormat.getInstance();
			Number number = nf.parse(inputData);
			long lnputValue = number.longValue();
			inputData = String.valueOf(lnputValue);
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			try {
				returnElementIfPresent(firstXpathKey).clear();
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			} catch (Exception e) {
				captureScreenShot = true;
				return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
			}
		} else if (inputData.equals("Random")) {
			Random randNum = new Random();
			Set<Integer> set = new LinkedHashSet<Integer>();
			while (set.size() < 9) {
				set.add(randNum.nextInt(9) + 1);
			}
			Object[] arr = set.toArray();
			for (int j = 0; j < arr.length; j++) {
			}
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
			}
			String str = sb.toString();
			inputData = str;
			
			if (inputData.startsWith("6")) {
				inputData = inputData.replace('6', '7');
			} else if (inputData.startsWith("9")) {
				inputData = inputData.replace('9', '5');
			} else {
				inputData = inputData;
			}
			
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field"); 
			returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			inputData = inputData.substring(5, 9);
			getTextOrValues.put(firstXpathKey, inputData.trim());
		} 
		
		else if (inputData.equals("Auto") || inputData.equals("User")|| inputData.equals("Fert") || inputData.equals("Dental")) {
			SimpleDateFormat tsdf = new SimpleDateFormat("ddMMMyyHHmmssz");
			java.util.Date tcurDate = new java.util.Date();
			String tstrDate = tsdf.format(tcurDate);
			String tstrActDate = tstrDate.toString();
			inputData = inputData + tstrActDate;
			getTextOrValues.put(firstXpathKey, inputData.trim());
			
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			returnElementIfPresent(firstXpathKey).sendKeys(inputData);
		}
		else if(inputData.equals("VOIE")){
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String pass = "";
			for (int i = 0; i < 4; i++) {
			    pass += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=pass+"@loanglide.com";
			getTextOrValues.put(firstXpathKey, inputData.trim());
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			
		}
		else if(inputData.equals("DATE")){
			int startYear=1980;
			int endYear=2002;
			long start = Timestamp.valueOf(startYear+1+"-1-1 0:0:0").getTime();
			long end = Timestamp.valueOf(endYear+"-1-1 0:0:0").getTime();
			long ms=(long) ((end-start)*Math.random()+start);
			Date hireday=new Date(ms);
			SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
			String ds = sdf.format(hireday);
			inputData=ds;
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			getTextOrValues.put(firstXpathKey, inputData.trim());
		}
		else if(inputData.equals("FirstName") || inputData.equals("LastName")){
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String Name = "";
			for (int i = 0; i < 5; i++) {
				Name += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=Name;
			Name = inputData;
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			getTextOrValues.put(firstXpathKey, inputData.trim());
			
		}
	
		else 
		{
			System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
			try 
			{
				if (firstXpathKey.equals("SSN_TEXTFIELD")) {
					returnElementIfPresent(firstXpathKey).sendKeys(inputData);
					inputData = inputData.substring(5, 9);
					getTextOrValues.put(firstXpathKey, inputData.trim());
				}
				else
				{
					getTextOrValues.put(firstXpathKey, inputData.trim());
					returnElementIfPresent(firstXpathKey).sendKeys(inputData);
					
				}
			}
			
			catch (Exception e) 
			{
				captureScreenShot = true;
				return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
			}
			
		}
		test.pass("PASS");
		return "PASS";
	}

	public String InputText(String firstXpathKey, String secondXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Input ()
		 * 
		 * @parameter: String firstXpathKey , String secondXpathKey & String inputData
		 * 
		 * @notes: Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		if (inputData.isEmpty()) {
			System.out.println(": Test Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Test Data is Empty, taking this value from Hashmap");
			test.info(" Test Data is Empty, taking this value from Hashmap");
			inputData = (String) getTextOrValues.get(secondXpathKey);
			System.out.println(": expText " + inputData);
			APP_LOGS.debug(": expText " + inputData);
			test.info(": expText " + inputData);
			if (inputData == null) {
				System.out.println(": No Test Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				test.info(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				inputData = returnElementIfPresent(secondXpathKey).getText().trim();
			}
		} else if (inputData.equals("Auto") || inputData.equals("User") || inputData.equals("Dental") || inputData.equals("AutoLender") || inputData.equals("Lender") || inputData.equals("Config")|| inputData.equals("Employee")
				|| inputData.equals("HI") || inputData.equals("Resource") || inputData.equals("Fert")|| inputData.equals("Medical")) {
			
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String Name = "";
			for (int i = 0; i < 10; i++) {
				Name += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=Name;
			getTextOrValues.put(firstXpathKey, inputData.trim());
		} else if (inputData.equals("Auto@mail.com")) {
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String Name = "";
			for (int i = 0; i < 8; i++) {
				Name += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=Name+"@mail.com";
			getTextOrValues.put(firstXpathKey, inputData.trim());
		}else if(inputData.equals("Mail")){
				long timeSeed = System.nanoTime();
				double randSeed = Math.random() * 1000;
				long midSeed = (long) (timeSeed * randSeed);
				String s = midSeed + "";
				String subStr = s.substring(0, 9);
				inputData=inputData+subStr+"@mailinator.com";
		}
		else if(inputData.equals("ewq")){
			long timeSeed = System.nanoTime();
			double randSeed = Math.random() * 1000;
			long midSeed = (long) (timeSeed * randSeed);
			String s = midSeed + "";
			String subStr = s.substring(0, 9);
			inputData=inputData+subStr+"@loanglide.com";
		}
		else if (inputData.equals("Random")) {
			Random randNum = new Random();
			Set<Integer> set = new LinkedHashSet<Integer>();
			while (set.size() < 9) {
				set.add(randNum.nextInt(9) + 1);
			}
			Object[] arr = set.toArray();
			for (int j = 0; j < arr.length; j++) {
			}
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
			}
			String str = sb.toString();
			inputData = str;
			
			if (inputData.startsWith("6")) {
				inputData = inputData.replace('6', '7');
			} else if (inputData.startsWith("9")) {
				inputData = inputData.replace('9', '5');
			} else {
				inputData = inputData;
			}
			getTextOrValues.put(firstXpathKey, inputData.trim());
		}
		else if(inputData.equals("VOIE")){
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String pass = "";
			for (int i = 0; i < 4; i++) {
			    pass += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=pass+"@loanglide.com";
			getTextOrValues.put(firstXpathKey, inputData.trim());
		}
		else if(inputData.equals("DATE")){
			int startYear=1980;
			int endYear=2002;
			long start = Timestamp.valueOf(startYear+1+"-1-1 0:0:0").getTime();
			long end = Timestamp.valueOf(endYear+"-1-1 0:0:0").getTime();
			long ms=(long) ((end-start)*Math.random()+start);
			Date hireday=new Date(ms);
			SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
			String ds = sdf.format(hireday);
			inputData=ds;
		}
		else if(inputData.equals("BRCODE")){
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			refCode = "";
			for (int i = 0; i < 5; i++) {
				refCode += alphabet.charAt(random.nextInt(alphabet.length()));
				refCode = refCode.toUpperCase();
			}
			inputData=refCode;
			refCode = inputData;
			getTextOrValues.put(firstXpathKey, inputData.trim());
			
		}
		else if(inputData.equals("FirstName") || inputData.equals("LastName")){
			String alphabet = "abcdefghijklmnopqrstuvwxyz";
			Random random = new Random();
			String Name = "";
			for (int i = 0; i < 5; i++) {
				Name += alphabet.charAt(random.nextInt(alphabet.length()));
			}
			inputData=Name;
			Name = inputData;
			getTextOrValues.put(firstXpathKey, inputData.trim());
			
		}
		
		System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		test.info(" Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		Thread.sleep(1000);
		highlight = false;
		captureScreenShot = false;
		try {
			if (GTestName.contains("LG")) {
				Actions actions = new Actions(driver);
				if (bType.equals("Edge")) 
				{
					System.out.println(": For edge browser");
					returnElementIfPresent(firstXpathKey).click();
					returnElementIfPresent(firstXpathKey).clear();
					for (int i = 0; i < inputData.length(); i++) {
						char eachInvidualCharacter = inputData.charAt(i);
						String indivualLetter = new StringBuilder().append(eachInvidualCharacter).toString();
						actions.sendKeys(indivualLetter);
						actions.build().perform();
						Thread.sleep(50);
					}
					Thread.sleep(100);
					actions.moveByOffset(300, 0).doubleClick();
					actions.build().perform();
					Thread.sleep(1000);
				} 
				else if (tBedType.equals("MOBILE_EMULATION")) {
					if (firstXpathKey.equals("CITY_TEXTFIELD") == true) {
						returnElementIfPresent(firstXpathKey).sendKeys(inputData);
						Thread.sleep(1000);
					} else {
						if (firstXpathKey.equals("SSN_TEXTFIELD") || firstXpathKey.equals("BP_SSN_TEXTFIELD")) {
							actions.moveToElement(returnElementIfPresent(firstXpathKey));
							Thread.sleep(500);
							actions.doubleClick();
							Thread.sleep(1000);
							returnElementIfPresent(firstXpathKey).click();
							returnElementIfPresent(firstXpathKey).clear();
							for (int i = 0; i < inputData.length(); i++) {
								char eachInvidualCharacter = inputData.charAt(i);
								String indivualLetter = new StringBuilder().append(eachInvidualCharacter).toString();
								actions.sendKeys(indivualLetter);
								actions.build().perform();
								Thread.sleep(50);
							}
							Thread.sleep(100);
							actions.moveByOffset(300, 0).doubleClick();
							actions.build().perform();
							Thread.sleep(500);
							
					//		returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							getTextOrValues.put("SSNSAME_TEXTFIELD", inputData.trim());
							inputData = inputData.substring(5, 9);
							getTextOrValues.put(firstXpathKey, inputData.trim());
						} else {
							returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							Thread.sleep(1000);
						}
					}
				}
				else if (tBedType.equals("DESKTOP")) {
					if (firstXpathKey.equals("CITY_TEXTFIELD") == true) {
						returnElementIfPresent(firstXpathKey).sendKeys(inputData);
						Thread.sleep(1000);
					} else {
						if (firstXpathKey.equals("SSN_TEXTFIELD")|| firstXpathKey.equals("BP_SSN_TEXTFIELD")) {
							Thread.sleep(500);
							actions.moveToElement(returnElementIfPresent(firstXpathKey));
							Thread.sleep(500);
							actions.doubleClick();
							Thread.sleep(1000);
							returnElementIfPresent(firstXpathKey).click();
							returnElementIfPresent(firstXpathKey).clear();
							for (int i = 0; i < inputData.length(); i++) {
								char eachInvidualCharacter = inputData.charAt(i);
								String indivualLetter = new StringBuilder().append(eachInvidualCharacter).toString();
								actions.sendKeys(indivualLetter);
								actions.build().perform();
								Thread.sleep(50);
							}
							Thread.sleep(100);
							actions.moveByOffset(300, 0).doubleClick();
							actions.build().perform();
							Thread.sleep(500);	
							
					//		returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							getTextOrValues.put("SSNSAME_TEXTFIELD", inputData.trim());
							inputData = inputData.substring(5, 9);
							getTextOrValues.put(firstXpathKey, inputData.trim());
						} 
						else if(firstXpathKey.equals("ACCOUNTNUMBER_TEXTFIELD"))
						{
							returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							inputData = inputData;
							getTextOrValues.put("ACCOUNTNUMBER_TEXTFIELD", inputData.trim());
						}
						else if(firstXpathKey.equals("RESOURCE_NAME_TXTFIELD"))
						{
							returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							resourceName = inputData;
							getTextOrValues.put("ACCOUNTNUMBER_TEXTFIELD", resourceName.trim());
						}
						else
						{
							returnElementIfPresent(firstXpathKey).sendKeys(inputData);
							Thread.sleep(1000);
						}
					}
				}
		
				else {
					actions.moveToElement(returnElementIfPresent(firstXpathKey));
					Thread.sleep(500);
					actions.doubleClick();
					Thread.sleep(1000);
					returnElementIfPresent(firstXpathKey).click();
					returnElementIfPresent(firstXpathKey).clear();
					for (int i = 0; i < inputData.length(); i++) {
						char eachInvidualCharacter = inputData.charAt(i);
						String indivualLetter = new StringBuilder().append(eachInvidualCharacter).toString();
						actions.sendKeys(indivualLetter);
						actions.build().perform();
						Thread.sleep(50);
					}
					Thread.sleep(100);
					actions.moveByOffset(300, 0).doubleClick();
					actions.build().perform();
					Thread.sleep(500);
				}
			} else {
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			}
		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to enter -- " + inputData + " in " + firstXpathKey + "Field" + e.getMessage());
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String JSEInputText(String firstXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: JSEInputText ()
		 * 
		 * @parameter: String firstXpathKey & String inputData
		 * 
		 * @notes: Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		highlight = false;
		captureScreenShot = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		try 
		{
			WebElement element = returnElementIfPresent(firstXpathKey);
			js.executeScript("document.getElementByXpath(element).value=inputData"); 
			
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		return "PASS";
	}

	
	public String InputTextDirectly(String firstXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: InputTextDirectly ()
		 * 
		 * @parameter: String firstXpathKey & String inputData
		 * 
		 * @notes: Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		highlight = false;
		captureScreenShot = false;
		try {

			if (GTestName.contains("LG")) {
				Actions actions = new Actions(driver);
				actions.moveToElement(returnElementIfPresent(firstXpathKey));
				Thread.sleep(500);
				actions.doubleClick();
				Thread.sleep(500);
				returnElementIfPresent(firstXpathKey).clear();
				actions.sendKeys(inputData);
				actions.build().perform();
				Thread.sleep(100);
				actions.moveByOffset(200, 0).doubleClick();
				actions.build().perform();
				Thread.sleep(500);
			} else {
				returnElementIfPresent(firstXpathKey).click();
				Thread.sleep(2000);
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		return "PASS";
	}

	public String InputNumber(String firstXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: InputNumber ()
		 * 
		 * @parameter: String firstXpathKey & String inputData
		 * 
		 * @notes: Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			String regex = ".*\\d.*";
			if (inputData.matches(regex)) {
				NumberFormat nf = NumberFormat.getInstance();
				Number number = nf.parse(inputData);
				long lnputValue = number.longValue();
				inputData = String.valueOf(lnputValue);

				System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
				APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
				returnElementIfPresent(firstXpathKey).clear();
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			} else {
				System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
				APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
				returnElementIfPresent(firstXpathKey).sendKeys(inputData);
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String InputTextWithAutoAddress(String firstXpathKey, String inputData) throws Exception {
		/* @HELP
		@class:			Keywords
		@method:		Input ()
		@parameter:	String firstXpathKey & String inputData
		@notes:			Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		@returns:		("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method 
		@END
		 */	
		System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(returnElementIfPresent(firstXpathKey));
			actions.click();
			actions.sendKeys(inputData);
			actions.build().perform();
			actions.doubleClick();
			Thread.sleep(2000);
			actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String InputTextDDTWithAutoAddress(String firstXpathKey, String inputData) throws Exception {
		/* @HELP
		@class:			Keywords
		@method:		InputTextDDTWithAutoAddress
		@parameter:		String firstXpathKey & String inputData
		@notes:			Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
		@returns:		("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method 
		@END
		 */	
		
		inputData = (String) DDTText.get(inputData);
		System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		test.pass(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(returnElementIfPresent(firstXpathKey));
			actions.click();
			actions.sendKeys(inputData);
			actions.build().perform();
			actions.doubleClick();
			Thread.sleep(2000);
			if(inputData.equals("HomeImprovement"))
			{
				
				actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
				Thread.sleep(2000);
			}
			else if(inputData.equals("Medical"))
			{
				
				actions.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN, Keys.ENTER).perform();
				Thread.sleep(2000);
			}
			else if(inputData.equals("Debt Consolidation"))
			{
				
				actions.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER).perform();
				Thread.sleep(2000);
			}
			else if(inputData.equals("Other"))
			{
				
				actions.sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN, Keys.ENTER).perform();
				Thread.sleep(2000);
			}
			else
			{
				actions.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			return "FAIL - Not able to enter " + inputData + " in " + firstXpathKey + " Field";
		}
		test.pass("PASS");
		return "PASS";
	}

	public boolean isElementPresentBy(By by) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: isElementPresent ()
		 * 
		 * @parameter: By by
		 * 
		 * @notes: Supported method for finding an element.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			captureScreenShot = true;
			return false;
		}
	}

	public String Login() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Login ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Inputs the default login details as mentioned in the "Config  Details" sheet of the master xlsx and performs click action on the login button.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			getConfigDetails();
			System.out.println(": Entering: " + username + " in USERNAME Field");
			APP_LOGS.debug(": Entering: " + username + " in USERNAME Field");
			returnElementIfPresent(GUSER_XPATH).sendKeys(username);
			System.out.println(": PASS");
			APP_LOGS.debug(": PASS");

			System.out.println(": Entering: " + password + " in PASSWORD Field");
			APP_LOGS.debug(": Entering: " + password + " in PASSWORD Field");
			returnElementIfPresent(GPASS_XPATH).sendKeys(password);
			System.out.println(": PASS");
			APP_LOGS.debug(": PASS");

			System.out.println(": Performing Click action on LOGIN");
			APP_LOGS.debug(": Performing Click action on LOGIN");
			returnElementIfPresent(GLOGIN).click();
		} catch (Exception e) {
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to Loging with " + username + " : Username and " + password + ": Password");
			return ("FAIL - Not able to Loging with " + username + " : Username and " + password + ": Password");
		}
		return "PASS";
	}

	public String LogintoAdmin(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: LogintoAdmin()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Login to the admin application
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		String objectIdentifierValue1 = "";
		String objectArray1[] = null;
		String object = OR.getProperty(firstXpathKey);
		objectArray = object.split("__");
		objectIdentifierValue = objectArray[1].trim();
		String object1 = OR.getProperty(secondXpathKey);
		objectArray1 = object1.split("__");
		objectIdentifierValue1 = objectArray1[1].trim();
		System.out.println(": Logging into admin application ");
		APP_LOGS.debug(": Logging into admin application ");
		test.info(": Logging into admin application ");
		try
		{
				Thread.sleep(1000);
				InputText("ADMIN_UNAME_TEXTFIELD","",objectIdentifierValue);
				Thread.sleep(1000);
				InputText("ADMIN_PWD_TEXTFIELD","",objectIdentifierValue1);
				Thread.sleep(1000);
				Click("ADMIN_SIGIN_BUTTON");
				Thread.sleep(5000);
				
		}
		catch (Exception e) 
		{
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to read"+firstXpathKey);
			return (": FAIL - Not able to read"+firstXpathKey);
		}
		test.pass("Pass");
		return "PASS";
	}

	public String Logout(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Logout()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Logout from the application
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Logout from the application");
		APP_LOGS.debug(": Logout from the application");
		test.info(": Logout from the application");
		try 
		{
			Click(firstXpathKey);
			Thread.sleep(5000);
			Click(secondXpathKey);
			Thread.sleep(5000);
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Logout from the application");
			return "FAIL - Not able to Logout from the application";
		}
	
	test.pass("PASS");
	return "PASS";
	}

	public String LogintoMerchant(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: LogintoMerchant ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Login to the merchant application
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		String objectIdentifierValue1 = "";
		String objectArray1[] = null;
		String object = OR.getProperty(firstXpathKey);
		objectArray = object.split("__");
		objectIdentifierValue = objectArray[1].trim();
		String object1 = OR.getProperty(secondXpathKey);
		objectArray1 = object1.split("__");
		objectIdentifierValue1 = objectArray1[1].trim();
		System.out.println(": Logging into merchant application ");
		APP_LOGS.debug(": Logging into merchant application ");
		test.info(": Logging into merchant application ");
		try
		{
				Thread.sleep(1000);
				InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","",objectIdentifierValue);
				Thread.sleep(1000);
				InputText("PASSWORD_TEXTFIELD","",objectIdentifierValue1);
				Thread.sleep(1000);
				Click("LOGIN_BUTTON");
				Thread.sleep(5000);
				
		}
		catch (Exception e) 
		{
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to read"+firstXpathKey);
			return (": FAIL - Not able to read"+firstXpathKey);
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String MouseHover(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: MouseHover ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Hover mouse over given Object, link, Hyperlink, selections or buttons of a web page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Performing Mouse hover on " + firstXpathKey);
		APP_LOGS.debug(": Performing Mouse hover on " + firstXpathKey);
		test.info(" Performing Mouse hover on " + firstXpathKey);
		highlight = false;
		captureScreenShot = false;
		try {
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement root = returnElementIfPresent(firstXpathKey);
			act.moveToElement(root).build().perform();
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to do mouse hover on -- " + firstXpathKey);
			return "FAIL - Not able to do mouse hover on -- " + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	@SuppressWarnings("deprecation")
	public String OpenBrowser(String browserType) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: OpenBrowser ()
		 * 
		 * @parameter: String browserType
		 * 
		 * @notes: Opens Browsers, Sets Timeout parameter and Maximize the Browser
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getConfigDetails();
		int WaitTime;
		NumberFormat nf = NumberFormat.getInstance();
		Number number = nf.parse(waitTime);
		WaitTime = number.intValue();
		CONFIG_IMPLICIT_WAIT_TIME = WaitTime;
		failedResult = "";
		String URL = "https://srinivasu1819:8b7f7e3f-e35c-4011-9848-04ae983ba67b@ondemand.saucelabs.com:443/wd/hub";
		System.out.println(": Opening: " + bType + " Browser");
		try {

			if (tBedType.equals("DESKTOP")) {
				// ***************** 1. For Desktop Browsers****************/
				if (bType.equals("Chrome")) {
					System.setProperty("webdriver.chrome.driver", chromedriverPath);
					if (GTestName.contains("LG")) {
						DesiredCapabilities caps = new DesiredCapabilities();
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--allow-running-insecure-content");
						LoggingPreferences logPrefs = new LoggingPreferences();
						logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
						caps.setCapability(ChromeOptions.CAPABILITY, options);
						caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
						caps.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
						options.merge(caps);
						driver = new ChromeDriver(options);
					} else {
						driver = new ChromeDriver();
					}
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();

					// ***************** 1. For Desktop Browsers***************/
					/*
					 * if (bType.equals("Chrome")) { System.setProperty("webdriver.chrome.driver", chromedriverPath); if (GTestName.contains("LG")) { Proxy proxy = new Proxy(); proxy.setHttpProxy("localhost:8090"); proxy.setFtpProxy("localhost:8090");
					 * proxy.setSslProxy("localhost:8090"); DesiredCapabilities caps = DesiredCapabilities.chrome(); ChromeOptions options = new ChromeOptions(); options.addArguments("--allow-running-insecure-content"); LoggingPreferences logPrefs = new LoggingPreferences();
					 * logPrefs.enable(LogType.PERFORMANCE, Level.ALL); caps.setCapability(ChromeOptions.CAPABILITY, options); caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs); caps.setCapability(CapabilityType.PROXY, proxy); driver = new ChromeDriver(caps); } else {
					 * driver = new ChromeDriver(); } getBrowserVersion(); APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser"); driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS); driver.manage().window().maximize();
					 */

				} else if (bType.equals("Edge")) {
					System.setProperty("webdriver.edge.driver", edgedriverPath);
					driver = new EdgeDriver();
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} else if (bType.equals("Edge")) {
					System.setProperty("webdriver.edge.driver", edgedriverPath);
					driver = new EdgeDriver();
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();

				} else if (bType.equals("Mozilla")) {
					System.setProperty("webdriver.gecko.driver", geckodriverPath);
					driver = new FirefoxDriver();
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} else if (bType.equals("Safari")) {
					driver = new SafariDriver();
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} else if (bType.equals("IE")) {
					System.setProperty("webdriver.ie.driver", iedriverPath);
					driver = new InternetExplorerDriver();
					getBrowserVersion();
					APP_LOGS.debug(": Opening: " + bTypeVersion + " Browser");
					driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
					driver.manage().window().maximize();
				} else if (bType.equals("Opera")) {
					driver = new OperaDriver();
				} else if (bType.equals("HtmlUnit")) {
					driver = new HtmlUnitDriver(true);
				}
				driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);

			} 
			else if (tBedType.equals("MOBILE_EMULATION")) 
			{
				if (bType.equals("Chrome")) 
				{
					System.setProperty("webdriver.chrome.driver", chromedriverPath);
					if (GTestName.contains("LG")) {
						mobileEmulation = new HashMap<String, String>();						
						mobileEmulation.put("deviceName", deviceName);
//						Map<String, Object> chromeOptions = new HashMap<String, Object>();
//						chromeOptions.put("mobileEmulation", mobileEmulation);
//						DesiredCapabilities capabilities = new DesiredCapabilities();
//						capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
						ChromeOptions chromeOptions = new ChromeOptions();
				        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
						getBrowserVersion();
						System.out.println(": Opening Mobile Emulator : " + bTypeVersion + " in Chrome Browser");
						APP_LOGS.debug(": Opening Mobile Emulator : " + bTypeVersion + " in Chrome Browser");
						System.out.println(": Launching : " + capabilities.getCapability("chromeOptions"));
						APP_LOGS.debug(": Launching : " + capabilities.getCapability("chromeOptions"));
						driver = new ChromeDriver(chromeOptions);
						driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);
						driver.manage().window().maximize();
					}
				} 
				else {
					System.out.println(": The Browser Type: " + bType + " is not valid. Please Enter a valid Browser Type");
					APP_LOGS.debug(": The Browser Type: " + bType + " is not valid. Please Enter a valid Browser Type");
					return "FAIL - The Browser Type: " + bType + " is not valid. Please Enter a valid Browser Type";
				}
			} 
						
			else {
				System.out.println(": The Browser Type: " + bType + " is not valid. Please Enter a valid Browser Type");
				return "FAIL - The Browser Type: " + bType + " is not valid. Please Enter a valid Browser Type";
			}

		} catch (Exception e) {
			return "FAIL - Not able to Open Browser";
		}
		return "PASS";
	}

	public String OpenDBConnection() throws InterruptedException {	
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: OpenDBConnection ()
		 * 
		 * @parameter: 
		 * 
		 * @notes: Connect to the database mentioned in Config details.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight=false;
		captureScreenShot=false;
		System.out.println(": Connecting to the "+databaseType+" database");
		APP_LOGS.debug(": Connecting to the "+databaseType+" database");
		test.info(": Connecting to the "+databaseType+" database");
		try {
			if(databaseType.equals("My SQL")){
				Class.forName("com.mysql.jdbc.Driver");
				}else if(databaseType.equals("Oracle")){
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if(databaseType.equals("MS SQL Server")){
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}
		} catch (ClassNotFoundException e) {
			System.out.println(": "+e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(dbConnection,dbUsername,dbPassword);
			System.out.println(": Database Connected");
		} catch (SQLException e) {
			
			System.out.println(": "+e.getMessage());
			return "FAIL - Not able to connect to the "+databaseType;
		}
		return "PASS";
	}

	public String PageRefresh() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: PageRefresh
		 * 
		 * @parameter: String URLKey
		 * 
		 * @notes: Refresh the Webpage as per given URLKey
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
//		System.out.println(": Refreshing to (" + SUTUrl + ") Site ");
//		APP_LOGS.debug(": Refreshing to (" + SUTUrl + ") Site ");
//		test.info(" Refreshing to (" + SUTUrl + ") Site ");
		try {

			driver.navigate().refresh();
			Thread.sleep(5000);
		}

		catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to Refresh " + SUTUrl + " Site" + e.getMessage());
			return "FAIL - Not able to Refresh " + SUTUrl + " Site" + e.getMessage();

		}
		test.pass("Pass");
		return "PASS";
	}

	public String QuitBrowser() throws ATUTestRecorderException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: QuitBrowser ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Quits all opened Browsers or Brower instances after the test case Execution.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		getTextOrValues.clear();
		scriptTableFirstRowData = "";
		System.out.println(": Quiting all opened Browsers");
		APP_LOGS.debug(": Quiting all opened Browsers");
		try {
			driver.close();
			driver = null;
			if (captureVideoRecording.equals("Yes")) {
				recorder.stop();
				System.out.println(": Video Recording Stopped ");
				APP_LOGS.debug(": Video Recording Stopped ");
				Thread.sleep(SYNC_WAIT);
			}

		} catch (Exception e) {
			return "FAIL - Not able to Quit all opened Browsers";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String RegisterBusinessOwner(String firstXpathKey, String secondXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: RegisterBusinessOwner ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: It register the business owner
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Registering Business Owner");
		APP_LOGS.debug(": Registering Business Owner");
		test.info(": Registering Business Owner");
		try 
		{
			if(inputData.equals("DirectUpgrade"))
			{
				InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
				InputText("PASSWORD_TEXTFIELD","","L0angl!de");
				Click(firstXpathKey);
				Wait("SMALL_WAIT");
				WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
				InputText("FIRSTNAME_TEXTFIELD","","FirstName");
				InputText("LASTNAME_TEXTFIELD","","LastName");
				InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
				InputText("BUSINESSNAMEE_TEXTFIELD","","HI");
				GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
				SelectValueFromBootStrapDropDown("BUSINESSTYPE_DROPDOWN", "LIST_OPTIONS", "Home Improvement");
				ScrollElementIntoView(secondXpathKey);
				Click(secondXpathKey);
				WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
				InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
				InputText("CITY_TEXTFIELD","","Los Angeles");
				InputText("STATE_TEXTFIELD","","CA");
				InputText("ZIPCODE_TEXTFIELD","","90012");
		//		Click("LENDERTYPECODEYES_RADIOBUTTON");
		//		ScrollElementIntoView(secondXpathKey);
		//		Click(secondXpathKey);
				Click("REG_SUBMIT_BUTTON");
				WaitTillElementAppears("REG_CONGRATULATIONS_TEXT");
		//		VerifyTextContains("SIGNUPOFFERESSFEATURES_TEXT","","You�re signed up to offer Essential Financing to your customers");
				VerifyText("ESSENTIALFINANCING_TEXT","","Essential Financing");
				VerifyText("GETMONEYFROMLENDER_TEXT","","Your customers get money directly from the lender");
				VerifyText("DEALERFEE_TEXT","","You pay no merchant/dealer fee");
				VerifyText("GETNOTIFIEDANDFUNDED_TEXT","","You get notified when their loan is approved and funded");
				VerifyText("PROMFINANCINGGROEBUSINESS_TEXT","","Offer 0% Promotional Financing to grow your business even more");
		//		VerifyText("RATESFROMZEROAPR_TEXT","","Rates from 0% APR​");
				VerifyText("UGETMONEYFROMLENDER_TEXT","","You get money directly from the lender");
				Click("UPGRADEPLAN_BUTTON");
				WaitTillElementAppears("QUALIFIEDBUSINESSCAN_TEXT");
				VerifyText("QUALIFIEDBUSINESSCAN_TEXT","","With Promotional Financing, qualified businesses can:");
				Click("CONTINUE_BUTTON");
				WaitTillElementAppears("MOREABOUTURBUSINESS_TEXT");
				InputText("UNDEROWNER_TEXTFIELD","","3");
				Click("OWNERSHIPTYPE_DROPDOWN");
				Click("PROPRIETORSHIP_OWNERTYPE");
				SelectValueFromBootStrapDropDown("STATEOFINCORP_DROPDOWN","","California");
				InputText("FEDERALTAXID_TEXTFIELD","","Random");
			//	InputText("REFERALCODE_TEXTFIELD","","FRD");
				ScrollElementIntoView(secondXpathKey);
				Click(secondXpathKey);
				WaitTillElementAppears("FINALLYURBANKINFO_TEXT");
				InputText("ROUTINGNUMBER_TEXTFIELD","","022304030");
				InputText("CONFROUTNUMBER_TXTFIELD","","022304030");
				InputText("ACCOUNTNUMBER_TEXTFIELD","","5451522545");
				InputText("CONFACCNUMBER_TXTFIELD","","5451522545");
				InputText("BANKNAME_TEXTFIELD","","Corporation Bank");
				Click("BANKACCOUNTTYPE_DROPDOWN");
				Click("ACCOUNTTYPESAVINGS_LIST");
				ScrollElementIntoView(secondXpathKey);
				Click(secondXpathKey);
				WaitTillElementAppears("OKALMOSTDONE_TEXT");
				VerifyText("ELECTRONICSIGNATURE_TEXT","","Electronic signature");
				InputText("FIRSTNAME_TEXTFIELD","FIRSTNAME_TEXTFIELD","");
				InputText("LASTNAME_TEXTFIELD","LASTNAME_TEXTFIELD","");
				HighlightElement("REGISTER_DATE");
				VerifyLoanApplyDate("REGISTER_DATE","Registration");
				VerifyTextContains("REGISTER_DATE","","Signed date");
				Click("AGREE_BUTTON");
				WaitTillElementAppears("REG_CONGRATULATIONS_TEXT");
				VerifyTextContains("UPGRDAE_OURBUSENROL_TEXT","","Our Business Enrollment Team will be in touch shortly to activate Promotional Financing.");
				VerifyText("ACTIVATIONINPROGRESS_TEXT","","Activation in Progress");
				System.out.println(": Registered Business Owner");
				APP_LOGS.debug(": Registered Business Owner");
				test.info(": Registered Business Owner");
				Thread.sleep(5000);
			}
			else if(inputData.equals("PendingDirect"))
			{
				InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
				InputText("PASSWORD_TEXTFIELD","","L0angl!de");
				Click(firstXpathKey);
				Wait("SMALL_WAIT");
				WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
				InputText("FIRSTNAME_TEXTFIELD","","FirstName");
				InputText("LASTNAME_TEXTFIELD","","LastName");
				InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
				InputText("BUSINESSNAMEE_TEXTFIELD","","Medical");
				GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
				SelectValueFromBootStrapDropDown("BUSINESSTYPE_DROPDOWN", "LIST_OPTIONS", "Home Improvement");
				ScrollElementIntoView(secondXpathKey);
				Click(secondXpathKey);
				WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
				InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
				InputText("CITY_TEXTFIELD","","Los Angeles");
				InputText("STATE_TEXTFIELD","","CA");
				InputText("ZIPCODE_TEXTFIELD","","90012");
			//	Click("LENDERTYPECODENO_RADIOBUTTON");
				Click("REG_SUBMIT_BUTTON");
				WaitTillElementAppears("REG_CONGRATULATIONS_TEXT");
				VerifyText("SIGNUPOFFERESSFEATURES_TEXT","","You’re signed up to offer Essential Financing to your customers​");
				VerifyText("ESSENTIALFINANCING_TEXT","","Essential Financing");
				VerifyText("GETMONEYFROMLENDER_TEXT","","Your customers get money directly from the lender");
				VerifyText("DEALERFEE_TEXT","","You pay no merchant/dealer fee​");
				VerifyText("GETNOTIFIEDANDFUNDED_TEXT","","You get notified when their loan is approved and funded");
			//	WaitTillElementAppears("URREGISTERED_LABEL");
			//	VerifyText("URREGISTERED_LABEL","","Your account is activated, you can now start offering finance to your customer");
				System.out.println(": Registered Business Owner");
				APP_LOGS.debug(": Registered Business Owner");
				test.info(": Registered Business Owner");
				Thread.sleep(5000);
			}
			else if(inputData.equals("Direct"))
			{
				InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
				InputText("PASSWORD_TEXTFIELD","","L0angl!de");
				Click(firstXpathKey);
				Wait("SMALL_WAIT");
				WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
				InputText("FIRSTNAME_TEXTFIELD","","FirstName");
				InputText("LASTNAME_TEXTFIELD","","LastName");
				InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
				InputText("BUSINESSNAMEE_TEXTFIELD","","Medical");
				GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
				SelectValueFromBootStrapDropDown("BUSINESSTYPE_DROPDOWN", "LIST_OPTIONS", "Medical");
				ScrollElementIntoView(secondXpathKey);
				Click(secondXpathKey);
				WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
				InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
				InputText("CITY_TEXTFIELD","","Los Angeles");
				InputText("STATE_TEXTFIELD","","CA");
				InputText("ZIPCODE_TEXTFIELD","","90012");
			//	Click("LENDERTYPECODENO_RADIOBUTTON");
				Click("REG_SUBMIT_BUTTON");
				WaitTillElementAppears("REG_CONGRATULATIONS_TEXT");
		//		VerifyTextContains("SIGNUPOFFERESSFEATURES_TEXT","","You�re signed up to offer Essential Financing to your customers");
				VerifyText("ESSENTIALFINANCING_TEXT","","Essential Financing");
				VerifyText("GETMONEYFROMLENDER_TEXT","","Your customers get money directly from the lender");
				VerifyText("DEALERFEE_TEXT","","You pay no merchant/dealer fee");
				VerifyText("GETNOTIFIEDANDFUNDED_TEXT","","You get notified when their loan is approved and funded");
			//	WaitTillElementAppears("URREGISTERED_LABEL");
			//	VerifyText("URREGISTERED_LABEL","","Your account is activated, you can now start offering finance to your customer");
				System.out.println(": Registered Business Owner");
				APP_LOGS.debug(": Registered Business Owner");
				test.info(": Registered Business Owner");
				Thread.sleep(5000);
			}
			else
			{
				
			}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to register the service");
			return "FAIL - Not able to register the service";
		}
		test.pass("PASS");
		return "PASS";
	}

	
	public String RegisterBusinessService(String firstXpathKey, String secondXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: RegisterBusinessservice ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: It register the business service
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Registering Business Service");
		APP_LOGS.debug(": Registering Business Service");
		test.info(": Registering Business Service");
		try 
		{
			InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
			InputText("PASSWORD_TEXTFIELD","","L0angl!de");
			Click(firstXpathKey);
			Wait("SMALL_WAIT");
			WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
			InputText("FIRSTNAME_TEXTFIELD","","FirstName");
			InputText("LASTNAME_TEXTFIELD","","LastName");
			InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
			InputText("BUSINESSNAMEE_TEXTFIELD","","HI");
			GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
			Click("BUSINESSTYPE_DROPDOWN");
			Click("DENTALBUSINESS_TYPE");
			InputText("WEBSITEURL_TEXTFIELD","","www.loanglide.com");
			Click("AVGANNUALINCOME_DROPDOWN");
			Click("AVGINCOMEAMOUNT_LIST");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
			InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
			InputText("CITY_TEXTFIELD","","Los Angeles");
			InputText("STATE_TEXTFIELD","","CA");
			InputText("ZIPCODE_TEXTFIELD","","90012");
			Click("LENDERTYPECODEYES_RADIOBUTTON");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("MOREABOUTURBUSINESS_TEXT");
			InputText("UNDEROWNER_TEXTFIELD","","3");
			Click("OWNERSHIPTYPE_DROPDOWN");
			Click("PROPRIETORSHIP_OWNERTYPE");
			SelectValueFromBootStrapDropDown("STATEOFINCORP_DROPDOWN","","California");
			InputText("FEDERALTAXID_TEXTFIELD","","Random");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("FINALLYURBANKINFO_TEXT");
			InputText("ROUTINGNUMBER_TEXTFIELD","","022304030");
			InputText("CONFROUTNUMBER_TXTFIELD","","022304030");
			InputText("ACCOUNTNUMBER_TEXTFIELD","","5451522545");
			InputText("CONFACCNUMBER_TXTFIELD","","5451522545");
			InputText("BANKNAME_TEXTFIELD","","Corporation Bank");
			GetAttributeValue("BANKNAME_TEXTFIELD");
			GetAttributeValue("ACCOUNTNUMBER_TEXTFIELD");
			GetAttributeValue("ROUTINGNUMBER_TEXTFIELD");
			Click("BANKACCOUNTTYPE_DROPDOWN");
			Click("ACCOUNTTYPESAVINGS_LIST");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("OKALMOSTDONE_TEXT");
			VerifyText("ELECTRONICSIGNATURE_TEXT","","Electronic signature");
			InputText("FIRSTNAME_TEXTFIELD","FIRSTNAME_TEXTFIELD","");
			InputText("LASTNAME_TEXTFIELD","LASTNAME_TEXTFIELD","");
			HighlightElement("REGISTER_DATE");
			VerifyLoanApplyDate("REGISTER_DATE","Registration");
			VerifyTextContains("REGISTER_DATE","","Signed date");
			Click("AGREE_BUTTON");
			WaitTillElementAppears("URREGISTERED_LABEL");
			VerifyText("URREGISTERED_LABEL","","Your account is activated, you can now start offering finance to your customer");
			System.out.println(": Registered Business Service");
			APP_LOGS.debug(": Registered Business Service");
			test.info(": Registered Business Service");
			Thread.sleep(5000);
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to register the service");
			return "FAIL - Not able to register the service";
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String RegisterIndirectBO(String firstXpathKey, String secondXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: RegisterIndirectBO ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: It register the indirect business owner
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Registering Indirect Business Owner");
		APP_LOGS.debug(": Registering Indirect Business Owner");
		test.info(": Registering Indirect Business Owner");
		try 
		{
			InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
			InputText("PASSWORD_TEXTFIELD","","L0angl!de");
			Click(firstXpathKey);
			Wait("SMALL_WAIT");
			WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
			InputText("FIRSTNAME_TEXTFIELD","","FirstName");
			InputText("LASTNAME_TEXTFIELD","","LastName");
			InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
			InputText("BUSINESSNAMEE_TEXTFIELD","","Dental");
	//		InputText("BUSINESSNAMEE_TEXTFIELD","","Fert");
			GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
			SelectValueFromBootStrapDropDown("BUSINESSTYPE_DROPDOWN", "", "Dental");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
			InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
			InputText("CITY_TEXTFIELD","","Los Angeles");
			InputText("STATE_TEXTFIELD","","CA");
			InputText("ZIPCODE_TEXTFIELD","","90012");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("MOREABOUTURBUSINESS_TEXT");
			InputText("UNDEROWNER_TEXTFIELD","","3");
			Click("OWNERSHIPTYPE_DROPDOWN");
			Click("PROPRIETORSHIP_OWNERTYPE");
			SelectValueFromBootStrapDropDown("STATEOFINCORP_DROPDOWN","","California");
			InputText("FEDERALTAXID_TEXTFIELD","","Random");
	//		InputText("REFERALCODE_TEXTFIELD","","FRD");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("FINALLYURBANKINFO_TEXT");
			InputText("ROUTINGNUMBER_TEXTFIELD","","022304030");
			InputText("CONFROUTNUMBER_TXTFIELD","","022304030");
			InputText("ACCOUNTNUMBER_TEXTFIELD","","5451522545");
			InputText("CONFACCNUMBER_TXTFIELD","","5451522545");
			InputText("BANKNAME_TEXTFIELD","","Corporation Bank");
			Click("BANKACCOUNTTYPE_DROPDOWN");
			Click("ACCOUNTTYPESAVINGS_LIST");
			GetAttributeValue("ROUTINGNUMBER_TEXTFIELD");
			GetAttributeValue("ACCOUNTNUMBER_TEXTFIELD");
			GetAttributeValue("BANKNAME_TEXTFIELD");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("OKALMOSTDONE_TEXT");
			VerifyText("ELECTRONICSIGNATURE_TEXT","","Electronic signature");
			InputText("FIRSTNAME_TEXTFIELD","FIRSTNAME_TEXTFIELD","");
			InputText("LASTNAME_TEXTFIELD","LASTNAME_TEXTFIELD","");
			HighlightElement("REGISTER_DATE");
			VerifyLoanApplyDate("REGISTER_DATE","Registration");
			VerifyTextContains("REGISTER_DATE","","Signed date");
			Click("AGREE_BUTTON");
			WaitTillElementAppears("OURBUSINESSENROLLMENT_TEXT");
			VerifyText("OURBUSINESSENROLLMENT_TEXT","","Our Business Enrollment Team will be in touch shortly to activate your account.");
			System.out.println(": Registered InDirect Business Owner");
			APP_LOGS.debug(": Registered InDirect Business Owner");
			test.info(": Registered InDirect Business Owner");
			Thread.sleep(5000);
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to register the service");
			return "FAIL - Not able to register the service";
		}
		test.pass("PASS");
		return "PASS";
	}

	
	public String RegisterIndirectBODDT(String firstXpathKey, String secondXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: RegisterIndirectBO ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: It register the indirect business owner
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Registering Indirect Business Owner");
		APP_LOGS.debug(": Registering Indirect Business Owner");
		test.info(": Registering Indirect Business Owner");
		try 
		{
			inputData = (String) DDTText.get(inputData);
			System.out.println(inputData);
			InputText("MERCHANT_EMAILADDRESS_TEXTFIELD","","Auto@mail.com");
			InputText("PASSWORD_TEXTFIELD","","L0angl!de");
			Click(firstXpathKey);
			Wait("SMALL_WAIT");
			WaitTillElementAppears("TELLUSABOUTURSELFANDBUSINESS_TEXT");
			InputText("FIRSTNAME_TEXTFIELD","","FirstName");
			InputText("LASTNAME_TEXTFIELD","","LastName");
			InputText("CONTACTPHNUMBER_TEXTFIELD","","7015168317");
			InputDDTdata("BUSINESSNAMEE_TEXTFIELD","","${BusinessName}");
	//		InputText("BUSINESSNAMEE_TEXTFIELD","","Fert");
			GetBusinessName("BUSINESSNAMEE_TEXTFIELD");
			SelectValueFromDDTBootStrapDropDown("BUSINESSTYPE_DROPDOWN", "", "${BusinessType}");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("URBUSINESSADDRESS_TEXT");
			InputText("BUSINESSADDRESSLINE1_TEXTFIELD","","210 West Temple Street");
			InputText("CITY_TEXTFIELD","","Los Angeles");
			InputText("STATE_TEXTFIELD","","CA");
			InputText("ZIPCODE_TEXTFIELD","","90012");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("MOREABOUTURBUSINESS_TEXT");
			InputText("UNDEROWNER_TEXTFIELD","","3");
			Click("OWNERSHIPTYPE_DROPDOWN");
			Click("PROPRIETORSHIP_OWNERTYPE");
			SelectValueFromDDTBootStrapDropDown("STATEOFINCORP_DROPDOWN","","${StateofIncorporation}");
			InputText("FEDERALTAXID_TEXTFIELD","","Random");
	//		InputText("REFERALCODE_TEXTFIELD","","FRD");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("FINALLYURBANKINFO_TEXT");
			InputText("ROUTINGNUMBER_TEXTFIELD","","022304030");
			InputText("CONFROUTNUMBER_TXTFIELD","","022304030");
			InputText("ACCOUNTNUMBER_TEXTFIELD","","5451522545");
			InputText("CONFACCNUMBER_TXTFIELD","","5451522545");
			InputText("BANKNAME_TEXTFIELD","","Corporation Bank");
			Click("BANKACCOUNTTYPE_DROPDOWN");
			Click("ACCOUNTTYPESAVINGS_LIST");
			ScrollElementIntoView(secondXpathKey);
			Click(secondXpathKey);
			WaitTillElementAppears("OKALMOSTDONE_TEXT");
			VerifyText("ELECTRONICSIGNATURE_TEXT","","Electronic signature");
			InputText("FIRSTNAME_TEXTFIELD","FIRSTNAME_TEXTFIELD","");
			InputText("LASTNAME_TEXTFIELD","LASTNAME_TEXTFIELD","");
			HighlightElement("REGISTER_DATE");
			VerifyLoanApplyDate("REGISTER_DATE","Registration");
			VerifyTextContains("REGISTER_DATE","","Signed date");
			Click("AGREE_BUTTON");
			WaitTillElementAppears("OURBUSINESSENROLLMENT_TEXT");
			VerifyText("OURBUSINESSENROLLMENT_TEXT","","Our Business Enrollment Team will be in touch shortly to activate your account.");
			System.out.println(": Registered InDirect Business Owner");
			APP_LOGS.debug(": Registered InDirect Business Owner");
			test.info(": Registered InDirect Business Owner");
			Thread.sleep(5000);
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to register the service");
			return "FAIL - Not able to register the service";
		}
		test.pass("PASS");
		return "PASS";
	}

	private StringBuffer readFile(String filePath) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: readFile ()
		 * 
		 * @parameter: String filepath
		 * 
		 * @notes: Read the file from the given file path
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		BufferedReader br = null;
		StringBuffer stringBuffer = new StringBuffer();

		try {

			String lineString = null;
			br = new BufferedReader(new java.io.FileReader(filePath));

			while ((lineString = br.readLine()) != null) {
				stringBuffer.append(lineString);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return stringBuffer;
	}

	public String ScrollElementIntoView(String firstXpathKey) 
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ScrollElementIntoView ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Scroll page until element is visible on the page where
		 * element is passed in firstXpathKey.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got
		 * executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Scrolling the page until element visible on the page ");
		APP_LOGS.debug(": Scrolling the page until element visible on the page ");
		test.info(" Scrolling the page until element visible on the page ");
		try {
			WebElement element = returnElementIfPresent(firstXpathKey);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "");
			Thread.sleep(500);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not Able to Scroll The Page: " + e.getMessage());
			return "FAIL - Not Able to Scrol The Page to END Using END key";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String SelectDateFromCalendar(String firstXpathKey, String inputData) throws Exception 
	{
        /* @HELP
        @class:                    Keywords
        @method:            Input ()
        @parameter:  String firstXpathKey & String inputData
        @notes:                    Inputs the value in any edit box. Value is defined in the master xlsx file and is assigned to "inputData" local variable. We cannot perform a data driven testing using the input keyword.
        @returns:           ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method 
        @END
        */
        System.out.println(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
        APP_LOGS.debug(": Entering: " + '"' + inputData + '"' + " text in " + firstXpathKey + " Field");
        highlight=false;
        captureScreenShot=false;
        try
        {
               Thread.sleep(1000);       
               WebElement table = returnElementIfPresent(firstXpathKey);
 //            System.out.println(table);
               List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
               for (WebElement row : tableRows)
               {
            	   List<WebElement> cells = row.findElements(By.xpath("td"));
            	   for (WebElement cell : cells)
            	   {
            		   if (cell.getText().equals(inputData))
            		   {
            			   System.out.println(cell.getText());
            			   System.out.println(inputData);
            			   driver.findElement(By.linkText(inputData)).click();
            		   }
            	   }
               }                     
        }
        catch (Exception e)
        {
               captureScreenShot=true;
               return "FAIL - Not able to enter date " + inputData + " in " + firstXpathKey + " Calendar Field"+e.getMessage();
        }
        test.pass("PASS");
        return "PASS";
	}
	
	public String SelectRadioButton(String firstXpathKey) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectRadioButton ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Select Radio Button
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Selecting Radio Button " + firstXpathKey);
		APP_LOGS.debug(": Selecting Radio Button " + firstXpathKey);
		test.info(" Selecting Radio Button " + firstXpathKey + " Button");

		highlight = false;
		captureScreenShot = false;
		try {
			Actions action = new Actions(driver);
			action.moveToElement(returnElementIfPresent(firstXpathKey)).click().build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to click on -- " + firstXpathKey + " Radio button" + e.getLocalizedMessage());
			return "FAIL - Not able to select " + firstXpathKey + " Radio button";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String SelectTomorrowDate(String firstXpathkey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectTomorrowDate ()
		 * 
		 * @parameter: String firstXpathKey,String secondXpathkey, String inputData
		 * 
		 * @notes: Select the Date as per given parameter
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		Calendar cal = null;
		String date = null;
		try {
			String object = OR.getProperty(firstXpathkey);
			objectArray = object.split("__");
			objectIdentifierValue = objectArray[1].trim();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			date = sdf.format(cal.getTime());
				
		//  returnElementIfPresent(firstXpathkey).click();
			objectIdentifierValue = objectIdentifierValue + "'" + date + "')]";
			driver.findElement(By.xpath(objectIdentifierValue)).click();
		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to selecte Tomorrow Date");
			return "FAIL - Not able to selecte Tomorrow Date";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String SelectUnselectCheckbox(String firstXpathKey, String checkBoxVal) throws InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectUnselectCheckbox ()
		 * 
		 * @parameter: String firstXpathKey, String checkBoxVal
		 * 
		 * @notes: Select or Unselect the checkbox of a webpage as per the value of local variable "chechBoxVal" mentioned in the "Test Steps" sheet in module excel.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Performing Select Unselect action on " + firstXpathKey);
		APP_LOGS.debug(": Performing Select Unselect action on " + firstXpathKey);
		test.info(": Performing Select Unselect action on " + firstXpathKey);
		highlight = false;
		captureScreenShot = false;
		Thread.sleep(5000);
		try {
			if (checkBoxVal.equals("TRUE")) {
				if (returnElementIfPresent(firstXpathKey).isSelected()) {
				} else {
					returnElementIfPresent(firstXpathKey).click();
				}
			} else {
				if (returnElementIfPresent(firstXpathKey).isSelected()) {
					returnElementIfPresent(firstXpathKey).click();
				}
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not able to Select Unselect Checkbox-- " + firstXpathKey);
			return "FAIL - Not able to Select Unselect Checkbox-- " + firstXpathKey + " Exception " + e.getMessage();
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String SelectValueFromDDTBootStrapDropDown(String firstXpathkey, String secondXpathkey, String inputData) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectValueFromDDTBootStrapDropDown ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Selects the "inputData" as mentioned in the module xlsx from the DropDown in a webpage.firstXpathKey would be location of the Dropdown on webpage and dataColVal would be visible text of the dropdown.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		inputData = (String) DDTText.get(inputData);
		System.out.println(": Selecting " + inputData + " from the BootStrap Dropdown");
		APP_LOGS.debug(": Selecting " + inputData + " from the BootStrap Dropdown");
		test.info(": Selecting " + inputData + " from the BootStrap Dropdown");
		highlight = false;
		captureScreenShot = false;
		try {
			
			if (inputData.isEmpty()) 
			{
				System.out.println(": Test Data is Empty, taking this value from Hashmap");
				APP_LOGS.debug(": Test Data is Empty, taking this value from Hashmap");
				test.info(" Test Data is Empty, taking this value from Hashmap");
				inputData = (String) getTextOrValues.get(secondXpathkey);
				System.out.println(": expText " + inputData);
				APP_LOGS.debug(": expText " + inputData);
				test.info(": expText " + inputData);
				if (inputData == null) 
				{
					System.out.println(": No Test Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					APP_LOGS.debug(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					test.info(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					inputData = returnElementIfPresent(secondXpathkey).getText().trim();
				}
			}
			
			
			returnElementIfPresent(firstXpathkey).click();
			Thread.sleep(2000);
			List<WebElement> options = returnElementsIfPresent("LIST_OPTIONS");
			for (WebElement opt : options) {
				
	            if (opt.getText().equals(inputData)) 
	            {
	                opt.click();
	                break;
	            }
	        }
			Thread.sleep(1000);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not able to select " + inputData + " from the Dropdown");
			return "FAIL - Not able to select " + inputData + " from the Dropdown";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String SelectValueFromBootStrapDropDown(String firstXpathkey, String secondXpathkey, String inputData) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectValueFromBootStrapDropDown ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Selects the "inputData" as mentioned in the module xlsx from the DropDown in a webpage.firstXpathKey would be location of the Dropdown on webpage and dataColVal would be visible text of the dropdown.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Selecting " + inputData + " from the BootStrap Dropdown");
		APP_LOGS.debug(": Selecting " + inputData + " from the BootStrap Dropdown");
		test.info(": Selecting " + inputData + " from the BootStrap Dropdown");
		highlight = false;
		captureScreenShot = false;
		try {
			
			if (inputData.isEmpty()) 
			{
				System.out.println(": Test Data is Empty, taking this value from Hashmap");
				APP_LOGS.debug(": Test Data is Empty, taking this value from Hashmap");
				test.info(" Test Data is Empty, taking this value from Hashmap");
				inputData = (String) getTextOrValues.get(secondXpathkey);
				System.out.println(": expText " + inputData);
				APP_LOGS.debug(": expText " + inputData);
				test.info(": expText " + inputData);
				if (inputData == null) 
				{
					System.out.println(": No Test Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					APP_LOGS.debug(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					test.info(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
					inputData = returnElementIfPresent(secondXpathkey).getText().trim();
				}
			}
			/*
			 * if(inputData.equals("Dental")) {
			 * returnElementIfPresent(firstXpathkey).click();
			 * Click("LOANPURPOSE_INDIRECT_OPTION"); Click("INDIRECT_DENTAL_LIST"); } else {
			 * returnElementIfPresent(firstXpathkey).click(); }
			 */
			
			returnElementIfPresent(firstXpathkey).click();
			Thread.sleep(2000);
			List<WebElement> options = returnElementsIfPresent("LIST_OPTIONS");
			for (WebElement opt : options) {
				
	            if (opt.getText().equals(inputData)) 
	            {
	                opt.click();
	                break;
	            }
	        }
			Thread.sleep(1000);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not able to select " + inputData + " from the Dropdown");
			return "FAIL - Not able to select " + inputData + " from the Dropdown";
		}
		test.pass("Pass");
		return "PASS";
	}


	public String SelectValueFromDropDown(String firstXpathKey, String inputData) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectValueFromDropDown ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Selects the "inputData" as mentioned in the module xlsx from the DropDown in a webpage.firstXpathKey would be location of the Dropdown on webpage and dataColVal would be visible text of the dropdown.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Selecting : " + inputData + " from the Dropdown");
		APP_LOGS.debug(": Selecting : " + inputData + " from the Dropdown");
		test.info(" Selecting : " + inputData + " from the Dropdown");
		highlight = false;
		captureScreenShot = false;
		try {
			Select sel = new Select(returnElementIfPresent(firstXpathKey));
			sel.selectByVisibleText(inputData);
			Thread.sleep(2000);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not able to select " + inputData + " from the Dropdown");
			return "FAIL - Not able to select " + inputData + " from the Dropdown";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String SelectMultiValueFromDropDown(String firstXpathKey, String inputData) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SelectValueFromDropDown ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Selects the "inputData" as mentioned in the module xlsx from the DropDown in a webpage.firstXpathKey would be location of the Dropdown on webpage and dataColVal would be visible text of the dropdown.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Selecting : " + inputData + " from the Dropdown");
		APP_LOGS.debug(": Selecting : " + inputData + " from the Dropdown");
		test.info(" Selecting : " + inputData + " from the Dropdown");
		highlight = false;
		captureScreenShot = false;
		try {
		//	Select sel = new Select(returnElementIfPresent(firstXpathKey));
			
			List<WebElement> options = returnElementsIfPresent(firstXpathKey);
			int count = options.size();
			
			System.out.println("Values are ="+count);
			
			Thread.sleep(2000);
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR :  Not able to select " + inputData + " from the Dropdown");
			return "FAIL - Not able to select " + inputData + " from the Dropdown";
		}
		test.pass("Pass");
		return "PASS";
	}

	

	public String SendingDatatoInputAndVerifyColumnData(String firstXpathKey, String secondXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SendingDatatoInputAndVerifyColumnData ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: Enter input data in text field and verify the column data
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		try {
			InputText(firstXpathKey, secondXpathKey, inputData);
			Thread.sleep(2000);
			List<WebElement> status = returnElementsIfPresent(secondXpathKey);
			int count = status.size();
			if (count == 0) {
				captureScreenShot = true;
				System.out.println(": After Selecting " + inputData + " filter option, No search result is displayed");
				APP_LOGS.debug(": After Selecting " + inputData + " filter option, No search result is displayed");
				test.info(": After Selecting " + inputData + " filter option, No search result is displayed");
				return "FAIL - After Selecting " + inputData + " filter option, No search result is displayed";
			} else {
				System.out.println(":  After Selecting " + inputData + " filter option, " + count + " results are displayed");
				APP_LOGS.debug(":  After Selecting " + inputData + " filter option, " + count + " results are displayed");
				test.info("After Selecting " + inputData + " filter option, " + count + " results are displayed");
				for (int i = 0; i < count; i++) {
					String data = status.get(i).getText();
					if (data.contains(inputData)) {
						bflag = true;
					} else {
						captureScreenShot = true;
						System.out.println(": Fail because of Other than " + inputData + " search result is displayed ");
						APP_LOGS.debug(": Fail because of Other than " + inputData + " search result is displayed ");
						test.info(": Fail because of Other than " + inputData + " search result is displayed ");
						return "Fail because of Other than " + inputData + " search result is displayed ";
					}
				}
			}

		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println("Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to select " + inputData + " from the Dropdown");
			return "FAIL - Not able to select " + inputData + " from the Dropdown";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String SwitchToFrame(String FirstXpathKey, String SecondXpathKey) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: SwitchToFrame
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: It will switch to frame
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		
		System.out.println(": Switching to the frame");
		APP_LOGS.debug(": Switching to the frame");
		test.info(": Switching to the frame");
		
		try {
				driver.switchTo().frame("micro-app-checkout");
				System.out.println("Switched to the frame");
		//		returnElementIfPresent(FirstXpathKey).sendKeys("08161990");
		//		Thread.sleep(3000);
		//		returnElementIfPresent(SecondXpathKey).sendKeys("500000");
		//		Thread.sleep(3000);
		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println("Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to switch frame ");
			return "FAIL - Not able to switch frame ";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String StartTimer()
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: StartTimer ()
		 * 
		 * @parameter:
		 * 
		 * @notes: It will start the timer.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Start the timer");
		APP_LOGS.debug(": Start the timer");
		test.info(": Start the timer");
		
		try
		{
			startTimer = System.currentTimeMillis()/1000;
		} 
		catch (Exception e) 
		{
			test.log(Status.ERROR, "ERROR : Unable to start the timer");
			return "FAIL - Unable to start the timer";
		}
		test.pass("PASS");
		return "PASS";
		
	}

	public String SwitchToNewWindow()
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: TestCaseEnds ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Performs necessary actions before concluding the testcase like if testcase has anything fail it will declare by Assert.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		System.out.println(": Switch to new window");
		APP_LOGS.debug(": Switch to new window");
		test.info(": Switch to new window");
		try
		{
			Thread.sleep(3000);
			winHandleBefore = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			    Thread.sleep(2000);
			}
		} 
		catch (Exception e) 
		{
			test.log(Status.ERROR, "ERROR : Unable to switch new window");
			return "FAIL - Unable to switch new window";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String TestCaseEnds() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: TestCaseEnds ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Performs necessary actions before concluding the testcase like if testcase has anything fail it will declare by Assert.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": TestCase is Ending");
		APP_LOGS.debug(": TestCase is Ending");
		test.info(": TestCase is Ending");
		getTextOrValues.clear();
		scriptTableFirstRowData = "";
		try {
			if (Fail == true) {
				System.out.println(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
				APP_LOGS.debug(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
				test.info(" TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
				highlight = false;
				Fail = false;
				failedResult = "";
				String failedResult1 = failedResult;
				if (captureVideoRecording.equals("Yes")) {
					recorder.stop();
					System.out.println(": Video Recording Stopped As test case completed");
					APP_LOGS.debug(": Video Recording Stopped As test case completed");
					test.info(" Video Recording Stopped As test case completed");
				}
				Thread.sleep(5000);
				CalculateExecutionTime();
				Assert.assertTrue(false, failedResult1);
			} else {
				System.out.println(": TEST SCRIPT:=> " + GTestName + " Has PASSED************");
				APP_LOGS.debug(": TEST SCRIPT:=> " + GTestName + " Has PASSED************");
				test.info(" TEST SCRIPT:=> " + GTestName + " Has PASSED************");
				Fail = true;
				failedResult = "";
				String failedResult1 = failedResult;
				if (captureVideoRecording.equals("Yes")) {
					recorder.stop();
					System.out.println(": Video Recording Stopped As test case completed");
					APP_LOGS.debug(": Video Recording Stopped As test case completed");
					test.info(" Video Recording Stopped As test case completed");
				}
				Thread.sleep(5000);
				Assert.assertTrue(true, failedResult1);
				Fail = false;
				CalculateExecutionTime();
			}
		} catch (Exception e) {
			test.log(Status.ERROR, "ERROR : Not able to end TC");
			return "FAIL - Not able to end TC";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String TestCaseFail() throws InterruptedException, ATUTestRecorderException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: TestCaseFail
		 * 
		 * @parameter: None
		 * 
		 * @notes: Failing the TestCase if Expected behavior is not Matched
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		System.out.println(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
		APP_LOGS.debug(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
		test.info(": TEST SCRIPT:=> " + GTestName + " Has FAILED!!!!!!!!!!!!");
		highlight = false;
		Fail = false;
		failedResult = "";
		String failedResult1 = failedResult;
		if (captureVideoRecording.equals("Yes")) {
			recorder.stop();
			System.out.println(": Video Recording Stopped As test case completed");
			APP_LOGS.debug(": Video Recording Stopped As test case completed");
			test.info(": Video Recording Stopped As test case completed");
			Thread.sleep(SYNC_WAIT);
		}
		Assert.assertTrue(false, failedResult1);
		test.pass("PASS");
		return "PASS";
	}

	public String uploadThroughAutoIT(String filelocation) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: uploadThroughAutoIT ()
		 * 
		 * @parameter: String filelocation
		 * 
		 * @notes: Requires .exe file which is generated by AutoIT.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		System.out.println(": Performing uploading :-> ");
		APP_LOGS.debug(": Performing uploading :-> ");
		highlight = false;
		captureScreenShot = false;
		try {
			Thread.sleep(5000);
			Runtime.getRuntime().exec(filelocation);
			Thread.sleep(5000);

		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			return "FAIL - Not Able to perform uploading :->  ";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String UploadFile(String fileLocation) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: uploadFile ()
		 * 
		 * @parameter:String fileLocation
		 * 
		 * @notes: Requires file location as parameter.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			System.out.println(": Uploading a file from Specified location " + fileLocation);
			APP_LOGS.debug(": Uploading a file from Specified location " + fileLocation);
			test.info(" Uploading a file from Specified location " + fileLocation);
			StringSelection stringSelection = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (RuntimeException localRuntimeException) {
			captureScreenShot = true;
			System.out.println("Error in uploading a file from location: " + localRuntimeException.getMessage());
			test.log(Status.ERROR, "ERROR : Error in uploading a file");
			return "FAIL - Error in uploading a file";
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String VerifyAddressFieldNotContainValidation(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyAddressFieldNotContainValidation ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify that address field should not display validation message
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Verifying address filed should not display validation message");
		APP_LOGS.debug(" Verifying address filed should not display validation message");
		test.info(" Verifying address filed should not display validation message");
		try
		{
			Thread.sleep(2000);
			boolean b = returnElementIfPresent(firstXpathKey).isDisplayed();			
			if(b == true)
			{
				System.out.println(": Address filed is not displaying validation message, Moving Ahead");
				APP_LOGS.debug(" Address filed is not displaying validation message, Moving Ahead");
				test.pass(" Address filed is not displaying validation message, Moving Ahead");
			}
			else
			{
				captureScreenShot = true;
				System.out.println(": Address filed is displaying validation message, Please refer screenshot");
				APP_LOGS.debug(": Address filed is displaying validation message, Please refer screenshot");
				test.fail(": Address filed is displaying validation message, Please refer screenshot");
			}
		}
		catch(Exception e)
		{
			System.out.println(": Address page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyButtonIsDisable(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyButtonIsDisable ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Hover mouse over given Object, link, Hyperlink, selections or buttons of a web page and get the tooltip from the element and verifies it with expText.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Button is Disable " + firstXpathKey);
		APP_LOGS.debug(": Verifying Button is Disable  " + firstXpathKey);
		try {
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement root = returnElementIfPresent(firstXpathKey);
			act.moveToElement(root).build().perform();
			Thread.sleep(2000);

			if (!root.isEnabled())
			{
				 System.out.println(": Button present in disabled mode : " + root.getText()); 
				 APP_LOGS.debug(": Button present in disabled mode : " + root.getText());
				 test.pass(": Button present in disabled mode : " + root.getText());
			} 
			else 
			{
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Button present in enable mode but expected it should be disbale : " + root.getText());
				test.fail(": Button present in enable mode but expected it should be disbale : " + root.getText());
				return "FAIL - Expected -> Button " + root.getText() + " is disable mode AND Actual it is enable mode ";
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Button not present in disable mode";
		}
		return "PASS";

	}

	public String VerifyButtonIsEnable(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyButtonIsEnable ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Hover mouse over given Object, link, Hyperlink, selections or buttons of a web page and get the tooltip from the element and verifies it with expText.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Button is Enable " + firstXpathKey);
		APP_LOGS.debug(": Verifying Button is Enable  " + firstXpathKey);
		try {
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement root = returnElementIfPresent(firstXpathKey);
			act.moveToElement(root).build().perform();
			Thread.sleep(2000);

			if (root.isEnabled()) {
				System.out.println(": Button present in enable mode : " + root.getText());
				APP_LOGS.debug(": Button present in enable mode : " + root.getText());
				test.pass(": Button present in enable mode : " + root.getText());
			} else {
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Button present in disable mode but expected it should be enable" + root.getText());
				test.fail(": Button present in disable mode but expected it should be enable" + root.getText());
				return "FAIL - Expected -> Button " + root.getText() + " is enable mode AND Actual it is disable mode ";
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Button not present in enable mode";
		}
		return "PASS";

	}

	public String VerifyBoxesShouldHaveDifferentValue(String firstXpathKey, String secondXpathKey, String inputData)
	{
		
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyBoxesShouldHaveDifferentValue ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Verify that boxes should dispaly different value
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		String name = null;
		String name1 = null;
		String name2 = null;
		String name3 = null;
		System.out.println(": Verifying that boxes displaying different value");
		APP_LOGS.debug(": Verifying that boxes displaying different value");
		test.info(": Verifying that boxes displaying different value");
		try {
				name = returnElementIfPresent(firstXpathKey).getText();
				name1 = returnElementIfPresent(secondXpathKey).getText();
				name2 = returnElementIfPresent("AMOUNTFINANCED").getText();
				name3 = returnElementIfPresent("TOTALOFPAYMENTS").getText();
				
				if(name.equals(name1) && name.equals(name3) && name.equals(name2) && name1.equals(name2) && name1.equals(name3) && name1.equals(name) && name2.equals(name3) && name2.equals(name1) && name2.equals(name))
				{
					captureScreenShot = true;
					System.out.println(": All boxes having same value, Please refer screenshot ");
					APP_LOGS.debug(": All boxes having same value, Please refer screenshot ");
					test.fail(": All boxes having same value, Please refer screenshot ");
					return "FAIL - All boxes having same value, Please refer screenshot ";
				}
				else
				{
					System.out.println(": Boxes have different value, Moving Ahead ");
					APP_LOGS.debug(": Boxes have different value, Moving Ahead ");
					test.pass(": Boxes have different value, Moving Ahead ");
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the boxes having different value");
			return "FAIL - Not able to Verify the boxes having different value";
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyBusinessIsActive(String firstXpathKey, String secondXpathKey) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyBusinessIsActive ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify business is active.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying business is active");
		APP_LOGS.debug(": Verifying business is active");
		test.info(": Verifying business is active");
		try {
			Thread.sleep(2000);
			String flag = returnElementIfPresent(firstXpathKey).getText();
			if(flag.equals("Active"))
			{
				System.out.println(": Business is active, Moving AHead");
				APP_LOGS.debug(": Business is active, Moving AHead");
				test.pass(": Business is active, Moving AHead");
				VerifyText(firstXpathKey,"","Active");
			}
			else
			{
				Click(secondXpathKey);
				Thread.sleep(1000);
				WaitTillElementAppears("BOINFO_CURRENTSTATUS");
				Thread.sleep(1000);
				Click("BO_ACTIONS_BUTTON");
				Thread.sleep(1000);
				WaitTillElementAppears("ACTIVEBUSINESS_BUTTON");
				Thread.sleep(1000);
				Click("ACTIVEBUSINESS_BUTTON");
				Thread.sleep(1000);
				InputText("VERIFICATIONNOTES_TEXTAREA","","Activated by admin");
				Thread.sleep(1000);
				Click("UPDATEBUSINESS_BUTTON");
				Thread.sleep(1000);
				VerifyText("BOINFO_CURRENTSTATUS","","Active");
				Thread.sleep(1000);
				Click("BOMANAGEMENT_ICONBAG");
				Thread.sleep(1000);
				Click("EXISTINGBO_LINK");
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read " + firstXpathKey);
			return "FAIL - Not able to read " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}

	
	public String VerifyCheckBoxIsEnabled(String firstXpathKey) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyCheckBoxIsEnabled ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify check box is enabled or not.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying if " + firstXpathKey + "  is Enabled ");
		APP_LOGS.debug(": Verifying if " + firstXpathKey + "  is Enabled ");
		try {
			boolean isChecked;
			Thread.sleep(2000);
			isChecked = returnElementIfPresent(firstXpathKey).getAttribute("checked").equals("true");
			if (isChecked == true) {
				System.out.println(": " + firstXpathKey + " CheckBox is in checked status");
				APP_LOGS.debug(": " + firstXpathKey + " CheckBox ist in checked status");
			} else {
				highlight = true;
				captureScreenShot = true;
				System.out.println(": " + firstXpathKey + " CheckBox ist in unchecked status");
				APP_LOGS.debug(": " + firstXpathKey + " CheckBox ist in unchecked status");
				return "FAIL - :" + firstXpathKey + " CheckBox ist in unchecked status";
			}

		} catch (Exception e) {

			captureScreenShot = true;
			return "FAIL - Unable to Verify the Checkbox status";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyColor(String firstXpathKey, String inputData)
	{
		
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyColor ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Verify the color of the WebElement
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		String actColor=null;
		String expColor= null;
		System.out.println(": Verifying the color of the element");
		APP_LOGS.debug(": Verifying the color of the element");
		test.info(": Verifying the color of the element");
		try {
			if(inputData.equals("DarkGrey"))
			{
				String colour = returnElementIfPresent(firstXpathKey).getCssValue("color");
				actColor = Color.fromString(colour).asHex().trim();
				expColor = "#4a4a4a";
			}
			else if(inputData.equals("Green"))
			{
				actColor = returnElementIfPresent(firstXpathKey).getCssValue("color");
				expColor = "rgba(255, 255, 255, 1)";	
			}
			else if(inputData.equals("Orange"))
			{
				String colour = returnElementIfPresent(firstXpathKey).getCssValue("background-color");
				actColor = Color.fromString(colour).asHex().trim();
				expColor = "#fa7c4f";
			}
			if (expColor.equals(actColor)) {
				System.out.println(": "+firstXpathKey+" is displayed in " + inputData + "color");
				APP_LOGS.debug(": "+firstXpathKey+"  is displayed in " + inputData + "color");
				test.pass(": "+firstXpathKey+" is displayed in " + inputData + "color");
			} else {
				captureScreenShot = true;
				System.out.println(": Fail because of "+ firstXpathKey +" is not displayed in " + inputData + "color");
				APP_LOGS.debug(": Fail because of "+ firstXpathKey +" is not displayed in " + inputData + "color");
				test.fail(": Fail because of "+ firstXpathKey +" is not displayed in " + inputData + "color");
				return "FAIL- "+ firstXpathKey +"  is not displayed in " + inputData + "color";
			}
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to verify color");
			return "FAIL - Not able to verify color";
		}		
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyColumnData(String firstXpathkey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyColumnData ()
		 * 
		 * @parameter: String firstXpathkey, String inputData
		 * 
		 * @notes: Verify the column data 
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying " + inputData + " Column Data");
		APP_LOGS.debug(": Verifying " + inputData + " Column Data");
		test.info(": Verifying " + inputData + " Column Data");
		try {
			Thread.sleep(3000);
			List<WebElement> data = returnElementsIfPresent(firstXpathkey);
			int count = data.size();
			if (count == 0) {
				captureScreenShot = true;
				System.out.println(": After Verifying " + inputData + " Column data, No search result is displayed");
				APP_LOGS.debug(": After Verifying " + inputData + " Column data, No search result is displayed");
				test.info(" After Verifying " + inputData + " Column data, No search result is displayed");
				return "FAIL - After Verifying " + inputData + " Column data, No search result is displayed";
			} 
			else 
			{
					Thread.sleep(2000);
					System.out.println(": Verify " + inputData + " Column Data, " + count + " search result is displayed");
					APP_LOGS.debug(": Verify " + inputData + " Column Data, " + count + " search result is displayed");
					test.info(" Verify " + inputData + " Column Data, " + count + " search result is displayed");
					for (int i = 0; i < count; i++)
					{
						String text = data.get(i).getText();
						if (text.contains(inputData))
						{
							bflag = true;
						} 
						else 
						{
							captureScreenShot = true;
							System.out.println(": Fail because of Other than " + inputData + " search result is displayed ");
							APP_LOGS.debug(": Fail because of Other than " + inputData + " search result is displayed ");
							test.info(": Fail because of Other than " + inputData + " search result is displayed ");
							return "Fail because of Other than " + inputData + " search result is displayed ";
						}
					}
				}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to Verify " + inputData + " ColumnData");
			return "FAIL - Not able to Verify " + inputData + " ColumnData";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyColumnDataWithPagination(String firstXpathKey, String secondXpathKey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyColumnDataWithPagination ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String inputData
		 * 
		 * @notes: Verify the column data with pagination
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying " + inputData + " Column Data");
		APP_LOGS.debug(": Verifying " + inputData + " Column Data");
		test.info(": Verifying " + inputData + " Column Data");
		try {
			Thread.sleep(3000);
			List<WebElement> pages = returnElementsIfPresent(firstXpathKey);
			int count = pages.size();
			for (int i = 0; i < count-1; i++) {
				List<WebElement> data = returnElementsIfPresent(secondXpathKey);
				for (int j = 0; j < data.size(); j++) {
					String text = data.get(i).getText();
					if (text.contains(inputData)) {
						bflag = true;
					} else {
						captureScreenShot = true;
						System.out.println(": Fail because of Other than " + inputData + " search result is displayed ");
						APP_LOGS.debug(": Fail because of Other than " + inputData + " search result is displayed ");
						test.info(": Fail because of Other than " + inputData + " search result is displayed ");
						return "Fail because of Other than " + inputData + " search result is displayed ";
					}
				}
				returnElementIfPresent("NEXT_PAGE").click();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to Verify " + inputData + " ColumnData");
			return "FAIL - Not able to Verify " + inputData + " ColumnData";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyDefaultAttributeValue(String firstXpathKey, String expText) throws ParseException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyDefaultAttributeValue ()
		 * 
		 * @parameter: String firstXpathKey and String expText
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. expText should have as a whole it uses contains function.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(" Verifying " + firstXpathKey + " Text on the Page");

		try {
			getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getAttribute("placeholder"));
			actText = getTextOrValues.get(firstXpathKey).toString();
			actText = actText.trim();
			expText = expText.trim();

			if (actText.contains(expText) == true) {
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is->" + expText;
			}

		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyDisbursalAmountIsEmpty(String firstXpathKey, String inputData) throws ParseException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyDisbursalAmountIsEmpty ()
		 * 
		 * @parameter: It verifies disbursal loan amount should be empty
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. expText should have as a whole it uses contains function.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + inputData + " should be displayed empty");
		APP_LOGS.debug(": Verifying " + inputData + " should be displayed empty");
		test.info(": Verifying " + inputData + " should be displayed empty");

		try {
			
				String Date = returnElementIfPresent(firstXpathKey).getText();
				if (Date.equals("-")) {
					System.out.println(": "+inputData+ " is displayed as empty");
					APP_LOGS.debug(": "+inputData+ " is displayed as empty");
					test.pass(": "+inputData+ " is displayed as empty");
				} else {
					highlight = true;
					captureScreenShot = true;
					System.out.println(": "+inputData+ " is not displayed as empty, please refer screenshot");
					APP_LOGS.debug(": "+inputData+ " is not displayed as empty, please refer screenshot");
					test.fail(": "+inputData+ " is not displayed as empty, please refer screenshot");
					return "FAIL"+inputData+ " is not displayed as empty, please refer screenshot";
				}
			
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String VerifyElementNotPresent(String firstXpathKey, String expTEXT) throws ParseException
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyElementNotPresent ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the element is not present on the web page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Checking Element " + firstXpathKey + " Text from the Page");
		APP_LOGS.debug(": Checking Element " + firstXpathKey + " Text from the Page");
		test.info(": Checking Element " + firstXpathKey + " Text from the Page");
		highlight = false;
		try
		{
			String sFlag="";
			if(isElementPresent(firstXpathKey)==false)
			{
				sFlag="FALSE";
				System.out.println(": No: Is element Present on Page----> "+sFlag);
				APP_LOGS.debug(": No: Is element Present on Page----> "+sFlag);
				test.pass(": No: Is element Present on Page----> "+sFlag);
			}
			else
			{
				sFlag="TRUE";
				System.out.println(": Yes: Is element Present on Page----> "+sFlag);
				APP_LOGS.debug(": Yes: Is element Present on Page----> "+sFlag);
				test.fail(": Yes: Is element Present on Page----> "+sFlag);
				return "FAIL - Yes: Is element Present on Page----> "+sFlag;
			}
			if(expTEXT.equals(sFlag))
			{
				System.out.println(": Element is  " + firstXpathKey + " : Not Present in the page");
				APP_LOGS.debug(": Element is  " + firstXpathKey + " : Not Present in the page");
				test.pass(": Element is  " + firstXpathKey + " : Not Present in the page");
			}else{
				globalExpText = firstXpathKey;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Element is  " + firstXpathKey + " : Present in the page");
				APP_LOGS.debug(": Element is  " + firstXpathKey + " : Present in the page");
				test.fail(": Element is  " + firstXpathKey + " : Present in the page");
				return "FAIL - Element is  " + firstXpathKey + " : Present in the page";
			}	
		}
		catch(Exception e)
		{
			captureScreenShot = true;
			System.out.println("inside VerifyElement"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not Able to verify Element is present or Not--" + firstXpathKey;
	     }
	test.pass("PASS");
	return "PASS";
	}

	public String VerifyElementPresent(String firstXpathKey, String expTEXT) throws ParseException
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyElementPresent ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the element is present on the web page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Checking Element " + firstXpathKey + " Text from the Page");
		APP_LOGS.debug(": Checking Element " + firstXpathKey + " Text from the Page");
		test.pass(": Checking Element " + firstXpathKey + " Text from the Page");
		highlight = false;
		try
		{
			String sFlag="";
			if(isElementPresent(firstXpathKey)==true)
			{
				sFlag="TRUE";
				System.out.println(": Yes: Is element Present on Page----> "+sFlag);
				APP_LOGS.debug(": Yes: Is element Present on Page----> "+sFlag);
				test.pass(": Yes: Is element Present on Page----> "+sFlag);
			}
			else
			{
				sFlag="FALSE";
				captureScreenShot = true;
				System.out.println(": No: Is element Present on Page----> "+sFlag);
				APP_LOGS.debug(": No: Is element Present on Page----> "+sFlag);
				test.fail(": No: Is element Present on Page----> "+sFlag);
			}
			if(expTEXT.equals(sFlag))
			{
				System.out.println(": Element is present in the page");
				APP_LOGS.debug(": Element is  " + firstXpathKey + " : Present in the page");
				test.pass(": Element is  " + firstXpathKey + " : Present in the page");
			}else{
				captureScreenShot = true;
				System.out.println(": Element is not present in the page");
				APP_LOGS.debug(": Element is  " + firstXpathKey + " : Not Present in the page");
				test.fail(": Element is  " + firstXpathKey + " : Not Present in the page");
			}
			
		}
		catch(Exception e)
		{
			captureScreenShot = true;
			System.out.println("inside VerifyElement"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not Able to verify Element is present or Not--" + firstXpathKey;
	     }
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyFallBackToDirectFlagDisable(String firstXpathKey, String secondXpathKey) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFallBackToDirectFlagDisable ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify fallback to direct flag is enable.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying fallbacktodirect flag is disable");
		APP_LOGS.debug(": Verifying fallbacktodirect flag is disable");
		test.info(": Verifying fallbacktodirect flag is disable");
		try {
			Thread.sleep(2000);
			String flag = returnElementIfPresent(firstXpathKey).getText();
			if(flag.equals("false"))
			{
				System.out.println(": Fallbacktodirect flag is disabled, Moving AHead");
				APP_LOGS.debug(": Fallbacktodirect flag is disabled, Moving AHead");
				test.pass(": Fallbacktodirect flag is disabled, Moving AHead");
				VerifyText("FBD_CONFIGVALUE_TEXT","","false");
			}
			else
			{
				Click(secondXpathKey);
				Thread.sleep(1000);
				SelectValueFromBootStrapDropDown("ENTERCONFIGVALUE_TEXTFIELD","LIST_OPTIONS","False");
				Thread.sleep(1000);
				Click("CONFIG_DONE_BUTTON");
				Thread.sleep(2000);
				Click("GLOBALSERVICE_LINK");
				Thread.sleep(2000);
				VerifyText("FBD_CONFIGVALUE_TEXT","","false");
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read " + firstXpathKey);
			return "FAIL - Not able to read " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyFallBackToDirectFlagEnable(String firstXpathKey, String secondXpathKey) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFallBackToDirectFlagEnable ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify fallback to direct flag is enable.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying fallbacktodirect flag is enable");
		APP_LOGS.debug(": Verifying fallbacktodirect flag is enable");
		test.info(": Verifying fallbacktodirect flag is enable");
		try {
			Thread.sleep(2000);
			String flag = returnElementIfPresent(firstXpathKey).getText();
			if(flag.equals("true"))
			{
				System.out.println(": Fallbacktodirect flag is enabled, Moving AHead");
				APP_LOGS.debug(": Fallbacktodirect flag is enabled, Moving AHead");
				test.pass(": Fallbacktodirect flag is enabled, Moving AHead");
				VerifyText("FBD_CONFIGVALUE_TEXT","","true");
			}
			else
			{
				Click(secondXpathKey);
				Thread.sleep(1000);
				SelectValueFromBootStrapDropDown("ENTERCONFIGVALUE_TEXTFIELD","LIST_OPTIONS","True");
				Thread.sleep(1000);
				Click("CONFIG_DONE_BUTTON");
				Thread.sleep(2000);
				Click("GLOBALSERVICE_LINK");
				Thread.sleep(2000);
				VerifyText("FBD_CONFIGVALUE_TEXT","","true");
				
			}
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read " + firstXpathKey);
			return "FAIL - Not able to read " + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyFallBackToDirectOffers(String firstXpathKey, String secondXpathKey) throws InterruptedException
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFallBackToDirectOffers ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verifying fallback to direct offers displayed on the page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Verifying fallback to direct offers displayed on the page");
		APP_LOGS.debug(": Verifying fallback to direct offers displayed on the page");
		test.info(": Verifying fallback to direct offers displayed on the page");
		try
		{
			Thread.sleep(2000);
			List<WebElement> offers= returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if(count ==1)
			{
				VerifyText(firstXpathKey,"","Thank you for applying with LoanGlide!");
				System.out.println(": No offer found for customer for indirect application and direct application");
				APP_LOGS.debug(": No offer found for customer for indirect application and direct application");
				test.pass(": No offer found for customer for indirect application and direct application");
			}
			else
			{
				System.out.println(": No offer is found for indirect but offer received for direct application");
				APP_LOGS.debug(": No offer is found for indirect but offer received for direct application");
				test.pass(": No offer is found for indirect but offer received for direct application");
				WaitTillElementAppears("CONGRATULATIONS_TEXT");
				VerifyText("FALLBACK_VERBIAGE_TEXT","","Review your offers and select the offer which works best for you and complete your loan application");
				Click("VIEWMYOFFERS_BUTTON");
				WaitTillElementAppears("DIRECT_LOANAMT_LABEL");
				VerifyOffers(secondXpathKey);
			}
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	
	}

	public String VerifyFicoUsecases(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFicoUsecases ()
		 * 
		 * @parameter:String firstXpathKey
		 * 
		 * @notes: Verify the Fico message
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
				
		try
		{
			
			int count = returnElementsIfPresent(firstXpathKey).size();
			
			if (count==1) 
			{
				
				
			} 
			else
			{
				WaitTillElementAppears("VIEWMYOFFERS_BUTTON");
				Click("VIEWMYOFFERS_BUTTON");
				WaitTillElementAppears("SIDEBYSIDECOMPARISON_LINK");
				Click("SIDEBYSIDECOMPARISON_LINK");
				Click("RECOMMENDEDPLAN");
				Click("SELECTTHISOFFER_BUTTON");
				
				
			}
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyFileIsDownloaded() {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFileIsDownloaded ()
		 * 
		 * @parameter: None
		 * 
		 * @notes: Verifying file is downloaded
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */ highlight = false;
		captureScreenShot = false;
		String directoryName = "C:\\Users\\{username}\\Downloads";
		try {
			File directory = new File(directoryName);
			if (directory.isDirectory()) {
				for (int i = 0; i < directory.list().length; i++) {
					File file = new File(directory + "\\" + directory.list()[i]);
					if (file.getName().contains(".pdf") || file.getName().contains(".xml") || file.getName().contains(".csv")) {
						System.out.println("Successfully executed with file name of : " + file.getName());
					} else {
						System.out.println("directory has files with different file extention or folders.");
					}
				}
			} else {
				System.out.println("It is not a directory.");
			}
			System.out.println("Successfully verified files : " + directoryName);
			APP_LOGS.debug("Successfully verified files : " + directoryName);
		} catch (Exception ex) {
			System.out.println("Error in verifing contents of the directory : " + directoryName + " with exception " + ex.getMessage());
			return "FAIL - Error in verifing contents of the directory : " + directoryName;
		}
		return "PASS";
	}

	public String VerifyFileDownload(String dataColValue) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFileDownload ()
		 * 
		 * @parameter: dataColValue
		 * 
		 * @notes: Verifies file mentions in parameter.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		try {
			dataColValue.replaceAll("\\\\", "/");
			File file = new File(dataColValue);
			highlight = false;
			captureScreenShot = false;

			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			String str = String.format("%1.2f", kilobytes);
			kilobytes = Double.valueOf(str);

			if (file.exists() && bytes != 0) {
				System.out.println(": File is downloaded successfull at:-> " + dataColValue + " path");
				APP_LOGS.debug(": File is downloaded successfull at:-> " + dataColValue + " path");
			} else {
				highlight = true;
				System.out.println(": File is not present at the location ");
				APP_LOGS.debug(":  File is not present at the location ");
				return "FAIL - File is not present at the location ";
			}

		} catch (Exception exception) {
			captureScreenShot = true;
			System.out.println("Error in saving a file: " + exception.getMessage());
			return "FAIL - Error in saving a file";
		}

		return "PASS";
	}

	public String VerifyFileIsExportedAndSizeIsNotZero(String secondXpathKey, String dataColValue) throws Exception {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFileIsExportedAndSizeIsNotZero
		 * 
		 * @parameter: String secondXpathKey, String dataColValue
		 * 
		 * @notes: Verifies file mentions in parameter.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Entetred in Verify File is Exported and Size is Not zero");
		String tempFileName = "";
		String tempFileDownloadDirectory = "";
		// String objectIdentifierType="";
		String objectIdentifierValue = "";
		String objectArray[] = null;
		try {

			String user = System.getProperty("user.name");
			if (dataColValue.contains("C:\\Users\\{username}\\Downloads\\")) {
				dataColValue = dataColValue.replace("{username}", user);
			}
			String object = OR.getProperty(secondXpathKey);
			objectArray = object.split("__");
			objectIdentifierValue = objectArray[1].trim();
			Thread.sleep(5000);
			/*
			 * if (objectIdentifierValue.contains(".csv")) { objectIdentifierValue = fileName; // tempFileName = objectIdentifierValue; }
			 */

			tempFileName = objectIdentifierValue;
			System.out.println(": Verifing '" + tempFileName + "' Exported File is Downloaded properly.");
			APP_LOGS.debug(": Verifing '" + tempFileName + "' Exported File is Downloaded properly.");
			test.info(" Verifying '" + tempFileName + "' Exported File is Downloaded properly.");
			tempFileDownloadDirectory = dataColValue;
			dataColValue = dataColValue + tempFileName;
			dataColValue.replaceAll("\\\\", "/");
			File file = new File(dataColValue);
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			String str = String.format("%1.2f", kilobytes);
			kilobytes = Double.valueOf(str);

			if (file.exists() && bytes != 0) {
				System.out.println(": " + tempFileName + " file is Exported successfull at:-> '" + tempFileDownloadDirectory + "' Directory and its Size is " + kilobytes + "KB");
				APP_LOGS.debug(": " + tempFileName + " file is Exported successfull at:-> '" + tempFileDownloadDirectory + "' Directoryand and its Size is " + kilobytes + "KB");
				test.pass(" " + tempFileName + " file is Exported successfully at:-> '" + tempFileDownloadDirectory + "' Directoryand and its Size is " + kilobytes + "KB");
			} else {
				System.out.println(": " + tempFileName + " file is NOT Exported at:-> '" + tempFileDownloadDirectory + "' Directory and its Size is " + kilobytes + "KB");
				APP_LOGS.debug(": " + tempFileName + " file is NOT Exported at:-> '" + tempFileDownloadDirectory + "' Directory and its Size is " + kilobytes + "KB");
				test.fail("FAIL : " + tempFileName + " file is NOT Exported as it is not present at:-> '" + tempFileDownloadDirectory + "' Directory.");
				return "FAIL - " + tempFileName + " file is NOT Exported at:-> '" + tempFileDownloadDirectory + "' Directory and its Size is " + kilobytes + "KB";
			}

			if (file.delete()) {
				System.out.println(": After Verification: " + tempFileName + " file is deleted successfully from :-> '" + tempFileDownloadDirectory + "' Directory");
				APP_LOGS.debug(": After Verification: " + tempFileName + " file is deleted successfully from :-> '" + tempFileDownloadDirectory + "' Directory");
				test.info(" After Verification: " + tempFileName + " file is deleted successfully from :-> '" + tempFileDownloadDirectory + "' Directory");
			} else {
				System.out.println("After Verification: " + tempFileName + " file is NOT deleted from :-> '" + tempFileDownloadDirectory + "' Directory. Please check Manually");
				APP_LOGS.debug("After Verification: " + tempFileName + " file is NOT deleted from :-> '" + tempFileDownloadDirectory + "' Directory. Please check Manually");
				test.info("After Verification: " + tempFileName + " file is NOT deleted from :-> '" + tempFileDownloadDirectory + "' Directory. Please check Manually");
			}

		} catch (Exception exception) {
			System.out.println(": Error in saving a file: " + exception.getMessage());
			test.log(Status.ERROR, "ERROR : " + tempFileName + " file is NOT Exported at:-> '" + tempFileDownloadDirectory + "' Directory and got following error message=> " + exception.getMessage());
			return "FAIL - " + tempFileName + " file is NOT Exported at:-> '" + tempFileDownloadDirectory + "' Directory and got following error message=> " + exception.getMessage();
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyFirstName(String firstXpathKey, String secondXpathKey, String expText)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFirstName ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the first name should be equal to expText.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		if (expText.isEmpty()) {
			System.out.println(": Expected Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Expected Data is Empty, taking this value from Hashmap");
			test.info(" Expected Data is Empty, taking this value from Hashmap");
			expText = (String) getTextOrValues.get(secondXpathKey);
			if (expText == null) {
				System.out.println(": No Expected Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				expText = returnElementIfPresent(secondXpathKey).getText().trim();
			}
		}		
		try
		{
			String text = returnElementIfPresent(firstXpathKey).getText();
			String[] parts = text.split(" ");
			String firstName = parts[0].trim();
			actText = firstName;
			expText = expText.trim();
			if (actText.compareTo(expText) == 0) 
			{
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} 
			else
			{
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
			}
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyFontType(String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyFontType ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify the text font type.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		String expFontType = null;
		System.out.println(": Verifying the text fonttype");
		APP_LOGS.debug(": Verifying the text fonttype");
		test.info(": Verifying the text fonttype");
		try {
				String actText = returnElementIfPresent(firstXpathKey).getCssValue("font-family");			
				if(inputData.equals("Heading"))
				{
					expFontType = "Avenir";
				}
				else if(inputData.equals("Normal"))
				{
					expFontType = "Avenir-Medium";
				}
				if (actText.contains(expFontType)) {
					System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expFontType);
					APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expFontType);
					test.pass(" Actual is-> " + actText + " AND Expected is->" + expFontType);
				} else {
					captureScreenShot = true;
					System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expFontType);
					test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expFontType);
					return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expFontType;
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the font type");
			return "FAIL - Not able to Verify the font type";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyLastName(String firstXpathKey, String secondXpathKey, String expText)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyLastName ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the last name should be equal to expText.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		if (expText.isEmpty()) {
			System.out.println(": Expected Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Expected Data is Empty, taking this value from Hashmap");
			test.info(" Expected Data is Empty, taking this value from Hashmap");
			expText = (String) getTextOrValues.get(secondXpathKey);
			if (expText == null) {
				System.out.println(": No Expected Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				expText = returnElementIfPresent(secondXpathKey).getText().trim();
			}
		}
		
		try
		{
			String text = returnElementIfPresent(firstXpathKey).getText();
			String[] parts = text.split(" ");
			String lastName = parts[1].trim();
			actText = lastName;
			expText = expText.trim();
			if (actText.compareTo(expText) == 0) 
			{
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} 
			else
			{
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
			}
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyLightStreamOffers(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyLightStreamOffers ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify lightstream offers on direct finance offers page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying lightstream offers on direct finance offers page");
		APP_LOGS.debug(": Verifying lightstream offers on direct finance offers page");
		test.info(": Verifying lightstream offers on direct finance offers page");
		try 
		{
			Thread.sleep(3000);
			int count = returnElementsIfPresent(firstXpathKey).size();
			
			if(count > 0)
			{
				System.out.println(": " +count+ " lightstream offers on direct finance offers page");
				APP_LOGS.debug(": " +count+ " lightstream offers on direct finance offers page");
				test.info(": " +count+ " lightstream offers on direct finance offers page");	
			
			//		returnElementIfPresent(secondXpathKey).click();
					WaitTillElementAppears("LIGHTSTREAM_LENDISCLOSURE_LINK");
					Click("LIGHTSTREAM_LENDISCLOSURE_LINK");
					WaitTillElementAppears("HOWDOWEFETPAID_DIALOGBOX");
			//		VerifyText("HOWDOWEFETPAID_DIALOGBOX","","You are not yet approved or pre-approved for a loan or a specific rate. Please click �Select my offers� to complete your LightStream application to see exactly what LightStream can offer you. Your APR may differ based on loan purpose, amount, term, and your credit profile. Excellent credit is required to qualify for lowest rates. Rate is quoted with AutoPay discount, which is only available when you select AutoPay prior to loan funding. Rates under the invoicing option may be higher. Conditions and limitations apply. Advertised rates and terms are subject to change without notice. Not a commitment to lend. Payment example: Monthly payments for a $10,000 loan at 9.84% APR with a term of 3 years would result in 36 monthly payments of $321.92. Truist Bank is an Equal Housing Lender. � 2020 Truist Financial Corporation. SunTrust, Truist, LightStream, the LightStream logo, and the SunTrust logo are service marks of Truist Financial Corporation. All other trademarks are the property of their respective owners. Lending services provided by Truist Bank.");
					Click("HOWDOWEGOTPAID_X_BUTTON");
					GetText(secondXpathKey);
					VerifyText("LIGHTSTREAM_LOANAMT_LABEL",secondXpathKey,"");
					VerifyText("LIGHTSTREAM_FUNDSRECVD_LABEL",secondXpathKey,"");
			//		VerifyDisbursalAmountIsEmpty("LIGHTSTREAM_ORGINFEE_LABEL", "Orgination Fee");
					VerifyElementPresent("LIGHTSTREAM_MONTHLYAMT_TEXT","TRUE");
			//		VerifyElementPresent("LIGHTSTREAM_MONTHS_TEXT","TRUE");
					VerifyElementPresent("LIGHTSTREAM_APR_TEXT","TRUE");
			
			}
			else
			{
				System.out.println(": lightstream offers are not displayed on direct finance offers page");
				APP_LOGS.debug(": lightstream offers are not displayed on direct finance offers page");
				test.info(": lightstream offers are not displayed on direct finance offers page");
			}
				
			
		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	public String VerifyZiboLightStreamOffers(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyZiboLightStreamOffers ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify lightstream offers on direct finance offers page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying lightstream offers on direct finance offers page");
		APP_LOGS.debug(": Verifying lightstream offers on direct finance offers page");
		test.info(": Verifying lightstream offers on direct finance offers page");
		try 
		{
			Thread.sleep(3000);
			int count = returnElementsIfPresent(firstXpathKey).size();
			
			if(count > 0)
			{
				System.out.println(": " +count+ " lightstream offers on direct finance offers page");
				APP_LOGS.debug(": " +count+ " lightstream offers on direct finance offers page");
				test.info(": " +count+ " lightstream offers on direct finance offers page");	
			
					WaitTillElementAppears("LIGHTSTREAM_LENDISCLOSURE_LINK");
					Click("LIGHTSTREAM_LENDISCLOSURE_LINK");
					WaitTillElementAppears("HOWDOWEFETPAID_DIALOGBOX");
					Click("HOWDOWEGOTPAID_X_BUTTON");
					GetText("DIRECTLIG_UPLOANAMT_LABEL");
		//			VerifyText("DIFI_LOANAMT_LABEL",secondXpathKey,"");
					VerifyText("DIRECTLIG_FNDSRCVD_LABEL","DIRECTLIG_UPLOANAMT_LABEL","");
					VerifyElementPresent("DIRECTLIG_MONTHS_LABEL","TRUE");
					VerifyElementPresent("DIRECTLIG_APR_LABEL","TRUE");
			
			}
			else
			{
				System.out.println(": lightstream offers are not displayed on direct finance offers page");
				APP_LOGS.debug(": lightstream offers are not displayed on direct finance offers page");
				test.info(": lightstream offers are not displayed on direct finance offers page");
			}
				
			
		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}

	
	public String VerifyLoanApplyDate(String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyLoanApplyDate()
		 * 
		 * @parameter: String firstXpathKey, String Todaydate
		 * 
		 * @notes: Verifying loan apply date.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
	//	highlight = false;
	//	captureScreenShot = false;
		Date date = null;
		String expText=null;
		String createdDate=null;
		String todayDate = null;
		String actText = null;
		
		System.out.println(": Verifying Loan apply Date");
		APP_LOGS.debug(": Verifying Loan apply Date");
		test.info(": Verifying Loan apply Date");
		try {
				date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
				createdDate = formatter.format(date);
				todayDate=createdDate.toString();
				expText = todayDate.trim();
				if(inputData.equals("Registration"))
				{
					actText = returnElementIfPresent(firstXpathKey).getText();
					String act[]= actText.split(":");
					actText = act[1].trim();
				}
				else
				{
					actText = returnElementIfPresent(firstXpathKey).getText();
				}
				
				if (actText.equals(expText)) {
					System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
					APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
					test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
				} else {
					highlight = true;
					captureScreenShot = true;
					System.out.println("Screenshot ="+captureScreenShot);
					System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
					APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
					test.fail(": Actual is-> " + actText + " AND Expected is-> " + expText);
					return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the date");
			return "FAIL - Not able to Verify the date";
		}
		
		test.pass("PASS");
		return "PASS";
		
	}
	
	public String VerifyLoanPlanIsDisplayed(String firstXpathkey, String expText)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyLoanPlanIsDisplayed ()
		 * 
		 * @parameter: Verify the loanid should be displayed on loanplans
		 * 
		 * @notes: 
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bFlag = false;
		System.out.println(": Verifying the " +expText+ " LoanPlan should be displayed");
		APP_LOGS.debug(": Verifying the " +expText+ " LoanPlan should be displayed");
		test.info(": Verifying the " +expText+ " LoanPlan should be displayed");
		try
		{
			int id = returnElementsIfPresent(firstXpathkey).size();
			if(id>0)
			{
				System.out.println(": " +expText+ " is displayed on the loanplans page");
				APP_LOGS.debug(": " +expText+ " is displayed on the loanplans page");
				test.pass(": " +expText+ " is displayed on the loanplans page");
			}
			else
			{
				captureScreenShot = true;
				System.out.println(": " +expText+ " is not displayed on the loanplans page");
				APP_LOGS.debug(": " +expText+ " is not displayed on the loanplans page");
				test.fail(": " +expText+ " is not displayed on the loanplans page");
				return "FAIL - " +expText+ " is not displayed on the loanplans page";
			}
		}
			
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not able to verify " +expText);
			APP_LOGS.debug("FAIL - Not able to verify " +expText);
		}
		
		test.pass("PASS");
		return "PASS";
	}

	
	public String VerifyListItemsUnique(String firstXpathkey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyListItemsUnique ()
		 * 
		 * @parameter: Verify the loanid's should be displayed unique on dashboard 
		 * 
		 * @notes: 
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		boolean bFlag = false;
		List<String> list = new ArrayList<String>();
		String arr[] = null;
		System.out.println(": Verifying the " +inputData+ " should be displayed unique");
		APP_LOGS.debug(": Verifying the " +inputData+ " should be displayed unique");
		test.info(": Verifying the " +inputData+ " should be displayed unique");
		try {
			Thread.sleep(3000);
			List<WebElement> id = returnElementsIfPresent(firstXpathkey);
			int count = id.size();
			for (int i = 0; i <= count - 1; i++) {
				String loanId = id.get(i).getText();
				list.add(loanId);
				arr = list.toArray(new String[list.size()]);
			}
			for (int i = 0; i < arr.length - 1; i++) {
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[i] == arr[j]) {
						bFlag = false;
						captureScreenShot = true;
						System.out.println(": " + inputData + "are same, please refer screenshot");
						APP_LOGS.debug(": " + inputData + "are same, please refer screenshot");
						test.fail(": " + inputData + "are same, please refer screenshot");
					}
				}
			}
			bFlag = true;
			System.out.println(": " + inputData + " are showing unique");
			APP_LOGS.debug(": " + inputData + " are showing unique");
			test.pass(": " + inputData + " are showing unique");
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not able to verify " +inputData);
			APP_LOGS.debug("FAIL - Not able to verify " +inputData);
		}
		
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyMenuShouldNotDisplayed(String firstXpathkey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyMenuShouldNotDisplayed ()
		 * 
		 * @parameter: Verify the menu should not displayed when user giving disabled access to user
		 * 
		 * @notes: Verify the menu should not displayed when user giving disabled access to user
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		
		System.out.println(": Verifying admin menu should not displayed");
		APP_LOGS.debug(": Verifying admin menu should not displaying");
		test.info(": Verifying admin menu should not displaying");
		
		try
		{
			Thread.sleep(3000);
			int menu = returnElementsIfPresent(firstXpathkey).size();
			if(menu==0)
			{
				System.out.println(": Admin menu is not displayed");
				APP_LOGS.debug(": Admin menu is not displayed");
				test.pass(": Admin menu is not displayed");
			}
			else
			{
				captureScreenShot = true;
				System.out.println(": Admin menu is displaying, Please refer screenshot");
				APP_LOGS.debug(": Admin menu is displaying, Please refer screenshot");
				test.fail(": Admin menu is displaying, Please refer screenshot");
			}
		} 
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not able to verify admin menu");
			APP_LOGS.debug("FAIL - Not able to verify admin menu");
		}
		
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyNameIsEmpty(String firstXpathKey,String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyNameIsEmpty ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Verify that name is empty
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		String name = null;
		System.out.println(": Verifying that name is empty");
		APP_LOGS.debug(": Verifying that name is empty");
		test.info(": Verifying that name is empty");
		try {
				name = returnElementIfPresent(firstXpathKey).getText();
				if(inputData.equals("FirstName"))
				{	
					String[] parts = name.split(" ");
					String firstName = parts[0].trim();
					inputData = firstName.trim();
				}
				else if(inputData.equals("LastName"))
				{
					String[] parts = name.split(" ");
					String lastName = parts[1].trim();
					inputData = lastName.trim();
				}
				if(inputData.isEmpty())
				{
					captureScreenShot = true;
					System.out.println(": "+ inputData + "is displaying empty, Please refer screenshot ");
					APP_LOGS.debug(": "+ inputData + "is displaying empty, Please refer screenshot ");
					test.fail(": "+ inputData + "is displaying empty, Please refer screenshot ");
					return ": FAIL -"+ inputData + "is displaying empty, Please refer screenshot ";
				}
				else
				{
					System.out.println(": Name is not displaying empty, Moving Ahead ");
					APP_LOGS.debug(": Name is not displaying empty, Moving Ahead ");
					test.pass(": Name is not displaying empty, Moving Ahead ");
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the name is empty");
			return "FAIL - Not able to Verify the name is empty";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyNewPage(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyNewPage ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the new page displayed on e-sign page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Verifying new page displaing on the e-sign page");
		APP_LOGS.debug(": Verifying new page displaing on the e-sign page");
		test.info(": Verifying new page displaing on the e-sign page");
		try
		{
			List<WebElement> offers= returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if(count>0)
			{
				System.out.println(": New page is displayed on the e-sign page, Moving Ahead");
				APP_LOGS.debug(": New page is displayed on the e-sign page, Moving Ahead");
				test.pass(": New page is displayed on the e-sign page, Moving Ahead");
			}
			else
			{
				for(int i=0;i<=2;i++)
				{
					PageRefresh();
					WaitTillElementAppears("FIRSTTHINGSFIRST_PROGRESSTEXT");
					InputText("LOANAMOUNT_TEXTFIELD","","15000");
					InputText("ANNUALINCOME_TEXTFIELD","","50000");
					GetAttributeValue("LOANAMOUNT_TEXTFIELD");
					ScrollElementIntoView("NEXT_BUTTON");
					Click("NEXT_BUTTON");
					WaitTillElementAppears("TELLUSMOREABOUTURSELF");
					InputText("FIRSTNAME_TEXTFIELD","","Auto");
					InputText("LASTNAME_TEXTFIELD","","User");
					InputText("EMAILADDRESS_TEXTFIELD","","VOIE");
					InputText("PHONENUMBER_TEXTFIELD","","7015168317");
					InputText("DATEOFBIRTH_TEXTFIELD","","08161970");
					InputText("SSN_TEXTFIELD","","Random");
					ScrollElementIntoView("NEXT_BUTTON");
					Click("NEXT_BUTTON");	
					WaitTillElementAppears("WHERETOLIVE_PROGRESSTEXT");		
					InputTextWithAutoAddress("ADDRESSLINE1_TEXTFIELD","210 West");
					ScrollElementIntoView("AGREEFINDMYRATES_BUTTON");		
					Click("AGREEFINDMYRATES_BUTTON");		
					WaitTillElementAppears("VIEWMYOFFERS_BUTTON");		
					Click("VIEWMYOFFERS_BUTTON");		
					WaitTillElementAppears("SIDEBYSIDECOMPARISON_LINK");		
					Click("SIDEBYSIDECOMPARISON_LINK");		
					Click("RECOMMENDEDPLAN");		
					Click("SELECTTHISOFFER_BUTTON");		
					WaitTillElementAppears("COMPLETE_BUTTON");		
					InputText("FIRSTNAME_TEXTFIELD","FIRSTNAME_TEXTFIELD","");	
					InputText("LASTNAME_TEXTFIELD","LASTNAME_TEXTFIELD","");	
					InputText("LASTDIGITSOFSSN_TEXTFIELD","SSN_TEXTFIELD","");	
					Click("COMPLETE_BUTTON");	
					WaitTillElementAppears("ONELASTSTEP_TEXT");		
					VerifyText("ONELASTSTEP_TEXT","","One Last Step!");
					VerifyText("BEFFINZLOAN_TEXT","","We just need few more information before finalizing your loan!");
					List<WebElement> offers1= returnElementsIfPresent(firstXpathKey);
					int count1 = offers1.size();
					if(count1>0)
					{
						System.out.println(": New page is displayed on the e-sign page, Moving Ahead");
						APP_LOGS.debug(": New page is displayed on the e-sign page, Moving Ahead");
						test.pass(": New page is displayed on the e-sign page, Moving Ahead");
					}
					break;	
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	
	}
	
	public String VerifyOffers(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyOffers ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Verify the offers displayed on the page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Verifying offers displayed on the page");
		APP_LOGS.debug(": Verifying offers displayed on the page");
		test.info(": Verifying offers displayed on the page");
		try
		{
			List<WebElement> offers= returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if(count>0)
			{
				System.out.println(": " +count + ": Offers are displayed on the page, Moving Ahead");
				APP_LOGS.debug(": " +count + ": Offers are displayed on the page, Moving Ahead");
				test.pass(": " +count + ": Offers are displayed on the page, Moving Ahead");
			}
			else
			{
				captureScreenShot = true;
				System.out.println(": " +count + ": Offers are displayed on the page, Please refer screenshot");
				APP_LOGS.debug(": " +count + ": Offers are displayed on the page, Please refer screenshot");
				test.fail(": " +count + ": Offers are displayed on the page, Please refer screenshot");
				return "FAIL -" +count + ": Offers are displayed on the page, Please refer screenshot";
			}
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	}
	
	public String ValidateOffers(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ValidateOffers ()
		 * 
		 * @parameter:
		 * 
		 * @notes: Validate the offers displayed on the offers page as well as e-sign page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		String expText="";
		System.out.println(": Validating the offers displayed on the page");
		APP_LOGS.debug(": Validating the offers displayed on the page");
		test.info(": Validating the offers displayed on the page");
		try
		{
			int i;
			String mmotnh;
			List<WebElement> offers= returnElementsIfPresent("SLICKDOTS_LIST");
			int count = offers.size();
			for(i=0;i<count;i++)
			{
				driver.findElement(By.xpath("//ul[@class='slick-dots']/li["+(i+1)+"]")).click();
				Thread.sleep(3000);
				String desc = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div/div/div/div["+(i+1)+"]/div/div/div[1]/div[2]/div[1]")).getText();
				if(desc.contains("Any outstanding balance after completion of the initial period"))
				{
					String monthlypyment = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div/div/div/div["+(i+1)+"]/div/div/div[1]/div[1]/div[1]/span")).getText();
					getTextOrValues.put("MONTHLYPAYMENTT_LABEL", monthlypyment.trim());
					mmotnh = monthlypyment;
					monthlypyment = monthlypyment+"/month";
					getTextOrValues.put("MONTHLYPAYMENT_LABEL", monthlypyment.trim());
					Click("SELECTTHISOFFER_BUTTON");
					Thread.sleep(3000);
					expText = "Pay 24 monthly payments and all outstanding balance within initial period of 24 months at 0% APR.";
					VerifyTextContains(firstXpathKey,"",expText);
				}
				else
				{
					String monthlypyment = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div/div/div/div["+(i+1)+"]/div/div/div[1]/div[1]/div[1]/span")).getText();
					getTextOrValues.put("MONTHLYPAYMENTT_LABEL", monthlypyment.trim());
					mmotnh = monthlypyment;
					monthlypyment = monthlypyment+"/month";
					getTextOrValues.put("MONTHLYPAYMENT_LABEL", monthlypyment.trim());
					String termmonths = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div/div/div/div["+(i+1)+"]/div/div/div/div[2]/div[2]/div[1]")).getText();
					String term[] = termmonths.split("mo");
					termmonths = term[0]+" months.";
					getTextOrValues.put("LOANTERMMONTHS_LABEL", termmonths.trim());
					String apr = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div/div/div/div["+(i+1)+"]/div/div/div/div[2]/div[2]/div[2]")).getText();
					if(apr.equals("0.00% APR")==true)
					{
						String app[]=apr.split("\\.");
						apr = app[0]+"% APR";
						getTextOrValues.put("PERCENTAGEAPR_LABEL", apr.trim());
					}
					else
					{
						apr = apr;
						getTextOrValues.put("PERCENTAGEAPR_LABEL", apr.trim());
					}
					Click("SELECTTHISOFFER_BUTTON");
					Thread.sleep(3000);
					expText = "Pay "+monthlypyment+" at "+apr+" for "+termmonths;
					VerifyTextContains(firstXpathKey,"",expText);
				}
				Click("VIEWLOANAGREEMENT_BUTTON");
				mmotnh = mmotnh+" (e)";
				VerifyText("AMOUNTOFPYMNTS_LABEL","",mmotnh);
				Thread.sleep(1000);
				Click("LOANAGREEMENT_X_BUTTON");
				Thread.sleep(2000);
				if(i<count-1)
				{
					Click("ESIGN_BACKBUTTON");
				}
				else
				{
				}
				Thread.sleep(2000);	
			}			
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	
	}
	
	public String ValidateCart(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: ValidateCart ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Validate the cart items
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		String expText="";
		String actText="";
		System.out.println(": Validating the cart items");
		APP_LOGS.debug(": Validating the cart items");
		test.info(": Validating the cart items");
		try
		{
			String Item1 = returnElementIfPresent(firstXpathKey).getText();
			String app[]=Item1.split(":");
			String app1[]=app[1].split("\\$");
			float price1 = Float.parseFloat(app1[1]);
			
			String Item2 = returnElementIfPresent(secondXpathKey).getText();
			String app2[]=Item2.split(":");
			String app3[]=app2[1].split("\\$");
			float price2 = Float.parseFloat(app3[1]);
			
			float total = price1+price2;
			expText = Float.toString(total);
			expText =expText+"0";
			
			String Item3 = returnElementIfPresent("ORDERITEMSTOTAL_LABEL").getText();
			String app4[]=Item3.split(":");
			String app5[]=app4[1].split("\\$");
			Float price3 = Float.parseFloat(app5[1]);
			actText=Float.toString(price3);
			actText =actText+"0";
	
			if (actText.equals(expText)==true) {
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
			}
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	
	}
	
	public String VerifyPaginationAndClick(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPaginationAndClick ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify the pagination exists on the page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Verifying pagination is exists on the page");
		APP_LOGS.debug(": Verifying pagination is exists on the page");
		test.info(": Verifying pagination is exists on the page");
		try {
			Thread.sleep(3000);
			List<WebElement> offers = returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if (count > 0) 
			{
				System.out.println(": Pagination exists"); 
				APP_LOGS.debug(": Pagination exists");
				test.pass(": Pagination exists");
				returnElementIfPresent(firstXpathKey).click();
				Thread.sleep(5000);
			}
			else
			{
				
			}
		} catch (Exception e) {
			System.out.println("pagination" + e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
		}
		
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyPercentageOnSidebySidePage(String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPercentageOnSidebySidePage ()
		 * 
		 * @parameter: String firstXpathKey, String inputData)
		 * 
		 * @notes: Verify the percent decimal places on side by side offers page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		boolean bFlag = false;

		System.out.println(": Verifying percentage should be displaying upto two decimal places");
		APP_LOGS.debug(": Verifying percentage should be displaying upto two decimal places");
		test.info(": Verifying percentage should be displaying upto two decimal places");
		try {
			Thread.sleep(5000);
			List<WebElement> offers = returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if (count > 0) {
				for (int i = 0; i < count - 1; i++) {
					WebElement we = driver.findElement(By.xpath("//div[@class='MuiGrid-root width-85 text-center margin-top-10-px MuiGrid-item']//following-sibling::div[@class='loan-offer-comparison-card false']["+(i+1)+"]"));
					we.click();
					WebElement we1 = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div["+(i+3)+"]/div[2]/div[2]/div"));
					inputData = we1.getText().trim();
					String dec[] = inputData.split("\\.");
					String dd = dec[1];
					String ddd[] = dd.split("\\%");
					inputData = ddd[0];
					String regex = "^\\d{2}$";
					if (inputData.matches(regex)) {
						bFlag = true;
					}
				}
				if (bFlag == true) {
					System.out.println(": After the decimal point showing two decimal places");
					APP_LOGS.debug(": After the decimal point showing two decimal places");
					test.pass(": After the decimal point showing two decimal places");
				} else {
					captureScreenShot = true;
					System.out.println("After the decimal point it's not showing two decimal places");
					APP_LOGS.debug("After the decimal point it's not showing two decimal places");
					test.fail("After the decimal point it's not showing two decimal places");
					return "FAIL - After the decimal point it's not showing two decimal places";
				}
			} else {
				captureScreenShot = true;
				System.out.println(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				APP_LOGS.debug(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				test.fail(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				return "FAIL -" + count + ": Offers are not displayed on the page, Please refer screenshot";
			}
		} catch (Exception e) {
			System.out.println("Offers page" + e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyPercentageDecimalPlaces(String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPercentageDecimalPlaces ()
		 * 
		 * @parameter: String firstXpathKey, String inputData)
		 * 
		 * @notes: Verify the percent should be displayed up to two decimal places
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		System.out.println(": Verifying percentage should be displaying upto two decimal places");
		APP_LOGS.debug(": Verifying percentage should be displaying upto two decimal places");
		test.info(": Verifying percentage should be displaying upto two decimal places");
		try
		{
					String dd = null;
					String text = returnElementIfPresent(firstXpathKey).getText();
					String[] arrSplit_3 = text.split("\\.");
					if(inputData.equals("DoubleCheck")|| inputData.equals("Esign"))
					{
						dd = arrSplit_3[2];
					}
					else if(inputData.equals("AnnualPercent"))
					{
						dd = arrSplit_3[1];
					}
					String ddd[] = dd.split("\\%");
					text = ddd[0];
					String regex = "^\\d{2}$";
					if (text.matches(regex)) {
						System.out.println(": After the decimal point showing two decimal places");
						APP_LOGS.debug(": After the decimal point showing two decimal places");
						test.pass(": After the decimal point showing two decimal places");
					} else {
						captureScreenShot = true;
						System.out.println("After the decimal point it's not showing two decimal places");
						APP_LOGS.debug("After the decimal point it's not showing two decimal places");
						test.fail("After the decimal point it's not showing two decimal places");
						return "FAIL - After the decimal point it's not showing two decimal places";
					}
				
		}
		catch(Exception e)
		{
			System.out.println("Offers page"+e.getMessage());
			test.log(Status.ERROR, " ERROR : Not able to read --" + firstXpathKey);
			return "FAIL - Not able to read --" + firstXpathKey;
	     }
		test.pass("PASS");
		return "PASS";
	
	}

	public String VerifyPermalinkContent(String expPermalinkContent) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPermalinkContent ()
		 * 
		 * @parameter: String expPermalinkContent
		 * 
		 * @notes: Verifying the link which is present in URL
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {
			Thread.sleep(3000);
			String actURL = driver.getCurrentUrl();
			System.out.println(": Verifing Page URL:");
			APP_LOGS.debug(": Verifing Page URL:");
			test.info(": Verifing Page URL:");
			String[] actURLContent = actURL.split("/");
			actPermalinkContent = actURLContent[actURLContent.length - 1];
			if (expPermalinkContent.equalsIgnoreCase(actPermalinkContent)) {
				System.out.println(": Actual Permalink value is-> " + actPermalinkContent + " AND Expected is-> " + expPermalinkContent);
				APP_LOGS.debug(": Actual Permalink value is-> " + actPermalinkContent + " AND Expected is->" + expPermalinkContent);
				test.pass(": Actual Permalink value is-> " + actPermalinkContent + " AND Expected is->" + expPermalinkContent);

			} else {
				captureScreenShot = true;
				System.out.println("FAIL - Not Able to verify permalink content " + actPermalinkContent + "");
				APP_LOGS.debug("FAIL - Not Able to verify permalink content " + actPermalinkContent + "");
				test.fail("FAIL - Not Able to verify permalink content " + actPermalinkContent + "");
			}
		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println("FAIL - Not Able to verify actual permalink content " + actPermalinkContent + "");
			APP_LOGS.debug("FAIL - Not Able to verify actual permalink content " + actPermalinkContent + "");

		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyPostRequest(String inputData) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPostRequest ()
		 * 
		 * @parameter: String inputData
		 * 
		 * @notes: Verify the post request call
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		RestAssured.baseURI=inputData;
		Response response = null;
		boolean bFlag = false;
		int expStatusCode = 200;
		
		System.out.println(": Calling the API");
		APP_LOGS.debug(": Calling the API");
		test.info(": Calling the API");
		try
		{
			if(inputData.equals("https://api-qa.loanglide.com/api/v1/lenders/1111/service"))
			{
				response = RestAssured.given().contentType("application/json").post();
				bFlag = response.asString().contains("Processed payment job successfully");
				int actStatusCode =response.getStatusCode();  
				if(bFlag == true && actStatusCode == expStatusCode) 
				{ 
					System.out.println(": Status code and message is displaying as expected");
					APP_LOGS.debug(": Status code and message is displaying as expected");
					test.pass(": Status code and message is displaying as expected");
				} 
				else 
				{
					System.out.println(": Either status code or message is not displaying as expected");
					APP_LOGS.debug(": Either status code or message is not displaying as expected");
					test.fail(": Either status code or message is not displaying as expected");
					TestCaseFail();
				}
			}
			else
			{
				response = RestAssured.given().contentType("application/json").post();
				WaitTillElementAppears("LOANSERVICE_SUCCESS");
				bFlag = response.asString().contains("Processed loan servicing job successfully");
				int actStatusCode =response.getStatusCode();  
				if(bFlag == true && actStatusCode == expStatusCode) 
				{ 
					System.out.println(": Status code and message is displaying as expected");
					APP_LOGS.debug(": Status code and message is displaying as expected");
					test.pass(": Status code and message is displaying as expected");
				} 
				else 
				{
					System.out.println(": Either status code or message is not displaying as expected");
					APP_LOGS.debug(": Either status code or message is not displaying as expected");
					test.fail(": Either status code or message is not displaying as expected");
					TestCaseFail();
				}
			}
		}
		catch(Exception e)
		{
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to verify post request");
			return "FAIL - Not able to verify post request";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyPrefilledDataFromInputField(String firstXpathKey, String expText) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyPrefilledDataFromInputField ()
		 * 
		 * @parameter: String firstXpathKey, String expText
		 * 
		 * @notes: Verify the pre filled data from input field
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		try {
			expText = expText.trim();
			returnElementIfPresent(firstXpathKey).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			returnElementIfPresent(firstXpathKey).sendKeys(Keys.chord(Keys.CONTROL, "c"));
			Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
			String str = (String) transferable.getTransferData(DataFlavor.stringFlavor);
			actText = str;
	
			if (actText.compareTo(expText) == 0) {
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is->" + expText;
			}
	
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to get value from Input Field");
			return "FAIL - Not able to get value from Input Field";
		}
		test.pass("Pass");
		return "PASS";
	}
	 
	public String VerifyRejectedBO(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyRejectedBO ()
		 * 
		 * @parameter: String firstXpathkey, String inputData
		 * 
		 * @notes: Verify the rejected bo should be in the new bo list in admin portal
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		
		String objectIdentifierValue = "";
		String objectArray[] = null;

		System.out.println(": Verifying rejected business owner should be displayed in new bo list");
		APP_LOGS.debug(": Verifying rejected business owner should be displayed in new bo list");
		test.info(": Verifying rejected business owner should be displayed in new bo list");
		
		try
		{
			Thread.sleep(5000);
			String object = OR.getProperty(firstXpathKey);
			objectArray = object.split("__");
			objectIdentifierValue = objectArray[1].trim();
			objectIdentifierValue = objectIdentifierValue + "'" + businessName + "')]";
			int count =driver.findElements(By.xpath(objectIdentifierValue)).size();
			if(count == 1)
			{
				System.out.println(": Rejected business owner is displayed in new bo list");
				APP_LOGS.debug(": Rejected business owner is displayed in new bo list");
				test.pass(": Rejected business owner is displayed in new bo list");
				
			}
			else
			{
				captureScreenShot = true;
				System.out.println(": Rejected business owner is not displayed in new bo list");
				APP_LOGS.debug(": Rejected business owner is not displayed in new bo list");
				test.fail(": Rejected business owner is not displayed in new bo list");
				return "FAIL - Rejected business owner is not displayed in new bo list";
				
			}
			
		} 
		catch (Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify rejected bo in new bo list");
			return "FAIL - Not able to Verify rejected bo in new bo list";
		}
		
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyReportsStatusbyFilter(String firstXpathkey, String inputData) throws Exception {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyReportsbyStatus ()
		 * 
		 * @parameter: String firstXpathkey, String inputData
		 * 
		 * @notes: Verify the reports when user apply the status filter
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		
		highlight = false;
		captureScreenShot = false;
		boolean bflag = false;
		System.out.println(": Verifying " + inputData + " Reports");
		APP_LOGS.debug(": Verifying " + inputData + " Reports");
		test.info(": Verifying " + inputData + " Reports");
		try {
			Thread.sleep(2000);
			List<WebElement> data = returnElementsIfPresent(firstXpathkey);
			int count = data.size();
			if (count == 0) {
				captureScreenShot = true;
				System.out.println(": After Verifying " + inputData + " Reports, No search result is displayed");
				APP_LOGS.debug(": After Verifying " + inputData + " Reports, No search result is displayed");
				test.info(": After Verifying " + inputData + " Reports, No search result is displayed");
			} 
			else 
			{
					Thread.sleep(1000);
					System.out.println(": Verify " + inputData + " Reports, " + count + " search result is displayed");
					APP_LOGS.debug(": Verify " + inputData + " Reports, " + count + " search result is displayed");
					test.info(": Verify " + inputData + " Reports, " + count + " search result is displayed");
					for (int i = 0; i < count; i++)
					{
						String text = data.get(i).getText();
						if (text.contains(inputData))
						{
							bflag = true;
						} 
						else 
						{
							captureScreenShot = true;
							System.out.println(": Fail because of Other than " + inputData + " search result is displayed ");
							APP_LOGS.debug(": Fail because of Other than " + inputData + " search result is displayed ");
							test.info(": Fail because of Other than " + inputData + " search result is displayed ");
							return "Fail because of Other than " + inputData + " search result is displayed ";
						}
					}
				}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify " + inputData + " ColumnData");
			return "FAIL - Not able to Verify " + inputData + " ColumnData";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyServiceProviderQuotedAmountisAutoPopulated(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyServiceProviderQuotedAmountisAutoPopulated ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify the service provider quoted amount is auto populate.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		System.out.println(": Verifying that service provider quoted amount should be populated");
		APP_LOGS.debug(": Verifying that service provider quoted amount should be populated");
		test.info(": Verifying that service provider quoted amount should be populated");
		
		try {
				String actText = returnElementIfPresent(firstXpathKey).getAttribute("value");
				if(actText.isEmpty())
				{
					captureScreenShot = true;
					System.out.println(": Service provider quoted amount is not auto populated, Please refer screenshot ");
					APP_LOGS.debug(": Service provider quoted amount is not auto populated, Please refer screenshot ");
					test.fail(": Service provider quoted amount is not auto populated, Please refer screenshot ");
					return "FAIL - Service provider quoted amount is not auto populated, Please refer screenshot ";
				}
				else
				{
					System.out.println(": Service provider quoted amount is auto populated, Moving Ahead ");
					APP_LOGS.debug(": Service provider quoted amount is auto populated, Moving Ahead ");
					test.pass(": Service provider quoted amount is auto populated, Moving Ahead ");
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the amount is auto populated");
			return "FAIL - Not able to Verify the amount is auto populated";
		}
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifySigIn(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifySigIn ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: It verifies the user is logged successfully.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(" Verifying SignIn");
		APP_LOGS.debug(" Verifying SignIn");
		test.info(" Verifying SignIn");
		try 
		{
			int count = returnElementsIfPresent(firstXpathKey).size();
			if(count==0)
			{
				System.out.println(" User is logged successfully, Moving Ahead");
				APP_LOGS.debug(" User is logged successfully, Moving Ahead");
				test.pass(" User is logged successfully, Moving Ahead");
			}
			else
			{
				Thread.sleep(3000);
				highlight = true;
				captureScreenShot = true;
				System.out.println(" User is logged with invalid email or password, Please login with valid email and password");
				APP_LOGS.debug(" User is logged with invalid email or password, Please login with valid email and password");
				test.fail(" User is logged with invalid email or password, Please login with valid email and password");
				TestCaseFail();
			}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify SignIn");
			return "FAIL - Not able to Verify SignIn";
		}
	
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyTableData(String firstXpathKey, String secondXpathKey, String expText) {

		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTableData ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey, String expText
		 * 
		 * @notes: Performs the verification of the table data by getting column data from firstXpathKey and secondXpathKey and verify it against the expText or dataColVal.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		try {

			String actText = getColumnData(firstXpathKey, secondXpathKey);
			String[] inter = actText.split(",");
			for (int i = 0; i < inter.length; i++) {
				System.out.println("Values : " + inter[i]);
			}
			System.out.println("Total " + inter.length + " records found.");
			for (int i = 0; i < inter.length; i++) {
				if (inter[i].contains(expText)) {
					System.out.println(": Actual is-> " + inter[i] + " AND Expected is-> " + expText);
					APP_LOGS.debug(": Actual is-> " + inter[i] + " AND Expected is->" + expText);

				} else {
					highlight = true;
					System.out.println(": Actual is-> " + inter[i] + " AND Expected is-> " + expText);
					return "FAIL - Actual is-> " + inter[i] + " AND Expected is->" + expText;
				}
			}

		} catch (Exception ex) {
			System.out.println("Unable to match data with table data.");
			APP_LOGS.debug("Unable to match data with table data ");
			return "FAIL - Not able to match data with table data.";
		}
		return "PASS";
	}

	public String VerifyText(String firstXpathKey, String secondXpathKey, String expText) throws ParseException, InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyText ()
		 * 
		 * @parameter: String firstXpathKey, Optional=>String secondXpathKey, Optional=> String expText
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. Verification can be performed on the same page or on different pages. User can perform two different webelement's text comparison by passing argument as objectKeySecond.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		String objectIdentifierValue = "";
		String objectArray[] = null;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(" Verifying " + firstXpathKey + " Text on the Page");
		Thread.sleep(SYNC_WAIT);
		String regex = "[0-9].[0-9]";
		if (expText.matches(regex)) {
			NumberFormat nf = NumberFormat.getInstance();
			Number number = nf.parse(expText);
			long lnputValue = number.longValue();
			expText = String.valueOf(lnputValue);
		}
		if (expText.isEmpty()) {
			System.out.println(": Expected Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Expected Data is Empty, taking this value from Hashmap");
			test.info(" Expected Data is Empty, taking this value from Hashmap");
			expText = (String) getTextOrValues.get(secondXpathKey);
			if (expText == null) {
				System.out.println(": No Expected Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				expText = returnElementIfPresent(secondXpathKey).getText().trim();
			}
			if (expText.contains("Dental Loan of $")) {
				String[] parts = expText.split(" ");
				String loan = parts[3].trim();
				expText = loan;
			}
		}
		try {
			if (firstXpathKey.equals("DENTALLONE_TITLE") || firstXpathKey.equals("EDIT_SSN_LABEL")) {
				unhighlightElement(returnElementIfPresent(firstXpathKey));
				actText = returnElementIfPresent(firstXpathKey).getText().trim();
				if (actText.contains("Dental Loan of $")) {
					String[] parts = actText.split(" ");
					String loan = parts[3].trim();
					actText = loan;
				} else if (actText.contains("XXX-XX-")) {
					String[] parts = actText.split("-");
					String ssn = parts[2].trim();
					actText = ssn;
				}
			}
			
			else if(firstXpathKey.equals("NACHA_TRANS1_TEXT"))
			{
				
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + loanId + "']/..//following-sibling::td[last()]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("NACHA_TRANS3_TEXT"))
			{
				
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + loanId + "']/..//following-sibling::td[2]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("NACHA_TRANS2_TEXT"))
			{
				
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + loanId + "']/..//following-sibling::td[3]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("DIR_BOREGSTATUS"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + businessName + "')]//following-sibling::td[7]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("TRANSACTION_STATUS")|| firstXpathKey.equals("NACHA_TRANSSTATUS_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + loanId + "']//following-sibling::td[last()]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("BRFCODE_NAME"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	refCode + "']";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("BRFCODE_DESCRIPTION"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	refCode + "']//following-sibling::td[1]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("BRCODE_ISACTIVE"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	refCode + "']//following-sibling::td[2]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("RESOURCE_NAME_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("RESOURCE_DESC_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']//following-sibling::td[1]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("RESOURCE_PURPOSE_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']//following-sibling::td[2]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("RESOURCE_CATEGORY_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']//following-sibling::td[3]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("RESOURCE_ACTIVESTATUS_TEXT"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue + "'" + 	resourceName + "']//following-sibling::td[5]";
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else if(firstXpathKey.equals("EXITBO_BUSINESSNAME"))
			{
				String object = OR.getProperty(firstXpathKey);
				objectArray = object.split("__");
				objectIdentifierValue = objectArray[1].trim();
				objectIdentifierValue = objectIdentifierValue  +"'" + 	businessName + "']";
				System.out.println("Business Name ="+objectIdentifierValue);
				actText = driver.findElement(By.xpath(objectIdentifierValue)).getText();
			}
			else 
			{
				actText = returnElementIfPresent(firstXpathKey).getText().trim();
			}
			
			if(secondXpathKey.equals("MERCHANT_EMAILADDRESS_TEXTFIELD"))
			{
				expText = expText.toLowerCase().trim();
			}
			else
			{
				expText = expText.trim();
			}
			if (actText.equals(expText)==true) {
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, " ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyTextAttributeValue(String firstXpathKey, String secondXpathKey, String expText) throws ParseException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTextAttributeValue ()
		 * 
		 * @parameter: String firstXpathKey, Optional=>String secondXpathKey, Optional=> String expText
		 * 
		 * @notes: Verifies the Actual attribute value as compared to the Expected attribute value. Verification can be performed on the same page or on different pages. User can perform two different webelement's text comparision by passing argument as objectKeySecond. In this it is not necessary
		 * expText should have as a whole it uses contains function.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(" Verifying " + firstXpathKey + " Text on the Page");
	
		if (expText.isEmpty()) {
			System.out.println(": Expected Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Expected Data is Empty, taking this value from Hashmap");
			test.info(" Expected Data is Empty, taking this value from Hashmap");
			expText = (String) getTextOrValues.get(secondXpathKey);
			if(expText.contains("Auto"))
			{
				System.out.println("Hi");
				expText = expText.toLowerCase();
			}
			if (expText == null) {
				System.out.println(": No Expected Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				expText = returnElementIfPresent(secondXpathKey).getText().trim();
			}
		}
		else
		{
			expText = expText.trim();
		}
		
		try {
			getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getAttribute("value"));
			actText = getTextOrValues.get(firstXpathKey).toString();
			actText = actText.trim();
			if (actText.contains(expText) == true) {
				System.out.println(": Actual is->  " + actText + " AND Expected is->  " + expText);
				APP_LOGS.debug(": Actual is->  " + actText + " AND Expected is->  " + expText);
				test.pass(" Actual is->  " + actText + " AND Expected is->  " + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is->  " + actText + " AND Expected is->  " + expText);
				test.fail(" FAIL : Actual is->  " + actText + " AND Expected is->  " + expText);
				return "FAIL - Actual is->  " + actText + " AND Expected is->  " + expText;
			}
	
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyTextDDTdata(String firstXpathKey, String secondXpathKey, String expText) throws ParseException, InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTextDDTdata ()
		 * 
		 * @parameter: String firstXpathKey, Optional=>String secondXpathKey, Optional=> String expText
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. Verification can be performed on the same page or on different pages for DDT.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(": Verifying " + firstXpathKey + " Text on the Page");

		try {
			getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getText());
			actText = getTextOrValues.get(firstXpathKey).toString().trim();
			expText = expText.trim();
			if (actText.compareTo(expText)==0) {
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(": Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.fail(": Actual is-> " + actText + " AND Expected is->" + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is->" + expText;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		return "PASS";
	}

	public String VerifyTextContains(String firstXpathKey, String secondXpathKey, String expText) throws ParseException, InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTextContains ()
		 * 
		 * @parameter: String firstXpathKey, Optional=>String secondXpathKey, Optional=> String expText
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. Verification can be performed on the same page or on different pages. User can perform two different webelement's text comparision by passing argument as objectKeySecond. In this it is not necessary
		 * expText should have as a whole it uses contains function.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(" Verifying " + firstXpathKey + " Text on the Page");
		Thread.sleep(SYNC_WAIT);
	
		String regex = "[0-9].[0-9]";
		if (expText.matches(regex)) {
			NumberFormat nf = NumberFormat.getInstance();
			Number number = nf.parse(expText);
			long lnputValue = number.longValue();
			expText = String.valueOf(lnputValue);
		}
		if (expText.isEmpty()) {
			System.out.println(": Expected Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Expected Data is Empty, taking this value from Hashmap");
			test.info(" Expected Data is Empty, taking this value from Hashmap");
			if(secondXpathKey.equals("EDITBANK_ACCNUMBER_TXTFIELD"))
			{
				expText = (String) getTextOrValues.get("EDITBANK_ACCNUMBER_TXTFIELD");
				if (expText.length() >= 20) 
				{
					expText = expText.substring(expText.length() - 4);
				} 
				else if (expText.length() > 19) 
				{
					expText = expText.substring(expText.length() - 2);
				} 
				else
				{
					expText = expText;
				}
			}
			else
			{
				expText = (String) getTextOrValues.get(secondXpathKey);
			}
			
			if (expText == null) {
				System.out.println(": No Expected Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				test.info(" No Expected data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				expText = returnElementIfPresent(secondXpathKey).getText().trim();
				
			} 
		}
		
		else if(expText.equals("https://apply-qa.loanglide.com/business/"))
		{
			expText = expText+homeURL;
		}
		else if(expText.equals("to-date of"))
		{
			expText = expText+" $"+requestedAmount;
		}
		
		try {
			
			if(firstXpathKey.equals("LOANPREAPPROVED_LABEL"))
			{
				actText = returnElementIfPresent("LOANPREAPPROVED_LABEL").getText().trim();
				String text[] = actText.split("\\$");
				actText = text[0].trim();
			}
			else if(firstXpathKey.equals("BO_ACCOUNT_LABEL"))
			{
				actText =  returnElementIfPresent(firstXpathKey).getText();
				if (actText.length() > 21) 
				{
					actText = actText.substring(actText.length() - 4);
				} 
				else if (actText.length() > 19) 
				{
					actText = actText.substring(actText.length() - 2);
				} 
				else
				{
					actText = actText;
				}
			}
			
			else if(firstXpathKey.equals("ESIGN_OFFER_LABEL"))
			{
				actText =  returnElementIfPresent(firstXpathKey).getText();
				if(actText.contains("15.90% APR") || actText.contains("14.90% APR") || actText.contains("7.99% APR"))
				{
					String sr[] = actText.split("for");	  
					actText = sr[1].trim();
					String word=""; 
				    for(int i=0; i<actText.length(); i++) 
				    {
				    	word = word+actText.charAt(i); 
				    }
				    actText = word;
					String str1[] = actText.split(" ");
					actText = sr[0]+"for "+str1[0]+" "+str1[2];
				}
			}
			
			else
			{
					actText = returnElementIfPresent(firstXpathKey).getText().trim();	
			}
			expText = expText.trim();
			if (actText.contains(expText)) {
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.pass(": Actual is-> " + actText + " AND Expected is-> " + expText);
				Thread.sleep(3000);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is-> " + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is-> " + expText);
				Thread.sleep(3000);
				return "FAIL - Actual is-> " + actText + " AND Expected is-> " + expText;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			System.out.println(e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyTextContainsDot(String firstXpathKey)
	{
		
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTextContainsDot ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify that text should not display dot(.)
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Verifying that text should not contains dot(.)");
		APP_LOGS.debug(": Verifying that text should not contains dot(.)");
		test.info(": Verifying that text should not contains dot(.)");
		try {
				String periods = returnElementIfPresent(firstXpathKey).getText();
				if(periods.contains("."))
				{
					captureScreenShot = true;
					System.out.println(": Text contains dot(.), Please refer screenshot ");
					APP_LOGS.debug(": Text contains dot(.), Please refer screenshot ");
					test.fail(": Text contains dot(.), Please refer screenshot ");
					return ": FAIL - Text contains dot(.), Please refer screenshot ";
				}
				else
				{
					System.out.println(": Text not contains dot(.), Moving Ahead ");
					APP_LOGS.debug(": Text not contains dot(.), Moving Ahead ");
					test.pass(": Text not contains dot(.), Moving Ahead ");
				}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the amount is auto populated");
			return "FAIL - Not able to Verify the amount is auto populated";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyTitle(String actTitle, String expTitle) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTitle ()
		 * 
		 * @parameter: String actTitle & String expTitle
		 * 
		 * @notes: Verifies the Actual Web Page Title as compared to the Expected Web Page title. Verification is performed on the same Web page.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Page Title");
		APP_LOGS.debug(": Verifying Page Title");
		test.info(" Verifying Page Title");
		try {
			expTitle = expTitle.replace("_", ",");
			actTitle = driver.getTitle();
			if (actTitle.compareTo(expTitle) == 0) {
				System.out.println(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				APP_LOGS.debug(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				test.pass(" Actual is-> " + actTitle + " AND Expected is->" + expTitle);
			} else {
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				test.fail(" FAIL : Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				return "FAIL - Actual is-> " + actTitle + " AND Expected is->" + expTitle;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to get title");
			return "FAIL - Not able to get title";
		}
		test.pass("Pass");
		return "PASS";
	}

	public String VerifyToolTip(String firstXpathKey, String expText) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyToolTip ()
		 * 
		 * @parameter: String firstXpathKey, String expText
		 * 
		 * @notes: Hover mouse hover given Object, link, Hyperlink, selections or buttons of a web page and get the tooltip from the element and verifies it with expText.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Performing Mouse hover on " + firstXpathKey);
		APP_LOGS.debug(": Performing Mouse hover on " + firstXpathKey);
		test.info(" Performing Mouse hover on " + firstXpathKey);
		try {
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement root = returnElementIfPresent(firstXpathKey);
			act.moveToElement(root).build().perform();
			Thread.sleep(2000);
			String actText = root.getText();

			if (actText.contains(expText)) {
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				APP_LOGS.debug(": Actual is-> " + actTitle + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actTitle + " AND Expected is->" + expText);
			} else {
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is->" + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is->" + expText;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to get tool tip text");
			return "FAIL - Not able to get tool tip text";
		}
		test.pass("Pass");
		return "PASS";

	}

	public String VerifyTextDDTdataContains(String firstXpathKey, String secondXpathKey, String expText) throws ParseException, InterruptedException {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTextDDTdata ()
		 * 
		 * @parameter: String firstXpathKey, Optional=>String secondXpathKey, Optional=> String expText
		 * 
		 * @notes: Verifies the Actual Text as compared to the Expected Text. Verification can be performed on the same page or on different pages for DDT.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying " + firstXpathKey + " Text on the Page");
		APP_LOGS.debug(": Verifying " + firstXpathKey + " Text on the Page");
		test.info(" Verifying " + firstXpathKey + " Text on the Page");

		try {
			getTextOrValues.put(firstXpathKey, returnElementIfPresent(firstXpathKey).getText());
			actText = getTextOrValues.get(firstXpathKey).toString();
			actText = actText.trim();
			expText = expText.trim();

			if (actText.contains(expText)) {
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				APP_LOGS.debug(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.pass(" Actual is-> " + actText + " AND Expected is->" + expText);
			} else {
				globalExpText = expText;
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actText + " AND Expected is->" + expText);
				test.fail(" FAIL : Actual is-> " + actText + " AND Expected is->" + expText);
				return "FAIL - Actual is-> " + actText + " AND Expected is->" + expText;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			test.log(Status.ERROR, "ERROR : Not able to read text--" + firstXpathKey);
			return "FAIL - Not able to read text--" + firstXpathKey;
		}
		test.pass("Pass");
		return "PASS";
	}
	
	public String VerifyTitleContains(String expTitle) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTitleContains ()
		 * 
		 * @parameter: String expTitle
		 * 
		 * @notes: Verifies the Expected Web Page title.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Page Title");
		APP_LOGS.debug(": Verifying Page Title");
		try {
			expTitle = expTitle.replace("_", ",");
			expTitle.trim();
			actTitle = driver.getTitle();
			if (actTitle.contains(expTitle)) {
				System.out.println(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				APP_LOGS.debug(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
			} else {
				captureScreenShot = true;
				System.out.println(": Actual is-> " + actTitle + " AND Expected is->" + expTitle);
				return "FAIL - Actual is-> " + actTitle + " AND Expected is->" + expTitle;
			}
		} catch (Exception e) {
			captureScreenShot = true;
			return "FAIL - Not able to get title";
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyTransactions(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTransactions ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: It verifies the transactions.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Transactions");
		APP_LOGS.debug(": Verifying Transactions");
		test.info(": Verifying Transactions");
		try 
		{
			Thread.sleep(3000);
			int trans1 = returnElementsIfPresent(firstXpathKey).size();
			if(trans1 >= 1 && trans1 <= 5)
			{
				System.out.println(": Dashboard is displaying transactions");
				APP_LOGS.debug(": Dashboard is displaying transactions");
				test.info(": Dashboard is displaying transactions");
				
				returnElementIfPresent(secondXpathKey).click();
				Thread.sleep(3000);
				int trans2 = returnElementsIfPresent(firstXpathKey).size();
				
				if(trans2 >= 5 && trans2 <=10)
				{
					System.out.println(": Dashboard is displaying 5 or more transactions");
					APP_LOGS.debug(": Dashboard is displaying 5 or more transactions");
					test.info(": Dashboard is displaying 5 or more transactions");
				}
				else
				{
					highlight = true;
					captureScreenShot = true;
					System.out.println(": Dashboard is displaying 5 and less than 10 transactions");
					APP_LOGS.debug(": Dashboard is displaying 5 and less than 10 transactions");
					test.fail(": Dashboard is displaying 5 and less than 10 transactions");
				}
			}
			else
			{
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Dashboard is not displaying transactions");
				APP_LOGS.debug(": Dashboard is not displaying transactions");
				test.fail(": Dashboard is not displaying  transactions");
			}
		}
		catch (Exception e) {
			captureScreenShot = true;
			System.out.println(": " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Not able to Verify the boxes having different value");
			return "FAIL - Not able to Verify the boxes having different value";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyTwoDecimalLength(String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyTwoDecimalLength ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Verify two decimal places should be displayed offer page as well as on Side by Side Comparison
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */

		try {
			if(inputData.equals("SideBySide"))
			{
				inputData = returnElementIfPresent(firstXpathKey).getText().trim();
				inputData = inputData.substring(0, 7);
			} else {
				inputData = returnElementIfPresent(firstXpathKey).getText().trim();
			}
			String dec[] = inputData.split("\\$");
			inputData = dec[1];
			String dec1[] = inputData.split("\\.");
			inputData = dec1[1];
			System.out.println(" InputData ="+inputData);
			String regex = "^\\d{2}$";
			if (inputData.matches(regex)) {
				System.out.println(": After the decimal point showing two decimal places");
				APP_LOGS.debug(": After the decimal point showing two decimal places");
				test.pass(": After the decimal point showing two decimal places");
			} else {
				captureScreenShot = true;
				System.out.println("After the decimal point it's not showing two decimal places");
				APP_LOGS.debug("After the decimal point it's not showing two decimal places");
				test.fail("After the decimal point it's not showing two decimal places");
				return "FAIL - After the decimal point it's not showing two decimal places";
			}
		} catch (Exception e) {
			captureScreenShot = true;
			e.printStackTrace();
			return "FAIL - Not able to verify two decimal places length";
		}
		test.pass("PASS");
	return "PASS";
	}

	public String VerifyDecimalLengthSidebySidepage(String firstXpathKey, String inputData) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyDecimalLengthSidebySidepage ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: Verify two decimal places should be displayed offer page as
		 * well as on Side by Side Comparison
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got
		 * executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		boolean bFlag = false;
		try {
			List<WebElement> offers = returnElementsIfPresent(firstXpathKey);
			int count = offers.size();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					WebElement we = driver.findElement(By.xpath("//div[@class='MuiGrid-root width-85 text-center margin-top-10-px MuiGrid-item']//following-sibling::div[@class='loan-offer-comparison-card false']["+(i+1)+"]"));
					we.click();
					WebElement we1 = driver.findElement(By.xpath("//div[@id='root']/div/div/div[2]/div[1]/div/div[2]/div["+(i+1)+"]/div[2]/div[1]/div[2]"));
					inputData = we1.getText().trim();
					String str[] = inputData.split("\\n");
					inputData = str[0];
					String dec[] = inputData.split("\\$");
					inputData = dec[1];
					String abc[] = inputData.split("\\.");
					inputData = abc[1];
					String regex = "^\\d{2}$";
					if (inputData.matches(regex)) {
						bFlag = true;
					}
				}
				if (bFlag == true) {
					System.out.println(": After the decimal point showing two decimal places");
					APP_LOGS.debug(": After the decimal point showing two decimal places");
					test.pass(": After the decimal point showing two decimal places");
				} else {
					captureScreenShot = true;
					System.out.println("After the decimal point it's not showing two decimal places");
					APP_LOGS.debug("After the decimal point it's not showing two decimal places");
					test.fail("After the decimal point it's not showing two decimal places");
					return "FAIL - After the decimal point it's not showing two decimal places";
				}

			} else {
				captureScreenShot = true;
				System.out.println(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				APP_LOGS.debug(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				test.fail(": " + count + ": Offers are not displayed on the page, Please refer screenshot");
				return "FAIL -" + count + ": Offers are not displayed on the page, Please refer screenshot";
			}
		} catch (Exception e) {
			captureScreenShot = true;
			e.printStackTrace();
			return "FAIL - Not able to verify two decimal places length";
		}
		test.pass("PASS");
		return "PASS";
	}
	
	public String VerifyUpgradeOffersCalculation(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyUpgradeOffersCalculation ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify Upgrade offers calculation on direct finance offers
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Upgrade offers calculation on offers page");
		APP_LOGS.debug(": Verifying Upgrade offers calculation on offers page");
		test.info(": Verifying Upgrade offers calculation on offers page");
		try {
			Thread.sleep(3000);
			String Amount = returnElementIfPresent("UPGRADE_FUNDSRECEIVED_LABEL").getText();
			Amount = Amount.replace("$", "");
			Amount = Amount .replace(",", "");
			int  LoanAmt = Integer.parseInt(Amount);
			String Amt = returnElementIfPresent(firstXpathKey).getText();
			Amt = Amt.replace("$", "");
			Amt = Amt .replace(",", "");
			int  loanAmmt = Integer.parseInt(Amt);
			String Fee = returnElementIfPresent(secondXpathKey).getText();
			Fee = Fee.replace("$", "");
			Fee = Fee .replace(",", "");
			int  orgFee = Integer.parseInt(Fee);
			int funds = loanAmmt - orgFee;
			
			if (LoanAmt == funds) {
				System.out.println(": Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				APP_LOGS.debug(": Actual is-> " + LoanAmt + " AND Expected is->" + funds);
				test.pass(" Actual is-> " + LoanAmt + " AND Expected is->" + funds);
			} else {
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				test.fail(" FAIL : Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				return "FAIL - Actual is-> " + LoanAmt + " AND Expected is-> " + funds;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyZiboUpgradeOffersCalculation(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyZiboUpgradeOffersCalculation ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify Upgrade offers calculation on direct finance offers
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying Upgrade offers calculation on offers page");
		APP_LOGS.debug(": Verifying Upgrade offers calculation on offers page");
		test.info(": Verifying Upgrade offers calculation on offers page");
		try {
			Thread.sleep(3000);
			String Amount = returnElementIfPresent("DIRECTUPG_FNDSRCVD_LABEL").getText();
			Amount = Amount.replace("$", "");
			Amount = Amount .replace(",", "");
			int  LoanAmt = Integer.parseInt(Amount);
			String Amt = returnElementIfPresent(firstXpathKey).getText();
			Amt = Amt.replace("$", "");
			Amt = Amt .replace(",", "");
			int  loanAmmt = Integer.parseInt(Amt);
			String Fee = returnElementIfPresent(secondXpathKey).getText();
			Fee = Fee.replace("$", "");
			Fee = Fee .replace(",", "");
			int  orgFee = Integer.parseInt(Fee);
			int funds = loanAmmt - orgFee;
			
			if (LoanAmt == funds) {
				System.out.println(": Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				APP_LOGS.debug(": Actual is-> " + LoanAmt + " AND Expected is->" + funds);
				test.pass(" Actual is-> " + LoanAmt + " AND Expected is->" + funds);
			} else {
				highlight = true;
				captureScreenShot = true;
				System.out.println(": Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				test.fail(" FAIL : Actual is-> " + LoanAmt + " AND Expected is-> " + funds);
				return "FAIL - Actual is-> " + LoanAmt + " AND Expected is-> " + funds;
			}
		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyUpgradeOffers(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyUpgradeOffers ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify upgrade offers on direct finance offers page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying upgrade offers on direct finance offers page");
		APP_LOGS.debug(": Verifying upgrade offers on direct finance offers page");
		test.info(": Verifying upgrade offers on direct finance offers page");
		try 
		{
			Thread.sleep(3000);
			int count = returnElementsIfPresent(firstXpathKey).size();
			
			if(count > 0)
			{
				System.out.println(": " +count+ " upgrade offers on direct finance offers page");
				APP_LOGS.debug(": " +count+ " upgrade offers on direct finance offers page");
				test.info(": " +count+ " upgrade offers on direct finance offers page");	
				
				//	returnElementIfPresent(firstXpathKey).click();
					GetText(secondXpathKey);
					VerifyText("UPPGRADE_LOANAMT_LABEL","OFFERS_LOANAMOUNT_LABEL","");
					VerifyUpgradeOffersCalculation("UPPGRADE_LOANAMT_LABEL","UPGRADE_ORIGINATIONFEE_LABEL");
					VerifyElementPresent("UPGRADE_MONTHLYAMOUNT_TEXT","TRUE");
		//			VerifyElementPresent("UPGRADE_MONTHS_TEXT","TRUE");
					VerifyElementPresent("UPGRADE_PERCENTAGEAPR_LABEL","TRUE");
				
			}
			else
			{
				captureScreenShot = true;
				System.out.println("upgrade offers are not displayed on direct finance offers page");
				APP_LOGS.debug("upgrade offers are not displayed on direct finance offers page");
				test.info("upgrade offers are not displayed on direct finance offers page");
			}
			
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyZiboUpgradeOffers(String firstXpathKey, String secondXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyZiboUpgradeOffers ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathKey
		 * 
		 * @notes: Verify upgrade offers on direct finance offers page
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying upgrade offers on direct finance offers page");
		APP_LOGS.debug(": Verifying upgrade offers on direct finance offers page");
		test.info(": Verifying upgrade offers on direct finance offers page");
		try 
		{
			Thread.sleep(5000);
			int count = returnElementsIfPresent(firstXpathKey).size();
			
			if(count > 0)
			{
				System.out.println(": " +count+ " upgrade offers on direct finance offers page");
				APP_LOGS.debug(": " +count+ " upgrade offers on direct finance offers page");
				test.info(": " +count+ " upgrade offers on direct finance offers page");	
				
					GetText(secondXpathKey);
			//		VerifyText("DIFI_LOANAMT_LABEL","LOANAMOUNT_TEXTFIELD","");
					VerifyZiboUpgradeOffersCalculation("DIRECTUPG_UPLOANAMT_LABEL","DIRECTUPG_ORGFEE_LABEL");
					VerifyElementPresent("DIRECTUPG_MONTHS_LABEL","TRUE");
					VerifyElementPresent("DIRECTUPG_APR_LABEL","TRUE");
				
			}
			else
			{
				captureScreenShot = true;
				System.out.println("upgrade offers are not displayed on direct finance offers page");
				APP_LOGS.debug("upgrade offers are not displayed on direct finance offers page");
				test.info("upgrade offers are not displayed on direct finance offers page");
			}
			
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getMessage());
			return "FAIL - Not able to Verify offers";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyUserAcessNotAvailableFromDropDown(String firstXpathKey, String secondXpathkey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyUserAcessNotAvailableFromDropDown ()
		 * 
		 * @parameter: String firstXpathKey, String secondXpathkey, String inputData
		 * 
		 * @notes: Verify User should not available in drop down as mentioned in inputData.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		highlight = false;
		captureScreenShot = false;
		Boolean found = false;
		if (inputData.isEmpty()) {
			System.out.println(": Test Data is Empty, taking this value from Hashmap");
			APP_LOGS.debug(": Test Data is Empty, taking this value from Hashmap");
			test.info(" Test Data is Empty, taking this value from Hashmap");
			inputData = (String) getTextOrValues.get(secondXpathkey);
			System.out.println(": expText " + inputData);
			APP_LOGS.debug(": expText " + inputData);
			test.info(": expText " + inputData);
			if (inputData == null) {
				System.out.println(": No Test Data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				APP_LOGS.debug(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				test.info(": No Test data present in Hashmap, taking this value from secondXpathKey object of Webpage");
				inputData = returnElementIfPresent(secondXpathkey).getText().trim();
			}
		}
		System.out.println(": Verifying " + inputData + " option is available from dropdown");
		APP_LOGS.debug(": Verifying " + inputData + " option is available from dropdown");
		test.info(": Verifying " + inputData + " option is available from dropdown");
		try {
			returnElementIfPresent(firstXpathKey).click();
			Thread.sleep(3000);
			List<WebElement> userAccess = returnElementsIfPresent("LIST_OPTIONS");
			int count = userAccess.size();
			for (int i = 0; i < count; i++) {
				String text = userAccess.get(i).getText();
				if (text.equals(inputData)) {
					found = true;
					break;
				}
			}
			if (found) {
				captureScreenShot = true;
				System.out.println(": User is avialble from drop down, Please refer screenshot");
				APP_LOGS.debug(": User is avialble from drop down, Please refer screenshot");
				test.fail(": User is avialble from drop down, Please refer screenshot");
				return "FAIL - User is avialble from drop down, Please refer screenshot";
			} else {
				System.out.println(": User is not avialble from drop down, Moving Ahead");
				APP_LOGS.debug(": User is not avialble from drop down, Moving Ahead");
				test.pass(": User is not avialble from drop down, Moving Ahead");	
			}
		} catch (Exception e) {
			captureScreenShot = true;
			e.printStackTrace();
			test.log(Status.ERROR, "ERROR: Not able to verify user access not available in drop down");
			return "FAIL - Not able to verify user access not available in drop down";
		}
		test.pass("PASS");
		return "PASS";
	}

	public String VerifyUserIsDeleted(String firstXpathKey)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyUserIsDeleted ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify User is deleted, if user is not delete then delete it.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying User is deleted on all users Page");
		APP_LOGS.debug(": Verifying User is deleted on all users Page");
		test.info(": Verifying User is deleted on all users Page");
		try {
			Thread.sleep(5000);
			List<WebElement> template = returnElementsIfPresent(firstXpathKey);
			int count = template.size();
			Thread.sleep(2000);
			if (count > 0) {
				System.out.println(": user is still displayed in all users Page, delete the user");
				APP_LOGS.debug(": user is still displayed in all users Page, delete the user");
				test.info(": user is still displayed in all users Page, delete the user");
				Click("ALLUSERS_ACTIONS_BUTTON");
				Thread.sleep(2000);
				Click("REMOVEUSER_LIST");
				Thread.sleep(2000);
				Click("USER_REALLYSURE");
			} else {
				System.out.println(": User is deleted successfully, Moving Ahead");
				APP_LOGS.debug(": User is deleted successfully, Moving Ahead");
				test.info(": User is deleted successfully, Moving Ahead");
			}
		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Verify user is deleted";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	
	public String VerifyZoomOut()
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: VerifyUserIsDeleted ()
		 * 
		 * @parameter: String firstXpathKey
		 * 
		 * @notes: Verify User is deleted, if user is not delete then delete it.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
	
		highlight = false;
		captureScreenShot = false;
		System.out.println(": Verifying User is deleted on all users Page");
		APP_LOGS.debug(": Verifying User is deleted on all users Page");
		test.info(": Verifying User is deleted on all users Page");
		try {
			((JavascriptExecutor)driver).executeScript("document.body.style.zoom='90%';");

		} 
		catch (Exception e) 
		{
			System.out.println(": Exception: " + e.getMessage());
			test.log(Status.ERROR, "ERROR : Alert Exception: " + e.getLocalizedMessage());
			return "FAIL - Not able to Verify user is deleted";
		}
		
	test.pass("PASS");
	return "PASS";
	}
	

	public String Wait(String stepWaitTime) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Wait ()
		 * 
		 * @parameter: String WaitTime
		 * 
		 * @notes: Wait for a user defined specific time to load the page for ex: 20 seconds. String "WaitTime" captures the value from the module xlsx file.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		try {

			System.out.println(": Waiting for Page to load.");
			APP_LOGS.debug(": Waiting for Page to load.");
			test.info(" Waiting for Page to load.");
			stepWaitTime = stepWaitTime.trim();
			if (stepWaitTime.equals("SYNC_WAIT") || stepWaitTime.equals("SMALL_WAIT") || stepWaitTime.equals("MID_WAIT") || stepWaitTime.equals("LONG_WAIT")) {
				if (stepWaitTime.equals("SYNC_WAIT")) {
					Thread.sleep(Constants.SYNC_WAIT);
				} else if (stepWaitTime.equals("SMALL_WAIT")) {
					Thread.sleep(Constants.SMALL_WAIT);
				} else if (stepWaitTime.equals("MID_WAIT")) {
					Thread.sleep(Constants.MID_WAIT);
				} else if (stepWaitTime.equals("LONG_WAIT")) {
					Thread.sleep(Constants.LONG_WAIT);
				}
			} else {
				APP_LOGS.debug(": FAIL - Please check the Wait data in Test Case sheet. It can be SYNC_WAIT,SMALL_WAIT,MID_WAIT or LONG_WAIT BUT written as: " + stepWaitTime);
				test.fail(" FAIL : - Please check the Wait data in Test Case sheet. It can be SYNC_WAIT,SMALL_WAIT,MID_WAIT or LONG_WAIT BUT written as: " + stepWaitTime);
				return (": FAIL - Please check the Wait data in Test Case sheet. It can be SYNC_WAIT,SMALL_WAIT,MID_WAIT or LONG_WAIT BUT written as: " + stepWaitTime);
			}

		} catch (Exception e) {
			APP_LOGS.debug(": FAIL - Not able to wait for " + stepWaitTime + " Seconds to load the page");
			test.log(Status.ERROR, "ERROR : Not able to wait for " + stepWaitTime + " Seconds to load the page");
			return ("FAIL - Not able to wait for " + stepWaitTime + " Seconds to load the page");
		}
		test.pass("Pass");
		return "PASS";
	}

	public String WaitTillElementAppears(String ObjectIdentifier) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: WaitTillElementAppears ()
		 * 
		 * @parameter: String ObjectIdentifier
		 * 
		 * @notes: Wait for a element appears on the page for ex. 60 seconds.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		try {
			highlight = false;
			captureScreenShot = false;
			int i = 0;
			int expWaitTime = 90;
			System.out.println(": Waiting for " + ObjectIdentifier + " element to Appear.");
			APP_LOGS.debug(": Waiting for " + ObjectIdentifier + " element to Appear.");
			test.info(" Waiting for " + ObjectIdentifier + " element to Appear.");
			String objectIdentifierValue = "";
			String objectArray[] = null;
			String object = OR.getProperty(ObjectIdentifier);
			objectArray = object.split("__");
			objectIdentifierValue = objectArray[1].trim();
			System.out.println(": Waiting for Max " + expWaitTime + " seconds to Appear " + ObjectIdentifier + " Element which may NOT Present on Page");
			APP_LOGS.debug(": Waiting for Max " + expWaitTime + " seconds to Appear " + ObjectIdentifier + " Element which may NOT Present on Page");
			test.info(" Waiting for Max " + expWaitTime + " seconds to Appear " + ObjectIdentifier + " Element which may NOT Present on Page");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			while (isElementPresentBy(By.xpath(objectIdentifierValue)) == false) {
				if (i <= expWaitTime) {
					System.out.println(": " + ObjectIdentifier + " Element is currently NOT Present on Page. Going to check again after 1 second.");
					APP_LOGS.debug(": " + ObjectIdentifier + " Element is currently NOT Present on Page. Going to check again after 1 second.");
					test.info(" " + ObjectIdentifier + " Element is currently NOT Present on Page. Going to check again after 1 second.");
					Thread.sleep(1000);
					i++;
				} else {
					System.out.println(": Element not loaded in " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					APP_LOGS.debug(": Element not loaded in " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					test.fail(" FAIL : Element not loaded in " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					captureScreenShot = true;
					return ("FAIL - Element not loaded in " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
				}
			}
			System.out.println(": " + ObjectIdentifier + " Element is Now Present on Page, Moving Ahead.");
			APP_LOGS.debug(": " + ObjectIdentifier + " Element is Now Present on Page, Moving Ahead");
			test.info(" " + ObjectIdentifier + " Element is Now Present on Page, Moving Ahead");
			driver.manage().timeouts().implicitlyWait(CONFIG_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		} catch (Exception e) {
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to wait Till " + ObjectIdentifier + " Element Appears on Page. Please see the screenshot for more details.");
			test.log(Status.ERROR, "ERROR : Not able to wait Till " + ObjectIdentifier + " Element Appears on Page. Please see the screenshot for more details.");
			return ("FAIL - Not able to wait Till " + ObjectIdentifier + " Element Appears on Page. Please see the screenshot for more details.");
		}
		test.pass("Pass");
		return "PASS";
	}

	public String waitUntilCountChanges(final String firstXpathKey, String inputData)
	{
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: waitUntilCountChanges ()
		 * 
		 * @parameter: String firstXpathKey, String inputData
		 * 
		 * @notes: It will wait till the element count is changes as per time mentioned in web driver wait.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		System.out.println(": Waiting for "+inputData+" element count changes");
		APP_LOGS.debug(": Waiting for "+inputData+" element count changes");
		test.info(": Waiting for "+inputData+" element count changes");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(420));
	        wait.until(new ExpectedCondition<Boolean>() 
	        {
	            public Boolean apply(WebDriver driver) 
	            {
	            	String Count=returnElementIfPresent(firstXpathKey).getText();
	                int elementCount = Integer.parseInt(Count);
	                driver.navigate().refresh();
	                try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
	                if (elementCount > pendingValidationCount)
	                {
	                	return true;
	                }    
	                else
	                {
	                	return false;
	                }
	                   
	            }
	        });	
	        
		} 
		catch (Exception e) 
		{
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to read "+ firstXpathKey);
			return (": FAIL - Not able to read "+ firstXpathKey);
		}
		test.pass("PASS");
		return "PASS";
	}
	public String WaitWhileElementPresent(String ObjectIdentifier) {
		/*
		 * @HELP
		 * 
		 * @class: Keywords
		 * 
		 * @method: Wait ()
		 * 
		 * @parameter: String ObjectIdentifier
		 * 
		 * @notes: Wait for a user defined specific time to load the Object for ex: 60 seconds.
		 * 
		 * @returns: ("PASS" or "FAIL" with Exception in case if method not got executed because of some runtime exception) to executeKeywords method
		 * 
		 * @END
		 */
		try {
			highlight = false;
			captureScreenShot = false;
			int i = 0;
			int expWaitTime = 60;
			System.out.println(": Waiting for " + ObjectIdentifier + " element to Disappear.");
			APP_LOGS.debug(": Waiting for " + ObjectIdentifier + " element to Disappear");
			test.info(" Waiting for " + ObjectIdentifier + " element to Disappear");
			String objectIdentifierValue = "";
			String objectArray[] = null;
			String object = OR.getProperty(ObjectIdentifier);
			objectArray = object.split("__");
			objectIdentifierValue = objectArray[1].trim();
			System.out.println(": Waiting for Max " + expWaitTime + " seconds to Disappear " + ObjectIdentifier + " Element which is Currently Present on Page");
			APP_LOGS.debug(": Waiting for Max " + expWaitTime + " seconds to Disappear " + ObjectIdentifier + " Element which is Currently Present on Page");
			test.info(" Waiting for Max " + expWaitTime + " seconds to Disappear " + ObjectIdentifier + " Element which is Currently Present on Page");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			while (isElementPresentBy(By.xpath(objectIdentifierValue)) == true) {
				if (i <= expWaitTime) {
					System.out.println(": " + ObjectIdentifier + " Element is Present on page. Checking again after 1 Second");
					APP_LOGS.debug(": " + ObjectIdentifier + " Element is Present on page. Checking again after 1 Second");
					test.info(": " + ObjectIdentifier + " Element is Present on page. Checking again after 1 Second");
					Thread.sleep(1000);
					i++;
				} else {
					System.out.println(": Element is still present on Page after waiting " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					APP_LOGS.debug(": Element is still present on Page after waiting " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					test.fail(" FAIL : Element is still present on Page after waiting " + expWaitTime + " Seconds. So stopping this test script execution. Please see the screenshot for more details.");
					captureScreenShot = true;
					return ("FAIL - Element is still present on Page after waiting " + expWaitTime + " Seconds. Please see the screenshot for more details.");
				}
			}
			System.out.println(": " + ObjectIdentifier + " Element is Now NOT Present on Page, Moving Ahead.");
			APP_LOGS.debug(": " + ObjectIdentifier + " Element is Now NOT Present on Page, Moving Ahead");
			test.info(" " + ObjectIdentifier + " Element is Now NOT Present on Page, Moving Ahead");
			driver.manage().timeouts().implicitlyWait(CONFIG_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);

		} catch (Exception e) {
			captureScreenShot = true;
			APP_LOGS.debug(": FAIL - Not able to wait Till " + ObjectIdentifier + " Element Disappears from the Page. Please see the screenshot for more details.");
			test.log(Status.ERROR, "ERROR : Not able to wait Till " + ObjectIdentifier + " Element Disappears from the Page. Please see the screenshot for more details.");
			return ("FAIL - Not able to wait Till " + ObjectIdentifier + " Element Disappears from the Page. Please see the screenshot for more details.");
		}
		test.pass("Pass");
		return "PASS";
	}
}