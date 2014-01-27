
import javax.swing.*;

public class GUIInterface extends JFrame {
    
    public GUIInterface() 
    {  
           
       // Make the window visible.
       this.setVisible(true);
        
       // Set title of window.
       this.setTitle("Sudoku Game");
       
       // Set size of the window.
       this.setSize(500, 500);
       
       // Set window to appear at centre of screen.
       this.setLocationRelativeTo(null);
       
       // Disable window resizing.
       this.setResizable(false);
       
       // Exit program on window close.
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }

}