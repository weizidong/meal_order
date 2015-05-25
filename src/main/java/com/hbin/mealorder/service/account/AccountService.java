package com.hbin.mealorder.service.account;

import com.hbin.mealorder.model.dao.account.AccountDao;
import com.hbin.mealorder.model.entity.account.Account;
import com.hbin.mealorder.service.wx.user.WechartUserService;
import com.hbin.mealorder.service.wx.user.dto.WeixinUserInfo;
import com.lifesense.framework.common.util.StringUtil;

public class AccountService {
	private AccountDao accountDao = new AccountDao();

	WechartUserService wechartUserService = new WechartUserService();

	/**
	 * 保存Account
	 * 
	 * @param openId
	 * @return
	 */
	public Account saveAccount(String openId) {
		
		Account account = accountDao.getByOpenId(openId);
		boolean isNew = account == null;

		if (isNew) {
			account = Account.generate(openId);

		}

		boolean isUpdate = StringUtil.isEmpty(account.getNickname());

		if (isUpdate) {
			WeixinUserInfo userInfo = wechartUserService.getUserInfo(openId, null);
			if (userInfo != null) {
				String headimgurl = userInfo.getHeadimgurl();
				String nickname = userInfo.getNickname();

				account.setHeadimg(headimgurl);
				account.setNickname(nickname);
			}
		}

		if (isNew) {
			accountDao.create(account);
		} else if (isUpdate) {
			accountDao.update(account);
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
