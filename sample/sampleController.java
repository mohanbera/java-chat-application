package sample;

import com.sun.javafx.geom.Area;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import sun.font.TextLabel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class sampleController
{

    @FXML
    private TextArea chatField4;

    @FXML
    private TextArea chatField1;

    @FXML
    private MenuButton chatFriends1;

    @FXML
    private Text chatText1;

    @FXML
    private ScrollPane scrollpane1;

    @FXML
    private FlowPane flowpane1;


    static boolean start1=false;
    static String query0="";
    static Connection connection=null;
    static ResultSet rs=null;
    static Statement st=null;
    static int myID;
    static int friendID;
    static String matchString="NA";
    static String friendName1="";
    static String userNm1="";
    public static HashMap<String,ArrayList<String[]>> memberCollection=new HashMap<>();
    public static ArrayList<String[]> currentList=new ArrayList<>();
    static int count1=0;


    public static String userName1="";

    @FXML
    public boolean init(ActionEvent event, String userName0, String passWord0) throws SQLException
    {

        boolean bool0=false;

        try
        {
            connection=connectionConfiguration.getConnection();
        } catch (InterruptedException e)
        {
            System.out.print("prb1:- Problem with the connection\n");
        }
        if(connection!=null)
        {
            //for insertion

            query0 = "select * from sampleTable where username= '" + userName0 + "' and " + " password = '" + passWord0 + "'";
            try
            {
                st = connection.createStatement();
            } catch (SQLException e)
            {
                System.out.print("prb2:- Sql connection problem\n");
            }
            try
            {
                rs = st.executeQuery(query0);
            } catch (SQLException e)
            {
                System.out.print("prb3:- null pointer exception\n");
            }
            String name = "";
            int count1 = 0;

            while (rs.next())
            {
                name = rs.getString("name");
                userNm1=name;
                myID=rs.getInt("id");
                count1++;
            }
            if (count1 > 0)
            {
                System.out.print("Welcome Mr. " + name + "\n");
                bool0=true;
            } else
            {
                System.out.print("Wrong Username or password\n");
            }
        }

        if(bool0)
        {
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Window stage = scene.getWindow();
            stage.hide();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = null;
            try
            {
                root1 = (Parent) loader1.load();
            } catch (IOException e)
            {
                System.out.print("Failed2\n");
            }
            Stage stage1 = new Stage();
            Scene scene1=new Scene(root1);
            scene1.getStylesheets().add(getClass().getResource("styleSample.css").toExternalForm());
            stage1.setScene(scene1);
            stage1.show();
            userName1 = userName0;

            stage1.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    memberCollection.replace(friendName1,currentList);
                    fileManage1 fileManage101=new fileManage1();
                    try
                    {
                        fileManage101.setHashMap(memberCollection,userName1);
                    } catch (IOException e)
                    {
                        System.out.println("*prb17 with IOException*");
                    }
                    System.out.println("Stage is closing");
                    System.exit(0);
                }
            });
            System.out.print(userName0 + "\n");
        }
        return bool0;
    }

    @FXML
    public void handleSaveButton(ActionEvent event) throws IOException, SQLException
    {

    }

    @FXML
    public void handleButton2(ActionEvent event) throws IOException, SQLException
    {
        if(start1)
        {
            scrollpane1.setPannable(true);
            if(friendName1.equals(""))
            {
                chatText1.setText("Please select a friend name\n");
            }
            else
            {
                String str1 = chatField1.getText();
                int len2=str1.length();
                if(len2>0)
                {
                    String timeStr1 = java.time.LocalTime.now().toString();
                    int len1 = timeStr1.length();
                    int count1 = 0;
                    int pos1 = 0;
                    System.out.print(timeStr1 + "\n");

                    me1: for (int i = len1 - 1; i > -1; i--)
                    {
                        char chr1 = timeStr1.charAt(i);
                        if (chr1 == ':')
                        {
                            pos1 = i;
                            break me1;
                        }
                    }

                    int[] arr101=getTheLenth(str1.length());

                    int W1=arr101[0];
                    int W2=370-W1;

                    int H1=arr101[1];
                    int H2=arr101[1];


                    timeStr1 = timeStr1.substring(0, pos1);


                    String msg01 = "\tyou\t\t\t" + timeStr1 + "\n\n\t" + str1 + "\n";

                    Label btn1 = new Label();
                    Label btn2 = new Label();
                    btn2.getStyleClass().add("color-label2");
                    btn1.getStyleClass().add("color-label1");
                    btn2.setPrefSize(W1,H1);
                    btn1.setPrefSize(W2,H2);
                    btn1.setWrapText(true);// for automatically new line
                    btn1.setText(msg01);


                    String[] strArr1=new String[6];
                    String[] strArr2=new String[6];

                    strArr1[0]=String.valueOf(W1);
                    strArr1[1]=String.valueOf(H1);
                    strArr1[2]="color-label2";

                    strArr2[0]=String.valueOf(W2);
                    strArr2[1]=String.valueOf(H2);
                    strArr2[2]="color-label1";
                    strArr2[3]=msg01;

                    currentList.add(strArr1);
                    currentList.add(strArr2);

                    chatField1.setText("");
                    set(friendName1, "sender", userName1);
                    set(friendName1, "message", str1);

                    flowpane1.getChildren().addAll(btn2,btn1);
                    scrollpane1.setContent(flowpane1);
                    Platform.runLater(() -> {
                        scrollpane1.setVvalue(1.0);
                        btn1.setLayoutX(100);
                    });
                }
            }
        }
    }

    public String get(String userName0,String columName0) throws SQLException
    {
        String resString="";
        query0 = "select "+columName0+" from sampleTable where username= '" + userName0+"' " ;
        try
        {
            st = connection.createStatement();
        } catch (SQLException e)
        {
            System.out.print("prb2:- Sql connection problem\n");
        }
        try
        {
            rs = st.executeQuery(query0);
        } catch (SQLException e)
        {
            System.out.print("prb3:- null pointer exception\n");
        }
        while (rs.next())
        {
            resString=rs.getString(columName0);
        }
        return resString;
    }

    public void set(String friendName0,String columName0,String setString0) throws SQLException
    {
        query0 = "update sampleTable set "+columName0+" = '" + setString0+"' where username = '"+friendName0+"' ";
        PreparedStatement ps=connection.prepareStatement(query0);
        int num1=ps.executeUpdate();
    }



    @FXML
    public void handleButton3(ActionEvent event) throws IOException, SQLException
    {
        if(!start1)
        {
            start1 = true;
            count1++;
            chatField4.setText("Welcome "+userName1);
            scrollpane1.setFitToWidth(true);

            if(count1==1)
            {
                String resString = "";
                query0 = "select name,username from sampleTable";
                try
                {
                    st = connection.createStatement();
                } catch (SQLException e)
                {
                    System.out.print("prb2:- Sql connection problem\n");
                }
                try
                {
                    rs = st.executeQuery(query0);
                } catch (SQLException e)
                {
                    System.out.print("prb3:- null pointer exception\n");
                }
                fileManage1 fileManage11=new fileManage1();
                memberCollection=fileManage11.getHashMap(userName1);
                System.out.println(memberCollection.size());
                while (rs.next())
                {
                    String name1 = rs.getString("name");

                    String userNameforMemCol=rs.getString("username");
                    MenuItem menuItem1 = new MenuItem(name1);
                    if(!memberCollection.containsKey(userNameforMemCol))
                    {
                        ArrayList<String[]> arrayList=new ArrayList<>();
                        String[] strings01=new String[6];
                        strings01[0]="370";
                        strings01[1]="10";
                        strings01[2]="color-label2";
                        strings01[3]="*********Welcome to BlackBox**********";
                        arrayList.add(strings01);
                        memberCollection.put(userNameforMemCol,arrayList);
                    }
                    menuItem1.setOnAction(new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent e)
                        {
                            chatText1.setText(friendName1);
                            if(!friendName1.equals(""))
                            {
                                memberCollection.replace(friendName1,currentList);
                            }
                            currentList=memberCollection.get(userNameforMemCol);
                            flowpane1.getChildren().clear();

                            for(String[] strings:currentList)
                            {

                                    Label label1 = new Label();
                                    label1.setPrefSize(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                                    label1.getStyleClass().add(strings[2]);
                                    label1.setText(strings[3]);
                                    label1.setWrapText(true);
                                    flowpane1.getChildren().add(label1);
                                    Platform.runLater(()-> {
                                                scrollpane1.setVvalue(1.0);
                                            });
                            }

                            friendName1 = userNameforMemCol;
                            chatText1.setText("");
                        }
                    });
                    chatFriends1.getItems().add(menuItem1);
                }
                fileManage11.setHashMap(memberCollection,userName1);
                Thread thread1 = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (start1)
                        {
                            String friendName = "";
                            String message1 = "";
                            try
                            {
                                friendName = get(userName1, "sender");
                                message1 = get(userName1, "message");
                            } catch (SQLException e)
                            {
                                System.out.print("prb6:- sql exception\n");
                            }
                            final String msg1 = "\t" + friendName + "\t\t" + (java.time.LocalTime.now() + "").substring(0, 5) + "\n\n\t" + message1;
                            if (!friendName.equals(matchString) && friendName.equals(friendName1))
                            {
                                try
                                {
                                    set(userName1, "sender", "NA");

                                } catch (SQLException e)
                                {
                                    System.out.print("prb7:- problem with setData in database\n");
                                }
                                int len2 = message1.length();
                                Platform.runLater(() -> {

                                    Label button1 = new Label();
                                    Label button2 = new Label();

                                    int[] arr102=getTheLenth(len2);

                                    int W2=arr102[0];
                                    int H2=arr102[1];

                                    int W1=370-W2;
                                    int H1=H2;

                                    button1.setPrefSize(W1,H1);
                                    button2.setPrefSize(W2,H2);
                                    button1.setText(msg1);
                                    button1.setWrapText(true);
                                    button1.getStyleClass().add("color-label3");

                                    String[] strArr103=new String[6];
                                    String[] strArr105=new String[6];

                                    strArr103[0]=String.valueOf(W1);
                                    strArr103[1]=String.valueOf(H1);
                                    strArr103[2]="color-label3";
                                    strArr103[3]=msg1;

                                    strArr105[0]=String.valueOf(W2);
                                    strArr105[1]=String.valueOf(H2);
                                    strArr105[2]="color-label2";

                                    currentList.add(strArr103);
                                    currentList.add(strArr105);

                                    flowpane1.getChildren().addAll(button1, button2);
                                    scrollpane1.setContent(flowpane1);
                                });
                            }

                            else if(!friendName.equals("NA") && !friendName.equals(""))
                            {
                                ArrayList<String[]> arrayList203=new ArrayList<>();
                                if(memberCollection.containsKey(friendName))
                                {
                                    arrayList203 = memberCollection.get(friendName);
                                    String[] strArr203 = new String[6];
                                    String[] strArr205 = new String[6];
                                    int[] arr203 = getTheLenth(message1.length());

                                    int W2 = arr203[0];
                                    int H2 = arr203[1];

                                    int W1 = 370 - W2;
                                    int H1 = H2;

                                    strArr203[0] = String.valueOf(W1);
                                    strArr203[1] = String.valueOf(H1);
                                    strArr203[2] = "color-label3";
                                    strArr203[3] = msg1;

                                    strArr205[0] = String.valueOf(W2);
                                    strArr205[1] = String.valueOf(H2);
                                    strArr205[2] = "color-label2";

                                    arrayList203.add(strArr203);
                                    arrayList203.add(strArr205);
                                    memberCollection.replace(friendName, arrayList203);
                                    try
                                    {
                                        set(userName1, "sender", "NA");
                                    } catch (SQLException e)
                                    {
                                        System.out.println("*prb18 sqlException");
                                    }
                                }
                            }

                            try
                            {
                                Thread.sleep(300);
                            } catch (InterruptedException e)
                            {
                                System.out.print("prb7:- Thread.sleep() exception\n");
                            }
                            if (!friendName.equals(matchString))
                            {
                                scrollpane1.setVvalue(1.0);
                            }
                        }
                    }
                });

                thread1.start();
            }
        }
        else
        {
            start1=false;
        }
    }


    public int[] getTheLenth(int len2)
    {
        int[] arr001=new int[2];
        if (len2 > 20)
        {
            if(len2/42>0)
            {
                arr001[0]=50;
                arr001[1]=100+(len2/42)*41;
            }
            else
            {
                arr001[0]=50;
                arr001[1]=100;
            }
        } else
        {
            arr001[0]=170;
            arr001[1]=100;
        }
        return arr001;
    }
}