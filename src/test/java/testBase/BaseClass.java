package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
 

public class BaseClass {
    
    static public WebDriver driver;
    public Logger logger;
    public Properties p;
    
    @BeforeClass(groups= {"sanity","master","regression"})
    @Parameters({"os", "browser"})
     
    public void setup(@Optional String os, String br) throws IOException 
    {
        //Loading properties file
        FileReader file = new FileReader(".//src//test//resources//config.properties");
        p=new Properties();
        p.load(file);
        
        //Loading Log4j file
        logger=LogManager.getLogger(this.getClass());//Log4j
        
              
        
        //Launching Browser based on condition
        switch(br.toLowerCase())
        {
        case "chrome": driver=new ChromeDriver(); break;
        case "edge": driver=new EdgeDriver(); break;
        case "firefox": driver=new FirefoxDriver(); break;
        default: System.out.println("No matching browser..");
                    return;
        
        }
        
        
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
        
       
    }
    
    @AfterClass(groups= {"sanity","master","regression"})
    public void tearDown()
    {
        driver.quit();
    }
    

    public String randomeString()
    {
        String generatedString=RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
    
    public String randomeNumber()
    {
        String generatedString=RandomStringUtils.randomNumeric(10);
        return generatedString;
    }
    
    public String randomAlphaNumeric()
    {
        String str=RandomStringUtils.randomAlphabetic(3);
        String num=RandomStringUtils.randomNumeric(3);
        
        return (str+"@"+num);
    }
    
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);
        
        sourceFile.renameTo(targetFile);
            
        return targetFilePath;

    }
}