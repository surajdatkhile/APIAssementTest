package Runner;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/Resource/Feature",
				  glue = {"apiStepDef"},
				 // plugin = {"html:target/cucumber-reports","json:target/cucmber.json"},
				  plugin = {"pretty", "html:src/test/Resource/cucumber-reports/"+"index"+".html", "json:src/test/Resource/cucumber-reports/CucumberTestReport.json"}, // Generate HTML and JSON reports
				  monochrome = true,
				  publish = true)


public class TestRunner extends AbstractTestNGCucumberTests
{
	/*
	 * @DataProvider public Object [][] Scenario(){
	 * 
	 * return super.scenarios(); }
	 */
	
}
