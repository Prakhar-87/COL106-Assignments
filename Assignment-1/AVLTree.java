import java.util.Dictionary;

// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    private Boolean compare(AVLTree a, AVLTree b){
        if(a.key>b.key || (a.key==b.key && a.address>b.address))return true;
        return false;
    }

    private int height(AVLTree a){
        if(a==null)return 0;
        return a.height;
    }

    private AVLTree rightrotate(AVLTree n){
        AVLTree temp = n.left;
        n.left = n.left.right;
        temp.right = n;
        n.height = 1 + Math.max(height(n.left),height(n.right));
        temp.height = 1 + Math.max(height(temp.left),height(temp.right));
        return temp;
    }

    private AVLTree leftrotate(AVLTree n){
        AVLTree temp = n.right;
        n.right = n.right.left;
        temp.left = n;
        n.height = 1 + Math.max(height(n.left),height(n.right));
        temp.height = 1 + Math.max(height(temp.left),height(temp.right));
        return temp;
    }

    private AVLTree insert(AVLTree n, AVLTree x){

        // normal bst insertion
        if(n==null){
            return new AVLTree(x.address,x.size,x.key);
        }
        
        if(compare(n, x)){
            n.left = insert(n.left, x);
        }
        else{
            n.right = insert(n.right, x);
        }
        // else return n;

        int diff = height(n.left) - height(n.right);

        if(diff<=1 && diff>=-1){
            n.height = 1+Math.max(height(n.left),height(n.right));            
            return n;
        }

        // System.err.println("!");

        
        if(diff>1 &&compare(n.left, x)){
            //left left
            return rightrotate(n);
        }
        
        else if(diff<-1 && compare(x,n.right)){
            // right right
            return leftrotate(n);
        }
        
        else if(diff>1 && compare(x,n.left)){
            // left right
            n.left = leftrotate(n.left);
            return rightrotate(n);
        }        

        else {
            // right left
            n.right = rightrotate(n.right);
            return leftrotate(n);            
        }
    }
    public AVLTree Insert(int address, int size, int key) 
    { 
        if(this==null)return null;

        AVLTree new_ = new AVLTree(address, size, key), temp = this;
        while(temp.parent!=null)temp=temp.parent;
        if(temp.right==null){
            temp.right = new_;
            new_.parent = temp;
            new_.height = 1; 
            return temp;
        }

        temp = temp.right;
        insert(temp, new_);
        return new_;
        // while(true){
        //     if(compare(new_, temp)){
        //         if(temp.right==null){  
        //             temp.right = new_;
        //             new_.parent = temp;
        //             break;
        //         }
        //         else temp = temp.right;
        //     }
        //     else{
        //         if(temp.left==null){
        //             temp.left = new_;
        //             new_.parent = temp;
        //             break;
        //         }
        //         else temp = temp.left;
        //     }            
        // }
        // AVLTree par = new_;        
        // while(par.parent!=null){
        //     if(Math.abs(height(par.left)-height(par.right))>1){
        //         if(height(par.left)>height(par.right)){
        //             AVLTree x = par, y = par.left;
        //             if(height(y.left)>height(y.right)){
        //                 AVLTree z = y.left;
        //                 // case - right rotate

        //             }
        //             else{
        //                 AVLTree z = y.right;
        //                 // case - left rotate 
        //                 // case - right rotate
        //             }

        //         }
        //         else{

        //         }
        //     }
        //     par.height = Math.max(height(par.left),height(par.right))+1;            
        //     par = par.parent;
        // }
        // return null;
    }

    private AVLTree min_node(AVLTree n){
        while(n.left!=null)n=n.left;
        return n;
    }
    
    private AVLTree delete(AVLTree n, AVLTree x){
        if(n==null)return n;

        if(n.key==x.key && n.address==x.address && n.size==x.size){
            if(n.left==null || n.right==null){
                if(n.left==null)n = n.right;
                else n = n.left;                
            }
            else{
                AVLTree temp = min_node(n.right);
                n.key = temp.key;
                n.right = delete(n.right, temp);
            }            
        }
        else if(compare(x, n))n.right = delete(n.right, x);
        else{
            n.left = delete(n.left, x);
        }
        if(n==null)return null;
        
        int diff = height(n.left) - height(n.right);

        if(diff<=1 && diff>=-1){
            n.height = 1+Math.max(height(n.left),height(n.right));
            return n;
        }
        if(diff>1 && compare(n.left, x)){            
            //left left
            return rightrotate(n);
        }
        
        else if(diff<-1 && compare(n.right,x)){
            // right right
            return leftrotate(n);
        }
        
        else if(diff>1 && compare(x, n.left)){
            // left right
            n.left = leftrotate(n.left);
            return rightrotate(n);
        }        

        else {
            // right left
            n.right = rightrotate(n.right);
            return leftrotate(n);            
        }
    }
    
    public boolean Delete(Dictionary z)
    {
        if(this==null)return false;
        AVLTree temp = this;
        while(temp.parent!=null)temp = temp.parent;
        if(temp.right==null)return false;
        temp = temp.right;
        
        // AVLTree e = new AVLTree(z.address,z.size,z.key);
        // AVLTree x = delete(temp, e);
        // if(x==null)return false;        
        return true;
    }
        
    // public AVLTree Find(int k, boolean exact)
    // { 
    //     return null;
    // }

    // public AVLTree getFirst()
    // { 
    //     return null;
    // }

    // public AVLTree getNext()
    // {
    //     return null;
    // }

    public boolean sanity()
    { 
        return false;
    }
}


