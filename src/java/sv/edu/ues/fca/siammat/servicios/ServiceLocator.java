package sv.edu.ues.fca.siammat.servicios;

/**
 *
 * @author David
 */
public class ServiceLocator {
    private static GenericService genericService;

    public static GenericService getBasicService() {
        if(genericService==null){
            genericService=new GenericService();
        }
        return genericService;
    }


}
