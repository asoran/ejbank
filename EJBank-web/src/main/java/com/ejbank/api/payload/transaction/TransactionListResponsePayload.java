package com.ejbank.api.payload.transaction;

import java.util.List;

public class TransactionListResponsePayload {

    private final int total;

    private final List<TransactionPayload> transactions;

    private final String error;

    public TransactionListResponsePayload(int total, List<TransactionPayload> transactions) {
        this.total = total;
        this.transactions = transactions;
        this.error = null;
    }

    public TransactionListResponsePayload(String error) {
        this.error = error;
        this.transactions = null;
        this.total = 0;
    }

    public int getTotal() {
        return total;
    }

    public List<TransactionPayload> getTransactions() {
        return transactions;
    }

    public String getError() {
        return error;
    }
}
