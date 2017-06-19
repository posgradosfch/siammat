package sv.edu.ues.fca.siammat.filtros;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sscruz
 */
public class FilterElementGroup implements Serializable{
  private List<Object> filters;
  private int relationalOperator;

    public FilterElementGroup() {
        this.filters=new ArrayList();
    }
  
  
  
  public void addFilterElement(FilterElement fe){
      this.filters.add(fe);
  }
  
  public void addFilterGroup(FilterElementGroup fg,int relOp){
      this.filters.add(fg);
      fg.setRelationalOperator(relOp);
  }  
  
  public void clearFilters(){
     for(Object ob : this.filters){
        FilterElement fe=(FilterElement)ob;
        fe.clearValue();
     }
  }
  
  public String generateWhereClause(){
      String wc="";
      for(Object ob:this.filters){
        try{

          if(ob.getClass().getSuperclass().getSimpleName().equals("FilterElement")){ 
           FilterElement fe=(FilterElement)ob;
           String tmp=fe.whereClauseFormat();
           if(wc.equals(""))
            wc+=fe.whereClauseFormat();
           else
            if(!tmp.equals(""))
             wc+=((fe.getRelationalOperator()==1?" AND":" OR")+" "+tmp);
           }
          else{
           FilterElementGroup fg=(FilterElementGroup)ob;
           String tmp=fg.generateWhereClause();

           if(wc.equals("")){
              if(!tmp.equals(""))
               wc="("+tmp+")";
            }
           else
            if(!tmp.equals(""))
              wc+=((fg.getRelationalOperator()==1?" AND":" OR")+" ("+tmp+")");   
          }
        }catch(Exception e){
            
        }
      }
      
      return wc;
  }

    public int getRelationalOperator() {
        return relationalOperator;
    }

    public void setRelationalOperator(int relationalOperator) {
        this.relationalOperator = relationalOperator;
    }
}
