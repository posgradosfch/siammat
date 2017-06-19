package sv.edu.ues.fca.siammat.filtros;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sscruz
 */
public abstract class FilterElement implements Serializable{
    private boolean mandatory;
    private String dbFieldName;
    //private int fieldType;
    private int relationalOperator;
    public static final int AND = 1;
    public static final int OR = 2;
    public static final int INTEGER = 1;
    public static final int LONG = 2;
    public static final int DOUBLE = 3;
    public static final int FLOAT = 4;
    public static final int DATE = 5;
    public static final int STRING = 6;

    public FilterElement(String dbFieldName, int relationalOperator) {
        this.dbFieldName = dbFieldName;
     //   this.fieldType = fieldType;
        this.relationalOperator = relationalOperator;
    }

    public String getDbFieldName() {
        return dbFieldName;
    }

/*    public int getFieldType() {
        return fieldType;
    }
*/
    public int getRelationalOperator() {
        return relationalOperator;
    }

    public abstract String whereClauseFormat() throws Exception;

    public abstract void clearValue();

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    
    
}
