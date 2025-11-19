package gui.mainPage;

import functionality.logic_controller.LogicController;
import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        iv.setPreserveRatio(true);

        btnPayment.setGraphic(iv);
    }


    @FXML
    public void handlebtnPayment() {
        NavigationController.openNewPageAndClosePrevious2(btnPayment,"/gui/transfer_payment/TransferPayment.fxml","Payment");
    }
}


