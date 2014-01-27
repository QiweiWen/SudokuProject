import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyResultsMouseAdapter extends MouseAdapter 
{
    
    private GUIInterface gUIInterfaceJFrame;
    private ResultsGUI resultsGUI;
    
    public MyResultsMouseAdapter(GUIInterface aParentJFrame, ResultsGUI aResultsJPanel)
    {
        this.gUIInterfaceJFrame = aParentJFrame;
        this.resultsGUI = aResultsJPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        /*
         * Back end code to signify transition to main menu.
         */
        
        this.gUIInterfaceJFrame.remove(this.resultsGUI);
        this.resultsGUI = null;
        
        MainMenuGUI newMainMenuGUI = new MainMenuGUI(this.gUIInterfaceJFrame);
        newMainMenuGUI.launchMainMenuGUI();
    }
    
}
