package constants;


import enums.*;
import utilities.datareader.PropertiesReader;

public final class Constants {

    private Constants() {
    }

    private static final String routeDir = System.getProperty("user.dir");


    private static final String BROWSER = PropertiesReader.returnKey(Configurations.BROWSER);
    private static final String WEBRUNMODE = PropertiesReader.returnKey(Configurations.WEBRUNMODE);
    private static final String WEBPLATFORM = PropertiesReader.returnKey(Configurations.WEBPLATFORM);


    private static final String URL = PropertiesReader.returnKey(Configurations.URL);

    private static String dirForCreateExtentReport = "";

    public static String getRouteDir() {
        if (PropertiesReader.returnKey(Configurations.OVERRIDEREPORTS).equalsIgnoreCase("yes")) {
            return dirForCreateExtentReport = routeDir + "/src/test/resources/reports/index.html";

        }
        return dirForCreateExtentReport = routeDir + "/src/test/resources/reports/" + System.currentTimeMillis() + ".html";
    }

    private static final String ERRORMESSAGE = "Su intento de inicio de sesión no se realizó correctamente. Por favor, inténtelo de nuevo.";


    public static String getWebrunmode() {
        return WEBRUNMODE;
    }

    public static String getWEBPLATFORM() {
        return WEBPLATFORM;
    }

    public static String getUrl() {
        return URL;
    }

    public static String getErrormessage() {
        return ERRORMESSAGE;
    }


    public static Browsers getBrowserEnum() {
        try {
            return Browsers.valueOf(BROWSER.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid browser value '" + BROWSER + "' for key 'browser'. " +
                            "Allowed values are: " + java.util.Arrays.toString(Browsers.values()),
                    e
            );
        }
    }

    public static RunMode getWebRunModeEnum() {
        try {
            return RunMode.valueOf(WEBRUNMODE.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid WEBRUNMODE value '" + WEBRUNMODE + "'. " +
                            "Allowed values are: " + java.util.Arrays.toString(RunMode.values()),
                    e
            );
        }
    }

    public static WebPlatform getWebPlatformEnum() {
        try {
            return WebPlatform.valueOf(WEBPLATFORM.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                    "Invalid WEBPLATFORM value '" + WEBPLATFORM + "'. " +
                            "Allowed values are: " + java.util.Arrays.toString(WebPlatform.values()),
                    e
            );
        }
    }
}

