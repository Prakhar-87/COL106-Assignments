// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        BSTree defrag = new BSTree();
        if(freeBlk.getFirst()==null || freeBlk.getFirst().getNext()==null)return;
        for (Dictionary d = freeBlk.getFirst(); d!=null; d = d.getNext())defrag.Insert(d.address, d.size, d.address);

        Dictionary min_address = defrag.getFirst();

        // System.out.print("Min_address is : ");
        // System.out.print("(" + min_address.address + "," + min_address.size + "," + min_address.key + ") ");

        // System.out.print("Defrag is : ");

        // for(Dictionary d=defrag.getFirst();d!=null;d=d.getNext()){
        //     System.out.print("(" + d.address + "," + d.size + "," + d.key + ") ");
        // }

        // System.out.println("");
        

        while(min_address!=null){
            // Dictionary min_ = min_address;
            Dictionary temp = defrag.Find(min_address.address + min_address.size, true);
            // Dictionary temp_ = temp;
            if(temp!=null){
                int a_temp = temp.address, s_temp = temp.size, k_temp = temp.key;                                
                int a_min = min_address.address, s_min = min_address.size, k_min = min_address.key;
                // min_.key = min_.size;
                // temp_.key = temp_.size;
                BSTree min_free = new BSTree(a_min,s_min,s_min);
                BSTree temp_free = new BSTree(a_temp,s_temp,s_temp);
                BSTree min_defrag = new BSTree(a_min,s_min,a_min);
                BSTree temp_defrag = new BSTree(a_temp,s_temp,a_temp);

                // System.out.print("("+min_.address+","+min_.size+","+min_.key+") ");
                // System.out.print("("+temp_.address+","+temp_.size+","+temp_.key+") ");

                freeBlk.Delete(min_free);
                freeBlk.Delete(temp_free);
                defrag.Delete(min_defrag);                
                defrag.Delete(temp_defrag);                                
                defrag.Insert(a_min, s_min+s_temp, a_min);
                freeBlk.Insert(a_min, s_temp+s_min, s_temp+s_min);
                min_address = defrag.getFirst();
                
                // System.out.print("Min_address is : ");
                // System.out.print("(" + min_address.address + "," + min_address.size + "," + min_address.key + ") ");

                // System.out.print("Defrag is : ");

                // for(Dictionary d=defrag.getFirst();d!=null;d=d.getNext()){
                //     System.out.print("(" + d.address + "," + d.size + "," + d.key + ") ");
                // }
                // System.out.println("");

            }
            
            else {
                // System.out.print("Min_address is : ");
                // System.out.print("(" + min_address.address + "," + min_address.size + "," + min_address.key + ") ");    
                min_address = min_address.getNext();
                // if(min_address!=null) {
                //     System.out.print("Min_address is : ");
                //     System.out.print("(" + min_address.address + "," + min_address.size + "," + min_address.key + ") ");    
                // }
                // System.out.println("");
            }
            
        }
        // delete(defrag);        
        return;
    }
}