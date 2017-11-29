/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas
 */
public class Supermercado {
    static ArrayList<Queue<Cliente>> cajas;
    static int numCajas;
    static int numClientes;
    static int total;
    static long tiempoMedioCliente=0;
    static Thread t;
    
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       
      cajas=new ArrayList<Queue<Cliente>>();
      numCajas=1;
      numClientes=34;
      
      for(int i=0;i<numCajas;i++){
          cajas.add(new LinkedList<Cliente>());
      }
      
      for(int i=0;i<numClientes;i++){
          new Cliente("cliente"+i,cajas).start();
      }
      System.currentTimeMillis();
      /*
      while(numClientes>0){
          for(Queue<Cliente> caja:cajas){
              if(!caja.isEmpty()){
                  Cliente cliente=caja.poll();
                  Thread.sleep(1000);
                  total=total+cliente.pagado();
                  tiempoMedioCliente=tiempoMedioCliente+cliente.tiempoEspera();
                  
                  numClientes--;
              }
          }
          
      }
      */
      ExecutorService execService=Executors.newFixedThreadPool(5);
      while(numClientes>0){
          for(Queue<Cliente> caja:cajas){
              if(!caja.isEmpty()){
                  
                  Cliente cliente=caja.poll();
                  
                  t=new Thread(new Runnable() {
                      @Override
                      public void run() {
                          try {
                              Thread.sleep(1000);
                              total=total+cliente.pagado();
                              tiempoMedioCliente=tiempoMedioCliente+cliente.tiempoEspera();
                          } catch (InterruptedException ex) {
                              Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
                          }
                      }
                  });
                  execService.execute(t);
                  
                      
                  
                  
                  
                  numClientes--;
              }
          }
          
      }
     
      execService.shutdown();
      while(!execService.isTerminated()){
          
      }

        System.out.println("El tiempo medio de espera de los clientes ha sido: "+(tiempoMedioCliente/5));
      
            
    }
    
}
