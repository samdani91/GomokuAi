import javax.swing.*;
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
		setSize(width, height);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel setupPanel = new JPanel();
		setupPanel.setLayout(new BoxLayout(setupPanel, BoxLayout.PAGE_AXIS));
        JPanel difficultyPanel = new JPanel();
        JPanel startingPlayerPanel = new JPanel();
		
		buttonStart = new JButton("Start Game");
        JRadioButton rbNormal = new JRadioButton("Normal (Faster)");
		rbHard = new JRadioButton("Hard (Slower)");

        JRadioButton rbHuman = new JRadioButton("Human");
		rbComputer = new JRadioButton("Computer");

        ButtonGroup bgDifficulty = new ButtonGroup();
        ButtonGroup bgStartingPlayer = new ButtonGroup();
		
		bgDifficulty.add(rbNormal);
		bgDifficulty.add(rbHard);
		
		bgStartingPlayer.add(rbHuman);
		bgStartingPlayer.add(rbComputer);

        JLabel taDifficulty = new JLabel("Difficulty: ");
        JLabel taStartingPlayer = new JLabel("Starts First.");
		
		rbNormal.setSelected(true);
		rbComputer.setSelected(true);
		
		difficultyPanel.add(taDifficulty);
		difficultyPanel.add(rbNormal);
		difficultyPanel.add(rbHard);
		
		startingPlayerPanel.add(rbComputer);
		startingPlayerPanel.add(rbHuman);
		startingPlayerPanel.add(taStartingPlayer);
		
		setupPanel.add(difficultyPanel);
		setupPanel.add(startingPlayerPanel);
		setupPanel.add(buttonStart);
		
		add(setupPanel);
		pack();
	}

	public Object[] fetchSettings() {
        int difficulty;
        if( rbHard.isSelected() ) {
			difficulty = 4;
		} else difficulty = 3;

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
	}
	
	
}
