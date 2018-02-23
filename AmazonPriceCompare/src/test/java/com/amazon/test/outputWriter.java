package com.amazon.test;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.amazon.methods.SearchProductTuple;




public class OutputWriter {

	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private static final String FILE_HEADER = "ranking,product title,amazon price,amazon Reduced Price,ebay price, walmart price, cheapest site, profit percent,  ebay url , walmart url, comments";

	public static void writeCsvFile(String fileName, ArrayList<PriceList> pricelists) {
		
		//Create new priceLists objects
//		PriceList priceList1 = new PriceList(1, "Ahmed", "Mohamed", "M", 25);
//		PriceList priceList2 = new PriceList(2, "Sara", "Said", "F", 23);
//		PriceList priceList3 = new PriceList(3, "Ali", "Hassan", "M", 24);
//		PriceList priceList4 = new PriceList(4, "Sama", "Karim", "F", 20);
//		PriceList priceList5 = new PriceList(5, "Khaled", "Mohamed", "M", 22);
//		PriceList priceList6 = new PriceList(6, "Ghada", "Sarhan", "F", 21);
		
		//Create a new list of priceList objects
//		ArrayList<PriceList> priceLists = new ArrayList<PriceList>();
//		priceLists.add(priceListObj);
//		priceLists.add(priceList2);
//		priceLists.add(priceList3);
//		priceLists.add(priceList4);
//		priceLists.add(priceList5);
//		priceLists.add(priceList6);
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(fileName); // pass second arg as true to append to csv

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new priceList object list to the CSV file
			for (PriceList priceList : pricelists) {
				
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getRanking())));
				fileWriter.append(COMMA_DELIMITER);
				
//"product title,amazon url, amazon price,ebay url,ebay price, walmart url, walmart price, cheapest site, profit percent, comments"
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getProductTitle())));
				fileWriter.append(COMMA_DELIMITER);

/*				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonUrl())));
				fileWriter.append(COMMA_DELIMITER);*/

				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonPrice())));
				fileWriter.append(COMMA_DELIMITER);
/*				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonReducedPrice())));
				fileWriter.append(COMMA_DELIMITER);*/
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getEbayPrice())));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getWalmartPrice())));
				fileWriter.append(COMMA_DELIMITER);
				
				

				

				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getCheapest())));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getProfitPercent())));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getEbayUrl())));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getWalmartUrl())));
				fileWriter.append(COMMA_DELIMITER);
				
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getComments())));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			
			
			System.out.println("CSV file was created successfully !!!");
			
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	public static void writeOrAppendToFile(String fileName, PriceList priceList) {
		
		BufferedWriter bw = null;
		FileWriter fileWriter = null;

		try {

			String data = " This is new content";

			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			
			if(priceList.getProfitPercent()<30) {
			
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getRanking())));
			fileWriter.append(COMMA_DELIMITER);
			
//"product title,amazon url, amazon price,ebay url,ebay price, walmart url, walmart price, cheapest site, profit percent, comments"
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getProductTitle())));
			fileWriter.append(COMMA_DELIMITER);

/*				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonUrl())));
			fileWriter.append(COMMA_DELIMITER);*/

			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonPrice())));
			fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getAmazonReducedPrice())));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getEbayPrice())));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getWalmartPrice())));
			fileWriter.append(COMMA_DELIMITER);
			
			

			

			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getCheapest())));
			fileWriter.append(COMMA_DELIMITER);
			
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getProfitPercent())));
			fileWriter.append(COMMA_DELIMITER);
			
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getEbayUrl())));
			fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getWalmartUrl())));
			fileWriter.append(COMMA_DELIMITER);
			
			fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(priceList.getComments())));
			fileWriter.append(NEW_LINE_SEPARATOR);
				
			
/*			bw = new BufferedWriter(fileWriter);

			bw.write(data);*/

			System.out.println("Done");
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	}
	
	
	public static void createFileWithHeaders(String fileName) {

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(fileName); // pass second arg as true to append to csv

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
	
	public static List<String> readFromExcelFile(String excelFile){
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		List<String> urlList = new ArrayList<String>();
        Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		
        System.out.println("Retrieving Sheets using for-each loop");
        Sheet sheet = workbook.getSheetAt(0);
        
     // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = cell.getRichStringCellValue().getString();
                System.out.print(cellValue + "\t");
                urlList.add(cellValue);
            }
            System.out.println();
        }
        return urlList;
	}
	
	public static List<String> readFromExcelFileBasedOnRows(String excelFile, int rowStart, int rowEnd){
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		List<String> urlList = new ArrayList<String>();
        Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(excelFile));
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		
        System.out.println("Retrieving Sheets using for-each loop");
        Sheet sheet = workbook.getSheetAt(0);
        
     // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
        	if(row.getRowNum() == rowStart) {
        		do {
        			
            for(Cell cell: row) {
                String cellValue = cell.getRichStringCellValue().getString();
                System.out.print(cellValue + "\t");
                urlList.add(cellValue);
            }
        		}while(row.getRowNum() != rowEnd);
        	}
            System.out.println();
        }
        return urlList;
	}
	
	public static void writeUrlRankToCsvFile(List<SearchProductTuple>productDetails, String url, String fileName) {

		FileWriter fileWriter = null;

		try {
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fileWriter = new FileWriter(file.getAbsoluteFile(), true);

				/*fileWriter.append(url);
				fileWriter.append(COMMA_DELIMITER);*/

				for(int i =0; i<productDetails.size(); i++) {
					fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(productDetails.get(i).getProductUrl())));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(StringEscapeUtils.escapeCsv(String.valueOf(productDetails.get(i).getRank())));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(NEW_LINE_SEPARATOR);

				}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
		
	}
	
	public static void deleteFileIfExist(String filePath) {
		File file = new File(filePath);
		 if (file.exists()){
		     file.delete();
		 }  

	}
	
	
}