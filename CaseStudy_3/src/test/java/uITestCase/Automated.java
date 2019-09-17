package uITestCase;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Verify;

import junit.framework.Assert;

public class Automated {

	static WebDriver d =  null; WebElement sib= null;
	static String  Url="https://company.trivago.com/careers/open-positions/";
	static String CommonUrl = "https://company.trivago.com/?post_type=job-position&p=";
	
	public static void main(String[] args) throws InterruptedException {
		
		d=setBrowser("Chrome");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		d.navigate().to(Url);
		validations();
		
	}
	
	@SuppressWarnings("deprecation")
	private static void validations() throws InterruptedException {
		

    	
    		d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    		
    		/***
			 * All job profile URL’s should be dynamically retrieved
			 ***/
    		try {
    			
    			List <WebElement> sib = d.findElements(By.xpath("//tr[@class='list-jobs-wd']/following-sibling::tr"));
    			
    			for (WebElement webElement : sib) {
    		           System.out.println(webElement.getAttribute("onclick"));
    		            
    		            String loc_href =webElement.getAttribute("onclick");
    		            String[] l = loc_href.split("=");
    		            String job_id_s = l[3];
    		            String[] job_id= job_id_s.split(" ");
    		            String jobid = job_id[0];
    		           
    		            
    		            String finals = CommonUrl+jobid;
    		            System.out.println("HREF: " + finals);
    		            
    	    }
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		/***
    		 *  • Job title should have a <h1> tag
				• Job Family should have a value
				• Experience level should have a value
				• Location should have a value
				• Language should have a value
				• Apply button should be present on the page
				• What you’ll do should have a <b> tag
				• What you’ll definitely need should have <b> tag
				• What we’d love you to have should have <b> tag
    		 */
    	 try {
        		d.navigate().to("https://company.trivago.com/?post_type=job-position&p=23010");
        		
        		WebElement JobTitle = d.findElement(By.xpath("//div[@class='col g-1of1 margin--none common-title']/h1"));
        		String ActualTag = JobTitle.getTagName();
        		System.out.println("ActualTag: " +ActualTag);
        		String Expected ="h1";
        		Assert.assertEquals(Expected, ActualTag);
        		
        		String job_family = d.findElement(By.id("job_family")).getText();
        		Assert.assertNotNull(job_family);
        		
        		String Experience = d.findElement(By.xpath("//*[@id='content']/section[3]/div/div/div[2]/p[2]/span")).getText();
        		Assert.assertNotNull(Experience);
        		
        		
        		String Location = d.findElement(By.className(("//*[@id='content']/section[3]/div/div/div[2]/p[3]/span"))).getText();
        		Assert.assertNotNull(Location);
        		
        		
        		String Language = d.findElement(By.className(("//*[@id='content']/section[3]/div/div/div[2]/p[4]/span"))).getText();
        		Assert.assertNotNull(Language);
        		
        		
        		WebElement Button = d.findElement(By.xpath("//button[@class='btn btn--tertiary btn--small apply-small margin-top-16 no-tablet utm__attached']"));
        		Boolean actual= Button.isDisplayed();
        		Assert.assertTrue(actual);
        		
        		WebElement Whatyoulldo = d.findElement(By.xpath("//*[@id='content']/section[3]/div/div/div[3]/p[11]/b"));
        		String ActualWhatyoulldoTag = Whatyoulldo.getTagName();
        		String ExpectedWhatyoulldoTag ="b";
        		Assert.assertEquals(ExpectedWhatyoulldoTag, ActualWhatyoulldoTag);
        		
        		WebElement Whatyoulldeﬁnitelyneed = d.findElement(By.xpath("//*[@id='content']/section[3]/div/div/div[3]/p[12]/b"));
        		String ActualWhatyoulldeﬁnitelyneedTag = Whatyoulldeﬁnitelyneed.getTagName();
        		String ExpectedWhatyoulldeﬁnitelyneedTag ="b";
        		Assert.assertEquals(ExpectedWhatyoulldeﬁnitelyneedTag, ActualWhatyoulldeﬁnitelyneedTag);
        		
        		WebElement Whatwedloveyoutohave = d.findElement(By.xpath("//*[@id='content']/section[3]/div/div/div[3]/p[13]/b"));
        		String ActualWhatwedloveyoutohaveneedTag = Whatwedloveyoutohave.getTagName();
        		String ExpectedWhatwedloveyoutohave ="b";
        		Assert.assertEquals(ExpectedWhatwedloveyoutohave, ActualWhatwedloveyoutohaveneedTag);
        		
        		
        		
        }catch (Exception e) {
        	e.printStackTrace();
        }
        }
	
		private static WebDriver setBrowser(String Browser) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ishad\\my-workspace\\CaseStudy_3\\src\\test\\resources\\chromedriver76\\chromedriver.exe");
		WebDriver d = new ChromeDriver();
		return d;
	}

	
}
