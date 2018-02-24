package sample;

import java.util.HashSet;
import java.util.Set;

public class GameField {
    private int sizeX;
    private int sizeY;
    private double centerSize;
    Set<Ball> balls;

    public GameField(int size, double centerSize) {
        this.sizeX = size;
        this.sizeY = size;
        this.centerSize = centerSize;
        this.balls = new HashSet<>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public double getCenterSize() {
        return centerSize;
    }

    public synchronized void addBall(Ball ball){
        balls.add(ball);
    }
    
    public synchronized boolean freePosition(Position position){
        boolean result = true;
        for (Ball ball: balls
             ) {
            if(ball.getPosition().equals(position)){
                result = false;
            }
        }
        return result;
    }

}
