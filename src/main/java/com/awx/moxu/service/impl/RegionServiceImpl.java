package com.awx.moxu.service.impl;

import com.awx.moxu.filter.BizException;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.blade.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Region;
import com.awx.moxu.service.RegionService;
import com.awx.moxu.mapper.RegionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
implements RegionService{
    public static final int PROVINCE_LEVEL = 1;
    public static final int CITY_LEVEL = 2;
    public static final int DISTRICT_LEVEL = 3;
    public static final int TOWN_LEVEL = 4;
    public static final int VILLAGE_LEVEL = 5;

    @Override
    public boolean submit(Region region) {
        Integer cnt = Math.toIntExact(baseMapper.selectCount(Wrappers.<Region>query().lambda().eq(Region::getCode, region.getCode())));
        if (cnt > 0) {
            return this.updateById(region);
        }
        // 设置祖区划编号
        Region parent = baseMapper.selectById(region.getParentCode());
        if (Func.isNotEmpty(parent) || Func.isNotEmpty(parent.getCode())) {
            String ancestors = parent.getAncestors() + StringPool.COMMA + parent.getCode();
            region.setAncestors(ancestors);
        }
        // 设置省、市、区、镇、村
        Integer level = region.getLevel();
        String code = region.getCode();
        String name = region.getName();
        if (level == PROVINCE_LEVEL) {
            region.setProvinceCode(code);
            region.setProvinceName(name);
        } else if (level == CITY_LEVEL) {
            region.setCityCode(code);
            region.setCityName(name);
        } else if (level == DISTRICT_LEVEL) {
            region.setDistrictCode(code);
            region.setDistrictName(name);
        } else if (level == TOWN_LEVEL) {
            region.setTownCode(code);
            region.setTownName(name);
        } else if (level == VILLAGE_LEVEL) {
            region.setVillageCode(code);
            region.setVillageName(name);
        }
        return this.save(region);
    }

    @Override
    public boolean removeRegion(String id) {
        Integer cnt = Math.toIntExact(baseMapper.selectCount(Wrappers.<Region>query().lambda().eq(Region::getParentCode, id)));
        if (cnt > 0) {
            throw new BizException("请先删除子节点!");
        }
        return removeById(id);
    }

    @Override
    public List<INode> lazyList(String parentCode, Map<String, Object> param) {
        return baseMapper.lazyList(parentCode, param);
    }

    @Override
    public List<INode> lazyTree(String parentCode, Map<String, Object> param) {
        return baseMapper.lazyTree(parentCode, param);
    }

}




