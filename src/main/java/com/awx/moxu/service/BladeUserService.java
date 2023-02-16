package com.awx.moxu.service;

import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

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

}
