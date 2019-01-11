package com.dicka.springreadexcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

    /** declare path location file **/
    public final static String LOCATION_XLSX_FILE = "./example.xlsx";
    public final static String LOCATION_XLS_FILE = "./example.xls";

    public static void main(String [] args) throws IOException, InvalidFormatException{

        /** create workbook **/
        Workbook workbook = WorkbookFactory.create(new File(LOCATION_XLSX_FILE));

        /** retrieview file **/
        System.out.println("Workbook has : "+workbook.getNumberOfSheets() +" sheets :");

        /** coba iterator dengan while **/
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieve Sheet with iterator using while");
        while (sheetIterator.hasNext()){
            Sheet sheet = sheetIterator.next();
            /** getSheetName di ambil dari nama entitynya Employee **/
            System.out.println("=> "+ sheet.getSheetName());
        }

        /** coba iteratori dengan forEach **/
        System.out.println("Retrieve Sheet with iteratori using for");
        for (Sheet sheet: workbook){
            /** getSheetName di ambil dari nama entitynya Employee **/
            System.out.println("=> "+sheet.getSheetName());
        }

        /** coba iteratori dengan lambda **/
        System.out.println("Retrieve Sheet with iteratori using lambda");
        workbook.forEach(sheet -> {
            System.out.println("=> "+sheet.getSheetName());
        });


        /** getting the sheet at index of zero **/
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();

        /** using while **/
        System.out.println("\n\n iterator over Rows and columns using iterator\n" );
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue +"\t");
            }
            System.out.println();
        }

        /** using for **/
        System.out.println("\n\n iterator over rows and columns using for\n");
        for (Row row: sheet){
            for (Cell cell: row){
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue+"\t");
            }
            System.out.println();
        }

        /** using lambda **/
        System.out.println("\n\n iterator over rows and columns using lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                printCellValue(cell);
            });
            System.out.println();
        });

        workbook.close();
    }

    private static void printCellValue(Cell cell){
        switch (cell.getCellTypeEnum()){
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case STRING:
                System.out.print(cell.getRichStringCellValue().getString());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    System.out.print(cell.getDateCellValue());
                }else{
                    System.out.print(cell.getNumericCellValue());
                }
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            case BLANK:
                System.out.print("");
                break;
            default:
                System.out.print("");
        }
        System.out.print("\t");
    }

}
