package com.ifunshow.crawl.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.xml.sax.InputSource;

import com.ifunshow.crawl.beans.CrawlLog;
import com.ifunshow.crawl.beans.CrawlTask;
import com.ifunshow.crawl.beans.CrawlTaskGroup;
import com.ifunshow.crawl.beans.DataColumnBean;
import com.ifunshow.crawl.beans.DataRowBean;
import com.ifunshow.crawl.beans.DataTableBean;
import com.ifunshow.crawl.beans.XmlConfigTemplate;
import com.ifunshow.crawl.constant.Constant;
import com.ifunshow.crawl.db.MyDbUtil;
import com.ifunshow.crawl.util.FilesUtil;
import com.ifunshow.crawl.util.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * <p>
 *    运行解析入库任务Job
 * </p>
 * 
 * @author yuyf
 */
public class RunResolveJob extends Thread {
    private static Logger _log = LoggerFactory.getLogger(RunResolveJob.class);
    
    private CrawlTaskGroup crawlGroup;
    private CrawlTask crawlTask;
    private XmlConfigTemplate crawlXml;
    private XmlConfigTemplate resolveXml;
    private String crawl_log_id;
    private Long jobStartTime;
    
    RunResolveJob(){}
    
    RunResolveJob(CrawlTaskGroup crawlGroup,CrawlTask crawlTask,XmlConfigTemplate crawlXml,XmlConfigTemplate resolveXml,String crawl_log_id,Long jobStartTime){
    	//_log.debug("初始化类");
    	this.crawlGroup = crawlGroup;
    	this.crawlTask = crawlTask;
    	this.crawlXml = crawlXml;
    	this.resolveXml = resolveXml;    	
    	this.crawl_log_id = crawl_log_id;
    	this.jobStartTime = jobStartTime;
    }
    
    public void run(){
    	//_log.info("取得抓取结果信息");
    	CrawlLog crawlLog = (CrawlLog)MyDbUtil.Open().get_one("select * from crawl_log where row_id = ?",new Object[]{this.crawl_log_id},CrawlLog.class);
    	
    	List list = FilesUtil.getFiles(crawlLog.getCrawl_file_path());
    	if(!list.isEmpty()){
        	Iterator _ite = list.iterator();
    		while(_ite.hasNext()){
    			String fileInfo[] = (String[])_ite.next();
    	    	long startTime = System.currentTimeMillis();//解析开始时间
    	    	String resolvexml = this.resolveXml.getXml_content();
    	    	resolvexml = resolvexml.replace("#{file_to_path}#",fileInfo[1]);
    	    	resolvexml = resolvexml.replace("#{filepath}#",fileInfo[0]);
    	    	
    	    	//_log.info("取得解析脚本");
    			Reader reader = new StringReader(resolvexml);
    			ScraperConfiguration config = new ScraperConfiguration(new InputSource(reader));
    			String fileWorkPath = Constant.RESOLVE_FILE_SAVE_ROOT+crawlTask.getCrawl_to_dir()+this.jobStartTime+"/";

    			//_log.info("初始化解析脚本环境");
    			Scraper scraper = new Scraper(config, fileWorkPath);
    			scraper.execute();//执行
    			long endTime = System.currentTimeMillis();//解析结束时间

    			//_log.info("加载文件目录下的xml文件为String");
    	    	String conciseXML = this.loadConciseXML(fileWorkPath+fileInfo[1]);

    	    	_log.info("插入解析结果到数据表");
    			String sql = "INSERT INTO result_record(row_id,log_id,task_id,resolve_file_path,resolve_record_xml,resolve_start_date,resolve_end_date,resolve_status,resolve_note,row_creator,row_create_date)VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    			String result_record_id = UUIDGenerator.getUUID();
    			Object[] params = new Object[]{result_record_id,this.crawl_log_id,this.crawlTask.getRow_id(),fileInfo[0],conciseXML,new Date(startTime),new Date(endTime),new Integer(1),"解析成功",crawlTask.getTask_name()+"-"+this.getClass().getName(),new Date()};
    			try {
    				MyDbUtil.Open().execute(sql, params);
    			} catch (Exception e) {
    				_log.info(e.getMessage());
    			}

    			//_log.info("将Xml转成java对象DataTableBean");
    			DataTableBean tableBean = (DataTableBean)this.resolveXMLToaJavObject(conciseXML);

    			//_log.info("执行对象固化入库");
    			startTime = System.currentTimeMillis();//同步开始时间
    			tableBean.saveToDb(this.crawlTask.getTask_name(),this.resolveXml.getResult_target_table(),result_record_id);
    			endTime = System.currentTimeMillis();//同步结束时间
    	    	
    			//_log.info("更新同步入库时间信息");
    			String update_sql = "update result_record set synchronize_start_date = ?,synchronize_end_date = ?,synchronize_status = ?,synchronize_note = ? where row_id = ?";
    			Object[] update_params = new Object[]{new Date(startTime),new Date(endTime),new Integer(1),"同步入库成功",result_record_id};
    			try {
    				MyDbUtil.Open().execute(update_sql, update_params);
    			} catch (Exception e) {
    				_log.info(e.getMessage());
    			}
    			_log.info("解析入库结束");    			
    		}
    	}
    }

    
    //加载简洁的XML为字符串
    public String loadConciseXML(String file_path){
    	String xml = "";
    	try {
			//_log.info("读进解析配置文件");  
			File file = new File(file_path);
			//_log.info("转字符串");  
			xml = FileUtils.readFileToString(file,"GBK");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return xml;
    }
    
    
    //将简洁的XML转变成java对象
    public Object resolveXMLToaJavObject(String xml){
    	XStream xstream = new XStream(new DomDriver());

    	//_log.info("注册bean别名");  
    	xstream.alias("table", DataTableBean.class);
    	xstream.alias("row", DataRowBean.class);
    	xstream.alias("column", DataColumnBean.class);
    	xstream.alias("rows", ArrayList.class);
    	xstream.alias("columns", ArrayList.class);
    	xstream.alias("key", String.class);
    	xstream.alias("value", String.class);

    	//_log.info("将xml转对象"); 
    	Object o = xstream.fromXML(xml);
    	return o;
    }
    
    

	public String getCrawl_log_id() {
		return crawl_log_id;
	}

	public void setCrawl_log_id(String crawlLogId) {
		this.crawl_log_id = crawlLogId;
	}

	public Long getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(Long jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public CrawlTaskGroup getCrawlGroup() {
		return crawlGroup;
	}

	public void setCrawlGroup(CrawlTaskGroup crawlGroup) {
		this.crawlGroup = crawlGroup;
	}

	public CrawlTask getCrawlTask() {
		return crawlTask;
	}

	public void setCrawlTask(CrawlTask crawlTask) {
		this.crawlTask = crawlTask;
	}

	public XmlConfigTemplate getCrawlXml() {
		return crawlXml;
	}

	public void setCrawlXml(XmlConfigTemplate crawlXml) {
		this.crawlXml = crawlXml;
	}

	public XmlConfigTemplate getResolveXml() {
		return resolveXml;
	}

	public void setResolveXml(XmlConfigTemplate resolveXml) {
		this.resolveXml = resolveXml;
	}
 }