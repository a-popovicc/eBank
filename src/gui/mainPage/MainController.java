package gui.mainPage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnPayment;

    @FXML
    private BarChart<?, ?> balanceChart; // koristi generičke tipove za X i Y

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // === Providna pozadina BarCharta ===
        balanceChart.setStyle("-fx-background-color: transparent;");

        Node plotBackground = balanceChart.lookup(".chart-plot-background");
        if (plotBackground != null) {
            plotBackground.setStyle("-fx-background-color: transparent;");
        }

        balanceChart.setLegendVisible(false);

        // === Postavljanje slike na dugme (40x40 krug) ===
        Image img = new Image(getClass().getResource("/resources/picture/btnPlacanje.png").toExternalForm());
        ImageView iv = new ImageView(img);
        iv.setFitWidth(40);
        iv.setFitHeight(40);
        iv.setPreserveRatio(true);

        btnPayment.setGraphic(iv);
    }
}


