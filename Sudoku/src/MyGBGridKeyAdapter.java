import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;


public class MyGBGridKeyAdapter extends KeyAdapter {
    
    private JLabel currentJLabel;
    private JButton assistButton;
    private MouseListener newAssistMouseListener;
    
    public MyGBGridKeyAdapter(JLabel aSelectedJLabel, JButton aAssistButton, MouseListener aNewAssistMouseListener)
    {
        this.currentJLabel = aSelectedJLabel;
        this.assistButton = aAssistButton;
        this.newAssistMouseListener = aNewAssistMouseListener;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        char keyPressed = e.getKeyChar();
        
        if(keyPressed >= '1' && keyPressed <= '9')
        {
            this.currentJLabel.setBackground(Color.WHITE);
            this.currentJLabel.removeKeyListener(this);
            this.assistButton.removeMouseListener(newAssistMouseListener);
            
            // Getting key pressed info from GUI grid.
            String[] part = this.currentJLabel.getName().split(",");
            int xPos = Integer.parseInt(part[0]);
            int yPos = Integer.parseInt(part[1]);
            int valueEntered = keyPressed - 48;
            
            /*
             * Code here to call back-end processing
             * interface function. On success execute
             * the below command.
             */
            
            // Changes JLabel value to entered number.
            this.currentJLabel.setText(" "+keyPressed+" ");
        }
        

    }
    
}
