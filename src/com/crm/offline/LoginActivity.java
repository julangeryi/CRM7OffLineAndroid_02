package com.crm.offline;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.base.utils.FileUtils;
import com.crm.constant.DataBaseConstant;
import com.crm.fragment.ContentFragment;
import com.crm.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;

import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

@ContentView(R.layout.activity_login)
public class LoginActivity extends SlidingFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(LoginActivity.this);

		setBehindContentView(R.layout.left_menu);
		dataBase();

		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(100);
		initFragment();
	}

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		// 开启事务
		FragmentTransaction ft = fm.beginTransaction();
		// 替换帧布局
		ft.replace(R.id.fl_left_menu, new LeftMenuFragment());
		ft.replace(R.id.fl_content, new ContentFragment());
		// 提交事务
		ft.commit();
	}

	// 关于数据库文件的处理
	private void dataBase() {
		String external = FileUtils.getExternalStoragePath();
		String dataFileName = external + DataBaseConstant.docLocation + DataBaseConstant.dbName;
		File f = new File(dataFileName);
		if (!f.exists()) {
			try {
				InputStream is = LoginActivity.this.getAssets().open(DataBaseConstant.dbName);
				FileUtils.createDirs(external + DataBaseConstant.docLocation);
				File destFileInfoDB3 = new File(
						external + DataBaseConstant.docLocation + "/" + DataBaseConstant.dbName);
				FileUtils.copyFile(is, destFileInfoDB3, false);
			} catch (IOException ie) {
				// TODO Auto-generated catch block
			}

		}

	}

}
