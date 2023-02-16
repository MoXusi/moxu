/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.awx.moxu.granter;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.service.impl.BladeUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * PasswordTokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class PasswordTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "password";

	@Resource
	private BladeUserServiceImpl userClient;

	@Override
	public UserInfo grant(Map<String, Object> tokenParameter) {
		String account = (String) tokenParameter.get("account");
		String password = (String) tokenParameter.get("password");
		UserInfo userInfo = null;
		if (!StrUtil.hasBlank(account, password)) {
			userInfo= userClient.userInfo(account, password);
		}
		return userInfo;
	}



}
