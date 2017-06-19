package sv.edu.ues.fca.siammat.servicios;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author galicia
 */
public interface GenericService {
    
    public void save(Serializable entidad);

    /**
     * Elimina la entidad
     *
     * @param entidad
     */
    public void remove(Serializable entidad);

    /**
     * Elimina una lista de entidades
     *
     * @param entidades
     */
    public void remove(List entidades);

    /**
     * Refresca el objeto a su estado original
     *
     * @param entidad
     */
    public void refresh(Serializable entidad);

    /**
     * Guarda una lista de entidades
     *
     * @param entidades Lista de entidades a guardar
     */
    public void save(List entidades);

    /**
     *
     * @param hql Consulta en formato hql
     * @return lista de registros
     */
    public List find(String hql);

    /**
     *
     * @return La conexion activa manejada por hibernate
     * @throws SQLException
     */
    public Connection getConexion();

    public Object getSingle(String query) ;

}
