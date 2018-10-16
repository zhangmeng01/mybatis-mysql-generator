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
public class GenDbCreateCodeForApp {

	private static Logger logger = LoggerFactory.getLogger(GenDbCreateCodeForApp.class);
	// 项目的命名空间
	private static final String MODULE = "biz";
	private static final String PACKAGE = "com.syswin.xwtoon";
	private static final String PACKAGE_SERVICE = "com.syswin.xwtoon.app.service";
	private static final String PACKAGE_CONTROLLER = "com.syswin.xwtoon.app.controller";

	// 指定模版地址
	// private static final String
	// DIRCTORY_PATH="E://xwtoon//workspace//git//xwtoon//";
	private static final String DIRCTORY_PATH = "E://git_repo_micro//xwtoon//";
	private static final String TEMPLETE_PATH = DIRCTORY_PATH
			+ "xwtoon-tools//src//main//resources//generator//templates";

	// 生成的保存路径
	private static final String FTL_TEMPLATE_PATH = DIRCTORY_PATH + "xwtoon-app//src//main//webapp//WEB-INF//ftl//";
	private static final String CONTROLLER_PATH = DIRCTORY_PATH
			+ "xwtoon-app//src//main//java//com//syswin//xwtoon//app//controller//biz//";

	private static final String ENTITY_PATH = DIRCTORY_PATH
			+ "xwtoon-dao//src//main//java//com//syswin//xwtoon//entity//biz//";
	private static final String DAO_PATH = DIRCTORY_PATH
			+ "xwtoon-dao//src//main//java//com//syswin//xwtoon//dao//biz//";
	private static final String MAPPER_PATH = DIRCTORY_PATH + "xwtoon-dao//src//main//resources//mappings//biz//";

	private static final String SERVICEIMPL_PATH = DIRCTORY_PATH
			+ "xwtoon-app-service//src//main//java//com//syswin//xwtoon//app//service//impl//biz//";
	private static final String SERVICE_PATH = DIRCTORY_PATH
			+ "xwtoon-app-interface//src//main//java//com//syswin//xwtoon//app//service//biz//";
	private static final String ENTITY_INTERFACE_PATH = DIRCTORY_PATH
			+ "xwtoon-app-interface//src//main//java//com//syswin//xwtoon//app//service//bean//biz//";

	// 已存在是否需要覆盖
	private static boolean isOverwrite = true;
	// 作者
	private static final String author = "He Xueke";
	// 是否要包含删除功能
	private static boolean IS_INCLUDE_DELETE = true;
	private Configuration cfg;
	private static List<String> talbes = Lists.newArrayList();

