import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;


public class MyGBButtonMouseAdapter extends MouseAdapter{
    
    private GUIInterface gUIInterfaceJFrame;
    private GameBoardGUI gameBoardJPanel;
    
    public MyGBButtonMouseAdapter(GUIInterface aParentJFrame, GameBoardGUI aGameBoardJPanel) 
    {
        this.gUIInterfaceJFrame = aParentJFrame;
        this.gameBoardJPanel = aGameBoardJPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        JButton buttonClicked = (JButton) e.getComponent();
        
        if(buttonClicked.getName().equals("Back"))
        {
            /*
             * Interface function to revert back end to 
             * main menu mode.
             */
            
            this.gUIInterfaceJFrame.remove(this.gameBoardJPanel);
            this.gameBoardJPanel = null;
            
            MainMenuGUI newMainMenuGUI = new MainMenuGUI(this.gUIInterfaceJFrame);
            newMainMenuGUI.launchMainMenuGUI();
            
        }
        
    }
    
}
