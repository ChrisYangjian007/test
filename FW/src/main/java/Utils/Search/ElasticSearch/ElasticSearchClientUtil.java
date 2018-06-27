//package Service.ElasticSearch;
//
//import Interface.ISearch;
//import org.elasticsearch.action.ActionRequestBuilder;
//import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.Requests;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.cluster.metadata.MappingMetaData;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHitField;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.sort.SortOrder;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;
//
///**
// * Created by Administrator on 2017-08-20.
// */
//public class ElasticSearchClientUtil implements ISearch {
//    private static Logger logger = Logger.getLogger(String.valueOf(ElasticSearchClientUtil.class));
//    private static TransportClient client = ClientFactory.getClient();
//
//    @Override
//    public <T> boolean add(T model) {
//        String indexName = SearchUtil.getIndexName(model);
//        String typeName = SearchUtil.getTypeName(model);
//        String json = SearchUtil.ModelToJson(model);
//        String id = SearchUtil.getidValue(model).toString();
//        IndexResponse response;
//        if (id == null)
//        {// 如果id为null,则不设置，es会自动生成
//            response = client.prepareIndex(indexName, typeName).setSource(json).execute().actionGet();
//        }
//        else
//        {
//            response = client.prepareIndex(indexName, typeName, id).setSource(json).execute().actionGet();
//        }
//        boolean bool = Integer.parseInt(response.getId()) >= 0;
//        logger.info("添加对象" + model + (bool ? "成功！" : "失败"));
//        return Integer.parseInt(response.getId()) >= 0;
//    }
//
//    @Override
//    public <T> boolean add(List<T> models) {
//        boolean bool = false;
//        if (models.size() <= 1) {
//            logger.warning("插入数据集合为空！");
//        } else {
//            String indexName = SearchUtil.getIndexName(models.get(0));
//            String typeName = SearchUtil.getTypeName((models.get(0)));
//            BulkRequestBuilder bulkRequest = client.prepareBulk();
//            // either use client#prepare, or use Requests# to directly build index/delete requests
//            for (T model : models) {
//                try {
//                    String json = SearchUtil.ModelToJson(model);
//                    String id = SearchUtil.getidValue(model).toString();
//                    if (id == null) {// 如果id为null,则不设置，es会自动生成
//                        bulkRequest.add(client.prepareIndex(indexName, typeName).setSource(json));
//                    } else {
//                        bulkRequest.add(client.prepareIndex(indexName, typeName, id).setSource(json));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    continue;
//                }
//            }
//            BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//            if (!bulkResponse.hasFailures()) {
//                // process failures by iterating through each bulk response item
//                bool = true;
//            }
//        }
//        logger.info("添加对象列表：" + models + (bool ? " 成功！" : " 失败"));
//        return bool;
//    }
//
//    @Override
//    public <T> T get(Class<T> clazz, String id) {
//        T result = null;
//        try {
//            String indexName = SearchUtil.getIndexName(clazz);
//            String typeName = SearchUtil.getTypeName((clazz));
//            GetResponse response = client.prepareGet(indexName, typeName, id).execute().actionGet();
//            Map<String, Object> map = response.getSource();
//            if (map != null) {
//                // map.put("id",id);//or map.put("id",response.getId());
//                map.put(SearchUtil.getidName(clazz), id);
//            }
//            result = SearchUtil.MapToModel(map, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        logger.info("根据id:" + id + ",获取对象：" + result);
//        return result;
//    }
//
//    @Override
//    public boolean delete(String id, Class clazz) {
//        DeleteResponse response = client.prepareDelete(SearchUtil.getIndexName(clazz), SearchUtil.getTypeName(clazz), id).execute().actionGet();
//        boolean bool = !response.getType().equals("");
//        logger.info("根据id:" + id + ",删除对象" + (bool ? "成功！" : "失败,请检查对象是否存在"));
//        return bool;
//    }
//
//    @Override
//    public boolean createIndex(String indexName) {
//        boolean bool = client.admin().indices().prepareCreate(indexName).execute().actionGet().isAcknowledged();
//        if (bool) {
//            logger.info("使用默认配置创建索引\"" + indexName + "\"成功！");
//        } else {
//            throw new RuntimeException("创建索引\"" + indexName + "\"失败！");
//        }
//        return bool;
//    }
//
//    @Override
//    public boolean createIndex(Class clazz) {
//        Settings indexSettings = Settings.builder()
//                .put("number_of_shards", SearchUtil.getShards(clazz))
//                .put("number_of_replicas", SearchUtil.getReplicas(clazz))
//                .build();
//        CreateIndexRequest indexRequest = new CreateIndexRequest(SearchUtil.getIndexName(clazz), indexSettings);
//        boolean bool = client.admin().indices().create(indexRequest).actionGet().isAcknowledged();
//        String indexName = SearchUtil.getIndexName(clazz);
//        if (bool) {
//            logger.info("创建索引\"" + indexName + "\"成功");
//        } else {
//            // LOG.warn("创建索引:" + indexName + "失败！");
//            throw new RuntimeException("创建索引\"" + indexName + "\"失败");
//        }
//        return bool;
//    }
//
//    @Override
//    public boolean createIndexWithSettings(Class clazz) {
//        String indexName = SearchUtil.getIndexName(clazz);
//        String settings = SearchUtil.getSettings(clazz);
//        // 如果settings为null(settings.yml文件不存在)，则使用带
//        // 有number_of_shards和number_of_replicas配置的创建索引
//        if (settings == null) {
//            createIndex(clazz);
//        } else {
//            logger.info("settings内容：" + settings);
//            // 通过web操作完成
//            // String response = WebUtil.httpExecute(RequestMethodType.PUT, Constant.BASE_URL + indexName, settings);
//            // JSONObject responseJson = JSON.parseObject(response);
//            // boolean bool = Boolean.parseBoolean(responseJson.get("acknowledged").toString());
//            boolean bool = client.admin().indices().prepareCreate(indexName).setSettings(settings).execute().actionGet().isAcknowledged();
//            if (bool) {
//                logger.info("使用settings创建索引\"" + indexName + "\"成功！");
//            } else {
//                // LOG.warn("创建索引:" + indexName + "失败！");
//                throw new RuntimeException("创建索引\"" + indexName + "\"失败,可能的问题：您的settings配置不正确！");
//            }
//            return bool;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteIndex(String indexName) {
//        try {
//            boolean bool = client.admin().indices().prepareDelete(indexName).execute().actionGet().isAcknowledged();
//            logger.info("删除索引\"" + (bool ? indexName + "\"成功" : "失败"));
//            return bool;
//        } catch (Exception e) {
//            logger.warning("没有要删除的索引\"" + indexName + "\"");
//            return false;
//        }
//    }
//
//    @Override
//    public boolean deleteType(String indexName, String typeName) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteType(Class<?> clazz) {
//        return deleteType(SearchUtil.getIndexName(clazz), SearchUtil.getTypeName(clazz));
//    }
//
//    @Override
//    public boolean hasIndex(String indexName) {
//        boolean indexExists = client.admin().indices().prepareExists(indexName).execute().actionGet().isExists();
//        logger.info("存在性检测====>索引\"" + indexName + (indexExists ? "\"存在" : "\"不存在"));
//        return indexExists;
//    }
//    /**
//     * 解析查询语句，并调用查询接口
//     * @param params 符合Solr查询语法的查询语句（为了兼容现有业务的查询接口以及方便查询，按照solr的查询语法格式做了一层包装， 用户的查询语句只要符合solr的语法要求均可在此进行查询操作。）
//     *            eg：q=name:'东方财富'&q=tgsType:2&sort=name+desc,key+asc&start=10&rows=5&fl=name,age,sex,key&hl=true
//     *            &hl.fl=name,logid&hl.simple.pre=<em>&hl.simple.post=</em> 详情请参考solr的查询语法 注：多个字段间以英文逗号（,）分隔，eg:
//     *            fl=name,age / sort=name+desc,key+asc
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    @Override
//    public <T> List<T> search(Class<T> clazz, String params) {
//        // 调用参数解析工具
//        Map<String, Object> paramsMap = SearchUtil.getParamsMap(params);
//        String searchParams = (String)paramsMap.get("searchParams");// 查询参数
//        String filterParams = (String)paramsMap.get("filterParams");// 过滤参数
//        int start = (Integer)paramsMap.get("start");// 开始位置
//        int rows = (Integer)paramsMap.get("rows");// 每页显示记录条数
//        List<String> showFields = (List<String>)paramsMap.get("showFields");// 要显示的字段
//        Map<String, SortOrder> sortFields = (HashMap<String, SortOrder>)paramsMap.get("sortFields");// 排序字段及规则
//        boolean showHighLight = (Boolean)paramsMap.get("showHighLight");// 是否高亮显示
//        List<String> HighLightFields = (List<String>)paramsMap.get("HighLightFields");// 高亮显示的字段
//        String HighLightPreTag = (String)paramsMap.get("HighLightPreTag");// 高亮显示的前置标记
//        String HighLightPostTag = (String)paramsMap.get("HighLightPostTag");// 高亮显示的后置标记
//        return search(clazz,
//                searchParams,
//                filterParams,
//                start,
//                rows,
//                showFields,
//                sortFields,
//                showHighLight,
//                HighLightFields,
//                HighLightPreTag,
//                HighLightPostTag);
//    }
//    /**
//     * 查询接口，包括普通查询和高亮查询
//     * @param clazz: 要查询实体类对应的Class对象,比如MGlobal.class
//     * @param searchParams: 符合Lucene规则的查询语句，比如：name:'东方财富' AND typeCode:4
//     * @param filterParams: 符合Lucene规则的查询语句
//     * @param start: 查询结果显示的开始位置
//     * @param rows: 查询结果显示的记录条数
//     * @param showFields 如果showFields为null或者size为0,则默认显示全部字段
//     * @param sortFields 如果sortFields为null或者size为0,则默认不排序
//     * @param showHighLight 是否进行高亮设置（高亮显示）
//     * @param HighLightFields 高亮显示的字段
//     * @param HighLightPreTag 高亮显示的前缀
//     * @param HighLightPostTag 高亮显示的后缀
//     * @return 返回查询结果实体类的List集合
//     */
//    public <T> List<T> search(Class<T> clazz, String searchParams, String filterParams, int start, int rows,List<String> showFields, Map<String, SortOrder> sortFields, boolean showHighLight, List<String> HighLightFields, String HighLightPreTag, String HighLightPostTag) {
//        List<T> list = new ArrayList<T>();
//        String indexName = SearchUtil.getIndexName(clazz);
//        String typeName = SearchUtil.getTypeName((clazz));
//        // 是否显示指定字段
//        boolean isSpecifiedShowFields = !(showFields == null || showFields.size() == 0);
//        // 是否按照指定字段进行排序
//        boolean isSortByFields = !(sortFields == null || sortFields.keySet().size() == 0);
//        // 是否进行高亮显示设置
//        boolean isShowHighLight = showHighLight;
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName);
//        searchRequestBuilder.setTypes(typeName);
//        // 设置是否按查询匹配度排序
//        searchRequestBuilder.setExplain(true);
//        // 设置query
//        QueryStringQueryBuilder queryBuilder = new QueryStringQueryBuilder(searchParams);
//        searchRequestBuilder.setQuery(queryBuilder);
//        // 设置filter
//        // if(filterParams != null){
//        // QueryStringQueryBuilder queryFilterBuilder = new QueryStringQueryBuilder(filterParams);
//        // QueryBuilder filter = QueryBuilders.
//        // searchRequestBuilder.setPostFilter(filter);
//        // }
//        // 设置开始位置及大小
//        searchRequestBuilder.setFrom(start).setSize(rows);
//        // 设置显示的字段
//        if (showFields != null) {
//            for (String field : showFields) {
//                searchRequestBuilder.addStoredField(field);
//            }
//        }
//        // 设置排序字段
//        String sortInfo = "[";
//        if (isSortByFields) {
//            for (String field : sortFields.keySet()) {
//                sortInfo += field + ":" + sortFields.get(field).toString() + ",";
//                searchRequestBuilder.addSort(field, sortFields.get(field));
//            }
//            sortInfo = sortInfo.substring(0, sortInfo.length() - 1) + "]";
//        }
//        // 设置高亮显示
//        if (isShowHighLight) {
//            HighlightBuilder highlightBuilder = null;
//
//            for (String field : HighLightFields) {
//                highlightBuilder = new HighlightBuilder().field(field);
//                highlightBuilder.preTags("<span style=\"color:red\">");
//                highlightBuilder.postTags("</span>");
//            }
//            searchRequestBuilder.highlighter(highlightBuilder);
//        }
//
//        // 执行查询操作
//        SearchResponse response = searchRequestBuilder.execute().actionGet();
//        // 处理查询结果
//        SearchHits hits = response.getHits();
//        long total = hits.getTotalHits();// 记录数量
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        for (SearchHit hit : hits.getHits()) {
//            if (!isSpecifiedShowFields) {// 如果不是显示指定字段
//                resultMap = hit.getSource();// 如果设置显示指定字段的话，hit.getSource()为null
//            } else {// 如果是显示指定字段
//                Map<String, SearchHitField> map = hit.fields();
//                // 将指定显示的字段放入map
//                for (String field : map.keySet()) {
//                    String value = map.get(field).getValue().toString();
//                    resultMap.put(field, value);
//                }
//                // OR for(String field : showFields){resultMap.put(field, hit.field(field).getValue().toString());}
//            }
//            // resultMap.put("id", hit.getId());
//            resultMap.put(SearchUtil.getidName(clazz), hit.getId());
//            T model = null;
//            if (resultMap != null) {
//                model = SearchUtil.MapToModel(resultMap, clazz);
//            }
//
//            if (isShowHighLight) {
//                // 获取对应的高亮域
//                Map<String, HighlightField> result = hit.highlightFields();
//                // 从设定的高亮域中取得指定域
//                for (String field : result.keySet()) {
//                    HighlightField HLField = result.get(field);
//                    String fieldName = HLField.getName();
//                    Text[] texts = HLField.fragments();
//                    String fieldNewValue = "";
//                    for (Text text : texts) {
//                        fieldNewValue += text;
//                    }
//                    // 通过反射将高亮字段对应的高亮内容设置进去
//                    String firstLetter = fieldName.substring(0, 1).toUpperCase();
//                    String methodName = "set" + firstLetter + fieldName.substring(1);
//                    try {
//                        Method method = clazz.getDeclaredMethod(methodName, String.class);
//                        method.invoke(model, fieldNewValue);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    // System.out.println(fieldName + " : " + fieldNewValue + "==========");
//                }
//            }
//            list.add(model);
//        }
////        StringBuilder info = new StringBuilder();
////        info.append("查询类型：" + (isShowHighLight ? "高亮查询" : "普通查询"));
////        info.append(",index：").append(indexName);
////        info.append(",type：").append(typeName);
////        info.append(",查询语句：").append(searchParams);
////        info.append(filterParams != null ? ",过滤语句：" + filterParams : "");
////        info.append(",开始位置：").append(start);
////        info.append(",显示行数：").append(rows);
////        info.append(isSpecifiedShowFields ? "，显示的指定字段为：" + showFields : "，显示全部字段");
////        info.append(isSortByFields ? "，排序规则：" + sortInfo : "，按照默认规则排序");
////        info.append(isShowHighLight ? ",高亮显示的字段为：" + HighLightFields + ",高亮显示前缀：" + HighLightPreTag + ",高亮显示后缀：" + HighLightPostTag : "");
////        logger.info(list.size() == 0 ? info + "，本次无查询结果！" : info + "，此次查询共有" + total + "条记录");
//        return list;
//    }
//
//    @Override
//    public long count(Class clazz, String params) {
//        return 0;
//    }
//
//    @Override
//    public boolean delete(Class clazz, String params) {
//        return false;
//    }
//
//    @Override
//    public boolean createMapping(Class clazz) {
//        try {
//            String indexName = SearchUtil.getIndexName(clazz);
//            String typeName = SearchUtil.getTypeName((clazz));
//            PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(typeName).source(SearchUtil.getMapping(clazz));
//            client.admin().indices().putMapping(mappingRequest).actionGet();
//            logger.info("创建mapping\"" + typeName + "\"成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean hasMapping(Class clazz) {
//        String indexName = SearchUtil.getIndexName(clazz);
//        String typeName = SearchUtil.getTypeName((clazz));
//        return hasMapping(indexName, typeName);
//    }
//
//    /* 非接口实现类，生成SearchRequestBuilder对象并做一些通用设置 */
//    public SearchRequestBuilder getSRB(Class clazz)
//    {
//        String indexName = SearchUtil.getIndexName(clazz);
//        String typeName = SearchUtil.getTypeName((clazz));
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(indexName);
//        searchRequestBuilder.setTypes(typeName);
//        return searchRequestBuilder;
//    }
//
//    // public ActionRequestBuilderList<Factory<? extends A>> ls
//    public ActionRequestBuilder getRequestBuilder(Class<? extends ActionRequestBuilder> requestClazz, Class modelClazz)
//    {
//        String indexName = SearchUtil.getIndexName(modelClazz);
//        String typeName = SearchUtil.getTypeName((modelClazz));
//        return client.prepareSearch(indexName).setTypes(typeName);
//    }
//
//    @Override
//    public boolean hasMapping(String indexName, String typeName) {
//        ClusterStateResponse clusterStateResponse = client.admin().cluster().prepareState().execute().actionGet();
//        MappingMetaData mappingMetaData = clusterStateResponse.getState().getMetaData().index(indexName).getMappings().get(typeName);
//        // ImmutableMap<String,MappingMetaData> indexMappings =
//        // clusterStateResponse.getState().getMetaData().index(indexName).getMappings();
//        boolean mappingExists = mappingMetaData != null ? true : false;
//        logger.info("存在性检测====>" + (mappingExists ? "mapping \"" + indexName + "/" + typeName + "\"存在" : "mapping：" + indexName + "/" + typeName + "不存在！"));
//        return mappingExists;
//    }
//}
