package com.awx.moxu.service.impl;

import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.Func;
import com.awx.moxu.vo.DeptVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Dept;
import com.awx.moxu.service.DeptService;
import com.awx.moxu.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Override
    public List<DeptVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }
    @Override
    public List<String> getDeptNames(String deptIds) {
        return baseMapper.getDeptNames(Func.toStrArray(deptIds));
    }

    @Override
    public String getDeptIds(String deptNames) {
        List<Dept> deptList = baseMapper.selectList(Wrappers.<Dept>query().lambda().in(Dept::getDeptName, Func.toStrArray(deptNames)));
        if (deptList != null && deptList.size() > 0) {
            return deptList.stream().map(dept -> Func.toStr(dept.getId())).distinct().collect(Collectors.joining(","));
        }
        return null;
    }

}




