package com.awx.moxu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.service.impl.MenuServiceImpl;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.vo.MenuVO;
import com.awx.moxu.wrapper.MenuWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    /**
     * 列表
     */
    @GetMapping("/list")
    public R<List<MenuVO>> list(@RequestParam Map<String, Object> menu) {
        BeanUtil.mapToBean(menu, Menu.class, false);
        List<Menu> list = menuService.list();
        return R.data(MenuWrapper.build().listNodeVO(list));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(menuService.removeByIds(Func.toStringList(ids)));
    }

}
