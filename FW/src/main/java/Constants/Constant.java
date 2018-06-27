package Constants;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017-08-20.
 */
public class Constant {
    //系统启动时是否进行初始化操作（删除已有的所有数据并重新生成相应的index与mapping）
    public static boolean INIT;
    //系统启动时是否进行初始化检测（包括检测index、mapping是否存在，如果不存在则根据实体类的注解描述自动解析并创建相应的index、mapping）
    public static boolean IS_CHECK;
    public static int START;//搜索默认开始位置
    public static int ROWS;//搜索结果条数
    public static List<String> HOSTS;//服务器地址
    public static int CLIENT_PORT;//client端口号
    public static int WEB_PORT;//web端口号
    public static String BASE_URL;
    public static String CLUSTER_NAME;//es集群名称
    public static String MODELS_PACKAGE;//实体类所在包路径
    public static String HIGHLIGHT_PRE_TAGS;//默认的高亮搜索前缀
    public static String HIGHLIGHT_POST_TAGS;//默认的高亮搜索后缀

    /**
     * 系统发布环境：正式、开发、测试
     */
    public static String SYSTEM_PUBLISH_SETTING = "SV004";
    /**
     * 全局异常提示
     */
    public static String SYSTEM_GLOBAL_MESSAGE = "EX0001";
    /**
     * 短格式日期
     */
    public static final SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 时分秒格式日期
     */
    public static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_HM = "HH:mm";
    public static final String DATE_FORMAT_FOR_ENTITY = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时分秒格式日期
     */
    public static final SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * ID模式
     */
    public static final SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssS");
    /**
     * 文件存储目录
     */
    public static final String UPLOADFILE = "uploadfiles";
    /**
     * 广场ICON 存储
     */
    public static final String UPLOADFILE_SQUARE = "squarefiles";
    /**
     * 存储头相
     */
    public static final String UPLOADFILE_HEADIMAGE = "headimagefiles";
    /**
     * 未登录状态
     */
    public static final String GLOBAL_NOT_LOGIN = "-1";
    /**
     * 布尔true
     */
    public static final String GLOBAL_BOOLEAN_TRUE = "1";
    /**
     * 布尔false
     */
    public static final String GLOBAL_BOOLEAN_FALSE = "0";
    public static final String DETAIL_HTML_PATH = "api/apiCommon/html?type=TYPE&id=ID";
}
