package com.ejbank.api;

import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.TransactionBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionServer {

	@EJB
	private TransactionBean transactionBean;

	@EJB
	private AccountBean accountBean;

	@GET
	@Path("/list/{account_id}/{offset}/{user_id}")
	public Object listTransaction(@PathParam("account_id") int accountId, @PathParam("offset") int offset, @PathParam("user_id") int userId) {
		return null;
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
