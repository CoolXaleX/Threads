package sample.gameObject;

import sample.utils.Position;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Player implements Runnable {
    private Ball ball;
    private GameField field;
    private long timeToSleepMs;
    private ReentrantLock lock;

    public Player(GameField field, long timeToSleepMs, ReentrantLock lock) {
        this.field = field;
        this.timeToSleepMs = timeToSleepMs;
        this.lock = lock;
    }

    private void setBallIntoField() {
        int x, y;
        Position position;
        do {
            x = (int) Math.round(Math.random() * (field.getSizeX() - 1));
            y = (int) Math.round(Math.random() * (field.getSizeY() - 1));
            position = new Position(x, y);
        } while (field.notFreePosition(position));
        this.ball = new Ball(position, Thread.currentThread().getName());
        this.field.addBall(this.ball);
    }

    private void moveBall(Ball ball, GameField field) {
        ArrayList<Position> positions = new ArrayList<>();
        try {
            lock.lock();
            Position curPos = ball.getPosition();
            //Left
            if (curPos.getX() - 1 >= 0) {
                positions.add(new Position(curPos.getX() - 1, curPos.getY()));
            }
            //Right
            if (curPos.getX() + 1 < field.getSizeX()) {
                positions.add(new Position(curPos.getX() + 1, curPos.getY()));
            }
            //UP
            if (curPos.getY() - 1 >= 0) {
                positions.add(new Position(curPos.getX(), curPos.getY() - 1));
            }
            //DOWN
            if (curPos.getY() + 1 < field.getSizeY()) {
                positions.add(new Position(curPos.getX(), curPos.getY() + 1));
            }

            for (int i = 0; i < positions.size(); i++) {
                if (field.notFreePosition(positions.get(i))) {
                    positions.remove(i);
                    --i;
                }
            }

            if (positions.size() > 0) {
                ball.move(positions.get((int) (Math.round(Math.random() * (positions.size() - 1)))));
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            this.setBallIntoField();
            while (true) {
                Thread.sleep(this.timeToSleepMs);
                this.moveBall(this.ball, this.field);
                // make move
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
