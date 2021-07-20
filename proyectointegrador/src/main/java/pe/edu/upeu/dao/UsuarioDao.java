package pe.edu.upeu.dao;

import java.io.Console;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.UsuarioTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;

public class UsuarioDao extends AppCrud{
    LeerArchivo lar;
    UsuarioTO usTO;

    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();   


    public void crearNuevoUsuario() {
       
        
        usTO=new UsuarioTO();
        lar=new LeerArchivo("Usuario.txt");
        String user=lte.leer("", "Ingrese un usuario:").toLowerCase();
        if(validarExistUser(user)){
            usTO.setUsuario(user);
            usTO.setIdUsuario(generarId(lar, 0, "U", 1));
            usTO.setPerfil(lte.leer("", "Ingrese el Perfil del Usuario (ADMIN, VENDEDOR):").toUpperCase());

            Console  constx= System.console();
            System.out.println("crear Contrase;a:");
            char[] clave=constx.readPassword("ingresa contrase;a: ");
            char[] secondclave= constx.readPassword("vuelva a ingresar la contrase;a: ");
            usTO.setClave(String.valueOf(clave));
            usTO.setClave(String.valueOf(secondclave));

            if((new String(clave)).equals(new String(secondclave))) {
                System.out.println("ingreso la contraseña de forma correcta");
            }else {
                //Ansi colorX=new Ansi();
                System.err.println("  -- !errorlas contrase;as no coincide vuelva a intenta!--");
                crearNuevoUsuario();
                ut.clearConsole();
                
        

            }
            
            agregarContenido(lar, usTO);
                              
        }else{
        
           System.out.println("red Eliga otro usuario:"); 
           crearNuevoUsuario();
        }
    }

    public boolean login(String usuario, char[] clave) {
        lar=new LeerArchivo("Usuario.txt");
       Object[][] data=buscarContenido(lar, 1, usuario);
       if(data.length==1 && data[0][2].equals(String.valueOf(clave))){
        return true;
       }
        return false;
    }

    public boolean validarExistUser(String user) {
        lar=new LeerArchivo("Usuario.txt");
       Object[][] data=buscarContenido(lar, 1, user);  
       if(data!=null && data.length>0){
           System.out.println("-----ya existe otro usuario con ese nombre-----");
        return false;
       }
       return true;
    }

}

    