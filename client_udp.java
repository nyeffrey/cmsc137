import java.io.*;
import java.net.*;
import java.util.*;

class client_udp {
	
	
	
	
	public static void main(String args[]) throws Exception {

		int serverPort = 8888;

		
		
		
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];


		
		
		
		TCP tcp = new TCP(0,0,0,1,0,0);
		String sentence = tcp.stringify();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
		clientSocket.send(sendPacket);
		

		
		
		
		Arrays.fill(receiveData, (byte)0);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		
			clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		Thread.sleep(2000);
		System.out.println("server: " + modifiedSentence.trim());

		
		//System.out.println("test");
		
		tcp = new TCP(0,0,1,0,0,0);
		sentence = tcp.stringify();
		sendData = sentence.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
		clientSocket.send(sendPacket);

		//System.out.println("feck");
		
		
		byte[] header = new byte[512];
		byte[] data = new byte[512];
		
		
		
		
		
		
		
		
		
		
		
		
		
		while(true){

			Arrays.fill(receiveData, (byte)0);
					receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			byte[] temp = receivePacket.getData();

			/*
				System.out.println("test stuff");
			*/
			
			Arrays.fill(header, (byte)0);
			Arrays.fill(data, (byte)0);
				header = Arrays.copyOfRange(temp, 0, 512);
			data = Arrays.copyOfRange(temp, 512, 1024);


			TCP tcpH = decode((new String(header)).trim());


			Thread.sleep(2000);
			System.out.println("");
			
				System.out.println("server");
			System.out.println(tcpH.stringify());
			
			
			
			System.out.print((new String(data)).trim());
			System.out.println("");


			tcp = new TCP(0,tcpH.seqNum,0,0,0,0);
			sentence = tcp.stringify();
			sendData = sentence.getBytes();
			
			
			
			
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
			clientSocket.send(sendPacket);


			
			if((new String(data)).indexOf(".") != -1){
				break;
			}

		}

		
		Thread.sleep(2000);
		
		
		System.out.println("Start closing connection");
		Thread.sleep(2000);


		
		tcp = new TCP(0,0,0,0,1,0);
		sentence = tcp.stringify();
		
		
		sendData = sentence.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
				clientSocket.send(sendPacket);

		//System.out.println("feck1");
		
		Arrays.fill(receiveData, (byte)0);
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		
		
		clientSocket.receive(receivePacket);
		TCP tcpH = decode((new String(receivePacket.getData())).trim());
		Thread.sleep(2000);
		//System.out.println("test1");
		
		
		
		
		
		
		
		System.out.println("server: ");
		System.out.println(tcpH.stringify());

		
		//System.out.println("test2");
		
		Arrays.fill(receiveData, (byte)0);
		
		
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		
		
		
		
		tcpH = decode((new String(receivePacket.getData())).trim());
		Thread.sleep(2000);
		System.out.println("server: ");
		System.out.println(tcpH.stringify());

		
		
		
		tcp = new TCP(0,0,1,0,0,0);
		sentence = tcp.stringify();
		
		
		
		sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
		clientSocket.send(sendPacket);

		
		
		Thread.sleep(10000);

		
		
		clientSocket.close();
		System.out.println("Connection closed");


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static TCP decode(String s){
		
		
		
				String parts[] = s.split(",");
		
		
		
		
		return new TCP(Integer.parseInt(parts[0].split(":")[1].trim()), Integer.parseInt(parts[1].split(":")[1].trim()), Integer.parseInt(parts[2].split(":")[1].trim()), Integer.parseInt(parts[3].split(":")[1].trim()), Integer.parseInt(parts[4].split(":")[1].trim()), Integer.parseInt(parts[5].split(":")[1].trim()));
	}


}