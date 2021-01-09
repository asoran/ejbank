package com.ejbank.api.payload.transaction;

public class TransactionValidationPayload {
	private int transaction;
	private boolean approve;
	private int author;

	public void setTransaction(int transaction) {
		this.transaction = transaction;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getTransaction() {
		return transaction;
	}

	public boolean isApprove() {
		return approve;
	}

	public int getAuthor() {
		return author;
	}
}
