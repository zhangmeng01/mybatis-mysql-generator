package ${package}.${module};
<#assign upClassName=className?cap_first> 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.syswin.xwtoon.app.util.DictCode;
import com.syswin.common.utils.Result;
import com.syswin.common.utils.Result.Status;
import com.syswin.xwtoon.app.service.bean.${module}.${upClassName}Bean;
import com.syswin.xwtoon.app.service.${module}.${upClassName}Service;
 
@Controller
@RequestMapping("/${className}")
public class ${upClassName}Controller {
    @Autowired
	private ${upClassName}Service ${className}Service;

	/**
	 * @Description：查询列表 
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/${className}List")
	@ResponseBody
	public PageInfo<${upClassName}Bean> list(${upClassName}Bean ${className}Bean) {
		PageInfo<${upClassName}Bean> page=${className}Service.findListPage(${className}Bean,pageNum,pageSize);
		return page;
	}
	
	
	/** 
	 * @Description： 跳转到添加或修改页面 
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/toEdit${upClassName}")
	public ModelAndView toEdit${upClassName}(@RequestParam(required=false)Long id){
	      ${upClassName}Bean ${className}Bean=new ${upClassName}Bean();
	      if(id!=null){
	         ${className}Bean=${className}Service.getById(id);
	      } 
	      return new ModelAndView("/${className}_edit").addObject("${className}Bean", ${className}Bean);
	}
	
	/** 
	 * @Description： 保存或修改
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/edit${upClassName}")
	@ResponseBody
	public Result edit${upClassName}(${upClassName}Bean ${className}Bean){
	   if(${className}Bean.get${upClassName}Id()==null){
			  boolean result=${className}Service.save(${className}Bean);
			  if(!result){
				  return new Result(Status.ERROR,"保存失败"); 
			  } 
			 return new Result(Status.SUCCESS,"保存成功"); 
		  }else{
			  boolean result=${className}Service.updateById(${className}Bean);
			  if(!result){
				  return new Result(Status.ERROR,"修改失败"); 
			  } 
			 return new Result(Status.SUCCESS,"修改成功"); 
		  } 
	}
	
	
	/**
	 * @Description：删除 
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public Result delete${upClassName}(@PathVariable("id")Long id){
		boolean flag=${className}Service.deleteById(id);
		if(!flag){ 
			return new Result(Status.ERROR,"删除失败");
		}
		 return new Result(Status.SUCCESS,"删除成功");
	}
	
	
}