import math

def minimax(board, depth, alpha, beta, maximizing_player, game):
    if depth == 0 or game.is_winner(1) or game.is_winner(2) or game.is_full():
        return evaluate_board(game)

    valid_moves = game.get_valid_moves()

    if maximizing_player:
        max_eval = -math.inf
        for col in valid_moves:
            game.make_move(col)
            eval = minimax(board, depth - 1, alpha, beta, False, game)
            max_eval = max(max_eval, eval)
            alpha = max(alpha, eval)
            game.undo_move(col)
            if beta <= alpha:
                break
        return max_eval
    else:
        min_eval = math.inf
        for col in valid_moves:
            game.make_move(col)
            eval = minimax(board, depth - 1, alpha, beta, True, game)
            min_eval = min(min_eval, eval)
            beta = min(beta, eval)
            game.undo_move(col)
            if beta <= alpha:
                break
        return min_eval

def evaluate_board(game):
    # Simple heuristic: More advanced heuristics can use weighted positional scores
    if game.is_winner(1):
        return 10000
    elif game.is_winner(2):
        return -10000
    else:
        return 0
