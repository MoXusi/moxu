package com.awx.moxu.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.Menu;
import com.awx.moxu.service.impl.MenuServiceImpl;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
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
     * 详情
     */
    @GetMapping("/detail")
    public R<MenuVO> detail(Menu menu) {
        Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
        return R.data(MenuWrapper.build().entityVO(detail));
    }
    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Menu menu) {
        if(StrUtil.hasBlank(menu.getParentId())){
            menu.setParentId("0");
        }
        return R.status(menuService.saveOrUpdate(menu));
    }
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
     * 前端按钮数据
     */
    @GetMapping("/buttons")
    public R<List<MenuVO>> buttons(HttpServletRequest request) {
        BladeUser user = JwtUtils.getUser(request);
        List<MenuVO> list = menuService.buttons(user.getRoleId());
        return R.data(list);
    }
    /**
     * 列表
     */
    @GetMapping("/list")
    public R<List<MenuVO>> list(@RequestParam Map<String, Object> menu) {
        List<Menu> list = menuService.list(Condition.getQueryWrapper(menu, Menu.class).lambda().orderByAsc(Menu::getSort));
        return R.data(MenuWrapper.build().listNodeVO(list));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(menuService.removeByIds(Func.toStringList(ids)));
    }

    /**
     * 获取菜单树形结构
     */
    @GetMapping("/tree")
    public R<List<MenuVO>> tree() {
        List<MenuVO> tree = menuService.tree();
        return R.data(tree);
    }

}
