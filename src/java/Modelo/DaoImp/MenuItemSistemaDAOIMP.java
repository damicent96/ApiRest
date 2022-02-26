package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.MenuItemSistemaDAO;
import Modelo.Dto.MenuItemSistemaDTO;
import Modelo.Dto.MenuSistemaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuItemSistemaDAOIMP implements MenuItemSistemaDAO {

    private String sql;
    private Conexion conexion;
    private PreparedStatement ps;
    private ResultSet rs;

    public MenuItemSistemaDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarRegistro(MenuItemSistemaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "INSERT INTO public.menu_items_sistema( descripcion, comentario, id_menu_sistema) VALUES ( ?, ?, ?);";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            ps.setInt(3, dto.getMenuSistema().getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean modificarRegistro(MenuItemSistemaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "UPDATE public.menu_items_sistema SET  descripcion=?, comentario=?, id_menu_sistema=?  WHERE id_menu_items_sistema=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setString(1, dto.getDescrip());
            ps.setString(2, dto.getComentario());
            ps.setInt(3, dto.getMenuSistema().getId());
            ps.setInt(4, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean eliminarRegistro(MenuItemSistemaDTO dto) {
        try {
            conexion.Transaccion(Conexion.TR.INICIAR);
            sql = "DELETE FROM  public.menu_items_sistema  WHERE id_menu_items_sistema=?;";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, dto.getId());
            if (ps.executeUpdate() > 0) {
                conexion.Transaccion(Conexion.TR.CONFIRMAR);
                return true;
            } else {
                conexion.Transaccion(Conexion.TR.CANCELAR);
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            conexion.Transaccion(Conexion.TR.CANCELAR);
            return false;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public MenuItemSistemaDTO recuperarRegistro(Integer id) {
        try {
            MenuItemSistemaDTO dto = null;
            sql = "SELECT A.id_menu_items_sistema, A.descripcion, A.comentario, A.id_menu_sistema, B.descrip AS menu\n"
                + "FROM public.menu_items_sistema A INNER JOIN public.menu_sistema B ON A.id_menu_sistema= B.id_menu_sistema\n"
                + "WHERE A.id_menu_items_sistema = ? ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                dto = new MenuItemSistemaDTO();
                dto.setId(rs.getInt("id_menu_items_sistema"));
                dto.setDescrip(rs.getString("descripcion"));
                dto.setComentario(rs.getString("comentario"));
                dto.setMenuSistema(new MenuSistemaDTO(rs.getInt("id_menu_sistema"), rs.getString("menu")));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<MenuItemSistemaDTO> recuperarRegistros() {
            try {
            MenuItemSistemaDTO dto = null;
            List<MenuItemSistemaDTO> lista;
            sql = "SELECT A.id_menu_items_sistema, A.descripcion, A.comentario, A.id_menu_sistema, B.descrip AS menu\n"
                + "FROM public.menu_items_sistema A INNER JOIN public.menu_sistema B ON A.id_menu_sistema= B.id_menu_sistema ";
            ps = conexion.obtenerConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new MenuItemSistemaDTO();
                dto.setId(rs.getInt("id_menu_items_sistema"));
                dto.setDescrip(rs.getString("descripcion"));
                dto.setComentario(rs.getString("comentario"));
                dto.setMenuSistema(new MenuSistemaDTO(rs.getInt("id_menu_sistema"), rs.getString("menu")));
                lista.add(dto);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conexion.cerrarConexion();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MenuSistemaDAOIMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String aviso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
