package com.ejbank.api;

import com.ejbank.api.payload.AccountDetailPayload;
import com.ejbank.entities.Account;
import com.ejbank.haricots.AccountBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountServer {

    @EJB
    private AccountBean accountBean;

    @GET
    @Path("/{account_id}/{user_id}")
    public AccountDetailPayload getAccountDetail(@PathParam("account_id") int accountId, @PathParam("user_id") int userId) {
        Account account = accountBean.getAccountById(accountId);
        /* todo : do error */
        double interest = accountBean.computeInterest(account);
        return new AccountDetailPayload(account, interest);
    }
}
