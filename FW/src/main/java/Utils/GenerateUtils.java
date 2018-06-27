package Utils;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author 杨建
 * @date 2018/3/7
 */
public class GenerateUtils {
    public static String[] getArrray(int index) {
        String[][] array = new String[][]{{"0", "2", "4", "6", "8", "1", "3", "5", "7", "9"}, {"1", "3", "5", "7", "9", "0", "2", "4", "6", "8"}, {"2", "4", "6", "8", "0", "3", "5", "7", "9", "1"}, {"3", "5", "7", "9", "1", "2", "4", "6", "8", "0"}, {"4", "6", "8", "0", "2", "5", "7", "9", "1", "3"}, {"5", "7", "9", "1", "3", "4", "6", "8", "0", "2"}, {"6", "8", "0", "2", "4", "7", "9", "1", "3", "5"}, {"8", "0", "2", "4", "6", "9", "1", "3", "5", "7"}, {"7", "9", "1", "3", "5", "8", "0", "2", "4", "6"}, {"9", "1", "3", "5", "7", "0", "2", "4", "6", "8"}};
        return array[index];
    }

    /**
     * @param ruleCode  标签规则
     * @param clearCode 明码
     * @return
     */
    public static String codetozs(String ruleCode, int clearCode) {
        String str = String.format("%09d", clearCode);
        String substring = str.substring(str.length() - 1);
        StringBuilder sb = new StringBuilder();
        sb.append(ruleCode + substring);
        String[] arrray = getArrray(Integer.parseInt(substring));
        for (int i = 0; i < 9; ++i) {
            sb.append(arrray[Integer.parseInt(str.substring(i, i + 1))]);
        }
        return sb.toString();
    }

