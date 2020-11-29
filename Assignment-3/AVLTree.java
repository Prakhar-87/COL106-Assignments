// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    // private int height;  // The height of the subtree
        
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
        if(a==null)return -1;
        return a.height;
    }

    private AVLTree rightrotate(AVLTree n){
        AVLTree gr = n.parent;
        AVLTree par = n;
        AVLTree lc = n.left;
        AVLTree lc_r = lc.right;
        if(gr.right==par)gr.right = lc;
        else gr.left = lc;
        lc.right = par;
        lc.parent = gr;
        par.left = lc_r;
        if(lc_r!=null)lc_r.parent = par;
        par.parent = lc;
        par.height = 1 + Math.max(height(par.left), height(par.right));
        lc.height = 1 + Math.max(height(par),height(lc.left));
        return lc;
    }

    private AVLTree leftrotate(AVLTree n){
        AVLTree gr = n.parent;
        AVLTree par = n;
        AVLTree rc = n.right;
        AVLTree rc_l = rc.left;
        if(gr.right==par)gr.right = rc;
        else gr.left = rc;
        rc.left = par;
        rc.parent = gr;
        par.right = rc_l;
        if(rc_l!=null)rc_l.parent = par;
        par.parent = rc;
        par.height = 1 + Math.max(height(par.left), height(par.right));
        rc.height = 1 + Math.max(height(par),height(rc.right));
        return rc;                                    
    }

    private AVLTree insert(AVLTree n, AVLTree x){

        // normal bst insertion
        if(n==null){
            return new AVLTree(x.address,x.size,x.key);
        }
        
        if(compare(n, x)){
            n.left = insert(n.left, x);
            n.left.parent = n;
            // return n;
        }
        else{
            n.right = insert(n.right, x);
            n.right.parent = n;
            // return n;
        }
        // else return n;
        

        int diff = height(n.left) - height(n.right);

        if(diff<=1 && diff>=-1){
            n.height = 1+Math.max(height(n.left),height(n.right));            
            return n;
        }

        // // System.err.println("!");

        
        if(diff>1 && height(n.left.left)>=height(n.left.right)){
            //left left
            return rightrotate(n);
        }
        
        else if(diff<-1 && height(n.right.right)>=height(n.right.left)){
            // right right
            return leftrotate(n);
        }
        
        else if(diff>1){
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
        // System.out.println(this.key);
        if(this==null)return null;

        AVLTree new_ = new AVLTree(address, size, key), temp = this;
        while(temp.parent!=null)temp=temp.parent;
        if(temp.right==null){
            temp.right = new_;
            new_.parent = temp;
            // System.out.println(this.right.key);
            // System.out.println(new_.parent.key);
            return new_;
        }

        temp = temp.right;
        insert(temp, new_);
        return new_;        
    }
    
    private AVLTree min_node(AVLTree n){
        while(n.left!=null)n=n.left;
        return n;
    }

    private int count = 0;
    
    private AVLTree delete(AVLTree n, AVLTree x){
        if(n==null)return null;

        if(n.key==x.key && n.address==x.address && n.size==x.size){
            count++;
            if(n.left==null && n.right==null){
                AVLTree par = n.parent; 
                if(par.left==n)par.left=null;
                else par.right=null;
                n.parent=null;
                return null;
            }
            else if(n.right==null){
                n.address = n.left.address;
                n.key = n.left.key;
                n.size = n.left.size;
                n.left.parent = null;
                n.left = null;
            }
            else if(n.left==null){
                n.address = n.right.address;
                n.key = n.right.key;
                n.size = n.right.size;
                n.right.parent = null;
                n.right = null;                
            }
            else{
                AVLTree temp = min_node(n.right);
                n.address = temp.address; temp.address = x.address;
                n.key = temp.key; temp.key = x.key;
                n.size = temp.size; temp.size = x.size;
                AVLTree k = delete(n.right, x);
                if(n.right!=null){n.right=k;n.right.parent = n;}
            }
        }
        else if(compare(x, n)) {
            AVLTree k = delete(n.right, x);
            if(n.right!=null){n.right = k;k.parent=n;}
        }
        else {
            AVLTree k = delete(n.left, x);
            if(n.left!=null){n.left=k;k.parent=n;}
        }
                
        int diff = height(n.left) - height(n.right);

        if(diff<=1 && diff>=-1){
            n.height = 1+Math.max(height(n.left),height(n.right));
            return n;
        }
        if(diff>1 && height(n.left.left)>=height(n.left.right)){
            //left left
            return rightrotate(n);
        }
        
        else if(diff<-1 && height(n.right.right)>=height(n.right.left)){
            // right right
            return leftrotate(n);
        }
        
        else if(diff>1){
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
        count=0;
        AVLTree e = new AVLTree(z.address,z.size,z.key);
        delete(temp, e);
        if(count>0)return true;            
        return false;
    }

    public AVLTree Find(int key, boolean exact)
    { 
        if(this==null)return null;

        AVLTree temp = this;
        while(temp.parent!=null)temp=temp.parent;
        if(temp.right==null)return null;
        temp = temp.right;

        //Exact==true means we have to find a node such that node.key == required key        
        if(exact){
            while(temp!=null && temp.key!=key){
                if(temp.key<key)temp=temp.right;
                else temp=temp.left;
            }
            if(temp==null)return null;

            // so the temp that we got after this loop may not be the one with minimum address
            // so we need to look in the left subtree
            int x = temp.key;
            AVLTree to_return = temp;
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
        // Exact == false means we need to find smallest node such that node.key >= key
        else{
            while(temp!=null && temp.key<key)temp=temp.right;
            if(temp==null)return null;
            
            //Used to implement Best Split Fit
            // So, after this while loop, I will land on a node whose key value is greater than required key
            // So, to implement Best Split Fit, I need to look in the node's left subtree

            // if required key is the value of node itself or it doesn't have left subtree
            // if(temp.key==key || temp.left==null)return temp;

            AVLTree to_return = temp;
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
        
    public AVLTree getFirst()
    { 
        if(this==null)return null;
        AVLTree temp = this;
        while(temp.parent!=null)temp = temp.parent;
        if(temp.right==null)return null;
        temp = temp.right;
        while(temp.left!=null)temp=temp.left;
        return temp;
    }

    // Will return the next element in inorder traversal
    public AVLTree getNext()
    { 
        if(this==null)return null;
        
        // if getnext called on sentinel then return null
        if(this.parent==null)return null;

        AVLTree temp = this;
        if(temp.right!=null){
            temp=temp.right;
            while(temp.left!=null)temp=temp.left;
            return temp;
        }
        while(temp.parent!=null && temp.parent.left!=temp)temp=temp.parent;
        if(temp.parent==null)return null;
        return temp.parent;
    }

    // Function to check correct order of Binary Search Tree (isbst will initiate the process by calling check_bst)
    private boolean isbst(AVLTree root){
        return check_bst(root, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Function to check correct order of BSTree
    // Will check if the current node meets the conditions and then will tighten the bound and check in its left and right subtree
    private boolean check_bst(AVLTree root,int min_key, int max_key, int min_add, int max_add){
        if(root==null)return true;  
        if(root.key<min_key || (root.key==min_key && root.address<min_add))return false;
        if(root.key>max_key || (root.key==max_key && root.address>max_add))return false;

        // left subtree can contain same pairs if it exist, but right subtree can't
        // This is the reason of passing root.address + 1 in 2nd check_bst function
        return (check_bst(root.left, min_key, root.key, min_add, root.address) && check_bst(root.right, root.key, max_key, root.address+1, max_add));
    }

    // Function to check if there is a cycle in my tree or not
    private boolean check_cycle(AVLTree root){
        if(root.left==null && root.right==null)return true;
        if(root.left==null){
            if(root.right.parent!=root)return false;
            return check_cycle(root.right);
        }
        if(root.right==null){
            if(root.left.parent!=root)return false;
            return check_cycle(root.left);
        }
        if(root.right.parent!=root || root.left.parent!=root)return false;
        return check_cycle(root.left) && check_cycle(root.right);        
    }

    private boolean check_height(AVLTree root){
        if(root==null)return true;
        if(Math.abs(height(root.left)-height(root.right))>1)return false;
        if(height(root)!=(1+Math.max(height(root.left), height(root.right))))return false;
        return check_height(root.left)&&check_height(root.right);
    }

    public boolean sanity()
    { 
        if(this==null)return false;
        AVLTree temp = this;
        // will try to go to root and also check for a cycle if any in the path of this to root
        if(this.parent!=null){
            AVLTree slow = temp;
            AVLTree fast = temp.parent;
            while(fast!=slow && fast.parent!=null && fast.parent.parent!=null){
                fast = fast.parent.parent;
                slow = slow.parent;
            }
            if(fast==slow)return false;
            if(fast.parent==null)temp = fast;
            else temp = fast.parent;
        }
        // if we reach here, this means there is no cycle in the path of this to root
        // there might exist a cycle which is not in the path of this to root        
        
        while(temp.parent!=null)temp = temp.parent;

        // value of temp should be -1,-1,-1
        if(temp.size!=-1 || temp.address!=-1 || temp.key!=-1)return false;

        // left should be null
        if(temp.left!=null)return false;

        // empty tree
        if(temp.right==null)return true;
        
        temp=temp.right;
        AVLTree root = temp;
        // check if there is a cycle
        if(!check_cycle(root))return false;

        // Reaching this line ensures there is no cycle in my BSTree
        
        // check if we have a correct Binary tree or not i.e. order is satisfied or not
        Boolean flag = isbst(root);

        if(!flag)return false;

        return check_height(temp);
    }
}


