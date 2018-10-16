package com.syswin.xwtoon.db.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;

public interface IExcelFormater {
	/**
	 * 遍历excel某一行以某种格式返回数据
	 * 
	 * @param row
	 * @return
	 * @throws Exception
	 */
	public Object getFormattedValue(HSSFRow row) throws Exception;
}
