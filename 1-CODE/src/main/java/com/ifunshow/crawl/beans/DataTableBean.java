package com.ifunshow.crawl.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.ifunshow.crawl.db.MyDbUtil;
import com.ifunshow.crawl.util.UUIDGenerator;

public class DataTableBean {
	private ArrayList<DataRowBean> rows;
	
	public int saveToDb(String task_name,String targetTable,String result_record_id){
		int row_cnt = 0;
		if(this.rows != null && !this.rows.isEmpty()){
			String sql = "Insert into " + targetTable + "(row_id,result_record_id,{fields},row_creator,row_create_date)values(?,?,{values},?,?)";
			String fields = "";
			String values = "";
			Iterator<DataRowBean> _r_ite = this.rows.iterator();
			while(_r_ite.hasNext()){
				row_cnt++;
				DataRowBean drb = _r_ite.next();
				if(drb.getColumns() != null && !drb.getColumns().isEmpty()){
					Iterator<DataColumnBean> _c_ite = drb.getColumns().iterator();
					Object[] params = new Object[(drb.getColumns().size()+4)];
					params[0] = UUIDGenerator.getUUID();
					params[1] = result_record_id;
					int column_cnt = 2;
					while(_c_ite.hasNext()){
						DataColumnBean dcb = _c_ite.next();
						if("".equals(fields)){
							fields += dcb.getKey();							
						}else{
							fields += ","+ dcb.getKey();
						}
						if("".equals(values)){
							values += "?";							
						}else{
							values += ","+ "?";
						}
						params[column_cnt] = dcb.getValue();
						column_cnt++;
					}
					sql = sql.replace("{fields}", fields);
					sql = sql.replace("{values}", values);
					params[column_cnt] = task_name+"-"+this.getClass().getName();
					params[column_cnt+1] = new Date();
					sql = sql.replace("{values}", values);
					try {
						MyDbUtil.Open().execute(sql,params);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			System.out.println("����ݣ�");
		}
		return row_cnt;
	}

	public ArrayList<DataRowBean> getRows() {
		return rows;
	}

	public void setRows(ArrayList<DataRowBean> rows) {
		this.rows = rows;
	}
}
