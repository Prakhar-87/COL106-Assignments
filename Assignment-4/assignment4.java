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
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String nodes_file = s.next();
        String edges_file = s.next();
        String function = s.next();
        String line = "";
        s.close();
        FileWriter writer = null;
        HashMap<String, Integer> nodes = new HashMap<>();
        try{
            writer = new FileWriter("output_node.txt");
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(nodes_file));    
            int i=1;
            int num = 0;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] values;
                String label;
                // This extract all the labels from nodes.csv file
                if(line.charAt(0)=='\"'){
                    values = line.split("\",\"");
                    label = (values[1].subSequence(0, values[1].length()-1) + "\n");                    
                }
                else{
                    values = line.split(",");
                    label = values[1];
                    // writer.append(values[1] + "\n");
                }   
                nodes.put(label,i);
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

        try{
            // writer.open();
            writer = new FileWriter("output_edge.txt");
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(edges_file));    
            br.readLine();
            while((line = br.readLine()) != null){
                String[] values;
                String[] sub_values;
                // This extract all the edges from edges.csv file
                if(line.charAt(0)=='\"'){
                    values = line.split("\",");
                    writer.append(values[0].subSequence(1, values[0].length()));
                    if(values.length == 2) {
                        sub_values = values[1].split(",");
                        writer.append(sub_values[0]);
                        writer.append(sub_values[1]);
                    }
                    else{
                        writer.append(values[1].subSequence(1, values[1].length()));
                        writer.append(values[2]);
                    }
                }
                else{
                    values = line.split(",\"");
                    if(values.length==1){
                        values = line.split(",");
                        writer.append(values[0]);
                        writer.append(values[1]);
                        writer.append(values[2]);
                    }
                    else{
                        writer.append(values[0]);
                        values = values[1].split("\",");
                        writer.append(values[0]);
                        writer.append(values[1]);
                    }
                }                
                writer.append("\n");
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

    }
}

class Vertex
{
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(Vertex v) {
        this.id = v.getId();
    }

    public int getId() {
        return id;
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