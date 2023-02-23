package com.awx.moxu.service;

import com.awx.moxu.entity.Dict;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【menu(菜单表)】的数据库操作Service
* @createDate 2023-02-14 21:55:25
*/
public interface MenuService extends IService<Menu> {

    /**
     * 菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> routes(String roleId);

    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> buttons(String roleId);

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

}
