package com.awx.moxu.controller;

import com.awx.moxu.entity.Param;
import com.awx.moxu.service.ParamService;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/param")
public class ParamController {

    private ParamService paramService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    public R<Param> detail(Param param) {
        Param detail = paramService.getOne(Condition.getQueryWrapper(param));
        return R.data(detail);
    }

    /**
     * 分页
     */
    @GetMapping("/list")
    public R<IPage<Param>> list(@RequestParam Map<String, Object> param, Query query) {
        IPage<Param> pages = paramService.page(Condition.getPage(query), Condition.getQueryWrapper(param, Param.class));
        return R.data(pages);
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Param param) {
        return R.status(paramService.saveOrUpdate(param));
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(paramService.removeByIds(Func.toStringList(ids)));
    }

}
