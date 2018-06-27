package Models.Enum;

/**
 * Created by Administrator on 2017-08-27.
 */
public enum ESOperateType {
    QUERY("_search"),//查询
    COUNT("_count"),//统计
    DELETE("_delete");//删除
    private String value;
    private ESOperateType(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
    public static void main(String[] args) {
        for(Models.Enum.ESOperateType type : Models.Enum.ESOperateType.values()){
            System.out.println(type + ":" + type.value);
        }
    }
}
