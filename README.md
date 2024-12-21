# AI Group Project: Connect 4

**Team**: Elen Pluzyan, Tatev Stepanyan, Levon Yedigaryan

**Course**: CS241/346 Artificial Intelligence and Decision Support

**Professor**: Monika Stepanyan

Fall 2024, AUA

---

## Project Overview
This project involves implementing an AI for the classic game *Connect 4*. The AI employs a variety of algorithms and heuristics to make intelligent decisions, and the code is structured for extensibility and experimentation. A GUI provides a user-friendly interface for playing against the AI.

---

## Project Structure
The following is a description of the project's directory and files:

- **`scripts/`**: Contains all technical components of the project. For detailed explanations of the methods and algorithms, refer to the `Project.pdf` document.
    - **`AI.java`**: Base class for the AI player implementation.
    - **`AlphaBeta.java`**: Implements the Alpha-Beta pruning algorithm for efficient game-tree search.
    - **`ConnectFour.java`**: Core logic for managing the Connect 4 game.
    - **`ConnectFourGUI.java`**: Graphical User Interface for playing Connect 4 against the AI.
    - **`Heuristic.java`**: Interface for defining heuristic functions.
    - **`HillClimbing.java`**: Implements the Hill Climbing algorithm for decision-making.
    - **`IllegalMoveException.java`**: Custom exception for handling illegal game moves.
    - **`StupidPlayer.java`**: A player that makes random moves to see whether our algorithms actually work as AI's.
    - **`STatistics.java`**: Runs simulations and creates a txt file with all the results.
    - **`MonteCarlo.java`**: Implements the Monte Carlo Tree Search algorithm.
    - **`OurOwnHeuristic.java`**: A custom heuristic designed by our team for AI decision-making.
    - **`PositionalHeuristic.java`**: Heuristic based on positional advantage in the game board.
    - **`SequenceCountingHeuristic.java`**: Heuristic that evaluates sequences of connected tokens.
    - **`Test.java`**: Contains test cases for validating the functionality of the project components.
    - **`ThreatHeuristic.java`**: Heuristic that prioritizes potential threats on the board.
    - **`WinProbabilityHeuristic.java`**: Heuristic that estimates the probability of winning from the current state.
- **`Project.pdf`**: Comprehensive documentation covering project goals, implementation details, and methods.

---

## Requirements
To run this project, you will need the following:
- Java Development Kit (JDK) 8 or higher.
- A Java Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse (optional, for editing).
- A compatible operating system (Windows, macOS, or Linux).

---

## Steps to Run
1. **Setup Environment**: Ensure the JDK is installed and properly configured in your system.
2. **Clone the Repository**: Download the project files from the repository or copy them to your local machine.
3. **Compile the Code**:
   - Navigate to the project directory in your terminal.
   - Use the `javac` command to compile all `.java` files in the `scripts/` directory. Example:
     ```bash
     javac scripts/*.java
     ```
4. **Run the Game**:
   - Execute the `ConnectFourGUI` class to start the graphical interface. Example:
     ```bash
     java scripts.ConnectFourGUI
     ```
   - Alternatively, run `ConnectFour.java` for a command-line version.
5. **Experiment with AI**: Test different algorithms and heuristics by modifying the AI configuration in the code.

---

## Notes
- The `Project.pdf` file provides additional insight into the design and algorithms used.
- Each heuristic and algorithm is modular, allowing for easy substitution and testing of different strategies.

---

## Acknowledgements
We thank Professor Monika Stepanyan for guidance and support throughout the project.
