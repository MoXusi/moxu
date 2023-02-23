package com.awx.moxu.service;

import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.vo.RoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface BladeRoleService extends IService<BladeRole> {
    /**
     * 树形结构
     *
     * @return
     */
    List<RoleVO> tree();
    /**
     * 权限配置
     *
     * @param roleIds 角色id集合
     * @param menuIds 菜单id集合
     * @return 是否成功
     */
    boolean grant(List<String> roleIds,List<String> menuIds);

    /**
     * 获取角色ID
     *
     * @param roleNames
     * @return
     */
    String getRoleIds(String roleNames);

    /**
     * 获取角色名
     *
     * @param roleIds
     * @return
     */
    List<String> getRoleNames(String roleIds);
}
