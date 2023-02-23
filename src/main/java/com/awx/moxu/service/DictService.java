package com.awx.moxu.service;

import com.awx.moxu.entity.Dict;
import com.awx.moxu.vo.DictVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface DictService extends IService<Dict> {


    /**
     * 树形结构
     *
     * @return
     */
    List<DictVO> tree();

    /**
     * 新增或修改
     * @param dict
     * @return
     */
    boolean submit(Dict dict);

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
