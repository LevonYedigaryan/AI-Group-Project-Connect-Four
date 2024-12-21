import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectFourGUI {
    private static JFrame setupFrame, playerFrame, gameFrame;
    private static int width = 7, height = 6, goalState = 4, numPlayers = 2;
    private static String[] playerTypes;
    private static int[] playerDepths;
    private static ConnectFour game;
    private static boolean gameOver = false;
    private static AI[] players;
    private static JLabel turnLabel;
    private static StringBuilder gameHistory = new StringBuilder();
    private static Color[] playerColors;
    private static Color[] predefinedColors = {
        Color.RED,      // Player 1
        Color.YELLOW,   // Player 2
        Color.GREEN,    // Player 3
        Color.CYAN,     // Player 4
        Color.MAGENTA   // Player 5
    };

    public static void main(String[] args) {
        setupWindow();
    }

    private static void setupWindow() {
        setupFrame = new JFrame("Connect Four Setup");
        setupFrame.setSize(400, 400);
        setupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupFrame.setLayout(new GridLayout(5, 2));

        JLabel widthLabel = new JLabel("Board Width: ");
        JTextField widthField = new JTextField("7");
        setupFrame.add(widthLabel);
        setupFrame.add(widthField);

        JLabel heightLabel = new JLabel("Board Height: ");
        JTextField heightField = new JTextField("6");
        setupFrame.add(heightLabel);
        setupFrame.add(heightField);

        JLabel goalLabel = new JLabel("Goal State (number of connected discs): ");
        JTextField goalField = new JTextField("4");
        setupFrame.add(goalLabel);
        setupFrame.add(goalField);

        JLabel playersLabel = new JLabel("Number of Players (2-5): ");
        JTextField playersField = new JTextField("2");
        setupFrame.add(playersLabel);
        setupFrame.add(playersField);

        JButton startButton = new JButton("Next: Set Players");
        setupFrame.add(new JLabel());
        setupFrame.add(startButton);

        startButton.addActionListener(e -> {
            width = Integer.parseInt(widthField.getText());
            height = Integer.parseInt(heightField.getText());
            goalState = Integer.parseInt(goalField.getText());
            numPlayers = Integer.parseInt(playersField.getText());

            if (numPlayers >= 2 && numPlayers <= 5) {
                setupFrame.setVisible(false);
                playerSetupWindow();
            } else {
                JOptionPane.showMessageDialog(setupFrame, "Please enter a valid number of players (2-5).");
            }
        });

        setupFrame.setVisible(true);
    }

    private static void playerSetupWindow() {
        playerFrame = new JFrame("Connect Four - Set Players");
        playerFrame.setSize(400, 400);
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerFrame.setLayout(new GridLayout(numPlayers + 1, 3));

        playerTypes = new String[numPlayers];
        playerDepths = new int[numPlayers];
        players = new AI[numPlayers];

        JLabel[] labels = new JLabel[numPlayers];
        JComboBox<String>[] playerTypeComboBoxes = new JComboBox[numPlayers];
        JTextField[] depthFields = new JTextField[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            labels[i] = new JLabel("Player " + (i + 1) + " Type: ");
            playerTypeComboBoxes[i] = new JComboBox<>(new String[]{"Human", "AlphaBeta", "MonteCarlo", "HillClimbing", "Stupid"});
            playerTypeComboBoxes[i].setSelectedIndex(0);
            playerTypes[i] = (String) playerTypeComboBoxes[i].getSelectedItem();

            final int index = i;
            playerTypeComboBoxes[i].addActionListener(e -> playerTypes[index] = (String) playerTypeComboBoxes[index].getSelectedItem());

            depthFields[i] = new JTextField("0");
            playerDepths[i] = Integer.parseInt(depthFields[i].getText());

            final JTextField finalDepthField = depthFields[i];
            depthFields[i].addActionListener(e -> {
                try {
                    playerDepths[index] = Integer.parseInt(finalDepthField.getText());
                } catch (NumberFormatException exception) {
                    playerDepths[index] = 0;
                }
            });

            playerFrame.add(labels[i]);
            playerFrame.add(playerTypeComboBoxes[i]);
            playerFrame.add(depthFields[i]);
        }

        JButton startGameButton = new JButton("Start Game");
        playerFrame.add(new JLabel());
        playerFrame.add(new JLabel());
        playerFrame.add(startGameButton);

        startGameButton.addActionListener(e -> {
            for (int i = 0; i < numPlayers; i++) {
                try {
                    playerDepths[i] = Integer.parseInt(depthFields[i].getText());
                } catch (NumberFormatException exception) {
                    playerDepths[i] = 0;
                }
            }

            for (int i = 0; i < numPlayers; i++) {
                if (playerTypes[i].equals("Human")) {
                    players[i] = null;
                } else {
                    AI playerAI = null;
                    switch (playerTypes[i]) {
                        case "AlphaBeta" -> playerAI = new AlphaBeta();
                        case "MonteCarlo" -> playerAI = new MonteCarlo();
                        case "HillClimbing" -> playerAI = new HillClimbing();
                        case "Stupid" -> playerAI = new StupidPlayer();
                    }
                    players[i] = playerAI;
                }
            }

            playerFrame.setVisible(false);
            startGameWindow();
        });

        playerFrame.setVisible(true);
    }

    private static void startGameWindow() {
        game = new ConnectFour(height, width, goalState, numPlayers);
        playerColors = generateUniqueColors(numPlayers);
        gameFrame = new JFrame("Connect Four Game");
        gameFrame.setSize(width * 100, height * 100 + 150);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(null);

        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, width * 100, height * 100);
        gameFrame.add(gamePanel);

        turnLabel = new JLabel("Player 1's Turn");
        turnLabel.setBounds(width * 100 / 2 - 50, height * 100 + 10, 200, 40);
        gameFrame.add(turnLabel);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setBounds(width * 100 / 2 - 50, height * 100 + 50, 100, 40);
        newGameButton.addActionListener(e -> {
            gameOver = false;
            game.reset();
            gameFrame.setVisible(false);
            setupWindow();
        });
        gameFrame.add(newGameButton);

        JButton historyButton = new JButton("Game History");
        historyButton.setBounds(width * 100 / 2 + 60, height * 100 + 50, 150, 40);
        historyButton.addActionListener(e -> showGameHistory());
        gameFrame.add(historyButton);

        gameFrame.setVisible(true);
        gameLoop();
    }

    private static Color[] generateUniqueColors(int numPlayers) {
        Color[] colors = new Color[numPlayers];
        
        // Ensure we don't exceed the predefined colors available
        if (numPlayers > predefinedColors.length) {
            throw new IllegalArgumentException("Too many players for the available predefined colors.");
        }
    
        // Assign predefined colors to players
        for (int i = 0; i < numPlayers; i++) {
            colors[i] = predefinedColors[i];  // Assign predefined color to each player
        }
    
        return colors;
    }
    

    private static void gameLoop() {
        Timer timer = new Timer(1000, e -> {
            if (gameOver) return;

            if (players[game.player() - 1] != null) {
                AI aiPlayer = players[game.player() - 1];
                int move = aiPlayer.play(game, playerDepths[game.player() - 1]);
                try {
                    game.play(move);
                } catch (Exception ignored) {
                }
                gameFrame.repaint();
                updateTurnLabel();
                checkGameOver();
            }
        });
        timer.start();
    }

    private static void updateTurnLabel() {
        turnLabel.setText("Player " + game.player() + "'s Turn");
    }

    private static void showGameHistory() {
        JFrame historyFrame = new JFrame("Game History");
        historyFrame.setSize(400, 400);
        JTextArea historyArea = new JTextArea(gameHistory.toString());
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyFrame.add(scrollPane);
        historyFrame.setVisible(true);
    }
    
    private static void checkGameOver() {
        if (game.won()) {
            gameOver = true;
            int winner = game.winner();
            String winnerType = playerTypes[winner - 1];  // Get player type (Human, MonteCarlo, etc.)
            gameHistory.append("Game ").append(gameHistory.length() / 30 + 1).append(": Player ").append(winner)
                    .append(" (").append(winnerType).append(") wins!\n");
            JOptionPane.showMessageDialog(gameFrame, "Player " + winner + " (" + winnerType + ") wins!");
        } else if (game.finished()) {
            gameOver = true;
            gameHistory.append("Game ").append(gameHistory.length() / 30 + 1).append(": It's a draw!\n");
            JOptionPane.showMessageDialog(gameFrame, "It's a tie!");
        }
    }
    

    private static class GamePanel extends JPanel {
        public GamePanel() {
            this.setPreferredSize(new Dimension(width * 100, height * 100));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (gameOver) return;

                    int col = e.getX() / 100;
                    if (game.isLegal(col) && players[game.player()-1]==null) {
                        System.out.println("Monika");
                        try {
                            game.play(col);
                        } catch (Exception ignored) {
                        }
                        repaint();
                        updateTurnLabel();
                        checkGameOver();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * 100, row * 100, 100, 100);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * 100, row * 100, 100, 100);
                }
            }

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int player = game.board()[row][col];
                    if (player != 0) {
                        g.setColor(playerColors[player - 1]);
                        g.fillOval(col * 100 + 10, row * 100 + 10, 80, 80);
                    }
                }
            }
        }
    }
}
