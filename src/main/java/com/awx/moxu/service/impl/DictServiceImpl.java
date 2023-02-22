package com.awx.moxu.service.impl;

import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.vo.DictVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Dict;
import com.awx.moxu.service.DictService;
import com.awx.moxu.mapper.DictMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
implements DictService{

    @Override
    public List<DictVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }
}




