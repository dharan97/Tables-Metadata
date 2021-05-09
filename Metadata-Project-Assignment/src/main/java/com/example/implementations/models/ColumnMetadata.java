package com.example.implementations.models;

public class ColumnMetadata {

	private String columnName;
	private String dataType;
	private int size;
	private String nullable;
	private String isAutoIncremented;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String isNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getIsAutoIncremented() {
		return isAutoIncremented;
	}

	public void setIsAutoIncremented(String isAutoIncremented) {
		this.isAutoIncremented = isAutoIncremented;
	}
}
