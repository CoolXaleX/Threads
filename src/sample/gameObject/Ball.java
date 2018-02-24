package sample.gameObject;

import sample.utils.Position;

public class Ball {
    private Position position;
    private String name;

    public Ball(Position position, String name) {
        this.position = position;
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position){
        this.position = position;
        System.out.println(this.position + " " + this.name);
    }
}
