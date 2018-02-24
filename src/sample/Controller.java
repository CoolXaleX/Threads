package sample;

public class Controller {

    public void start(){
        GameField field = new GameField(COUNT_SECTORS, r);
        Thread player1 = new Thread(new Player(field,circle1,300));
        Thread player2 = new Thread(new Player(field,circle2,200));
        player1.setName("BLUE");
        player2.setName("RED");
        player1.start();
        player2.start();
    }
}
