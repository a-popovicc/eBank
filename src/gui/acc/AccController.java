package gui.acc;

import gui.navigation.NavigationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import user.Account;
import user.AccountSession;
import user.User;
import user.UserSession;

import java.net.URL;
import java.util.ResourceBundle;

public class AccController implements Initializable {
    @FXML
    private Label differenceLabel;
    @FXML
    private Label accNuberLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label balanceLabel;
    // koristi generičke tipove za X i Y
    @FXML
    private BarChart<String, Number> balanceChart;

    @FXML
    private Button btnPayment;
    @FXML
    private Button btnBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // === Providna pozadina BarCharta ===
        balanceChart.setStyle("-fx-background-color: transparent;");

        Node plotBackground = balanceChart.lookup(".chart-plot-background");
        if (plotBackground != null) {
            plotBackground.setStyle("-fx-background-color: transparent;");
        }

        balanceChart.setLegendVisible(true);


        Image img = new Image(getClass().getResource("/resources/picture/btnPayment.png").toExternalForm());
        ImageView iv = new ImageView(img);
        iv.setFitWidth(50);
        iv.setFitHeight(50);
        iv.setPreserveRatio(true);
        btnPayment.setGraphic(iv);

        Image img1 = new Image(getClass().getResource("/resources/picture/home.png").toExternalForm());
        ImageView iv1 = new ImageView(img1);
        iv1.setFitWidth(50);
        iv1.setFitHeight(50);
        iv1.setPreserveRatio(true);
        btnBack.setGraphic(iv1);

        Account active= AccountSession.getActiveAccount();
        User activUser= UserSession.getActiveUser();
        if(active!=null && activUser!=null){
            accNuberLabel.setText(active.getAccountNumber());
            userLabel.setText(activUser.getName()+" "+activUser.getSurname());
            balanceLabel.setText(active.balanceToString()+" "+"RSD");

            fillBalanceChart(active);

        }else {
            System.out.println("No active account");
        }
    }

    private void fillBalanceChart(Account account) {

        // Očisti prethodne podatke (ako se reloaduje scena)
        balanceChart.getData().clear();

        double now = account.getBalanceNow();
        double before = account.getBalanceBefore();

        // Pravi seriju podataka
        BarChart.Series<String, Number> series = new BarChart.Series<>();

        series.getData().add(new BarChart.Data<>("Before", before));
        series.getData().add(new BarChart.Data<>("Now", now));

        balanceChart.getData().add(series);
        double diff = now - before;

        if (diff > 0) {
            differenceLabel.setText(String.format("+%.2f", diff));
            differenceLabel.setStyle("-fx-text-fill: #43A047;"); // zelena
        } else if (diff < 0) {
            differenceLabel.setText(String.format("%.2f", diff)); // automatski ima "-"
            differenceLabel.setStyle("-fx-text-fill: #E53935;"); // crvena
        } else {
            differenceLabel.setText("");
        }

    }



    @FXML
    public void handleBtnPayment() {
        NavigationController.openNewPageAndClosePrevious2(btnPayment,"/gui/transfer_payment/TransferPayment.fxml","Payment");
    }
    @FXML
    public void handleBtnBack() {
        NavigationController.openNewPageAndClosePrevious2(btnBack,"/gui/main_page/main.fxml","Main Page");
    }
}


