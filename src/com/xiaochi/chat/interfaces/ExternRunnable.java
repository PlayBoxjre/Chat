package com.xiaochi.chat.interfaces;

public interface ExternRunnable extends Runnable ,Comparable<ExternRunnable>{

	
	
	
	public void run(int flag);
	public void run(int flag,Class clz);

	public void set(int flag,Class clz);
	public int getFlag();
	public Class getClz();
	public void set(int i, Class clz,int flowBufferId, Runnable runnable);
	
	
	

}
