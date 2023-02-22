package com.awx.moxu.mapper;

import com.awx.moxu.entity.Dict;
import com.awx.moxu.vo.DictVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.awx.moxu.entity.Dict
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 获取树形节点
     *
     * @return
     */
    List<DictVO> tree();
}




