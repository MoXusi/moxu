package com.awx.moxu.granter; /**
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


import cn.hutool.extra.spring.SpringUtil;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TokenGranterBuilder
 *
 * @author Chill
 */
@AllArgsConstructor
public class TokenGranterBuilder {

    /**f
     * TokenGranter缓存池
     */
    private static final Map<String, ITokenGranter> GRANTER_POOL = new ConcurrentHashMap<>();

    static {
        GRANTER_POOL.put(PasswordTokenGranter.GRANT_TYPE, SpringUtil.getBean(PasswordTokenGranter.class));
        GRANTER_POOL.put(CaptchaTokenGranter.GRANT_TYPE, SpringUtil.getBean(CaptchaTokenGranter.class));
    }

    /**
     * 获取TokenGranter
     *
     * @param grantType 授权类型
     * @return ITokenGranter
     */
    public static ITokenGranter getGranter(String grantType) throws Exception {
        ITokenGranter tokenGranter = GRANTER_POOL.get(grantType);
        if (tokenGranter == null) {
            throw new Exception("no grantType was found");
        } else {
            return tokenGranter;
        }
    }

}
