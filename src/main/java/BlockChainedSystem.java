package src.main.java;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class BlockChainedSystem {

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.getLast().mineBlock(difficulty);

        blockChain.add(new Block("Yo im the second block", blockChain.getLast().hash));
        System.out.println("Trying to Mine block 2... ");
        blockChain.getLast().mineBlock(difficulty);

        blockChain.add(new Block("Hey im the third block", blockChain.getLast().hash));
        System.out.println("Trying to Mine block 3... ");
        blockChain.getLast().mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe Block chain: ");
        System.out.println(blockChainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
