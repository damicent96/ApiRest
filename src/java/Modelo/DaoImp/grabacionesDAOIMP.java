package Modelo.DaoImp;

import Modelo.Dao.grabacionesDAO;
import Modelo.Dto.grabacionesDTO;
import Modelo.Dto.vehiculosDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class grabacionesDAOIMP implements grabacionesDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public grabacionesDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(grabacionesDTO grabacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.grabaciones(id_vehiculo, fecha, hora, duracion, tamanho) VALUES (?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, grabacion.getVehiculo().getId_vehiculo());
            ps.setDate(2, grabacion.getFecha());
            ps.setTime(3, grabacion.getHora());
            ps.setTime(4, grabacion.getDuracion());
            ps.setString(5, grabacion.getTamaño().toString());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean modificarRegistro(grabacionesDTO grabacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.grabaciones SET id_vehiculo=?, fecha=?, hora=?, duracion=?, tamanho=? WHERE id_grabacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, grabacion.getVehiculo().getId_vehiculo());
            ps.setDate(2, grabacion.getFecha());
            ps.setTime(3, grabacion.getHora());
            ps.setTime(4, grabacion.getDuracion());
            ps.setString(5, grabacion.getTamaño().toString());
            ps.setInt(6, grabacion.getId_grabacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean eliminarRegistro(grabacionesDTO grabacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.grabaciones WHERE id_grabacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, grabacion.getId_grabacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public grabacionesDTO recuperarRegistro(Integer id) {
        try {
            grabacionesDTO grabacion = null;
            sql = "SELECT g.id_grabacion, v.id_vehiculo, v.marca, v.modelo, v.chapa , g.fecha, g.hora, g.duracion, g.tamanho\n"
                    + "  FROM public.grabaciones g\n"
                    + "  INNER JOIN public.vehiculos v on v.id_vehiculo = g.id_vehiculo\n"
                    + "  WHERE id_grabacion = ?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                grabacion = new grabacionesDTO();
                grabacion.setId_grabacion(rs.getInt("id_grabacion"));
                grabacion.setVehiculo(new vehiculosDTO(rs.getInt("id_vehiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("chapa")));
                grabacion.setFecha(rs.getDate("fecha"));
                grabacion.setHora(rs.getTime("hora"));
                grabacion.setDuracion(rs.getTime("usuario"));
                //grabacion.setTamaño(rs.getString("tamanho"));
            }
            return grabacion ;
        } catch (SQLException ex) {
            Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<grabacionesDTO> recuperarRegistros() {
        try {
            grabacionesDTO grabacion;
            List<grabacionesDTO> lista;
            sql = "SELECT g.id_grabacion, v.id_vehiculo, v.marca, v.modelo, v.chapa , g.fecha, g.hora, g.duracion, g.tamanho\n"
                    + "  FROM public.grabaciones g\n"
                    + "  INNER JOIN public.vehiculos v on v.id_vehiculo = g.id_vehiculo ;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                grabacion = new grabacionesDTO();
                grabacion.setId_grabacion(rs.getInt("id_grabacion"));
                grabacion.setVehiculo(new vehiculosDTO(rs.getInt("id_vehiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("chapa")));
                grabacion.setFecha(rs.getDate("fecha"));
                grabacion.setHora(rs.getTime("hora"));
                grabacion.setDuracion(rs.getTime("usuario"));
                //grabacion.setTamaño(rs.getString("tamanho"));
                lista.add(grabacion);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(grabacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
