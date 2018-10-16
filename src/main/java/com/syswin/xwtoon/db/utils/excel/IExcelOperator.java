package com.syswin.xwtoon.db.utils.excel;

/**
 * Created by 145846 on 2016/9/20.
 */
public interface IExcelOperator {

	/**
	 * 在遍历过程中处理数据
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Object optValue(Object param) throws Exception;
}
