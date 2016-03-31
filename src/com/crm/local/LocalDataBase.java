package com.crm.local;

import com.base.utils.FileUtils;
import com.crm.constant.DataBaseConstant;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;

import android.content.Context;

public class LocalDataBase {
	
	
	private static DbUtils dbInstance;
	
	
	private LocalDataBase(Context context){
		String dataFileName = FileUtils.getExternalStoragePath() + DataBaseConstant.docLocation ;
		DaoConfig daoConfig = new DaoConfig(context);
		daoConfig.setDbDir(dataFileName);
		daoConfig.setDbName(DataBaseConstant.dbName);
		daoConfig.setDbVersion(1);
		dbInstance = DbUtils.create(daoConfig);
		
	}
	public static DbUtils getInstance(Context context){
		if(null == dbInstance){
			new LocalDataBase(context);
		}
		return dbInstance;
	}
	
}
