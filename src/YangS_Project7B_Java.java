//CSC700-34 PROJECT7B PRIMS MST
//SHAWN YANG
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class YangS_Project7B_Java {

    public static void main (String args[]){
        Scanner infile= null;
        PrintWriter outfile1 = null;
        PrintWriter outfile2 = null;
        int numNodes=-1;

        if (args.length<3){
            throw new IllegalArgumentException("Please suppy 1 input file and 2 output files");
        }

        try {
            infile = new Scanner(new FileReader(args[0]));
            outfile1 = new PrintWriter(args[1]);
            outfile2 = new PrintWriter(args[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (infile.hasNextInt())
            numNodes = infile.nextInt();
        else{
            System.out.println("input file does not have valid node numbers, or the correct format");
            System.exit(0);
        }
        System.out.println("Open files: \r\n"+args[0]+" OK \r\n"+ args[1]+" OK \r\n"+ args[2]+" OK \r\n"+"numNodes: "+numNodes);

        PrimMST primMST = new PrimMST(numNodes, infile,outfile1,outfile2);
        primMST.start();

        infile.close();
        outfile1.close();
        outfile2.close();

    }

}
