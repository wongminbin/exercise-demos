package com.phs.esl.canal.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author HuangZhibin
* 
* 2018年10月25日 下午8:17:41
*/
public interface IBaseDao {

	/**
	 * <pre>查询语句</pre>
	 * @param sql
	 * @return
	 */
	@Select("${value}")
	Map<String, Object> selectCommondSql(String sql);
	
	/**
	 *  <pre>查询语句</pre>
	 * @param sql
	 */
	@Select("${SELECT_SQL}")
	Map<String, Object> selectKeyValue(Map<String, Object> values);
	
	
	/**
	 *  <pre>插入语句</pre>
	 * @param sql
	 */
	@Insert("${value}")
	void insertCommondSql(String sql);
	
	/**
	 *  <pre>插入语句</pre>
	 * @param sql
	 */
	@Insert("${INSERT_SQL}")
	void insertKeyValue(Map<String, Object> values);
	
	/**
	 * <pre>更新语句</pre>
	 * @param sql
	 */
	@Update("${value}")
	void updateCommondSql(String sql);
	
	/**
	 * <pre>更新语句</pre>
	 * @param sql
	 */
	@Update("${UPDATE_SQL}")
	void updateKeyValue(Map<String, Object> values);
	
	/**
	 * <pre> 删除语句</pre>
	 * @param sql
	 */
	void deleteCommondSql(String sql);
}
