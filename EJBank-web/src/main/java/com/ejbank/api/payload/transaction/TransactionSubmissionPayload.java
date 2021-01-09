package com.ejbank.api.payload.transaction;

public class TransactionSubmissionPayload {
	private int source;
	private int destination;
	private double amount;
	private String comment;
	private int author;

	public TransactionSubmissionPayload(int source, int destination, double amount, String comment, int author) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
		this.comment = comment;
		this.author = author;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

	public double getAmount() {
		return amount;
	}

	public String getComment() {
		return comment;
	}

	public int getAuthor() {
		return author;
	}
}
