package code;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cliente implements Runnable{
	
	private CajaQueue caja;
	private int id;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public Cliente(CajaQueue caja, int id) {
		this.caja = caja;
		this.id = id;
	}

	@Override
	public void run() {
		try {
        	System.out.printf("%s - Entra cliente numero %d\n", LocalTime.now().format(dateTimeFormatter), id);
        	TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1,4));;
            caja.cola(id);
        }
		catch (InterruptedException e) {
            System.out.printf("%s -> I've been interrupted while printing document\n", Thread.currentThread().getName());
        }
	}

}
