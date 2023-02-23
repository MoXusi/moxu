package com.awx.moxu.incrementer;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
//        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        String userId = BaseContext.getContext();
        this.strictInsertFill(metaObject, "createUser", () ->userId, String.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "createTime", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
        if (metaObject.hasGetter("id") && metaObject.hasSetter("id")){
            // 获取旧值
            Object lastSave = metaObject.getValue("id");
            // 判断旧值不存在
//            if (Objects.isNull(lastSave) || lastSave instanceof String){
//                // 旧值的合法性检测
//                if (!"01".equals(lastSave) && !"02".equals(lastSave))
//                    // 不合法的值默认为没有，则手动设置（存在且合法则自动跳过，不填充）
//                    setFieldValByName("lastSave", "01",metaObject);
//            }
            if (!Objects.isNull(lastSave)){
                setFieldValByName("id", IdUtil.simpleUUID(),metaObject);
            }
        }
        // 或者
        //this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        // 或者
        String userId = BaseContext.getContext();
        this.strictUpdateFill(metaObject, "updateTime", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "updateUser", () -> userId, String.class); // 起始版本 3.3.3(推荐)
        // 或者
        //this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }
}
