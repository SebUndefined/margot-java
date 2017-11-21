package de.onetwotree.margaux.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

/**
 * Created by SebUndefined on 21/11/17.
 */
@Configuration
public class StorageProperties {

    private String location = "/var/appStorage/margaux/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
