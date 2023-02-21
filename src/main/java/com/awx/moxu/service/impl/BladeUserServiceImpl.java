package com.awx.moxu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.awx.moxu.entity.UserInfo;
import com.awx.moxu.utils.Aes;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.service.BladeUserService;
import com.awx.moxu.mapper.BladeUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【blade_user(用户表)】的数据库操作Service实现
* @createDate 2023-02-02 21:48:14
*/
@Service
public class BladeUserServiceImpl extends ServiceImpl<BladeUserMapper, BladeUser>
    implements BladeUserService{

    @Override
    public int saveUser(BladeUser bladeUser) {
        bladeUser.setPassword(Aes.encryptHex(bladeUser.getPassword()));
        return baseMapper.insert(bladeUser);
    }

    @Override
    public UserInfo userInfo(String account,String password) {
        UserInfo userInfo = new UserInfo();
        LambdaQueryWrapper<BladeUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(BladeUser::getAccount,account);
        queryWrapper.eq(BladeUser::getPassword, Aes.encryptHex(password));
        BladeUser bladeUser = baseMapper.selectOne(queryWrapper);
        userInfo.setUser(bladeUser);
        if (!BeanUtil.isEmpty(bladeUser)) {
            List<String> roleAlias = baseMapper.getRoleAlias(bladeUser.getRoleId());
            userInfo.setRoles(roleAlias);
        }
        return userInfo;
    }

}




