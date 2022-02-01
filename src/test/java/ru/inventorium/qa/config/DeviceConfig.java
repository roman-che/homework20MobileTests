package ru.inventorium.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/device.properties"})
public interface DeviceConfig extends Config {

    @Key("device")
    String device();

    @Key("os_version")
    String os_version();
}
