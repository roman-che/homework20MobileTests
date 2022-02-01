package ru.inventorium.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ru.inventorium.qa.drivers.*;
import ru.inventorium.qa.helpers.*;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;
import static ru.inventorium.qa.helpers.Attach.getSessionId;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        Configuration.browser = BrowserstackMobileDriver.class.getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {

        step("Open browser", () -> {
            Selenide.open();
        });
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close browser", () -> {
            Selenide.closeWebDriver();
        });

        Attach.attachVideo(sessionId);
    }
}
