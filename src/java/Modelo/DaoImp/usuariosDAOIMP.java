package Modelo.DaoImp;

import Modelo.Dao.usuariosDAO;
import Modelo.Dto.perfilesDTO;
import Modelo.Dto.usuariosDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuariosDAOIMP implements usuariosDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public usuariosDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(usuariosDTO usuario) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.usuarios(id_perfil, nombres, apellidos, usuario, contrasena) VALUES (?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, usuario.getPerfil().getId_perfil());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContrasena());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean modificarRegistro(usuariosDTO usuario) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.usuarios SET id_perfil=?, nombres=?, apellidos=?, usuario=?, contrasena=? WHERE id_usuario=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, usuario.getPerfil().getId_perfil());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getContrasena());
            ps.setInt(6, usuario.getId_usuario());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean user_disable(Integer id) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.usuarios SET estado=? WHERE id_usuario=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public usuariosDTO recuperarRegistro(Integer id) {
        try {
            usuariosDTO usuario = null;
            sql = "SELECT u.id_usuario, u.id_perfil, p.descripcion as perfil, u.nombres, u.apellidos, u.usuario, u.contrasena, u.estado\n"
                    + " FROM public.usuarios u\n"
                    + " INNER JOIN public.perfiles p on p.id_perfil = u.id_perfil\n"
                    + " WHERE id_usuario= ? AND u.estado=true;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new usuariosDTO();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setPerfil(new perfilesDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
            }
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<usuariosDTO> recuperarRegistros() {
        try {
            usuariosDTO usuario;
            List<usuariosDTO> lista;
            sql = "SELECT u.id_usuario, u.id_perfil, p.descripcion as perfil, u.nombres, u.apellidos, u.usuario, u.contrasena\n"
                    + " FROM public.usuarios u\n"
                    + " INNER JOIN public.perfiles p on p.id_perfil = u.id_perfil\n"
                    + " WHERE u.estado=true;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                usuario = new usuariosDTO();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setPerfil(new perfilesDTO(rs.getInt("id_perfil"), rs.getString("perfil")));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(usuariosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarRegistro(usuariosDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
