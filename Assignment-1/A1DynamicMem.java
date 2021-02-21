// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize<=0)return -1;
        Dictionary temp = freeBlk.Find(blockSize, false);
        // System.out.println("Size:"+temp.size + "   Address:"+temp.address + "   Key:"+temp.key);
        if(temp!=null){
            if(temp.size==blockSize){
                freeBlk.Delete(temp);
                allocBlk.Insert(temp.address, temp.size, temp.address);
            }
            else{
                freeBlk.Delete(temp);
                allocBlk.Insert(temp.address, blockSize, temp.address);
                freeBlk.Insert(temp.address+blockSize, temp.size-blockSize, temp.size-blockSize);                
            }
            return temp.address;
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary temp = allocBlk.Find(startAddr, true);
        if(temp!=null){
            allocBlk.Delete(temp);
            freeBlk.Insert(temp.address, temp.size, temp.size);
            return 0;
        }
        return -1;
    }
}