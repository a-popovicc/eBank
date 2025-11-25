package gui.navigation;

import gui.acc_card.AccCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Account;

public class NavigationController {

    public static void openInNewWindow(String fxmlPath, String title, Runnable onClose) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

            // callback kada se zatvori
            stage.setOnHiding(event -> {
                if (onClose != null) {
                    onClose.run();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void openInNewWindow(Stage currentStage, String fxmlPath, String title, Runnable onOpen) {
        try {

            // zatvori trenutni stage
            if (currentStage != null) {
                currentStage.close();
            }

            // učitaj FXML
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlPath));
            Parent root = loader.load();

            // otvori novi stage
            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setScene(new Scene(root));
            newStage.show();

            // OVDE se odmah izvršava callback
            if (onOpen != null) {
                onOpen.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void openNewPageAndClosePrevious(Node nodeInCurrentWindow,  String fxmlPath,  String title){

        Stage oldStage = (Stage) nodeInCurrentWindow.getScene().getWindow();

        openInNewWindow(
                oldStage,
                fxmlPath,
                title,
                () -> {
                    // ZATVARA SVE PRETHODNE PROZORE
                    Stage.getWindows().stream()
                            .filter(w -> w instanceof Stage)
                            .forEach(w -> {
                                if (w.isShowing()) w.hide();
                            });
                }
        );

    }
    public static void openNewPageAndClosePrevious2(Node nodeInCurrentWindow,  String fxmlPath,  String title){

        Stage oldStage = (Stage) nodeInCurrentWindow.getScene().getWindow();

        openInNewWindow(oldStage, fxmlPath, title, null);
    }

    public static Parent loadAccountCard(String accName, String accNumber) {
        try {
            FXMLLoader loader = new FXMLLoader( NavigationController.class.getResource("/gui/acc_card/AccCard.fxml"));

            Parent card = loader.load();
            AccCardController controller = loader.getController();

            controller.setAccountData(accName, accNumber);


            return card;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Parent loadEmptyAccountCard(Account account) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource("/gui/acc_card/AccCard.fxml") );

            Parent card = loader.load();

            AccCardController controller = loader.getController();

            controller.setAccountData(account.getName(), account.getAccountNumber());


            return card;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}







