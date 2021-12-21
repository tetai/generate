package ${do2EntityPath};


import org.mapstruct.Mapper;
import ${doPath}.${entity}DO;
import ${entityPath}.${entity}Entity;
import org.mapstruct.factory.Mappers;
import com.wakedata.kangyang.infra.common.BaseConvert;
/** 
 * @description:
 * @author: ${author}
 * @date: ${date}
 */
@Mapper
public interface ${entity}EntityDoConvert extends BaseConvert<${entity}Entity, ${entity}DO> {

    ${entity}EntityDoConvert INSTANCE = Mappers.getMapper(${entity}EntityDoConvert.class);

}
