package utilities.Listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import utilities.datareader.ExcelReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> list, ITestContext iTestContext) {
        System.out.println("----------------METHOD INTERCEPTOR--------------------------");
        System.out.println("methods: " + list.size());

        // Read all test cases from Excel
        List<HashMap<String, String>> listofHashMap =
                ExcelReader.excelReader("Data");

        // List to store the final filtered test methods
        List<IMethodInstance> endResult = new ArrayList<>();
        String testCategory = System.getProperty("testCategory");
        System.out.println("testCategory: " + testCategory);


        for (IMethodInstance iMethodInstance : list) {
            for (HashMap<String, String> hashMap : listofHashMap) {
                if (hashMap.get("execute").equalsIgnoreCase("yes")
                     && hashMap.get("expected").equalsIgnoreCase("valid")  ) {
                    endResult.add(iMethodInstance);
                }
            }
        }

     /*   for (int i = 0; i < list.size(); i++) {
            for (HashMap<String, String> listHashMaps : listofHashMap) {
                if (listHashMaps.get("execute").equalsIgnoreCase("y")) {
                    endResult.add(list.get(i));
                }
            }
        }*/


        return endResult;
    }
}
// && iMethodInstance.getMethod().getMethodName().startsWith(listHashMaps.get("expected"))