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
import cn.hutool.core.util.StrUtil;
import com.awx.moxu.constant.CommonConstant;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.service.DictService;
import com.awx.moxu.service.MenuService;
import cn.hutool.extra.spring.SpringUtil;
import com.awx.moxu.utils.ForestNode.BaseEntityWrapper;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.vo.MenuVO;
import sun.security.krb5.internal.PAData;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class MenuWrapper extends BaseEntityWrapper<Menu, MenuVO> {

	private static MenuService menuService;

	private static DictService dictClient;

	static {
		menuService = SpringUtil.getBean(MenuService.class);
		dictClient = SpringUtil.getBean(DictService.class);
	}

	public static MenuWrapper build() {
		return new MenuWrapper();
	}

	@Override
	public MenuVO entityVO(Menu menu) {
		MenuVO menuVO = BeanUtil.copyProperties(menu, MenuVO.class);
		if (Func.equals(menu.getParentId(), CommonConstant.TOP_PARENT_ID)) {
			menuVO.setParentName(CommonConstant.TOP_PARENT_NAME);
		} else {
			Menu parent = menuService.getById(menu.getParentId());
			if(parent==null){
				menuVO.setParentName("上级已删除");
			}else {
				menuVO.setParentName(parent.getName());
			}

		}
		String menuCategory = dictClient.getValue("menu_category", Func.toInt(menuVO.getCategory()));
		String buttonFunc = dictClient.getValue("button_func", Func.toInt(menuVO.getAction()));
		String yesNo = dictClient.getValue("yes_no", Func.toInt(menuVO.getIsOpen()));
		if (!StrUtil.isBlank(menuCategory)) {
			menuVO.setCategoryName(menuCategory);
		}
		if (!StrUtil.isBlank(buttonFunc)) {
			menuVO.setActionName(buttonFunc);
		}
		if (!StrUtil.isBlank(yesNo)) {
			menuVO.setIsOpenName(yesNo);
		}
		return menuVO;
	}
	public List<MenuVO> listNodeVO(List<Menu> list) {
		List<MenuVO> collect = list.stream().map(menu -> BeanUtil.copyProperties(menu, MenuVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}
}
