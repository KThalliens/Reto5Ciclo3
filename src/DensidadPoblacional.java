public class DensidadPoblacional extends ObjetoGeografico {


    public DensidadPoblacional(String Nombre,int Id_cuerpo_agua,String Municipio,String T_CuerpoDeAgua, String Tipo_Agua, double IRCA){
        super(Nombre,Id_cuerpo_agua,Municipio,T_CuerpoDeAgua,Tipo_Agua,IRCA);
    }

    public DensidadPoblacional(){
    }


    public int afeccion(int NoDeHabitantes){
        int NivelAfeccion=0;
        if((NoDeHabitantes)<0 &&(NoDeHabitantes)<=10000){
            NivelAfeccion=0;
        }else{
            if((NoDeHabitantes)>10000 && (NoDeHabitantes)<=50000){
                NivelAfeccion=1;
            }else{
                if((NoDeHabitantes)>50000){
                    NivelAfeccion=2;
                }
            }
        } 
        return NivelAfeccion;  
    }
}
