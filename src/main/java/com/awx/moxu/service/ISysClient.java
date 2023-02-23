package com.awx.moxu.service;

import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.entity.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ISysClient {

    /**
     * 获取部门
     *
     * @param id 主键
     * @return Dept
     */
    Dept getDept(String id);

    /**
     * 获取部门名
     *
     * @param id 主键
     * @return 部门名
     */
    String getDeptName(String id);

    /**
     * 获取部门id
     *
     * @param deptNames 部门名
     * @return 部门id
     */
    String getDeptIds(String deptNames);

    /**
     * 获取部门名
     *
     * @param deptIds 主键
     * @return
     */
    List<String> getDeptNames(String deptIds);

    /**
     * 获取岗位id
     *
     * @param postNames 岗位名
     * @return 岗位id
     */
    String getPostIds(String postNames);

    /**
     * 获取岗位名
     *
     * @param postIds 主键
     * @return
     */
    List<String> getPostNames(String postIds);


    /**
     * 获取角色
     *
     * @param id 主键
     * @return Role
     */
    BladeRole getRole(String id);

    /**
     * 获取角色id
     *
     * @param roleNames 角色名
     * @return 角色id
     */
    String getRoleIds(String roleNames);

    /**
     * 获取角色名
     *
     * @param id 主键
     * @return 角色名
     */
    String getRoleName(String id);

    /**
     * 获取角色名
     *
     * @param roleIds 主键
     * @return
     */
    List<String> getRoleNames(String roleIds);

    /**
     * 获取角色别名
     *
     * @param id 主键
     * @return 角色别名
     */
    String getRoleAlias(String id);
}
