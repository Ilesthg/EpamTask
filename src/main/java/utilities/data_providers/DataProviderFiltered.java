package utilities.data_providers;

import org.testng.annotations.DataProvider;
import pages.LoginBehaviours;
import utilities.datareader.ExcelReader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProviderFiltered {

    @DataProvider(name = "Data")
    public Object[][] DataProvider(Method m) {
        List<HashMap<String, String>> hashMapList = ExcelReader.excelReader("Login");
        List<Object[]> testData = new ArrayList<>();

        for (HashMap<String, String> dataRow : hashMapList) {
            // You can add filtering logic here if needed
            String expectedOutcome = dataRow.get("expectedOutcome");

            // Skip if no expected outcome defined
            if (expectedOutcome != null && !expectedOutcome.isEmpty()) {
                testData.add(new Object[]{dataRow});
            }
        }

        return testData.toArray(new Object[0][]);
    }
    @DataProvider(name = "ValidData")
    public Object[] validDataProvider(Method m) {

        List<HashMap<String, String>> hashMapList = ExcelReader.excelReader("Login");
        List<Object> validData = new ArrayList<>();

        for (HashMap<String, String> hashMaps : hashMapList) {
            if ("valid".equalsIgnoreCase(hashMaps.get("expectedOutcome"))
                    ){ // Filter valid rows
                validData.add(hashMaps);
            }
        }
        return validData.toArray();
    }

    @DataProvider(name = "InvalidData")
    public Object[] invalidDataProvider(Method m) {
        List<HashMap<String, String>> hashMapList = ExcelReader.excelReader("Login");
        List<Object> invalidData = new ArrayList<>();

        for (HashMap<String, String> hashMaps : hashMapList) {
            if ("invalid".equalsIgnoreCase(hashMaps.get("expected"))){ // Filter invalid rows
                invalidData.add(hashMaps);
            }
        }
        return invalidData.toArray();
    }







}
