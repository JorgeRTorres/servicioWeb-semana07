
package pe.company.ws;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pe.company.dao.ProductoDao;
import pe.company.vo.ProductoVo;

@Path("producto")
public class ProductoRest {

    @Context
    private UriInfo context;
    private ProductoDao productoDao= new ProductoDao();

    public ProductoRest() {
    }

    @Path("/listar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductoVo> listar() {
        return productoDao.findAll();
    }

    @Path("/agregar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregar(ProductoVo producto) {
        productoDao.insert(producto);
        return Response.ok().entity(producto).build();
    }
}