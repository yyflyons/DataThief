package com.funshow.crawl.beans;

import java.util.Date;

public class ResultRecord {
	private String row_id;
	private String task_id;
	private String log_id;
	private String resolve_file_path;
	private String resolve_record_xml;
	private Date resolve_start_date;
	private Date resolve_end_date;
	private Integer resolve_status;
	private String resolve_note;
	private Date synchronize_start_date;
	private Date synchronize_end_date;
	private Integer synchronize_status;
	private String synchronize_note;
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
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String logId) {
		log_id = logId;
	}
	public Integer getSynchronize_status() {
		return synchronize_status;
	}
	public void setSynchronize_status(Integer synchronizeStatus) {
		synchronize_status = synchronizeStatus;
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
	public String getResolve_file_path() {
		return resolve_file_path;
	}
	public void setResolve_file_path(String resolveFilePath) {
		resolve_file_path = resolveFilePath;
	}
	public String getResolve_record_xml() {
		return resolve_record_xml;
	}
	public void setResolve_record_xml(String resolveRecordXml) {
		resolve_record_xml = resolveRecordXml;
	}
	public Date getResolve_start_date() {
		return resolve_start_date;
	}
	public void setResolve_start_date(Date resolveStartDate) {
		resolve_start_date = resolveStartDate;
	}
	public Date getResolve_end_date() {
		return resolve_end_date;
	}
	public void setResolve_end_date(Date resolveEndDate) {
		resolve_end_date = resolveEndDate;
	}
	public Integer getResolve_status() {
		return resolve_status;
	}
	public void setResolve_status(Integer resolveStatus) {
		resolve_status = resolveStatus;
	}
	public String getResolve_note() {
		return resolve_note;
	}
	public void setResolve_note(String resolveNote) {
		resolve_note = resolveNote;
	}
	public Date getSynchronize_start_date() {
		return synchronize_start_date;
	}
	public void setSynchronize_start_date(Date synchronizeStartDate) {
		synchronize_start_date = synchronizeStartDate;
	}
	public Date getSynchronize_end_date() {
		return synchronize_end_date;
	}
	public void setSynchronize_end_date(Date synchronizeEndDate) {
		synchronize_end_date = synchronizeEndDate;
	}
	public String getSynchronize_note() {
		return synchronize_note;
	}
	public void setSynchronize_note(String synchronizeNote) {
		synchronize_note = synchronizeNote;
	}
}
