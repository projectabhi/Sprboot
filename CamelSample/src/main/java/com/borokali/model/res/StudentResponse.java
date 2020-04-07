package com.borokali.model.res;

public class StudentResponse {

	private int rollNo;
	private String addr;
	private int errorCode;
	private String errMsg;
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	@Override
	public String toString() {
		return "StudentResponse [rollNo=" + rollNo + ", addr=" + addr + ", errorCode=" + errorCode + ", errMsg="
				+ errMsg + "]";
	}
	
	
}
