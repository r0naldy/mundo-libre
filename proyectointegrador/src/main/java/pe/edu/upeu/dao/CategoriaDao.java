package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.CategoriaTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;



public class CategoriaDao extends AppCrud {
    LeerArchivo lar;
    CategoriaTO catTO;
   

    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();

    public Object[][] crearCategoria() {
        ut.clearConsole();
        
        catTO=new CategoriaTO();
        AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
        System.out.println(colory.fgBrightRed().a("-------------Registro de Categoria---------------"));
        lar=new LeerArchivo("Categoria.txt");
        catTO.setIdCateg(generarId(lar, 0, "C", 1));
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
        System.out.println(colorX.fgBrightBlue().a(""));   
        catTO.setNombre(lte.leer("", "Ingrese nombre de categoria:"));
        return agregarContenido(lar, catTO);}


    

      
    public void reporteCategoria(Object[][] data) {
        ut.clearConsole();
        
        System.out.println("-------------Reporte de Categoria------------");
        imprimirLista(data);
    }
    
    public void reporteCategoria() {
        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
        System.out.println(colorX.fgBrightBlue().a("-------------Reporte de Categoria----------"));
        lar=new LeerArchivo("Categoria.txt");
       Object[][] data=listarContenido(lar);
        String dataX="";
        ut.pintarLine('H',21);
        ut.pintarTextHeadBody('H', 5, "ID,Noombre"); 
        System.out.println("");
        ut.pintarLine('H',21);
       AnsiConsole.systemInstall();
        Ansi colorY=new Ansi();
        System.out.println(colorY.fgBrightGreen().a("")); 
       for (int i = 0; i < data.length; i++) {
           for (int j = 0; j < data[0].length; j++) {
               if(j==0){
                dataX+=""+data[i][j];
               }else{
                dataX+=","+data[i][j]; 
               }               
           }
           ut.pintarTextHeadBody('B', 5, dataX);  
           dataX="";
           
          
       }   
         
    }   
   
}

    

