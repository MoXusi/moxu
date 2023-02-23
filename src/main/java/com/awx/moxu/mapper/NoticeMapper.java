package com.awx.moxu.mapper;

import com.awx.moxu.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author admin
* @description 针对表【notice(通知公告表)】的数据库操作Mapper
* @createDate 2023-02-23 19:58:32
* @Entity com.awx.moxu.entity.Notice
*/
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}




