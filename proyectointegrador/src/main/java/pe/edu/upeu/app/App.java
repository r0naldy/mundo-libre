package pe.edu.upeu.app;

import java.io.Console;

import pe.edu.upeu.dao.CategoriaDao;
import pe.edu.upeu.dao.InventarioDao;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.dao.VentaDao;
import pe.edu.upeu.gui.MainGUI;
import pe.edu.upeu.modelo.CategoriaTO;
import pe.edu.upeu.modelo.InventarioTO;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;



public class App {
     

    public static void registrarCategoria(CategoriaTO categ) {
        System.out.println("------------Registro de Categoria de Productos---------");
        System.out.println("IDCateg:"+categ.getIdCateg()+"\tNombre:"+categ.getNombre());
    }

    public static void menuMain(){UtilsX ut=new UtilsX();
        ut.clearConsole();
        Ansi colorX=new Ansi();
        
        String mensaje="===============Menu Principal ================="+(colorX.fgBrightCyan().a(
        "\n1=Registro de Categoria"+
        "\n12=Mostrar lista de Categoria "+
        "\n2=Registrar Productos"+
        "\n21=Mostrar inventario"+
        "\n3=Realizar Venta"+
        "\n31=Reporte de Venta en Rango de Fechas"+
        "\n4=Registro de Usuarios"+
        "\n0=Salir del programa"));
        LeerTeclado lt=new LeerTeclado();  
        CategoriaDao daoC;       
        UsuarioDao daoUso;
        InventarioDao proDao;
        VentaDao venDao;
        int opcion=0;
        opcion=lt.leer(0, mensaje);
        do{            
            switch(opcion){
                case 1:                
                daoC=new CategoriaDao(); daoC.crearCategoria(); 
                ut.clearConsole(); break;
                case 12: 
                ut.clearConsole();
                daoC=new CategoriaDao(); daoC.reporteCategoria(); break;    
                case 2: proDao=new InventarioDao(); proDao.crearInventario(); break;
                case 21: proDao=new InventarioDao(); proDao.reporteInventario(); break;
                case 3: venDao=new VentaDao(); venDao.registroVentaGeneral(); break;
                case 31: venDao=new VentaDao(); venDao.reporteVentasRangoFecha(); break;
                case 4: daoUso=new UsuarioDao(); daoUso.crearNuevoUsuario(); break;
                case 0: break;
                default: System.out.println("La opcion que eligio no existe!");
                break;
            }
            if(opcion!=0){
                
                if(lt.leer("", "\nDesea seguir probando SI=S/NO=N:").toUpperCase().charAt(0)=='S'){
                    ut.clearConsole();
                    opcion=lt.leer(0, mensaje); 
                }else{
                    opcion=0;
                }                
            }
            
        }while(opcion!=0);        
    }

    public static void validarAcceso() {
        LeerTeclado lt=new LeerTeclado(); 
        Console  constx= System.console();
        AnsiConsole.systemInstall();
        Ansi colorM=new Ansi();
        System.out.println(colorM.fgBrightCyan().a("---------Login--------").reset());
        
        String usuario=lt.leer("", "Ingrese su usuario:");
        System.out.println("Ingrese su clave:");
        char[] clave=constx.readPassword();
        UsuarioDao usuDao=new UsuarioDao();
        if(usuDao.login(usuario, clave)){
            menuMain(); 
        }else{
            System.out.println("Intente Nuevamente!!");

            validarAcceso();
        }
    }


    public static void main( String[] args ){
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
       System.out.println(colorX.fgRed().a("***************BIENVENIDOS A PRO-SURA***********").reset());
        validarAcceso();       
        //menuMain(); 
        //new MainGUI();
    }
}
   