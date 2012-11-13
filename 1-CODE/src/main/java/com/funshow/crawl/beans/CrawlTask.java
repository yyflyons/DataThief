package com.funshow.crawl.beans;

import java.util.Date;

public class CrawlTask {
	private String row_id;
	private String group_id;
	private String task_name;
	private String corn_trigger_id;
	private String target_url;
	private String crawl_xml_id;
	private String crawl_to_dir;
	private String resolve_xml_id;
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
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String groupId) {
		group_id = groupId;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String taskName) {
		task_name = taskName;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String targetUrl) {
		target_url = targetUrl;
	}
	public String getCrawl_xml_id() {
		return crawl_xml_id;
	}
	public void setCrawl_xml_id(String crawlXmlId) {
		crawl_xml_id = crawlXmlId;
	}
	public String getCrawl_to_dir() {
		return crawl_to_dir;
	}
	public void setCrawl_to_dir(String crawlToDir) {
		crawl_to_dir = crawlToDir;
	}
	public String getResolve_xml_id() {
		return resolve_xml_id;
	}
	public void setResolve_xml_id(String resolveXmlId) {
		resolve_xml_id = resolveXmlId;
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
	public String getCorn_trigger_id() {
		return corn_trigger_id;
	}
	public void setCorn_trigger_id(String cornTriggerId) {
		corn_trigger_id = cornTriggerId;
	}	
}
