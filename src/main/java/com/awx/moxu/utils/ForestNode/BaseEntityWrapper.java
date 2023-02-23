package com.awx.moxu.utils.ForestNode;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 沫须水
 */
public abstract class BaseEntityWrapper<E, V> {
    public BaseEntityWrapper() {
    }

    public abstract V entityVO(E entity);

    public List<V> listVO(List<E> list) {
        return list.stream().map(this::entityVO).collect(Collectors.toList());
    }

    public IPage<V> pageVO(IPage<E> pages) {
        List<V> records = this.listVO(pages.getRecords());
        IPage<V> pageVo = new Page(pages.getCurrent(), pages.getSize(), pages.getTotal());
        pageVo.setRecords(records);
        return pageVo;
    }
}
