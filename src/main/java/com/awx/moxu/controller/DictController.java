package com.awx.moxu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.awx.moxu.entity.Dict;
import com.awx.moxu.service.DictService;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.R.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
public class DictController {

    private DictService dictService;
    /**
     * 列表
     */
    @GetMapping("/list")
    public R<List<INode>> list(@RequestParam Map<String, Object> map) {
        Dict dict = BeanUtil.toBeanIgnoreCase(map, Dict.class, false);
        LambdaQueryWrapper<Dict> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.orderByAsc(Dict::getSort);
        List<Dict> list = dictService.list();
//        return R.data(DictWrapper.build().listNodeVO(list));
        System.out.println(dict);
        return R.data(null);
    }
}
