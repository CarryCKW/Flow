package test;

import com.flow.exdException.InvalidGraphException;
import com.flow.flowdefinition.FlowDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GraphValidTest {

    private static int N = 6;

    public static void main(String[] args) throws IOException {
//        String filename = "vocationflow.txt";
        String s = "[student]->[admin1]:[admin1]->[admin2]:[admin2]->[admin3]:[admin1]->[admin3]";
//        FlowDefinition.cout2File(filename, FlowDefinition.CHOICE.Vocation, s);

//        ArrayList<FlowDefinition.Node> prenodes = FlowDefinition.getPreNodes("admin3");
//        prenodes.forEach(node -> {
//            System.out.println("node.name:" + node.name + "node.index:" + node.index);
//        });
//        System.out.println(prenodes.size());
//        System.out.println(prenodes.get(0).name);
//
//        FlowDefinition.Node node = FlowDefinition.getCurrentNode("admin1");
//        System.out.println("getcurrent node:" +node.toString());

//        System.out.println(FlowDefinition.getFlowDefinition(null, FlowDefinition.CHOICE.Vocation));
    String s1 = "[student]->[admin1]:[admin1]->[admin2]:[admin2]->[admin3]";
    FlowDefinition.cout2File(null, FlowDefinition.CHOICE.Vocation, s1);

    }

    public static String[][] checkGraphValid(String s) {
        try {
            String[][] graph = new String[N][2];
            int[][] g = new int[N][N];
            System.out.println(Arrays.deepToString(g));
            boolean isvalid = false;
            ArrayList<Node> nodes = new ArrayList<>();
            Set<String> set = new HashSet<>();
            String[] edges = s.split(":");
            int idx = 0;
            for (int j=0;j<edges.length;++j) {
                String edge = edges[j];
                String[] ns = edge.split("->");
                for (int i=0;i<2;++i){
                    ns[i] = ns[i].replaceAll("]","");
                    ns[i] = ns[i].replaceAll("\\[", "");
                    if (!set.contains(ns[i])){
                        set.add(ns[i]);
                        nodes.add(new Node(ns[i], idx));
                        idx += 1;
                    }
                    System.out.print(ns[i] + " ");
                    graph[j][i] = ns[i];
                }
                System.out.println("");
                int idx1 = 0;
                for (int i=0;i<nodes.size();++i){
                    if (nodes.get(i).name.equals(ns[0])){
                        idx1 = nodes.get(i).index;
                        break;
                    }
                }
                int idx2 = 0;
                for (int i=0;i<nodes.size();++i){
                    if (nodes.get(i).name.equals(ns[1])){
                        idx2 = nodes.get(i).index;
                        break;
                    }
                }
                g[idx1][idx2] = 1;
            }
            System.out.println(nodes);
            System.out.println(Arrays.deepToString(g));
            System.out.println(Arrays.deepToString(graph));
            isvalid = isValid(g);
            if (isvalid) {
                return graph;
            } else {
                throw new InvalidGraphException("wrong");
            }
        }catch (InvalidGraphException e){
            e.printStackTrace();
            throw new InvalidGraphException("wrong vocation flow graph definition");
        }
    }

    /**
     * return true if g contains no circle, otherwise false
     * @param g 2D graph
     * @return true or false
     */
    public static boolean isValid(int[][] g){
        return true;
    }


    static class Node {
        public String name;
        public int index;

        public Node(String name, int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", index=" + index +
                    '}';
        }
    }
}
