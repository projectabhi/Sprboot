package com.borokali.model.res;

public class StudentResponse {

	private int rollNo;
	private String addr;
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
	@Override
	public String toString() {
		return "StudentResponse [rollNo=" + rollNo + ", addr=" + addr + "]";
	}
	
}
