public class WinProbabilityHeuristic extends Heuristic {
    @Override
    public int evaluate(ConnectFour game, int player) {
        if (game.won()) {
            if (game.winner() == player) {
                return Integer.MAX_VALUE; // Winning state
            } else {
                return Integer.MIN_VALUE; // Losing state
            }
        }

        int[][] board = game.board();
        int score = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                // Check for potential wins
                if (board[row][col] == player) {
                    score += evaluatePotentialWin(board, row, col, player);
                } else if (board[row][col] == 3 - player) {
                    score -= evaluatePotentialWin(board, row, col, 3 - player);
                }
            }
        }
        return score;
    }

    private int evaluatePotentialWin(int[][] board, int row, int col, int player) {
        int score = 0;

        // Check all directions
        score += potentialInDirection(board, row, col, 0, 1, player); // Horizontal
        score += potentialInDirection(board, row, col, 1, 0, player); // Vertical
        score += potentialInDirection(board, row, col, 1, 1, player); // Diagonal (/)
        score += potentialInDirection(board, row, col, 1, -1, player); // Anti-diagonal (\)

        return score;
    }

    private int potentialInDirection(int[][] board, int row, int col, int deltaRow, int deltaCol, int player) {
        int potential = 0;
        int playerCount = 0;
        int emptyCount = 0;

        for (int i = 0; i < 4; i++) {
            int r = row + i * deltaRow;
            int c = col + i * deltaCol;

            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                if (board[r][c] == player) {
                    playerCount++;
                } else if (board[r][c] == 0) {
                    emptyCount++;
                } else {
                    return 0; // Opponent blocks the potential win
                }
            } else {
                return 0; // Out of bounds
            }
        }

        if (playerCount == 3 && emptyCount == 1) {
            potential += 100; // Almost winning
        } else if (playerCount == 2 && emptyCount == 2) {
            potential += 10; // Good potential
        }
        return potential;
    }
}
