package com.ejbank.api;

import com.ejbank.api.payload.AccountPayload;
import com.ejbank.api.payload.transaction.TransactionPayload;
import com.ejbank.entities.Account;
import com.ejbank.entities.Transaction;
import com.ejbank.entities.User;
import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.TransactionBean;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionServer {

	@EJB
	private TransactionBean transactionBean;

	@EJB
	private AccountBean accountBean;

	@EJB
	private UserBean userBean;

	@GET
	@Path("/list/{account_id}/{offset}/{user_id}")
	public List<TransactionPayload> listTransaction(@PathParam("account_id") int accountId,
													@PathParam("offset") int offset,
													@PathParam("user_id") int userId) {

		List<Transaction> transactions = transactionBean.getAllTransactionsByAccountId(accountId);
		User ask = userBean.getUserById(userId);
		return transactions.stream().map( t -> new TransactionPayload(t, ask.getType())).collect(Collectors.toList());
	}

	@POST
	@Path("/preview")
	public Object getTransactionPreview(Object transactionPreviewPayload) {

		// calcul
		return null;
	}

	@POST
	@Path("/apply")
	public Object applyTransaction(Object transactionSubmissionPayload) {
		return new Object() {
			String result = "false";
			String message = "lol";
		};// TransactionSubmissionResponsePayload
	}

	@POST
	@Path("/validation")
	public Object validateTransaction(Object transactionValidationPayload) {
		return null; // TransactionValidationResponsePaylaod
	}

	@GET
	@Path("/validation/notification/{user_id}")
	public int getValidationNotificationNumberById(@PathParam("user_id") int userId) {
		return 3;
	}
}
