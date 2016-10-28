class TCP {
	public int seqNum, ackNum, ackBit, synBit, finBit, winSize;
	public String data = "";

	
	
	public TCP(int seqNum, int ackNum, int ackBit, int synBit, int finBit, int winSize){
		this.seqNum = seqNum;
		this.ackNum = ackNum;
		this.ackBit = ackBit;
		this.synBit = synBit;
		this.finBit = finBit;
		this.winSize = winSize;
	}

	
	
	
	public TCP(int seqNum, int ackNum, int ackBit, int synBit, int finBit, int winSize, String data){
		this.seqNum = seqNum;
		this.ackNum = ackNum;
		this.ackBit = ackBit;
		this.synBit = synBit;
		this.finBit = finBit;
		this.winSize = winSize;
		this.data = data;
	}

	
	
	
	public String stringify(){
		if(data.equals(""))
			return "seqn: "+seqNum+", ackn: "+ackNum+", ackb: "+ackBit+", synb: "+synBit+", finb: "+finBit+", ws: "+winSize;
		return "seqn: "+seqNum+", ackn: "+ackNum+", ackb: "+ackBit+", synb: "+synBit+", ws: "+winSize+", data: "+data;
	}

	
	
	
	public TCP decode(String s){
		String parts[] = s.split(",");
		
		
		if(parts.length == 6){
			return new TCP(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
		}
		
		
		return new TCP(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), parts[6]);
	}
}