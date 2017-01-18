package com.xiaochi.chat.exception;

public class NotInputMustSquenceException extends Exception {

	public NotInputMustSquenceException() {
	}

	public NotInputMustSquenceException(Throwable t) {
		super(t);
	}
	public NotInputMustSquenceException(String mesg){
		super(mesg);
	}

	public NotInputMustSquenceException(String mesg, Throwable cause) {
		super(
				"not input music squence ,please input you want to filter conditions.\n"
						+ mesg, cause);
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}
