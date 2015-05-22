package com.hbin.mealorder.api.account;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbin.mealorder.service.account.AccountService;

@Path("account")
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountApi {
	private AccountService accountService = new AccountService();
}
