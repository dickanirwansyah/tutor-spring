package com.dicka.springreadexcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class SpringReadExcelApplication {

	private static List<Employee> employees = new ArrayList<>();
	private static String[] colums = {"Name", "Email", "Date Of Birth", "Salary"};
	static {
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);
		employees.add(new Employee("Muhammad Dicka Nirwansyah", "dickanirwansyah@gmail.com",
				dateOfBirth.getTime(), 1200000.0));

		dateOfBirth.set(1965, 10, 15);
		employees.add(new Employee("Denada Rosa Florina", "denaflorina@gmail.com",
				dateOfBirth.getTime(), 1500000.0));


	}

	public static void main(String[] args) throws IOException, InvalidFormatException{
		SpringApplication.run(SpringReadExcelApplication.class, args);

		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();

		/** sheet **/
		Sheet sheet = workbook.createSheet("Employee");
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		/** cell style **/
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		/** create row **/
		Row headerRow = sheet.createRow(0);

		/** create shell **/
		for (int i=0; i < colums.length; i++){
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colums[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		/** create other row **/
		int rowNum = 1;
		for (Employee employee: employees){
			Row row = sheet.createRow(rowNum++);
			row.createCell(0)
					.setCellValue(employee.getName());
			row.createCell(1)
					.setCellValue(employee.getEmail());
			Cell dateOfBirthCell = row.createCell(2);
			dateOfBirthCell.setCellValue(employee.getDateOfBirth());
			dateOfBirthCell.setCellStyle(dateCellStyle);

			row.createCell(3)
					.setCellValue(employee.getSalary());

		}

		/** resice all column to the fit content size **/
		for (int i=0; i < colums.length; i++){
			sheet.autoSizeColumn(i);
		}

		FileOutputStream outputStream = new FileOutputStream("example.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();

	}

	/** example modify excel **/
	private static void modifyExistingWorkBook() throws InvalidFormatException, IOException{
		Workbook workbook = WorkbookFactory.create(new File("existing-spreadsheet.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(2);


		if (cell == null)
			cell = row.getCell(2);

		cell.setCellType(CellType.STRING);
		cell.setCellValue("Update Value");

		/** write file **/
		FileOutputStream outputStream = new FileOutputStream("existing-spreadsheet.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

}

