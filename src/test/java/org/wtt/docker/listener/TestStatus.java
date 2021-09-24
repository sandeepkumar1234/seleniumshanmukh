package org.wtt.docker.listener;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestStatus {

	@JsonProperty("testClass")
	private String testClass;

	@JsonProperty("description")
	private String description;

	@JsonProperty("status")
	private String status;

	@JsonProperty("startTime")
	private String startTime;

	@JsonProperty("endTime")
	private String endTime;

	@JsonProperty("duration")
	private String duration;

	@JsonProperty("executionTime")
	private String executionTime;

	@JsonProperty("testName")
	private String testName;

	@JsonProperty("result")
	private String result;
	
	@JsonProperty("testPlanId")
	private String testPlanId;
	
	

	public void setTestPlanId(String testPlanId) {
		this.testPlanId = testPlanId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExecutionDate(String executionTime) {
		this.executionTime = executionTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void setResult(String result) {
		this.result = result;
	}

}