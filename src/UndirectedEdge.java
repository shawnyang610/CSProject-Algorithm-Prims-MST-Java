import java.io.PrintWriter;

public class UndirectedEdge {
    int ni, nj, edgeCost;
    UndirectedEdge next;
    public UndirectedEdge (int inNi, int inNj, int inEdgeCost){
        ni = inNi;
        nj = inNj;
        edgeCost = inEdgeCost;
        next = null;
    }
    public void printEdge (PrintWriter inOutfile){
        inOutfile.println(ni+" "+nj+" "+edgeCost);
    }
}
