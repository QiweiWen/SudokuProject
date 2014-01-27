import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyGBGridMouseAdapter extends MouseAdapter{
    
    private GUIInterface gUIInterfaceJFrame;
    private GameBoardGUI gameBoardJPanel;
    private JLabel lastSelectedJLabel;
    private KeyListener lastKeyListener;
    private MouseListener lastAssistMouseListener;
    private JButton assistButton;
    
    public MyGBGridMouseAdapter(GUIInterface aParentJFrame, GameBoardGUI aGameBoardJPanel, JButton aAssistButton)
    {
        this.gUIInterfaceJFrame = aParentJFrame;
        this.gameBoardJPanel = aGameBoardJPanel;
        this.lastSelectedJLabel = null;
        this.lastKeyListener = null;
        this.lastAssistMouseListener = null;
        this.assistButton = aAssistButton;
    }
    
    public void mouseClicked(MouseEvent e) 
    {   
        JLabel selectedJLabel = (JLabel) e.getComponent();
        MouseListener newAssistMouseListener = new MyGBAssistMouseAdapter(this.gUIInterfaceJFrame, this.gameBoardJPanel, selectedJLabel);
        KeyListener newKeyListener = new MyGBGridKeyAdapter(selectedJLabel, this.assistButton, newAssistMouseListener);
        
        if(this.lastSelectedJLabel != selectedJLabel)
        {
            selectedJLabel.addKeyListener(newKeyListener);
            selectedJLabel.setBackground(Color.GREEN);
            selectedJLabel.requestFocus();
           
            this.assistButton.addMouseListener(newAssistMouseListener); 
        }
        
        if(this.lastSelectedJLabel != null)
        {
            this.lastSelectedJLabel.setBackground(Color.WHITE);
            this.lastSelectedJLabel.removeKeyListener(this.lastKeyListener);
            this.assistButton.removeMouseListener(lastAssistMouseListener);
        }
        
        if(this.lastSelectedJLabel == selectedJLabel)
        {
            this.lastSelectedJLabel = null;
            this.lastKeyListener = null;
            this.lastAssistMouseListener = null;
        }
        else
        {
            this.lastKeyListener = newKeyListener;
            this.lastSelectedJLabel = selectedJLabel;
            this.lastAssistMouseListener = newAssistMouseListener;
        }
        
        
        
        this.gUIInterfaceJFrame.validate();
        this.gUIInterfaceJFrame.repaint();
        
    }
    
}
