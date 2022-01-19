
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LectorAguacontroller {  

    @FXML private Button loginButton;
    @FXML private Button processButton;
    @FXML private TextField Nombre;
    @FXML private TextField Id_cuerpo_agua;
    @FXML private TextField Municipio;
    @FXML private TextField T_CuerpoDeAgua;
    @FXML private TextField Tipo_Agua;
    @FXML private TextField IRCA;
    @FXML private TextArea Datos;
    @FXML private TextArea Salida;
    @FXML private Button DensidadButton;


    String nombre= ""; //Inicializamos
    String id_cuerpo_agua= "";
    String  municipio= "";
    String t_CuerpoDeAgua= "";
    String tipo_Agua= "";
    String irca= "";

    ArrayList<String>Lista_Cuerpo =new ArrayList<>(); //realizando la creacion del arreglo
    CuerpoDeAgua[] arrayCuerpos= new CuerpoDeAgua[100];



    @FXML void addlinea(ActionEvent event){

        String nombre= Nombre.getText(); 
        String id_cuerpo_agua=Id_cuerpo_agua.getText();
        String municipio= Municipio.getText();
        String t_CuerpoDeAgua= T_CuerpoDeAgua.getText();
        String tipo_Agua=Tipo_Agua.getText();
        String irca= IRCA.getText();

        //Informacion que sale en la caja de TEXTAREA
        Datos.appendText(nombre+" "+id_cuerpo_agua+" "+municipio+" "+t_CuerpoDeAgua+" "+tipo_Agua+" "+irca+"\n");


        String Lista=nombre+" "+id_cuerpo_agua+" "+municipio+" "+t_CuerpoDeAgua+" "+tipo_Agua+" "+irca;
        Lista_Cuerpo.add(Lista);

        //Opcional, recetear la informacion de las cajas de texto
        Nombre.setText("");
        Id_cuerpo_agua.setText("");
        Municipio.setText("");
        T_CuerpoDeAgua.setText(""); 
        Tipo_Agua.setText("");
        IRCA.setText("");

    }


    @FXML void resullinea(ActionEvent event) throws IOException{
        CuerpoDeAgua[] arrayCuerpos= new CuerpoDeAgua[100]; 
        double cont_total=0;  //contadores
        int cont_medios=0; //contadores
        String cuerpo=""; //variable para guardar la informacion. 
        for (int i=0; i<Lista_Cuerpo.size(); i++) {
            String[] array =Lista_Cuerpo.get(i).split(" "); //el nuevo array para hacer la separacion por espacio. 
            arrayCuerpos[i] =new CuerpoDeAgua(array[0], Integer.parseInt(array[1]),array[2],array[3],array[4], Double.parseDouble(array[5])); 
        }
        for (int i=0; i<Lista_Cuerpo.size(); i++) { //recorrido de el arreglo para hallar los recpectivas salidas
        if((arrayCuerpos[i].getIRCA())>0 && (arrayCuerpos[i].getIRCA())<=5){
            cuerpo="SIN RIESGO";
            cont_total+=1;
        }else{
            if((arrayCuerpos[i].getIRCA())>=5.1 && (arrayCuerpos[i].getIRCA()<=14)){
                cuerpo="BAJO";
                cont_total+=1;
            }else{
                if((arrayCuerpos[i].getIRCA())>=14.1 && (arrayCuerpos[i].getIRCA())<=35){
                    cuerpo="MEDIO";
                    cont_total+=1;
                    cont_medios+=1;               
                }else{
                    if((arrayCuerpos[i].getIRCA())>=35.1 && (arrayCuerpos[i].getIRCA())<=80){
                        cuerpo="ALTO";
                    }else{
                        if((arrayCuerpos[i].getIRCA())>=80.1 && (arrayCuerpos[i].getIRCA())<=100){
                            cuerpo="INVIABLE SANITARIAMENTE"; 
                        }
                    }

                }
            }
        }
        Salida.appendText(cuerpo+"\n");
        }
        String Cont_total=String.format(Locale.ROOT,"%.2f",cont_total);
        Salida.appendText(Cont_total+"\n");
        

        for (int i=0; i<Lista_Cuerpo.size(); i++) {
        if((arrayCuerpos[i].getIRCA())>=14.1 && (arrayCuerpos[i].getIRCA())<=35){
            Salida.appendText(arrayCuerpos[i].getNombre()+" ");
            }   
        }
        if(cont_medios==0){
            Salida.appendText("NA");      
        }

        Salida.appendText("\n");

        double IRCA_menor=arrayCuerpos[0].getIRCA();
        String IRCA_nomb=arrayCuerpos[0].getNombre();   
        double Id_menor=arrayCuerpos[0].getId_cuerpo_agua();

        for (int i=0; i<Lista_Cuerpo.size(); i++) {
                if (IRCA_menor>arrayCuerpos[i].getIRCA()){
                IRCA_menor= arrayCuerpos[i].getIRCA();
                IRCA_nomb= arrayCuerpos[i].getNombre();
                Id_menor=arrayCuerpos[i].getId_cuerpo_agua();
                }
        }
        String ID_menor=String.format(Locale.ROOT,"%.2f",Id_menor);
        Salida.appendText(IRCA_nomb+" "+ID_menor+" "); 
        Salida.appendText("\n");

    }


        // Metodo para abrir la ventana Densidad poblacional
    @FXML void DensPoblacional(ActionEvent event)throws IOException { 
    //Mostrar otro formulario. 
    FXMLLoader loader = new // estandar, pero se puede cambiar la palabra de azul
    FXMLLoader(getClass().getResource("DensidadPoblacional.fxml"));//para que aparezca la venta
    Parent root = loader.load();  // estandar, pero se puede cambiar la palabra de azul
    DPoblacionalController controlador = loader.getController(); //la ventana que queremos abrir
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

    }


}