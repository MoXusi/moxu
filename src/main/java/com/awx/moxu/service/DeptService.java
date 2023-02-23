package com.awx.moxu.service;

import com.awx.moxu.entity.Dept;
import com.awx.moxu.vo.DeptVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface DeptService extends IService<Dept> {
    /**
     * 树形结构
     * @return
     */
    List<DeptVO> tree();
}
