package com.awx.moxu.mapper;

import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.vo.RoleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 沫须水
 * @Entity com.awx.moxu.entity.BladeRole
 */
@Mapper
public interface BladeRoleMapper extends BaseMapper<BladeRole> {

    /**
     * 自定义分页
     *
     * @param page
     * @param role
     * @return
     */
    List<RoleVO> selectRolePage(IPage page, RoleVO role);

    /**
     * 获取树形节点
     *
     * @return
     */
    List<RoleVO> tree();
}




