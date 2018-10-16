package com.syswin.xwtoon.db.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by 145846 on 2016/9/20.
 */
public class Transfer2Sql implements IExcelFormater {
	Logger logger = LoggerFactory.getLogger(Transfer2Sql.class);
	private static String sqlTemplate = "INSERT INTO `%tableName%`  ( `%columnNames%`) VALUES ( '%values%');";
	private Map<Integer, String> mapping;
	private Set<Integer> keySet;
	private String templateInThisCase;

	// 不要默认的构造函数，否则缺少必要的参数
	private Transfer2Sql() {
	}

	/**
	 * @param mapping   excel行号对应数据库哪个字段
	 * @param tableName 数据库表名
	 */
	public Transfer2Sql(Map<Integer, String> mapping, String tableName) {
		this.mapping = mapping;
		StringBuffer columnNames = new StringBuffer();
		keySet = mapping.keySet();
		for (Integer key : keySet) {
			columnNames.append("'" + mapping.get(key) + "',");
		}
		// 去掉columnNames的最后一个逗号
		setTemplateInThisCase(sqlTemplate.replace("%tableName%", tableName).replace("%columnNames%",
				columnNames.substring(0, columnNames.length() - 2)));
	}

	public Object getFormattedValue(HSSFRow row) throws Exception {
		StringBuffer values = new StringBuffer();
		for (Integer key : keySet) {
			values.append("'" + row.getCell(key) + "',");
		}
		// 去掉最后一个逗号
		return getTemplateInThisCase().replace("%values%", values.substring(0, values.length() - 2));
	}

	public String getTemplateInThisCase() {
		return templateInThisCase;
	}

	public void setTemplateInThisCase(String templateInThisCase) {
		this.templateInThisCase = templateInThisCase;
	}
}
