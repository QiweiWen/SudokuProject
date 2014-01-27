import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.*;


public class MainMenuGUI extends JLabel {
    
    private GUIInterface parentFrame;
    
    public MainMenuGUI(GUIInterface aParentFrame)
    {
        this.parentFrame = aParentFrame;
        
        JPanel buttonGridPanel  = new JPanel();
        buttonGridPanel.setLayout(new GridLayout(4, 1, 0, 50));
        buttonGridPanel.setOpaque(false);
        
        
        Icon newImageIcon = new ImageIcon("src/mainMenuTitle.png");
        JLabel screenTitle = new JLabel(newImageIcon);
        buttonGridPanel.add(screenTitle);
        
        MouseListener newMyMouseAdapter = new MyMainMenuMouseAdapter(this.parentFrame, this);
        
        JButton beginnerButton = new JButton("Beginner");
        beginnerButton.setName("beginnerButton");
        beginnerButton.addMouseListener(newMyMouseAdapter);
        buttonGridPanel.add(beginnerButton);
        
        JButton intermediateButton = new JButton("Intermediate");
        intermediateButton.setName("intermediateButton");
        intermediateButton.addMouseListener(newMyMouseAdapter);
        buttonGridPanel.add(intermediateButton);
        
        JButton advancedButton = new JButton("Advanced");
        advancedButton.setName("advancedButton");
        advancedButton.addMouseListener(newMyMouseAdapter);
        buttonGridPanel.add(advancedButton);
       
        this.setLayout(new BorderLayout());
        
        Icon newBgImageIcon = new ImageIcon("src/intermediateBg.png");
        this.setIcon(newBgImageIcon);
        
        this.add(buttonGridPanel, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(100,80)), BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(100,80)), BorderLayout.SOUTH);
        this.add(Box.createRigidArea(new Dimension(100,100)), BorderLayout.EAST);
        this.add(Box.createRigidArea(new Dimension(100,100)), BorderLayout.WEST);
    }
    
    public void launchMainMenuGUI()
    {   
        this.parentFrame.add(this);
        this.parentFrame.validate();
        this.parentFrame.repaint();
    }
}
