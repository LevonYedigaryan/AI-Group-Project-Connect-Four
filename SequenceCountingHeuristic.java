public class SequenceCountingHeuristic extends Heuristic {
    private static final int FOUR_IN_A_ROW = 1000;
    private static final int THREE_IN_A_ROW = 100;
    private static final int TWO_IN_A_ROW = 10;

    @Override
    public int evaluate(ConnectFour game, int player) {
        int[][] board = game.board();
        int score = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != 0) {
                    int currentPlayer = board[row][col];
                    int multiplier = currentPlayer == player ? 1 : -1;

                    // Evaluate all directions
                    score += multiplier * evaluateDirection(board, row, col, 0, 1); // Horizontal
                    score += multiplier * evaluateDirection(board, row, col, 1, 0); // Vertical
                    score += multiplier * evaluateDirection(board, row, col, 1, 1); // Diagonal /
                    score += multiplier * evaluateDirection(board, row, col, 1, -1); // Anti-diagonal \
                }
            }
        }

        return score;
    }

    private int evaluateDirection(int[][] board, int row, int col, int deltaRow, int deltaCol) {
        int count = 0, empty = 0;

        for (int i = 0; i < 4; i++) {
            int r = row + i * deltaRow;
            int c = col + i * deltaCol;

            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                if (board[r][c] == board[row][col]) {
                    count++;
                } else if (board[r][c] == 0) {
                    empty++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // Assign weights based on sequence length
        if (count == 4) return FOUR_IN_A_ROW; // Winning move
        if (count == 3 && empty > 0) return THREE_IN_A_ROW; // Threat potential
        if (count == 2 && empty > 1) return TWO_IN_A_ROW; // Small advantage

        return 0;
    }
}
