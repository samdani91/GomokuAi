import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.Serial;

import javax.swing.JPanel;

public class BoardGUI extends JPanel {

	private final Graphics2D g2D;
	private final BufferedImage image;
	private boolean isAIThinking = false;

	@Serial
	private static final long serialVersionUID = 1L;

	private final int sideLength;
	private final int boardSize;
	private final int cellLength;

	public BoardGUI(int sideLength, int boardSize) {
		this.sideLength = sideLength;
		this.boardSize = boardSize;
		this.cellLength  = sideLength / boardSize;

		image = new BufferedImage(sideLength, sideLength, BufferedImage.TYPE_INT_ARGB);

		g2D = (Graphics2D)image.getGraphics();
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2D.setColor(new Color(0, 139, 139));
		g2D.fillRect(0,0,sideLength, sideLength);

		g2D.setColor(new Color(51, 51, 51));
		for(int i=1; i<=boardSize; i++) {
			g2D.drawLine(i*cellLength, 0, i*cellLength, sideLength);
		}
		for(int i=1; i<=boardSize; i++) {
			g2D.drawLine(0, i*cellLength, sideLength, i*cellLength);
		}
	}

	public int getRelativePos(int x) {
		if(x >= sideLength) x = sideLength-1;
		return (int) ( x * boardSize / sideLength );
	}

	public Dimension getPreferredSize() {
		return new Dimension(sideLength, sideLength);
	}

	public void printWinner(int winner) {
		FontMetrics metrics = g2D.getFontMetrics(g2D.getFont());
		String text = winner == 2 ? "YOU WON!" : (winner == 1 ? "COMPUTER WON!" : "TIED!");

		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2D.setFont(new Font(g2D.getFont().getName(), Font.PLAIN, 48));

		g2D.setColor(winner == 2 ? new Color(0, 100, 0) : (winner == 1 ? new Color(139, 0, 0) : new Color(75, 0, 130)));
		int x = (sideLength/2 - metrics.stringWidth(text)*2);
		int y = sideLength/2;

		g2D.drawString(text,x-2,y);
		g2D.drawString(text,x+2,y);
		g2D.drawString(text,x,y-2);
		g2D.drawString(text,x,y+2);

		g2D.setColor(winner == 2 ? new Color(0, 255, 127) : (winner == 1 ? new Color(255, 69, 0) : new Color(153, 50, 204)));

		g2D.drawString(text,x,y);

		repaint();
	}

	public void drawStone(int posX, int posY, boolean black) {
		if(posX >= boardSize || posY >= boardSize) return;

		g2D.setColor(black ? Color.black : new Color(245, 245, 245));
		g2D.fillOval((int)(cellLength*(posX+0.05)),
				(int)(cellLength*(posY+0.05)),
				(int)(cellLength*0.9),
				(int)(cellLength*0.9));
		g2D.setColor(new Color(51, 51, 51));
		g2D.setStroke(new BasicStroke(2));
		g2D.drawOval((int)(cellLength*(posX+0.05)),
				(int)(cellLength*(posY+0.05)),
				(int)(cellLength*0.9),
				(int)(cellLength*0.9));

		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g.create();

		g2D.drawImage(image, 0, 0, sideLength, sideLength, null);

		if(isAIThinking) {
			printThinking(g2D);
		}

		g2D.setColor(new Color(25, 25, 112));
		g2D.drawRect(0, 0, sideLength, sideLength);
	}

	private void printThinking(Graphics2D g2D) {
		FontMetrics metrics = g2D.getFontMetrics(g2D.getFont());
		String text = "Thinking...";

		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2D.setFont(new Font(g2D.getFont().getName(), Font.PLAIN, 48));

		int x = (sideLength/2 - metrics.stringWidth(text)*2);
		int y = sideLength/2;

		g2D.setColor(new Color(255, 255, 0, 150));
		g2D.drawString(text,x,y);
	}

	public void attachListener(MouseListener listener) {
		addMouseListener(listener);
	}

	public void setAIThinking(boolean flag) {
		isAIThinking = flag;
		repaint();
	}
}
