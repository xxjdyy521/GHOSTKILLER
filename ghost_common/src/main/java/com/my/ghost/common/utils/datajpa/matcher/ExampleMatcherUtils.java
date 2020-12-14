package com.my.ghost.common.utils.datajpa.matcher;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.PageRequest;

/**
 * 
 * SpringDataJpa 模糊查询工具类 ---获取  example及pageable
 *
 */
public class ExampleMatcherUtils {
	
	/**
	  * 获取 Jpa Repository所需的example
	 * @param matchersMap -- 所需模糊查询字段
	 * @param entity -- 实体
	 * @return
	 */
	public static <T> Example<T> getExample(Map<String, GenericPropertyMatcher> matchersMap,T entity){
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("abc", GenericPropertyMatchers.contains());
		Iterator matchersIt = matchersMap.keySet().iterator();
		while(matchersIt.hasNext()) {
			String columnName = matchersIt.next().toString();
			matcher = matcher.withMatcher(columnName, matchersMap.get(columnName));
		}
		Example<T> example = Example.of(entity, matcher);
		return example;
	}
	
	
	/**
	 * 获取 Jpa Repository所需的Pageable
	 * @param pageSize
	 * @param currentPage
	 * @param direction -- 排序
	 * @param sortColumn -- 值定排序行
	 * @return
	 */
	public static <T>Pageable getPageable(int pageSize,int currentPage,Direction direction,List<String> sortColumns){
		Sort sort = new Sort(direction, sortColumns);
		if (currentPage <= 0) {
			currentPage = 1;
        }
        if (pageSize <= 0) {
        	pageSize = 10;
        }
        currentPage--;
        Pageable pageable = PageRequest.of(currentPage, pageSize,sort);
		return pageable;
	}
}
