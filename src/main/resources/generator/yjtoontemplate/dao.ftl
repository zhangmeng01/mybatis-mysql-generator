package ${package}.mapper;
import com.yjtoon.framework.mapper.ICrudMapper;
import ${package}.domain.${className?cap_first};
import org.springframework.stereotype.Repository;
<#assign upClassName=className?cap_first>  

@Repository
public interface I${upClassName}Mapper extends ICrudMapper<${upClassName}>{
 
   
}