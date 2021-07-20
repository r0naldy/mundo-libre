
package pe.edu.upeu.modelo;

import lombok.Data;

@Data
public class VentaDetalleTO {
 //IdVEntaDetalle	IdVenta	IdProducto	precioUnit	porcentUtil	cantidad	precioTotal
   public String idVentaDetalle, idVenta, idInventario;
   public double precioUnit, nll, cantidad, precioTotal;
}
