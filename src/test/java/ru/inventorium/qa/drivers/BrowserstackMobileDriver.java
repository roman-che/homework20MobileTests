package ru.inventorium.qa.drivers;

import ru.inventorium.qa.config.*;
import org.aeonbits.owner.ConfigFactory;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    private static final CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    private static final String userName = credentials.user_name();
    private static final String accessKey = credentials.access_key();
    private static final String appUrl = credentials.appURL();
    private static final String remoteUrl = credentials.remoteURL();

    private static final DeviceConfig config = ConfigFactory.create(DeviceConfig.class, System.getProperties());
    private static final String device = config.device();
    private static final String os_version = config.os_version();

    private static final ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private static final String projectTitle = projectConfig.project();
    private static final String buildName = projectConfig.build();
    private static final String projectName = projectConfig.name();

    public static URL getBrowserstackUrl() {
        try {
            return new URL(remoteUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        // Set your access credentials
        desiredCapabilities.setCapability("browserstack.user", userName);
        desiredCapabilities.setCapability("browserstack.key", accessKey);

        // Set URL of the application under test
        desiredCapabilities.setCapability("app", appUrl);

        // Specify device and os_version for testing
        desiredCapabilities.setCapability("device", device);
        desiredCapabilities.setCapability("os_version", os_version);

        // Set other BrowserStack capabilities
        desiredCapabilities.setCapability("project", projectTitle);
        desiredCapabilities.setCapability("build", buildName);
        desiredCapabilities.setCapability("name", projectName);

        return new AndroidDriver(getBrowserstackUrl(), desiredCapabilities);
    }
}
