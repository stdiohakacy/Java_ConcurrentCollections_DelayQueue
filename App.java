package _14;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {
	private long duration;
	private String message;

	public DelayedWorker(long duration, String message) {
		super();
		this.duration = System.currentTimeMillis() + duration;
		this.message = message;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int compareTo(Delayed other) {
		if (this.duration < ((DelayedWorker) other).getDuration()) {
			return -1;
		}

		if (this.duration > ((DelayedWorker) other).getDuration()) {
			return +1;
		}

		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return "DelayedWorker [message=" + message + "]";
	}

}

public class App {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		try {
			queue.put(new DelayedWorker(1000, "message 1"));
			queue.put(new DelayedWorker(10000, "message 2"));
			queue.put(new DelayedWorker(4000, "message 3"));
		} catch (Exception e) {
		}

		while (!queue.isEmpty()) {
			System.out.println(queue.take());
		}
	}
}
