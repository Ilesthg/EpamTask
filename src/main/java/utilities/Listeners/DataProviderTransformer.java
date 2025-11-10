package utilities.Listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import utilities.data_providers.DataProviderFiltered;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DataProviderTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setDataProvider("DataFiltered");
        annotation.setDataProviderClass(DataProviderFiltered.class);


    }
}
