import java.util.Random;

public class MonteCarlo extends AI {
    private static int simulations;

    public MonteCarlo(){
        simulations=1000;
    }

    public int play(ConnectFour game, int depth) {
        simulations=depth;
        int player=game.player();
        int bestMove = Integer.MIN_VALUE;
        double bestWinRate = Integer.MIN_VALUE;
        int moves[]=game.legalMoves();
        for (int i = 0; i < moves.length; i++) {
                ConnectFour newGame = game.clone();
                try {
                    newGame.play(moves[i]);
                }
                catch(Exception e){}
                int winRate = simulateRandomGames(newGame, simulations, player);
                if (winRate > bestWinRate) {
                    bestWinRate = winRate;
                    bestMove = moves[i];
                }
        }
        if(!game.isLegal(bestMove)){
            System.out.println("Wumpus");
        }
        return bestMove;
    }

    public int play(ConnectFour game) {
        return play(game, simulations);
    }

    private int simulateRandomGames(ConnectFour game, int simulations, int player) {
        int wins = 0;
        for (int i = 0; i < simulations; i++) {
            ConnectFour simGame = game.clone();
            while (!simGame.won() && !simGame.finished()) {
                int move = randomMove(simGame);
                try {
                    simGame.play(move);
                }
                catch(Exception e){}
            }
            if (simGame.winner()==player) {
                wins++;
            }
        }
        return wins;
    }

    private int randomMove(ConnectFour game) {
        Random random = new Random();
        int move=game.legalMoves()[random.nextInt(game.legalMoves().length)];
        if(!game.isLegal(move)){
            System.out.println("Kanachi");
        }
        return move;
    }

    public String toString(){
        return "MonteCarlo";
    }
}
