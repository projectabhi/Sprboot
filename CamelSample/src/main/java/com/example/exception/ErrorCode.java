package com.example.exception;

public enum ErrorCode {

	RETRY_EXCEPTION(1001),
	CONNECT_EXCEPTION(1002);
	
	private final int errCode;

	private ErrorCode(int errCode) {
		this.errCode = errCode;
	}

	public int getErrCode() {
		return errCode;
	}
	
}
