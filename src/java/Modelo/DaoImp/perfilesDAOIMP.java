
package Modelo.DaoImp;

import Modelo.Dao.perfilesDAO;
import Modelo.Dto.perfilesDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class perfilesDAOIMP implements perfilesDAO{

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public perfilesDAOIMP() {
        conexion = new Conexion();
    }
    
    
    @Override
    public boolean agregarRegistro(perfilesDTO perfil) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.pperfiles(descripcion, comentario) VALUES (?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, perfil.getDescripcion());
            ps.setString(2, perfil.getComentario());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }

    @Override
    public boolean modificarRegistro(perfilesDTO perfil) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.perfiles SET descripcion= ?, comentario= ? WHERE id_perfil= ?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, perfil.getDescripcion());
            ps.setString(2, perfil.getComentario());
            ps.setInt(3, perfil.getId_perfil());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean perfil_disable(Integer id) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.perfiles SET estado=? WHERE id_perfil=?;";
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
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public perfilesDTO recuperarRegistro(Integer id) {
        try {
            perfilesDTO perfil = null;
            sql = " SELECT id_perfil, descripcion, comentario FROM public.perfiles WHERE id_perfil=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
              perfil = new perfilesDTO();  
              perfil.setId_perfil(rs.getInt("id_perfil"));
              perfil.setDescripcion(rs.getString("descripcion"));
              perfil.setComentario(rs.getString("comentario"));
            }
            return perfil;
        } catch (SQLException ex) {
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<perfilesDTO> recuperarRegistros() {
        try {
            List<perfilesDTO> lista = null;
            perfilesDTO perfil = null;
            sql = " SELECT id_perfil, descripcion, comentario FROM public.perfiles ORDER BY id_perfil ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
              perfil = new perfilesDTO();  
              perfil.setId_perfil(rs.getInt("id_perfil"));
              perfil.setDescripcion(rs.getString("descripcion"));
              perfil.setComentario(rs.getString("comentario"));
              lista.add(perfil);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    @Override
    public boolean eliminarRegistro(perfilesDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.perfiles WHERE id_perfil=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getId_perfil());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(perfilesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
