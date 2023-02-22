package com.awx.moxu.wrapper;

import cn.hutool.extra.spring.SpringUtil;
import com.awx.moxu.constant.CommonConstant;
import com.awx.moxu.entity.Dict;
import com.awx.moxu.service.DictService;
import com.awx.moxu.utils.ForestNode.ForestNodeMerger;
import com.awx.moxu.utils.ForestNode.INode;
import com.awx.moxu.vo.DictVO;
import cn.hutool.core.bean.BeanUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class DictWrapper {

    private static DictService dictService;

    static {
        dictService = SpringUtil.getBean(DictService.class);
    }

    public static DictWrapper build() {
        return new DictWrapper();
    }

    public DictVO entityVO(Dict dict) {
        DictVO dictVO = BeanUtil.copyProperties(dict, DictVO.class);
        if (!dict.getParentId().equals(CommonConstant.TOP_PARENT_ID)) {
            dictVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            Dict parent = dictService.getById(dict.getParentId());
            dictVO.setParentName(parent.getDictValue());
        }
        return dictVO;
    }

    public List<INode> listNodeVO(List<Dict> list) {
        List<INode> collect = list.stream().map(dict -> BeanUtil.copyProperties(dict, DictVO.class)).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }

}
