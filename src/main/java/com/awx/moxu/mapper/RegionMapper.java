package com.awx.moxu.mapper;

import com.awx.moxu.entity.Region;
import com.awx.moxu.utils.ForestNode.INode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.awx.moxu.entity.Region
 */
@Mapper
public interface RegionMapper extends BaseMapper<Region> {
    /**
     * 懒加载列表
     *
     * @param parentCode
     * @param param
     * @return
     */
    List<INode> lazyList(String parentCode, Map<String, Object> param);

    /**
     * 懒加载列表
     *
     * @param parentCode
     * @param param
     * @return
     */
    List<INode> lazyTree(String parentCode, Map<String, Object> param);

}




