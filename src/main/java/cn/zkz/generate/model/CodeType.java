package cn.zkz.generate.model;

/**
 * @Desc
 * @Author zkz
 * @Date 2021/12/20
 */
public enum CodeType {

    ENTITY(1, "实体"),
    DO(2, "do"),
    DTO(3, "dto"),
    CONTORL(4, "control"),
    SERVICE(5, "service"),
    SERVICEIMPL(6, "serviceimpl"),
    XML(7, "xml"),
    MAPPER(8, "mapper"),

    ;

    private Integer val;
    private String desc;

    CodeType(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    public Integer getVal() {
        return this.val;
    }
}
