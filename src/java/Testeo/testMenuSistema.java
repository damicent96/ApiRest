package Testeo;


import Modelo.Dao.MenuSistemaDAO;
import Modelo.DaoImp.MenuSistemaDAOIMP;
import Modelo.Dto.MenuSistemaDTO;
import Base.Conexion;

public class testMenuSistema {

    private Conexion conexion;
    private MenuSistemaDTO dto;
    private MenuSistemaDAO dao;

    public testMenuSistema() {

        
//________________________________________________INSERTAR UN REGISTRO________________________________________________
//        dto = new MenuSistemaDTO();
//        dto.setDescrip("INDEFINIDO");
//        dto.setComentario("contrato por tiempo indefinido");
//        dao = new MenuSistemaDAOIMP();
//
//        if (dao.agregarRegistro(dto)) {
//            System.out.println("El registro se ha insertado correctamente!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }

////_______________________________________________MODIFICAR UN REGISTRO____________-____________________________________  
//        dto = new MenuSistemaDTO();
//        dto.setId_perfil(4);
//        dto.setDescripcion("PRUEBA DE MODIFICACION");
//        dto.setComentario("mmmmmmmmmmmmmm");
//        dao = new MenuSistemaDAOIMP();
//
//        if (dao.modificarRegistro(dto)) {
//            System.out.println("El registro se ha modificado correctamente!!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }
//
////________________________________________________ELIMINAR UN REGISTRO________________________________________________
//        dto = new MenuSistemaDTO();
//        dto.setId(4);
//        dao = new MenuSistemaDAOIMP();
//
//        if (dao.eliminarRegistro(dto)) {
//            System.out.println("El registro se ha eliminado correctamente!");
//        } else {
//            System.out.println("Ha ocurrido un error en el proceso");
//        }

//________________________________________________LISTAR UN REGISTRO________________________________________________  
        dao = new MenuSistemaDAOIMP();
        dto = dao.recuperarRegistro(3);
        System.out.println("Id: " + dto.getId());
        System.out.println("Descripcion: " + dto.getDescrip());
        System.out.println("Comentario: " + dto.getComentario());
    

////____________________________________________LISTAR TODOS LOS REGISTROS____________________________________________
//        dao = new MenuSistemaDAOIMP();
//        
//          System.out.println("--------------------------LISTA DE TIPOS DE CONTRATO--------------------------");
//
//        for (MenuSistemaDTO dto : dao.recuperarRegistros()) {
//            System.out.println("Id: " + dto.getId_perfil());
//            System.out.println("Descripcion: " + dto.getDescripcion());
//            System.out.println("Comentario: " + dto.getComentario());
//            System.out.println("---------------------------------------------------------------------------");
//        }
//
//
    }

    public static void main(String[] args) {
        new testMenuSistema();

    }

}
