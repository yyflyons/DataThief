package com.ifunshow.crawl.beans;

import java.util.Date;

public class CrawlTaskGroup {
	private String row_id;
	private String group_name;
	private String trigger_id;
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
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
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
	public String getTrigger_id() {
		return trigger_id;
	}
	public void setTrigger_id(String triggerId) {
		trigger_id = triggerId;
	}
}
