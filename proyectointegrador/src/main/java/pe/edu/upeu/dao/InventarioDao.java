
package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.CategoriaTO;
import pe.edu.upeu.modelo.InventarioTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;

public class InventarioDao extends AppCrud {
    LeerArchivo lar;
    CategoriaTO catTO;
    InventarioTO prodTO;
    

    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();
    
    public Object[][] crearInventario() {
           
            System.out.println("-------------Registro de productos------------");
            mostrarCategoria();
            prodTO=new InventarioTO();
            lar=new LeerArchivo("inventario.txt"); 
            prodTO.setIdCateg(lte.leer("","Ingrese el ID de la categoria"));
            prodTO.setIdProducto(generarId(lar, 0, "P", 1));
            prodTO.setNombre(lte.leer("", "Ingrese nombre del producto"));
            prodTO.setPrecioUnit(lte.leer(0.0, "Ingrese Precio unitario"));
            prodTO.setStock(lte.leer(0.0, "Ingrese Stock"));  
            lar=new LeerArchivo("inventario.txt");
              
            return agregarContenido(lar,prodTO); 
            
    }
   
    
    public void mostrarCategoria() {
        lar=new LeerArchivo("Categoria.txt"); 
        Object[][] data=listarContenido(lar);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i][0]+"="+data[i][1]+",");
        }
        System.out.println("");
    }

    
    
    public void reporteInventario() {
        
        System.out.println("-------------Reporte de Categoria------------");
        lar=new LeerArchivo("inventario.txt");
       Object[][] data=listarContenido(lar);
        String dataX="";
        ut.pintarLine('H',13);
        ut.pintarTextHeadBody('H', 3, "IDInve,Noombre,idcateg,precioUnit,stock"); 
        System.out.println("");
        ut.pintarLine('H',13);
       
       for (int i = 0; i < data.length; i++) {
           for (int j = 0; j < data[0].length; j++) {
               if(j==0){
                dataX+=""+data[i][j];
               }else{
                dataX+=","+data[i][j]; 
               }               
           }
           ut.pintarTextHeadBody('B', 3, dataX);  
           dataX="";
       }        
    }
}

