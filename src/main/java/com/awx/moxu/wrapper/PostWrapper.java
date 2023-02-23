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
package com.awx.moxu.wrapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.awx.moxu.entity.Post;
import com.awx.moxu.service.DictService;
import com.awx.moxu.utils.ForestNode.BaseEntityWrapper;
import com.awx.moxu.vo.PostVO;

import java.util.Objects;

/**
 * 岗位表包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class PostWrapper extends BaseEntityWrapper<Post, PostVO> {

	private static DictService dictService;

	static {
		dictService = SpringUtil.getBean(DictService.class);
	}

	public static PostWrapper build() {
		return new PostWrapper();
	}

	@Override
	public PostVO entityVO(Post post) {
		PostVO postVO = Objects.requireNonNull(BeanUtil.copyProperties(post, PostVO.class));
		String categoryName = dictService.getValue("post_category", post.getCategory());
		postVO.setCategoryName(categoryName);
		return postVO;
	}

}
