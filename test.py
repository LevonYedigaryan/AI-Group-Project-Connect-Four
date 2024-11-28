game = ConnectFour()
game.print_board()

# Minimax usage
print("Minimax selected column:", minimax(game.board, 4, -math.inf, math.inf, True, game))

# Monte Carlo usage
print("Monte Carlo selected column:", monte_carlo(game.board, 100, game))

# Hill Climbing usage
print("Hill Climbing selected column:", hill_climbing(game))
