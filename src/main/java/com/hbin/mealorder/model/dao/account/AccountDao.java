package com.hbin.mealorder.model.dao.account;

import com.hbin.mealorder.model.entity.account.Account;
import com.hbin.mealorder.model.mapper.account.AccountMapper;
import com.lifesense.framework.mybatis.dao.BaseDAO;

public class AccountDao extends BaseDAO<Account, String, AccountMapper> {
	/**
	 * 根据OpenId获取Account
	 * 
	 * @param openId
	 * @return
	 */
	public Account getByOpenId(String openId) {
		return getMapper().getByOpenId(openId);
	}

}
