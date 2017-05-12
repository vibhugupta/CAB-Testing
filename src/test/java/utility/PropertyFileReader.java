package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertyFileReader {

    public static Properties locatorFileReader(String filePath)

    {
        File file = new File(filePath);

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();

        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  System.out.println("URL ::" + prop.getProperty("firstName"));
        return prop;
    }
}
