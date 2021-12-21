package ${entity2DtoPath};


import org.mapstruct.Mapper;
import ${entityPath}.${entity}Entity;
import ${dtoPath}.${entity}DTO;
import org.mapstruct.factory.Mappers;
import com.wakedata.kangyang.infra.common.BaseConvert;
/**
* @description:
* @author: ${author}
* @date: ${date}
*/
@Mapper
public interface ${entity}DtoEntityConvert extends BaseConvert<${entity}DTO, ${entity}Entity> {

    ${entity}DtoEntityConvert INSTANCE = Mappers.getMapper(${entity}DtoEntityConvert.class);

}