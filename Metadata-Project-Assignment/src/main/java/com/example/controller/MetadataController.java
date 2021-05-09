package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.implementations.MongoServiceImpl;
import com.example.implementations.models.TableMetadata;

@RestController
@RequestMapping("/getMetadata")
public class MetadataController {

	@Autowired
	private MongoServiceImpl service;

	@GetMapping(value = "/")
	public List<TableMetadata> getMetadata() {
		List<TableMetadata> list = service.getMetadata();
		return list == null ? new ArrayList<TableMetadata>() : list;
	}
}
