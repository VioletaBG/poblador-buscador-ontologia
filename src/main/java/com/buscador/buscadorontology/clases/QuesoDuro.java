package com.buscador.buscadorontology.clases;

public class QuesoDuro extends Queso{
    private String envejecimiento;

    public QuesoDuro(String id,String d, String p, String n,String pa,String e) {
        super(id,d,p,n,pa);  
        envejecimiento=e;    
   }

   public String getEnvejecimiento() {
       return envejecimiento;
   }

   public void setEnvejecimiento(String envejecimiento) {
       this.envejecimiento = envejecimiento;
   }


	
   

}
