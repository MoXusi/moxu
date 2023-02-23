package com.awx.moxu.service;

import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.utils.execl.UserExcel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author admin
* @description 针对表【blade_user(用户表)】的数据库操作Service
* @createDate 2023-02-02 21:48:14
*/
public interface BladeUserService extends IService<BladeUser> {
    /**
     * 新建用户
     *
     * @param bladeUser 用户实体
     * @return
     */
    int saveUser(BladeUser bladeUser);
    /**
     * 获取用户信息
     *
     * @param account    账号
     * @param password   密码
     * @return
     */
    UserInfo userInfo(String account, String password);
    /**
     * 获取角色名
     *
     * @param roleIds
     * @return
     */
    List<String> getRoleName(String roleIds);
    /**
     * 获取部门名
     *
     * @param deptIds
     * @return
     */
    List<String> getDeptName(String deptIds);
    /**
     * 新增或修改用户
     * @param user
     * @return
     */
    boolean submit(BladeUser user);
    /**
     * 获取导出用户数据
     *
     * @param queryWrapper
     * @return
     */
    List<UserExcel> exportUser(Wrapper<BladeUser> queryWrapper);
}
