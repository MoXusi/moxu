package com.awx.moxu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.awx.moxu.entity.Dict;
import com.awx.moxu.service.DictService;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.vo.DictVO;
import com.awx.moxu.wrapper.DictWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * 详情
     */
    @GetMapping("/detail")
    public R<DictVO> detail(Dict dict) {
        Dict detail = dictService.getOne(Condition.getQueryWrapper(dict));
        return R.data(DictWrapper.build().entityVO(detail));
    }
    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Dict dict) {
        return R.status(dictService.submit(dict));
    }


    /**
     * 列表
     */
    @GetMapping("/list")
    public R<List<INode>> list(@RequestParam Map<String, Object> dict) {
        LambdaQueryWrapper<Dict> dictLambdaQueryWrapper =
                Condition.getQueryWrapper(dict, Dict.class).lambda().orderByAsc(Dict::getSort);
        List<Dict> list = dictService.list(dictLambdaQueryWrapper);
        return R.data(DictWrapper.build().listNodeVO(list));
    }

    /**
     * 获取字典树形结构
     *
     * @return
     */
    @GetMapping("/tree")
    public R<List<DictVO>> tree() {
        List<DictVO> tree = dictService.tree();
        return R.data(tree);
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(dictService.removeByIds(Func.toStringList(ids)));
    }
}
