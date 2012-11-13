/**
 * 主触发器
 * 
 */

package com.ifunshow.crawl.main;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.ifunshow.crawl.beans.CrawlTaskGroup;
import com.ifunshow.crawl.beans.QuartzCornTrigger;
import com.ifunshow.crawl.db.MyDbUtil;

/**
 * 
 * @author yuyf
 */
public class TaskTrigger {
    Logger _log = LoggerFactory.getLogger(TaskTrigger.class);	
    public void run() throws Exception {
        Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
        //查询取得任务组
        List g_list = MyDbUtil.Open().query("select * from crawl_task_group where row_status = 1",CrawlTaskGroup.class);
        if(!g_list.isEmpty()){
            _log.info("任务组个数："+g_list.size());            
            Iterator _g_ite = g_list.iterator();           
            while(_g_ite.hasNext()){
            	CrawlTaskGroup current_group = (CrawlTaskGroup)_g_ite.next();
            	//_log.info("查询取得trigger信息");
                QuartzCornTrigger qct = (QuartzCornTrigger) MyDbUtil.Open().get_one("select * from quartz_cron_trigger where row_id = ?",new Object[]{current_group.getTrigger_id()},QuartzCornTrigger.class);
                //_log.info("设置触发器");
                CronTrigger trigger = new CronTrigger(current_group.getRow_id(),Scheduler.DEFAULT_GROUP,qct.getCronExpression());
                //_log.info("设置JOB");
            	JobDetail jobDetail = new JobDetail(current_group.getRow_id(),Scheduler.DEFAULT_GROUP, RunTaskJob.class);
            	//_log.info("设置关联信息");
            	jobDetail.getJobDataMap().put("CrawlTaskGroupInstance", current_group); 
            	_log.info("增加【"+current_group.getRow_id()+"->"+current_group.getGroup_name()+"】任务组到调度进程");
            	sched.scheduleJob(jobDetail, trigger); 
			}
            _log.info("启动计划进度");
            sched.start();
        }
    }
    
    public static void main(String[] args) throws Exception {
    	TaskTrigger example = new TaskTrigger();
        example.run();
    }
}
