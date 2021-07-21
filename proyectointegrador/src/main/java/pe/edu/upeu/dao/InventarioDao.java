package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.CategoriaTO;
import pe.edu.upeu.modelo.InventarioTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import org.fusesource.jansi.Ansi;



public class InventarioDao extends AppCrud {
    LeerArchivo lar;
    CategoriaTO catTO;
    InventarioTO prodTO;
    

    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();
    
    public Object[][] crearInventario() {
        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colorY=new Ansi();
            System.out.println(colorY.fgBrightGreen().a("-------------Registro de productos------------"));
            mostrarCategoria();
            prodTO=new InventarioTO();
            System.out.println("");
            lar=new LeerArchivo("inventario.txt"); 
            prodTO.setIdCateg(lte.leer("","Ingrese el ID de la categoria"));
            prodTO.setIdProducto(generarId(lar, 0, "P", 1));
            prodTO.setNombre(lte.leer("", "Ingrese nombre del producto"));
            prodTO.setPrecioUnit(lte.leer(0.0, "Ingrese Precio unitario"));
            prodTO.setStock(lte.leer(0.0, "Ingrese Stock")); 
            ut.clearConsole();
            System.out.println("");
            AnsiConsole.systemInstall();
            Ansi colorZ=new Ansi();
            System.out.println(colorZ.fgBrightBlue().a("===============Producto agregado al inventario=============="));
            lar=new LeerArchivo("inventario.txt");
            ut.pintarLine('H',27);    
            return agregarContenido(lar, prodTO);
            
    }
    
   
    
    public void mostrarCategoria() {
        ut.clearConsole();
        
        lar=new LeerArchivo("Categoria.txt"); 
        Object[][] data=listarContenido(lar);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i][0]+"="+data[i][1]+",");
        }
        System.out.println("");

    }

    
     
    public void reporteInventario() {
       ut.clearConsole();
       AnsiConsole.systemInstall();
       Ansi colorX=new Ansi();
       System.out.println("");
       System.out.println("");
       System.out.println("");
        System.out.println(colorX.fgBrightRed().a("=======================Reporte de Inventario====================== "));
         System.out.println("");
        lar=new LeerArchivo("inventario.txt");
       Object[][] data=listarContenido(lar);
        String dataX="";
        ut.pintarTextHeadBody('H', 3,  " IDdeInventario,Noombre,idcategoria,precioUnitario,stock,"); 
          AnsiConsole.systemInstall();
        Ansi colorY=new Ansi();
         System.out.println(colorY.fgBrightBlue().a(""));
        ut.pintarLine('H',32);
      
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if(j==0){
                 dataX+=""+data[i][j];
                }else{
                 dataX+=","+data[i][j]; 
                }               
            }
            ut.pintarTextHeadBody('V', 3, dataX);
            ut.pintarLine('H',32);
            dataX="";
        }    
              
     }      
 }
 
 