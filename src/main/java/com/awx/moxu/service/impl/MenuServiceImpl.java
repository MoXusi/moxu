package com.awx.moxu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.Func;
import com.awx.moxu.vo.MenuVO;
import com.awx.moxu.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.service.MenuService;
import com.awx.moxu.mapper.MenuMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author admin
* @description 针对表【menu(菜单表)】的数据库操作Service实现
* @createDate 2023-02-14 21:55:25
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{


    @Override
    public List<MenuVO> routes(String roleId) {
        if (StrUtil.isBlank(roleId)) {
            return null;
        }
        List<Menu> allMenus = baseMapper.allMenu();
        List<Menu> roleMenus = baseMapper.roleMenu(Func.toStringList(roleId));
        List<Menu> routes = new LinkedList<>(roleMenus);
        roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
        routes.sort(Comparator.comparing(Menu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper();
        List<Menu> collect = routes.stream().filter(x -> x.getCategory().equals(1)).collect(Collectors.toList());
        return menuWrapper.listNodeVO(collect);
    }
    public void recursion(List<Menu> allMenus, List<Menu> routes, Menu roleMenu) {
        Optional<Menu> menu = allMenus.stream().filter(x -> x.getId().equals(roleMenu.getParentId())).findFirst();
        if (menu.isPresent() && !routes.contains(menu.get())) {
            routes.add(menu.get());
            recursion(allMenus, routes, menu.get());
        }
    }

    @Override
    public List<MenuVO> buttons(String roleId) {
        List<Menu> buttons = baseMapper.buttons(Func.toStringList(roleId));
        MenuWrapper menuWrapper = new MenuWrapper();
        return menuWrapper.listNodeVO(buttons);
    }

    @Override
    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }


}




