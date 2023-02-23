package com.awx.moxu.vo;


import com.awx.moxu.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 通知公告视图类
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoticeVO extends Notice {

	//通知类型名
	private String categoryName;

}
