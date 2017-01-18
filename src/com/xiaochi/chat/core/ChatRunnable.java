package com.xiaochi.chat.core;

import java.util.concurrent.Callable;

import com.xiaochi.chat.base.DataBase;

public class ChatRunnable extends AbsChatRunnable {

	public ChatRunnable(int flag, Class clz) {
		super(flag, clz);
		// TODO Auto-generated constructor stub
	}

	public ChatRunnable() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("==== ChatRunnable ===");
		System.out.println("=== run : chat = "
				+ DataBase.WORD_MEAN[getFlag()] + " ; clz = "
				+ getClz().getClass().getCanonicalName() + " ===");
		if (getCallback() != null)
			getCallback().run();
	}

	@Override
	public void run(int flag, Class clz) {

	}



}
