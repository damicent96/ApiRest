
package Modelo.DaoImp;

import Modelo.Dao.ubicacionesDAO;
import Modelo.Dto.ubicacionesDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ubicacionesDAOIMP implements ubicacionesDAO{
    
    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public ubicacionesDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(ubicacionesDTO ubicacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.ubicaciones(descripcion, latitud, longitud) VALUES (?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, ubicacion.getDescripcion());
            ps.setString(2, ubicacion.getLatitud());
            ps.setString(3, ubicacion.getLongitud());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean modificarRegistro(ubicacionesDTO ubicacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.ubicaciones SET descripcion=?, latitud=?, longitud=? WHERE id_ubicacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, ubicacion.getDescripcion());
            ps.setString(2, ubicacion.getLatitud());
            ps.setString(3, ubicacion.getLongitud());
            ps.setInt(4, ubicacion.getId_ubicacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean eliminarRegistro(ubicacionesDTO ubicacion) {
            try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.ubicaciones WHERE id_ubicacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, ubicacion.getId_ubicacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public ubicacionesDTO recuperarRegistro(Integer id_ubicacion) {
            try {
            ubicacionesDTO ubicacion = null;
            sql = "SELECT id_ubicacion, descripcion, latitud, longitud FROM public.ubicaciones WHERE id_ubicacion=?;;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id_ubicacion);
            rs = ps.executeQuery();
            if (rs.next()) {
              ubicacion = new ubicacionesDTO();  
              ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
              ubicacion.setDescripcion(rs.getString("descripcion"));
              ubicacion.setLatitud(rs.getString("latitud"));
              ubicacion.setLongitud(rs.getString("longitud"));
            }
            return ubicacion;
        } catch (SQLException ex) {
            Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }

    @Override
    public List<ubicacionesDTO> recuperarRegistros() {
        try {
            List<ubicacionesDTO> lista = null;
            ubicacionesDTO ubicacion = null;
            sql = "SELECT id_ubicacion, descripcion, latitud, longitud FROM public.ubicaciones ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
              ubicacion = new ubicacionesDTO();  
              ubicacion.setId_ubicacion(rs.getInt("id_ubicacion"));
              ubicacion.setDescripcion(rs.getString("descripcion"));
              ubicacion.setDescripcion(rs.getString("latitud"));
              ubicacion.setDescripcion(rs.getString("longitud"));
              lista.add(ubicacion);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ubicacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
