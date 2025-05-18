import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

public class MainGUI extends JFrame {
	@Serial
	private static final long serialVersionUID = 1L;

	private JPanel boardPanel;
	private final JButton buttonStart;
	private final JRadioButton rbHard;
	private final JRadioButton rbComputer;

	public MainGUI(int width, int height, String title) {
		// Set Nimbus Look and Feel
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ignored) {}

		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel setupPanel = new JPanel();
		setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.Y_AXIS));
		setupPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

		// Difficulty Panel
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
		difficultyPanel.setBorder(BorderFactory.createTitledBorder("Select Difficulty"));
		difficultyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JRadioButton rbNormal = new JRadioButton("Normal (Faster)");
		rbHard = new JRadioButton("Hard (Slower)");
		rbNormal.setSelected(true);

		ButtonGroup bgDifficulty = new ButtonGroup();
		bgDifficulty.add(rbNormal);
		bgDifficulty.add(rbHard);

		difficultyPanel.add(rbNormal);
		difficultyPanel.add(rbHard);
		difficultyPanel.setMaximumSize(new Dimension(200, 100)); // unify panel width

		// Starting Player Panel
		JPanel startingPlayerPanel = new JPanel();
		startingPlayerPanel.setLayout(new BoxLayout(startingPlayerPanel, BoxLayout.Y_AXIS));
		startingPlayerPanel.setBorder(BorderFactory.createTitledBorder("Who Starts First"));
		startingPlayerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JRadioButton rbHuman = new JRadioButton("Human");
		rbComputer = new JRadioButton("Computer");
		rbComputer.setSelected(true);

		ButtonGroup bgStartingPlayer = new ButtonGroup();
		bgStartingPlayer.add(rbHuman);
		bgStartingPlayer.add(rbComputer);

		startingPlayerPanel.add(rbComputer);
		startingPlayerPanel.add(rbHuman);
		startingPlayerPanel.setMaximumSize(new Dimension(200, 100)); // unify panel width

		// Start Button (Centered)
		buttonStart = new JButton("Start Game");
		buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonStart.setPreferredSize(new Dimension(120, 35));

		// Add everything
		setupPanel.add(difficultyPanel);
		setupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		setupPanel.add(startingPlayerPanel);
		setupPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		setupPanel.add(buttonStart);

		add(setupPanel);
		pack();
		setLocationRelativeTo(null); // center on screen
		setResizable(false);
	}

	public Object[] fetchSettings() {
		int difficulty = rbHard.isSelected() ? 4 : 3;
		boolean computerStarts = rbComputer.isSelected();
		return new Object[]{difficulty, computerStarts};
	}

	public void listenGameStartButton(ActionListener listener) {
		buttonStart.addActionListener(listener);
	}

	public void attachBoard(JPanel board) {
		boardPanel = board;
	}

	public void showBoard() {
		setContentPane(boardPanel);
		invalidate();
		validate();
		pack();
		setLocationRelativeTo(null);
	}
}
