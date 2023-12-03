package com.example;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import classes.api.JsonDatabaseV2;
import javafx.application.Platform;

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

public class DashboardController implements Initializable{
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
    private ComboBox<?> availableFlowers_status;

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
    private TableColumn<?,?> purchase_col_flowerID;

    @FXML
    private TableColumn<?,?> purchase_col_flowerName;

    @FXML
    private TableColumn<?,?> purchase_col_price;

    @FXML
    private TableColumn<?,?> purchase_col_quantity;

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
    private TableView<?> purchase_tableView;

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
        // slip the id to get the prefix which is before "_"
        String[] prefix = elementId.split("_");
        
        // now we just need to add "_btn" to the prefix to get the button id
        String btnIdToShow = prefix[0] + "_btn";
        // make change to button that interact with the form
        // since the button is in the main_form so we need to get the button from main_form
        Button selectBtn = (Button) main_form.lookup("#" + btnIdToShow); // lookup() method is used to find the node with the specified CSS selector.
        
        for (Button btn : new Button[]{home_btn, availableFlowers_btn, purchase_btn}) {
            btn.setStyle("-fx-background-color: transparent");
        }

        for(AnchorPane form: new AnchorPane[]{home_form, availableFlowers_form, purchase_form}){
            form.setVisible(false);
        }
        selectBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #d3133d, #a4262f)");
        clickForm.setVisible(true);


        // showButtStyle(selectBtn);
    }    
        // // // 🤣 when you hate ur life
        // private void handleButtonClick(AnchorPane clickedForm) {
        //     // Hide all forms
        //     for (AnchorPane form : new AnchorPane[]{home_form, availableFlowers_form, purchase_form}) {
        //         form.setVisible(false);
        //     }
    
        //     // Show the form associated with the clicked button
        //     showForm(clickedForm);

        // }
    
        // private void showForm(AnchorPane form) {
        //     if (form != null) {
        //         form.setVisible(true);
        //     }
        // }
        
        // // change button style
        // private void showButtStyle(Button clickedBtn) {
        //     for (Button btn : new Button[]{home_btn, availableFlowers_btn, purchase_btn}) {
        //         btn.setStyle("-fx-background-color: transparent");
        //     }
        //     clickedBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #d3133d, #a4262f)");
        // }

    // method
    String listStatus[] = {"In Stock", "Out of Stock"};
    // Show status in selection box
    public void availableFlowersStatus() {
        List<String> statusList = new ArrayList<>();
        for(String status: listStatus){
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
        getData.imagePath = "";
    }
    // add data to db
    public void availableFlowersAdd() {
        // get data from text field
        String flowerId = availableFlowers_flowerID.getText();
        String flowerName = availableFlowers_flowerName.getText();
        String status = (String)availableFlowers_status.getSelectionModel().getSelectedItem();
        String price = availableFlowers_price.getText();
        String image_path = getData.imagePath;

        // formate json data to java object
        // just call JsonDatabaseV2 to fetch data from db_path
        JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>("src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class); 
        try{
            Alert alert;
            // check if input is empty
            if(flowerId.isEmpty() || flowerName.isEmpty() || status == null || price.isEmpty() || image_path == ""){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields!");
                alert.showAndWait();
            }else{
                // Check if the flowerId already exists
                if(flowersDb.checkEntity("flowerId", flowerId).isPresent()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Flower ID: " + flowerId  + " was already exists!");
                    alert.showAndWait();
                }else{
                    // since cannot get date from datepicker so we need to get it from java 
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    // push data to db
                    FlowersData newFlower = new FlowersData(Integer.parseInt(flowerId), flowerName, status, Double.parseDouble(price), date.format(formatter), image_path);
                    flowersDb.addEntity(newFlower);
                    
                    // Refresh table view or repopulate table view
                    availableFlowersShowListData();
                  /*   alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Add data successfully!");
                    alert.showAndWait() */;
                    
                    // clear data in form
                    availableFlowersClear();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
    // Insert Image
    private Image image;
    public void availableFlowersInsertImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if(file != null){
            getData.imagePath = file.getAbsolutePath();
            image = new Image(file.toURI().toString(),116,148, false , true);
            availableFlowers_imageView.setImage(image);
        }
    }
    // Formate our data to display in table view
    public ObservableList<FlowersData> availableFlowersListData(){
        ObservableList<FlowersData> flowersList = FXCollections.observableArrayList();
        try {
            // formate json data to java object
            JsonDatabaseV2<FlowersData> flowersDb = new JsonDatabaseV2<>("src/main/resources/com/example/data/stock/FlowersDb.json", FlowersData.class); 
            // store data to list then push it to observable list
            List<FlowersData> loadedData = flowersDb.getEntityList(); 
            // List<FlowersData> loadedData = new GetFlowersData().getFlowersList(); 
            // System.out.println(loadedData);
            // for(FlowersData flower: flowersDb.getEntityList()){
            //     flowersList.add(flower);
            // }
            // don't want to use loop
            flowersList.addAll(loadedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flowersList;
    }

    
    private ObservableList<FlowersData> availableFlowersList;
    public void availableFlowersShowListData(){
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

    //// Home Form
    // display username
    public void displayUsername() {
        String user = getData.username;
        username.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }
    // switch form in dashboard 
    public void switchForm(ActionEvent event){
        
       
        // // or want to be more crazy but cool 😎
        // home_btn.setOnAction(e -> handleButtonClick(home_form));
        // availableFlowers_btn.setOnAction(e -> handleButtonClick(availableFlowers_form));
        // purchase_btn.setOnAction(e -> handleButtonClick(purchase_form));
        
        // //old but gold 👌
        // if(event.getSource() == home_btn){
        //     justSwitch(home_form);
        // }else if(event.getSource() == availableFlowers_btn){
        //     justSwitch(availableFlowers_form);
        // }else if(event.getSource() == purchase_btn){
        //     justSwitch(purchase_form);
        // }
        
        // why else-if when you can do these 😎
        home_btn.setOnAction(e -> justSwitch(home_form));
        availableFlowers_btn.setOnAction(e -> {
            availableFlowersShowListData();
            availableFlowersStatus();
            justSwitch(availableFlowers_form);
        });
        purchase_btn.setOnAction(e -> justSwitch(purchase_form));
        

    }
    

    //// Part of Main form
    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get() == ButtonType.OK) {
                // Loading LOGIN Form
                // Hide the current window (login form)
                // loginBtn.getScene().getWindow().hide();
                
                // no-hide just close
                Window window = logoutBtn.getScene().getWindow();
                ((Stage) window).close();
                App.setRoot("Login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }
    
    public void minimize() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        displayUsername();
        availableFlowersShowListData();
        availableFlowersStatus();
    };
}
