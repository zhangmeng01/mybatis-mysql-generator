package com.syswin.xwtoon.db.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 自动代码生成工具操作类
 */
public class GenDbCreateCodeForyjtoondemodao {

	private static Logger logger = LoggerFactory.getLogger(GenDbCreateCodeForyjtoondemodao.class);
	// 项目的命名空间
	private static final String MODULE = "biz";
	private static final String PACKAGE = "net.dcjy.dcdy";
	private static final String PACKAGE_SERVICE = "net.dcjy.dcdy.admin.service";
	private static final String PACKAGE_CONTROLLER = "com.syswin.xwtoon.admin.controller";

	// 指定模版地址
	// private static final String
	// DIRCTORY_PATH="E://xwtoon//workspace//git//xwtoon//";
	private static final String DIRCTORY_PATH = "D://workspase//git//dcdy//";
	private static final String DIRCTORY_PATH_TEMPLATE = "D://workspase//git//xwtoon//";
	private static final String TEMPLETE_PATH = DIRCTORY_PATH_TEMPLATE
			+ "xwtoon-tools//src//main//resources//generator//dctemplate";

	// 生成的保存路径
	private static final String FTL_TEMPLATE_PATH = DIRCTORY_PATH
			+ "dcdy-admin//src//main//webapp//WEB-INF//ftl//zone//";
	private static final String CONTROLLER_PATH = DIRCTORY_PATH
			+ "dcdy-admin//src//main//java//net//dcjy//dcdy//admin//controller//";

	private static final String ENTITY_PATH = DIRCTORY_PATH + "dcdy-dao//src//main//java//net//dcjy//dcdy//entity//";
	private static final String DAO_PATH = DIRCTORY_PATH + "dcdy-dao//src//main//java//net//dcjy//dcdy//dao//";
	private static final String MAPPER_PATH = DIRCTORY_PATH + "dcdy-dao//src//main//resources//mappings//";

	private static final String SERVICEIMPL_PATH = DIRCTORY_PATH
			+ "dcdy-admin-service//src//main//java//net//dcjy//dcdy//user//service//impl//";
	private static final String SERVICE_PATH = DIRCTORY_PATH
			+ "dcdy-admin-interface//src//main//java//net//dcjy//dcdy//user//service//";
	private static final String ENTITY_INTERFACE_PATH = DIRCTORY_PATH
			+ "dcdy-admin-interface//src//main//java//net//dcjy//dcdy//user//service//bean//";

	// 已存在是否需要覆盖
	private static boolean isOverwrite = true;
	// 作者
	private static final String author = "SHENZL";
	// 是否要包含删除功能
	private static boolean IS_INCLUDE_DELETE = true;
	private Configuration cfg;
	private static List<String> talbes = Lists.newArrayList();
	private Map<String, Object> root = new HashMap<String, Object>();
	private String className;

	public GenDbCreateCodeForyjtoondemodao() {
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
			GenDbCreateCodeForyjtoondemodao hf = new GenDbCreateCodeForyjtoondemodao();
			// 过滤点不用生成的表
//            talbes=GenDb.getInstance().getTableName("qrtz");
//		    hf.GenProcess(hf);
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
			hf.GenSingleProcess("yjt_user_info"); // 指定单个数据库表生成代码;
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
		className = GenUtils.replaceUnderlineAndfirstToUpper(tableName.replace("yjt", ""), "_", "");
		className = GenUtils.firstCharacterToLower(className);
		root.put("className", className);
		root.put("columns", columns);
		root.put("tableName", tableName);
		root.put("keyColumn", columns.get(0));
		root.put("title", className + "管理");
		createDao();
//		 createServiceEntity();
//		 createServiceImpl();
//		 createController();
	}

	/**
	 * 功能描述:根据数据库表批量代码生产
	 * 
	 * @date 2016年7月22日 下午5:06:42
	 * @version 2.2.0
	 * @author llh
	 */
	public void GenProcess(GenDbCreateCodeForyjtoondemodao hf) throws Exception {
		for (String tableName : talbes) {
			GenSingleProcess(tableName);
		}
	}

	public void createDao() throws Exception {
		Template template = cfg.getTemplate("entity.ftl");
		Template templateMapper = cfg.getTemplate("mapper.ftl");
		Template templateDao = cfg.getTemplate("dao.ftl");
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, ENTITY_PATH,
				GenUtils.firstCharacterToUpper(className) + ".java", template, isOverwrite);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, MAPPER_PATH,
				GenUtils.firstCharacterToUpper(className) + ".xml", templateMapper, isOverwrite);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, DAO_PATH,
				"I" + GenUtils.firstCharacterToUpper(className) + "Dao.java", templateDao, isOverwrite);
	}

	public void createServiceEntity() throws Exception {
		Template templateInterface = cfg.getTemplate("entity-interface.ftl");
		root.put("package", PACKAGE_SERVICE);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, ENTITY_INTERFACE_PATH,
				GenUtils.firstCharacterToUpper(className) + "Bean.java", templateInterface, isOverwrite);
	}

	public void createServiceImpl() throws Exception {
		Template templateService = cfg.getTemplate("service.ftl");
		Template templateServiceImpl = cfg.getTemplate("serviceImpl.ftl");
		root.put("package", PACKAGE_SERVICE);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, SERVICE_PATH,
				"I" + GenUtils.firstCharacterToUpper(className) + "Service.java", templateService, isOverwrite);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, SERVICEIMPL_PATH,
				GenUtils.firstCharacterToUpper(className) + "ServiceImpl.java", templateServiceImpl, isOverwrite);
	}

	public void createController() throws Exception {
		Template templateController = cfg.getTemplate("controller.ftl");
		Template pageList = cfg.getTemplate("viewList.ftl");
		Template pageForm = cfg.getTemplate("viewForm.ftl");
		className = className.replace("biz", "");
		root.put("fileName", GenUtils.firstCharacterToLower(className));
		root.put("ftlpath", "template");
		root.put("package", PACKAGE);
		String FTL_TEMPLATE_PATH = DIRCTORY_PATH + "dcdy-admin//src//main//webapp//WEB-INF//ftl//template//";
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, CONTROLLER_PATH,
				GenUtils.firstCharacterToUpper(className) + "Controller.java", templateController, isOverwrite);
		root.put("className", GenUtils.firstCharacterToLower(className));
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, FTL_TEMPLATE_PATH,
				GenUtils.firstCharacterToLower(className) + "_list.ftl", pageList, isOverwrite);
		GenDbCreateCodeForyjtoondemodao.buildTemplate(root, FTL_TEMPLATE_PATH,
				GenUtils.firstCharacterToLower(className) + "_edit.ftl", pageForm, isOverwrite);
	}

	/**
	 * @Description 设置页面要的参数
	 * @author: llh
	 * @since: 2016年7月22日 下午4:33:12
	 */
	private void setPageParm(Map<String, Object> root) {
		root.put("contextPath", "<#assign base=request.contextPath />");
		root.put("common", "<#include \"common/common.ftl\" > ");
		root.put("isDel", IS_INCLUDE_DELETE);
		root.put("author", author);
		root.put("base", "base");
		root.put("date", new Date());
		root.put("package", PACKAGE);
		root.put("module", MODULE);
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

}
