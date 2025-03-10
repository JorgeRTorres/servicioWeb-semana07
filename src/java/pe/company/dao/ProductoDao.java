
package pe.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import pe.company.dbase.ConexionDb;
import pe.company.vo.CategoriaVo;
import pe.company.vo.ProductoVo;

public class ProductoDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private CategoriaDao categoriaDao = new CategoriaDao();

    public ProductoDao() {
    }
    
    public void insert(ProductoVo productoVo){
        try {
            conn = ConexionDb.MySQL();
            ps=conn.prepareStatement("insert into productos(nombre, marca, precio, stock, id_categoria) values (?,?,?,?,?)");
            ps.setString(1, productoVo.getNombre());
            ps.setString(2, productoVo.getMarca());
            ps.setDouble(3, productoVo.getPrecio());
            ps.setInt(4, productoVo.getStock());
            ps.setInt(5, productoVo.getCategoria().getId_categoria());
            
            int rows=ps.executeUpdate();
            if(rows!=1)
                throw new Exception("Error!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Collection<ProductoVo> findAll(){
        List<ProductoVo> list = new ArrayList<>();
        try {
            conn=ConexionDb.MySQL();
            ps=conn.prepareStatement("select * from productos");
            rs=ps.executeQuery();
            
            while(rs.next()){
                ProductoVo productoVo = new ProductoVo();
                productoVo.setId_producto(rs.getInt("id_producto"));
                productoVo.setNombre(rs.getString("nombre"));
                productoVo.setMarca(rs.getString("marca"));
                productoVo.setPrecio(rs.getDouble("precio"));
                productoVo.setStock(rs.getInt("stock"));
                
                CategoriaVo categoriaVo = categoriaDao.findById(rs.getInt("id_categoria"));
                productoVo.setCategoria(categoriaVo);
                
                list.add(productoVo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
