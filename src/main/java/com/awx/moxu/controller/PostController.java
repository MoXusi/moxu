package com.awx.moxu.controller;

import com.awx.moxu.entity.Post;
import com.awx.moxu.service.PostService;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.utils.support.Condition;
import com.awx.moxu.utils.support.Query;
import com.awx.moxu.vo.PostVO;
import com.awx.moxu.wrapper.PostWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author anLiHang
 * @date 2023/2/23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    public R<PostVO> detail(Post post) {
        Post detail = postService.getOne(Condition.getQueryWrapper(post));
        return R.data(PostWrapper.build().entityVO(detail));
    }
    /**
     * 分页 岗位表
     */
    @GetMapping("/list")
    public R<IPage<PostVO>> list(Post post, Query query) {
        IPage<Post> pages = postService.page(Condition.getPage(query), Condition.getQueryWrapper(post));
        return R.data(PostWrapper.build().pageVO(pages));
    }

    /**
     * 自定义分页 岗位表
     */
    @GetMapping("/page")
    public R<IPage<PostVO>> page(PostVO post, Query query) {
        IPage<PostVO> pages = postService.selectPostPage(Condition.getPage(query), post);
        return R.data(pages);
    }

    /**
     * 新增 岗位表
     */
    @PostMapping("/save")
    public R save(@RequestBody Post post) {
        return R.status(postService.save(post));
    }

    /**
     * 修改 岗位表
     */
    @PostMapping("/update")
    public R update(@RequestBody Post post) {
        return R.status(postService.updateById(post));
    }
    /**
     * 新增或修改 岗位表
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Post post) {
        return R.status(postService.saveOrUpdate(post));
    }

    /**
     * 删除 岗位表
     */
    @PostMapping("/remove")
    public R remove(@RequestParam String ids) {
        return R.status(postService.removeByIds(Func.toStringList(ids)));
    }
    /**
     * 下拉数据源
     */
    @GetMapping("/select")
    public R<List<Post>> select() {
        List<Post> list = postService.list(Wrappers.<Post>query().lambda());
        return R.data(list);
    }

}
