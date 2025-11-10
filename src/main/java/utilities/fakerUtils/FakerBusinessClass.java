package utilities.fakerUtils;

import java.util.ArrayList;
import java.util.List;

public class FakerBusinessClass {


    public static List<String> validNames() {
        return FakerServiceClass.listRandomFirstNames();
    }

    public static List<String> invalidNames() {
        List<String> list = new ArrayList<>();
        for (String name : FakerServiceClass.listRandomFirstNames()) {
            {
                String a = name + "1234567890";
                list.add(name);
            }
        }
        return list;
    }
    //Array is accepted in the fixture as dataset
    public static Object[] returnListValidFirstNames() {
        return FakerServiceClass.returnListFirstNames();
    }


}
