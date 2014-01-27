import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ResultsGUI extends JLabel{
    
    private GUIInterface parentFrame;
    private ResultType givenResult;
    
    public ResultsGUI(GUIInterface aParentFrame, ResultType aResult)
    {
        this.parentFrame = aParentFrame;
        this.givenResult = aResult;
        
        JLabel resultLabel;
        
        if(this.givenResult == ResultType.SUCCESS)
        {
            Icon newBgImageIcon = new ImageIcon("src/winnerBg.png");
            this.setIcon(newBgImageIcon);
        }
        else if(this.givenResult == ResultType.FAILURE)
        {
            Icon newBgImageIcon = new ImageIcon("src/loserBg.png");
            this.setIcon(newBgImageIcon);
        }
        
        
        JButton mainMenuButton = new JButton("Back to Main Menu");
        mainMenuButton.setName("mainMenuButton");
        MouseListener newResultsMouseListener = new MyResultsMouseAdapter(this.parentFrame, this);
        mainMenuButton.addMouseListener(newResultsMouseListener);
        
        this.setLayout(new BorderLayout());

        this.add(mainMenuButton, BorderLayout.SOUTH);
    }
    

    public void launchResultsGUI()
    {   
        this.parentFrame.add(this);
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
    
    
}
