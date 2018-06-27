package com.dalian.sea.mapper;

import com.dalian.sea.model.ZsCorporateNews;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ZsCorporateNewsMapper extends Mapper<ZsCorporateNews> {

    /***
     * 企业新闻显示数据
     * @param
     * @return
     */
    List<ZsCorporateNews> getCorporateNewsBy();

    /***
     * 新增
     * @param corporateNews
     * @return
     */
    Integer addCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 修改
     * @param corporateNews
     * @return
     */
    Integer updateCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 根据id查询信息
     * @param corporateNewsId
     * @return
     */
    ZsCorporateNews getCorporateNewsById(Long corporateNewsId);

    /***
     * 新增内容
     * @param
     * @return
     */
    Integer updateAddCorporateNews(ZsCorporateNews corporateNews);

    /***
     * 删除新闻
     * @param corporateNews
     * @return
     */
    Integer delectCorporateNews(ZsCorporateNews corporateNews);
}