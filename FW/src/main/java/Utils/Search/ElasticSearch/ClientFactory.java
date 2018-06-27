//package Service.ElasticSearch;
//
//import Constants.Constant;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
////import org.apache.log4j.Logger;
//
///**
// * Created by Administrator on 2017-08-20.
// */
//public class ClientFactory {
////    private static Logger logger = Logger.getLogger(ClientFactory.class);
//    private static TransportClient client;
//
//    public static TransportClient getClient() {
//        if (client == null) {
//            Settings settings = Settings.builder().put("cluster.name", Constant.CLUSTER_NAME).build();
//            try {
//                for (String host : Constant.HOSTS) {
////                    logger.info("发现节点：" + host + "...........正在连接该节点>>>>>>>>>");
//                    client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Constant.CLIENT_PORT));
//                }
//            } catch (UnknownHostException e) {
////                logger.info("连接es客户端发生错误" + e.toString());
//                e.printStackTrace();
//            }
//        }
//        return client;
//    }
//}
