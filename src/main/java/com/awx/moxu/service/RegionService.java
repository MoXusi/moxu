package com.awx.moxu.service;

import com.awx.moxu.entity.Region;
import com.awx.moxu.utils.ForestNode.INode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface RegionService extends IService<Region> {
    /**
     * 提交
     *
     * @param region
     * @return
     */
    boolean submit(Region region);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean removeRegion(String id);

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
