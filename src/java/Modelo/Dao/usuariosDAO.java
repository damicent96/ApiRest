
package Modelo.Dao;

import Base.BaseSQL;
import Modelo.Dto.usuariosDTO;


public interface usuariosDAO extends BaseSQL<usuariosDTO>{
    boolean user_disable(Integer id);
}
