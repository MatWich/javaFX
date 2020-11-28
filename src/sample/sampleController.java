package sample;

import code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.TabableView;
import java.io.*;
import java.net.URL;
import java.util.*;

public class sampleController implements Initializable {

    @FXML
    private TableView<CarShowroom> CStableView;
    @FXML
    private TableColumn<CarShowroom, String> CSNameColumn;

    @FXML
    private TableView<Vehicle> tableView;
    @FXML
    private TableColumn<Vehicle, String> nameColumn;
    @FXML
    private TableColumn<Vehicle, Double> priceColumn;
    @FXML
    private TableColumn<Vehicle, Integer> PYColumn;
    @FXML
    private TableColumn<Vehicle, String> statusColumn;
    @FXML
    private TableColumn<Vehicle, String> salonNameColumn;

    private CarShowroomContainer csc;
    private DataGenerator dg;

    @FXML
    private TextField searchField;
    //@FXML private Tooltip tooltip;

    public void changgeNameCellEvent(TableColumn.CellEditEvent editteCell) {
        Vehicle carSelected = tableView.getSelectionModel().getSelectedItem();
        carSelected.setName(editteCell.getNewValue().toString());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File f = new File("data.csv");
        /*ObjectOutputStream dos;
        try (FileOutputStream fos = new FileOutputStream(f)) {
            this.dg = new DataGenerator();
            this.csc = dg.createSalonContainer();
            dos = new ObjectOutputStream(fos);
            dos.writeObject(this.csc);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            FileInputStream dis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(dis);
            this.csc = (CarShowroomContainer) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //tooltip.setText("Dzialaj dziadostwie");
        // tabelka CSRC
        CSNameColumn.setCellValueFactory(new PropertyValueFactory<CarShowroom, String>("name"));

        // tabelka Vehicle
        nameColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("Name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Double>("Price"));
        PYColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("prod_year"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("status"));
        salonNameColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("salonName"));

        // mock dummy data
        List<CarShowroom> salonyList = csc.getSalonyList();
        CStableView.setItems(getAllCarShowRooms(salonyList));

        //tableView.setItems(getCars());
        tableView.setItems(getCarsFromSalons(salonyList));
        // allow editing table
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.createTooltips();///////////////

        // wieloktorne zaznaczenie
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }


    // change Scene
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {
        Parent koszykViewParent = FXMLLoader.load(getClass().getResource("koszyk.fxml"));
        Scene koszykViewScene = new Scene(koszykViewParent);

        // this line get the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(koszykViewScene);
        window.show();
    }

    // Remove car
    public void kupBtnPushed() {
        ObservableList<Vehicle> selectedRows, allCars;
        allCars = tableView.getItems();

        // zaznaczone tylko
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        ObservableList<CarShowroom> shows = CStableView.getSelectionModel().getSelectedItems();
        CarShowroom cs1 = shows.get(0);



        for (Vehicle veh : selectedRows) {
            if (veh.getStatus() != "Rez") {
                allCars.remove(veh);


                    for (CarShowroom cs : csc.getSalonyList())
                            if (cs.getVehicles().contains(veh))
                                cs.getVehicles().remove(veh);

                //tableView.setItems(getCarsFromSalons(csc.getSalonyList()));
                tableView.refresh();


            } else {
                System.out.println("ten szamochod jest zarezerwowany :(");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("ten szamochod jest zarezerwowany :(");
                alert.setTitle("Tez bym chcial kupic ale ktos zarezerwowal :(");
                alert.setHeaderText("Nie kupisz tego oj nie nie");

                ButtonType cancelButtonType = new ButtonType("No dobra", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getDialogPane().getButtonTypes().add(cancelButtonType);
                alert.showAndWait();
            }
        }

    }

    // rezerwacja
    public void zarBtnPushed() {
        ObservableList<Vehicle> selectedRows, allCars;
        allCars = tableView.getItems();

        // zaznaczone tylko
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Vehicle veh : selectedRows) {
            allCars.remove(veh);
            veh.setStatus("Rez");
            allCars.add(veh);
            tableView.refresh();
        }
    }

    // Onclick for selected carshowRoom
    public void showCarsBtnPushed() {
        ObservableList<CarShowroom> selectedRow;
        selectedRow = CStableView.getSelectionModel().getSelectedItems();
        CarShowroom cs = selectedRow.get(0);

        tableView.setItems(getVehsFromShowRoom(cs));
        this.createTooltips();///////////////

    }

    // MA ZCZYTYWAC WSZYSTKIE SAMOCHODY NA POCZATEK
    public ObservableList<Vehicle> getCars() {
        ObservableList<Vehicle> cars = FXCollections.observableArrayList();

        cars.add(new Vehicle("Passat B5", 10430, 19920, 234563, "Dost"));
        cars.add(new Vehicle("Passat B4", 10320, 1992, 23, "Rez"));
        cars.add(new Vehicle("Passat B3", 100, 1992, 23312, "Dost"));
        cars.add(new Vehicle("Passat B2", 10430, 1992, 234, "Dost"));
        cars.add(new Vehicle("Passat B1", 43100, 1992, 2323, "Dost"));

        return cars;

    }

    // zwraca liste wszystkich samochodow we wszystkich salonach
    public ObservableList<Vehicle> getCarsFromSalons(List<CarShowroom> data) {
        ObservableList<Vehicle> cars = FXCollections.observableArrayList();

        for (CarShowroom cs : data) {
            //for (Vehicle veh : cs.getVehicles())
            // cars.add(veh);
            cars.addAll(cs.getVehicles());
        }

        return cars;
    }


    // zwraca dostepne szalony z szamochodami
    public ObservableList<CarShowroom> getAllCarShowRooms(List<CarShowroom> data) {
        ObservableList<CarShowroom> cars = FXCollections.observableArrayList();

        cars.addAll(data);
        return cars;
    }

    public ObservableList<Vehicle> getVehsFromShowRoom(CarShowroom cs) {
        ObservableList<Vehicle> cars = FXCollections.observableArrayList();
        cars.addAll(cs.getVehicles());
        return cars;
    }


    // jak zostanie podany pusty string to wyswietli wyglad domyslny, jak mamy zaznaczony jaki salon to mozemy wpisac nawet jedna litere i wyswietli szamochodzy w tym salonie
    public void search() {
        String lookingFor = searchField.getText();
        List<Vehicle> cars;
        ObservableList<Vehicle> carsToReturn = FXCollections.observableArrayList();
        try {
            if (lookingFor.equalsIgnoreCase("")) {
                List<CarShowroom> salonyList = this.csc.getSalonyList();
                tableView.setItems(getCarsFromSalons(salonyList));
                return;
            }
            ObservableList<CarShowroom> selectedRows;
            selectedRows = CStableView.getSelectionModel().getSelectedItems();
            CarShowroom cs = selectedRows.get(0);

            cars = cs.getVehicles();
            for (Vehicle veh : cars)
                if (veh.getName().contains(lookingFor))
                    carsToReturn.add(veh);

            tableView.setItems(carsToReturn);
            return;


        } catch (Exception e) {

            System.out.println(e);
        }


        for (CarShowroom cs : this.csc.getSalonyList()) {
            cars = cs.getVehicles();
            for (Vehicle veh : cars) {
                if (veh.getName().contains(lookingFor))
                    carsToReturn.add(veh);
            }

        }
        tableView.setItems(carsToReturn);
        tableView.refresh();

    }

    public void createTooltips() {
        tableView.setRowFactory(new Callback<TableView<Vehicle>, TableRow<Vehicle>>() {
            @Override
            public TableRow<Vehicle> call(TableView<Vehicle> param) {
                return new TableRow<Vehicle>() {
                    @Override
                    protected void updateItem(Vehicle veh, boolean b) {
                        super.updateItem(veh, b);
                        if (veh == null) {
                            setTooltip(null);
                        } else {
                            Tooltip tooltip = new Tooltip();
                            String text = "Name: " + veh.getName() + "\nPrice: " + veh.getPrice() + "\nProdduction Year: " + veh.getProd_year() + "\nEngCap: " + veh.getEng_capacity() + "\nWhere: " + veh.getSalonName() + "Status: " + veh.getStan();
                            tooltip.setText(text);
                            setTooltip(tooltip);
                        }
                    }
                };
            }
        });
    }

}
