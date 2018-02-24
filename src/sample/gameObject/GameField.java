package sample.gameObject;

import sample.utils.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class GameField {
    private int sizeX;
    private int sizeY;
    private final Set<Ball> balls;
    private ReentrantLock lock;

    public GameField(int size, ReentrantLock lock) {
        this.sizeX = size;
        this.sizeY = size;
        this.balls = new HashSet<>();
        this.lock = lock;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void addBall(Ball ball) {
        synchronized (this.balls) {
            balls.add(ball);
        }
    }

    public boolean notFreePosition(Position position) {
        boolean result = true;
        synchronized (this.balls) {
            try {
                lock.lock();
                for (Ball ball : balls
                        ) {
                    if (ball.getPosition().equals(position)) {
                        result = false;
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return !result;
    }

    public Set<Ball> getBalls() {
        synchronized (this.balls) {
            return this.balls;
        }
    }

}
