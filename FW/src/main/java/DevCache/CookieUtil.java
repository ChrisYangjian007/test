package DevCache;

import Extend.StringExtend;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
//import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017-08-07.
 */
public class CookieUtil {

//    private static final Logger logger = Logger.getLogger(CookieUtil.class);
    /// 添加Cookie
    /// <param name="p_strValue">值</param>
    /// <param name="p_intDays">有效天数</param>
    /// <param name="p_strKey">键名</param>
    public static void setCookie(HttpServletResponse response, String p_strKey, String p_strValue, int p_intDays) {
        Cookie m_Cookie = new Cookie(p_strKey, p_strValue);
        m_Cookie.setPath("/");
        if (p_intDays != 0) {
            m_Cookie.setMaxAge(p_intDays*60*60*24);
        }
        response.addCookie(m_Cookie);
    }

    /// 获取Coolie值
    /// <param name="p_strKey">键名</param>
    public static String getCookie(HttpServletRequest request, String p_strKey) {
        String m_strReturn = "";
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
//            logger.info("没有cookie==============");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(p_strKey)) {
                    m_strReturn = cookie.getValue();
                    break;
                }
            }
        }
        return m_strReturn;
    }

    /// 更新双键Cookie的值
    /// <param name="p_strKey">主键</param>
    /// <param name="p_strSubKey">子键</param>
    /// <param name="p_strValue">值</param>
    public static void UpdateCookie(HttpServletRequest request, HttpServletResponse response, String p_strKey, String p_strValue, int p_intDays) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
//            logger.info("没有cookie==============");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(p_strKey)) {
                    cookie.setValue(p_strValue);
                    cookie.setPath("/");
                    cookie.setMaxAge(p_intDays);// 设置为30min
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
    /// 删除登录信息
    public static void Clear(HttpServletRequest request, HttpServletResponse response,String p_strKey) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
//            logger.info("没有cookie==============");
        } else {
            for (Cookie cookie : cookies) {
                if (StringExtend.Empty(p_strKey)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                } else {
                    if (cookie.getName().equals(p_strKey)) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);// 立即销毁cookie
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        break;
                    }
                }
            }
        }
    }
    /**
     * 根据名字获取cookie
     * @param request
     * @param p_strKey cookie名字
     */
    public static Cookie getCookieByName(HttpServletRequest request,String p_strKey){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(p_strKey)){
            return (Cookie)cookieMap.get(p_strKey);
        }else{
            return null;
        }
    }
    /**
     * 将cookie封装到Map里面
     * @param request
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
