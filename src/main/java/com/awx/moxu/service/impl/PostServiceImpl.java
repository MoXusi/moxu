package com.awx.moxu.service.impl;

import com.awx.moxu.utils.Func;
import com.awx.moxu.vo.PostVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Post;
import com.awx.moxu.service.PostService;
import com.awx.moxu.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public String getPostIds(String postNames) {
        List<Post> postList = baseMapper.selectList(Wrappers.<Post>query().lambda().in(Post::getPostName, Func.toStrArray(postNames)));
        if (postList != null && postList.size() > 0) {
            return postList.stream().map(post -> Func.toStr(post.getId())).distinct().collect(Collectors.joining(","));
        }
        return null;
    }

    @Override
    public List<String> getPostNames(String postIds) {
        return baseMapper.getPostNames(Func.toStrArray(postIds));
    }
}




