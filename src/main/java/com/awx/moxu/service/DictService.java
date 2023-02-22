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
}
