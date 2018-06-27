package com.dalian.sea.common;

import com.dalian.sea.DTO.TreeJsonDTO;
import Extend.StringExtend;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017-10-19.
 */
public class HandleHelper {
    /// <summary>
    /// 转换树形Json
    /// </summary>
    /// <returns></returns>
    public static String TreeToJson(List<TreeJsonDTO> list, String ParentId) {
        final StringBuilder strJson = new StringBuilder();
        List<TreeJsonDTO> item = list.stream().filter(p -> p.getParentId().equals(ParentId)).collect(Collectors.toList());
        strJson.append("[");
        if (item.size() > 0) {
            item.forEach(entity -> {
                strJson.append("{");
                strJson.append("\"id\":\"" + entity.getId() + "\",");
                strJson.append("\"text\":\"" + StringExtend.ReplaceEmpty(entity.getText()) + "\",");
                strJson.append("\"value\":\"" + entity.getValue() + "\",");
                if (!StringExtend.Empty(entity.getAttribute())) {
                    strJson.append("\"" + entity.getAttribute() + "\":\"" + entity.getAttributeValue() + "\",");
                }
                if (!StringExtend.Empty(entity.getAttributeA())) {
                    strJson.append("\"" + entity.getAttributeA() + "\":\"" + entity.getAttributeValueA() + "\",");
                }
                if (!StringExtend.Empty(StringExtend.ReplaceEmpty(entity.getTitle()))) {
                    strJson.append("\"title\":\"" + StringExtend.ReplaceEmpty(entity.getTitle()) + "\",");
                }
                if (!StringExtend.Empty(StringExtend.ReplaceEmpty(entity.getImg()))) {
                    strJson.append("\"img\":\"" + StringExtend.ReplaceEmpty(entity.getImg()) + "\",");
                }
                if (entity.getCheckstate() != 0) {
                    strJson.append("\"checkstate\":" + entity.getCheckstate() + ",");
                }
                if (entity.getParentId() != null) {
                    strJson.append("\"parentnodes\":\"" + entity.getParentId() + "\",");
                }
                strJson.append("\"showcheck\":" + entity.getShowcheck().toString().toLowerCase() + ",");
                strJson.append("\"isexpand\":" + entity.getIsexpand().toString().toLowerCase() + ",");
                if (entity.getComplete() == true) {
                    strJson.append("\"complete\":" + entity.getComplete().toString().toLowerCase() + ",");
                }
                strJson.append("\"hasChildren\":" + entity.getHasChildren().toString().toLowerCase() + ",");
                strJson.append("\"ChildNodes\":" + TreeToJson(list, entity.getId()) + "");
                strJson.append("},");
            });
            strJson.deleteCharAt(strJson.length()-1);
        }
        strJson.append("]");
        return strJson.toString();
    }

    public static String TreeToJson2(List<TreeJsonDTO> list, String parentId) {
        final StringBuilder strJson = new StringBuilder();
        List<TreeJsonDTO> item = list.stream().filter(p -> p.getParentId().equals(parentId)).collect(Collectors.toList());
        strJson.append("[");
        if (item.size() > 0) {
            for (TreeJsonDTO entity : item){
                strJson.append("{");
                strJson.append("\"id\":\"" + entity.getId() + "\",");
                strJson.append("\"text\":\"" + StringExtend.ReplaceEmpty(entity.getText()) + "\",");
                strJson.append("\"value\":\"" + entity.getValue() + "\",");
                if (!StringExtend.Empty(entity.getAttribute())) {
                    strJson.append("\"" + entity.getAttribute() + "\":\"" + entity.getAttributeValue() + "\",");
                }
                if (!StringExtend.Empty(entity.getAttributeA())) {
                    strJson.append("\"" + entity.getAttributeA() + "\":\"" + entity.getAttributeValueA() + "\",");
                }
                if (!StringExtend.Empty(StringExtend.ReplaceEmpty(entity.getTitle()))) {
                    strJson.append("\"title\":\"" + StringExtend.ReplaceEmpty(entity.getTitle()) + "\",");
                }
                if (!StringExtend.Empty(StringExtend.ReplaceEmpty(entity.getImg()))) {
                    strJson.append("\"img\":\"" + StringExtend.ReplaceEmpty(entity.getImg()) + "\",");
                }
                if (entity.getCheckstate() != 0) {
                    strJson.append("\"checkstate\":" + entity.getCheckstate() + ",");
                }
                if (entity.getParentId() != null) {
                    strJson.append("\"parentnodes\":\"" + entity.getParentId() + "\",");
                }
                strJson.append("\"showcheck\":" + entity.getShowcheck().toString().toLowerCase() + ",");
                strJson.append("\"isexpand\":" + entity.getIsexpand().toString().toLowerCase() + ",");
                if (entity.getComplete() == true) {
                    strJson.append("\"complete\":" + entity.getComplete().toString().toLowerCase() + ",");
                }
                strJson.append("\"hasChildren\":" + entity.getHasChildren().toString().toLowerCase() + ",");
                strJson.append("\"ChildNodes\":" + TreeToJson(list, entity.getId()) + "");
                strJson.append("},");
            }
            strJson.deleteCharAt(strJson.length()-1);
        }
        strJson.append("]");
        return strJson.toString();
    }
}
