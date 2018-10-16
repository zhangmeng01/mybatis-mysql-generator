package ${package}.service.impl;
<#assign upClassName=className?cap_first>
import ${package}.domain.${upClassName};
import ${package}.mapper.I${upClassName}Mapper;
import org.springframework.stereotype.Service;
import ${package}.service.I${upClassName}Service;
import com.yjtoon.framework.impl.BaseServiceImpl;
 
@Service
public class ${upClassName}ServiceImpl extends BaseServiceImpl<I${upClassName}Mapper,${upClassName}> implements I${upClassName}Service{
   
   
}