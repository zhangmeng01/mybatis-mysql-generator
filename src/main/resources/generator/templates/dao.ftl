package ${package}.dao.${module};
import com.qitoon.framework.core.dao.ICrudDao;
import ${package}.entity.${module}.${className?cap_first};
import org.springframework.stereotype.Repository;
<#assign upClassName=className?cap_first>  
@Repository
public interface I${upClassName}Dao extends ICrudDao<${upClassName}>{

    /**
    * 功能描述:根据对象条件查询
    */
    List<${upClassName}> findEntityWrapper(@Param("ew")EntityWrapper entityWrapper);
}