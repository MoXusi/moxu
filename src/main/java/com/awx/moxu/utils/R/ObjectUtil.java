package com.awx.moxu.utils.R;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * @author anLiHang
 * @date 2023/2/15
 */
public class ObjectUtil extends ObjectUtils {
    public ObjectUtil() {
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }
}