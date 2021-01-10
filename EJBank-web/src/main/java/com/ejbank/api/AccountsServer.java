package com.ejbank.api;

import com.ejbank.api.payload.account.*;
import com.ejbank.entities.Advisor;
import com.ejbank.entities.Customer;
import com.ejbank.entities.User;
import com.ejbank.haricots.AccountBean;
import com.ejbank.haricots.AdvisorBean;
import com.ejbank.haricots.CustomerBean;
import com.ejbank.haricots.UserBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountsServer {

    @EJB
    private AccountBean accountBean;

    @EJB
    private CustomerBean customerBean;

    @EJB
    private AdvisorBean advisorBean;

    @EJB
    private UserBean userBean;

    @GET
    @Path("/{id}")
    public CustomerAccountsResponsePayload getCustomerAccounts(@PathParam("id") int id) {
        Optional<Customer> customer = customerBean.getCustomerById(id);

        return customer.map(value -> new CustomerAccountsResponsePayload(
                value.getAccounts().stream().map(AccountPayload::new).collect(Collectors.toList())
        )).orElseGet(() -> new CustomerAccountsResponsePayload("You are not a customer"));
    }

    @GET
    @Path("/attached/{id}")
    public AttachedAccountsResponsePayload getAttachedUserOfAdvisor(@PathParam("id") int id) {
        Optional<Advisor> advisor = advisorBean.getAdvisorById(id);

        return advisor.map(value -> new AttachedAccountsResponsePayload(
                value.getAttachedCustomers().stream().flatMap( c -> c.getAccounts().stream() )
                    .map( a -> new AttachedAccountPayload(a, accountBean.getValidationNumberOfAccount(a)))
                    .collect(Collectors.toList())
        )).orElseGet(() -> new AttachedAccountsResponsePayload("You are not an advisor"));
    }

    @GET
    @Path("/all/{id}")
    public AllAccountsResponsePayload getAllUserAccountWithUserData(@PathParam("id") int id) {
        Optional<User> user = userBean.getUserById(id);

        return user.map( u -> {
            if ( u.getType().equals("advisor") ) {
                Advisor advisor = userBean.recoverAdvisorFromUser(u);
                return new AllAccountsResponsePayload(
                        advisor.getAttachedCustomers().stream()
                                .flatMap( c -> c.getAccounts().stream() )
                                .map(AccountForTransactionPayload::new)
                                .collect(Collectors.toList())
                );
            } else {
                Customer customer = userBean.recoverCustomerFromUser(u);

                return new AllAccountsResponsePayload(
                        customer.getAccounts().stream()
                            .map(AccountForTransactionPayload::new)
                            .collect(Collectors.toList())
                );
            }
        }).orElseGet( () -> new AllAccountsResponsePayload("Unknown user"));
    }

}
