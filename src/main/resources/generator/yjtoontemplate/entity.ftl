package ${package}.domain;
import com.yjtoon.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#assign upClassName=className?cap_first> 
@Data
@EqualsAndHashCode(callSuper=false) 
public class ${upClassName} extends BaseEntity<${upClassName}> {

	private static final long serialVersionUID = 1L;     
<#list columns as column>
    private ${column.dataType} ${column.columnName}; //${column.columnComment}
</#list> 
 
}