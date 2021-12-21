package ${controlPath};


import ${servicePath}.${entity}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @description:
 * @author: ${author}
 * @date: ${date}
 */
@Slf4j
@RestController
@RequestMapping(value="/${entity?uncap_first}")
public class ${entity}Controller {

    @Autowired
    private ${entity}Service ${entity?uncap_first}Service;


}
