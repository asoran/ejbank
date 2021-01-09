package com.ejbank.api;

import com.ejbank.api.payload.AccountPayload;
import com.ejbank.api.payload.transaction.*;
import com.ejbank.entities.Account;
import com.ejbank.entities.Transaction;
import com.ejbank.entities.User;
import com.ejbank.entities.Account;
import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.TransactionBean;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Comparator;
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
	public TransactionPreviewResponsePayload getTransactionPreview(TransactionPreviewPayload transactionPreview) {
		Account srcAcc = accountBean.getAccountById(transactionPreview.getSource());

		boolean result = true;
		double srcAmount = srcAcc.getBalance();
		int overdraft = srcAcc.getAccountType().getOverdraft();
		double amount = transactionPreview.getAmount();
		String message = null;

		if(srcAmount + overdraft < amount) {
			result = false;
			message = "Vous ne disposez pas d'un solde suffisant...";
		}

		return new TransactionPreviewResponsePayload(result, srcAmount, srcAmount - amount, message, null);
	}

	@POST
	@Path("/apply")
	public TransactionSubmissionResponsePayload applyTransaction(TransactionSubmissionPayload transactionSubmission) {
		boolean result = false;
		Account srcAcc = accountBean.getAccountById(transactionSubmission.getSource());
		double srcAmount = srcAcc.getBalance();
		int overdraft = srcAcc.getAccountType().getOverdraft();
		double amount = transactionSubmission.getAmount();

		if(srcAmount + overdraft >= amount) {
			result = true;
			transactionBean.addNewTransaction(transactionSubmission.getSource(), transactionSubmission.getDestination(),
				transactionSubmission.getAmount(), transactionSubmission.getComment(),
				transactionSubmission.getAuthor());
			// Et appliquer la transaction dans la balance ????????
		}

		return new TransactionSubmissionResponsePayload(result, result
			? "La transaction a été réalisée avec succès !"
			: "Vous ne disposez pas d'un solde suffisant");
	}

	@POST
	@Path("/validation")
	public TransactionValidationResponsePayload validateTransaction(TransactionValidationPayload transactionValidation) {
		boolean isApprove = transactionValidation.isApprove();
		// TODO: A t'il le droit de valider cette demande ????
		if(true) {
			Transaction tr = transactionBean.getTransactionsById(transactionValidation.getTransaction());
			tr.setApplied(isApprove);
			transactionBean.update(tr);
		}

		return new TransactionValidationResponsePayload(true,
			(isApprove ? "Approuvé" : "Refusé") + " avec succès !", null);
	}

	@GET
	@Path("/validation/notification/{user_id}")
	public int getValidationNotificationNumberById(@PathParam("user_id") int userId) {
		ArrayList<Transaction> list = new ArrayList<>();
		accountBean.getAccountsByCustomerId(userId).forEach(acc -> {
			list.addAll(transactionBean.getAllTransactionsByAccountId(acc.getId()));
		});
		return list.size();
	}
}
