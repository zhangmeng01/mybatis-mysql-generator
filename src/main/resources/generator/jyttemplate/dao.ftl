package ${package}.dao;
import ${package}.entity.${className?cap_first};
import org.springframework.stereotype.Repository;
<#assign upClassName=className?cap_first>  
@Repository
public interface I${upClassName}Dao extends ICrudBaseDao<${upClassName}>{
 
   
}