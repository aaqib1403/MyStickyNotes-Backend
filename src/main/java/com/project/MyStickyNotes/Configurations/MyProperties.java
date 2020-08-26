package com.project.MyStickyNotes.Configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "spring.datasource")
@PropertySource(value = "classpath:application.properties")
public class MyProperties {

	String url;
    String username;
    String password;

	public MyProperties() {
	}

	public MyProperties(String url, String username, String password) {

		this.url = url;
		this.username = username;
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