    /**
     * 追溯码转明码
     *
     * @param TraceCode 追溯吗
     * @return
     */
    public static String zstoCode(String TraceCode) {
        String orgcode = TraceCode.substring(0, 5);
        String substring = orgcode.substring(orgcode.length() - 1);
        String[] arrray = getArrray(Integer.parseInt(substring));
        Map<String, Integer> map = new HashMap();
        for (int i = 0; i < arrray.length; ++i) {
            map.put(arrray[i], Integer.valueOf(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < 14; ++i) {
            sb.append(map.get(TraceCode.substring(i, i + 1)));
        }
        String newStr = sb.toString().replaceFirst("^0*", "");
        return newStr;
    }

    /**
     * 集合转码段
     *
     * @param longs
     * @return
     */
    public static String listToCodeString(List<Integer> longs) {
        Integer start = -1;
        Integer last = -1;
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < longs.size(); i++) {
                int str = longs.get(i);
                if (start == -1) {
                    start = str;
                } else {
                    if (last == -1) {
                        last = start;
                    }
                    if (str - last == 1) {
                        last = str;
                    } else {
                        sb = sb.append(start).append("-").append(last).append(";");
                        start = str;
                        last = -1;
                    }
                }
            }
            if (last != -1) {
                sb = sb.append(start).append("-").append(last).append(";");
            }
            if (longs.size() > 1) {
                if (longs.get(longs.size() - 1) - longs.get(longs.size() - 2) != 1) {
                    sb = sb.append(longs.get(longs.size() - 1)).append("-").append(longs.get(longs.size() - 1)).append(";");
                }
            } else {
                sb = sb.append(longs.get(longs.size() - 1)).append("-").append(longs.get(longs.size() - 1)).append(";");
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }

    /**
     * 码段比较
     *
     * @param userCode      当前码段
     * @param availableCode 库存可用码段
     */
    public static List<Long> jiaoListToList(String userCode, String availableCode) {

        List<Long> list1 = new ArrayList();
        List<Long> list2 = new ArrayList();
        String[] split = userCode.split(";");
        for (int i = 0; i < split.length; ++i) {
            String[] split1 = split[i].split("-");
            for (long j = Long.parseLong(split1[0]); j <= Long.parseLong(split1[1]); ++j) {
                list1.add(Long.valueOf(j));
            }
        }

        String[] splitv = availableCode.split(";");
        for (int i = 0; i < splitv.length; ++i) {
            String[] split1v = splitv[i].split("-");
            for (long j = Long.parseLong(split1v[0]); j <= Long.parseLong(split1v[1]); ++j) {
                list2.add(Long.valueOf(j));
            }
        }
        list2.removeAll(list1);
        return list2;
    }

    /**
     * 集合交集
     *
     * @param userCode      当前码段
     * @param availableCode 库存可用码段
     */
    public static List<Integer> listToList(String userCode, String availableCode) {

        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        String[] split1 = userCode.split("-");
        for (int j = Integer.valueOf(split1[0]); j <= Integer.valueOf(split1[1]); ++j) {
            list1.add(j);
        }
        String[] splitv = availableCode.split(";");
        for (int i = 0; i < splitv.length; ++i) {
            String[] split1v = splitv[i].split("-");
            for (int j = Integer.valueOf(split1v[0]); j <= Integer.valueOf(split1v[1]); ++j) {
                list2.add(j);
            }
        }
        list2.retainAll(list1);
        return list2;
    }

    /**
     * 集合取交集
     *
     * @param firstArrayList
     * @param secondArrayList
     * @return
     */
    public static List<Integer> receiveCollectionList(List<Integer> firstArrayList, List<Integer> secondArrayList) {
        List<Integer> resultList = new ArrayList<Integer>();
        try {
            LinkedList<Integer> result = new LinkedList<Integer>(firstArrayList);// 大集合用linkedlist
            HashSet<Integer> othHash = new HashSet<Integer>(secondArrayList);// 小集合用hashset
            Iterator<Integer> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
            while (iter.hasNext()) {
                if (!othHash.contains(iter.next())) {
                    iter.remove();
                }
            }
            resultList = new ArrayList<Integer>(result);
        } catch (Exception e) {

        }
        return resultList;
    }

    /**
     * 集合取差集
     *
     * @param firstArrayList
     * @param secondArrayList
     * @return
     */
    public static List<Integer> chaCollectionList(List<Integer> firstArrayList, List<Integer> secondArrayList) {
        List<Integer> resultList = new ArrayList<Integer>();
        try {
            LinkedList<Integer> result = new LinkedList<Integer>(firstArrayList);// 大集合用linkedlist
            HashSet<Integer> othHash = new HashSet<Integer>(secondArrayList);// 小集合用hashset
            Iterator<Integer> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
            while (iter.hasNext()) {
                if (othHash.contains(iter.next())) {
                    iter.remove();
                }
            }
            resultList = new ArrayList<Integer>(result);
        } catch (Exception e) {

        }
        return resultList;
    }

    /**
     * 码段转集合
     *
     * @param availableCode
     * @return
     */
    public static List<Integer> codeToList(String availableCode) {

        List<Integer> list2 = new ArrayList();
        try {
            String[] splitv = availableCode.split(";");
            for (int i = 0; i < splitv.length; ++i) {
                String[] split1v = splitv[i].split("-");
                for (int j = Integer.valueOf(split1v[0]); j <= Integer.valueOf(split1v[1]); ++j) {
                    list2.add(j);
                }
            }
        } catch (Exception e) {

        }

        return list2;
    }

    public static String getImportTitle(String userAgent, String title) throws UnsupportedEncodingException {
        String finalFileName = null;
        if (StringUtils.contains(userAgent, "MSIE")) {//IE浏览器
            finalFileName = URLEncoder.encode(title, "UTF8");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
            finalFileName = new String(title.getBytes(), "ISO8859-1");
        } else {
            finalFileName = URLEncoder.encode(title, "UTF8");//其他浏览器
        }
        return finalFileName;
    }

}
