package Extend;

/**
 * Created by Administrator on 2016/12/5.
 */
public final class StringExtend {
    public static boolean Empty(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * 去除前后指定字符
     *
     * @param source 目标字符串
     * @param beTrim 要删除的指定字符
     * @return 删除之后的字符串
     * 调用示例：System.out.println(Trim(", ashuh  ",","));
     */
    public static String Trim(String source, String beTrim) {
        if (source == null) {
            return "";
        }
        source = source.trim();
        if (source.isEmpty()) {
            return "";
        }
        String beginChar = source.substring(0, 1);
        // 循环去掉字符串首的beTrim字符
        while (beginChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(1, source.length());
            beginChar = source.substring(0, 1);
        }
        // 循环去掉字符串尾的beTrim字符
        String endChar = source.substring(source.length() - 1, source.length());
        while (endChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(0, source.length() - 1);
            endChar = source.substring(source.length() - 1, source.length());
        }
        return source;
    }

    public static String ReplaceEmpty(String str) {
        if (Empty(str)) {
            return "";
        }
        return str.replace("&nbsp;", "");
    }
}
