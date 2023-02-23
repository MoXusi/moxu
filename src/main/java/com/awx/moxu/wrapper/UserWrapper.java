package com.awx.moxu.wrapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.awx.moxu.entity.BladeUser;
import com.awx.moxu.service.BladeUserService;
import com.awx.moxu.service.DictService;
import com.awx.moxu.utils.ForestNode.BaseEntityWrapper;
import com.awx.moxu.utils.Func;
import com.awx.moxu.utils.R.R;
import com.awx.moxu.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class UserWrapper  extends BaseEntityWrapper<BladeUser, UserVO> {

    private static BladeUserService userService;

    private static DictService dictClient;

    static {
        userService = SpringUtil.getBean(BladeUserService.class);
        dictClient = SpringUtil.getBean(DictService.class);
    }

    public static UserWrapper build() {
        return new UserWrapper();
    }

    @Override
    public UserVO entityVO(BladeUser user) {
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        List<String> roleName = userService.getRoleName(user.getRoleId());
        List<String> deptName = userService.getDeptName(user.getDeptId());
        userVO.setRoleName(Func.join(roleName));
        userVO.setDeptName(Func.join(deptName));
        String sex = dictClient.getValue("sex", Func.toInt(user.getSex()));
        if (!Func.hasEmpty(sex)) {
            userVO.setSexName(sex);
        }
        return userVO;
    }

}
