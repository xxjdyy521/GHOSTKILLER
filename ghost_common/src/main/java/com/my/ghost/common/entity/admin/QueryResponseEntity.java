package com.my.ghost.common.entity.admin;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 查询封装，主要获取total
 * @author l
 *
 * @param <T>
 */
@Data
public class QueryResponseEntity<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<T> list;
	
	private long total;
}
