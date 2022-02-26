package Modelo.DaoImp;

import Modelo.Dao.notificacionesDAO;
import Modelo.Dto.notificacionesDTO;
import Modelo.Dto.ubicacionesDTO;
import Modelo.Dto.usuariosDTO;
import Modelo.Dto.vehiculosDTO;
import Base.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class notificacionesDAOIMP implements notificacionesDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public notificacionesDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(notificacionesDTO notificacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.notificaciones(descripcion, id_vehiculo, id_usuario, id_ubicacion, fecha, hora) VALUES (?, ?, ?, ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, notificacion.getDescripcion());
            ps.setInt(2, notificacion.getVehiculo().getId_vehiculo());
            ps.setInt(3, notificacion.getUsuario().getId_usuario());
            ps.setInt(4, notificacion.getUbicacion().getId_ubicacion());
            ps.setDate(5, notificacion.getFecha());
            ps.setTime(6, notificacion.getHora());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean modificarRegistro(notificacionesDTO notificacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.notificaciones SET descripcion=?, id_vehiculo=?, id_usuario=?, id_ubicacion=?, fecha=?, hora=? WHERE id_notificacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, notificacion.getDescripcion());
            ps.setInt(2, notificacion.getVehiculo().getId_vehiculo());
            ps.setInt(3, notificacion.getUsuario().getId_usuario());
            ps.setInt(4, notificacion.getUbicacion().getId_ubicacion());
            ps.setDate(5, notificacion.getFecha());
            ps.setTime(6, notificacion.getHora());
            ps.setInt(7, notificacion.getId_notificacion());

            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean eliminarRegistro(notificacionesDTO notificacion) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM public.notificaciones WHERE id_notificacion=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, notificacion.getId_notificacion());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public notificacionesDTO recuperarRegistro(Integer id) {
        try {
            notificacionesDTO notificacion = null;
            sql = "SELECT n.id_notificacion, n.descripcion, v.id_vehiculo,\n"
                    + "  v.marca, v.modelo, v.chapa,\n"
                    + "  u.id_usuario, u.nombres, u.apellidos,\n"
                    + "  ub.id_ubicacion, ub.descripcion, n.fecha, n.hora\n"
                    + "  FROM public.notificaciones n\n"
                    + "  INNER JOIN vehiculos v on v.id_vehiculo = n.id_vehiculo\n"
                    + "  INNER JOIN usuarios u on u.id_usuario = n.id_usuario\n"
                    + "  INNER JOIN ubicaciones ub on ub.id_ubicacion = n.id_ubicacion\n"
                    + "  WHERE id_notificacion = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                notificacion = new notificacionesDTO();
                notificacion.setDescripcion(rs.getString("n.descripcion"));
                notificacion.setVehiculo(new vehiculosDTO(rs.getInt("id_vehiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("chapa")));
                notificacion.setUsuario(new usuariosDTO(rs.getInt("id_usuario"), rs.getString("nombres"), rs.getString("apellidos")));
                notificacion.setUbicacion(new ubicacionesDTO(rs.getInt("id_ubicacion"), rs.getString("ub.descripcion")));
                notificacion.setFecha(rs.getDate("fecha"));
                notificacion.setHora(rs.getTime("hora"));
            }
            return notificacion;
        } catch (SQLException ex) {
            Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<notificacionesDTO> recuperarRegistros() {
try {
            notificacionesDTO notificacion;
            List<notificacionesDTO> lista;
            sql = "SELECT n.id_notificacion, n.descripcion, v.id_vehiculo,\n"
                    + "  v.marca, v.modelo, v.chapa,\n"
                    + "  u.id_usuario, u.nombres, u.apellidos,\n"
                    + "  ub.id_ubicacion, ub.descripcion, n.fecha, n.hora\n"
                    + "  FROM public.notificaciones n\n"
                    + "  INNER JOIN vehiculos v on v.id_vehiculo = n.id_vehiculo\n"
                    + "  INNER JOIN usuarios u on u.id_usuario = n.id_usuario\n"
                    + "  INNER JOIN ubicaciones ub on ub.id_ubicacion = n.id_ubicacion\n"
                    + "  WHERE id_notificacion = ?";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                notificacion = new notificacionesDTO();
                notificacion.setDescripcion(rs.getString("n.descripcion"));
                notificacion.setVehiculo(new vehiculosDTO(rs.getInt("id_vehiculo"), rs.getString("marca"), rs.getString("modelo"), rs.getString("chapa")));
                notificacion.setUsuario(new usuariosDTO(rs.getInt("id_usuario"), rs.getString("nombres"), rs.getString("apellidos")));
                notificacion.setUbicacion(new ubicacionesDTO(rs.getInt("id_ubicacion"), rs.getString("ub.descripcion")));
                notificacion.setFecha(rs.getDate("fecha"));
                notificacion.setHora(rs.getTime("hora"));
                lista.add(notificacion);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(notificacionesDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
