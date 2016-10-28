import java.io.*;
import java.net.*;
import java.util.*;

class server_udp {
	public static void main(String args[]) throws Exception {

		int dropRate = 0;
		int ws = 5;
		String message = "yey";


		ArrayList<Integer> clients = new ArrayList<Integer>();
		int serverPort = 8888;
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		DatagramSocket serverSocket = new DatagramSocket(serverPort);
		
		
		
		
		
		
		
		System.out.println("Wating for connection...");

		
		
		
		
		
		Arrays.fill(receiveData, (byte)0);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivePacket);
		String sentence = new String(receivePacket.getData());
		System.out.println("Connecting...");
		Thread.sleep(2000);
		System.out.println("client: " + sentence.trim());

		
			//System.out.println("this is a meaassage");
		
		
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort();
		TCP tcp = new TCP(0,0,1,1,0,0);
		
		
		
		
		sentence = tcp.stringify();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);

					
		//System.out.println("try this!");			
					
					Arrays.fill(receiveData, (byte)0);
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
					serverSocket.receive(receivePacket);
					sentence = new String(receivePacket.getData());
					Thread.sleep(2000);
					System.out.println("client: " + sentence.trim());


		System.out.println("message: " + message);
		message += ".";

		
		
		
		
		byte[] messageBytes = message.getBytes();

	
		

		byte[] header = new byte[512];
		byte[] data = new byte[512];
		int sq = 0;
		
		for(int i=0; i<messageBytes.length; ){
			

			Thread.sleep(2000);
			TCP tcpH = new TCP(sq++,0,0,0,0,0);
			Arrays.fill(header, (byte)0);
			Arrays.fill(data, (byte)0);
			byte[] headerTemp = tcpH.stringify().getBytes();


			byte[] dataTemp = Arrays.copyOfRange(messageBytes, i, i+ws);

			//System.out.println("yey it works");

					for(int j=0; j<headerTemp.length;j++){
						header[j] = headerTemp[j];
					}
					
					
					for(int j=0; j<dataTemp.length; j++){
						data[j] = dataTemp[j];
					}


			
			Random r = new Random();
			
			
							if(r.nextInt(100) >= dropRate){
								sendData = concat(header, data);
								sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
								serverSocket.send(sendPacket);	
							}else{
								System.out.println("droping");
							}


			

			serverSocket.setSoTimeout(4000);   

	       
		   
					while(true){        
						try {
							Arrays.fill(receiveData, (byte)0);
							serverSocket.receive(receivePacket);
							tcpH = decode((new String(receivePacket.getData())).trim());
							Thread.sleep(2000);
							System.out.println("client: " + tcpH.stringify());
							i+=ws;
							break;

						}
						catch (SocketTimeoutException e) {
							System.out.println("resend data");
							sq--;
							break;
						}
					}
			
	       
		}

		
		Arrays.fill(receiveData, (byte)0);
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			TCP tcpH = decode((new String(receivePacket.getData())).trim());
			Thread.sleep(2000);
		
		
		System.out.println("client: ");
		System.out.println(tcpH.stringify());


		
		
		tcp = new TCP(0,0,1,0,0,0);
		sentence = tcp.stringify();
			sendData = sentence.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);

		//System.out.println("try again");
		
		
		Thread.sleep(2000);

		
		
		
		
		tcp = new TCP(0,0,0,0,1,0);
		sentence = tcp.stringify();
		sendData = sentence.getBytes();
		
		
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);


		//System.out.println("try again1");
		
		
		
		Arrays.fill(receiveData, (byte)0);
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivePacket);
		tcpH = decode((new String(receivePacket.getData())).trim());
		Thread.sleep(2000);
		System.out.println("client: ");
		System.out.println(tcpH.stringify());


		
		
		
		serverSocket.close();
		System.out.println("Connection closed");
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static byte[] concat(byte[] a, byte[] b) {
	   int aLen = a.length;
	   int bLen = b.length;
	   byte[] c= new byte[aLen+bLen];
	   System.arraycopy(a, 0, c, 0, aLen);
	   System.arraycopy(b, 0, c, aLen, bLen);
	   return c;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static TCP decode(String s){
		String parts[] = s.split(",");
		return new TCP(Integer.parseInt(parts[0].split(":")[1].trim()), Integer.parseInt(parts[1].split(":")[1].trim()), Integer.parseInt(parts[2].split(":")[1].trim()), Integer.parseInt(parts[3].split(":")[1].trim()), Integer.parseInt(parts[4].split(":")[1].trim()), Integer.parseInt(parts[5].split(":")[1].trim()));
	}
	
}