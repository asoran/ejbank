package com.ejbank.api.payload.transaction;

public class TransactionSubmissionResponsePayload {
	private final boolean result;
	private final String message;

	public TransactionSubmissionResponsePayload(boolean result, String message) {
		this.result = result;
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
}
