
package Modelo.DaoImp;

import Modelo.Dao.vehiculosDAO;
import Modelo.Dto.vehiculosDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class vehiculosDAOIMP implements vehiculosDAO{
    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public vehiculosDAOIMP(){
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(vehiculosDTO vehiculo) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.vehiculos(marca, modelo, chapa) VALUES ( ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setString(3, vehiculo.getChapa());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public boolean modificarRegistro(vehiculosDTO vehiculo) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.vehiculos SET marca=?, modelo=?, chapa=? WHERE id_vehiculo=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setString(3, vehiculo.getChapa());
            ps.setInt(4, vehiculo.getId_vehiculo());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean eliminarRegistro(vehiculosDTO vehiculo) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.vehiculos WHERE id_vehiculo=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, vehiculo.getId_vehiculo());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;                        
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public vehiculosDTO recuperarRegistro(Integer id) {
    try {
            vehiculosDTO vehiculo = null;
            sql = " SELECT id_vehiculo, marca, modelo, chapa FROM public.vehiculos WHERE id_vehiculo=? ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
              vehiculo = new vehiculosDTO();  
              vehiculo.setId_vehiculo(rs.getInt("id_vehiculo"));
              vehiculo.setMarca(rs.getString("marca"));
              vehiculo.setModelo(rs.getString("modelo"));
              vehiculo.setChapa(rs.getString("chapa"));
            }
            return vehiculo;
        } catch (SQLException ex) {
            Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<vehiculosDTO> recuperarRegistros() {
        try {
            List<vehiculosDTO> lista = null;
            vehiculosDTO vehiculo = null;
            sql = "SELECT id_vehiculo, marca, modelo, chapa FROM public.vehiculos;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
              vehiculo = new vehiculosDTO();  
              vehiculo.setId_vehiculo(rs.getInt("id_vehiculo"));
              vehiculo.setMarca(rs.getString("marca"));
              vehiculo.setModelo(rs.getString("modelo"));
              vehiculo.setChapa(rs.getString("chapa"));
              lista.add(vehiculo);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        finally{
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(vehiculosDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
