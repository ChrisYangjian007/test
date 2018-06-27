package Listener;

import Interface.IBasedataService;
import Models.BaseData;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

/**
 * 系统全局容器
 * @author John
 */
public class Application implements ServletContextListener {
	private static ServletContext context;
	private static String PREFIX = "SV.";
	@Override
	public void contextInitialized(ServletContextEvent event) {
		 context = event.getServletContext();	
		 initAppVariable();
		 //XmppServer.getInstance();
		 //ConfigManager.getInstance().getConfig().setProperty("server.home.dir", Application.class.getResource("/").getPath());
	}
	private static void initAppVariable(){
		ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(context);
		IBasedataService service = app.getBean(IBasedataService.class);
		Map<String, BaseData> map = service.getAppVariable();
		for(String key : map.keySet()){
			context.setAttribute(PREFIX+key, map.get(key));
		}
	}
	
	/**
	 * 刷新全局变量值
	 */
	public static void refresh(){
		initAppVariable();
	}

	/**
	 * 获取全局变量值
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		BaseData bd = (BaseData)context.getAttribute(PREFIX+key);
		String val = null;
		if(bd != null){
			val = bd.getName();
		}
		return val;
	}
	public static String getString(String key,String defaultVal){
		BaseData bd = (BaseData)context.getAttribute(PREFIX+key);
		String val = null;
		if(bd != null){
			val = bd.getName();
		}
		val = val == null?defaultVal:val;
		return val;
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		

	}
}
