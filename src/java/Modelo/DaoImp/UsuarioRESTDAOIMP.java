package Modelo.DaoImp;

import Base.Conexion;
import Base.Util;
import Modelo.Dao.UsuarioRESTDAO;
import Modelo.Dto.UsuarioRESTDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioRESTDAOIMP implements UsuarioRESTDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    private String token;

    public UsuarioRESTDAOIMP() {
        conexion = new Conexion();
        token = "";
    }

    @Override
    public boolean validarUsuario(UsuarioRESTDTO dto) {
        try {
            
            sql = "SELECT id FROM public.usuarios_rest "
                + "WHERE usuario = ? AND clave = ?  AND nro_ip = ? AND estado = TRUE AND id_empresa = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getUsuario());
            ps.setString(2, dto.getClave());
            ps.setString(3, dto.getNroIp());
            ps.setInt(4, dto.getIdEmpresa());
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("reconoce los datos del usuario ");
                token = Util.generarToken();
                int idUsuario= rs.getInt("id");
                conexion.Transaccion(Conexion.TR.INICIAR);
                sql = "UPDATE public.usuarios_rest SET token=?, creacion_token=now() WHERE id = ?; ";
                ps = conexion.obtenerConexion().prepareStatement(sql);
                ps.setString(1, token);
                ps.setInt(2, idUsuario);
                if (ps.executeUpdate() > 0) {
                    conexion.Transaccion(Conexion.TR.CONFIRMAR);
                    return true;
                } else {
                    conexion.Transaccion(Conexion.TR.CANCELAR);
                    return false;
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error " + ex.getMessage());
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public String getToken(){
        if (!token.isEmpty()) {
            return token;
        }
        return null;
    }
    
    @Override
    public boolean agregarRegistro(UsuarioRESTDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificarRegistro(UsuarioRESTDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarRegistro(UsuarioRESTDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioRESTDTO recuperarRegistro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioRESTDTO> recuperarRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
