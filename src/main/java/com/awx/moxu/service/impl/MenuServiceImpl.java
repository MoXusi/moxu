package com.awx.moxu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.service.MenuService;
import com.awx.moxu.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【menu(菜单表)】的数据库操作Service实现
* @createDate 2023-02-14 21:55:25
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




