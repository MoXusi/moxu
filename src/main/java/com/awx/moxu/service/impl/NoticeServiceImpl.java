package com.awx.moxu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.awx.moxu.entity.Notice;
import com.awx.moxu.service.NoticeService;
import com.awx.moxu.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【notice(通知公告表)】的数据库操作Service实现
* @createDate 2023-02-23 19:58:32
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




