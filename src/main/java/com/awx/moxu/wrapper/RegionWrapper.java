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
import com.awx.moxu.entity.Region;
import com.awx.moxu.service.RegionService;
import com.awx.moxu.utils.ForestNode.BaseEntityWrapper;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.vo.RegionVO;

import java.util.List;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class RegionWrapper extends BaseEntityWrapper<Region, RegionVO> {

	private static RegionService regionService;

	static {
		regionService = SpringUtil.getBean(RegionService.class);
	}

	public static RegionWrapper build() {
		return new RegionWrapper();
	}

	@Override
	public RegionVO entityVO(Region region) {
		RegionVO regionVO = Objects.requireNonNull(BeanUtil.copyProperties(region, RegionVO.class));
		Region parentRegion = regionService.getById(region.getParentCode());
		regionVO.setParentName(parentRegion.getName());
		return regionVO;
	}

	public List<INode> listNodeLazyVO(List<INode> list) {
		return ForestNodeMerger.merge(list);
	}

}
