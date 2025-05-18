import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Board {

	private BoardGUI gui;
	private int[][] boardMatrix;

	public Board(int sideLength, int boardSize) {
		gui = new BoardGUI(sideLength, boardSize);
		boardMatrix = new int[boardSize][boardSize];
	}

	public Board(Board board) {
		int[][] matrixToCopy = board.getBoardMatrix();
		boardMatrix = new int[matrixToCopy.length][matrixToCopy.length];
		for(int i = 0; i < matrixToCopy.length; i++) {
			for(int j = 0; j < matrixToCopy.length; j++) {
				boardMatrix[i][j] = matrixToCopy[i][j];
			}
		}
	}

	public int getBoardSize() {
		return boardMatrix.length;
	}

	public void removeStoneNoGUI(int posX, int posY) {
		boardMatrix[posY][posX] = 0;
	}

	public void addStoneNoGUI(int posX, int posY, boolean black) {
		boardMatrix[posY][posX] = black ? 2 : 1;
	}

	public boolean addStone(int posX, int posY, boolean black) {
		if(boardMatrix[posY][posX] != 0) return false;
		gui.drawStone(posX, posY, black);
		boardMatrix[posY][posX] = black ? 2 : 1;
		return true;
	}

	public ArrayList<int[]> generateMoves() {
		ArrayList<int[]> moveList = new ArrayList<>();
		int boardSize = boardMatrix.length;

		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(boardMatrix[i][j] > 0) continue;

				if(i > 0) {
					if(j > 0) {
						if(boardMatrix[i-1][j-1] > 0 || boardMatrix[i][j-1] > 0) {
							moveList.add(new int[]{i, j});
							continue;
						}
					}
					if(j < boardSize - 1) {
						if(boardMatrix[i-1][j+1] > 0 || boardMatrix[i][j+1] > 0) {
							moveList.add(new int[]{i, j});
							continue;
						}
					}
					if(boardMatrix[i-1][j] > 0) {
						moveList.add(new int[]{i, j});
						continue;
					}
				}
				if(i < boardSize - 1) {
					if(j > 0) {
						if(boardMatrix[i+1][j-1] > 0 || boardMatrix[i][j-1] > 0) {
							moveList.add(new int[]{i, j});
							continue;
						}
					}
					if(j < boardSize - 1) {
						if(boardMatrix[i+1][j+1] > 0 || boardMatrix[i][j+1] > 0) {
							moveList.add(new int[]{i, j});
							continue;
						}
					}
					if(boardMatrix[i+1][j] > 0) {
						moveList.add(new int[]{i, j});
						continue;
					}
				}
			}
		}

		return moveList;
	}

	public int[][] getBoardMatrix() {
		return boardMatrix;
	}

	public void startListening(MouseListener listener) {
		gui.attachListener(listener);
	}

	public BoardGUI getGUI() {
		return gui;
	}

	public int getRelativePos(int x) {
		return gui.getRelativePos(x);
	}

	public void printWinner(int winner) {
		gui.printWinner(winner);
	}

	public void thinkingStarted() {
		gui.setAIThinking(true);
	}

	public void thinkingFinished() {
		gui.setAIThinking(false);
	}
}
