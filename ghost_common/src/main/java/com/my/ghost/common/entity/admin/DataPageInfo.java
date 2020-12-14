package com.my.ghost.common.entity.admin;

import lombok.Data;

/**
 * 分页信息
 * @author l
 *
 * @param <T>
 */
@Data
public class DataPageInfo<T> {
	private int currentPage;
	private int pageSize;
	private T data;
}
