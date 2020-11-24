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
        
        while(min_address!=null){

            Dictionary temp = defrag.Find(min_address.address + min_address.size, true);
            if(temp!=null){
                int a_temp = temp.address, s_temp = temp.size, k_temp = temp.key;
                int a_min = min_address.address, s_min = min_address.size, k_min = min_address.key;
                defrag.Delete(temp);
                freeBlk.Delete(temp);
                defrag.Delete(min_address);
                freeBlk.Delete(min_address);
                defrag.Insert(a_min, s_min+s_temp, a_min);
                freeBlk.Insert(a_min, s_temp+s_min, a_min);
                min_address = defrag.getFirst();
            }
            else min_address = min_address.getNext();
        }
        // delete(defrag);        
        return;
    }
}