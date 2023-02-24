package com.awx.moxu.controller;

import com.awx.moxu.entity.Region;
import com.awx.moxu.service.RegionService;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.awx.moxu.vo.RegionVO;
import com.awx.moxu.wrapper.RegionWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author anLiHang
 * @date 2023/2/24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    public R<RegionVO> detail(Region region) {
        Region detail = regionService.getOne(Condition.getQueryWrapper(region));
        return R.data(RegionWrapper.build().entityVO(detail));
    }

    /**
     * 分页 行政区划表
     */
    @GetMapping("/list")
    public R<IPage<Region>> list(Region region, Query query) {
        IPage<Region> pages = regionService.page(Condition.getPage(query), Condition.getQueryWrapper(region));
        return R.data(pages);
    }

    /**
     * 懒加载列表
     */
    @GetMapping("/lazy-list")
    public R<List<INode>> lazyList(String parentCode, @RequestParam Map<String, Object> menu) {
        List<INode> list = regionService.lazyList(parentCode, menu);
        return R.data(RegionWrapper.build().listNodeLazyVO(list));
    }

    /**
     * 懒加载列表
     */
    @GetMapping("/lazy-tree")
    public R<List<INode>> lazyTree(String parentCode,@RequestParam Map<String, Object> menu) {
        List<INode> list = regionService.lazyTree(parentCode, menu);
        return R.data(RegionWrapper.build().listNodeLazyVO(list));
    }

    /**
     * 新增 行政区划表
     */
    @PostMapping("/save")
    public R save(@RequestBody Region region) {
        return R.status(regionService.save(region));
    }

    /**
     * 修改 行政区划表
     */
    @PostMapping("/update")
    public R update(@RequestBody Region region) {
        return R.status(regionService.updateById(region));
    }

    /**
     * 新增或修改 行政区划表
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Region region) {
        return R.status(regionService.submit(region));
    }


    /**
     * 删除 行政区划表
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String id) {
        return R.status(regionService.removeRegion(id));
    }

    /**
     * 行政区划下拉数据源
     */
    @GetMapping("/select")
    public R<List<Region>> select(@RequestParam(required = false, defaultValue = "00") String code) {
        List<Region> list = regionService.list(Wrappers.<Region>query().lambda().eq(Region::getParentCode, code));
        return R.data(list);
    }
}
