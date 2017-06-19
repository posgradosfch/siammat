package sv.edu.ues.fca.siammat.servicios;

/**
 *
 * @author David
 */
public class ServiceLocator {
//    private static GenericService genericService;
    private static GenericService genericService;

    public static GenericService getBasicService() {
        if(genericService==null){
            genericService = new GenericServiceImplPU("SIAMMATPU");
            //genericService = new GenericServiceImpl();
        }
        return genericService;
    }
 
}
