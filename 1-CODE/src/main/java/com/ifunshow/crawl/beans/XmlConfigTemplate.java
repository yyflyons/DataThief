package com.ifunshow.crawl.beans;

import java.util.Date;

public class XmlConfigTemplate {
	private String row_id;
	private String config_file_name;
	private String type;
	private String xml_content;
	private String result_target_table;
	private Integer row_status;
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
	public String getConfig_file_name() {
		return config_file_name;
	}
	public void setConfig_file_name(String configFileName) {
		config_file_name = configFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXml_content() {
		return xml_content;
	}
	public void setXml_content(String xmlContent) {
		xml_content = xmlContent;
	}
	public String getResult_target_table() {
		return result_target_table;
	}
	public void setResult_target_table(String resultTargetTable) {
		result_target_table = resultTargetTable;
	}
	public Integer getRow_status() {
		return row_status;
	}
	public void setRow_status(Integer rowStatus) {
		row_status = rowStatus;
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
