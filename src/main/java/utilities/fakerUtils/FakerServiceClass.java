package utilities.fakerUtils;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class FakerServiceClass {
    private static final Faker faker = new Faker();


     static String randomFirstName() {
        return faker.name().firstName();
    }

     static String randomLastName() {
        return faker.name().lastName();
    }

     static List<String> listRandomLastNames() {
        ArrayList<String> listLastNames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listLastNames.add(faker.name().lastName());
        }
        return listLastNames;
    }

     static List<String> listRandomFirstNames() {
        ArrayList<String> listFirstNames = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listFirstNames.add(faker.name().firstName());
        }
        return listFirstNames;

    }
    public static Object[] returnListFirstNames() {
        List<String> list = new ArrayList<>();
         for (int i = 0; i < 3; i++) {
             list.add(faker.name().firstName());
         }
         return list.toArray();
}
     }
