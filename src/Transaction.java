
/**
 * Transaction.java create a Transaction class with simple method to keep tracks all variable.
 * 1.0 version create simple method and include transactionCost.
 * @author lyont
 * @version 1.0
 */

public class Transaction implements  Comparable<Transaction>{
	/**
	 * blockNumber is a assigned Block number.
	 * index is a assigned within a list of blocks.
	 * gasLimit is a capacity of the Transaction.
	 * gasPrice is a limit value hold.
	 * fromAdr is a address of a main sender.
	 * toAdr is address to a main receiver.
	 */
	private int blockNumber;
	private int index;
	private int gasLimit;
	private long gasPrice;
	private String fromAdr;
	private String toAdr;
	/**
	 * Transaction constructor with 6 parameter.
	 * 
	 * @param number	have number of this blocks to the Transaction.
	 * @param index		have index of this Transaction from a list of Transaction.
	 * @param gasLimit	have capacity for this Transaction.
	 * @param gasPrice	have price for this Transaction.
	 * @param fromAdr	have sender address.
	 * @param toAdr		have receiver address.
	 */
	public Transaction(int number, int index, int gasLimit, long gasPrice, String fromAdr, String toAdr){
		blockNumber = number;
		this.index=index;
		this.gasLimit=gasLimit;
		this.gasPrice=gasPrice;
		this.fromAdr=fromAdr;
		this.toAdr=toAdr;
	}
	/**
	 * Get to block number.
	 * 
	 * @return the number of that particular Transaction.
	 */
	public int getBlockNumber() {
		return blockNumber;
	}
	/**
	 * Get to index number.
	 * @return the index of that particular Transaction.
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * Get to  max capacity.
	 * @return the capacity of that particular Transaction.
	 */
	public int getGasLimit() {
		return gasLimit;
	}
	/**
	 *  Get to  Transaction price.
	 * @return the price of that particular Transaction.
	 */
	public long getGasPrice() {
		return gasPrice;
	}
	/**
	 * Get to main sender.
	 * @return the sender address of that particular Transaction.
	 */
	public String getFromAddress() {
		return fromAdr;
	}
	/**
	 * Get to main receiver.
	 * @return the receiver address of that particular Transaction.
	 */
	public String getToAddress() {
		return toAdr;
	}
	/**
	 * Calculate transaction fee.
	 * @return the fee of that particular Transaction.
	 */
	public double transactionCost() {

		return gasPrice*gasLimit/1e18;
	}
	/**
	 *  Format string to show index and block number.
	 *  @return format String.
	 */
	public String toString(){
		return "Transaction " + index + " for Block " + blockNumber;
	}
	/**
	 * Override compareTo method to check index number.
	 * 
	 * @return index	High index of two Transaction.
	 */
	@Override
	public int compareTo(Transaction o) {
		// TODO Auto-generated method stub
		if(this.index > o.index) {
		return this.index;
	}
		else if (this.index < o.index) {
			return o.index;
		}
		else {
			return 0;
		}
	}

	
	
	
}
