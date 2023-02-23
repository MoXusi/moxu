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
package com.awx.moxu.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.awx.moxu.constant.CacheNames;
import com.awx.moxu.entity.AuthInfo;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.granter.ITokenGranter;
import com.awx.moxu.granter.TokenGranterBuilder;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.RedisUtils;
import com.awx.moxu.utils.TokenUtil;
import com.wf.captcha.SpecCaptcha;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 认证模块
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	@Resource
	RedisUtils redisUtils;

	@PostMapping("token")
	public R<AuthInfo> token(@RequestParam(defaultValue = "password", required = false) String grantType,
							 @RequestParam(required = false) String refreshToken,
							 @RequestParam(required = false) String account,
							 @RequestParam(required = false) String password) throws Exception {

		Map<String,Object> map = new HashMap<>();
		map.put("account",account);
		map.put("password",password);
		map.put("refreshToken",refreshToken);
		map.put("grantType",grantType);
		ITokenGranter granter = TokenGranterBuilder.getGranter(grantType);
		UserInfo userInfo = granter.grant(map);

		if (userInfo == null || userInfo.getUser() == null || userInfo.getUser().getId() == null) {
			return R.fail(TokenUtil.USER_NOT_FOUND);
		}
		return R.data(TokenUtil.createAuthInfo(userInfo));
	}

	@GetMapping("/captcha")
	public R<Object> captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		String key = UUID.randomUUID().toString();
		// 存入redis并设置过期时间为30分钟
		redisUtils.set(CacheNames.CAPTCHA_KEY + key, verCode, 30L);
		JSONObject json = JSONUtil.createObj();
		json.putOpt("key", key);
		json.putOpt("image", specCaptcha.toBase64());
		// 将key和base64返回给前端
		return R.data(json);
	}

}
