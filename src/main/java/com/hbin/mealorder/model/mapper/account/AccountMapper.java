package com.hbin.mealorder.model.mapper.account;

import com.hbin.mealorder.model.entity.account.Account;
import com.lifesense.framework.mybatis.mapper.BaseMapper;

public interface AccountMapper extends BaseMapper<Account, String> {
	/**
	 * 根据OpenId获取Account
	 * 
	 * @param openId
	 * @return
	 */
	Account getByOpenId(String openId);

}
