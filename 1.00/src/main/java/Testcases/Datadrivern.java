package Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Datadrivern {
	static List<String> usernamelist = new ArrayList<String>();
	static List<String> passwordlist = new ArrayList<String>();


	public void readexcl() throws IOException {
		
		File file = new File("C:\\Users\\ARO EDWIN\\Documents\\excel.xlsx");
		
		FileInputStream filein = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(filein);

		Sheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowite = sheet.rowIterator();

		while (rowite.hasNext()) {
			Row rowval = rowite.next();
			Iterator<Cell> colite = rowval.iterator();
			int i = 2;
			while (colite.hasNext()) {
				if (i % 2 == 0) {
					// Cell username=colite.next();
					usernamelist.add(colite.next().getStringCellValue());
				} else {
					// Cell password=colite.next();
					passwordlist.add(colite.next().getStringCellValue());
				}
				i++;
			}

		}
	}
	public void login(String uname,String passw) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ARO EDWIN\\Downloads\\chromedriver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		WebElement user=driver.findElement(By.id("txtUsername"));
		user.sendKeys(uname);
		
		WebElement pass=driver.findElement(By.id("txtPassword"));
		pass.sendKeys(passw);
		
		WebElement click=driver.findElement(By.id("btnLogin"));
		click.click();
		driver.quit();
	}
	public void execute() {
		for(int i=0;i<usernamelist.size();i++) {
			login(usernamelist.get(i),passwordlist.get(i));
		}
	}

	public static void main(String[] args) throws IOException {

		Datadrivern obj = new Datadrivern();
		obj.readexcl();
		System.out.println("USERNAME :"+usernamelist);
		System.out.println("PASSWORD :"+passwordlist);
		obj.execute();
	}

}
