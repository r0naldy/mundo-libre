package pe.edu.upeu.modelo;

import lombok.Data;

@Data
public class VentaDetalleTO {

   public String idVentaDetalle, idVenta, idInventario;
   public double precioUnit, producto, cantidad, precioTotal;
  
}
