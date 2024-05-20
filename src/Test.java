import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Blocks.readFile("ethereumP1data.csv");
		Blocks.sortBlocksByNumber();
		ArrayList<Blocks> blocks = Blocks.getBlocks();
		// the number is index is 6. greaded is 11
		blocks.get(6).uniqFromTo();
	}

}
