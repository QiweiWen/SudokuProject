

public class Main {
    
    public static void main(String[] args) 
    {
        
                GUIInterface newGUIInterface = new GUIInterface();
                
                MainMenuGUI newMainMenuGUI = new MainMenuGUI(newGUIInterface);
                newMainMenuGUI.launchMainMenuGUI();
                
                
//                GameBoardGUI newGameBoardGUI = new GameBoardGUI(newGUIInterface);
//                newGameBoardGUI.loadGameBoardGUI(GameDifficulty.BEGINNER);
//                
//                newGameBoardGUI.setSolidGridCellValue(0, 1, 6);
//                newGameBoardGUI.setSolidGridCellValue(8, 5, 1);
//                newGameBoardGUI.setSolidGridCellValue(4, 4, 3);
//                
//                newGameBoardGUI.launchGameBoardGUI();
                
                
//                ResultsGUI newResultsGUI = new ResultsGUI(newGUIInterface, ResultType.FAILURE);
//                newResultsGUI.launchResultsGUI();
                
    }
    
    public static Game g;
}
