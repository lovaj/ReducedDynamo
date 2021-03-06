import java.util.concurrent.*;
import java.util.*;

public class StorageNode_Client_Main {

		public static void main(String[] args) {
			String bootstrapServerIp = null;
			Scanner sc = new Scanner(System.in);
			ScannerPorte scanner = new ScannerPorte();
			StorageNode taskNode;
			System.out.println("args.length=" + args.length);

			if (args.length == 1) //versione di rete
				{bootstrapServerIp = args[0];
					System.out.println("bootstrapServerIp=" + bootstrapServerIp);
					taskNode = new StorageNode(scanner, bootstrapServerIp);
				} else		//versione locale
				taskNode = new StorageNode(scanner);

			ExecutorService exec = Executors.newCachedThreadPool();

			System.out.println("Inserire il numero di Storage Node che si vogliono creare:");

			int sn = sc.nextInt();

			System.out.println("Inserire il numero di Client che si vogliono creare:");

			int cl = sc.nextInt();

			for (int i = 0;i < sn;i++) {
					exec.execute(taskNode);
				}

			try {
					Thread.sleep(1000);

				} catch (Exception e) {
					e.printStackTrace();
				}

			for (int i = 0;i < cl;i++) {
					Client taskClient;

					if (args.length == 1) //versione di rete
						{taskClient = new Client(scanner, bootstrapServerIp);
						} else
						taskClient = new Client(scanner);

					exec.execute(taskClient);
				}

			exec.shutdown();

		}

	}
