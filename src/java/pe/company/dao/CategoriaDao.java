
package pe.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pe.company.dbase.ConexionDb;
import pe.company.vo.CategoriaVo;

public class CategoriaDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategoriaDao() {
    }
    
    CategoriaVo findById(int id_categoria){
        CategoriaVo categoriaVo = null;
        try {
            conn = ConexionDb.MySQL();
            ps=conn.prepareStatement("select * from categorias where id_categoria=?");
            ps.setInt(1, id_categoria);
            rs=ps.executeQuery();
            
            if(rs.next()){
                categoriaVo = new CategoriaVo();
                categoriaVo.setId_categoria(rs.getInt("id_categoria"));
                categoriaVo.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriaVo;
    }
}
