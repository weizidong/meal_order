package com.hbin.mealorder.service.account;

import com.hbin.mealorder.model.dao.account.AccountDao;
import com.hbin.mealorder.model.entity.account.Account;

public class AccountService {
	private AccountDao accountDao = new AccountDao();

	/**
	 * 保存Account
	 * 
	 * @param openId
	 * @return
	 */
	public Account saveAccount(String openId) {
		Account account = accountDao.getByOpenId(openId);
		if (account == null) {
			account = Account.generate(openId);
			accountDao.create(account);
		}
		return account;
	}

	/**
	 * 获取Account
	 * 
	 * @param accountId
	 * @return
	 */
	public Account getAccount(String accountId) {
		return null;
	}
}
