import java.util.Random;

public class StupidPlayer extends AI{
    public int play(ConnectFour game, int depth){
        Random random = new Random();
        System.out.println("Mandarin");
        return game.legalMoves()[random.nextInt(game.legalMoves().length)];
    }
}
