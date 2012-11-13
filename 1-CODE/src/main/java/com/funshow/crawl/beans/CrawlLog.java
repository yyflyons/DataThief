package com.funshow.crawl.beans;

import java.util.Date;

public class CrawlLog {
	private String row_id;
	private String task_id;
	private String crawl_file_path;
	private Date crawl_start_date;
	private Date crawl_end_date;
	private String crawl_note;
	private String crawl_status;
	private String row_creator;
	private Date row_create_date;
	
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String rowId) {
		row_id = rowId;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String taskId) {
		task_id = taskId;
	}

	public Date getCrawl_start_date() {
		return crawl_start_date;
	}
	public void setCrawl_start_date(Date crawlStartDate) {
		crawl_start_date = crawlStartDate;
	}
	public Date getCrawl_end_date() {
		return crawl_end_date;
	}
	public void setCrawl_end_date(Date crawlEndDate) {
		crawl_end_date = crawlEndDate;
	}
	public String getCrawl_note() {
		return crawl_note;
	}
	public void setCrawl_note(String crawlNote) {
		crawl_note = crawlNote;
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
	public String getCrawl_file_path() {
		return crawl_file_path;
	}
	public void setCrawl_file_path(String crawlFilePath) {
		crawl_file_path = crawlFilePath;
	}
	public String getCrawl_status() {
		return crawl_status;
	}
	public void setCrawl_status(String crawlStatus) {
		crawl_status = crawlStatus;
	}
}
