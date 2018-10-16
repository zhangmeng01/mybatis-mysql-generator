package ${package}.dao.${module};

import ${package}.dao.ICrudDao;
import ${package}.entity.${module}.${className?cap_first};
import org.springframework.stereotype.Repository;
<#assign upClassName=className?cap_first>  
@Repository
public interface I${upClassName}Dao extends ICrudDao<${upClassName}>{
 
}