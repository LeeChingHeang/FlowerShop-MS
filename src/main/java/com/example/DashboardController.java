package com.example;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private TableColumn<?, ?> availableFlowers_col_flowerID;

    @FXML
    private TableColumn<?, ?> availableFlowers_col_flowerName;

    @FXML
    private TableColumn<?, ?> availableFlowers_col_price;

    @FXML
    private TableColumn<?, ?> availableFlowers_col_status;

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
    private TableView<?> availableFlowers_tableView;

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
    private TableColumn<?, ?> purchase_col_flowerID;

    @FXML
    private TableColumn<?, ?> purchase_col_flowerName;

    @FXML
    private TableColumn<?, ?> purchase_col_price;

    @FXML
    private TableColumn<?, ?> purchase_col_quantity;

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
    private void justSwitch(AnchorPane form) {
        home_form.setVisible(false);
        availableFlowers_form.setVisible(false);
        purchase_form.setVisible(false);
        form.setVisible(true);
    }    
        // // 🤣 when you hate life
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

    // method
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
        
        // why else if when you can do this 😎
        home_btn.setOnAction(e -> justSwitch(home_form));
        availableFlowers_btn.setOnAction(e -> justSwitch(availableFlowers_form));
        purchase_btn.setOnAction(e -> justSwitch(purchase_form));
        

    }
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
        // TODO Auto-generated method stub
    };
}
