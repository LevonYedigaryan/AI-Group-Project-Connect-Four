
public class HillClimbing extends AI{
    Heuristic heuristic;

    public HillClimbing(){
        this(new OurOwnHeuristic());
    }

    public HillClimbing(Heuristic heuristic){
        this.heuristic=heuristic;
    }

    public int play(ConnectFour game, int depth){
        ConnectFour newGame=game.clone();
        return play(newGame, depth, game.player());
    }
    
    public int play(ConnectFour game, int depth, int player) {
        if(depth<=0 || game.finished()){
            return -1;
        }
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < game.columns(); i++) {
            if (game.isLegal(i)) {
                ConnectFour newGame = game.clone();
                try{
                    newGame.play(i);
                }
                catch(Exception e){}
                play(newGame, depth-1, game.next());
                if(player==1 && i==2){
                    System.out.println("Varduhi");
                }
                int score=heuristic.evaluate(newGame, player);
                if (score > bestScore){
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        try{
            game.play(bestMove);
        }
        catch(Exception e){}
        return bestMove;
    }

    public String toString(){
        return "HillClimbing";
    }
}   