package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.implementations.MysqlDatabaseImpl;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private MysqlDatabaseImpl databaseImpl;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		databaseImpl.queryForMetadata();
	}

}
