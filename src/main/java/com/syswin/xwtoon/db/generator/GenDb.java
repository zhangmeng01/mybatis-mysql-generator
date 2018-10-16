package com.syswin.xwtoon.db.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 功能描述:自动代码生成器之数据库操作字段
 * 
 * @date 2016年7月22日 下午4:41:18
 * @version 2.2.0
 * @author llh
 */// TODO: 2016/8/16
public class GenDb {
	private static final GenDb db = new GenDb();
	// 数据库名
	private static final String DB_NAME = "org_crm";
	private static final String DB_URL = "jdbc:mysql://172.28.5.92:3306/" + DB_NAME;
	private static final String DB_USER_NAME = "syswin";
	private static final String DB_USER_PWD = "syswin";
	private Connection conn = null;
	private final Map<String, String> dateTypeMap = new HashMap<>();
	private final Map<String, String> dbTypeMap = new HashMap<>();

	private GenDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					DB_URL + "?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8", DB_USER_NAME,
					DB_USER_PWD);
			// 数据初始化集合对象
			dateTypeMap.put("bigint", "Long");
			dateTypeMap.put("varchar", "String");
			dateTypeMap.put("datetime", "Date");
			dateTypeMap.put("char", "String");
			dateTypeMap.put("tinyint", "Integer");
			dateTypeMap.put("int", "Integer");
			dateTypeMap.put("double", "Double");
			dateTypeMap.put("smallint", "Integer");
			dateTypeMap.put("bigint", "Long");
			dateTypeMap.put("text", "String");
			dateTypeMap.put("date", "Date");

			dbTypeMap.put("int", "INTEGER");
			dbTypeMap.put("text", "VARCHAR");
			dbTypeMap.put("longtext", "LONGVARCHAR");
			dbTypeMap.put("date", "TIMESTAMP");
			dbTypeMap.put("datetime", "TIMESTAMP");
			dbTypeMap.put("double", "NUMERIC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GenDb getInstance() {
		return db;
	}

	/**
	 * @Description 获取数据库的所有表 ，并且根据前缀过滤
	 * @author: llh
	 * @since: 2016年7月22日 下午12:03:12
	 */
	public List<String> getTableName(String filterPrefix) throws Exception {
		List<String> list = Lists.newArrayList();
		PreparedStatement ps = conn.prepareStatement(
				"select table_name from information_schema.tables" + " where table_schema='" + DB_NAME + "'");
		ResultSet rs = ps.executeQuery();
		if (StringUtils.isEmpty(filterPrefix)) {
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} else {
			while (rs.next()) {
				String table = rs.getString(1);
				if (!table.startsWith(filterPrefix)) {
					list.add(table);
				}
			}
		}

		return list;
	}

	/**
	 * 获取数据库的所有表
	 * 
	 * @Description�? TODO
	 * @author: llh
	 * @since: 2016年7月22日 下午12:03:12
	 */
	public List<String> getAllTableName() throws Exception {
		List<String> list = Lists.newArrayList();
		PreparedStatement ps = conn.prepareStatement(
				"select table_name from information_schema.tables" + " where table_schema='" + DB_NAME + "'");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

	/**
	 * 获取单表的字段名称，注释
	 * 
	 * @Description�? TODO
	 * @author: llh
	 * @since:2016年7月22日 下午1:30:16
	 */
	public List<GenColumn> getTableNameColumn(String tableName) throws Exception {
		List<GenColumn> list = Lists.newArrayList();
		PreparedStatement ps = conn.prepareStatement("select distinct column_name,column_comment,data_type from "
				+ "information_schema.columns where table_name='" + tableName + "' and table_schema='" + DB_NAME + "'");
		ResultSet rs = ps.executeQuery();
		GenColumn column = null;
		while (rs.next()) {
			column = new GenColumn();
			column.setColumnName(GenUtils.replaceUnderlineAndfirstToUpper(rs.getString(1), "_", ""));
			column.setDbName(rs.getString(1));
			column.setColumnComment(rs.getString(2));
			column.setDataType(getDataType(rs.getString(3)));
			column.setDbType(getDbType(rs.getString(3)));
			list.add(column);
		}
		return list;
	}

	private String getDataType(String dataType) {
		if (dateTypeMap.containsKey(dataType)) {
			return dateTypeMap.get(dataType);
		}
		return "String";
	}

	private String getDbType(String dbType) {
		if (dbTypeMap.containsKey(dbType)) {
			return dbTypeMap.get(dbType);
		}
		return dbType;
	}

}
