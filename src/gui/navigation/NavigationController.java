package gui.navigation;

import gui.acc_card.AccCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Account;

public class NavigationController {

    // ===========================
    //   OPEN IN NEW WINDOW (NO CLOSE)
    // ===========================
    public static void openInNewWindow(String fxmlPath, String title, Runnable onClose) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(false);       // 🔥 DISABLE RESIZE
            stage.setScene(new Scene(root));
            stage.show();

            if (onClose != null) {
                stage.setOnHiding(event -> onClose.run());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===========================
    //   OPEN, CLOSE CURRENT
    // ===========================
    public static void openInNewWindow(Stage currentStage, String fxmlPath, String title, Runnable onOpen) {
        try {
            if (currentStage != null) {
                currentStage.close();
            }

            FXMLLoader loader = new FXMLLoader(NavigationController.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setTitle(title);
            newStage.setResizable(false);  // 🔥 DISABLE RESIZE
            newStage.setScene(new Scene(root));
            newStage.show();

            if (onOpen != null) {
                onOpen.run();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===========================
    //   OPEN NEW PAGE + CLOSE OLD
    // ===========================
    public static void openNewPageAndClosePrevious(Node nodeInCurrentWindow, String fxmlPath, String title) {
        Stage oldStage = (Stage) nodeInCurrentWindow.getScene().getWindow();

        openInNewWindow(oldStage, fxmlPath, title, () -> {
            // Callback — trenutno ne radi ništa i to je dobro.
        });
    }

    public static void openNewPageAndClosePrevious2(Node nodeInCurrentWindow, String fxmlPath, String title) {
        Stage oldStage = (Stage) nodeInCurrentWindow.getScene().getWindow();
        openInNewWindow(oldStage, fxmlPath, title, null);
    }

    // ===========================
    //   LOAD ACCOUNT CARD
    // ===========================
    public static Parent loadAccountCard(String accName, String accNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationController.class.getResource("/gui/acc_card/AccCard.fxml")
            );

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
            FXMLLoader loader = new FXMLLoader(
                    NavigationController.class.getResource("/gui/acc_card/AccCard.fxml")
            );

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
