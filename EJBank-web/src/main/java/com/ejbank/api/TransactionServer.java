package com.ejbank.api;

import com.ejbank.api.payload.transaction.*;
import com.ejbank.entities.Account;
import com.ejbank.entities.Customer;
import com.ejbank.entities.Transaction;
import com.ejbank.entities.User;
import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.TransactionBean;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
	public TransactionListResponsePayload listTransaction(@PathParam("account_id") int accountId,
														  @PathParam("offset") int offset,
														  @PathParam("user_id") int userId) {

		Optional<Account> account = accountBean.getAccountById(accountId);
		if ( !account.isPresent() ) {
			return new TransactionListResponsePayload("Unknown account");
		}

		if ( offset < 0 ) {
			return new TransactionListResponsePayload("Offset cannot be lower that 0");
		}

		Optional<User> user = userBean.getUserById(userId);
		if ( user.isPresent() ) {
			if ( user.get().getType().equals("customer") ) {
				if ( account.get().getCustomer().getId() != userId ) {
					return new TransactionListResponsePayload("Access forbidden");
				} else {
					List<TransactionPayload> tps = transactionBean.getAllTransactionsByAccountId(accountId, offset)
							.stream().map( t -> new TransactionPayload(t, user.get().getType()))
							.collect(Collectors.toList());

					return new TransactionListResponsePayload(tps.size(), tps);
				}
			} else {
				if ( account.get().getCustomer().getAdvisor().getId() != userId ) {
					return new TransactionListResponsePayload("Access forbidden");
				} else {
					List<TransactionPayload> tps = transactionBean.getAllTransactionsByAccountId(accountId, offset)
							.stream().map( t -> new TransactionPayload(t, user.get().getType()))
							.collect(Collectors.toList());

					return new TransactionListResponsePayload(tps.size(), tps);
				}
			}
		} else {
			return new TransactionListResponsePayload("Unknown user");
		}
	}

	@POST
	@Path("/preview")
	public TransactionPreviewResponsePayload getTransactionPreview(TransactionPreviewPayload transactionPreview) {
		Optional<Account> oa = accountBean.getAccountById(transactionPreview.getSource());

		if ( !oa.isPresent() ) {
			return new TransactionPreviewResponsePayload("Unknown source account");
		}

		Account srcAcc = oa.get();

		boolean result = true;
		double srcAmount = srcAcc.getBalance();
		int overdraft = srcAcc.getAccountType().getOverdraft();
		double amount = transactionPreview.getAmount();
		String message = null;

		if(srcAmount + overdraft < amount) {
			result = false;
			message = "Vous ne disposez pas d'un solde suffisant...";
		}

		return new TransactionPreviewResponsePayload(result, srcAmount, srcAmount - amount, message);
	}

	@POST
	@Path("/apply")
	public TransactionSubmissionResponsePayload applyTransaction(TransactionSubmissionPayload transactionSubmission) {
		boolean result = false;
		Optional<Account> oa = accountBean.getAccountById(transactionSubmission.getSource());

		if ( !oa.isPresent() ) {
			return new TransactionSubmissionResponsePayload(false, "Unknown source account");
		}

		Account srcAcc = oa.get();
		if(srcAcc.getCustomer().getId() != transactionSubmission.getAuthor() &&
			srcAcc.getCustomer().getAdvisor().getId() != transactionSubmission.getAuthor()) {
			return new TransactionSubmissionResponsePayload(false, "Unauthorized access");
		}

		double srcAmount = srcAcc.getBalance();
		int overdraft = srcAcc.getAccountType().getOverdraft();
		double amount = transactionSubmission.getAmount();

		if(srcAmount + overdraft >= amount) {
			result = true;

			transactionBean.addNewTransaction(transactionSubmission.getSource(), transactionSubmission.getDestination(),
				transactionSubmission.getAmount(), transactionSubmission.getComment(),
				transactionSubmission.getAuthor(), amount < 1000);
			// TODO: Et appliquer la transaction dans la balance ????????
		}

		return new TransactionSubmissionResponsePayload(result, result
			? "La transaction a été réalisée avec succès !"
			: "Vous ne disposez pas d'un solde suffisant");
	}

	@POST
	@Path("/validation")
	public TransactionValidationResponsePayload validateTransaction(TransactionValidationPayload transactionValidation) {
		boolean isApprove = transactionValidation.isApprove();

		Transaction tr = transactionBean.getTransactionsById(transactionValidation.getTransaction());
		if(tr.getAccountFrom().getCustomer().getAdvisor().getId() != transactionValidation.getAuthor()) {
			return new TransactionValidationResponsePayload(false, "You can't validate this transaction",
			"Unauthorized access");
		}

		tr.setApplied(isApprove);
		transactionBean.update(tr);
		return new TransactionValidationResponsePayload(true,
			(isApprove ? "Approuvé" : "Refusé") + " avec succès !", null);
	}

	@GET
	@Path("/validation/notification/{user_id}")
	public long getValidationNotificationNumberById(@PathParam("user_id") int userId) {
		return accountBean.getAccountsByCustomerId(userId).stream()
				.mapToLong(accountBean::getNumberOfPendingValidation).sum();
	}
}
