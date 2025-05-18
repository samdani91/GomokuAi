import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Game {
	private final Board board;
	private boolean isPlayersTurn = true;
	private boolean gameFinished = false;
	private int minimaxDepth = 3;
	private boolean aiStarts = true;
	private final MiniMaxAI miniMaxAI;
	private int winner;

	public Game(Board board) {
		this.board = board;
		miniMaxAI = new MiniMaxAI(board);
		
		winner = 0;
	}

	public void start() {
		if(aiStarts) playMove(board.getBoardSize()/2, board.getBoardSize()/2, false);

		board.startListening(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				if(isPlayersTurn) {
					isPlayersTurn = false;
					Thread mouseClickThread = new Thread(new MouseClickHandler(arg0));
					mouseClickThread.start();
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
	}

	public void setAIDepth(int depth) {
		this.minimaxDepth = depth;
	}

	public class MouseClickHandler implements Runnable{
		private final MouseEvent event;

		public MouseClickHandler(MouseEvent event) {
			this.event = event;
		}

		public void run() {
			if(gameFinished) return;

			int posX = board.getRelativePos( event.getX() );
			int posY = board.getRelativePos( event.getY() );

			if(!playMove(posX, posY, true)) {
				isPlayersTurn = true;
				return;
			}

			winner = checkWinner();

			if(winner == 2) {
				System.out.println("Player WON!");
				board.printWinner(winner);
				gameFinished = true;
				return;
			}

			int[] aiMove = miniMaxAI.calculateNextMove(minimaxDepth);

			if(aiMove == null) {
				System.out.println("No possible moves left. Game Over.");
				board.printWinner(0);
				gameFinished = true;
				return;
			}

			playMove(aiMove[1], aiMove[0], false);

			System.out.println("Black: " + MiniMaxAI.getScore(board,true,true) + " White: " + MiniMaxAI.getScore(board,false,true));

			winner = checkWinner();

			if(winner == 1) {
				System.out.println("AI WON!");
				board.printWinner(winner);
				gameFinished = true;
				return;
			}

			if(board.generateMoves().isEmpty()) {
				System.out.println("No possible moves left. Game Over.");
				board.printWinner(0);
				gameFinished = true;
				return;

			}

			isPlayersTurn = true;
		}

	}

	public void setAIStarts(boolean aiStarts) {
		this.aiStarts = aiStarts;
	}


	private int checkWinner() {
		if(MiniMaxAI.getScore(board, true, false) >= MiniMaxAI.getWinScore()) return 2;
		if(MiniMaxAI.getScore(board, false, true) >= MiniMaxAI.getWinScore()) return 1;
		return 0;
	}
	private boolean playMove(int posX, int posY, boolean black) {
		return board.addStone(posX, posY, black);
	}
	
}
