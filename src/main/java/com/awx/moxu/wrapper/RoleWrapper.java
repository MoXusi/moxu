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
import com.awx.moxu.constant.CommonConstant;
import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.service.BladeRoleService;
import com.awx.moxu.utils.ForestNode.BaseEntityWrapper;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.Func;
import com.awx.moxu.vo.RoleVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class RoleWrapper extends BaseEntityWrapper<BladeRole, RoleVO> {

	private static BladeRoleService roleService;

	static {
		roleService = SpringUtil.getBean(BladeRoleService.class);
	}

	public static RoleWrapper build() {
		return new RoleWrapper();
	}

	@Override
	public RoleVO entityVO(BladeRole role) {
		RoleVO roleVO = BeanUtil.copyProperties(role, RoleVO.class);
		if (Func.equals(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			BladeRole parent = roleService.getById(role.getParentId());
			roleVO.setParentName(parent.getRoleName());
		}
		return roleVO;
	}

	public List<INode> listNodeVO(List<BladeRole> list) {
		List<INode> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}

}
