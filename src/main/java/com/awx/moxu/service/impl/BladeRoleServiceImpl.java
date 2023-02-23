package com.awx.moxu.service.impl;

import com.awx.moxu.entity.RoleMenu;
import com.awx.moxu.service.RoleMenuService;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.vo.RoleVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.service.BladeRoleService;
import com.awx.moxu.mapper.BladeRoleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class BladeRoleServiceImpl extends ServiceImpl<BladeRoleMapper, BladeRole>
implements BladeRoleService{

    RoleMenuService roleMenuService;

    @Override
    public List<RoleVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public boolean grant(List<String> roleIds,List<String> menuIds) {
        // 删除角色配置的菜单集合
        roleMenuService.remove(Wrappers.<RoleMenu>update().lambda().in(RoleMenu::getRoleId, roleIds));
        // 组装配置
        List<RoleMenu> roleMenus = new ArrayList<>();
        roleIds.forEach(roleId -> menuIds.forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenus.add(roleMenu);
        }));
        // 新增配置v
        return roleMenuService.saveBatch(roleMenus);
    }
}




