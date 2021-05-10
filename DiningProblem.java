import java.util.Random;

public class DiningProblem {
	public static final int NUM_OF_PHILOSOPHERS = 5;
	public static final int MAX_ALLOWED_WAIT_COUNT = 100;
	public static volatile Random rnd = new Random(System.currentTimeMillis());
	
	private static Philosopher[] philosophers = new Philosopher[NUM_OF_PHILOSOPHERS];
	private static volatile Fork[] forks = new Fork[NUM_OF_PHILOSOPHERS];

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++) {
			forks[i] = new Fork(i);
		}
		for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i);
		}
		
		for (Philosopher phil : philosophers) {
			phil.start();
		}
		
		while(true) {
			Thread.sleep(30_000);
			boolean hasEveryoneEaten = true;
			for (Philosopher philosopher : philosophers) {
				if (philosopher.eatCount == 0) {
					System.out.println("Philosopher " + philosopher.id + " has not eaten yet");
					hasEveryoneEaten = false;
				}
			}
			if (hasEveryoneEaten) {
				System.out.println("----- CONGRATULATIONS! ALL PHILOSOPHERS HAVE EATEN AT LEAST ONCE! -----");
			}
		}
	}

	
	static class Philosopher extends Thread {
		private int id;
		private State state;
		private int eatCount = 0;
		private volatile Fork leftFork, rightFork;
		private boolean hasLeftFork = false, hasRightFork = false;
		
		Philosopher(int id) {
			this.id = id;
			leftFork = forks[id];
			if (id == 0)
				rightFork = forks[NUM_OF_PHILOSOPHERS-1];
			else
				rightFork = forks[id-1];
		}

		@Override
		public void run() {
			try {
				while (true) {
					state = State.THINKING;
					System.out.println("Philosopher " + id + " is now in " + state.name() + " state");
					sleep(random(20_000));
					
					state = State.HUNGRY;
					System.out.println("Philosopher " + id + " is now in " + state.name() + " state");
					
					while(true) {
						while (true) {
							if (leftFork.grab(this)) {
								hasLeftFork = true;
								break;
							} else
								sleep(random(1000));
						}
						
						if (rightFork.grab(this)) {
							hasRightFork = true;
						} else {
							System.out.println("Philosopher " + id + " has left fork but right fork is not available."
									+ " Releasing left fork...");
							leftFork.release(this);
							hasLeftFork = false;
						}
						
						if (hasLeftFork && hasRightFork)
							break;
						else {
							sleep(random(1000));			// wait for some time before trying to grab again
							continue;
						}
					}
					
					state = State.EATING;
					System.out.println("EATING -- Philosopher " + id + " is now in " + state.name() + " state");
					eatCount++;
					sleep(random(1000));

					System.out.println("Philosopher " + id + " has eaten. Returning to Thinking state...");
					leftFork.release(this);
					hasLeftFork = false;
					rightFork.release(this);
					hasRightFork = false;
					state = State.THINKING;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public enum State {
			THINKING, HUNGRY, EATING;
		}
	}

	
	static class Fork {
		private int id;
		private volatile boolean isFree = true;

		Fork(int id) {
			this.id = id;
		}

		public synchronized boolean grab(Philosopher phil) {
			if (!isFree) {
				System.out.println("Fork " + id + " is not free. Philosopher " + phil.id + " is waiting...");
				return false;
			} else {
				isFree = false;
				System.out.println("Fork " + id + " is free. Now being grabbed by Philosopher " + phil.id + "...");
				return true;
			}
		}

		public synchronized void release(Philosopher phil) {
			isFree = true;
			System.out.println("Fork " + id + " has been released free by Philosopher " + phil.id);
		}
	}
	
	public static int random(int median) {
		int min = median - (int) (median*0.1);
		int max = median + (int) (median*0.1);
		return (min + rnd.nextInt(max - min));
	}
}