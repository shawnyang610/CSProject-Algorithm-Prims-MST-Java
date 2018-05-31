import java.io.PrintWriter;
import java.util.Scanner;

public class PrimMST {
    int numNodes;
    int[] inWhichSet;
    int totalMSGCost;
    UndirectedEdge edgeListHead;
    UndirectedEdge MSTofG;
    UndirectedEdge nextEdge;
    Scanner infile;
    PrintWriter outfile1;
    PrintWriter outfile2;

    public PrimMST(int inNumNodes,Scanner inInfile, PrintWriter inOutfile1, PrintWriter inOutfile2){
        numNodes = inNumNodes;
        infile = inInfile;
        outfile1 = inOutfile1;
        outfile2 = inOutfile2;
        inWhichSet = new int[numNodes+1];
        for (int i=1; i<numNodes+1; i++){
            if (i==1)
                inWhichSet[i]=1;
            else
                inWhichSet[i]=2;
        }
        totalMSGCost=0;
        edgeListHead = new UndirectedEdge(0,0,0);
        MSTofG = new UndirectedEdge(0,0,0);
        nextEdge=null;
    }

    public void start(){
        //step1,2,3
        readFromInput();
        while (!sameSet()) {
            //step4,5
            nextEdge = findAndRemoveNextEdge(edgeListHead);
            //step6
            pushEdge(nextEdge, MSTofG);
            totalMSGCost += nextEdge.edgeCost;
            if (inWhichSet[nextEdge.ni] == 1)
                move2SetA(nextEdge.nj, 1);
            else
                move2SetA(nextEdge.ni, 1);
            printSet(inWhichSet);
            //step7
            printList(MSTofG, outfile2);
        }//step8
        //step9
        outfile1.println("* * * The Prim's MST of the input graph is given below * * *");
        outfile1.println("numNodes: "+numNodes);
        printMST(outfile1);
        outfile1.println("Total MST Cost: "+totalMSGCost);
    }
    private void readFromInput(){
        int ni, nj, edgeCost;
        while (infile.hasNext()){
            ni=infile.nextInt();
            nj=infile.nextInt();
            edgeCost=infile.nextInt();
            insertEdge(new UndirectedEdge(ni,nj,edgeCost), edgeListHead);
            outfile2.println(ni+" "+nj+" "+edgeCost);
            printList(edgeListHead, outfile2);
        }
    }
    private void insertEdge (UndirectedEdge inEdge, UndirectedEdge inEdgeListHead){
        UndirectedEdge walker= inEdgeListHead;
        if (inEdgeListHead.next ==null){
            inEdgeListHead.next = inEdge;
        }
        else {
            while (walker.next!=null && inEdge.edgeCost>walker.next.edgeCost){
                walker = walker.next;
            }
            inEdge.next=walker.next;
            walker.next=inEdge;
        }
    }
    private UndirectedEdge removeEdge (UndirectedEdge inEdgeListHead){
        UndirectedEdge temp = inEdgeListHead.next;
        if (inEdgeListHead.next != null)
            inEdgeListHead.next = inEdgeListHead.next.next;
        return temp;
    }
    private void pushEdge (UndirectedEdge inEdge, UndirectedEdge inMSTofG){
        inEdge.next = inMSTofG.next;
        inMSTofG.next = inEdge;
    }
    private void move2SetA (int inNode, int inSetA){
        inWhichSet[inNode]=inSetA;
    }
    private void printSet (int[] inSet){
        for (int i=1; i<numNodes+1; i++){
            outfile2.println("Node: "+i+"Set: "+inSet[i]);
        }
    }
    private void printList (UndirectedEdge inListhead, PrintWriter inOutfile){
        UndirectedEdge walker = inListhead;
        inOutfile.print("Listhead -> ");
        int counter=0;
        while (walker.next!=null && counter < 9){
            inOutfile.print("<"+walker.ni+","+walker.nj+","+walker.edgeCost+"> ->");
            walker = walker.next;
            counter++;
        }
        inOutfile.println("<"+walker.ni+","+walker.nj+","+walker.edgeCost+"> -> null");
    }
    private boolean sameSet(){
        boolean stat = true;
        for (int i=1; i<numNodes+1; i++){
            if (inWhichSet[1]!=inWhichSet[i])
                stat=false;
        }
        return stat;
    }
    private void printMST (PrintWriter inOutfile){
        UndirectedEdge walker=MSTofG;
        while (walker.next != null){
            walker = walker.next;
            walker.printEdge(inOutfile);
        }
    }
    private UndirectedEdge findAndRemoveNextEdge(UndirectedEdge inEdgeList){
        UndirectedEdge walker = inEdgeList;
        UndirectedEdge preWalker = null;//a second walker walks before walker.
        UndirectedEdge edgeBetweenDiffSets = null;
        while (walker.next!=null){
            preWalker = walker;
            walker = walker.next;
            if (inWhichSet[walker.ni]!=inWhichSet[walker.nj]){
                edgeBetweenDiffSets = walker; //found the edge
                //delete the edge(walker) from list
                preWalker.next = walker.next;
                break;
            }

        }
        return edgeBetweenDiffSets;
    }

}
