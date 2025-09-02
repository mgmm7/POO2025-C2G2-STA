package pe.edu.upeu.asistencia.control;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionListener;

@Controller

public class MainguiController {
    @FXML
    private BorderPane bp;
    @FXML
    MenuBar menuBar;
    @FXML
    TabPane tabPane;
    @FXML
    MenuItem MenuItem1, MenuItemC;

    public void initialize() {

    }

    class MenuItemListener{
        public void handle(ActionEvent e){//STA

        }
        private void abrirTabPaneFXML(String fxmlPath,String tittle){
            try {

            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        }
    }


    class MenuListener{
        public void handle(Event e){

        }
    }
}