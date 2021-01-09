package com.ejbank.api.payload.transaction;

public class TransactionPreviewResponsePayload {
	private final boolean result;
	private final double before;
	private final double after;
	private final String message;
	private final String error;

	public TransactionPreviewResponsePayload(boolean result, double before, double after, String message, String error) {
		this.result = result;
		this.before = before;
		this.after = after;
		this.message = message;
		this.error = error;
	}

	public boolean isResult() {
		return result;
	}

	public double getBefore() {
		return before;
	}

	public double getAfter() {
		return after;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}
}
