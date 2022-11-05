package in.v2solutions.hybrid.tests;

import org.testng.annotations.Test;

import in.v2solutions.hybrid.testcases.Keywords;




public class TestRunner{
	
	@Test
	public static void initilizeBrowser() throws Exception {
		Keywords keywords = new Keywords();
		keywords.OpenBrowser("chrome");
	}

}