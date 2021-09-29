package org.wtt.docker.listener;

import java.time.LocalDateTime;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;


public class ExecutionListener implements ITestListener {
	
	private TestStatus testStatus;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long sTime;
	private Long eTime;
	private String result;

	public void onTestStart(ITestResult iTestResult) {
		this.testStatus = new TestStatus();
	}

	public void onTestSuccess(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "PASS");
		this.result = "PASSED";
	}

	public void onTestFailure(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "FAIL");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		this.sendStatus(iTestResult, "SKIPPED");
		this.result = "SKIPPED";
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// skip
	}

	public void onStart(ITestContext iTestContext) {
		sTime = System.currentTimeMillis();

	}

	public void onFinish(ITestContext iTestContext) {
		// skip
	}

	public void onTestFailure1(ITestResult Result) {
		System.out.println("The name of the testcase failed is :" + Result.getThrowable().getMessage());
		result = Result.getThrowable().getMessage();
		testStatus.setResult(result);
	}

	private void sendStatus(ITestResult iTestResult, String status) {
		eTime = System.currentTimeMillis();
		this.testStatus.setTestClass(iTestResult.getTestClass().getName());
		this.testStatus.setDescription(iTestResult.getMethod().getDescription());
		this.testStatus.setTestName(iTestResult.getMethod().getMethodName());
		this.testStatus.setStatus(status);
		this.testStatus.setExecutionDate(LocalDateTime.now().toString());
		this.testStatus.setEndTime(eTime + "");
		this.testStatus.setStartTime(sTime + "");
		if(status.equalsIgnoreCase("PASS")){
			this.testStatus.setResult("PASS");

		}else if(status.equalsIgnoreCase("SKIPPED")) {
			this.testStatus.setResult("SKIPPED");
		}else if(status.equalsIgnoreCase( "FAIL")) {
			this.testStatus.setResult(iTestResult.getThrowable().getLocalizedMessage());
		}
		
		long el = eTime - sTime;
		this.testStatus.setDuration(el + "");
		this.testStatus.setTestPlanId(PropertiesUtility.properties.getProperty("test.planId"));
	        this.testStatus.setTestPlanName(PropertiesUtility.properties.getProperty("test.planName"));

		ResultSender.send(this.testStatus);
	}

}
