package enums;

public enum Environments {
    QA("https://qa.saucedemo.com/"),
    STAGING("https://staging.saucedemo.com/"),
    PREPROD("https://preprod.saucedemo.com/"),
    PROD("https://www.saucedemo.com/");

    private final String url;

    Environments(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }


}
