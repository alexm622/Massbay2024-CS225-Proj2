import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.GridPane;

public class GameButton extends Button {
    private int row;
    private int col;
    private GridPane grid;
    public GameButton(int row, int col, GridPane grid) {
        super(" ");
        this.row = row;
        this.col = col;
        this.grid = grid;
        setPrefSize(50, 50);
        setFont(Font.font("Calibri", FontWeight.BOLD, 14));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public GridPane getGridPane() {
        return grid;
    }
}