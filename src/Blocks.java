import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.stream.Collectors;
/**
 * Blocks.java version 0.1 will create Ethereum Blocks with simple methods.
 * 1.0 version is create a blockchain and to have a foundation of fully function block chains.
 * 2.0 version include timestamp and transactions. The file have method find different timestamp and transactions between two blocks.
 * 3.0 version include Transactions variable to keep track a list of transactions for each blocks.
 * @author lyont
 * @version 3.0
 */
class Blocks implements Comparable<Blocks>{
	/**
	 * number is Block number.
	 * miner is Block address.
	 * timestamp is recorded time that the Block occurred.
	 * transactionsCount is Block contemplating transfer.
	 * blocks will store Blocks variables.
	 */
	private int number;
	private String miner;
	private long timestamp;
	private int transactionsCount;
	private static ArrayList<Blocks> blocks;
	ArrayList<Transaction> transactions;
	/**
	 * Blocks constructor with no parameter.
	 * <p>
	 * Initialized empty field.
	 */
	public Blocks(){
		
	}
	/**
	 * Blocks constructor with one parameter.
	 * @param number	have number of this block.
	 */
	public Blocks(int number){
		this.number=number;
	}
	/**
	 * Blocks constructor with two parameter.
	 * @param number	have number of this block.
	 * @param miner 	have address of this block.
	 */
	public Blocks(int number,String miner){
		this.number=number;
		this.miner = miner;
	}
	/**
	 * Blocks constructor with four parameter.
	 * @param number			have number of this block.
	 * @param miner				have address of this block.
	 * @param timestamp			have recored time of this block.
	 * @param transactionsCount	have transaction of this block.
	 * @throws IOException 		when some the parameter did not work.
	 */
	public Blocks(int number,String miner,long timestamp,int transactionsCount) throws IOException{
		this.number=number;
		this.miner = miner;
		this.timestamp=timestamp;
		this.transactionsCount = transactionsCount;
		transactions= new ArrayList<>(transactionsCount);
		readTransactions("ethereumtransactions1.csv");
	}
	/**
	 * Get to variable number of a block.
	 * @return the number of that particular block.
	 */
	public int getNumber() {
		return number; 
	}
	/**
	 * Get to variable address of a block.
	 * @return the address of that particular block.
	 */
	public String getMiner() {
		return miner;
	}
	/**
	 * Get to variable transactions of the block.
	 * @return the transactionsCount of that particular block.
	 */
	public int getTransactionCount() {
		return transactionsCount; 
	}
	public ArrayList<Transaction> getTransactions(){
		ArrayList<Transaction> counter = new ArrayList<>(transactions);
		return counter;
	}
	
	/**
	 * Get to variable timestamp of the block and format based on Unix Time by Central Time Zone.
	 * @return the format time.
	 */
	public String getDate() {
		SimpleDateFormat format= new SimpleDateFormat("EEE, dd MMMM YYYY HH:mm:ss z");
		format.setTimeZone(TimeZone.getTimeZone("CST"));
		Date time = new Date(timestamp*1000);
		
		return format.format(time); 
	}
	/**
	 * Get to a copy variable of an ArrayList of blocks.
	 * @return a copy of blocks ArrayList.
	 */
	public static ArrayList<Blocks> getBlocks() {
		//ArrayList seem not work with copyOf. But loop can work
		ArrayList<Blocks> copy = new ArrayList<Blocks>();
		for (int i=0;i<blocks.size();i++) {
			copy.add(blocks.get(i));
	       }
		
		return copy; 
	}
	
