import javafx.scene.control.Alert;

public class AlertBoxes {

    /**
     * Crea una ventana de alerta
     * @param title
     * @param message
     */

    public static void displayAlertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Crea una ventana de alerta
     * @param title
     * @param message
     */

    public static void displayResultAlertBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}