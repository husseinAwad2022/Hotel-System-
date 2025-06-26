package application;
import java.io.IOException;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;


import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;

public class SampleController implements Initializable  {
	@FXML
    private TextField txt_name;

    @FXML
    private TextField txt_passwrad;

    @FXML
    private TextField txt_age;

    @FXML
    private TextField txt_phone;

    @FXML
    private TextField txt_gender;

    @FXML
    private TextField txt_id;
	
    @FXML
    private TableView<employee> table;

	@FXML
    private TableColumn<employee, Integer> id;

    @FXML
    private TableColumn<employee, String> name;

    @FXML
    private TableColumn<employee, Integer> passward;

    @FXML
    private TableColumn<employee, String> gender;

    @FXML
    private TableColumn<employee, Integer> phone;

    @FXML
    private TableColumn<employee, Integer> age;
    
    
    ObservableList<employee> listM;
    ObservableList<employee> dataList;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
  
    
    public void Add_users (){    
        conn = sqlconnect.ConnectDb();
        String sql = "insert into employee (EID,EPASSWARD,ENMAE,EGENDER,EPHONENO,EAGE)values(?,?,?,?,?,? )";
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt( txt_id.getText()));
            pst.setInt(2, Integer.parseInt(txt_passwrad.getText()));
            pst.setInt(6, Integer.parseInt(txt_age.getText()));
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_gender.getText());
            pst.setInt(5, Integer.parseInt(txt_phone.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
          //  search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(id.getCellData(index).toString());
    txt_passwrad.setText(passward.getCellData(index).toString());
    txt_name.setText(name.getCellData(index).toString());
    txt_gender.setText(gender.getCellData(index).toString());
    txt_phone.setText(phone.getCellData(index).toString());
    txt_age.setText(age.getCellData(index).toString());
    
    }
    
    public void Edit (){   
    	conn = sqlconnect.ConnectDb();
    	
        String sql = "update  hotelsystem.employee set EPASSWARD=?,ENMAE=?,EGENDER=?,EPHONENO=?,EAGE=? where  EID=?";
        try {
        	
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txt_passwrad.getText()));
            pst.setString(2, txt_name.getText());
            pst.setString(3, txt_gender.getText());
            pst.setInt(4, Integer.parseInt(txt_phone.getText()));
            pst.setInt(5, Integer.parseInt(txt_age.getText()));
            pst.setInt(6,Integer.parseInt( txt_id.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "User Update  succes");
            UpdateTable();
          //  search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
    
    
    public void Delete(){
        conn = sqlconnect.ConnectDb();
        String sql = "delete from employee where EID = ?";
            try {
                pst = (PreparedStatement) conn.prepareStatement(sql);
                System.out.println(id.getCellData(0));
                pst.setInt(1,Integer.parseInt( txt_id.getText()));
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete");
                UpdateTable();
               // search_user();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    
    public void UpdateTable(){
    	/*id.setCellValueFactory(new PropertyValueFactory<employee,Integer>("EID"));
        name.setCellValueFactory(new PropertyValueFactory<employee,String>("ENMAE"));
        passward.setCellValueFactory(new PropertyValueFactory<employee,Integer>("EPASSWARD"));
        age.setCellValueFactory(new PropertyValueFactory<employee,Integer>("EAGE"));
        gender.setCellValueFactory(new PropertyValueFactory<employee,String>("EGENDER"));
        phone.setCellValueFactory(new PropertyValueFactory<employee,Integer>("EPHONENO"));
        */
        listM = sqlconnect.getDatausers();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        passward.setCellValueFactory(new PropertyValueFactory<>("passward"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table.setItems(listM);
        
    }

   
   

    
@Override
public void initialize(URL url, ResourceBundle rb) {
	
	UpdateTable();

} 
		
	}