package com.funshow.crawl.beans;

import java.util.Date;

public class ParaStaticData {
	private String row_id;
	private String table_name;
	private String column_name;
	private String code;
	private String value;
	private String description;
	private String row_creator;
	private Date row_create_date;
	private String row_last_updator;
	private Date row_last_update_date;
	
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String rowId) {
		row_id = rowId;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String tableName) {
		table_name = tableName;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String columnName) {
		column_name = columnName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRow_creator() {
		return row_creator;
	}
	public void setRow_creator(String rowCreator) {
		row_creator = rowCreator;
	}
	public Date getRow_create_date() {
		return row_create_date;
	}
	public void setRow_create_date(Date rowCreateDate) {
		row_create_date = rowCreateDate;
	}
	public String getRow_last_updator() {
		return row_last_updator;
	}
	public void setRow_last_updator(String rowLastUpdator) {
		row_last_updator = rowLastUpdator;
	}
	public Date getRow_last_update_date() {
		return row_last_update_date;
	}
	public void setRow_last_update_date(Date rowLastUpdateDate) {
		row_last_update_date = rowLastUpdateDate;
	}
}
