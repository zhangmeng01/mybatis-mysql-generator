package ${package}.dao;
import com.qitoon.framework.core.dao.ICrudDao;
import ${package}.entity.${className?cap_first};
import org.springframework.stereotype.Repository;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.qitoon.framework.utils.sqlPlus.EntityWrapper;
<#assign upClassName=className?cap_first>  
@Repository
public interface I${upClassName}Dao extends ICrudDao<${upClassName}>{
 
   /**
    * 功能描述:根据对象条件查询
    */
    List<${upClassName}> findEntityWrapper(@Param("ew")EntityWrapper entityWrapper);
}