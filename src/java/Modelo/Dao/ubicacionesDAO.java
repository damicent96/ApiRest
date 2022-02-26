
package Modelo.Dao;

import Modelo.Dto.ubicacionesDTO;
import java.util.List;


public interface ubicacionesDAO {
    public boolean agregarRegistro(ubicacionesDTO ubicacion);
    public boolean modificarRegistro(ubicacionesDTO ubicacion);
    public boolean eliminarRegistro(ubicacionesDTO ubicacion);
    public ubicacionesDTO recuperarRegistro(Integer id_ubicacion);
    public List<ubicacionesDTO> recuperarRegistros();
    public String aviso();

}
