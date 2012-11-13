package com.funshow.crawl.beans;

import java.util.Date;

public class QuartzCornTrigger {
	private String row_id;	
	private String name;
	private String group;
	private String jobName;
	private String jobGroup;
	private Date startTime;
	private Date endTime;
	private String cronExpression;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
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
