package com.ejbank.api.payload.transaction;

public class TransactionPreviewPayload {
	private int source;
	private int destination;
	private double amount;
	private int author;

	public TransactionPreviewPayload(int source, int destination, double amount, int author) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
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

	public int getAuthor() {
		return author;
	}
}
