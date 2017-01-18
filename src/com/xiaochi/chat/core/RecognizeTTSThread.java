package com.xiaochi.chat.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import com.xiaochi.chat.interfaces.ExternRunnable;

public class RecognizeTTSThread extends Thread {
	private Queue<ExternRunnable> datas = new PriorityBlockingQueue();

	public  void post(ExternRunnable r) {
		System.out.println("insert an runnable tast :");
		datas.offer(r);
	}

	@Override
	public void run() {
		while (true) {
			// TODO Auto-generated method stub
			ExternRunnable r = null;
			synchronized (this) {
				if (null != (r = datas.poll())) {
					try {
						String dots = "";
						System.out.println("������" + (dots += "��"));
						/*
						 * ��Ҫ��������ʶ����������Ϣ
						 * */
						r.run();
						System.out.println("run an runnable");
						
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("====input type changed===");
						System.out.println(" input type = normal ");
						e.printStackTrace();
					
						datas.clear();

					}
				} else {

					try {
						System.out.println("----no task---");
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
