package com.xiaochi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
	private static int VERSION = 1;
	private static String DB_NAME = "Chat_db";
	private static String DEEP_MEAN_TABLE = "deep_mean_table";
	private static String FLOW_BUFFER_TABLE = "flow_buffer_table";
	private static String WORD_TAG = "word_tag_table";

	/**
	 * @return the vERSION
	 */
	public static int getVERSION() {
		return VERSION;
	}

	/**
	 * @return the dB_NAME
	 */
	public static String getDB_NAME() {
		return DB_NAME;
	}

	/**
	 * @return the dEEP_MEAN_TABLE
	 */
	public static String getDEEP_MEAN_TABLE() {
		return DEEP_MEAN_TABLE;
	}

	/**
	 * @return the fLOW_BUFFER_TABLE
	 */
	public static String getFLOW_BUFFER_TABLE() {
		return FLOW_BUFFER_TABLE;
	}

	/**
	 * @return the wORD_TAG
	 */
	public static String getWORD_TAG() {
		return WORD_TAG;
	}

	/**
	 * @return the deep_struct
	 */
	public String getDeep_struct() {
		return deep_struct;
	}

	/**
	 * @return the flow_buffer
	 */
	public String getFlow_buffer() {
		return flow_buffer;
	}

	/**
	 * @return the word_tag
	 */
	public String getWord_tag() {
		return word_tag;
	}
	
	
	/**
	 * @return the dEEP_CLOUMN_ARR
	 */
	public static String[] getDEEP_CLOUMN_ARR() {
		return DEEP_CLOUMN_ARR;
	}

	/**
	 * @return the fLOW_BUFFER_ARR
	 */
	public static String[] getFLOW_BUFFER_ARR() {
		return FLOW_BUFFER_ARR;
	}

	/**
	 * @return the wORD_ARR
	 */
	public static String[] getWORD_ARR() {
		return WORD_ARR;
	}


	private static String[] DEEP_CLOUMN_ARR = {"_id","SCENEID","ACTIONID","MUSTIN","DEFSET"};
	private static String[] FLOW_BUFFER_ARR = {"_id","SCENEID","ACTIONID","MUSTIN","DEFSET","HASIN","WORDSTAGIN"};
	private static String[] WORD_ARR = {"_id","FLOWBUFFERID","OSUBJ","PRED","ACCU","OCOM","TIME","LOCA","IF"};

	private String deep_struct = "create table "
			+ DEEP_MEAN_TABLE
			+ " (_id integer primary key autoincrement,SCENEID integer,ACTIONID integer,MUSTIN text,DEFSET integer );";
	private String flow_buffer = "create table "
			+ FLOW_BUFFER_TABLE
			+ " (_id integer primary key ,SCENEID integer,ACTIONID integer ,MUSTIN text,HASIN text,DEFSET integer ,WORDSTAGID integer);";
	private String word_tag = "create table "
			+ WORD_TAG
			+ " (_id integer,FLOWBUFFERID integer ,OSUBJ,PRED,ACCU,OCOM,TIME,LOCA,IF);";

	public DBManager(Context context) {
		this(context, DB_NAME, null, VERSION);
	}

	public DBManager(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(deep_struct);
		db.execSQL(flow_buffer);
		db.execSQL(word_tag);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if(oldVersion<newVersion){
			db.execSQL("drop table "+ DEEP_MEAN_TABLE);
			db.execSQL("drop table "+ FLOW_BUFFER_TABLE);
			db.execSQL("drop table "+ WORD_TAG);
			onCreate(db);
		}
	}

}
