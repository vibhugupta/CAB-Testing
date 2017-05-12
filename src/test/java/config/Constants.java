package config;

/**
 * Created by vibhu on 2/14/2017.
 */
public class Constants {

    //This is the list of System Variables
    //Declared as 'public', so that it can be used in other classes of this project
    //Declared as 'static', so that we do not need to instantiate a class object
    //Declared as 'final', so that the value of this variable can be changed
    // 'String' & 'int' are the data type for storing a type of value
    public static final String URL = "http://application-721436538.ap-northeast-2.elb.amazonaws.com/cab/#/login";
    public static final String Path_TestData = System.getProperty("user.dir") + "\\src\\test\\dataEngine\\DataEngine.xlsx";
    public static final String Path_locator = System.getProperty("user.dir") + "\\src\\main\\resources\\locatorsfile\\locators.properties";
    public static final String File_TestData = "DataEngine.xlsx";

    //List of Data Sheet Column Numbers
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID =1 ;
    //This is the new column for 'Page Object'
    public static final int Col_PageObject =3 ;
    //This column is shifted from 3 to 4
    public static final int Col_ActionKeyword =4 ;

    //New entry in Constant variable
    public static final int Col_RunMode =2;
    //List of Data Engine Excel sheets
    public static final String Sheet_TestSteps = "Test Steps";

    //New entry in Constant variable
    public static final String Sheet_TestCases = "Test Cases";
    // List of Test Data
    public static final String UserName = "130144";
    public static final String Password = "130144@mckinsey";
    public static final String CityToTravel = "MUMBAI";
    public static final String pickupLocation = "AIRPORT - T1";
    public static final String dropLocation = "AIRPORT - T2";
    public static final String chargeCode = "ABC123";

}