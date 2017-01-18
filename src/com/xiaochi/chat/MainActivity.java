package com.xiaochi.chat;

import com.xiaochi.chat.core.ChatCore;
import com.xiaochi.database.DBManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

public class MainActivity extends Activity {

	Handler h  = new Handler(){
		public void handleMessage(android.os.Message msg) {
		
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DBManager db = new DBManager(this);
		SQLiteDatabase l = db.getReadableDatabase();
		ChatCore.main(null);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
