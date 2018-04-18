package com.xfs.cp.dto;

/**
 * 需要转换为model的dto接口
 * @author han
 *
 * @param <T>
 */
public interface ToModelDTO<T> {

	/**
	 * dto转换为model
	 * @return
	 */
	public T dtoToModel();
}
