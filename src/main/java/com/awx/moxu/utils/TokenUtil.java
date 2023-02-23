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
package com.awx.moxu.utils;

import com.awx.moxu.entity.AuthInfo;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.incrementer.BaseContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证工具类
 *
 * @author Chill
 */
public class TokenUtil {

	public final static String CAPTCHA_HEADER_KEY = "Captcha-Key";
	public final static String CAPTCHA_HEADER_CODE = "Captcha-Code";
	public final static String CAPTCHA_NOT_CORRECT = "验证码不正确";
	public final static String TENANT_HEADER_KEY = "Tenant-Id";
	public final static String DEFAULT_TENANT_ID = "000000";
	public final static String USER_TYPE_HEADER_KEY = "User-Type";
	public final static String DEFAULT_USER_TYPE = "web";
	public final static String USER_NOT_FOUND = "用户名或密码错误";
	public final static String HEADER_KEY = "Authorization";
	public final static String HEADER_PREFIX = "Basic ";
	public final static String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";

	/**
	 * 创建认证token
	 *
	 * @param userInfo 用户信息
	 * @return token
	 */
	public static AuthInfo createAuthInfo(UserInfo userInfo) {
		BladeUser user = userInfo.getUser();
		BaseContext.setContext(user.getId());
		//设置jwt参数
		Map<String, Object> param = new HashMap<>(16);
		param.put("token_type", "access_token");
		param.put("oauth_id", userInfo.getOauthId());
		param.put("id", user.getId());
		param.put("role_id", user.getRoleId());
		param.put("account", user.getAccount());
		param.put("name", user.getAccount());
		param.put("role_name", userInfo.getRoles().toString());
		String token = JwtUtils.createJwt(param);
		System.out.println("token:"+token);
		System.out.println(user);
		AuthInfo authInfo = new AuthInfo();
		authInfo.setUserId(user.getId());
		authInfo.setOauthId(userInfo.getOauthId());
		authInfo.setAccount(user.getAccount());
		authInfo.setUserName(user.getName());
		authInfo.setAuthority(userInfo.getRoles().get(0));
		authInfo.setAccessToken(token);
		authInfo.setExpiresIn(JwtUtils.JWT_TTL);
		authInfo.setRefreshToken(createRefreshToken(userInfo));
		authInfo.setTokenType("bearer");
		authInfo.setLicense("powered by blade");
		return authInfo;
	}

	/**
	 * 创建refreshToken
	 *
	 * @param userInfo 用户信息
	 * @return refreshToken
	 */
	private static String createRefreshToken(UserInfo userInfo) {
		BladeUser user = userInfo.getUser();
		Map<String, Object> param = new HashMap<>(16);
		param.put("token_type", "access_token");
		param.put("user_id", user.getId());
		String token = JwtUtils.createJwt(param);
		return token;
	}

}
