package com.awx.moxu.controller;

import cn.hutool.core.util.StrUtil;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.entity.Dept;
import com.awx.moxu.service.DeptService;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.awx.moxu.vo.DeptVO;
import com.awx.moxu.wrapper.DeptWrapper;
import com.awx.moxu.wrapper.MenuWrapper;
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
@RequestMapping("/dept")
public class DeptController {

    private DeptService deptService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    public R<DeptVO> detail(Dept dept) {
        Dept detail = deptService.getOne(Condition.getQueryWrapper(dept));
        return R.data(DeptWrapper.build().entityVO(detail));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public R<IPage<INode>> list(@RequestParam Map<String, Object> dept, Query query) {
        QueryWrapper<Dept> queryWrapper = Condition.getQueryWrapper(dept, Dept.class);
        IPage<Dept> pages = deptService.page(Condition.getPage(query),queryWrapper);
        return R.data(DeptWrapper.build().pageVOINode(pages));
    }
    /**
     * 获取部门树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    public R<List<DeptVO>> tree() {
        List<DeptVO> tree = deptService.tree();
        return R.data(tree);
    }
    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Dept dept) {
        if(StrUtil.hasBlank(dept.getParentId())){
            dept.setParentId("0");
        }
        return R.status(deptService.saveOrUpdate(dept));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(deptService.removeByIds(Func.toStringList(ids)));
    }
}
