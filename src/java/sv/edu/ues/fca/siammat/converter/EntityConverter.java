package sv.edu.ues.fca.siammat.converter;

import java.lang.reflect.Field;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.sf.ehcache.hibernate.management.impl.BeanUtils;
import org.hibernate.proxy.HibernateProxy;

@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

    private String objectId;
    private String className;

    public EntityConverter(String objectId) {
        this.objectId = objectId;
    }
    
    public EntityConverter(){
    
    }
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && value.trim().length() > 0) {
            List hijos =getChildren(uic);
            if(hijos==null)
                return null;
            for (Object child : hijos) {
                    if(objectId==null){
                        objectId=getId(child);
                    }
                    
                    String valor = String.valueOf(BeanUtils.getBeanProperty(child, objectId));
                    if (valor.equals(value)) {
                        return child;
                    }
            }
        }
        return null;
    }

    private List getChildren(UIComponent uic) {
        for (UIComponent elemento : uic.getChildren()) {
            if (elemento instanceof UISelectItems) {
                UISelectItems u = (UISelectItems) elemento;
                return (List) u.getAttributes().get("value");
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {

        if (object != null && !(object instanceof String)) {
            if (objectId == null) {
                objectId = getId(object);
            }

                Object x = BeanUtils.getBeanProperty(object, objectId);//getMethod(object).invoke(object);
                return String.valueOf(x);
        }
        return null;
    }

    private String getId(Object object) {
        Class clase =object.getClass();
        if(object instanceof HibernateProxy){
           clase= ((HibernateProxy)object).getHibernateLazyInitializer().getPersistentClass();
        }
        
        if (clase.getAnnotation(javax.persistence.Entity.class) == null) {
            return null;
        }

        for (Field field : clase.getDeclaredFields()) {
            if (field.getAnnotation(javax.persistence.Id.class) != null) {
                return field.getName();
            }
        }

        return null;
    }
}
