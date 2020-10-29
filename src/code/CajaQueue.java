package code;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CajaQueue {
	
	private static final int NO_CAJA = -1;
	
	private final Semaphore semaphore;
	private final Lock reentrantLock = new ReentrantLock(true);
	private final Caja[] cajas;
    private final boolean[] cajasAvailable;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public CajaQueue(int numeroCajas) {
    	semaphore = new Semaphore(numeroCajas, true);
    	cajas = new Caja[numeroCajas];
    	cajasAvailable = new boolean[numeroCajas];
    	for(int i = 0; i < numeroCajas; i++) {
    		cajas[i] = new Caja(i);
    		cajasAvailable[i] = true;
    	}
    }
    
    public void cola(int id) throws InterruptedException{
    	try {
    		semaphore.acquire();
        	System.out.printf("%s - Accede cliente numero %d en cajeros\n", LocalTime.now().format(dateTimeFormatter), id);
    		int cajaNumber = selectCaja();
    		if(cajaNumber != NO_CAJA) {
    			cajas[cajaNumber].comprar(id);
        		cajasAvailable[cajaNumber] = true;
    		}
    	}
    	finally {
    		semaphore.release();
    	}
    }
    
    private int selectCaja() {
    	reentrantLock.lock();
    	try {
            for (int i = 0; i < cajas.length; i++) {
                if (cajasAvailable[i]) {
                    cajasAvailable[i] = false;
                    return i;
                }
            }
        }
    	finally {
            reentrantLock.unlock();
        }
        return NO_CAJA;
    }

}
