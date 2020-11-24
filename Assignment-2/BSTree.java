// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    private Boolean compare(BSTree a, BSTree b){
        if(a.key>b.key || (a.key==b.key && a.address>b.address))return true;
        return false;
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        if(this==null)return null;
        BSTree new_ = new BSTree(address, size, key), temp = this;
        while(temp.parent!=null)temp=temp.parent;
        if(temp.right==null){temp.right = new_;new_.parent = temp;return new_;}
        temp = temp.right;

        while(true){
            if(compare(new_, temp)){
                if(temp.right==null){  
                    temp.right = new_;
                    new_.parent = temp;
                    return new_;
                }
                else temp = temp.right;
            }
            else{
                if(temp.left==null){
                    temp.left = new_;
                    new_.parent = temp;
                    return new_;
                }
                else temp = temp.left;
            }
        }

    }

    public boolean Delete(Dictionary z)
    { 
        if(this==null)return false;
        BSTree temp = this;
        while(temp.parent!=null)temp = temp.parent;
        if(temp.right==null)return false;
        temp = temp.right;
        
        BSTree e = new BSTree(z.address,z.size,z.key);
        
        // Boolean flag = delete(temp, e);
        // return flag;

        while(temp!=null && (temp.key!=e.key || temp.address!=e.address || temp.size!=e.size)){
            if(compare(e, temp)){
                temp = temp.right;
            }
            else temp = temp.left;
        }

        if(temp==null)return false;

        if(temp.left==null && temp.right==null){
            if(temp.parent.left==temp)temp.parent.left = null;
            if(temp.parent.right==temp)temp.parent.right = null;            
            // temp.parent = null;
            temp = null;
            return true;
        }

        if(temp.left==null || temp.right==null){
            if(temp.left==null){
                if(temp.parent.left==temp){
                    temp.parent.left = temp.right;
                    temp.right.parent = temp.parent;
                    temp = null;
                }
                else{
                    temp.parent.right = temp.right;
                    temp.right.parent = temp.parent;
                    temp = null;
                }    
            }
            else{
                if(temp.parent.left==temp){
                    temp.parent.left = temp.left;
                    temp.left.parent = temp.parent;
                    temp = null;
                }
                else{
                    temp.parent.right = temp.left;
                    temp.left.parent = temp.parent;
                    temp = null;
                }
            }
            return true;
        }

        BSTree right_child = temp.right;
        BSTree succ = right_child;
        while(succ.left!=null)succ = succ.left;
        temp.key = succ.key;
        temp.address = succ.address;
        temp.size = succ.size;

        if(succ.right==null){
            if(succ.parent.left==succ)succ.parent.left = null;
            if(succ.parent.right==succ)succ.parent.right = null;            
            succ.parent = null;
            succ = null;
            return true;
        }

        //succ.right is not null
        if(succ.parent.left==succ){
            succ.parent.left = succ.right;
            succ.right.parent = succ.parent;
            succ = null;
        }
        else{
            succ.parent.right = succ.right;
            succ.right.parent = succ.parent;
            succ = null;
        }
        return true;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        if(this==null)return null;

        BSTree temp = this;
        while(temp.parent!=null)temp=temp.parent;
        if(temp.right==null)return null;
        temp = temp.right;

        if(exact){
            while(temp!=null && temp.key!=key){
                if(temp.key<key)temp=temp.right;
                else temp=temp.left;
            }
            if(temp==null)return null;

            // so the temp that we got after this loop may not be the one with minimum address
            // so we need to look in the left subtree
            int x = temp.key;
            BSTree to_return = temp;
            while(true){
                if(temp==null || temp.left==null)return to_return;
                temp = temp.left;
                if(temp.key==x){
                    to_return = temp;
                    // temp = temp.left;
                }
                else temp = temp.right;
            }
        }
        else{
            while(temp!=null && temp.key<key)temp=temp.right;
            if(temp==null)return null;
            
            //Used to implement Best Split Fit
            // So, after this while loop, I will land on a node whose key value is greater than required key
            // So, to implement Best Split Fit, I need to look in the node's left subtree

            // if required key is the value of node itself or it doesn't have left subtree
            // if(temp.key==key || temp.left==null)return temp;

            BSTree to_return = temp;
            while(true){
                while(temp.left !=null && temp.left.key>=key)temp=temp.left;
                // to_return is that min node in left chain whose key value os greater than reqiured key
                to_return = temp;
                
                // if left of temp is null then I'm done
                if(temp.left==null)return temp;
                else{
                    temp=temp.left;
                    while(temp.right!=null && temp.right.key<key)temp=temp.right;
                    if(temp.right==null)return to_return;
                    // the node to right of temp will be the node whose value is >= required key
                    temp=temp.right;
                    to_return = temp;
                }
            }
        }
    }

    public BSTree getFirst()
    { 
        BSTree temp = this;
        while(temp.parent!=null)temp = temp.parent;
        if(temp.right==null)return null;
        temp = temp.right;
        while(temp.left!=null)temp=temp.left;
        return temp;
    }

    public BSTree getNext()
    { 
        BSTree temp = this;
        if(temp.right!=null){
            temp=temp.right;
            while(temp.left!=null)temp=temp.left;
            return temp;
        }
        while(temp.parent!=null && temp.parent.left!=temp)temp=temp.parent;
        if(temp.parent==null)return null;
        return temp.parent;
    }

    public boolean sanity()
    { 
        return false;
    }
    // public static void main(String[] args) {
    //     BSTree test=new BSTree();
    //     test.Insert(10, 1, 1);
    //     test.Insert(113, 387, 387);
    //     test.Insert(9, 1, 1);
    //     test.Insert(6, 2, 2);
        // System.out.println(test.right.left.address);
        // System.out.println(test.Find(1, false).address);
        // test.Insert(0, 6, 0);
        // test.Insert(6, 5, 6);
        // test.Insert(10 , 5, 5);
        // test.Insert(20, 10, 6);
        // test.Insert(30, 15, 14);
        // test.Insert(40, 20, 20);
        // test.Insert(23, 16, 7);
        // test.Insert(24, 17, 7);
        // test.Insert(27, 17, 13);
        // test.Insert(28, 17, 14);
        // test.Insert(26, 18, 10);
        // test.Insert(25, 19, 9);

        // for(BSTree d = test.getFirst();d!=null;d=d.getNext()){
        //     System.out.println(d.address + "," + d.size + ","+d.key);
        // }

    //         System.out.println(test.right.key + " "+test.right.right.key);

    //     // BSTree bfit = test.Find(8, false);
    //     // System.out.println(bfit.address + " " + bfit.size + " " + bfit.key);

    //     // Dictionary iit = new BSTree(25, 19, 9);
    //     // test.Delete(iit);
    //     // bfit = test.Find(8, false);
    //     // System.out.println(bfit.address + " " + bfit.size + " " + bfit.key);

    // }

}