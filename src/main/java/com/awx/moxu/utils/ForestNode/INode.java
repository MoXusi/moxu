package com.awx.moxu.utils.ForestNode;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;
import java.util.List;

public interface INode extends Serializable {
    String getId();

    String getParentId();

    List<INode> getChildren();

    default Boolean getHasChildren() {
        return false;
    }
}
