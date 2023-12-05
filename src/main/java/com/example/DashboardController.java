package com.example;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;
import classes.api.JsonDatabaseV2;
import javafx.application.Platform;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button availableFlowers_addBtn;

    @FXML
    private Button availableFlowers_btn;

    @FXML
    private Button availableFlowers_clearBtn;

    @FXML
    private TableColumn<FlowersData, String> availableFlowers_col_flowerID;

    @FXML
    private TableColumn<FlowersData, String> availableFlowers_col_flowerName;

    @FXML
    private TableColumn<FlowersData, String> availableFlowers_col_price;

    @FXML
    private TableColumn<FlowersData, String> availableFlowers_col_status;

    @FXML
    private Button availableFlowers_deleteBtn;

    @FXML
    private TextField availableFlowers_flowerID;

    @FXML
    private TextField availableFlowers_flowerName;

    @FXML
    private AnchorPane availableFlowers_form;

    @FXML
    private ImageView availableFlowers_imageView;

    @FXML
    private Button availableFlowers_importBtn;

    @FXML
    private TextField availableFlowers_price;

    @FXML
    private TextField availableFlowers_search;

    @FXML
    private ComboBox<String> availableFlowers_status;

    @FXML
    private TableView<FlowersData> availableFlowers_tableView;

    @FXML
    private Button availableFlowers_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Label home_availableFlowers;

    @FXML
    private Button home_btn;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalCustomers;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button minimize;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_flowerID;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_flowerName;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_price;

    @FXML
    private TableColumn<CustomerData, String> purchase_col_quantity;

    @FXML
    private ComboBox<?> purchase_flowerID;

    @FXML
    private ComboBox<?> purchase_flowerName;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private Spinner<?> purchase_quantity;

    @FXML
    private TableView<CustomerData> purchase_tableView;

    @FXML
    private Label purchase_total;

    @FXML
    private Button purchase_btn;

    @FXML
    private Label username;

    // justSome Switch 🤓
    private void justSwitch(AnchorPane clickForm) {
        // since I name the button with the same name as the form (not include "_*")
        String elementId = clickForm.getId();
        // slice the id to get the prefix which before "_"
        String[] prefix = elementId.split("_");

        // now we just need to add "_btn" to the prefix to set the button id 
        String btnIdToShow = prefix[0] + "_btn";
        // make change to button that interact with the form
        // since the button is locate on main_form so we need to get the button id from it which is .fxml id=(#buttonId)
        // main_form
        Button selectBtn = (Button) main_form.lookup("#" + btnIdToShow); // lookup() method is used to find the node
                                                                         // with the specified CSS selector.

        for (Button btn : new Button[] { home_btn, availableFlowers_btn, purchase_btn }) {
            btn.setStyle("-fx-background-color: transparent");
        }

        for (AnchorPane form : new AnchorPane[] { home_form, availableFlowers_form, purchase_form }) {
            form.setVisible(false);
        }
        selectBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #d3133d, #a4262f)");
        clickForm.setVisible(true);

        // showButtStyle(selectBtn);
    }
    // // // 🤣 when you hate ur life
    // private void handleButtonClick(AnchorPane clickedForm) {
    // // Hide all forms
    // for (AnchorPane form : new AnchorPane[]{home_form, availableFlowers_form,
    // purchase_form}) {
    // form.setVisible(false);
    // }

    // // Show the form associated with the clicked button
    // showForm(clickedForm);

    // }

    // private void showForm(AnchorPane form) {
    // if (form != null) {
    // form.setVisible(true);
    // }
    // }

    // // change button style
    // private void showButtStyle(Button clickedBtn) {
    // for (Button btn : new Button[]{home_btn, availableFlowers_btn, purchase_btn})
    // {
    // btn.setStyle("-fx-background-color: transparent");
    // }
    // clickedBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,
    // #d3133d, #a4262f)");
    // }

    // method

    //// Available Flowers Form

    // delete data
    public void availableFlowersDelete() {
        // get data from text field
        String flowerId = availableFlowers_flowerID.getText();
        String flowerName = availableFlowers_flowerName.getText();
        // String status =
        // (String)availableFlowers_status.getSelectionModel().getSelectedItem();
        String price = availableFlowers_price.getText();
        String image_path = getData.imagePath;
        // loading flowersDb form json file
        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
        try {
            Alert alert;
            if (flowerId.isEmpty() || flowerName.isEmpty() || price.isEmpty() || image_path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Select data from table to delete!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Flower ID: " + flowerId + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    // delete data
                    flowersDb.deleteEntity("flowerId", flowerId);
                    availableFlowersListData();
                    availableFlowersClear();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Data Successfully DELETE!");
                    alert.showAndWait();

                    // Show updated data
                    availableFlowersShowListData();

                    /// Clear all field
                    availableFlowersClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update data
    public void availableFlowersUpdate() {
        // get data from text field
        String flowerId = availableFlowers_flowerID.getText();
        String flowerName = availableFlowers_flowerName.getText();
        String status = (String) availableFlowers_status.getSelectionModel().getSelectedItem();
        String price = availableFlowers_price.getText();
        String image_path = getData.imagePath;
        // loading flowersDb form json file
        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
        try {
            Alert alert;
            if (flowerId.isEmpty() || flowerName.isEmpty() || status == null || price.isEmpty() || image_path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Flower ID: " + flowerId + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    // update data
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    FlowersData updateFlower = new FlowersData(Integer.parseInt(flowerId), flowerName, status,
                            Double.parseDouble(price), date.format(formatter), image_path);
                    flowersDb.updateEntity(updateFlower, "flowerId", flowerId);
                    availableFlowersListData();
                    availableFlowersClear();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update data successfully!");
                    alert.showAndWait();

                    // Show updated data
                    availableFlowersShowListData();

                    /// Clear all field
                    availableFlowersClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // onSelect table element show info
    public void availableFlowerSelect() {
        FlowersData selectedFlower = availableFlowers_tableView.getSelectionModel().getSelectedItem();
        int num = availableFlowers_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1)
            return;

        availableFlowers_flowerID.setText(String.valueOf(selectedFlower.getFlowerId()));
        availableFlowers_flowerName.setText(selectedFlower.getFlowerName());
        availableFlowers_price.setText(String.valueOf(selectedFlower.getPrice()));

        getData.imagePath = selectedFlower.getImage();
        String uri = "file:" + selectedFlower.getImage();
        image = new Image(uri, 116, 148, false, true);
        availableFlowers_imageView.setImage(image);

    }

    String listStatus[] = { "In Stock", "Out of Stock" };

    // Show status in selection box
    public void availableFlowersStatus() {
        List<String> statusList = new ArrayList<>();
        for (String status : listStatus) {
            statusList.add(status);
        }

        // convert list to observable list so javafx can use it
        ObservableList listData = FXCollections.observableArrayList(statusList);
        availableFlowers_status.setItems(listData);

    }

    // clear data in form
    public void availableFlowersClear() {
        availableFlowers_flowerID.setText("");
        availableFlowers_flowerName.setText("");
        availableFlowers_status.getSelectionModel().clearSelection();
        availableFlowers_price.setText("");
        availableFlowers_imageView.setImage(null);
        // getData.imagePath = "";
        getData.imagePath = null; // so after clear we still could push data in even we didn't choose image
    }

    // add data to db
    public void availableFlowersAdd() {
        // get data from text field
        String flowerId = availableFlowers_flowerID.getText();
        String flowerName = availableFlowers_flowerName.getText();
        String status = (String) availableFlowers_status.getSelectionModel().getSelectedItem();
        String price = availableFlowers_price.getText();
        String image_path = getData.imagePath;

        // formate json data to java object
        // just call JsonDatabaseV2 to fetch data from db_path
        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
        try {
            Alert alert;
            // check if input is empty
            if (flowerId.isEmpty() || flowerName.isEmpty() || status == null || price.isEmpty() || image_path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields!");
                alert.showAndWait();
            } else {
                // Check if the flowerId already exists
                if (flowersDb.checkEntity("flowerId", flowerId).isPresent()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Flower ID: " + flowerId + " was already exists!");
                    alert.showAndWait();
                } else {
                    // since cannot get date from datepicker so we need to get it from java
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    // push data to db
                    FlowersData newFlower = new FlowersData(Integer.parseInt(flowerId), flowerName, status,
                            Double.parseDouble(price), date.format(formatter), image_path);
                    flowersDb.addEntity(newFlower);

                    // Refresh table view or repopulate table view
                    availableFlowersShowListData();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Add data successfully!");
                    alert.showAndWait();

                    // clear data in form
                    availableFlowersClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert Image
    private Image image;

    public void availableFlowersInsertImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.imagePath = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 116, 148, false, true);
            availableFlowers_imageView.setImage(image);
        }
    }

    // Formate our data to display in table view
    public ObservableList<FlowersData> availableFlowersListData() {
        ObservableList<FlowersData> flowersList = FXCollections.observableArrayList();
        try {
            // formate json data to java object
            JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                    "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
            // store data to list then push it to observable list
            List<FlowersData> loadedData = flowersDb.getEntityList();
            // List<FlowersData> loadedData = new GetFlowersData().getFlowersList();
            // System.out.println(loadedData);
            // for(FlowersData flower: flowersDb.getEntityList()){
            // flowersList.add(flower);
            // }
            // don't want to use loop
            flowersList.addAll(loadedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowersList;
    }

    private ObservableList<FlowersData> availableFlowersList;

    public void availableFlowersShowListData() {
        availableFlowersList = availableFlowersListData();
        // System.out.println(availableFlowersList.getClass());
        Platform.runLater(() -> {

            availableFlowers_col_flowerID.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
            availableFlowers_col_flowerName.setCellValueFactory(new PropertyValueFactory<>("flowerName"));
            availableFlowers_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            availableFlowers_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

            availableFlowers_tableView.setItems(availableFlowersList);

        });
    }
    // Search Data in Table
    public void availableFlowersSearch() {
        FilteredList<FlowersData> filteredData = new FilteredList<>(availableFlowersList, e -> true);

        availableFlowers_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(flowerData -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true; // Display all records if the search value is empty
                }

                String searchKey = newValue.toLowerCase();

                // Perform case-insensitive comparisons
                // return the matched data
                return flowerData.getFlowerId().toString().toLowerCase().contains(searchKey) ||
                        flowerData.getFlowerName().toLowerCase().contains(searchKey) ||
                        flowerData.getPrice().toString().toLowerCase().contains(searchKey) ||
                        flowerData.getStatus().toLowerCase().contains(searchKey);
            });
            // it constantly check if the search field is empty or not
            // Use the existing SortedList if available, otherwise create a new one
            SortedList<FlowersData> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(availableFlowers_tableView.comparatorProperty());
            availableFlowers_tableView.setItems(sortedData);
        });
    }

    //// Purchase Form
    public void purchaseFlowerId(){
        // loading FlowersData form flowersDb json file
        purchase_flowerName.getSelectionModel().clearSelection(); //  clear it to avoid error

        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
        // load filter only status "In Stock"
        List<FlowersData> loadedData = flowersDb.getEntityList();
        // filter only status "In Stock"
            try{
                ObservableList listData = FXCollections.observableArrayList();
                // push data to table via observable list 
                // filter only flowerId with status "In Stock"
                loadedData.stream()
                    .filter(flower -> flower.getStatus().equals("In Stock")).collect(Collectors.toList())
                    .forEach(flower -> {
                        listData.add(flower.getFlowerId());
                    });
                purchase_flowerID.setItems(listData);

                // set flag to avoid error when flowerId of selection not selected which is nulll
                if(purchase_flowerID.getSelectionModel().getSelectedItem() != null)
                     purchaseFlowerName();
                // purchase_flowerID.getSelectionModel().selectFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
   
    }
    
    public void purchaseFlowerName(){
        // loading FlowersData form flowersDb json file
        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>(
                "src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class);
        // load filter only status "In Stock"
        // List<FlowersData> loadedData = flowersDb.getEntityList();
        
            try{
                ObservableList listData = FXCollections.observableArrayList();
                // get flowerId form selection box
                int flowerId = (int) purchase_flowerID.getSelectionModel().getSelectedItem();
                System.out.println(flowerId);
                // push data to table via observable list 
                // fetch FlowerName associated with flowerId
                flowersDb.getEntityList().stream()
                    .filter(flower -> flower.getFlowerId() == flowerId).collect(Collectors.toList())
                    .forEach(flower -> {
                        listData.add(flower.getFlowerName());
                    });
                purchase_flowerName.setItems(listData);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public ObservableList<CustomerData> purchaseListData(){
        purchaseCustomerId();
        ObservableList<CustomerData> purchaseList = FXCollections.observableArrayList();
        // load data so we could use it to display in table view
        JsonDatabaseV2<CustomerData> purchaseDb = new JsonDatabaseV2<>("src/main/resources/com/example/data/purchase/CustomerDb.json", CustomerData.class);
        try {
            List<CustomerData> loadedData = purchaseDb.getEntityList();
            // filter only if customerId is matched
            loadedData.stream()
                .filter(customer -> customer.getCustomerId() == customerId).collect(Collectors.toList())
                .forEach(customer -> {
                    purchaseList.add(customer);
                }); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return purchaseList;
    }
    
   
    // Show data in table view
    private ObservableList<CustomerData> purchaseList;
    public void purchaseShowListData(){
        purchaseList = purchaseListData();
        Platform.runLater(() -> {
            purchase_col_flowerID.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
            purchase_col_flowerName.setCellValueFactory(new PropertyValueFactory<>("flowerName"));
            purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            purchase_tableView.setItems(purchaseList);
        });
    }
    
    private int customerId;
    public void purchaseCustomerId(){
        try {
            // On purchase we will generate new customer id
            // loading Customer_info form json file which is store the record the number of customer which have been purchase flowers in total 
            JsonDatabaseV2<CustomerInfo> customerDb = new JsonDatabaseV2<>(
                    "src/main/resources/com/example/data/purchase/Customer_info.json", CustomerInfo.class);
            // load the raw data to List
            List<CustomerInfo> customerList = customerDb.getEntityList();
            // fetch the last customer id
            customerId = customerList.get(customerList.size() - 1).getCustomerId();
            // System.out.println(customerId);
            // check to see on Customer_info table is empty or not if empty then set id to 1 else increment id
            if (customerList.size() == 0) { // if our List is empty then set id to 1 
                customerId = 1;
            } else {
                customerId++;
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    //// Home Form
    // display username
    public void displayUsername() {
        String user = getData.username;
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    // switch form in dashboard
    public void switchForm(ActionEvent event) {

        // //old but gold 👌
        // if(event.getSource() == home_btn){
        // justSwitch(home_form);
        // }else if(event.getSource() == availableFlowers_btn){
        // justSwitch(availableFlowers_form);
        // }else if(event.getSource() == purchase_btn){
        // justSwitch(purchase_form);
        // }

        // why else-if when you can do these 😎 (Lambda Expressions)
        home_btn.setOnAction(e -> justSwitch(home_form));
        availableFlowers_btn.setOnAction(e -> {
            justSwitch(availableFlowers_form);
            availableFlowersShowListData();
            availableFlowersStatus();
            availableFlowersSearch();
        });
        purchase_btn.setOnAction(e -> {
            justSwitch(purchase_form);
            purchaseShowListData();
            purchaseFlowerId();
            // purchaseFlowerName();
        });

    }

    //// Part of Main form
    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                // Loading LOGIN Form
                // Hide the current window (login form)
                // loginBtn.getScene().getWindow().hide();

                // no-hide just close
                Window window = logoutBtn.getScene().getWindow();
                ((Stage) window).close();
                Stage stage = new Stage();
                App.setRoot(stage, "Login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        displayUsername();
        // loading data in table view
        availableFlowersShowListData();
        availableFlowersStatus(); // loading Status in selection box

        purchaseShowListData();
        purchaseFlowerId();
        // purchaseFlowerName();

    };
}
