package sample;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Player implements Runnable {
    private Ball ball;
    private GameField field;
    private long timeToSleepMs;
    private Circle circle;

    public Player(GameField field, Circle circle, long timeToSleepMs) {
        this.field = field;
        this.timeToSleepMs = timeToSleepMs;
        this.circle = circle;
    }

    private void setBallIntoField() {
        int x, y;
        Position position;
        do {
            x = (int) Math.round(Math.random() * field.getSizeX());
            y = (int) Math.round(Math.random() * field.getSizeY());
            position = new Position(x,y);
        } while (!field.freePosition(position));
        //TODO добавить проверку на пустоту
        this.ball = new Ball(position,Thread.currentThread().getName());
        this.field.addBall(this.ball);
    }

    private void moveBall(Ball ball, GameField field){
        ArrayList<Position> positions = new ArrayList<>();
        Position curPos = ball.getPosition();
        //Left
        if (curPos.getX() - 1 >= 0){
            positions.add(new Position(curPos.getX() - 1,curPos.getY()));
        }
        //Right
        if (curPos.getX() + 1 < field.getSizeX()){
            positions.add(new Position(curPos.getX() + 1,curPos.getY()));
        }
        //UP
        if (curPos.getY() - 1 >= 0){
            positions.add(new Position(curPos.getX(),curPos.getY()- 1));
        }
        //DOWN
        if (curPos.getY() + 1 < field.getSizeY()){
            positions.add(new Position(curPos.getX(),curPos.getY() + 1));
        }

        for (int i = 0; i < positions.size(); i++) {
            if(!field.freePosition(positions.get(i))){
                positions.remove(i);
                --i;
            }
        }
        if (positions.size() > 0) {
            ball.move(positions.get((int) (Math.round(Math.random() * (positions.size() - 1)))));
            circle.setCenterX(field.getCenterSize() * 2 * (ball.getPosition().getX() + 1) - field.getCenterSize());
            circle.setCenterY(field.getCenterSize() * 2 * (ball.getPosition().getY() + 1) - field.getCenterSize());
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
