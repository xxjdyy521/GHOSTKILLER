package com.my.ghost.common.entity.admin;

import lombok.Data;

/**
 * Response信息实体类
 * T data需通过json来转为相应的对象，不能直接赋值
 * @author l
 *
 * @param <T>
 */
@Data
public class GhostResponseEntity<T> {
	private int code;
	private String message;
	private T data;
	
	public GhostResponseEntity() {};
	
	public GhostResponseEntity(int code,String message,T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public GhostResponseEntity(int code,String message) {
		this.code =code;
		this.message = message;
	}
	
	@SuppressWarnings("rawtypes")
	public static GhostResponseEntity success(String message,int code){
		GhostResponseEntity response = new GhostResponseEntity();
		response.setCode(code);
		response.setMessage(message);
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public static GhostResponseEntity failure(String message,int code){
		GhostResponseEntity response = new GhostResponseEntity();
		response.setCode(code);
		response.setMessage(message);
		return response;
	}
}
