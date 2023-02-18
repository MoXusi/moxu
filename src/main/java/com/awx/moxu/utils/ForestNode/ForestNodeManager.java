package com.awx.moxu.utils.ForestNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 沫须水
 */
public class ForestNodeManager<T extends INode> {
    private List<T> list;
    private List<String> parentIds = new ArrayList();

    public ForestNodeManager(List<T> items) {
        this.list = items;
    }

    public INode getTreeNodeAT(String id) {
        Iterator var2 = this.list.iterator();

        INode forestNode;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            forestNode = (INode)var2.next();
        } while(!forestNode.getId().equals(id));

        return forestNode;
    }

    public void addParentId(String parentId) {
        this.parentIds.add(parentId);
    }

    public List<T> getRoot() {
        List<T> roots = new ArrayList();
        Iterator var2 = this.list.iterator();

        while(true) {
            INode forestNode;
            do {
                if (!var2.hasNext()) {
                    return roots;
                }

                forestNode = (INode)var2.next();
            } while(!forestNode.getParentId().equals("0") && !this.parentIds.contains(forestNode.getId()));

            roots.add((T) forestNode);
        }
    }
}
