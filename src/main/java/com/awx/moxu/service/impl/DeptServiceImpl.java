package com.awx.moxu.service.impl;

import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.vo.DeptVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Dept;
import com.awx.moxu.service.DeptService;
import com.awx.moxu.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Override
    public List<DeptVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

}




