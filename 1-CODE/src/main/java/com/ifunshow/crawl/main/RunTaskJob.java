package com.ifunshow.crawl.main;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.xml.sax.InputSource;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ifunshow.crawl.beans.CrawlTask;
import com.ifunshow.crawl.beans.CrawlTaskGroup;
import com.ifunshow.crawl.beans.XmlConfigTemplate;
import com.ifunshow.crawl.constant.Constant;
import com.ifunshow.crawl.db.MyDbUtil;
import com.ifunshow.crawl.util.UUIDGenerator;

/**
 * <p>
 *    运行任务Job
 * </p>
 * 
 * @author yuyf
 */
public class RunTaskJob implements Job {

    private static Logger _log = LoggerFactory.getLogger(RunTaskJob.class);

    /**
     *   空构造函数（ 必须）
     */
    public RunTaskJob() {
    }

    /**
     * <p>
	 *   执行具体任务
     * </p>
     * 
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
    	
    	//_log.info("获取上下文参数");
    	CrawlTaskGroup crawlGroup= (CrawlTaskGroup)jobDataMap.get("CrawlTaskGroupInstance");
    	//_log.info("获取任务组任务列表");
    	List list = MyDbUtil.Open().query("select * from crawl_task where row_status = 1 and group_id = ?",new Object[]{crawlGroup.getRow_id()},CrawlTask.class);
		if(!list.isEmpty()){
            Iterator _ite = list.iterator();
            HashMap triggerMaps = new HashMap();

        	//_log.info("循环执行任务");
            while(_ite.hasNext()){
            	CrawlTask crawlTask = (CrawlTask)_ite.next();
            	
            	//_log.info("任务组："+crawlGroup.getGroup_name() +" -> 任务："+ crawlTask.getTask_name());
                
                XmlConfigTemplate crawlXml = (XmlConfigTemplate)MyDbUtil.Open().get_one("SELECT * FROM xml_config_template WHERE row_status = 1 AND row_id = ?", new Object[]{crawlTask.getCrawl_xml_id()}, XmlConfigTemplate.class);
                XmlConfigTemplate resolveXml = (XmlConfigTemplate)MyDbUtil.Open().get_one("SELECT * FROM xml_config_template WHERE row_status = 1 AND row_id = ?", new Object[]{crawlTask.getResolve_xml_id()}, XmlConfigTemplate.class);
        		
                long startTime = System.currentTimeMillis();//抓取开始时间
        		
        		_log.info(startTime +"->【"+ crawlGroup.getGroup_name()+"-->"+crawlTask.getTask_name() +"】开始");
        		
        		//_log.info("读取数据库存储的抓取脚本");
            	String crawlXmlContent = crawlXml.getXml_content();
            	crawlXmlContent = crawlXmlContent.replace("#{target_url}#",crawlTask.getTarget_url());
        		Reader reader = new StringReader(crawlXmlContent);
        		ScraperConfiguration config = new ScraperConfiguration(new InputSource(reader));
        		
        		//_log.info("抓取文件存放目录");
        		String fileToSavePath = Constant.CRAWL_FILE_SAVE_ROOT + crawlTask.getCrawl_to_dir() + startTime + "/";
        		
        		//_log.info("设置初始化抓取环境");
        		Scraper scraper = new Scraper(config, fileToSavePath);
        		
        		//_log.info("执行抓取");
        		scraper.execute();
        		long endTime = System.currentTimeMillis();//抓取结束时间
        		_log.info(endTime +"->【"+ crawlGroup.getGroup_name()+"-->"+crawlTask.getTask_name() +"】结束");
        		
        		//_log.info("抓取成功后执行插入");
        		String sql = "INSERT INTO crawl_log(row_id,task_id,crawl_file_path,crawl_start_date,crawl_end_date,crawl_note,crawl_status,row_create_date,row_creator)VALUES (?,?,?,?,?,?,?,?,?)";
        		
        		//_log.info("UUID产生主键");
        		String crawl_log_id = UUIDGenerator.getUUID();
        		
        		//_log.info("填充插入参数");
        		Object[] params = new Object[]{crawl_log_id,crawlTask.getRow_id(),fileToSavePath,new Date(startTime),new Date(endTime),"成功抓取",new Integer(2),new Date(),crawlTask.getTask_name()+"-"+this.getClass().getName()};
        		try {
        			MyDbUtil.Open().execute(sql, params);
        		} catch (Exception e) {
        			//e.printStackTrace();
        			_log.info(e.getMessage());
        		}
        		
        		//_log.info("起个线程做接下来的解析和入库");
        		new RunResolveJob(crawlGroup,crawlTask,crawlXml,resolveXml,crawl_log_id,startTime).start();
        		
        		_log.info(crawlGroup.getGroup_name()+"任务结束");

            }        	
        }
    }

}
