package com.parseOutlook2.demo;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebDriver getDriverBean() {

		final String DRIVERPATH = "/home/artevseev/Desktop/Selenium/geckodriver";
		System.setProperty("webdriver.gecko.driver", DRIVERPATH);
		System.setProperty("java.awt.headless", "false");
		FirefoxOptions op = new FirefoxOptions();
		op.setCapability("pageLoadStrategy", "eager");

		return new FirefoxDriver();
	}

}
