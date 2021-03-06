import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Simulatore {

		public static void main(String[] args) {
			ScannerPorte scanner = new ScannerPorte();
			StorageNode taskNode = new StorageNode(scanner);

			if (args.length == 1)
				taskNode = new StorageNode(scanner, args[0]);
			else
				taskNode = new StorageNode (scanner);

			ExecutorService exec = Executors.newCachedThreadPool();

			double randd, randdTime, randObjd;

			int randi, randiTime, randObji;

			for (int i = 0;i < 5;i++)
				exec.execute(taskNode);

			try {
					while (true) {
							randdTime = Math.random() * 10000; //dormiamo al massimo 10 secondi
							randiTime = (int) randdTime;
							System.out.println("Simulatore inattivo per " + randiTime);
							Thread.sleep(randiTime);
							System.out.println("Inizio generazione elementi casuali");
							randd = Math.random() * 6; //creiamo al massimo 5 oggetti
							randi = (int) randd;

							for (int i = 0;i < randi;i++) {
									randObjd = Math.random() * 2;
									randObji = (int) randObjd;

									if (randObji == 1) //creo un client
										{Client taskClient;

											if (args.length == 1)
												taskClient = new Client(scanner, args[0]);
											else
												taskClient = new Client(scanner);

											exec.execute(taskClient);
										} else //creo un nodo
										exec.execute(taskNode);
								}
						}

				} catch (Exception e) {
					e.printStackTrace();
				}


		}
	}
