package com.syswin.xwtoon.db.generator;

import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动代码生成工具操作类
 */
public class GenDbCreateCodeForOrg {

	private static Logger logger = LoggerFactory.getLogger(GenDbCreateCodeForOrg.class);

	// 生成代码后存放目录地址
	private static final String DIRCTORY_PATH = "E://mysql_template//";
	// 模板所在目录跟路径
	private static final String DIRCTORY_PATH_TEMPLATE = "E://git//xwtoon-tools-mysql//";
	// 模板地址
	private static final String TEMPLETE_PATH = DIRCTORY_PATH_TEMPLATE
			+ "src//main//resources//generator//orgtemplate";

	// 生成代码后存放目录地址,按目录划分
	private static final String ENTITY_PATH = DIRCTORY_PATH + "bean//";
	private static final String DAO_PATH = DIRCTORY_PATH + "dao//";
	private static final String DAOIMPL_PATH = DIRCTORY_PATH + "daoimpl//";
	private static final String MAPPER_PATH = DIRCTORY_PATH + "mapping//";

	private static final String SERVICEIMPL_PATH = DIRCTORY_PATH + "serviceimpl//";
	private static final String SERVICE_PATH = DIRCTORY_PATH + "service//";

	// 已存在是否需要覆盖
	private static boolean isOverwrite = true;
	// 作者
	private static final String author = "SHENZL";
	// 是否要包含删除功能
	private static boolean IS_INCLUDE_DELETE = true;
	private Configuration cfg;
	private static List<String> talbes = Lists.newArrayList();
	private Map<String, Object> root = new HashMap<>();
	private String className;

	public GenDbCreateCodeForOrg() {
		// 初始化FreeMarker配置
		// 创建个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件位
		try {
			cfg.setDirectoryForTemplateLoading(new File(TEMPLETE_PATH));
			setPageParm(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			GenDbCreateCodeForOrg hf = new GenDbCreateCodeForOrg();
			// 过滤不需要生成的表
			// talbes = GenDb.getInstance().getTableName("qrtz");
			// 生成所有表对应的代码
			logger.info("生成dao开始......");
			talbes = GenDb.getInstance().getAllTableName();
			hf.GenProcess(hf);
			/*
			 * String[] temtalbes = new
			 * String[]{"dcdy_class_meeting","dcdy_class_meeting_post",
			 * "dcdy_class_meeting_post_attachment",
			 * "dcdy_class_meeting_post_praise","dcdy_class_meeting_post_reply",
			 * "dcdy_class_meeting_post_reply_praise",
			 * "dcdy_resource_attachment","dcdy_resource_categoty","dcdy_resource_manage",
			 * "dcdy_user_resource_rel"}; for(String table : temtalbes){
			 * hf.GenSingleProcess(table); }
			 */
			//hf.GenSingleProcess("organization_member_info"); // 指定单个数据库表生成代码;
			logger.info("生成dao结束......");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述: 制定数据库表单个代码生产
	 *
	 * @date 2016年7月22日 下午5:06:42
	 * @version 2.2.0
	 * @author llh
	 */
	public void GenSingleProcess(String tableName) throws Exception {
		List<GenColumn> columns = GenDb.getInstance().getTableNameColumn(tableName);
		className = GenUtils.replaceUnderlineAndfirstToUpper(tableName.replace("xjd", ""), "_", "");
		className = GenUtils.firstCharacterToLower(className);
		root.put("className", className);
		root.put("columns", columns);
		root.put("tableName", tableName);
		root.put("keyColumn", columns.get(0));
		root.put("title", className + "管理");
		createDao();
		createServiceImpl();
	}

	/**
	 * 功能描述:根据数据库表批量代码生产
	 *
	 * @date 2016年7月22日 下午5:06:42
	 * @version 2.2.0
	 * @author llh
	 */
	public void GenProcess(GenDbCreateCodeForOrg hf) throws Exception {
		for (String tableName : talbes) {
			GenSingleProcess(tableName);
		}
	}

	public void createDao() throws Exception {
		root.put("package", "com.syswin.systoon.crm");
		Template template = cfg.getTemplate("entity.ftl");
		Template templateMapper = cfg.getTemplate("mapper.ftl");
		Template templateDao = cfg.getTemplate("dao.ftl");
		Template templateDaoImpl = cfg.getTemplate("daoImpl.ftl");
		GenDbCreateCodeForOrg.buildTemplate(root, ENTITY_PATH,
				GenUtils.firstCharacterToUpper(className) + ".java", template, isOverwrite);
		GenDbCreateCodeForOrg.buildTemplate(root, MAPPER_PATH,
				GenUtils.firstCharacterToUpper(className) + ".xml", templateMapper, isOverwrite);
		GenDbCreateCodeForOrg.buildTemplate(root, DAO_PATH,
				"I" + GenUtils.firstCharacterToUpper(className) + "Dao.java", templateDao, isOverwrite);
		GenDbCreateCodeForOrg.buildTemplate(root, DAOIMPL_PATH,
				GenUtils.firstCharacterToUpper(className) + "DaoImpl.java", templateDaoImpl, isOverwrite);
	}

	public void createServiceImpl() throws Exception {
		root.put("package", "com.syswin.systoon.crm");
		Template templateService = cfg.getTemplate("service.ftl");
		Template templateServiceImpl = cfg.getTemplate("serviceImpl.ftl");
		GenDbCreateCodeForOrg.buildTemplate(root, SERVICE_PATH,
				"I" + GenUtils.firstCharacterToUpper(className) + "Service.java", templateService, isOverwrite);
		GenDbCreateCodeForOrg.buildTemplate(root, SERVICEIMPL_PATH,
				GenUtils.firstCharacterToUpper(className) + "ServiceImpl.java", templateServiceImpl, isOverwrite);
	}

	/**
	 * 功能描述:根据目录生产文件
	 *
	 * @date 2016年7月22日 下午5:08:41
	 * @version 2.2.0
	 * @author llh
	 */
	public static void buildTemplate(Map<String, Object> root, String savePath, String fileName, Template template,
			boolean isOverwrite) {
		File newsDir = new File(savePath);
		if (!newsDir.exists()) {
			newsDir.mkdirs();
		}
		String realFileName = savePath + fileName;
		boolean isCreate = true;
		try {
			File realFile = new File(realFileName);
			if (realFile.exists()) {
				if (!isOverwrite) {
					isCreate = false;
				}
			}
			if (isCreate) {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				template.process(root, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("生成" + fileName + "成功");
	}

	/**
	 * @Description 设置页面要的参数
	 * @author: llh
	 * @since: 2016年7月22日 下午4:33:12
	 */
	private void setPageParm(Map<String, Object> root) {
		// root.put("contextPath","<#assign base=request.contextPath />");
		// root.put("common", "<#include \"common/common.ftl\" > ");
		root.put("isDel", IS_INCLUDE_DELETE);
		root.put("author", author);
		root.put("base", "base");
		root.put("date", new Date());
		// root.put("package",PACKAGE);
		// root.put("module",MODULE);
	}

}
