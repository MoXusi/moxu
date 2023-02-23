package com.awx.moxu.mapper;

import com.awx.moxu.entity.Dept;
import com.awx.moxu.vo.DeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.awx.moxu.entity.Dept
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 获取树形节点
     * @return
     */
    List<DeptVO> tree();
}




