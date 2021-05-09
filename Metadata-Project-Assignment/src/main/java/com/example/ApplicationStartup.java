package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.implementations.MysqlDatabaseImpl;
import com.example.implementations.models.TableMetadata;
import com.example.repo.MongoRepo;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
	private MysqlDatabaseImpl databaseImpl;
	
//	@Autowired
//	private MongoRepo mongoRepo;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event){
		
		List<TableMetadata> list = databaseImpl.queryForMetadata();
//		if(list != null) {
//			boolean added = mongoRepo.insertData(list);
//			if(added) {
//				System.out.println("Added data to mongodb");
//			} else {
//				System.out.println("Exception in adding data to db");
//			}
//		}
	}
	
}
