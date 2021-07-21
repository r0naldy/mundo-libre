package pe.edu.upeu.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.InventarioTO;
import pe.edu.upeu.modelo.VentaDetalleTO;
import pe.edu.upeu.modelo.VentaTO;
import pe.edu.upeu.util.LeerArchivo;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class VentaDao extends AppCrud{
   

    LeerArchivo lar;
    LeerTeclado lte=new LeerTeclado();
    UtilsX ut=new UtilsX();
    InventarioTO proTo;
    VentaTO ventTO;
    VentaDetalleTO vdTO;
    SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat formatoHora=new SimpleDateFormat("HH:mm:ss");    
    SimpleDateFormat formatoFecha=new SimpleDateFormat("dd-MM-yyyy"); 

    public void registroVentaGeneral() {
        String venta="SI";
        VentaTO vent=crearVenta();
        double preciototal=0;
        do{
            VentaDetalleTO vt= carritoVentas(vent);

            preciototal=preciototal+vt.getPrecioTotal();
            venta=lte.leer("", "Desea algo mas  (SI=S/NO=N)?").toUpperCase();
            
        }while(venta.charAt(0)=='S');        
        vent.setPrecioTotal(preciototal);
        lar=new LeerArchivo("Venta.txt");
        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
        System.out.println(colory.fgBrightCyan().a("===========boleta de Pago==========="));
        ut.pintarLine('H', 20);        
        editarRegistro(lar, 0, vent.getIdVenta(), vent, preciototal);
        
        ut.pintarLine('H', 20); 
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
        System.out.println(colorX.fgBrightGreen().a(" Total a pagar S/."+
        vent.getPrecioTotal()));
    }

    public VentaTO crearVenta() {ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
        System.out.println(colory.fgBrightBlue().a("*****************Registro de Venta*********************"));
        lar=new LeerArchivo("Venta.txt");
        ventTO=new VentaTO();
        ventTO.setIdVenta(generarId(lar, 0, "V", 1));
        ventTO.setDniCliente(lte.leer("", "Ingrese el DNI del cliente:"));
        ut.pintarLine('H', 20); 
        ventTO.setFechaVenta(formato.format(new Date()));
        ventTO.setPrecioTotal(0.0);
        lar=new LeerArchivo("Venta.txt");
        agregarContenido(lar, ventTO);
        return ventTO;
    }

    public VentaDetalleTO carritoVentas(VentaTO vTO) {
        mostrarInventario();
        ut.pintarLine('H', 20);
        vdTO=new VentaDetalleTO();
        lar=new LeerArchivo("VentaDetalle.txt");
        vdTO.setIdVentaDetalle(generarId(lar, 0, "VD", 2));
        vdTO.setIdInventario(lte.leer("", "Ingrese Id Producto:"));
        vdTO.setIdVenta(vTO.getIdVenta());
        LeerArchivo larX=new LeerArchivo("Inventario.txt"); 
        Object[][] dataProd=buscarContenido(larX, 0, vdTO.getIdInventario()); 
        double precioUnit=Double.parseDouble(String.valueOf(dataProd[0][3]));
        vdTO.setCantidad(lte.leer(0.0, "Ingrese cantidad:"));
        vdTO.setPrecioUnit(precioUnit);
        vdTO.setPrecioTotal(vdTO.getCantidad()*vdTO.getPrecioUnit());
           ut.pintarLine('H', 20);
           ut.clearConsole();
           AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
            System.out.println(colory.fgBrightBlue().a("============Detalles de la venta==========="));
        lar=new LeerArchivo("VentaDetalle.txt");
        agregarContenido(lar, vdTO); 
        ut.pintarLine('H', 20);
        return vdTO;
    }
    
 
    public void mostrarInventario() {
        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
        System.out.println(colory.fgBrightBlue().a("*****************************************Agregar Productos a carrito de ventas*********************"));
        System.out.println("");
        lar=new LeerArchivo("Inventario.txt");
        Object[][] data=listarContenido(lar);
        for (int i = 0; i < data.length; i++) {
            if(Double.parseDouble(String.valueOf(data[i][4]))>0){
                System.out.print(data[i][0]+"="+data[i][1]+"(Precio:"+data[i][3]+ 
                "Stock:"+data[i][4]+"); ");
            }            
        }
        System.out.println("");
    }
   

    public void reporteVentasRangoFecha() {
        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colory=new Ansi();
        System.out.println(colory.fgBrightBlue().a("*******************Reporte de Ventas por fechas*****************"));
        String fechaIni=lte.leer("", "Ingrese Fecha de Inicio (dd-MM-yyy):");
        String fechaFin=lte.leer("", "Ingrese Fecha Final (dd-MM-yyy):");
        lar=new LeerArchivo("Venta.txt");
        Object[][] dataV=listarContenido(lar);
        int contarVentasRF=0;
        try {
            for (int fila = 0; fila < dataV.length; fila++) {
                String[] fechaVenta=String.valueOf(dataV[fila][2]).split(" ");
                System.out.println("Datos Fecha:"+fechaVenta[0]+"  Fecha Teclado:"+formatoFecha.format(formatoFecha.parse(fechaIni)));
                Date fechaVentaX=formatoFecha.parse(fechaVenta[0]);
                if ( 
                    (fechaVentaX.after(formatoFecha.parse(fechaIni)) || fechaVenta[0].equals(fechaIni)) && 
                    (fechaVentaX.before(formatoFecha.parse(fechaFin)) || fechaVenta[0].equals(fechaFin))
                ) {
                    contarVentasRF=contarVentasRF+1;
                   
                }
                 
            }
              
            Object[][] dataVRF=new Object[contarVentasRF][dataV[0].length];
            int filaIndice=0;

           
            double  precioTotalX=0;
            for (int fila = 0; fila < dataV.length; fila++) {
                String[] fechaVenta=String.valueOf(dataV[fila][2]).split(" ");
                Date fechaVentaX=formatoFecha.parse(fechaVenta[0]);
                if ( 
                    (fechaVentaX.after(formatoFecha.parse(fechaIni)) || fechaVenta[0].equals(fechaIni)) && 
                    (fechaVentaX.before(formatoFecha.parse(fechaFin)) || fechaVenta[0].equals(fechaFin))
                ) {
                    for (int columna = 0; columna < dataV[0].length; columna++) {
                        dataVRF[filaIndice][columna]=dataV[fila][columna];
                    
                        if(columna==3){precioTotalX+=Double.parseDouble(String.valueOf(dataV[fila][columna]));}                        
                    }
                    filaIndice++;
                }
            }

        ut.clearConsole();
        AnsiConsole.systemInstall();
        Ansi colorp=new Ansi();
        System.out.println(colorp.fgBrightRed().a("************************Reporte de ventas*************************"));
        System.out.println("---------------Entre "+fechaIni + " a "+fechaFin+"-------------------");
        ut.pintarLine('H', 32);
        ut.pintarTextHeadBody('H', 3, "ID,DNI Cliente,F.Venta,hora,Total");
        System.out.println("");
        ut.pintarLine('H', 32);
        for (Object[] objects : dataVRF) {           
            String dataCadena=""+objects[0]+","+objects[1]+","+objects[2]+","+formatoHora.format(formato.parse(objects[2].toString()))+","+objects[3];
            ut.pintarTextHeadBody('B', 3, dataCadena);
        }
        System.out.println("");
        ut.pintarLine('H', 32);
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
        System.out.println(colorX.render("@|blue "+"Monto Recaudado: S/.|@ @|red "+
         (Math.round(precioTotalX*100.0)/100.0)+"|@"));


        } catch (Exception e) {        
            System.out.println("error"+e.getMessage());   
        }
    }


}
