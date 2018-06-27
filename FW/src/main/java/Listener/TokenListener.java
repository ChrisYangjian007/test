package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenListener implements ServletContextListener {
	public final static ExecutorService executors = Executors.newFixedThreadPool(3);
//	private static Logger log = Logger.getLogger(TokenListener.class);
//	public static AccessToken accessToken = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		executors.execute(new Runnable(){
			public void run() {
				while (true) {
//					try {
//						accessToken = HuanxinUtil.getAccessToken();
//						if (null != accessToken) {
//							log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());
//							// 休眠5180000秒
//							Thread.sleep((accessToken.getExpiresIn() - 4000) * 1000);
//						} else {
//							// 如果access_token为null，60秒后再获取
//							Thread.sleep(60 * 1000);
//						}
//
//					} catch (InterruptedException e) {
//						log.error("刷token线程中断了", e);
//						break;
//					}
				}
			}
		});
	}

}
