import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DPoblacionalController {

    @FXML private TextField TxTFNoHabitantes;
    @FXML private TextArea TextareaAfeccion;
    @FXML private Button ProcesarHabButton;  
    @FXML private Button NuevoButton;

    @FXML void resultadoafeccion(ActionEvent event) {
        int NoDeHabitantes= Integer.parseInt(TxTFNoHabitantes.getText());

        DensidadPoblacional NumeroABuscar=new DensidadPoblacional();       
        String NivelAfeccion= String.valueOf(NumeroABuscar.afeccion(NoDeHabitantes));
        TextareaAfeccion.setText(NivelAfeccion);
    }

    @FXML void nuevaconsulta(ActionEvent event) {
        //vaciar la textarea  para procesar nuevos datos. 
        TextareaAfeccion. selectAll(); 
        TextareaAfeccion. replaceSelection("");
        TxTFNoHabitantes.setText("");

    }

}
