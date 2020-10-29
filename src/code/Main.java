package code;

public class Main {

	public static void main(String[] args) {
		CajaQueue cajaQueue = new CajaQueue(5);
        Thread[] clienteThreads = new Thread[50];
        for (int i = 0; i < 50; i++) {
            clienteThreads[i] = new Thread(new Cliente(cajaQueue, i+1));
        }
        for (int i = 0; i < 50; i++) {
            clienteThreads[i].start();
        }
	}

}
