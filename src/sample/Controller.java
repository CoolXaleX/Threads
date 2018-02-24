package sample;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Controller {

    private GameField gameField;
    private ReentrantLock lock;
    private int COUNT_THREADS;

    public Controller(int COUNT_SECTORS, int COUNT_THREADS) {
        this.lock = new ReentrantLock();
        this.gameField = new GameField(COUNT_SECTORS, this.lock);
        this.COUNT_THREADS = COUNT_THREADS;
    }

    public void start() {
        Thread player;
        for (int i = 0; i < this.COUNT_THREADS; i++) {
            player = new Thread(new Player(this.gameField, 300, this.lock));
            player.setName("player" + (i + 1));
            player.start();
        }
    }

    public ArrayList<Position> getPositions() {
        Set<Ball> set = gameField.getBalls();
        ArrayList<Position> list = new ArrayList<>();
        try {
            this.lock.lock();
            for (Ball ball : set
                    ) {
                list.add(ball.getPosition());
            }
        }finally {
            this.lock.unlock();
        }
        return list;
    }
}
