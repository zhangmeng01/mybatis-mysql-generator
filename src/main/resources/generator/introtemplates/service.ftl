package ${package}.${module};
<#assign upClassName=className?cap_first>  
import ${package}.bean.${module}.${upClassName}Bean;
import java.util.List;
import com.github.pagehelper.PageInfo;
import ${package}.base.ICrudService;
 
public interface I${upClassName}Service extends ICrudService<${upClassName}Bean>{
     
 
}