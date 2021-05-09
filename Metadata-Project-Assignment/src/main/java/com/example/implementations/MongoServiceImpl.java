package com.example.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.implementations.models.TableMetadata;
import com.example.repo.MongoRepo;

@Service
public class MongoServiceImpl {
	
	@Autowired
	private MongoRepo mongoRepo;

	public List<TableMetadata> getMetadata() {
		return mongoRepo.getMetadaOfTables();
	}

}
