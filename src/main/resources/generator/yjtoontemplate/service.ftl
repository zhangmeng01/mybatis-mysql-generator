package ${package}.service;
<#assign upClassName=className?cap_first>  
import ${package}.domain.${upClassName};
import com.yjtoon.framework.IBaseService;
 
public interface I${upClassName}Service extends IBaseService<${upClassName}>{
     
 
}