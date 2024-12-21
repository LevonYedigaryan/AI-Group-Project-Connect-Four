public class PositionalHeuristic extends Heuristic {
    @Override
    public int evaluate(ConnectFour game, int player) {
        int[][] board = game.board();
        int score = 0;
        int[] columnWeights = {3, 4, 5, 7, 5, 4, 3};
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == player) {
                    score += columnWeights[col];
                } else if (board[row][col] == 3 - player) {
                    score -= columnWeights[col];
                }
            }
        }
        return score;
    }
}
