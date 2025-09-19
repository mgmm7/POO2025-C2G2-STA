package pe.edu.upeu.asistencia.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.util.Map;

@Controller

public class MainguiController {
    @FXML
    private BorderPane bp;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem MenuItemC;
    Menu menuEstilos=new Menu("Cambiar estilos");
    ComboBox<String> comboEstilo=new ComboBox<>();
    CustomMenuItem customMenuItem=new CustomMenuItem(comboEstilo);
    @Autowired
    ApplicationContext context;
    @FXML
    TabPane tabPane;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private void onIniciarSesion(ActionEvent event) {
        abrirTabPaneFXML("/fxml/main_participante.fxml", "Movimientos");
    }
    private void abrirTabPaneFXML(String fxmlPath,String tittle){
        try {

            for (Tab t : tabPane.getTabs()) {
                if (t.getText().equals(tittle)) {
                    tabPane.getSelectionModel().select(t);


                    Object ctrl = t.getUserData();
                    if (ctrl != null) {
                        if (ctrl instanceof pe.edu.upeu.asistencia.control.ReporteController) {
                            ((pe.edu.upeu.asistencia.control.ReporteController) ctrl).cargarDatos();
                        }
                    }
                    return;
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setControllerFactory(context::getBean);
            Parent root = fxmlLoader.load();

            Object controller = fxmlLoader.getController();

            ScrollPane scrollPane = new ScrollPane(root);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            Tab newtab = new Tab(tittle, scrollPane);
            newtab.setUserData(controller);
            tabPane.getTabs().add(newtab);
            tabPane.getSelectionModel().select(newtab);


            if (controller instanceof pe.edu.upeu.asistencia.control.ReporteController) {
                ((pe.edu.upeu.asistencia.control.ReporteController) controller).cargarDatos();
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void initialize() {
        comboEstilo.getItems().addAll("Estilo por defecto","Estilo oscuro","Estilo azul","Estilo rosado","Estilo verde","Estilo morado","Estilo naranja");
        comboEstilo.setOnAction(e->cambiarEstilo());
        customMenuItem.setHideOnClick(false);
        menuEstilos.getItems().addAll(customMenuItem);
        menuBar.getMenus().addAll(menuEstilos);
        MenuItemListener mIL=new MenuItemListener();

        MenuItemC.setOnAction(mIL::handle);
    }

    public void cambiarEstilo(){
        String estilo=comboEstilo.getSelectionModel().getSelectedItem();
        Scene scene=bp.getScene();
        scene.getStylesheets().clear();
        switch(estilo){
            case "Estilo oscuro":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-oscuro.css").toExternalForm());break;
            case "Estilo azul":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-azul.css").toExternalForm());break;
            case "Estilo rosado":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-rosado.css").toExternalForm());break;
            case "Estilo verde":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-verde.css").toExternalForm());break;
            case "Estilo morado":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-morado.css").toExternalForm());break;
            case "Estilo naranja":scene.getStylesheets().add(getClass().getResource("/css/css/estilo-naranja.css").toExternalForm());break;
            default:break;
        }
    }
    class MenuItemListener{
        Map<String, String[]> menuConfig = Map.of(
                "MenuItemReporte", new String[]{"/fxml/reporte.fxml","Reporte","T"},
                "MenuItemC", new String[]{"/fxml/login.fxml","salir","C"}
        );

        public void handle(ActionEvent e){//STA
                String id=((MenuItem)e.getSource()).getId();
                if (menuConfig.containsKey(id)){
                    String[] items=menuConfig.get(id);
                    if(items[2].equals("C")){
                        Platform.exit();
                        System.exit(0);
                    }else{
                        abrirTabPaneFXML(items[0],items[1]);
                    }
                }
        }
    }
}