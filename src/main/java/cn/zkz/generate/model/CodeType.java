package cn.zkz.generate.model;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/20
 */
public enum CodeType {

    ENTITY("entity", "实体"),
    DO("do", "do"),
    DTO("dto", "dto"),
    CONTORL("control", "control"),
    SERVICE("service", "service"),
    SERVICEIMPL("serviceimpl", "serviceimpl"),
    XML("xml", "xml"),
    MAPPER("mapper", "mapper"),
    PAGEQUERY("pageQuery", "pageQuery"),
    PAGEQUERYHANDLER("pageQueryHandler", "pageQueryHandler"),
    DOMAIN("domain", "domain"),
    GATEWAY("gateway", "gateway"),
    GATEWAYIMPL("gatewayImpl", "gatewayImpl"),
    DO2DTO("DO2DTO", "DO2DTO"),
    DO2ENTITY("DO2ENTITY", "DO2ENTITY"),
    ENTITY2DTO("ENTITY2DTO", "ENTITY2DTO"),

    ;

    private String val;
    private String desc;

    CodeType(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public String getVal() {
        return this.val;
    }
}
