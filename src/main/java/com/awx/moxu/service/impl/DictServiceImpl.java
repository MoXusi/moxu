package com.awx.moxu.service.impl;

import com.awx.moxu.filter.BizException;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.blade.StringPool;
import com.awx.moxu.vo.DictVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Dict;
import com.awx.moxu.service.DictService;
import com.awx.moxu.mapper.DictMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Override
    public boolean submit(Dict dict) {
        LambdaQueryWrapper<Dict> lqw = Wrappers.<Dict>query().lambda().eq(Dict::getCode, dict.getCode()).eq(Dict::getDictKey, dict.getDictKey());
        Integer cnt = Math.toIntExact(baseMapper.selectCount((Func.isEmpty(dict.getId())) ? lqw : lqw.notIn(Dict::getId, dict.getId())));
        if (cnt > 0) {
            throw new BizException(1003,"当前字典键值已存在!");
        }
        return saveOrUpdate(dict);
    }

    @Override
    public String getValue(String code, Integer dictKey) {
        return Func.toStr(baseMapper.getValue(code, dictKey), StringPool.EMPTY);
    }

    @Override
    public List<Dict> getList(String code) {
        return baseMapper.getList(code);
    }

}




