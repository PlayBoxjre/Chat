package com.xiaochi.chat.base;

import com.xiaochi.database.DBManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBase {
	public static String[] WORD_MEAN = { "主语", "谓语", "宾语", "宾部", "时间", "地点",
			"条件" };
	private SQLiteDatabase mDb;

	public DataBase(SQLiteDatabase sqLiteDatabase) {
		this.mDb = sqLiteDatabase;
	}

	public Cursor deepSearch(int SceneId, int actionId) {
		return mDb.query(DBManager.getDEEP_MEAN_TABLE(),
				DBManager.getDEEP_CLOUMN_ARR(), "SCENEID = ? AND ACTIONID = ?",
				new String[] { SceneId + "", actionId + "" }, null, null, null);
	}

	/**
	 * 查询深层表全表
	 * 
	 * @return
	 */
	public Cursor getDeepAllItem() {
		return mDb.query(DBManager.getDEEP_MEAN_TABLE(),
				DBManager.getDEEP_CLOUMN_ARR(), null, null, null, null, null);
	}

	/**
	 * 查询浅层表 缓存表全表
	 */
	public Cursor getBufferAllItem() {
		return mDb.query(DBManager.getFLOW_BUFFER_TABLE(),
				DBManager.getFLOW_BUFFER_ARR(), null, null, null, null, null);
	}

	/**
	 * 获取深层item id
	 * 
	 * @param sceneId
	 * @param actionid
	 * @return
	 */
	public int getDeepItemId(int sceneId, int actionid) {
		Cursor c = mDb.query(DBManager.getDEEP_MEAN_TABLE(),
				DBManager.getDEEP_CLOUMN_ARR(), "SCENEID = ? AND ACTIONID = ?",
				new String[] { sceneId + "", actionid + "" }, null, null, null);
		int _id = -1;
		if (c != null && c.getCount() != 0) {
			c.moveToFirst();
			_id = c.getInt(c.getColumnIndex("_id"));
			Log.i("kxx", "getBufferId = " + _id);
		}
		return _id;
	}

	/**
	 * 获取buffer的item id
	 * 
	 * @param sceneId
	 * @param actionid
	 * @return
	 */
	public int getBufferItemId(int sceneId, int actionid) {
		Cursor c = mDb.query(DBManager.getFLOW_BUFFER_TABLE(),
				DBManager.getFLOW_BUFFER_ARR(), "SCENEID = ? AND ACTIONID = ?",
				new String[] { sceneId + "", actionid + "" }, null, null, null);
		int _id = -1;
		if (c != null && c.getCount() != 0) {
			c.moveToFirst();
			_id = c.getInt(c.getColumnIndex("_id"));
			Log.i("kxx", "getBufferId = " + _id);
		}
		return _id;
	}

	/**
	 * 从deep中查找到的cursor 插入到 buffer中
	 * 
	 * @param deepQuery
	 * @return
	 */
	public int pollDeepItemToBufferItem(Cursor deepQuery) {
		ContentValues cv = new ContentValues();
		boolean ok = checkCursor(deepQuery);
		if (ok) {
			toContentValues(deepQuery, cv);
			return (int) mDb.insert(DBManager.getFLOW_BUFFER_TABLE(), null, cv);
		} else {
			return -1;
		}
	}

	/**
	 * 检查cursor是否为空
	 * 
	 * @param deepQuery
	 * @return
	 */
	private boolean checkCursor(Cursor deepQuery) {
		// TODO Auto-generated method stub
		return deepQuery != null && deepQuery.getCount() != 0;
	}

	/**
	 * 将deep的cursor 的想写到cv中
	 * 
	 * @param deepQuery
	 * @param cv
	 */
	private void toContentValues(Cursor deepQuery, ContentValues cv) {
		// TODO Auto-generated method stub
		int count = deepQuery.getCount();
		String[] columnNames = deepQuery.getColumnNames();
		final int length = columnNames.length;
		int i = 0, j = 0;
		deepQuery.moveToFirst();
		for (int k = 0; k < length; k++) {
			cv.put(columnNames[k], deepQuery.getString(deepQuery
					.getColumnIndex(columnNames[k])));
		}
	}

	/**
	 * 修改指定id的buffer item的数据
	 * @param id
	 * @param cv
	 * @param WhereClause
	 * @param whereArgs
	 * @return
	 */
	public int updateBufferById(int id, ContentValues cv, String WhereClause,
			String[] whereArgs) {

		return mDb.update(DBManager.getFLOW_BUFFER_TABLE(), cv, WhereClause,
				whereArgs);

	}
}
