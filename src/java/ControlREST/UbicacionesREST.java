package ControlREST;

import Base.Util;
import Modelo.Dao.TokenDAO;
import Modelo.DaoImp.ubicacionesDAOIMP;
import Modelo.DaoImp.TokenDAOIMP;
import Modelo.DaoImp.UsuarioRESTDAOIMP;
import Modelo.Dto.ubicacionesDTO;
import Modelo.Dto.RespuestaREST;
import Modelo.Dto.UsuarioRESTDTO;
import com.google.gson.Gson;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("ubicaciones")
public class UbicacionesREST {

    private ubicacionesDTO dto;
    private ubicacionesDAOIMP dao;
    private RespuestaREST respuestaDTO;
    private TokenDAO tokenDAO;

    @GET //metodo utilizado para las consultas, envio de datos.
    @Path("saludo")
    @Produces(MediaType.TEXT_PLAIN) //para ver que devuelve, em este caso un texto plano 
    public String test() {
        return " Bienvenidos a la Aplicación del REST ";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(String test) {
        return " Dato enviado desde el REST " + test;
    }

    @POST
    @Path("usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validarUsuario(InputStream json) {
        respuestaDTO = new RespuestaREST();
        Gson gson = new Gson();
        UsuarioRESTDTO usuarioDTO;
        usuarioDTO = gson.fromJson(Util.getJson(json), UsuarioRESTDTO.class);
        if (usuarioDTO != null) {
            System.out.println("usuario " + usuarioDTO.getUsuario());
            System.out.println("clave " + usuarioDTO.getClave());
            System.out.println("ip " + usuarioDTO.getNroIp());
        }
        UsuarioRESTDAOIMP usuarioDAO = new UsuarioRESTDAOIMP();
        if (usuarioDAO.validarUsuario(usuarioDTO) == true) {
            respuestaDTO.setMensaje("Token generado en forma exitosa");
            respuestaDTO.setToken(usuarioDAO.getToken());
            return new Gson().toJson(respuestaDTO);
        } else {
            respuestaDTO.setMensaje("Error durante la Operación");
            return new Gson().toJson(respuestaDTO);
        }
    }

    @GET
    @Path("registros")
    @Produces(MediaType.APPLICATION_JSON)
    public String recuperarRegistros() {
        dao = new ubicacionesDAOIMP();
        return new Gson().toJson(dao.recuperarRegistros());
    }

    @GET
    @Path("registro")
    @Produces(MediaType.APPLICATION_JSON)
    public String recuperarRegistro(@QueryParam("id") Integer id, @QueryParam("token") String token) {
        respuestaDTO = new RespuestaREST();
        tokenDAO = new TokenDAOIMP();
        if (tokenDAO.verificarToken(token) == true) {
            dao = new ubicacionesDAOIMP();
            ubicacionesDTO dto = dao.recuperarRegistro(id);
            if (dto != null) {
                return new Gson().toJson(dto);
            } else {
                respuestaDTO.setMensaje("Valor enviado no localizado");
                return new Gson().toJson(respuestaDTO);
            }
        } else {
            respuestaDTO.setMensaje("Token no valido");
            return new Gson().toJson(respuestaDTO);
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String insertar(InputStream json, @QueryParam("token") String token) {
        respuestaDTO = new RespuestaREST();
        tokenDAO = new TokenDAOIMP();
        Gson gson = new Gson();
        dto = gson.fromJson(Util.getJson(json), ubicacionesDTO.class);
        if (tokenDAO.verificarToken(token) == true) {
            dao = new ubicacionesDAOIMP();
            if (dao.agregarRegistro(dto) == true) {
                respuestaDTO.setMensaje("Operación Exitosa");
                return new Gson().toJson(respuestaDTO);
            } else {
                respuestaDTO.setMensaje("Error durante la Operación");
                return new Gson().toJson(respuestaDTO);
            }
        } else {
            respuestaDTO.setMensaje("Token no valido");
            return new Gson().toJson(respuestaDTO);
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String modificacion(InputStream json, @QueryParam("token") String token) {
        respuestaDTO = new RespuestaREST();
        tokenDAO = new TokenDAOIMP();
        Gson gson = new Gson();
        dto = gson.fromJson(Util.getJson(json), ubicacionesDTO.class);
        if (tokenDAO.verificarToken(token) == true) {
            dao = new ubicacionesDAOIMP();
            if (dao.modificarRegistro(dto) == true) {
                respuestaDTO.setMensaje("Operación Exitosa");
                return new Gson().toJson(respuestaDTO);
            } else {
                respuestaDTO.setMensaje("Error durante la Operación");
                return new Gson().toJson(respuestaDTO);
            }
        } else {
            respuestaDTO.setMensaje("Token no valido");
            return new Gson().toJson(respuestaDTO);
        }

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(@QueryParam("id") Integer id, @QueryParam("token") String token) {
        respuestaDTO = new RespuestaREST();
        tokenDAO = new TokenDAOIMP();
        if (tokenDAO.verificarToken(token) == true) {
            dao = new ubicacionesDAOIMP();
            Boolean resp = dao.eliminarRegistro(new ubicacionesDTO(id));
            if (resp == false) {
                respuestaDTO.setMensaje("Error durante la eliminación del registro");
                return new Gson().toJson(respuestaDTO);
            } else {
                respuestaDTO.setMensaje("Registro eliminado en forma exitosa");
                return new Gson().toJson(respuestaDTO);
            }

        } else {
            respuestaDTO.setMensaje("Token no valido");
            return new Gson().toJson(respuestaDTO);
        }

    }
}
