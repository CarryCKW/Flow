package com.flow.flowdefinition;


import com.flow.exdException.DataOpException;
import com.flow.exdException.InvalidGraphException;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 蔡小蔚
 */
@SuppressWarnings("ALL")
public class FlowDefinition {
    private static FileInputStream inputStream = null;
    private static FileWriter fileWriter = null;
    private static FileReader fileReader = null;
    private static int N = 12;



//    static {
//        try {
//            inputStream = new FileInputStream("VocationFlowGraphDefinition");
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//    }

    public enum CHOICE {
        ///
        Vocation, Reception
    }

    /**
     * look this https://www.cnblogs.com/blogtech/p/11151780.html
     * @param sourcefilename can be null
     * @param choice CHOICE Type
     * @return the original source graph
     * @throws IOException FileNotFoundException
     */
    public static String getFlowDefinition( CHOICE choice) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        if (choice.equals(CHOICE.Vocation)) {
            try{
                String ideaPath = "/src/main/resources/";
                String basePath = "WEB-INF/classes/";
                File file = new File(Thread.currentThread().getContextClassLoader().getResource("VocationFlowGraphDefinition.txt").getFile());
                fileReader = new FileReader(file);
//                String path = FlowDefinition.class.getResource(sourcefilename).getPath();
//            System.out.println("path: " + path);
//            fileReader = new FileReader("VocationFlowGraphDefinition.txt");
               // fileReader = new FileReader(ideaPath + sourcefilename);
                br = new BufferedReader(fileReader);
                String s = null;
                while ((s = br.readLine())!=null) {
                    result.append(s);
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            } finally {
                System.out.println("flowdefinition:" + result.toString());
                assert br != null;
                br.close();
            }

            return result.toString();
        }else {
            throw new DataOpException("not implement");
        }
    }
    /**
     * this func to check input changed's valid, if valid then output to the target file and change the source
     * flowDefinition file, otherwise throw exception
     * @param targetfilename targetfile
     * @param choice the enum value
     * @param changed the input value
     * @throws IOException exception
     * @throws InvalidGraphException exception
     */
    public static void cout2File( CHOICE choice, String changed) throws IOException, InvalidGraphException {
        if (choice.equals(CHOICE.Vocation)) {
            try {
                File file1 = new File(Thread.currentThread().getContextClassLoader().getResource("vocationflow.txt").getFile());
                File file2 = new File(Thread.currentThread().getContextClassLoader().getResource("VocationFlowGraphDefinition.txt").getFile());
                fileWriter = new FileWriter(file1);
                String[][] edges = new String[N][2];
                edges = checkGraphValid(changed);
                FileWriter fileWriter2 = new FileWriter(file2);
                fileWriter2.write(changed);
                fileWriter2.flush();
                fileWriter2.close();

                for (int i=0;i<N;++i){
                    if (edges[0] != null){
<<<<<<< HEAD
                        for (int j=0;j<2;++j) {
                            if (edges[i][j] != null) {
=======
                        for (int j=0;j<2;++j){
                            if (edges[i][j]!=null) {
>>>>>>> 856fee7df1fd1023d8daaa8ba64cabda829db61a
                                fileWriter.write(edges[i][j].toString());
                                fileWriter.write("\t");
                            }
                        }
                        fileWriter.write("\n");
                        fileWriter.flush();
                    } else {
                        fileWriter.close();
                        break;
                    }
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new FileNotFoundException();
            }catch (InvalidGraphException e){
                throw new InvalidGraphException("wrong vocation flow graph definition");
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException();
            }
        } else if (choice.equals(CHOICE.Reception)) {
            throw new DataOpException("not implement");
        } else {
            throw new DataOpException("not implement");
        }
    }

    /**
     * get the pre admin or stu role and its index by the current admin's name
     * @param currentName  the input graph
     * @return list of pre {@Link FlowDefinition.Node} Node
     */
    public static ArrayList<Node> getPreNodes(String currentName) throws InvalidGraphException {
        /**
         * we should change the way of getting the string changed from file named "vocationflow.txt"
         */
        String changed = "[student]->[admin1]:[admin1]->[admin2]:[admin2]->[admin3]:[admin1]->[admin3]";
        ArrayList<Node> nodes = getNodes(changed);
        final String[][] graph = checkGraphValid(changed);
//        Arrays.asList(graph).forEach(edge -> {
//            Arrays.asList(edge).forEach(System.out::println);
//        });
        int length = graph.length;
        final ArrayList<Node> res = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < 2;++j) {
                if (j==1 && graph[i][j]!=null && currentName.equals(graph[i][j])) {
                    final int finalI = i;
                    nodes.forEach(node -> {
                        if (node.name.equals(graph[finalI][0])) {
                            res.add(node);
                        }
                    });
                }
            }
        }

        return res;
    }

    public static Node getCurrentNode(String currentName) throws DataOpException {
        assert currentName!=null;
        /**
         * we should change the way of getting the string changed from file named "VocatinFlow.....txt"
         */
        String changed = "[student]->[admin1]:[admin1]->[admin2]:[admin2]->[admin3]:[admin1]->[admin3]";
        ArrayList<Node> nodes = getNodes(changed);
        Node need = new Node("", 1);
        nodes.forEach(node -> {
            if (node.name.equals(currentName)) {
                need.name = node.name;
                need.index = node.index;
            }
        });
        nodes.forEach(node -> {
            System.out.println(node.toString());
        });
        if (!need.name.equals(currentName)) {
            throw new DataOpException("can't find current node by currentName...");
        }
        return need;
    }

    /**
     * return graph edges if input s is valid, otherwise throw
     * @param s the input from jsp's editor
     * @return edges
     * InvalidGraphException exception
     */
    private static String[][] checkGraphValid(String s) throws InvalidGraphException {
        try {
            int vexnum=0;
            String[][] graph = new String[N][2];
            int[][] g = new int[N][N];
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
                    graph[j][i] = ns[i];
                }
                int idx1 = 0;
                for (int i=0;i<nodes.size();++i){
                    if (nodes.get(i).name.equals(ns[0])){
                        System.out.println("asdf:" + nodes.get(i).name);
                        idx1 = nodes.get(i).index;
                        break;
                    }
                }
                int idx2 = 0;
                for (int i=0;i<nodes.size();++i){
                    if (nodes.get(i).name.equals(ns[1])){
                        System.out.println("asdf:" + nodes.get(i).name);
                        idx2 = nodes.get(i).index;
                        break;
                    }
                }
                g[idx1][idx2] = 1;
            }
            isvalid = isValid(g);
            if (isvalid) {
                return graph;
            } else {
                throw new InvalidGraphException("wrong");
            }
        }
        catch (InvalidGraphException e){
            e.printStackTrace();
            throw new InvalidGraphException("wrong vocation flow graph definition");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException throws there...");
            throw new NullPointerException("here");
        }
    }

    private static ArrayList<Node> getNodes(String s) {
        try {
            String[][] graph = new String[N][2];
            int[][] g = new int[N][N];
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
                    graph[j][i] = ns[i];
                }
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
            return nodes;
        } catch (InvalidGraphException e){
            e.printStackTrace();
            throw new InvalidGraphException("wrong vocation flow graph definition");
        }catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("NullPointerException throws there...");
            throw new NullPointerException("here");
        }

    }
    public  int GetNodesNum() throws IOException {
        ArrayList<Node> list = getNodes(getFlowDefinition(CHOICE.Vocation ) ) ;
        int num=list.size() -1;
        return num;
    }
    /*public int GetCurrentStatus(String admin)
    {
        ArrayList<Node> nodeList = getPreNodes(admin) ;
        int result=0;
        for(int i=0;i<nodeList .size() -1;i++)
        {
            if (nodeList .get(i).index!=0){
            result += Math.pow(2,nodeList .get(i).index -1);
            }
        }
        return result ;
    }*/

    /**
     * return true if g contains no circle, otherwise false
     * @param g 2D graph
     * @return true or false
     */
    private static boolean v(int [][]g,boolean visited[],int a)
    {
        boolean x=true;
        for(int i=0;i<N;i++)
        {
            if(g[a][i]==1)
            {
                if(visited[i]==true)
                    return false;
                else
                {
                    visited[a]=true;
                    visited[i]=true;
                    x= v(g,visited,i);
                    visited[a]=false;
                    visited[i]=false;
                }
            }
        }
        return x;
    }
    private static boolean isValid(int[][] g){
        boolean valid=true;
        boolean[] visited=new boolean[N];
        for(int i=0;i<N;i++)
            visited [i]=false;
        for(int i=0;i<N;i++)
        {
            if(!v(g,visited,i))
            {
                valid =false;
                break;
            }
        }
        return valid ;
    }

    public static class Node {
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
