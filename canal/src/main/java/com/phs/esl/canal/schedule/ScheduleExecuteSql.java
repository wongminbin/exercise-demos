package com.phs.esl.canal.schedule;

import org.springframework.stereotype.Component;

import com.phs.esl.canal.core.ILogger;

/**
* @author HuangZhibin
* 
* 2018年10月26日 上午9:34:07
*/

@Component
public class ScheduleExecuteSql implements ILogger {
	
//	@Autowired
//	private IBaseService insertService;
//	
//	@Autowired
//	private IBaseService updateService;
//
//	private String[] sqls = {
//			"CREATE TABLE IF NOT EXISTS `t_user` (`id`  int(11) NOT NULL AUTO_INCREMENT ,`name`  varchar(255) NOT NULL ,`age`  smallint NOT NULL ,PRIMARY KEY (`id`))",
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI0', 28)", 
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI1', 28)", 
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI2', 28)", 
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI3', 28)", 
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI4', 28)", 
//			"INSERT INTO `t_user`(name, age) VALUES ('LISI5', 28)", 
//			"UPDATE `t_user` SET name = 'ZHANGSAN' WHERE id < 3 ",
//			"DELETE FROM `t_user` WHERE id = 5 ",
//			"ALTER TABLE `t_user` ADD COLUMN `address` varchar(255) NOT NULL DEFAULT ''",
//			"ALTER TABLE `t_user` ADD INDEX `name_index` (`name`)",
//	};
//	
//	@Scheduled(cron = "0/30 * * * * *")
//	public void schedule() throws Exception {
//		getLogger().info("start scheduled.......");
//		IBaseService service = null;
//		for (String sql : sqls) {
//			if (sql.startsWith("INSERT")) {
//				service = insertService;
//			} else {
//				service = updateService;
//			}
//			Optional.ofNullable(service).ifPresent(cons->cons.execute(sql));
//			Thread.sleep(1000);
//		}
//		
//	}
//	
//	@Autowired
//	private IBaseDao baseDao;
//	
//	@Scheduled(cron = "0/30 * * * * *")
//	public void schedule1() {
//		Map<String, Object> map = baseDao.selectCommondSql("SELECT * FROM em_mall WHERE pk_id = 1");
//		map.forEach((k, v) -> getLogger().info("k:{} , v:{}", k, v));
//	}
}
