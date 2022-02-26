

package Testeo;

import Modelo.Dao.perfilesDAO;
import Modelo.DaoImp.perfilesDAOIMP;
import Modelo.Dto.perfilesDTO;



public class prueba_perfiles {

    private perfilesDAO dao;
    private perfilesDTO dto;
    
     
public prueba_perfiles(){
    dto = new perfilesDTO();
    dto.setDescripcion("administradora");
    
    dao = new perfilesDAOIMP();
    
    if (dao.agregarRegistro(dto)) {
        System.out.println("Se ha agregado correctamente!");
    } else {
        System.out.println("Algo no funciono! :c");
    }
}
    
    public static void main(String[] args) {
        new prueba_perfiles();
        
    }
    
}
