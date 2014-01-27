import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Timer;
import javax.swing.JOptionPane;

public class GameBoardGUI extends JLabel{
    
    private GUIInterface parentFrame;
    private JLabel assistsRemaining;
    private JLabel[][] jLabelGrid;
    private JButton assistButton;
    private JLabel timer;
    
    public GameBoardGUI(GUIInterface aParentFrame)
    {
        this.parentFrame = aParentFrame;
    }
    
    public void loadGameBoardGUI(GameDifficulty aLevel)
    {
        this.setLayout(new GridBagLayout());
        
        if(aLevel == GameDifficulty.BEGINNER)
        {
            Icon newBgImageIcon = new ImageIcon("src/beginnerBg.png");
            this.setIcon(newBgImageIcon);
        }
        else if(aLevel == GameDifficulty.INTERMEDIATE)
        {
            Icon newBgImageIcon = new ImageIcon("src/intermediateBg.png");
            this.setIcon(newBgImageIcon);
        }
        else if(aLevel == GameDifficulty.ADVANCED)
        {
            Icon newBgImageIcon = new ImageIcon("src/advancedBg.png");
            this.setIcon(newBgImageIcon);
        }
        class timer_class implements Runnable{
        	int mins;
        	JLabel l;
        	int currTime;
        	private GUIInterface gUIInterfaceJFrame;
            private GameBoardGUI gameBoardJPanel;
        	timer_class(JLabel l, int mins,GUIInterface g, GameBoardGUI gb){
        		this.l = l;
        		this.mins = mins;
        		currTime = 0;
        	}
        	public void run(){
        		char str[] = new char[4];
        		str[0] = '0';
        		str[1] = ':';
        		str[2] = '0';
        		str[3] = '0';
        		while (true){
        			String minuteStr = String.format("%02d",(mins*60 - currTime) % 60);
        			str[0] = (char)((int)(mins*60 - currTime) / 60 + '0');
        			str[2] = minuteStr.charAt(0);
        			str[3] = minuteStr.charAt(1);
        			String timeStr = new String(str);
        			l.setText(timeStr);
        			try{
        				Thread.sleep(1000);
        			}catch(Exception e){}
        			currTime += 1;
        			if (currTime >= mins*60) {
        				 this.gUIInterfaceJFrame.remove(this.gameBoardJPanel);
        		         this.gameBoardJPanel = null;
        		         JOptionPane.showMessageDialog(null, "Time's up, try again.", "Time's up", JOptionPane.INFORMATION_MESSAGE);  
        		         MainMenuGUI newMainMenuGUI = new MainMenuGUI(this.gUIInterfaceJFrame);
        		         newMainMenuGUI.launchMainMenuGUI();
        			}
        		}
        	}
        }
        
        
        GridBagConstraints myConstraints = new GridBagConstraints();
        
        Icon newTitleImageIcon = new ImageIcon("src/playTitle.png");
        JLabel screenTitle = new JLabel(newTitleImageIcon);
        myConstraints.gridx = 0;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 5;
        this.add(screenTitle, myConstraints);
        
        JLabel timer = new JLabel("timer", JLabel.CENTER);
        timer.setOpaque(true);
        timer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myConstraints.gridx = 4;
        myConstraints.gridy = 2;
        myConstraints.gridwidth = 1;
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(timer, myConstraints);
        this.timer = timer;
        
        Thread t = new Thread(new timer_class(timer,5,parentFrame,this));
        t.start();
        // Preparing Game Board button mouse listeners.
        MouseListener newButtonMouseListener = new MyGBButtonMouseAdapter(this.parentFrame, this);
        
        JButton assistButton = new JButton("Assist");
        assistButton.setName("Assist");
        assistButton.addMouseListener(newButtonMouseListener);
        myConstraints.gridx = 4;
        myConstraints.gridy = 4;
        myConstraints.gridwidth = 1;
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(assistButton, myConstraints);
        this.assistButton = assistButton;
        
        JLabel assistsRemaining = new JLabel("   0   ", JLabel.CENTER);
        assistsRemaining.setOpaque(true);
        assistsRemaining.setName("Assists Remaining");
        assistsRemaining.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myConstraints.gridx = 4;
        myConstraints.gridy = 6;
        myConstraints.gridwidth = 1;
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(assistsRemaining, myConstraints);
        this.assistsRemaining = assistsRemaining;
        
        JButton backButton = new JButton("Back");
        backButton.setName("Back");
        backButton.addMouseListener(newButtonMouseListener);
        myConstraints.gridx = 0;
        myConstraints.gridy = 6;
        myConstraints.gridwidth = 1;
        myConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(backButton, myConstraints);
        
        // Column padding.
        myConstraints.gridx = 3;
        myConstraints.gridy = 0;
        myConstraints.gridwidth = 1;
        myConstraints.gridheight = 4;
        this.add(Box.createRigidArea(new Dimension(15,30)), myConstraints);
        
        // Under title padding.
        myConstraints.gridx = 0;
        myConstraints.gridy = 1;
        myConstraints.gridwidth = 5;
        myConstraints.gridheight = 1;
        this.add(Box.createRigidArea(new Dimension(250,50)), myConstraints);
        
        // Under grid padding.
        myConstraints.gridx = 0;
        myConstraints.gridy = 5;
        myConstraints.gridwidth = 5;
        myConstraints.gridheight = 1;
        this.add(Box.createRigidArea(new Dimension(20,10)), myConstraints);
        
        // Creating grid.
        JPanel sudokuGridPanel  = new JPanel();
        sudokuGridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        
        // Setting Sudoku JPanel layout.
        sudokuGridPanel.setLayout(new GridLayout(9, 9, 0, 0));
        
        // Preparing mouse listener.
        MouseListener newMyMouseAdapter = new MyGBGridMouseAdapter(this.parentFrame, this, this.assistButton);
        
        this.jLabelGrid = new JLabel[9][9];
        for(int row=0;row<9;row++)
        {   
            for(int col=0;col<9;col++)
            {   
                JLabel boxLabel = new JLabel("    ", JLabel.CENTER);
                boxLabel.setFocusable(true);
                boxLabel.setName(col+","+row);
                boxLabel.setFont(new Font("Serif", Font.BOLD, 20));
                boxLabel.setOpaque(true);
                boxLabel.setBackground(Color.WHITE);
                boxLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                boxLabel.addMouseListener(newMyMouseAdapter);
                
                if(row == 3 || row == 6)
                {
                    boxLabel.setBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, Color.BLACK));
                }
                
                if (col == 3 || col == 6)
                {
                    boxLabel.setBorder(BorderFactory.createMatteBorder(1, 4, 1, 1, Color.BLACK));
                }
                
                if ( (row == 3 || row == 6) && (col == 3 || col == 6))
                {
                    boxLabel.setBorder(BorderFactory.createMatteBorder(4, 4, 1, 1, Color.BLACK));
                }
                
                this.jLabelGrid[row][col] = boxLabel;
                
                sudokuGridPanel.add(boxLabel);
            }
        }
        
        myConstraints.gridx = 0;
        myConstraints.gridy = 2;
        myConstraints.gridwidth = 3;
        myConstraints.gridheight = 3;
        this.add(sudokuGridPanel, myConstraints);
        
    }
    
    public void launchGameBoardGUI()
    {
        this.parentFrame.add(this);
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
    
    public void setSolidGridCellValue(int aX, int aY, int aValue)
    {
        JLabel cell = this.jLabelGrid[aY][aX];
        cell.setText("  "+aValue+"  ");
        MouseListener[] mouseListenerList = cell.getMouseListeners();
        
        for(MouseListener listener : mouseListenerList)
        {
            cell.removeMouseListener(listener);
        }
        
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
    
    public void setGridCellValue(int aX, int aY, int aValue)
    {
        this.jLabelGrid[aY][aX].setText("  "+aValue+"  ");
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
    
    public int getGridCellValue(int aX, int aY)
    {
        String cellTextValue = this.jLabelGrid[aY][aX].getText().trim();
        return Integer.parseInt(cellTextValue);
    }
    
    public void setRemainingAssists(int aAssistsRemaining)
    {
        this.assistsRemaining.setText(""+aAssistsRemaining);
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
    
    public int getRemainingAssists()
    {   
        String remainingAssists = this.assistsRemaining.getText();
        return Integer.parseInt(remainingAssists);
    }
    
    public void setTimer(String aTimerValue)
    {
        this.timer.setText(aTimerValue);
    }
    
}
