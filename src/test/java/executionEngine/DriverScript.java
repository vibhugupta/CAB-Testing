package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import config.ActionKeywords;
import config.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.ExcelUtils;

import java.util.Properties;

/**
 * Created by vibhu on 2/14/2017.
 */
public class DriverScript {

    //This is a class object, declared as 'public static'
    //So that it can be used outside the scope of main[] method
    public static ActionKeywords actionKeywords;
    public static String sActionKeyword;
    public static String sPageObject;
    public static Properties locators;
    //This is reflection class object, declared as 'public static'
    //So that it can be used outside the scope of main[] method
    public static Method method[];

    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sRunMode;

    //Here we are instantiating a new object of class 'ActionKeywords'
    public DriverScript() throws NoSuchMethodException, SecurityException {
        actionKeywords = new ActionKeywords();
        //This will load all the methods of the class 'ActionKeywords' in it.
        //It will be like array of method, use the break point here and do the watch
        method = actionKeywords.getClass().getMethods();
    }


    public static void main(String[] args) throws Exception {


        ExcelUtils.setExcelFile(Constants.Path_TestData);


        //Declaring String variable for storing Object Repository path
        String Path_OR = Constants.Path_locator;
        //Creating file system object for Object Repository text/property file
        FileInputStream fs = new FileInputStream(Path_OR);
        //Creating an Object of properties
        locators = new Properties(System.getProperties());
        //Loading all the properties from Object Repository property file in to OR object
        locators.load(fs);


        DriverScript startEngine = new DriverScript();
        startEngine.execute_TestCase();


    }

    private void execute_TestCase() throws Exception {
        //This will return the total number of test cases mentioned in the Test cases sheet
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
        //This loop will execute number of times equal to Total number of test cases
        for (int iTestcase = 1; iTestcase <= iTotalTestCases; iTestcase++) {
            //This is to get the Test case name from the Test Cases sheet
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
            //This is to get the value of the Run Mode column for the current test case
            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
            //This is the condition statement on RunMode value
            if (sRunMode.equals("Yes")) {
                //Only if the value of Run Mode is 'Yes', this part of code will execute
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
                //This loop will execute number of times equal to Total number of test steps
                for (; iTestStep <= iTestLastStep; iTestStep++) {
                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
                    sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
                    execute_Actions();
                }
            }
        }
    }

    //This method contains the code to perform some action
    //As it is completely different set of logic, which revolves around the action only,
    //It makes sense to keep it separate from the main driver script
    //This is to execute test step (Action)
    private static void execute_Actions() throws Exception {
        //This is a loop which will run for the number of actions in the Action Keyword class
        //method variable contain all the method and method.length returns the total number of methods
        for (int i = 0; i < method.length; i++) {
            //This is now comparing the method name with the ActionKeyword value got from excel
            if (method[i].getName().equals(sActionKeyword)) {
                //In case of match found, it will execute the matched method
                method[i].invoke(actionKeywords, sPageObject);
                //Once any method is executed, this break statement will take the flow outside of for loop
                break;
            }
        }
    }
}