	public GenDbCreateCodeForApp() {
		// 初始化FreeMarker配置
		// 创建个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件位
		try {
			cfg.setDirectoryForTemplateLoading(new File(TEMPLETE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			GenDbCreateCodeForApp hf = new GenDbCreateCodeForApp();

			// 过滤点不用生成的表
			talbes = GenDb.getInstance().getTableName("qrtz");
			// hf.GenProcess(hf);
			hf.GenSingleProcess(hf, "xwt_biz_shop_follow"); // 指定单个数据库表生成代码;
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
	public void GenSingleProcess(GenDbCreateCodeForApp hf, String tableName) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		setPageParm(root);
		Template template = cfg.getTemplate("entity.ftl");
		Template templateMapper = cfg.getTemplate("mapper.ftl");
		Template templateDao = cfg.getTemplate("dao.ftl");
		Template templateService = cfg.getTemplate("service.ftl");
		Template templateServiceImpl = cfg.getTemplate("serviceImpl.ftl");
		Template templateController = cfg.getTemplate("controller.ftl");
//    	Template pageList = cfg.getTemplate("viewList.ftl");  
//    	Template pageForm = cfg.getTemplate("viewForm.ftl");  
		Template templateInterface = cfg.getTemplate("entity-interface.ftl");
		List<GenColumn> columns = GenDb.getInstance().getTableNameColumn(tableName);
		String className = GenUtils.replaceUnderlineAndfirstToUpper(tableName.replace("xwt", ""), "_", "");
		className = GenUtils.firstCharacterToLower(className);
		root.put("className", className);
		root.put("columns", columns);
		root.put("tableName", tableName);
		root.put("keyColumn", columns.get(0));
		root.put("title", className + "管理");
		root.put("package", PACKAGE);
		root.put("module", MODULE);
		hf.buildTemplate(root, ENTITY_PATH, GenUtils.firstCharacterToUpper(className) + ".java", template, isOverwrite);
		hf.buildTemplate(root, MAPPER_PATH, GenUtils.firstCharacterToUpper(className) + ".xml", templateMapper,
				isOverwrite);
		hf.buildTemplate(root, DAO_PATH, GenUtils.firstCharacterToUpper(className) + "Dao.java", templateDao,
				isOverwrite);
//     	 	 root.put("package",PACKAGE_SERVICE);
//     	 	 hf.buildTemplate(root,ENTITY_INTERFACE_PATH,GenUtils.firstCharacterToUpper(className)+"Bean.java",templateInterface,isOverwrite);
//      	 hf.buildTemplate(root,SERVICE_PATH,GenUtils.firstCharacterToUpper(className)+"Service.java",templateService,isOverwrite);
//   	 	 hf.buildTemplate(root,SERVICEIMPL_PATH,GenUtils.firstCharacterToUpper(className)+"ServiceImpl.java",templateServiceImpl,isOverwrite);
//           root.put("package",PACKAGE_CONTROLLER);
//    		 hf.buildTemplate(root,CONTROLLER_PATH,GenUtils.firstCharacterToUpper(className)+"Controller.java",templateController,isOverwrite);
//           hf.buildTemplate(root,FTL_TEMPLATE_PATH,GenUtils.firstCharacterToUpper(className)+"_list.ftl",pageList,isOverwrite);
//    		 hf.buildTemplate(root,FTL_TEMPLATE_PATH,GenUtils.firstCharacterToUpper(className)+"_edit.ftl",pageForm,isOverwrite);
	}

	/**
	 * 功能描述:根据数据库表批量代码生产
	 * 
	 * @date 2016年7月22日 下午5:06:42
	 * @version 2.2.0
	 * @author llh
	 */
	public void GenProcess(GenDbCreateCodeForApp hf) throws Exception {
		Map<String, Object> root = new HashMap<String, Object>();
		setPageParm(root);
		Template template = cfg.getTemplate("entity.ftl");
		Template templateMapper = cfg.getTemplate("mapper.ftl");
		Template templateDao = cfg.getTemplate("dao.ftl");
		Template templateService = cfg.getTemplate("service.ftl");
		Template templateServiceImpl = cfg.getTemplate("serviceImpl.ftl");
		Template templateController = cfg.getTemplate("controller.ftl");
//    	Template pageList = cfg.getTemplate("viewList.ftl");  
//    	Template pageForm = cfg.getTemplate("viewForm.ftl");

		Template templateInterface = cfg.getTemplate("entity-interface.ftl");
		for (String tableName : talbes) {
			List<GenColumn> columns = GenDb.getInstance().getTableNameColumn(tableName);
			String className = GenUtils.replaceUnderlineAndfirstToUpper(tableName.replace("jxt", ""), "_", "");
			className = GenUtils.firstCharacterToLower(className);
			root.put("className", className);
			root.put("columns", columns);
			root.put("tableName", tableName);
			root.put("keyColumn", columns.get(0));
			root.put("title", className + "管理");
			root.put("package", PACKAGE);
			root.put("module", MODULE);
			hf.buildTemplate(root, ENTITY_PATH, GenUtils.firstCharacterToUpper(className) + ".java", template,
					isOverwrite);
			hf.buildTemplate(root, MAPPER_PATH, GenUtils.firstCharacterToUpper(className) + ".xml", templateMapper,
					isOverwrite);
			hf.buildTemplate(root, DAO_PATH, GenUtils.firstCharacterToUpper(className) + "Dao.java", templateDao,
					isOverwrite);
			root.put("package", PACKAGE_SERVICE);
			hf.buildTemplate(root, ENTITY_INTERFACE_PATH, GenUtils.firstCharacterToUpper(className) + "Bean.java",
					templateInterface, isOverwrite);
			hf.buildTemplate(root, SERVICE_PATH, GenUtils.firstCharacterToUpper(className) + "Service.java",
					templateService, isOverwrite);
			hf.buildTemplate(root, SERVICEIMPL_PATH, GenUtils.firstCharacterToUpper(className) + "ServiceImpl.java",
					templateServiceImpl, isOverwrite);
			hf.buildTemplate(root, CONTROLLER_PATH, GenUtils.firstCharacterToUpper(className) + "Controller.java",
					templateController, isOverwrite);
//    		 hf.buildTemplate(root,FTL_TEMPLATE_PATH,GenUtils.firstCharacterToUpper(className)+"_list.ftl",pageList,isOverwrite);
//    		 hf.buildTemplate(root,FTL_TEMPLATE_PATH,GenUtils.firstCharacterToUpper(className)+"_edit.ftl",pageForm,isOverwrite);
		}

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
		root.put("date", new Date());
		root.put("package", PACKAGE);
	}

	/**
	 * 功能描述:根据目录生产文件
	 * 
	 * @date 2016年7月22日 下午5:08:41
	 * @version 2.2.0
	 * @author llh
	 */
	public void buildTemplate(Map<String, Object> root, String savePath, String fileName, Template template,
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
