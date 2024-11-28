class ConnectFour:
    def __init__(self, rows=6, cols=7, k=4):
        self.rows = rows
        self.cols = cols
        self.k = k  # Number of discs to connect to win
        self.board = [[0 for _ in range(cols)] for _ in range(rows)]
        self.current_player = 1

    def print_board(self):
        for row in self.board:
            print(' '.join(str(cell) for cell in row))
        print()

    def is_valid_move(self, col):
        return self.board[0][col] == 0

    def make_move(self, col):
        if not self.is_valid_move(col):
            return False
        for row in reversed(range(self.rows)):
            if self.board[row][col] == 0:
                self.board[row][col] = self.current_player
                break
        self.current_player = 3 - self.current_player  # Switch player
        return True

    def is_winner(self, player):
        for r in range(self.rows):
            for c in range(self.cols):
                if self.check_line(r, c, 1, 0, player) or \
                   self.check_line(r, c, 0, 1, player) or \
                   self.check_line(r, c, 1, 1, player) or \
                   self.check_line(r, c, 1, -1, player):
                    return True
        return False

    def check_line(self, r, c, dr, dc, player):
        count = 0
        for i in range(self.k):
            if 0 <= r + i * dr < self.rows and 0 <= c + i * dc < self.cols:
                if self.board[r + i * dr][c + i * dc] == player:
                    count += 1
                else:
                    break
            else:
                break
        return count == self.k

    def is_full(self):
        return all(self.board[0][c] != 0 for c in range(self.cols))

    def get_valid_moves(self):
        return [c for c in range(self.cols) if self.is_valid_move(c)]