	/** 
	 * Count frequencies of each block address.
	 * Method work depend on initialized blocks ArrayList.
	 */
	public static void calUniqMiners() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (int i=0;i<blocks.size();i++) {
			list.add(blocks.get(i).getMiner());
	       }
		// Get all unique miners into ArrayList of String
		ArrayList<String> tem = (ArrayList<String>) list.stream().distinct().collect( Collectors.toList()) ;
        System.out.println("Number of unique Miners: "  + tem.size() );
		System.out.println("Each unique Miner and its frequency:");
		// Counting by comparing tem to blocks
		for(int i=0;i < tem.size();i++) {
			int count = 0;
		//If found the same match to tem in block.count++	
		for (int i1=0;i1<blocks.size();i1++) {
			if(tem.get(i).equals(blocks.get(i1).getMiner()))
			count++;
	       }
		//Print each the Address and number of times appears
		System.out.println("Miner Address: "  + tem.get(i));
		System.out.println("Miner Frequency: "+ count);
		System.out.println();
		}
	}
	/**
	 * Return the number of different from Blocks A to Blocks B.
	 * 
	 * @param A			First block to compare.
	 * @param B			Second block to compare.
	 * @return diff		different of how far Blocks A to Blocks B respectively in number.
	 */
	public static int blockDiff(Blocks A, Blocks B) {
		int diff = A.getNumber() - B.getNumber();
		
		return diff;
	}
	/**
	 * Get blocks objects based on the taken number.
	 * @param num		Block number.
	 * @return null		if entered number did not correspond to block number or will get block number.
	 */
	public static Blocks getBlockByNumber(int num) {
		if(blocks == null) {
			return null;
		}
		for(int i= 0; i < blocks.size();i++) {
			if(blocks.get(i).getNumber()==num)
				return blocks.get(i);
		}
		return null;
	}
	/**
	 * format blocks constructor in to a sentence.
	 * @return Block number and Miner Address for a constructor with number and address. <p> Block number for a constructor with number. 
	 * 			Empty Block for an empty constructor.
	 */
	public String toString() {
		if (number != 0 && miner != null) {
		return "Block Number: " +number + " Miner Address: "+ miner;
		}
		else if (number != 0) {
			return "Block Number: " +number;
		}
		return "Empty Block";
	}
