package sv.edu.ues.fca.siammat.filtros;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 *
 * @author sscruz
 */
public class SimpleFilterElement<T> extends FilterElement {

    private T value;
    private String compareSign;
    private boolean upperCase;
    private boolean treatAsString=false;

    public SimpleFilterElement(String fieldName, int relOp, String compareSign) {
        super(fieldName,  relOp);
        /*switch (fieldType) {
            case FilterElement.INTEGER:
                this.value = (Integer) value;
                break;
            case FilterElement.FLOAT:
                this.value = (Float) value;
                break;
            case FilterElement.DOUBLE:
                this.value = (Double) value;
                break;
            case FilterElement.LONG:
                this.value = (Long) value;
                break;
            case FilterElement.DATE:
                this.value = (Date) value;
                break;
            case FilterElement.STRING:
                this.value = (String) value;
                break;
        }*/
        this.compareSign = compareSign;

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String whereClauseFormat() throws Exception {
        if (!this.compareSign.equals("")) {/* || this.compareSign.equals("!=") || this.compareSign.equals("<>") || this.compareSign.equals("<") ||
            this.compareSign.equals(">") || this.compareSign.equals("<=") || this.compareSign.equals(">=") || this.compareSign.equals("IS") ||
            this.compareSign.equals("IS NOT"))*/

            if (this.value != null ? !this.value.equals("") : false) {
                if(!this.value.getClass().getSimpleName().equals("String") && this.compareSign.toUpperCase().equals("LIKE"))
                    throw new Exception("Operador de comparación inválido para el tipo de datos.");
                
                if (this.value.getClass().getSimpleName().equals("String") || this.value.getClass().getSimpleName().equals("Date") || this.treatAsString) {
                    if (this.value.getClass().getSimpleName().equals("Date")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        return this.getDbFieldName() + this.compareSign + "'" + sdf.format(this.value) + "'";
                    }
                    if(this.compareSign.toUpperCase().equals("LIKE"))
                      return (this.getDbFieldName() +" "+ this.compareSign + " '%" + ((!this.upperCase)?this.value:String.valueOf(this.value).toUpperCase()) + "%'");
                    return this.getDbFieldName() + this.compareSign + "'" + ((!this.upperCase)?this.value:String.valueOf(this.value).toUpperCase()) + "'";
                } else {
                    return this.getDbFieldName() + this.compareSign + this.value;
                }
            }
        } else {
            throw new Exception("Operador de comparación desconocido o inválido.");
        }
        return "";
    }

    public boolean isUpperCase() {
        return upperCase;
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

    /**
     * @return the treatAsString
     */
    public boolean isTreatAsString() {
        return treatAsString;
    }

    /**
     * @param treatAsString the treatAsString to set
     */
    public void setTreatAsString(boolean treatAsString) {
        this.treatAsString = treatAsString;
    }

    @Override
    public void clearValue() {
        this.setValue(null);
    }


}
