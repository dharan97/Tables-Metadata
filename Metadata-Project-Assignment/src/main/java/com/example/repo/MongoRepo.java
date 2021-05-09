package com.example.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.example.implementations.models.TableMetadata;

@Repository
public class MongoRepo {

	@Autowired
	private MongoTemplate mongoTemplate;

	public boolean insertData(List<TableMetadata> metadata) {
		try {
			this.mongoTemplate.insert(metadata, TableMetadata.class);
		} catch (Exception e) {
			System.out.println("Exception Occured WHILE inserting data into mongodb");
			return false;
		}
		return true;
	}

	public List<TableMetadata> getMetadaOfTables() {
		List<TableMetadata> list = mongoTemplate.findAll(TableMetadata.class);
		return list;
	}
}
