package com.awx.moxu.vo;

import com.awx.moxu.entity.BladeRole;
import com.awx.moxu.utils.ForestNode.INode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 视图实体类
 *
 * @author Chill
 */
@Data
public class RoleVO extends BladeRole implements INode {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    /**
     * 父节点ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String parentId;

    /**
     * 子孙节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<INode> children;

    @Override
    public List<INode> getChildren() {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        return this.children;
    }

    /**
     * 上级角色
     */
    private String parentName;
}
