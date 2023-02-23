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

    /**
     * 获取字典表对应中文
     *
     * @param code    字典编号
     * @param dictKey 字典序号
     * @return
     */
    String getValue(String code, Integer dictKey);

    /**
     * 获取字典表
     *
     * @param code 字典编号
     * @return
     */
    List<Dict> getList(String code);
}