/**
 * Create a file then read though  comma split String. Then the info will put in blocks by number and miner address.
 * @param filename	read an initialized String file .
 * @throws IOException	if file input has error.
 * 
 */
	public static void readFile(String filename) throws IOException {
		FileInputStream  myReader = new FileInputStream (filename);
		
		try (Scanner scn = new Scanner(myReader)) {
			
			blocks = new ArrayList<Blocks>();
			//scanning file
			while (scn.hasNextLine()) {
			// split String by ","	
			  String[] data = scn.nextLine().split(",");
			// adding to blocks take parameter 
			  blocks.add(new Blocks(Integer.parseInt(data[0]),data[9],
					  Long.parseLong(data[16]),Integer.parseInt(data[17])));
			}
		}
	    myReader.close();
	}
	/**
	 * Override comaperTo method by using number to compare. the method is needed for sortBlocksByNumber() and readTransactions().
	 * <p>
	 * return the different of each Blocks then Blocks transactions
	 */
	@Override
	public int compareTo(Blocks comaprebl) {
		//compare Blocks
		int result = this.number - comaprebl.number;
		if(result!=0)
			return result;
		//compare Transactions
		 result = this.miner.compareTo(comaprebl.miner);
		if(result!=0)
			return result;
		double result2 = this.timestamp - comaprebl.timestamp;
		if(result2!=0)
			return result;
		return this.transactionsCount-comaprebl.transactionsCount;
	}
	/**
	 * method sort based on Block numbers.
	 */
	public static void sortBlocksByNumber() {
		Collections.sort(blocks);
	}
	/**
	 * Method will print out time different between two blocks.
	 * @param first		first Blocks to compare.
	 * @param second	second Blocks to compare.
	 */
	public static void timeDiff(Blocks first,Blocks second) {
		if(first == null || second == null) {
		System.out.println("A given Block is null.");
		}
		else {
			//format String output
			String output = "The difference in time between Block "+ first.number +" and Block "+ second.number +" is ";
			//calculate date of 2 variable
			Date time1 = new Date(first.timestamp*1000);
			Date time2 = new Date(second.timestamp*1000);
			long miliDiff = time2.getTime() - time1.getTime(); 
			//convert to second, minute,and hour
			long secDiff = Math.abs(miliDiff / 1000 % 60);
			long minDiff = Math.abs(miliDiff / (60 * 1000) % 60);
			long hrDiff = Math.abs(miliDiff / (60 * 60 * 1000) % 24);
			// format based the given time
			if(hrDiff==1)
				output+= hrDiff + " hour";
			else if(hrDiff>12)
				output+= 24-hrDiff + " hours";
			else
				output+= hrDiff + " hours";
			
			output+=", ";
			if(minDiff==1)
				output+= minDiff + " minute";
			else
				output+= minDiff + " minutes";
			
			output+=", and ";
			if(secDiff==1)
				output+= secDiff + " second.";
			else
				output+= secDiff + " seconds.";
				//output
				System.out.println(output);
		}
	}
	/**
	 * Method will print out time different between two blocks.
	 * @param first		first Blocks to compare.
	 * @param second	second Blocks to compare.
	 * @return the different in transaction between two block.
	 */
	public static int transactionDiff(Blocks first, Blocks second) {
		//sort first to make sure different orders do not mess up
		sortBlocksByNumber();
		int diff =0;
		//check for null
		if(first == null||second == null) {
			return -1;
		}
		//check for first block number always higher or equal to second block number
		if(first.getNumber()>=second.getNumber()) {
			return -1;
		}
		//loop to add transactions different
		for(int i = blocks.indexOf(first)+1;i< blocks.indexOf(second);i++) {
			diff += blocks.get(i).getTransactionCount();
		}
	return diff;	
	}
	
	/**
	 *  Create a file then read though  comma split String. Then the info will put in blocks by number and miner address.
	 * @param filename find the file to read Transaction.
	 * @throws IOException when scanner does not works
	 */
	
	public void readTransactions(String filename) throws IOException {
		FileInputStream  myReader = new FileInputStream (filename);
		TreeSet<Transaction> temSet = new TreeSet<Transaction>();
		Scanner scn = new Scanner(myReader);
			//scanning file
			while (scn.hasNextLine()) {
			// split String by ","	
			  String[] data = scn.nextLine().split(",");
			// adding to Transaction take parameter
			  int num = Integer.parseInt(data[3]);
			  int index = Integer.parseInt(data[4]);
			  int gasLimit = Integer.parseInt(data[8]);
			  long gasPrice = Double.valueOf(data[9]).longValue();
			  String fromAdr = data[5];
			  String toAdr = data[6];
			  //if the file have exact number match with blocks number.
			  if (num == this.number) {
			  Transaction tem = new Transaction(num,index,gasLimit,gasPrice,fromAdr,toAdr);
			  temSet.add(tem);
			  }
		  }
		scn.close();
		//add back to transactions.
	    TreeSet<Transaction> result = new TreeSet<Transaction>(temSet);
	    transactions.addAll(result);
	}
	/**
	 * Override hashCode. this method needed have not checked whether needed for readTransactions
	 * 
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(miner, number, timestamp, transactions, transactionsCount);
	}
	/**
 	 * Override equals. this method needed have not checked whether needed for readTransaction.
	 * 	 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blocks other = (Blocks) obj;
		return Objects.equals(miner, other.miner) && number == other.number && timestamp == other.timestamp
				&& Objects.equals(transactions, other.transactions) && transactionsCount == other.transactionsCount;
	}
	/**
	 * Calculate the average cost of all Transaction fees.
	 * @return the Average cost of Transaction fees.
	 */
	public double avgTransactionCost() {
		double result=0;
		
		for (int i =0; i < transactionsCount; i++) {
			result += transactions.get(i).transactionCost();
		}
			
		return result/transactionsCount;
	}
	/**
	 * Output texts which indicate every unique From address and who where the transactions send to. The method also have calculate total cost of each unique From address.
	 * <p> Print out From Address,To Address, and total transaction Cost.
	 */
	public void uniqFromTo() {
		DecimalFormat fromatNum = new DecimalFormat("0.00000000");
		List<String> list = new ArrayList<String>();
		//get to all List
		for (int i=0;i<transactions.size();i++) {
			list.add(transactions.get(i).getFromAddress());
	       }
		//remove duplicate From Address
		Set<String> set = new LinkedHashSet<>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		//began print
		System.out.println("Each transaction by from address for Block " + getNumber() +":");
		//loop through every unique From address
		for(int i=0; i < list.size();i++) {
			double counter = 0;
			System.out.println();
			System.out.println("From "+ list.get(i));
			//loop to all transactions
			for(int j=0; j < transactions.size();j++) {
				//if found, print out the To address and add to total cost
				if(list.get(i).equals(transactions.get(j).getFromAddress())) {
				System.out.println(" -> "+ transactions.get(j).getToAddress());
				counter += transactions.get(j).transactionCost();
				}
			}
			//final print
			System.out.println("Total cost of transactions: "+ fromatNum.format(counter)+" ETH");
		}
		
		
	}
	
}
