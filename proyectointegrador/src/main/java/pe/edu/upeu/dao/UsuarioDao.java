package pe.edu.upeu.dao;

import java.io.Console;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.UsuarioTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class UsuarioDao extends AppCrud{
    LeerArchivo lar;
    UsuarioTO usTO;

    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();  


    public void crearNuevoUsuario() {
       
        
        usTO=new UsuarioTO(); 
        lar=new LeerArchivo("Usuario.txt"); 
        
        AnsiConsole.systemInstall();
       Ansi colorX=new Ansi();
        { System.out.println(colorX.fgBrightBlue().a("---------------------Creando cuenta de usario--------------------"));}
        
        String user=lte.leer("", "Ingrese un usuario:").toLowerCase();
        if(validarExistUser(user)){
            usTO.setUsuario(user);
            usTO.setIdUsuario(generarId(lar, 0, "U", 1));
            usTO.setPerfil(lte.leer("", "Ingrese el Perfil del Usuario (ADMIN, VENDEDOR):").toUpperCase());
            ut.clearConsole();
            Console  constx= System.console();
            System.out.println("crear Contrase;a:");
            
            char[] clave=constx.readPassword("ingresa contrase;a: ");
            char[] secondclave= constx.readPassword("vuelva a ingresar la contrase;a: ");
            usTO.setClave(String.valueOf(clave));
            usTO.setClave(String.valueOf(secondclave));

            if((new String(clave)).equals(new String(secondclave))) {
                System.out.println("ingreso la contraseña de forma correcta");
               
                ut.pintarLine('H', 35);
                Ansi colorY=new Ansi(); 
                System.out.println(colorY.render("@|red ==============Su cuenta fue creado con exito=====================|@"));
                
            }else {
                
                Ansi colorY=new Ansi();
                System.err.println(colorY.render("@|red -- !error!---!las contrase;as no coincide vuelva a intenta!--|@"));
                crearNuevoUsuario();
            }
            
            agregarContenido(lar, usTO);
                              
        
        ut.pintarLine('H', 35); 
       } else{
           System.out.println(" Eliga otro usuario:"); 
           crearNuevoUsuario();
        }
    }
    
    public boolean login(String usuario, char[] clave) 
    {
        lar=new LeerArchivo("Usuario.txt");
       Object[][] data=buscarContenido(lar, 1, usuario);
       if(data.length==1 && data[0][2].equals(String.valueOf(clave))){
        return true;
       }
        return false;
        
    }
    

    public boolean validarExistUser(String user) {  ut.clearConsole();
        lar=new LeerArchivo("Usuario.txt");
       Object[][] data=buscarContenido(lar, 1, user);  
       if(data!=null && data.length>0){
           System.out.println("-----ya existe otro usuario con ese nombre-----");
        return false;
       }
       return true;
    }

}

    
    