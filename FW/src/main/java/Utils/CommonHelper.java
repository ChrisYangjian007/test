package Utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * Created by Administrator on 2017-10-17.
 */
public class CommonHelper {
    public static String ModelToJson(Object p_objModel) {
        return JSON.toJSONString(p_objModel);
//        JavaScriptSerializer m_javaScriptSerializer = new JavaScriptSerializer();
//        m_javaScriptSerializer.MaxJsonLength = Int32.MaxValue; //取得最大数值
//        var m_strJson = m_javaScriptSerializer.Serialize(p_objModel);
//        m_strJson = Regex.Replace(m_strJson, @ "\\/Date\((\d+)\)\\/", match =>
//        {
//            DateTime m_dt = new DateTime(1970, 1, 1);
//            m_dt = m_dt.AddMilliseconds(long.Parse(match.Groups[1].Value));
//            m_dt = m_dt.ToLocalTime();
//            return m_dt.ToString("yyyy-MM-dd HH:mm:ss");
//        });
//        return m_strJson;
    }

    public static <T> T JsonToModel(Class<T> t, String p_objModel) {
        return JSON.parseObject(p_objModel, t);
    }

    public static <T> List<T> JsonToModel(String p_objModel, Class<T> t) {
        return JSON.parseArray(p_objModel, t);
    }

    /// <summary>
    /// 对象互转
    /// </summary>
    /// <typeparam name="T">转换后的对象</typeparam>
    /// <param name="obj">原始对象</param>
    /// <param name="toModel">true Dto,Interface转model  false Model转dto,Interface</param>
    /// <param name="preFix">前缀</param>
    public static <T> T ModelToModel(Class<T> t,Object p_objModel) {
        String strJson = ModelToJson(p_objModel);
        return JsonToModel(t, strJson);
    }

    /// <summary>
    /// 对象互转
    /// </summary>
    /// <typeparam name="T">转换后的对象</typeparam>
    public static <T> List<T> ModelToModel(Object p_objModel, Class<T> t) {
        String strJson = ModelToJson(p_objModel);
        return JsonToModel(strJson, t);
    }

    /// <summary>
    /// 计时器结束
    /// </summary>
    public static String TimerEnd(StopWatch watch) {
        watch.stop();
        double costtime = watch.getTotalTimeMillis();
        return String.valueOf(costtime);
    }

    /// <summary>
    /// 计时器开始
    /// </summary>
    public static StopWatch TimerStart() {
        StopWatch watch = new StopWatch();
        watch.start();
        return watch;
    }
}
