package self.aub.study.java.third.poi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PoiDemo {
	
	//2003
	public static void createXlsFile(String fileName){
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "第一个Sheet!");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("第一行，第一列 2003");
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2007
	public static void createXlsxFile(String fileName){
		Workbook workbook = null;
//		Workbook workbook = new XSSFWorkbook();

		CellStyle colorStyle = workbook.createCellStyle();

//		System.out.println(new XSSFColor(Color.GREEN).getIndexed());
		System.out.println(IndexedColors.GREEN.getIndex());
//		colorStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//		colorStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		colorStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "第一个Sheet!");
		
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("第一行，第一列 2007");
		cell.setCellStyle(colorStyle);
		
		Cell cella=row.createCell(1);
		cella.setCellValue(100);
		Cell cellb=row.createCell(2);
		cellb.setCellValue(200);
		Cell cellc=row.createCell(3);
		//excel formula
		cellc.setCellFormula("SUM(B1:C1)");
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2003
	public static void readXlsFile(String fileName){
		BufferedInputStream bis = null;
		Workbook workbook = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(fileName));
			workbook = new HSSFWorkbook(bis);
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = workbook.getSheetAt(0);
		System.out.println(sheet.getSheetName());
		
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum: " + lastRowNum);
		
		Row row = sheet.getRow(0);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum: " + lastCellNum);
		
		Cell cell = row.getCell(0);
		
		System.out.println(cell.getStringCellValue());
	}
	
	//2007
	public static void readXlsxFile(String fileName){
		BufferedInputStream bis = null;
		Workbook workbook = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(fileName));
			workbook = null;
//			workbook = new XSSFWorkbook(bis);
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(workbook.getNumberOfSheets());
		Sheet sheet = workbook.getSheetAt(0);
		System.out.println(sheet.getSheetName());
		
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("lastRowNum: " + lastRowNum);
		
		Row row = sheet.getRow(0);
		int lastCellNum = row.getLastCellNum();
		System.out.println("lastCellNum: " + lastCellNum);
		
		Cell cell = row.getCell(0);
//		cell.getCellType();
		System.out.println(cell.getStringCellValue());
	}
	
	public static void main(String[] args) {
//		createXlsFile("c:/1.xls");
//		createXlsxFile("c:/1.xlsx");
//		readXlsFile("c:/1.xls");
		readXlsxFile("c:/1.xlsx");
	}
	
}
