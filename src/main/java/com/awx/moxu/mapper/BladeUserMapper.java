package com.awx.moxu.mapper;

import com.awx.moxu.entity.BladeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author admin
* @description 针对表【blade_user(用户表)】的数据库操作Mapper
* @createDate 2023-02-02 21:48:14
* @Entity com.awx.moxu.entity.BladeUser
*/
@Mapper
public interface BladeUserMapper extends BaseMapper<BladeUser> {

    BladeUser queryById(int id);


    /**
     * 获取角色别名
     *
     * @param id
     * @return
     */
    List<String> getRoleAlias(String id);
    /**
     * 获取角色名
     *
     * @param ids
     * @return
     */
    List<String> getRoleName(String[] ids);

    /**
     * 获取角色别名
     *
     * @param ids
     * @return
     */
    List<String> getRoleAlias(String[] ids);

    /**
     * 获取部门名
     *
     * @param ids
     * @return
     */
    List<String> getDeptName(String[] ids);
}




