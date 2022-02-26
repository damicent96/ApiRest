
package Modelo.Dao;

import Modelo.Dto.UsuarioRESTDTO;

/**
 *
 * @author Juan
 */
public interface UsuarioRESTDAO extends Base.BaseSQL<UsuarioRESTDTO>{
   boolean validarUsuario(UsuarioRESTDTO dto);
}
