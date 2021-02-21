import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;

public class A4_2019CS50441{

    static class graph{
        int n;
        ArrayList<Edge>[] adj;
        ArrayList<Integer> vis; // Keeping check of visited nodes
        ArrayList<Integer> avg; // For 1st part
        ArrayList<cooccurence_count> rank; // For 2nd part, will sort it to get desired output
        HashMap<String, Integer> nodes = new HashMap<>();
        HashMap<Integer, String> nodes_reverse = new HashMap<>();
        ArrayList<String>[] scc;
        // FileWriter writer;
        int count = 0;
        graph(int x){
            try{
                // writer = new FileWriter("rank_prakhar.txt");
                // writer = new FileWriter("rank_prakhar.txt");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            this.n = x;
            // this.writer = writer;
            adj = new ArrayList[n+1];
            scc = new ArrayList[n+1];
            vis = new ArrayList<Integer>();
            avg = new ArrayList<Integer>();
            rank = new ArrayList<cooccurence_count>();
            for(int i=0;i<=n;i++){
                adj[i] = new ArrayList<>();
                vis.add(0);
                avg.add(0);
            }            
        }

        void addVertex(String s,int i,int number_){
            nodes.put(s,i);
            nodes_reverse.put(i,s);
            // if(number_>172000){
            //     System.out.println(number_);
            // }
        }

        void addEdge(String s,String d,int w){
            Edge e = new Edge(nodes.get(d),w);
            adj[nodes.get(s)].add(e);
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
        Boolean compare(cooccurence_count a,cooccurence_count b){
            int counta = a.getCount();
            int countb = b.getCount();
            String sa = a.getCharacter();
            String sb = b.getCharacter();
            if(counta<countb)return false;
            else if(counta>countb)return true;
            int x = sa.compareTo(sb);
            if(x>=0)return true;
            return false;
        }

        void merge (ArrayList<cooccurence_count> a, int l, int m, int r){
            // l to m will comprise of left array
            // m+1 to r will comprise of right array
            
            int n1 = m-l+1;
            int n2 = r-m;
            ArrayList<cooccurence_count> left = new ArrayList<cooccurence_count>();
            ArrayList<cooccurence_count> right = new ArrayList<cooccurence_count>();

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
    
        void mergesort(ArrayList<cooccurence_count> a, int l, int r){
            int m = (l+r)/2;
            if(r>l){
                mergesort(a,l,m);
                mergesort(a,m+1,r);
                merge(a,l,m,r);
            }
            return;
        }

        void printarray(ArrayList<cooccurence_count> array,int l,int r,Boolean flag){
            if(flag){
                for(int i=l;i<=r;i++){
                    // System.out.print(array.get(i).getCharacter() + " " + array.get(i).getCount() + "\n");
                    System.out.print(array.get(i).getCharacter());
                    if(i==r)System.out.println();
                    else System.out.print(",");
                }
            }
        }  

        void sort(ArrayList<cooccurence_count> array){
            // printarray(array, 0, array.size()-1, true);
            mergesort(array, 0, n-1);
            printarray(array, 0, array.size()-1, true);
        }

        void addInRank(int count, String character){
            cooccurence_count c = new cooccurence_count(count, character);
            rank.add(c);
        }
  


// -----------------------------------------------------------------------------------------------------------------------------------------------------------        
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
        void rank(){
            // System.out.println(n);
            // System.out.println(nodes.get("AaKalibre (New Earth)Aa"));
            // System.out.println(nodes_reverse.get(18361));
            for(int i=1;i<=n;i++){
                int count=0;
                for(int j=0;j<adj[i].size();j++){
                    // if(avg.get(adj[i].get(j).getVertexId())!=i){
                        if(i==18361){
                            avg.set(adj[i].get(j).getVertexId(),i);
                            count +=adj[i].get(j).getLength();
                            // System.out.println(count);
                        }
                        else{
                            avg.set(adj[i].get(j).getVertexId(),i);
                            count +=adj[i].get(j).getLength();    
                        }
                        
                    // }
                }
                addInRank(count, nodes_reverse.get(i));
            }
            sort(rank);
        }
 
        // Code for 3rd Part
        Boolean compare_dfs(String a,String b){
            int x = a.compareTo(b);
            if(x>=0)return true;
            return false;
        }
        
        void merge_dfs(ArrayList<String> a, int l, int m, int r){
            // l to m will comprise of left array
            // m+1 to r will comprise of right array
            
            int n1 = m-l+1;
            int n2 = r-m;
            ArrayList<String> left = new ArrayList<String>();
            ArrayList<String> right = new ArrayList<String>();

            try{for(int i=0;i<n1;i++)left.add(a.get(i+l));
            for(int j=0;j<n2;j++)right.add(a.get(m+1+j));}
            catch(Exception e){
                System.out.println("Size:" + a.size() + ",l:" + l + ",r:" + r + ",m:" + m);
            }    
            int k=l, i=0, j=0;    
            while(i<n1 && j<n2){
                if(compare_dfs(left.get(i), right.get(j))){a.set(k,left.get(i));i++;}
                else{a.set(k,right.get(j));j++;}
                k++;
            }
            while(i<n1){a.set(k,left.get(i));i++;k++;}
            while(j<n2){a.set(k,right.get(j));j++;k++;}        
        }
    
        void mergesort_dfs(ArrayList<String> a, int l, int r){
            int m = (l+r)/2;
            if(r>l){
                mergesort_dfs(a,l,m);
                mergesort_dfs(a,m+1,r);
                merge_dfs(a,l,m,r);
            }
            return;
        }
        
        void sort_dfs(ArrayList<String> array){
            // printarray(array, 0, array.size()-1, true);
            mergesort_dfs(array, 0, array.size()-1);
            // printarray(array, 0, array.size()-1, true);
        }


        Boolean compare_int(pair a,pair b){
            if(a.getSize()>b.getSize() || (a.getSize()==b.getSize() && compare_dfs(a.getString(), b.getString())))return true;
            return false;
        }
        
        void merge_int(ArrayList<pair> a, int l, int m, int r){
            int n1 = m-l+1;
            int n2 = r-m;
            ArrayList<pair> left = new ArrayList<pair>();
            ArrayList<pair> right = new ArrayList<pair>();

            for(int i=0;i<n1;i++)left.add(a.get(i+l));
            for(int j=0;j<n2;j++)right.add(a.get(m+1+j));
            
            int k=l, i=0, j=0;    
            while(i<n1 && j<n2){
                if(compare_int(left.get(i), right.get(j))){a.set(k,left.get(i));i++;}
                else{a.set(k,right.get(j));j++;}
                k++;
            }
            while(i<n1){a.set(k,left.get(i));i++;k++;}
            while(j<n2){a.set(k,right.get(j));j++;k++;}        
        }
        
        void mergesort_int(ArrayList<pair> a, int l, int r){
            int m = (l+r)/2;
            if(r>l){
                mergesort_int(a,l,m);
                mergesort_int(a,m+1,r);
                merge_int(a,l,m,r);
            }
        }
        
        void sort_int(ArrayList<pair> array){
            // printarray(array, 0, array.size()-1, true);
            mergesort_int(array, 0, array.size()-1);
            // printarray(array, 0, array.size()-1, true);
        }
        
        void dfs(int u,int num_scc){
            vis.set(u,1);            
            scc[num_scc].add(nodes_reverse.get(u));

            for(int i=0;i<adj[u].size();i++){
                if(vis.get(adj[u].get(i).getVertexId())!=1){
                    dfs(adj[u].get(i).getVertexId(),num_scc);
                }
            }
        }
        
        void independent_storylines_dfs(){      
            int num_scc = 0;      
            ArrayList<pair> order = new ArrayList<pair>();
            // pair -> Constructor -> (size,FirstString,SCC_number)
            for(int i=1;i<=n;i++){
                if(vis.get(i)!=1){
                    scc[num_scc] = new ArrayList<>();
                    dfs(i,num_scc);
                    num_scc++;
                }
            }
            for(int i=0;i<num_scc;i++){
                sort_dfs(scc[i]);
                order.add(new pair(scc[i].size(),scc[i].get(0),i));
            }
            sort_int(order);
            for(int i=0;i<num_scc;i++){
                for(int j=0;j<scc[order.get(i).getSccNum()].size();j++){
                    System.out.print(scc[order.get(i).getSccNum()].get(j));
                    if(j!=scc[order.get(i).getSccNum()].size()-1){System.out.print(",");}
                    else System.out.println();
                    // try{
                        // writer.write(scc[order.get(i).getSccNum()].get(j));
                        // if(j!=scc[order.get(i).getSccNum()].size()-1){
                        //     writer.write(",");
                        // }
                    // }
                    // catch(Exception e){e.printStackTrace();}
                }
            }
        }            
    }

    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        // FileOutputStream f = new FileOutputStream("rank_data2_v2.txt");
        // FileOutputStream f = new FileOutputStream("dfs_data2_v2.txt");
        // System.setOut(new PrintStream(f));
        // PrintStream console = System.out;
        // BufferedReader inp = new BufferedReader(new FileReader("input_data2.txt"));
        // String nodes_file = s.next();
        // String edges_file = s.next();
        // String function = s.next();
        // String nodes_file = inp.readLine();
        // String edges_file = inp.readLine();
        // String function1 = inp.readLine();
        // String function2 = inp.readLine();
        // String function3 = inp.readLine();
        // inp.close();
        String nodes_file = args[0];
        String edges_file = args[1];
        String function = args[2];
        s.close();
        // System.out.println(nodes_file + " " + edges_file + " " + function);
        String line = "";
        graph g;
        int i=1;
        int num = 0;        
        HashMap<String, Integer> nodes_temp = new HashMap<>();

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
            
            // writer.close();
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
            // writer = new FileWriter("output_edge.txt");
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
        int number=0;
        for (Map.Entry<String, Integer> e : nodes_temp.entrySet()){ 
            // System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());             
            try{
                g.addVertex(e.getKey(), e.getValue(),number);
                number++;
                // writer.write("Key:" + e.getKey() + "   ");
                // writer.write("Value:" + e.getValue() + "\n");
            }
            catch(Exception er){
                System.out.println("ERROR");
                er.printStackTrace();
            }
        }
        // System.out.println(number);

        try {
            BufferedReader br = new BufferedReader(new FileReader(edges_file));    
            br.readLine();
            while((line = br.readLine()) != null){
                String[] values;
                String[] sub_values;
                String source, target;
                int weight=0;
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
                        source = values[0];
                        target = values[1];
                        try{weight = Integer.parseInt(values[2]);}
                        catch(Exception e){System.out.println("ERROR1\n" + source + "      " + target);}
                    }
                    else{
                        source = values[0];
                        values = values[1].split("\",");
                        target = values[0];
                        try{weight = Integer.parseInt(values[1]);}
                        catch(Exception e){System.out.println("ERROR2\n" + source + "      " + target);}
                    }
                }                
                int tempe=0;
                try{
                    g.addEdge(source, target, weight);
                    g.addEdge(target, source, weight);
                }
                catch(NullPointerException e){
                    if(tempe==0)
                    {System.out.println("Source:" + source + ",Tar:" + target + ",Weight:" +weight + "\n");tempe++;}
                }
            }
            br.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        if(function.equals("average")){
            if(num==0)System.out.println("0.00");
            else g.average();                    
        }
        else if(function.equals("rank")){
            if(num==0)System.out.println("");
            else g.rank();                    
        }
        else if(function.equals("independent_storylines_dfs")){
            if(num==0)System.out.println("");
            else g.independent_storylines_dfs();
        }
        
        // long start = System.currentTimeMillis();
        // if(function1.equals("average")){
        //     g.average();
        // }
        // long end = System.currentTimeMillis();
        // System.setOut(console);
        // // System.out.println("Average:" + (double)(end-start) + " ms");
        // System.setOut(new PrintStream(f));
        // start = System.currentTimeMillis();    
        // if(function2.equals("rank")){
        //     g.rank();
        // }
        // end = System.currentTimeMillis();
        // System.setOut(console);
        // // System.out.println("Rank:" + (double)(end-start) + " ms");
        // System.setOut(new PrintStream(f));
        // start = System.currentTimeMillis();    
        // // if(function3.equals("independent_storylines_dfs")){
        // //     g.independent_storylines_dfs();
        // // } 
        // end = System.currentTimeMillis();
        // System.setOut(console);
        // // System.out.println("Independent_storylines_dfs:" + (double)(end-start) + " ms");
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

class pair{
    private int size_scc;
    private String first;
    private int scc_number;

    pair(int size,String first,int num){
        this.size_scc = size;
        this.first = first;
        this.scc_number = num;
    }    
    public int getSize(){
        return this.size_scc;
    }
    public String getString(){
        return this.first;
    }
    public int getSccNum(){
        return this.scc_number;
    }
}