package com.awx.moxu.mapper;

import com.awx.moxu.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author admin
* @description 针对表【menu(菜单表)】的数据库操作Mapper
* @createDate 2023-02-14 21:55:25
* @Entity com.awx.moxu.entity.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 所有菜单
     *
     * @return
     */
    List<Menu> allMenu();



    /**
     * 权限配置菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> roleMenu(List<String> roleId);


    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> buttons(List<String> roleId);
}




