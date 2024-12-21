public class AlphaBeta extends AI{
    private Heuristic heuristic;

    public AlphaBeta(){
        this(new OurOwnHeuristic());
    }

    public AlphaBeta(Heuristic heuristic){
        this.heuristic=heuristic;
    }

    public int play(ConnectFour game, int depth) {
        ConnectFour newGame=game.clone();
        return alphabeta(newGame, depth, game.player(), Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
    }

    private int[] alphabeta(ConnectFour game, int depth, int player, int alpha, int beta){
        if(game.won() || depth<=0 || game.finished()){
            return new int[] {heuristic.evaluate(game, player), -1};
        }
        int bestMove=-1;
        if (game.player()==player) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < game.columns(); i++) {
                if (game.isLegal(i)) {
                    ConnectFour newGame = game.clone();
                    try{
                        newGame.play(i);
                    }
                    catch(Exception e){}
                    int eval = alphabeta(newGame, depth - 1, player, alpha, beta)[0];
                    if (eval > maxEval) {
                        maxEval = eval;
                        bestMove = i;
                    }
                    alpha = Math.max(alpha, eval);
                    if (alpha >= beta) break;
                }
            }
            return new int[] {maxEval, bestMove};
        }else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < game.columns(); i++) {
                if (game.isLegal(i)) {
                    ConnectFour newGame = game.clone();
                    try{
                        newGame.play(i);
                    }
                    catch(Exception e){}
                    int eval = alphabeta(newGame, depth - 1, player, alpha, beta)[0];
                    if (eval < minEval) {
                        minEval = eval;
                        bestMove = i;
                    }
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) break;
                }
            }
            return new int[] {minEval, bestMove};
        }
    }

    public String toString(){
        return "AlphaBeta";
    }
}