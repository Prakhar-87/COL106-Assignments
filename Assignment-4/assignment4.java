import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;

public class assignment4{

    static class graph{
        int n;
        LinkedList<Edge>[] adj;
        LinkedList<Integer> vis;
        LinkedList<Integer> avg;
        LinkedList<cooccurence_count> rank;
        HashMap<String, Integer> nodes = new HashMap<>();
        HashMap<Integer, String> nodes_reverse = new HashMap<>();
        FileWriter writer;
        int count = 0;
        graph(int x){
            try{
                writer = new FileWriter("rank_prakhar.txt");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            this.n = x;
            // this.writer = writer;
            adj = new LinkedList[n+1];
            vis = new LinkedList<Integer>();
            avg = new LinkedList<Integer>();
            rank = new LinkedList<cooccurence_count>();
            for(int i=0;i<=n;i++){
                adj[i] = new LinkedList<>();
                vis.add(0);
                avg.add(0);
            }            
        }

        void addVertex(String s,int i){
            nodes.put(s,i);
            nodes_reverse.put(i,s);
        }

        void addEdge(String s,String d,int w){
            Edge e = new Edge(nodes.get(d),w);
            adj[nodes.get(s)].addFirst(e);
        }

        //  1st function to calculate Avg value i.e. 1st part of Assignment
        void average(){
            int ans = 0;
            for(int i=1;i<=n;i++){
                int count=0;
                for(int j=0;j<adj[i].size();j++){
                    if(avg.get(adj[i].get(j).getVertexId())!=i){
                        avg.set(adj[i].get(j).getVertexId(),i);
                        count ++;
                    }
                }
                ans += count;
            }
            for(int i=0;i<avg.size();i++){
                avg.set(i,0);
            }
            float fin =(float)((float)ans/(float)n);
            System.out.printf("%.2f",fin); // To round off answer to 2 decimal places.
            System.out.println();
        }

        // Next 7 functions are for finding Rank i.e. 2nd part of Assignment
        // compare function will return true if 1st argument is less than the 2nd argument
        // i.e. return True if a < b
        Boolean compare(cooccurence_count a,cooccurence_count b){
            int counta = a.getCount();
            int countb = b.getCount();
            String sa = a.getCharacter();
            String sb = b.getCharacter();
            if(counta<countb)return false;
            else if(counta>countb)return true;
            int x = sa.compareTo(sb);
            if(x<0)return false;
            return true;
        }

        void merge (LinkedList<cooccurence_count> a, int l, int m, int r){
            // l to m will comprise of left array
            // m+1 to r will comprise of right array
            
            int n1 = m-l+1;
            int n2 = r-m;
            LinkedList<cooccurence_count> left = new LinkedList<cooccurence_count>();
            LinkedList<cooccurence_count> right = new LinkedList<cooccurence_count>();

            for(int i=0;i<n1;i++)left.add(new cooccurence_count(a.get(l+i).getCount(),a.get(l+i).getCharacter()));
            for(int j=0;j<n2;j++)right.add(new cooccurence_count(a.get(m+1+j).getCount(),a.get(m+1+j).getCharacter()));
    
            
            // For debugging
            // try{writer.write("Total -> ");printarray(a,l,r,true);
            // writer.write("Left -> ");printarray(left, 0, n1-1, true);
            // writer.write("Right -> ");printarray(right, 0, n2-1, true);}
            // catch(Exception e){e.printStackTrace();}
            
            int k=l, i=0, j=0;    
            while(i<n1 && j<n2){
                if(compare(left.get(i), right.get(j))){a.set(k,left.get(i));i++;}
                else{a.set(k,right.get(j));j++;}
                k++;
            }
            while(i<n1){a.set(k,left.get(i));i++;k++;}
            while(j<n2){a.set(k,right.get(j));j++;k++;}        
            
            // For debugging
            // try{writer.write("Total -> ");printarray(a,l,r,true);writer.write("\n");}
            // catch(Exception e){e.printStackTrace();}
        }
    
        void mergesort(LinkedList<cooccurence_count> a, int l, int r){
            int m = (l+r)/2;
            if(r>l){
                mergesort(a,l,m);
                mergesort(a,m+1,r);
                merge(a,l,m,r);
            }
            return;
        }

        void printarray(LinkedList<cooccurence_count> array,int l,int r,Boolean flag){
            if(flag){for(int i=l;i<=r;i++){
                // System.out.print(array.get(i).getCharacter());
                // if(i==0)System.out.println();
                // else System.out.print(",");
                try{
                    // writer.write("Char:" + array.get(i).getCharacter() + " Count:" + array.get(i).getCount() + "   ");
                    writer.write(array.get(i).getCharacter());
                    if(i==r){writer.write("\n");/* writer.close(); */}
                    else writer.write(",");
                }
                catch(IOException e){
                    e.printStackTrace();
                }                
            }}
            else{for(int i=l;i>=r;i--){
                // System.out.print(array.get(i).getCharacter());
                // if(i==0)System.out.println();
                // else System.out.print(",");
                try{
                    writer.write(array.get(i).getCharacter());
                    if(i==r){writer.write("\n");/* writer.close(); */}
                    else writer.write(",");
                }
                catch(IOException e){
                    e.printStackTrace();
                }                
            }}
        }

        // Will be using Merge-Sort 
        void sort(LinkedList<cooccurence_count> array){
            // printarray(array, 0, array.size()-1, true);
            mergesort(array, 0, n-1);
            printarray(array, 0, array.size()-1, true);
            try{writer.close();}
            catch(IOException e){e.printStackTrace();}
            // for(int i=n-1;i>=0;i--){
            //     // System.out.print(array.get(i).getCharacter());
            //     // if(i==0)System.out.println();
            //     // else System.out.print(",");
            //     try{
            //         writer.write(array.get(i).getCharacter());
            //         if(i==0){writer.write("\n");writer.close();}
            //         else writer.write(",");
            //     }
            //     catch(IOException e){
            //         e.printStackTrace();
            //     }
                
            // }
        }

        void addInRank(int count, String character){
            cooccurence_count c = new cooccurence_count(count, character);
            rank.add(c);
        }

        void rank(){
            for(int i=1;i<=n;i++){
                int count=0;
                for(int j=0;j<adj[i].size();j++){
                    if(avg.get(adj[i].get(j).getVertexId())!=i){
                        avg.set(adj[i].get(j).getVertexId(),i);
                        count +=adj[i].get(j).getLength();
                    }
                }
                // Add count,String to rank and then call sort to get the answer
                addInRank(count, nodes_reverse.get(i));
            }
            // I have inserted all Strings with their count number in a list
            // Now, I just need to sort them and then print them to get the desired output
            sort(rank);
        }

        
        
        void dfs(int u){
            vis.set(u,1);            
            try{
                writer.write(u);
                count++;
            }
            catch(IOException e){
                e.printStackTrace();
                System.out.println(u);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            for(int i=0;i<adj[u].size();i++){
                if(vis.get(adj[u].get(i).getVertexId())!=1){
                    dfs(adj[u].get(i).getVertexId());
                }
            }
            if(count==n){
                try {
                    writer.close();
                } catch (Exception e) {
                    //TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        BufferedReader inp = new BufferedReader(new FileReader("input.txt"));
        // String nodes_file = s.next();
        // String edges_file = s.next();
        // String function = s.next();
        String nodes_file = inp.readLine();
        String edges_file = inp.readLine();
        String function = inp.readLine();
        inp.close();
        s.close();
        String line = "";
        graph g;
        int i=1;
        int num = 0;        
        FileWriter writer = null;
        HashMap<String, Integer> nodes_temp = new HashMap<>();
        try{
            writer = new FileWriter("output_node.txt");
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(nodes_file));    
            
            br.readLine();
            while((line = br.readLine()) != null){
                String[] values;
                String label;
                // This extract all the labels from nodes.csv file
                if(line.charAt(0)=='\"'){
                    values = line.split("\",\"");
                    label = (values[1].subSequence(0, values[1].length()-1)).toString();                    
                }
                else{
                    values = line.split(",");
                    label = values[1];
                    // writer.append(values[1] + "\n");
                }   
                nodes_temp.put(label,i);
                i++;                             
            }
            num = i-1;
            
            writer.close();
            br.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        g = new graph(num);

        try{
            // writer.open();
            writer = new FileWriter("output_edge.txt");
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

        for (Map.Entry<String, Integer> e : nodes_temp.entrySet()){ 
            // System.out.println("Key: " + e.getKey() + " Value: " + e.getValue()); 
            try{
                g.addVertex(e.getKey(), e.getValue());
                // writer.write("Key:" + e.getKey() + "   ");
                // writer.write("Value:" + e.getValue() + "\n");
            }
            catch(Exception er){
                System.out.println("ERROR");
                er.printStackTrace();
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(edges_file));    
            br.readLine();
            while((line = br.readLine()) != null){
                String[] values;
                String[] sub_values;
                String source, target;
                int weight;
                // This extract all the edges from edges.csv file
                if(line.charAt(0)=='\"'){
                    values = line.split("\",");
                    // writer.append(values[0].subSequence(1, values[0].length()));
                    source = values[0].subSequence(1, values[0].length()).toString();
                    if(values.length == 2) {
                        sub_values = values[1].split(",");
                        // writer.append(sub_values[0]);
                        // writer.append(sub_values[1]);
                        target = sub_values[0];
                        weight = Integer.parseInt(sub_values[1]);
                    }
                    else{
                        // writer.append(values[1].subSequence(1, values[1].length()));
                        // writer.append(values[2]);
                        target = values[1].subSequence(1, values[1].length()).toString();
                        weight = Integer.parseInt(values[2]);
                    }
                }
                else{
                    values = line.split(",\"");
                    if(values.length==1){
                        values = line.split(",");
                        // writer.append(values[0]);
                        // writer.append(values[1]);
                        // writer.append(values[2]);
                        source = values[0];
                        target = values[1];
                        weight = Integer.parseInt(values[2]);
                    }
                    else{
                        // writer.append(values[0]);
                        source = values[0];
                        values = values[1].split("\",");
                        // writer.append(values[0]);                        
                        // writer.append(values[1]);
                        target = values[0];
                        weight = Integer.parseInt(values[1]);
                    }
                }                
                // writer.append("\n");
                // System.out.println(nodes.get("Richards, Franklin B"));
                try{
                    g.addEdge(source, target, weight);
                    g.addEdge(target, source, weight);
                }
                catch(NullPointerException e){
                    System.out.println("Source:" + source + ",Tar:" + target + ",Weight:" +weight + "\n");
                }
            }
            writer.close();
            br.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        // g.dfs(1);
        g.average();
        g.rank();
    }
}

class Edge
{
    // Target vertex ID
    private int vertexId;
    private int length;

    public Edge(int vertexId, int length) {
        this.vertexId = vertexId;
        this.length = length;
    }

    public int getVertexId() {
        return vertexId;
    }

    public int getLength() {
        return length;
    }
}

class cooccurence_count
{
    private int count;
    private String character;
    public cooccurence_count(int count, String character){
        this.count = count;
        this.character = character;
    }
    public int getCount(){
        return count;
    }

    public String getCharacter(){
        return character;
    }
}