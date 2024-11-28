import random

def monte_carlo(board, simulations, game):
    valid_moves = game.get_valid_moves()
    move_scores = {move: 0 for move in valid_moves}

    for move in valid_moves:
        for _ in range(simulations):
            game_copy = ConnectFour(game.rows, game.cols, game.k)
            game_copy.board = [row[:] for row in game.board]
            game_copy.make_move(move)

            while not game_copy.is_winner(1) and not game_copy.is_winner(2) and not game_copy.is_full():
                random_move = random.choice(game_copy.get_valid_moves())
                game_copy.make_move(random_move)

            if game_copy.is_winner(1):
                move_scores[move] += 1
            elif game_copy.is_winner(2):
                move_scores[move] -= 1

    best_move = max(move_scores, key=move_scores.get)
    return best_move
