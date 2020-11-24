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

    public void printBlk(){
        System.out.print("freeBlk is : ");
        Dictionary first = this.freeBlk.getFirst();
        while(first!=null){
          System.out.print(" ("+first.address+", "+first.size+", "+first.key+") ");
          if(this.freeBlk==first) System.out.print(" <-- ");
          first = first.getNext();
        }
  
        System.out.print("\nallocBlk is : ");
        first = this.allocBlk.getFirst();
        while(first!=null){
          System.out.print(" ("+first.address+", "+first.size+", "+first.key+") ");
          if(this.allocBlk==first) System.out.print(" <-- ");
          first = first.getNext();
        }
        System.out.print("\n");
      }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize<=0)return -1;
        Dictionary temp = freeBlk.Find(blockSize, false);
        // System.out.println("Size:"+temp.size + "   Address:"+temp.address + "   Key:"+temp.key);
        if(temp!=null){
            if(temp.size==blockSize){
                int x = temp.address;                
                allocBlk.Insert(x, blockSize, x);
                freeBlk.Delete(temp);
                return x;
            }
            else{
                int a = temp.address, s = temp.size, k = temp.key;
                freeBlk.Delete(temp);
                allocBlk.Insert(a, blockSize, a);
                freeBlk.Insert(a+blockSize, s-blockSize, s-blockSize);                
                return a;
            }
            
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary temp = allocBlk.Find(startAddr, true);
        if(temp!=null){            
            freeBlk.Insert(temp.address, temp.size, temp.size);
            allocBlk.Delete(temp);
            return 0;
        }
        return -1;
    }
}