def hill_climbing(game):
    current_state = game
    best_score = -math.inf
    best_move = None

    for move in current_state.get_valid_moves():
        game.make_move(move)
        score = evaluate_board(game)
        if score > best_score:
            best_score = score
            best_move = move
        game.undo_move(move)

    return best_move
