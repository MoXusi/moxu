package com.awx.moxu.mapper;

import com.awx.moxu.dto.MenuDTO;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.vo.MenuVO;
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

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

    /**
     * 授权树形结构
     *
     * @return
     */
    List<MenuVO> grantTree();

    /**
     * 授权树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> grantTreeByRole(List<String> roleId);

    /**
     * 菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> routes(List<String> roleId);


    /**
     * 获取配置的角色权限
     *
     * @param roleIds
     * @return
     */
    List<MenuDTO> authRoutes(List<String> roleIds);
}




