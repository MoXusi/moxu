package com.awx.moxu.service;

import com.awx.moxu.entity.Post;
import com.awx.moxu.vo.PostVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author 沫须水
 */
public interface PostService extends IService<Post> {
    /**
     * 自定义分页
     *
     * @param page
     * @param post
     * @return
     */
    IPage<PostVO> selectPostPage(IPage<PostVO> page, PostVO post);
}
