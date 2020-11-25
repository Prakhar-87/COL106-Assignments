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

    public int Allocate(int blockSize) {
        if(blockSize<=0)return -1;
        Dictionary temp = freeBlk.Find(blockSize, false);
        if(temp!=null){
            if(temp.size==blockSize){
                int x = temp.address;                
                allocBlk.Insert(x, blockSize, x);
                freeBlk.Delete(temp);
                // System.out.println(freeBlk.sanity());
                // System.out.println(allocBlk.sanity());
                return x;
            }
            else{
                int a = temp.address, s = temp.size, k = temp.key;
                freeBlk.Delete(temp);
                allocBlk.Insert(a, blockSize, a);
                freeBlk.Insert(a+blockSize, s-blockSize, s-blockSize);  
                // System.out.println(freeBlk.sanity());
                // System.out.println(allocBlk.sanity());              
                return a;
            }            
        }
        // System.out.println(freeBlk.sanity());
        // System.out.println(allocBlk.sanity());
        return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary temp = allocBlk.Find(startAddr, true);
        if(temp!=null){            
            freeBlk.Insert(temp.address, temp.size, temp.size);
            allocBlk.Delete(temp);
            // System.out.println(freeBlk.sanity());
            // System.out.println(allocBlk.sanity());
            return 0;
            
        }
        // System.out.println(freeBlk.sanity());
        // System.out.println(allocBlk.sanity());
        return -1;
        
    }


    public void Defragment() {
        BSTree defrag = new BSTree();
        if(freeBlk.getFirst()==null || freeBlk.getFirst().getNext()==null)return;
        for (Dictionary d = freeBlk.getFirst(); d!=null; d = d.getNext())defrag.Insert(d.address, d.size, d.address);

        Dictionary min_address = defrag.getFirst();

        while(min_address!=null){
            
            Dictionary temp = defrag.Find(min_address.address + min_address.size, true);
            
            if(temp!=null){
                int a_temp = temp.address, s_temp = temp.size, k_temp = temp.key;                                
                int a_min = min_address.address, s_min = min_address.size, k_min = min_address.key;
                BSTree min_free = new BSTree(a_min,s_min,s_min);
                BSTree temp_free = new BSTree(a_temp,s_temp,s_temp);
                BSTree min_defrag = new BSTree(a_min,s_min,a_min);
                BSTree temp_defrag = new BSTree(a_temp,s_temp,a_temp);

                freeBlk.Delete(min_free);
                freeBlk.Delete(temp_free);
                defrag.Delete(min_defrag);                
                defrag.Delete(temp_defrag);                                
                defrag.Insert(a_min, s_min+s_temp, a_min);
                freeBlk.Insert(a_min, s_temp+s_min, s_temp+s_min);
                min_address = defrag.getFirst();
            }
            
            else {
                min_address = min_address.getNext();
            }
            
        }
        // delete(defrag);        
        return;
    }
}