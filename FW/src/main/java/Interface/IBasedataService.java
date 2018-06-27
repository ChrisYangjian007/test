package Interface;

import Models.BaseData;

import java.util.List;
import java.util.Map;

/**
 * 基础数据业务逻辑
 * @author John
 */
public interface IBasedataService {

	/**
	 * 保存基础数据
	 * 
	 * @param baseData
	 */
	void add(BaseData baseData);

	/**
	 * 获得基础数据
	 * 
	 * @param id
	 * @return
	 */
	BaseData get(String id);

	/**
	 * 编辑基础数据
	 * 
	 * @param baseData
	 */
	void edit(BaseData baseData);

	/**
	 * 删除基础数据
	 * 
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 根据类型取基础数据
	 * @param baseData
	 * @return
	 */
	List<BaseData> getBaseDatas(BaseData baseData);
	
	/**
	 * 根据动态sql查找结果
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List<Map> getSelectMapList(String sql, Map params);
	
	/**
	 * 获取系统环境变量
	 */
	Map<String,BaseData> getAppVariable();
	
}
