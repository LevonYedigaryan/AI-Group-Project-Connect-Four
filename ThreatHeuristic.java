public class ThreatHeuristic extends Heuristic {
    public int evaluate(ConnectFour game, int player) {
        int[][] board = game.board();
        int score = 0;

        // Iterate through the board to detect lines
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == player) {
                    score += evaluateLine(board, row, col, player);
                } else if (board[row][col] == 3 - player) {
                    score -= evaluateLine(board, row, col, 3 - player);
                }
            }
        }
        return score;
    }

    private int evaluateLine(int[][] board, int row, int col, int player) {
        int score = 0;

        // Horizontal
        score += checkDirection(board, row, col, 0, 1, player);
        // Vertical
        score += checkDirection(board, row, col, 1, 0, player);
        // Diagonal (/)
        score += checkDirection(board, row, col, 1, 1, player);
        // Anti-diagonal (\)
        score += checkDirection(board, row, col, 1, -1, player);
        return score;
    }

    private int checkDirection(int[][] board, int row, int col, int deltaRow, int deltaCol, int player) {
        int count = 0;
        int consecutive = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * deltaRow;
            int c = col + i * deltaCol;
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                if (board[r][c] == player) {
                    consecutive++;
                } else if (board[r][c] != 0) {
                    return 0; // Blocked line
                }
            } else {
                return 0; // Out of bounds
            }
        }
        if (consecutive == 2) count += 10;
        if (consecutive == 3) count += 50;
        return count;
    }
}
