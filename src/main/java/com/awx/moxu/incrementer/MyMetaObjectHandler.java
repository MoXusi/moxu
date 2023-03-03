package com.awx.moxu.incrementer;

import com.awx.moxu.constant.BladeConstant;
import com.awx.moxu.utils.JwtUtils;
import com.awx.moxu.utils.SnowFlakeGenerateIdWorker;
import com.awx.moxu.utils.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    public SnowFlakeGenerateIdWorker snowFlakeGenerateIdWorker = new SnowFlakeGenerateIdWorker(0L,0L);

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = JwtUtils.getUser(request);
        this.strictInsertFill(metaObject, "createUser", () ->user.getUserId(), String.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "createTime", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
        if (metaObject.hasGetter(BladeConstant.DB_PRIMARY_KEY) && metaObject.hasSetter(BladeConstant.DB_PRIMARY_KEY)){
            // 获取旧值
            Object lastSave = metaObject.getValue(BladeConstant.DB_PRIMARY_KEY);
            // 判断旧值不存在
//            if (Objects.isNull(lastSave) || lastSave instanceof String){
//                // 旧值的合法性检测
//                if (!"01".equals(lastSave) && !"02".equals(lastSave))
//                    // 不合法的值默认为没有，则手动设置（存在且合法则自动跳过，不填充）
//                    setFieldValByName("lastSave", "01",metaObject);
//            }
            if (!Objects.isNull(lastSave)){
                setFieldValByName("id",snowFlakeGenerateIdWorker.generateNextId(),metaObject);
            }
        }
     }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = JwtUtils.getUser(request);
        this.strictUpdateFill(metaObject, "updateTime", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "updateUser", () -> user.getUserId(), String.class); // 起始版本 3.3.3(推荐)
    }
}
