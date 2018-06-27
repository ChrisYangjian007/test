//package Common;
//
//import Constants.Constant;
//import Extend.StringExtend;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.Properties;
//
///**
// * Created by Administrator on 2017-08-20.
// */
//public class Init {
//    static{
//        try {
//            initConstants();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        excuteCheck();
//    }
//    //执行初始化检测
//    private static void excuteCheck(){
//        if(Constant.INIT){
//            new Check().rebirthPlan();
//        }
//        if(!Constant.INIT && Constant.IS_CHECK){
//            new Check().check();
//        }
//    }
//    /*初始化相关常量参数*/
//    private static void initConstants() throws FileNotFoundException {
//        String strPath = StringExtend.Trim(Thread.currentThread().getContextClassLoader().getResource("").getPath(),"/")+"/";
//        InputStream in = new FileInputStream(strPath + "elasticsearch.properties");
//        Properties prop = new Properties();
//        try {
//            prop.load(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Constant.INIT = Boolean.parseBoolean(prop.getProperty("init", "false"));
//        Constant.IS_CHECK = Boolean.parseBoolean(prop.getProperty("isCheck","true"));
//        Constant.CLUSTER_NAME = prop.getProperty("cluster.name").trim();
//        Constant.START = Integer.parseInt(prop.getProperty("start","0"));
//        Constant.ROWS = Integer.parseInt(prop.getProperty("rows","10"));
//        Constant.CLIENT_PORT = Integer.parseInt(prop.getProperty("client.port","9300").trim());
//        Constant.WEB_PORT = Integer.parseInt(prop.getProperty("web.port","9200").trim());
//        Constant.HOSTS = Arrays.asList(prop.getProperty("hosts", "localhost").trim().split(","));
//        Constant.MODELS_PACKAGE = prop.getProperty("models.package.dir").trim();
//        Constant.HIGHLIGHT_PRE_TAGS = prop.getProperty("highlight.pre.tags","<span style=\"color:red\"").trim();
//        Constant.HIGHLIGHT_POST_TAGS = prop.getProperty("highlight.post.tags","</span>").trim();
//        Constant.BASE_URL = "http://" + Constant.HOSTS.get(0) + ":" + Constant.WEB_PORT + "/";
////        Preconditions.checkNotNull(Constant.MODELS_PACKAGE, "请设置实体类所在的包路径（models.package）！");
//    }
//}
