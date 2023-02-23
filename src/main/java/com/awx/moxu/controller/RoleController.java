package com.awx.moxu.controller;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.service.BladeRoleService;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.awx.moxu.vo.GrantVO;
import com.awx.moxu.vo.RoleVO;
import com.awx.moxu.wrapper.RoleWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author anLiHang
 * @date 2023/2/23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private BladeRoleService roleService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    public R<RoleVO> detail(BladeRole role) {
        BladeRole detail = roleService.getOne(Condition.getQueryWrapper(role));
        return R.data(RoleWrapper.build().entityVO(detail));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R<IPage<RoleVO>> list(@RequestParam Map<String, Object> role, Query query) {
        QueryWrapper<BladeRole> queryWrapper = Condition.getQueryWrapper(role, BladeRole.class);
        IPage<BladeRole> page = roleService.page(Condition.getPage(query), queryWrapper);
        return R.data(RoleWrapper.build().pageVO(page));
    }

    /**
     * 获取角色树形结构
     */
    @GetMapping("/tree")
    public R<List<RoleVO>> tree() {
        List<RoleVO> tree = roleService.tree();
        return R.data(tree);
    }
    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody BladeRole role) {
        if(StrUtil.hasBlank(role.getParentId())){
            role.setParentId("0");
        }
        return R.status(roleService.saveOrUpdate(role));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(roleService.removeByIds(Func.toStringList(ids)));
    }

    /**
     * 设置菜单权限
     */
    @PostMapping("/grant")
    public R grant(@RequestBody GrantVO grantVO) {
        boolean temp = roleService.grant(grantVO.getRoleIds(), grantVO.getMenuIds());
        return R.status(temp);
    }
}
