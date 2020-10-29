package code;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Caja {
	
	private final int cajaNumber;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Caja(int cajaNumber) {
        this.cajaNumber = cajaNumber;
    }
    
    public void comprar(int id) throws InterruptedException{
    	System.out.printf("%s - Pasando cliente numero %d productos en caja %s\n", LocalTime.now().format(dateTimeFormatter), id, cajaNumber+1);
    	TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,5));;
    	System.out.printf("%s - Saliendo cliente numero %d de caja %s\n", LocalTime.now().format(dateTimeFormatter), id, cajaNumber+1);
    }

}
