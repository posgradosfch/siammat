package sv.edu.ues.fca.siammat.filtros;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.text.SimpleDateFormat;


/**
 *
 * @author sscruz
 */
public class BetweenFilterElement<T> extends  FilterElement{
  private T lowerValue;
  private T upperValue;
 
    public BetweenFilterElement(String fieldName,int relOp) {
        super(fieldName,relOp);


    }
  
    public String whereClauseFormat()throws Exception{
        if(this.lowerValue!=null?!this.lowerValue.equals(""):false &&
           this.upperValue!=null?!this.upperValue.equals(""):false){
         if (this.lowerValue.getClass().getSimpleName().equals("String") || this.upperValue.getClass().getSimpleName().equals("Date")) {
          if(this.upperValue.getClass().getSimpleName().equals("Date")){
              SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
              return this.getDbFieldName()+" BETWEEN '"+sdf.format(this.lowerValue)+"' AND '"+sdf.format(this.upperValue)+"'";
          }
          return this.getDbFieldName()+" BETWEEN '"+this.lowerValue+"' AND '"+this.upperValue+"'";
         }
         else
          return this.getDbFieldName()+" BETWEEN "+this.lowerValue+" AND "+this.upperValue;   
        }
        else
         return "";
         //throw new Exception("Se deben completar ambos valores del rango");
      
    }

    public T getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(T lowerValue) {
        this.lowerValue = lowerValue;
    }

    public Object getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(T upperValue) {
        this.upperValue = upperValue;
    }

    @Override
    public void clearValue() {
        this.setLowerValue(null);
        this.setUpperValue(null);
    }
}
