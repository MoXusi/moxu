package com.awx.moxu.controller;

import cn.hutool.http.HttpRequest;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.service.impl.MenuServiceImpl;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private MenuServiceImpl menuService;

    /**
     * 前端菜单数据
     */
    @GetMapping("/routes")
    public R<List<MenuVO>> routes(HttpServletRequest request) {
        BladeUser user = JwtUtils.getUser(request);
        List<MenuVO> list = menuService.routes((user == null || user.getId() == "") ? null : user.getRoleId());
        return R.data(list);
    }


}
