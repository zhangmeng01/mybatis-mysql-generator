package ${package}.admin.controller;
<#assign upClassName=className?cap_first>
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import net.dcjy.dcdy.admin.util.Result;
import net.dcjy.dcdy.admin.util.Result.Status;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import net.dcjy.dcdy.admin.service.bean.${upClassName}Bean;
import net.dcjy.dcdy.admin.service.I${upClassName}Service;

 
@Controller
@RequestMapping("/${fileName}")
public class ${fileName?cap_first}Controller extends BaseController{
    @Autowired
	private I${upClassName}Service ${className}Service;

	/**
	* @Description:进入页面
	* @author:${author}
	*/
	@RequestMapping(value="/to${fileName?cap_first}List")
	public ModelAndView to${fileName?cap_first}List(){
		return new ModelAndView("/${ftlpath}/${fileName}_list");
    }

	/**
	 * @Description：查询列表 
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/${fileName}List")
	@ResponseBody
	public PageInfo<${upClassName}Bean> list(${upClassName}Bean ${fileName}Bean) {
		PageInfo<${upClassName}Bean> page=${className}Service.findListPage(${fileName}Bean,pageNum,pageSize);
		return page;
	}
	
	
	/** 
	 * @Description： 跳转到添加或修改页面 
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/toEdit${fileName?cap_first}")
	public ModelAndView toEdit${fileName?cap_first}(@RequestParam(required=false)Long id){
	      ${upClassName}Bean ${fileName}Bean=new ${upClassName}Bean();
	      if(id!=null){
	         ${fileName}Bean=${className}Service.getById(id);
	      } 
	      return new ModelAndView("/${ftlpath}/${fileName}_edit").addObject("${fileName}Bean", ${fileName}Bean);
	}
	
	/** 
	 * @Description： 保存或修改
	 * @author:${author} 
	 * @since:
	 */
	@RequestMapping(value="/edit${fileName?cap_first}")
	@ResponseBody
	public Result edit${fileName?cap_first}(${upClassName}Bean ${fileName}Bean){
	   if(${fileName}Bean.get${upClassName}Id()==null){
			  boolean result=${className}Service.save(${fileName}Bean);
			  if(!result){
				  return new Result(Status.ERROR,"保存失败"); 
			  } 
			 return new Result(Status.SUCCESS,"保存成功"); 
		  }else{
			  boolean result=${className}Service.updateById(${fileName}Bean);
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
	public Result delete${fileName?cap_first}(@PathVariable("id")Long id){
		boolean flag=${className}Service.deleteById(id);
		if(!flag){ 
			return new Result(Status.ERROR,"删除失败");
		}
		 return new Result(Status.SUCCESS,"删除成功");
	}
	
	
}