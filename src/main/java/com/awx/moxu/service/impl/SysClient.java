package com.awx.moxu.service.impl;

import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.entity.Dept;
import com.awx.moxu.service.BladeRoleService;
import com.awx.moxu.service.DeptService;
import com.awx.moxu.service.ISysClient;
import com.awx.moxu.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SysClient implements ISysClient {
    private DeptService deptService;

    private PostService postService;

    private BladeRoleService roleService;
    @Override
    public Dept getDept(String id) {
        return deptService.getById(id);
    }

    @Override
    public String getDeptName(String id) {
        return deptService.getById(id).getDeptName();
    }

    @Override
    public String getDeptIds(String deptNames) {
        return deptService.getDeptIds(deptNames);
    }

    @Override
    public List<String> getDeptNames(String deptIds) {
        return deptService.getDeptNames(deptIds);
    }

    @Override
    public String getPostIds(String postNames) {
        return postService.getPostIds(postNames);
    }

    @Override
    public List<String> getPostNames(String postIds) {
        return postService.getPostNames(postIds);
    }

    @Override
    public BladeRole getRole(String id) {
        return roleService.getById(id);
    }

    @Override
    public String getRoleIds(String roleNames) {
        return roleService.getRoleIds(roleNames);
    }

    @Override
    public String getRoleName(String id) {
        return roleService.getById(id).getRoleName();
    }

    @Override
    public List<String> getRoleNames(String roleIds) {
        return roleService.getRoleNames(roleIds);
    }

    @Override
    public String getRoleAlias(String id) {
        return roleService.getById(id).getRoleAlias();
    }
}
