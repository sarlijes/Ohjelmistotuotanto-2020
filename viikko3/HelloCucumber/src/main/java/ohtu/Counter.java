
package ohtu;

public class Counter {
   int value = 0;

   public void increase(){
        value++;
   } 
   
   public void reset(){
        value = 0;
   }    
   
   public void increment(int a){
        value += a;
   } 
   
   public int value(){
       return value;
   }
}
