package com.awx.moxu.service.impl;

import com.awx.moxu.vo.PostVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Post;
import com.awx.moxu.service.PostService;
import com.awx.moxu.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
implements PostService{

    @Override
    public IPage<PostVO> selectPostPage(IPage<PostVO> page, PostVO post) {
        return page.setRecords(baseMapper.selectPostPage(page, post));
    }
}




