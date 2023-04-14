package settings.configs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.Getter;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public class TestConfig {

    public static final String ENV = System.getProperty("env");
    public static final String SYS = System.getProperty("sys");


    private final Config CONFIG;

    @Getter
    private static String baseUrlWeb;
    @Getter
    private static String browserWeb;
    @Getter
    private static String browserVersionWeb;
    @Getter
    private static String browserSizeWeb;
    @Getter
    private static String remoteWeb;
    @Getter
    private static String remoteUrlVideoWeb;

    @Getter
    public static String loginMobile;
    @Getter
    public static String userPasswordMobile;
    @Getter
    public static String urlApplicationBrowserstackMobile;
    @Getter
    public static String urlBrowserstackMobile;
    @Getter
    public static String deviceMobile;
    @Getter
    public static String osVersionMobile;
    @Getter
    public static String projectMobile;
    @Getter
    public static String buildNumberMobile;
    @Getter
    public static String buildNameMobile;
    @Getter
    public static String appPackageMobile;
    @Getter
    public static String appActivityMobile;
    @Getter
    public static String hostMobile;

    public TestConfig() throws Exception {

        if ("api".equals(ENV)) {
            this.CONFIG = ConfigFactory.create(DesktopConfig.class, System.getProperties());
        } else {
            throw new Exception("Не верное окружение");
        }
    }}

