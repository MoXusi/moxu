package com.awx.moxu.mapper;

import com.awx.moxu.entity.Post;
import com.awx.moxu.vo.PostVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 沫须水
 * @Entity com.awx.moxu.entity.Post
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 自定义分页
     *
     * @param page
     * @param post
     * @return
     */
    List<PostVO> selectPostPage(IPage page, PostVO post);
    /**
     * 获取岗位名
     *
     * @param ids
     * @return
     */
    List<String> getPostNames(String[] ids);
}




