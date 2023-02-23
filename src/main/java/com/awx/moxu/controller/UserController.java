package com.awx.moxu.controller;

import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.service.BladeUserService;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.awx.moxu.vo.UserVO;
import com.awx.moxu.wrapper.UserWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private BladeUserService userService;

    /**
     * 查询单条
     */
    @GetMapping("/detail")
    public R<UserVO> detail(BladeUser user) {
        BladeUser detail = userService.getOne(Condition.getQueryWrapper(user));
        return R.data(UserWrapper.build().entityVO(detail));
    }
    /**
     * 查询单条
     */
    @GetMapping("/info")
    public R<UserVO> info(HttpServletRequest request) {
        BladeUser detail = userService.getById(JwtUtils.getUser(request).getId());
        return R.data(UserWrapper.build().entityVO(detail));
    }

    /**
     * 用户列表
     */
    @GetMapping("/list")
    public R<IPage<UserVO>> list(@RequestParam Map<String, Object> user, Query query) {
        QueryWrapper<BladeUser> queryWrapper = Condition.getQueryWrapper(user, BladeUser.class);
        IPage<BladeUser> pages = userService.page(Condition.getPage(query),queryWrapper);
        return R.data(UserWrapper.build().pageVO(pages));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody BladeUser user) {
        return R.status(userService.submit(user));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody BladeUser user) {
        return R.status(userService.updateById(user));
    }

}
