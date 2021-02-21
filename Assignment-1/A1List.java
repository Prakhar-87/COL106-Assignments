// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 
    // private static int count=0;

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {   

        // // if insertion is to be done after the head sentinel node
        // A1List temp = this;
        // temp = temp.getFirst();
        // if(temp==null)temp = this;            
        // if(temp.prev!=null){
        //     temp = temp.prev;
        // }
        //     A1List new_ = A1List(address,size,key);
        //     A1List next_ = temp.next;
        //     temp.next = new_;
        //     new_.prev = temp;
        //     new_.next = next_;
        //     next_.prev = new_;
        //     return new_;
        
        // if insertion is to be done on next node
        // {

            if(this==null || this.next==null)return null;

        //     if insertion is called at tail
            // if(this.next==null){
            //     A1List temp = new A1List(address, size, key);
            //     this.next = temp;
            //     temp.prev = this;

            //     temp.key = this.key;        
            //     temp.address = this.address;
            //     temp.size = this.size;
            //     this.key = key;
            //     this.address = address;
            //     this.size = size;
            //     return temp.prev;
            // }

            A1List temp = new A1List(address, size, key);
            A1List temp1 = this.next;
            this.next = temp;
            temp.prev = this;
            temp.next = temp1;
            temp1.prev = temp;
            return temp;  
        // }

    }

    public boolean Delete(Dictionary d)
    {
        if(this==null)return false;
        A1List temp = this;
        if(temp.getFirst()==null)return false;

        // if I have to delete the node "this" is pointing to
        // then I will just copy the values of previous node into "this" and delete previous node
        if(this.next!=null && this.prev!=null && this.key==d.key && this.address==d.address && this.size==d.size){
            A1List prev_ = this.prev;
            this.key = prev_.key;
            this.address = prev_.address;
            this.size = prev_.size;
            if(prev_.prev==null){
                this.prev = null;
                prev_ = null;
                return true;
            }
            this.prev = prev_.prev;
            prev_.prev.next = this;
            prev_ = null;
            return true;
        }

        temp = temp.getFirst();
        for(A1List x = temp; x!=null; x = x.getNext()){
            if(x.key==d.key && x==d){
                A1List next_ = x.next, prev_ = x.prev;
                next_.prev = prev_;
                prev_.next = next_;
                x = null;
                return true;
            }
        }
        return false;        
    }

    public A1List Find(int k, boolean exact)
    { 
        if(this==null)return null;
        A1List temp = this;
        temp = temp.getFirst();
        if(temp==null)return null;
        if(exact){      
            for(A1List d = temp; d!=null;d = d.getNext()){
                if(d.key==k)return d;
            }
            return null;
        }
        else{
            for(A1List d = temp; d!=null;d = d.getNext()){
                if(d.key>=k)return d;
            }
            return null;
        }
    }

    public A1List getFirst()  
    {   
        if(this==null)return null;
        if((this.next==null && this.prev.prev==null) ||(this.prev==null && this.next.next==null))return null;
        A1List temp = this;
        while(temp.prev!=null)temp=temp.prev;
        return temp.next;
    }
    
    public A1List getNext()  
    {            
        if(this==null)return null;
        if(this.next==null || this.next.next==null)return null;
        return this.next;
    }

    public boolean sanity()  
    {    
        // If this is null then this is a faulty case     
        if(this==null)return false;

        // If mode A1List is empty i.e. I only have head and tail sentinel
        if((this.next==null && this.prev.prev==null) ||(this.prev==null && this.next.next==null))return true;

        // For checking Circular list after "this" using Tortoise and Hare Algorithm
        if(this.prev!=null){
            A1List fast = this.prev, slow = this;
            while(fast!=slow && fast.prev!=null && fast.prev.prev!=null){
                fast = fast.prev.prev;
                slow = slow.prev;
            }
            if(slow==fast)return false;
        }
        
        // For checking Circular list before "this" using Tortoise and Hare Algorithm
        if(this.next!=null){
            A1List fast = this.next, slow = this;
            while(fast!=slow && fast.next!=null && fast.next.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }
            if(slow==fast)return false;
        }

        A1List temp = this;

        // I can safely call getfirst because list is not circular (if my program reaches uptill this point)
        temp = temp.getFirst();
        temp = temp.prev;
        // previous of the head node should be null
        if(temp.prev!=null)return false;

        // size, address and key of head sentinel node should be -1 (as in constructor)
        if(temp.key!=-1 || temp.address!=-1 || temp.size!=-1)return false;

        // I can safely use this because I can safely assume List is not circular if I have reached this line
        while(temp.next!=null){
            
            // this condition should hold for each node for a valid List (if not then return False)
            if(temp.next.prev!=temp)return false;

            temp = temp.next;
        }
        // this while loop gives me tail sentinel

        // size, address and key of tail sentinel node should be -1 (as in constructor)
        if(temp.key!=-1 || temp.address!=-1 || temp.size!=-1)return false;
        
        return true; 
    }
}