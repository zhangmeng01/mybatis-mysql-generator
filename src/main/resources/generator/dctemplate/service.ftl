package ${package};
<#assign upClassName=className?cap_first>  
import ${package}.bean.${upClassName}Bean;
import com.qitoon.framework.core.service.ICrudService;
 
public interface I${upClassName}Service extends ICrudService<${upClassName}Bean>{
     
 
}