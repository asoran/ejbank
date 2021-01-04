package com.ejbank.api;

import com.ejbank.api.payload.AccountPayload;
import com.ejbank.entities.Account;
import com.ejbank.haricots.AccountBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountServer {

    @EJB
    private AccountBean accountBean;

    @GET
    @Path("/{id}")
    public List<AccountPayload> getAllUserAccount(@PathParam("id") int id) {
        List<Account> accounts = accountBean.getAccountsByCustomerId(id);
        return accounts.stream().map(AccountPayload::build).collect(Collectors.toList());
    }

}
