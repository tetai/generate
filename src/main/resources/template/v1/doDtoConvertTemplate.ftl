package ${do2DtoPath};


import org.mapstruct.Mapper;
import ${doPath}.${entity}DO;
import ${dtoPath}.${entity}DTO;
import org.mapstruct.factory.Mappers;
import com.wakedata.kangyang.infra.common.BaseConvert;
/**
* @description:
* @author: ${author}
* @date: ${date}
*/
@Mapper
public interface ${entity}DtoDoConvert extends BaseConvert<${entity}DTO, ${entity}DO> {

    ${entity}DtoDoConvert INSTANCE = Mappers.getMapper(${entity}DtoDoConvert.class);

}