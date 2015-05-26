package com.hbin.mealorder.api.account;

import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbin.mealorder.model.entity.account.Account;
import com.hbin.mealorder.service.account.AccountService;

@Path("account")
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountApi {
	private AccountService accountService = new AccountService();

	@POST
	@Path("get_account/{accountId}")
	public Account getAccount(@PathParam("accountId") String accountId) {
		return accountService.getAccount(accountId);

	}

	@POST
	@Path("set_remark_name")
	public void setRemarkName(Map<String, String> params) {
		String accountId = params.get("accountId");
		String remarkName = params.get("remarkName");
		accountService.setRemarkName(accountId, remarkName);
	}
}
