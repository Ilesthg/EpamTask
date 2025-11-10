package utilities.datareader;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelReader {


    public static Object[] excelReaderDP(String sheetName) {
        List<HashMap<String, String>> hashMapList;

        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/testData.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            HashMap<String, String> hm;
            hashMapList = new ArrayList<>();
            for (int i = 1; i <= totalRows; i++) {
                hm = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = sheet.getRow(0).getCell(j).toString();
                    String value = sheet.getRow(i).getCell(j).toString();

                    hm.put(key, value);
                }
                hashMapList.add(hm);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hashMapList.toArray();
    }
    public static List<HashMap<String,String>> excelReader(String sheetName) {
        List<HashMap<String, String>> hashMapList;
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/testData.xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            HashMap<String, String> hm;
            hashMapList = new ArrayList<>();
            for (int i = 1; i <= totalRows; i++) {
                hm = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = sheet.getRow(0).getCell(j).toString();
                    String value = sheet.getRow(i).getCell(j).toString();

                    hm.put(key, value);
                }
                hashMapList.add(hm);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hashMapList;
    }


}
