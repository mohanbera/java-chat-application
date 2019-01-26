package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Controller
{
    @FXML
    private AnchorPane stage2;

    @FXML
    private ToggleButton crossBtn1;

    @FXML
    private TextField loginTextFUsername1;

    @FXML
    private PasswordField loginTextFPassWord11;

    @FXML
    private Button btn2;

    @FXML
    private Text loginLabel1;

    @FXML
    void loginActionBtn2(ActionEvent event) throws IOException
    {
        System.exit(0);
    }

    @FXML
    void loginActionBtn1(ActionEvent event) throws IOException
    {

        String userName1=loginTextFUsername1.getText();
        String passWord1=loginTextFPassWord11.getText();
        loginLabel1.setText("Please wait for a while......");
        System.out.print(userName1+" "+passWord1+"\n");

        boolean access1=false;
        sampleController sampleController1=new sampleController();
        try
        {
            access1=sampleController1.init(event,userName1,passWord1);
        } catch (SQLException e)
        {
            System.out.print("prb4:- problem with sql connection\n");
        }
        if(!access1)
        {
            loginLabel1.setText("Invalid username or password\n");
        }

    }
}
