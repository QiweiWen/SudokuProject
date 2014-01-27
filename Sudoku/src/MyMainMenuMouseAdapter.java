import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MyMainMenuMouseAdapter extends MouseAdapter {
    
    private GUIInterface gUIInterfaceJFrame;
    private MainMenuGUI mainMenuJPanel;
    
    public MyMainMenuMouseAdapter(GUIInterface aParentJFrame, MainMenuGUI aMainMenuJPanel)
    {
        this.gUIInterfaceJFrame = aParentJFrame;
        this.mainMenuJPanel = aMainMenuJPanel;
    }
    
    public void mouseClicked(MouseEvent e) 
    {   

        String buttonName = e.getComponent().getName();
        GameDifficulty level;
        
        this.gUIInterfaceJFrame.remove(mainMenuJPanel);
        mainMenuJPanel = null;
        
        GameBoardGUI newGameBoardGUI = new GameBoardGUI(gUIInterfaceJFrame);
        
        if(buttonName.equals("beginnerButton"))
        {
            level = GameDifficulty.BEGINNER;
        }
        else if(buttonName.equals("intermediateButton"))
        {
            level = GameDifficulty.INTERMEDIATE;
        }
        else
        {
            level = GameDifficulty.ADVANCED;
        }
        
        newGameBoardGUI.loadGameBoardGUI(level);
        int row, col;
        int val;
        Main.g = new Game(level);
        for(row = 0; row < 9; row++){
        	for(col = 0; col < 9; col++){
        		val = Main.g.get(row, col);
        		if(val != 0){
        			newGameBoardGUI.setSolidGridCellValue(row, col, val);
        		}
        	}
        }
        
        // Launch game board display.
        newGameBoardGUI.launchGameBoardGUI(); 
    }
    
    public void mouseEntered(MouseEvent e) 
    {   

        String buttonName = e.getComponent().getName();
        
        if(buttonName.equals("beginnerButton"))
        {
            Icon newBgImageIcon = new ImageIcon("src/beginnerBg.png");
            this.mainMenuJPanel.setIcon(newBgImageIcon);
        }
        else if(buttonName.equals("intermediateButton"))
        {
            Icon newBgImageIcon = new ImageIcon("src/intermediateBg.png");
            this.mainMenuJPanel.setIcon(newBgImageIcon);
        }
        else
        {
            Icon newBgImageIcon = new ImageIcon("src/advancedBg.png");
            this.mainMenuJPanel.setIcon(newBgImageIcon);
        }
        
        this.gUIInterfaceJFrame.validate();
        this.gUIInterfaceJFrame.repaint();
    }
    
}
