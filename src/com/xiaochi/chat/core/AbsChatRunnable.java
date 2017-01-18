package com.xiaochi.chat.core;

import com.xiaochi.chat.interfaces.ExternRunnable;

public abstract class AbsChatRunnable implements ExternRunnable{

	private int flag ;//需要填入的标记号（word_tag);
	private Class clz;//
	private int flowBufferId;
	
	
	private Runnable callback;
	public AbsChatRunnable(int flag,Class clz){
		this.flag = flag;
		this.clz = clz;
	}
	
	public AbsChatRunnable(){}
	public void setFlag(int flag){
		this.flag = flag;
	}
	public void setClass(Class clz){
		this.clz = clz;
	}
	

	@Override
	public int compareTo(ExternRunnable o) {
		// TODO Auto-generated method stub
		
		return 1;
	}

	@Override
	public void set(int flag, Class clz) {
		// TODO Auto-generated method stub
		this.flag = flag;
		this.clz=clz;
	}
	
	@Override
	public void set(int i, Class clz, int flowBufferId,Runnable runnable) {
		// TODO Auto-generated method stub
	this.flag = i;
	this.clz = clz;
	this.callback = runnable;
	this.flowBufferId = flowBufferId;
	}
	public void setCallback(Runnable callback) {
		this.callback = callback;
	}
	public Runnable getCallback() {
		return callback;
	}
	@Override
	public int getFlag() {
		// TODO Auto-generated method stub
		return this.flag;
	}

	@Override
	public Class getClz() {
		// TODO Auto-generated method stub
		return this.clz;
	}


}
