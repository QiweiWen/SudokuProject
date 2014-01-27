import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;


public class MyGBAssistMouseAdapter extends MouseAdapter {
    
    private GUIInterface gUIInterfaceJFrame;
    private GameBoardGUI gameBoardJPanel;
    private JLabel gridJLabel;
    
    public MyGBAssistMouseAdapter(GUIInterface aParentJFrame, GameBoardGUI aGameBoardJPanel, JLabel aGridJLabel)
    {
        this.gUIInterfaceJFrame = aParentJFrame;
        this.gameBoardJPanel = aGameBoardJPanel;
        this.gridJLabel = aGridJLabel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        // Take off highlight.
        this.gridJLabel.setBackground(Color.WHITE);
        
        // Getting grid coordinates.
        String[] part = this.gridJLabel.getName().split(",");
        int xPos = Integer.parseInt(part[0]);
        int yPos = Integer.parseInt(part[1]);

        // put in hint from back end, new code here!
        // Set Value of grid cell.
        int val = Main.g.hint(xPos, yPos);
        System.out.println(val);
        this.gameBoardJPanel.setSolidGridCellValue(xPos, yPos, val);
        
        // update number of assits left, new code here
        // Set value of Remaining tips/assists.
        this.gameBoardJPanel.setRemainingAssists(5);
        
    }
}
