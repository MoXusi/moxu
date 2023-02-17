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
import com.awx.moxu.controller.CacheNames;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.service.impl.BladeUserServiceImpl;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.RedisUtils;
import com.awx.moxu.utils.TokenUtil;
import com.awx.moxu.utils.WebUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component
@AllArgsConstructor
public class CaptchaTokenGranter implements ITokenGranter {

	public static final String GRANT_TYPE = "captcha";

	@Resource
	private BladeUserServiceImpl userClient;
	@Resource
	RedisUtils redisUtils;
	@Override
	public UserInfo grant(Map<String, Object> tokenParameter) throws Exception {
		HttpServletRequest request = WebUtils.getRequest();

		String key = request.getHeader(TokenUtil.CAPTCHA_HEADER_KEY);
		String code = request.getHeader(TokenUtil.CAPTCHA_HEADER_CODE);
		// 获取验证码
		String redisCode = String.valueOf(redisUtils.get(CacheNames.CAPTCHA_KEY + key));
		// 判断验证码
		if (code == null || !redisCode.equals(code)) {
			throw new Exception(TokenUtil.CAPTCHA_NOT_CORRECT);
		}
		String account = (String) tokenParameter.get("account");
		String password = (String) tokenParameter.get("password");
		UserInfo userInfo = null;
		if (!StrUtil.hasBlank(account, password)) {
			userInfo= userClient.userInfo(account, password);
		}
		return userInfo;
	}
}
