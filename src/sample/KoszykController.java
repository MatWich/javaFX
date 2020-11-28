package sample;

import code.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KoszykController implements Initializable {

    private Vehicle selectedVehicle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // change Scene
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {
        Parent koszykViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene koszykViewScene = new Scene(koszykViewParent);

        // this line get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(koszykViewScene);
        window.show();
    }

    public void initData(Vehicle vehicle)
    {

    }
}
