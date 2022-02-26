package Modelo.DaoImp;

import Base.Conexion;
import Modelo.Dao.TokenDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAOIMP implements TokenDAO {

    private Conexion conexion;
    private String query, msj;
    private ResultSet rs;
    private PreparedStatement ps;

    public TokenDAOIMP() {
        conexion = new Conexion();
    }

    @Override
    public boolean verificarToken(String token) {
        try {
            query = "SELECT id FROMM public.usuarios_rest WHERE token = ? ;";
            ps = conexion.obtenerConexion().prepareStatement(query);
            ps.setString(1, token);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                msj = "Token no existe";
                return false;
            }

        } catch (SQLException ex) {
            msj = "No se pudo recuperar el registro " + ex.getMessage();
            return false;
        }

    }

}
