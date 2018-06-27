package Utils;//package FW.Utils;
//
//import Models.Interface.IContext;
//import Models.Interface.IDispatch;
//import Models.Interface.IStub;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by Administrator on 2017-05-22.
// */
//public class ContextUtil
//{
//    private static final ThreadLocal<IContext> contextThread = new ThreadLocal();
//
//    public static IStub stub = null;
//
//    public static final IContext get()
//    {
//        return (IContext)contextThread.get();
//    }
//
//    public static final void set(IContext context)
//    {
//        if (context == null)
//            contextThread.remove();
//        else
//            contextThread.set(context);
//    }
//
//    public static final void remove()
//    {
//        contextThread.remove();
//    }
//    @Deprecated
//    public static IContext getContext() {
//        return get();
//    }
//
//    protected static IStub getStub()
//    {
//        return stub;
//    }
//
//    public static IDispatch getDispatch()
//            throws Throwable
//    {
//        if (getStub() != null)
//            return getStub().getDispatch();
//        return null;
//    }
//    public static String getServiceName(HttpServletRequest request)
//    {
//        String str;
//        if ((str = request.getContextPath()).startsWith("/"))
//            str = str.substring(1);
//        return str;
//    }
//
//    public static String getCookie(HttpServletRequest request, String paramString)
//    {
//        paramString = getServiceName(request) + "." + paramString;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null)
//            for (int i = cookies.length - 1; i > 0; i--)
//                if (cookies[i].getName().equals(paramString))
//                    return cookies[i].getValue();
//        return "";
//    }
//
//    public static String createDSNSessionID(HttpServletRequest request)
//    {
//        String DSNSessionID = request.getParameter("SID");
//        if ((StringUtil.isBlankOrNull(DSNSessionID)) || ("||0".equals(DSNSessionID)))
//        {
//            DSNSessionID = getCookie(request, "MAP_DOM_SID");
//            if (DSNSessionID != null)
//                try {
//                    DSNSessionID = URLDecoder.decode(DSNSessionID, "UTF-8");
//                } catch (Throwable ignore) {
//                    ignore.printStackTrace();
//                }
//        }
//        if (StringUtil.isBlankOrNull(DSNSessionID)) {
//            DSNSessionID = "NVL||0";
//        }
//        return DSNSessionID;
//    }
//    public static boolean canEvalEntryExp(String arg)
//    {
//        if (arg == null)
//            return false;
//        if ((arg.toLowerCase().startsWith("web")) || ((arg.indexOf(".") != arg.lastIndexOf(".")) && (arg.substring(arg.lastIndexOf(".") + 1, arg.length()).toLowerCase().startsWith("web"))))
//        {
//            return true;
//        }return false;
//    }
//    public static String getDSNSessionID(IContext context)
//    {
//        if (context == null) {
//            return "NVL||0";
//        }
//
//        String DSNSessionID = (String)context.getParameter("SID");
//        if ((StringUtil.isEmpty(DSNSessionID)) || ("||0".equals(DSNSessionID))) {
//            DSNSessionID = context.getCookie("MAP_DOM_SID");
//            if (!StringUtil.isEmpty(DSNSessionID))
//                try {
//                    DSNSessionID = URLDecoder.decode(DSNSessionID, "UTF-8");
//                    DSNSessionID = getSafeDSNSessionID(context, DSNSessionID);
//                } catch (UnsupportedEncodingException ignore) {
//                    ignore.printStackTrace();
//                    return "NVL||0";
//                }
//        } else {
//            DSNSessionID = getSafeDSNSessionID(context, DSNSessionID);
//        }
//        if (StringUtil.isEmpty(DSNSessionID)) {
//            return "NVL||0";
//        }
//        return DSNSessionID;
//    }
//
//    public static String getSafeDSNSessionID(IContext context, String DSNSessionID) {
//        if (context == null)
//            return "NVL||0";
//        if ((StringUtil.isEmpty(DSNSessionID)) || ("||0".equals(DSNSessionID))) {
//            return "NVL||0";
//        }
//        DSNSessionID = HtmlUtil.unSqlInjection(DSNSessionID);
//
//        if ((DSNSessionID != null) && (DSNSessionID.startsWith("[{\"__SID\":")))
//        {
//            try
//            {
//                DSNSessionID = DSNSessionID.substring(10);
//                context.setCookie("MAP_DOM_SID", DSNSessionID);
//                context.getEnv().setDSNSessionID(DSNSessionID);
//            } catch (Throwable ignore) {
//                ignore.printStackTrace();
//            }
//        }
//        return DSNSessionID;
//    }
//
//    public static boolean ignoreCheck(String arg, boolean throwEx)
//    {
//        if ((throwEx) && (
//                (arg == null) || (((arg.indexOf(".") == arg.lastIndexOf(".")) || ((arg.indexOf(".") != arg.lastIndexOf(".")) && (!arg.substring(arg.lastIndexOf(".") + 1, arg.length()).toLowerCase().startsWith("web")))) && (!arg.toLowerCase().startsWith("web")) && (!arg.toLowerCase().startsWith("getroleidbyusercode")) && (!arg.toLowerCase().startsWith("clearsessionidbyusercode")))))
//        {
//            throw new BKError(12345, "checkExp", "不支持的Rfc调用:" + arg);
//        }if (arg == null)
//        return true;
//        if (((arg.indexOf(".") != arg.lastIndexOf(".")) && (arg.substring(arg.lastIndexOf(".") + 1, arg.length()).toLowerCase().startsWith("web"))) || (((arg.toLowerCase().startsWith("webgetdictpermission")) || (arg.toLowerCase().startsWith("webloaddicdata")) || (arg.toLowerCase().startsWith("webloadpic")) || (arg.toLowerCase().startsWith("webshowpicture")) || (arg.toLowerCase().startsWith("webdownload")) || (arg.toLowerCase().startsWith("getroleidbyusercode")) || (arg.toLowerCase().startsWith("clearsessionidbyusercode"))) && (arg.indexOf(")") == arg.lastIndexOf(")"))))
//        {
//            return true;
//        }return false;
//    }
//
//    public static boolean ignoreCheck(String arg)
//    {
//        return ignoreCheck(arg, false);
//    }
//
//    public static boolean ignoreCheckSession(IContext context)
//    {
//        if (context == null)
//            return true;
//        Object wc = context.getParameter("__webCall");
//        Object p2 = context.getParameter("__exp");
//        String call = wc == null ? "" : wc.toString();
//        String exp = p2 == null ? "" : p2.toString();
//        if (call.equalsIgnoreCase("WebDoUICommand"))
//            return true;
//        if (exp.equalsIgnoreCase("WebDoUICommand"))
//            return true;
//        boolean ignore = (ignoreCheck(call, false)) || (ignoreCheck(exp, false));
//        return ignore;
//    }
//
//    public static void ignoreCheckLogin(IContext context)
//    {
//        if (context == null)
//            return;
//        context.setAttribute("noAlert", Boolean.valueOf(true));
//    }
//
//    @Deprecated
//    public static boolean isDoLogin(IContext context)
//    {
//        if (context == null)
//            return false;
//        return (context.getAttribute("noAlert") == Boolean.TRUE) || (context.getAttribute("logout") == Boolean.TRUE) || (context.getAttribute("islogin") == Boolean.TRUE) || ("1".equals(context.getParameter("noAlert"))) || ("1".equals(context.getParameter("logout"))) || ("1".equals(context.getParameter("islogin")));
//    }
//
//    public static boolean isWX(IContext context)
//    {
//        if (context == null)
//            return false;
//        String openid = (String)context.getParameter("openid");
//        if (!StringUtil.isEmpty(openid))
//            return true;
//        String media = getUIMedia(context);
//        if (VarUtil.toInt(media) == 2)
//            return true;
//        return (VarUtil.toBool(context.getAttribute("isWX")) == Boolean.TRUE.booleanValue()) || (VarUtil.toBool(context.getParameter("isWX")) == Boolean.TRUE.booleanValue());
//    }
//
//    public static boolean isMobile(IContext context)
//    {
//        if (context == null)
//            return false;
//        String media = getUIMedia(context);
//        if (VarUtil.toInt(media) >= 1) {
//            return true;
//        }
//        return (VarUtil.toBool(context.getAttribute("isMobile")) == Boolean.TRUE.booleanValue()) || (VarUtil.toBool(context.getParameter("isMobile")) == Boolean.TRUE.booleanValue());
//    }
//
//    public static boolean isAjax(IContext context)
//    {
//        if (context == null)
//            return false;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return false;
//        String requestWith = req.getHeader("X-Requested-With");
//        if ("XMLHttpRequest".equalsIgnoreCase(requestWith))
//            return true;
//        return false;
//    }
//
//    public static String getUIDebugFlag(IContext context)
//    {
//        if (context == null)
//            return "";
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return "";
//        String url = req.getRequestURI();
//        String flag = url.indexOf("_src.") >= 0 ? "_src" : "";
//        return flag;
//    }
//
//    public static String getUIVersion(IContext context)
//    {
//        if (context == null)
//            return "0";
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return "0";
//        String uiVersion = req.getHeader("X-YIGO-Version");
//        if (StringUtil.isEmpty(uiVersion))
//            uiVersion = VarUtil.toString(req.getAttribute("X-YIGO-Version"));
//        return uiVersion;
//    }
//
//    public static String getUIURL(IContext context)
//    {
//        if (context == null)
//            return null;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return null;
//        String uiURL = req.getHeader("X-YIGO-URL");
//        if (StringUtil.isEmpty(uiURL))
//            uiURL = VarUtil.toString(req.getAttribute("X-YIGO-URL"));
//        return uiURL;
//    }
//
//    public static String getUIMedia(IContext context)
//    {
//        if (context == null)
//            return null;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return null;
//        String uiMedia = req.getHeader("X-YIGO-Media");
//        if (StringUtil.isEmpty(uiMedia))
//            uiMedia = VarUtil.toString(req.getAttribute("X-YIGO-Media"));
//        return uiMedia;
//    }
//
//    public static String getUIHeader(IContext context, String k)
//    {
//        if (context == null)
//            return null;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return null;
//        String uiMedia = req.getHeader(k);
//        return uiMedia;
//    }
//
//    public static boolean isNewWebUI(IContext ctx)
//    {
//        String UIVersion = getUIVersion(ctx);
//        return VarUtil.toInt(UIVersion) >= 8;
//    }
//
//    public static void setSessionValue(IContext context, String key, String value)
//    {
//        if (context == null)
//            return;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return;
//        req.getSession().setAttribute(key, value);
//    }
//
//    public static Object getSessionValue(IContext context, String key)
//    {
//        if (context == null)
//            return null;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return null;
//        return req.getSession().getAttribute(key);
//    }
//
//    public static void removeSessionValue(IContext context, String key)
//    {
//        if (context == null)
//            return;
//        HttpServletRequest req = (HttpServletRequest)context.getRequest();
//        if (req == null)
//            return;
//        req.getSession().removeAttribute(key);
//    }
//
//    public static void cancelForceInnerShow(IContext context)
//    {
//        if (context == null)
//            return;
//        context.removeAttribute("WEB_NEWTAB");
//    }
//
//    public static void setForceInnerShow(IContext context)
//    {
//        if (context == null)
//            return;
//        context.setAttribute("WEB_NEWTAB", Boolean.FALSE);
//    }
//
//    public static Boolean isForceInnerShow(IContext context)
//    {
//        if (context == null)
//            return Boolean.valueOf(false);
//        Object o = context.getAttribute("WEB_NEWTAB");
//        if (o == Boolean.FALSE)
//            return Boolean.valueOf(true);
//        return Boolean.valueOf(false);
//    }
//
//    @Deprecated
//    public static int getJSPTagCurrPage(IContext context, int dbLocation)
//    {
//        String key = getJSPTagCurrPageKey(context, dbLocation);
//        Object oCurrPage = context.getParameter(key);
//        return StringUtil.isBlankOrStrNull(oCurrPage) ? 0 : VarUtil.toInt(oCurrPage);
//    }
//
//    public static boolean isEmpty(String SID) {
//        return (StringUtil.isEmpty(SID)) || ("NVL||0".equalsIgnoreCase(SID)) || ("||0".equalsIgnoreCase(SID));
//    }
//}