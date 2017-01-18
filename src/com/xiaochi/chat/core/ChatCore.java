package com.xiaochi.chat.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.xiaochi.chat.exception.NotInputMustSquenceException;
import com.xiaochi.chat.interfaces.ExternRunnable;

/**
 * 
 * 
 * 
 * manage and duel an action at some scene how to normal chat with people.
 * intelligence chat like person to person.
 * <p>
 * <b>If the sentence is not complete, can prompt us to complete the
 * sentence</b>
 * </p>
 * how to ues this class . : such as
 * 
 * <pre>
 * ChatCore instance = ChatCore.getCore();
 * instance.setBufferId(bufferId);
 * instance.setMustSquence(BYTES);
 * instance.setCallback(Callback);
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
public class ChatCore {

	public static final int NORMAL = 0x01;
	public static final int INPUT_AGAIN = 0x02;
	public static final int OUTPUT_AGAIN = 0x03;

	private static ChatCore _instance;
	private byte[] mMustSquence;
	private int bufferItemId;
	
	// private ExecutorService es = new
	// ThreadPoolExecutor((Runtime.getRuntime().availableProcessors()+1),128,60L,TimeUnit.SECONDS,
	// null);
	private RecognizeTTSThread regonizerTTs;
	
	
	
	private ChatCore() {
	}

	private ChatCore(byte[] in) {
		setMustSquence(in);
		
	}

	public void setMustSquence(byte[] in) {
		this.mMustSquence = in;
		
	}
	public void setBufferId(int bufferId){
		this.bufferItemId = bufferId;
	}
	
	public void setThread(RecognizeTTSThread t) {
		this.regonizerTTs = t;
	}

	/**
	 * init & create ChatCore instance this is a singleton pattern.
	 * 
	 * @param in
	 * @return
	 */
	public synchronized static ChatCore getCore() {
		if (_instance == null)
			_instance = new ChatCore();
		return _instance;
	}

	private void checkSquenceExist() throws NotInputMustSquenceException {
		if (mMustSquence == null || regonizerTTs == null)
			throw new NotInputMustSquenceException(
					"you not specify a byte[] or thread  to limit it ,please specify it  ");
	}

	public void startRun(Runnable runnable)
			throws NotInputMustSquenceException {
		startRun(null, runnable);
	}

	public void startRun(byte[] in) throws NotInputMustSquenceException {
		startRun(in, null);
	}

	public void startRun(byte[] in, Runnable runnable)
			throws NotInputMustSquenceException {
		if (in != null)
			setMustSquence(in);
		checkSquenceExist();
		run(runnable);
	}

	private void run(Runnable runnable) {
		final int length = this.mMustSquence.length;
		for (int i = 0; i < length; i++) {
			if (this.mMustSquence[i] == 0) {
				Class clz = ChatCore.class;
				ExternRunnable er = new ChatRunnable();
				er.set(i, clz,this.bufferItemId,runnable);
				regonizerTTs.post(er);
			
			}
		}

	}

	private void setInputType(int inputAgain) {
		// TODO Auto-generated method stub
		System.out.println("=====print input type =====");
		System.out.println("=====inputAgain=====");
	}
	
	public void fillOkAtSpecifyWord(int index){
		mMustSquence[index] = 1;
	}

	public static void main(String[] args){
		RecognizeTTSThread thr = new RecognizeTTSThread();
		thr.start();
		byte[] b = new byte[]{0,0,1,1,1,1,0};
		ChatCore initCore = ChatCore.getCore();
		initCore.setMustSquence(b);
		initCore.setThread(thr);
		Runnable r = new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("client want to duel something ----");
			}
		};
		try {
			initCore.startRun(r);
		} catch (NotInputMustSquenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
