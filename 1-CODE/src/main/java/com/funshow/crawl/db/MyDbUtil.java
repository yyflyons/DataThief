package com.funshow.crawl.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.funshow.crawl.beans.CrawlTask;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class MyDbUtil {
    private volatile static MyDbUtil faction = null;
    private static BoneCP connectionPool = null;

    private MyDbUtil() {
    	
    }
    //打开

    public static MyDbUtil Open() {
        while (faction == null) {
            synchronized(MyDbUtil.class){
                 if (faction == null) {
                     faction = new MyDbUtil();
                 }
            }
        }
        return faction;
    }
    //关闭连接

    public void Close(Connection conn) {
        DbUtils.closeQuietly(conn);
        //ShutdownPool();
    }
    /**   
     * 启动连接池
     * @throws Exception   
     */    
    public void StartPool() {   
        try {
            String dri = "com.mysql.jdbc.Driver";
            Class.forName(dri);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (connectionPool != null) {    
            ShutdownPool();    
        }     
        try {
         	BoneCPConfig config = new BoneCPConfig();	// create a new configuration object
         	config.setJdbcUrl("jdbc:mysql://localhost:3306/crawle?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gbk");	// set the JDBC url
        	config.setUsername("root");			// set the username
        	config.setPassword("yyflyons");		// set the password
            connectionPool = new BoneCP(config);               
            System.out.println("装配连接池OK");    
        } catch (Exception e) {    
            e.printStackTrace();   
        }    
    }
    /**   
     * 释放连接池
     */    
    public static void ShutdownPool() {    
        connectionPool.shutdown();    
    }    
   
    // 获得数据库连�?

    public synchronized Connection getConnection() {
        Connection conn = null;
        try {
            if(connectionPool == null){
                StartPool();            	
            }
            conn = connectionPool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    //执行查询方法(使用map)
    public List query(String sql) {
        List results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (List) qr.query(conn, sql, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    
    //执行查询方法(使用map)
    public List query(String sql, Object param) {
        List results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (List) qr.query(conn, sql, param, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    
    //执行查询方法(使用)
    public List query(String sql, Class module) {
        List results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (List) qr.query(conn, sql, new BeanListHandler(module));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    //执行查询方法(使用)
    public List query(String sql,Object[] params, Class module) {
        List results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (List) qr.query(conn, sql, params, new BeanListHandler(module));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    //查询单个数据
    public Object get_one(String sql, Class module) {
        Object results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (Object) qr.query(conn, sql, new BeanHandler(module));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    
    //查询单个数据
    public Object get_one(String sql,Object[] params ,Class module) {
        Object results = null;
        Connection conn = null;
        try {
            conn = getConnection();
            QueryRunner qr = new QueryRunner();
            results = (Object) qr.query(conn, sql, params , new BeanHandler(module));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close(conn);
        }
        return results;
    }
    
    
    
    //增删改方�?
    public int execute(String sql) throws Exception {
        Connection conn = getConnection();
        int rows = 0;
        try {
            QueryRunner qr = new QueryRunner();
            rows = qr.update(conn, sql);
        } finally {
            Close(conn);
        }
        return rows;
    }
    
    //增删改方法
    public int execute(String sql, Object[] params) throws Exception {
        Connection conn = getConnection();
        int rows = 0;
        try {
            QueryRunner qr = new QueryRunner();
            rows = qr.update(conn, sql, params);
        } finally {
            Close(conn);
        }
        return rows;
    }
    
    
    public static void main(String[] args) {
    	List list = MyDbUtil.Open().query("select * from crawl_task",CrawlTask.class);
    	System.out.println("list"+list.size());
    }
}