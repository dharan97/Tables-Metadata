package com.example.implementations.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "metadata")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TableMetadata {
	private String tableName;
	private List<ColumnMetadata> columnData;
	private PrimaryKeyMetadata pkData;
	private List<ForeignKeyMetadata> fkData;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnMetadata> getColumnData() {
		return columnData;
	}

	public void setColumnData(List<ColumnMetadata> columnData) {
		this.columnData = columnData;
	}

	public PrimaryKeyMetadata getPkData() {
		return pkData;
	}

	public void setPkData(PrimaryKeyMetadata pkData) {
		this.pkData = pkData;
	}

	public List<ForeignKeyMetadata> getFkData() {
		return fkData;
	}

	public void setFkData(List<ForeignKeyMetadata> fkData) {
		this.fkData = fkData;
	}

}
