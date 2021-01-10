package com.ejbank.api;

import com.ejbank.api.payload.account.AccountDetailPayload;
import com.ejbank.entities.Account;
import com.ejbank.entities.User;
import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountServer {

    @EJB
    private AccountBean accountBean;

    @EJB
    private UserBean userBean;

    @GET
    @Path("/{account_id}/{user_id}")
    public AccountDetailPayload getAccountDetail(@PathParam("account_id") int accountId, @PathParam("user_id") int userId) {
        Optional<Account> account = accountBean.getAccountById(accountId);

        if ( !account.isPresent() ) {
            return new AccountDetailPayload("Unknown account");
        }

        Optional<User> user = userBean.getUserById(userId);
        if ( !user.isPresent() ) {
            return new AccountDetailPayload("Unknown user");
        }

        if ( !accountBean.checkIfUserHasAccessToAccount(user.get(), account.get()) ) {
            return new AccountDetailPayload("Account access forbidden");
        }

        double interest = accountBean.computeInterest(account.get());
        return new AccountDetailPayload(account.get(), interest);
    }
}
