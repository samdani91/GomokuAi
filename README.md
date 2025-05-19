# Gomoku AI Game

A Java-based implementation of the classic board game **Gomoku (Five in a Row)**, featuring an AI opponent powered by the Minimax algorithm with alpha-beta pruning. The game includes a fully interactive GUI built using Java Swing, offering a smooth and engaging gameplay experience for human players against the AI.

## 🎮 Gameplay Overview

Gomoku is a strategy game where players take turns placing stones (X or O) on a grid, aiming to align **five consecutive stones** horizontally, vertically, or diagonally. This implementation allows a human player to compete against a smart, adaptive AI opponent.

## 🤖 AI Implementation

### Minimax with Alpha-Beta Pruning
The AI utilizes the classical **Minimax algorithm** enhanced with **alpha-beta pruning**, significantly reducing the number of game states that need to be evaluated. Key characteristics:
- Adjustable search depth (`minimaxDepth`)
- Efficient pruning for faster computation
- Defaults to a center-first strategy for opening moves

### Evaluation Function
A custom evaluation function scores board states by identifying:
- Rows of 2, 3, or 4 aligned stones (both offensive and defensive)
- Immediate threats and future potential
- Balance between attack and block strategies

This allows the AI to act both aggressively and defensively based on the current game context.

### Early Stopping Mechanism
To maintain responsiveness:
- Search depth is limited via `minimaxDepth`
- Time constraints (e.g., using `System.currentTimeMillis()`) help ensure the AI returns the best-known move if time runs low

### Optimized Move Generation
The AI explores only **empty positions adjacent to existing stones**, dramatically lowering the branching factor and speeding up decision-making.

## 🖥️ Graphical User Interface

The GUI is built with **Java Swing** and includes:
- Visual game board with real-time updates
- Stone placement animations
- Status messages: _"YOU WON!"_, _"COMPUTER WON!"_, _"Thinking…"_
- Simple and intuitive user interaction

## 📁 Project Structure

- `Board.java` – Core game logic and move generation
- `MiniMaxAI.java` – AI logic and recursive search
- `BoardGUI.java` – Graphical interface and user interaction
- `Main.java` – Application entry point

## 🔧 Installation & Running

### Requirements
- Java Development Kit (JDK) 8 or higher
- Git (to clone the repository)

### Steps
```bash
git clone https://github.com/samdani91/GomokuAi
cd GomokuAi
javac *.java
java Main
