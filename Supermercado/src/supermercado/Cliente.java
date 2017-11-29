/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomas
 */
public class Cliente extends Thread {
    Random aleatorio;
    ArrayList<Queue<Cliente>> cajasSupermercado;
    int compra;
    boolean pagado=false;
    long tiempoInicial=0;
    long tiempoFinal=0;


    public Cliente(String nombre,ArrayList<Queue<Cliente>> cajasSupermercado) {
        super(nombre);
        this.cajasSupermercado = cajasSupermercado;
        aleatorio=new Random();
        
    }
    
    public void run(){
        System.out.println("Nuevo cliente "+this.getName()+" llega al supermercado");
        try {
            sleep(aleatorio.nextInt(5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        int caja=aleatorio.nextInt(cajasSupermercado.size());
        System.out.println(this.getName()+" me voy a la caja "+caja);
        compra=aleatorio.nextInt(200);
        cajasSupermercado.get(caja).add(this);
        tiempoInicial=System.currentTimeMillis();
        irApagar();
        
        
    }

    private synchronized void irApagar() {
        while(!pagado){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tiempoFinal=System.currentTimeMillis();
        System.out.println(this.getName()+" terminé, me voy del super y he tardado: "+(tiempoFinal-tiempoInicial)+"mseg");
        
    }
    
    public synchronized int pagado(){
        this.pagado=true;
        notifyAll();
        return compra;
    }
    
    public long tiempoEspera(){
        return System.currentTimeMillis()-tiempoInicial;
    }
    
    
    
}
