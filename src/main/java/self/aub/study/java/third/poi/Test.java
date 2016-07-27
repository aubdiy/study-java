package self.aub.study.java.third.poi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("d:/a/a1.csv"));
		Map<String,String> map = new HashMap<String, String>();
		String s;
		while ((s = br.readLine()) != null) {
			String[] sa = s.split(",");
			map.put(sa[0], sa[1]);
		}
		
		
		BufferedInputStream bis = null;
		Workbook workbook = null;
		try {
			bis = new BufferedInputStream(new FileInputStream("d:/a/b1.xlsx"));
			workbook = null;
//			workbook = new XSSFWorkbook(bis);
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum: " + lastRowNum);
		
		for(int i=1;i<=lastRowNum;i++){
			Row row = sheet.getRow(i);
			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			String phone = cell0.getStringCellValue();
			System.out.print(phone);
			System.out.print(",");
			System.out.print(cell1.getStringCellValue());
			System.out.print(",");
			System.out.print(cell2.getStringCellValue());
			System.out.print(",");
			if(map.containsKey(phone)){
				System.out.print(map.get(phone));
			}else{
				System.out.print("");
			}
			System.out.print("\n");
		}
		
		
	}
}
