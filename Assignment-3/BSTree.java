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

    // for checksanity
    // class Type{
    //     int key,address;
    // }
    // private void addedge(ArrayList<Type> adj[], Type u, Type v){
    //     adj[u].add(v);
    //     adj[v].add(u);
    // }

    // to compare the order of nodes
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

        // this case is for if the node to be deleted is "this"
        // In this case, we can't really delete "this" node or else we will detach ourselves from BSTree
        // So we will delete the appropriate node by copying values in its parent and then deleting parent
        if(this.parent!=null && this.address==z.address && this.size==z.size && this.key==z.key && (this.left==null || this.right==null)){            
            
            this.address = this.parent.address;
            this.key = this.parent.key;
            this.size = this.parent.size;

            if(this.left==null && this.right==null){
            
                // we have to delete the root element and it is the only element
                if(this.parent.parent==null){                    
                    this.parent.right = null;
                    this.parent = null;
                    return true;
                }
            
                // it is a leaf node and my tree contains more than 1 element
                else{
                    BSTree par = this.parent;
                    BSTree gr = par.parent;
            
                    //this is a right child
                    if(par.right==this){                        
                        BSTree left_child = par.left;
            
                        // par is a right child
                        if(gr.right==par)gr.right = this;
            
                        // par is a left child
                        else gr.left = this;
            
                        this.parent = gr;
                        this.left = left_child;
                        if(left_child!=null)left_child.parent=this;
                        par = null;
                        return true;                                                    
                    }
            
                    // this is a left child
                    else{
                        BSTree right_child = par.right;
            
                        // par is a right child
                        if(gr.right==par)gr.right = this;
            
                        // par is a left child
                        else gr.left = this;
            
                        this.parent = gr;
                        this.right = right_child;
                        if(right_child!=null)right_child.parent = this;
                        par = null;
                        return true;                        
                    }
                }
            }
            
            else if(this.left==null){
                
                if(this.parent.parent==null){
                    this.parent.right = null;
                    this.parent = null;
                    return true;
                }

                else{
                
                    BSTree par = this.parent;
                    BSTree gr = par.parent;
                
                    //this is a right child
                    if(par.right==this){                        
                        BSTree left_child = par.left;
                
                        // par is a right child
                        if(gr.right==par)gr.right = this;
                
                        // par is a left child
                        else gr.left = this;
                
                        this.parent = gr;
                        this.left = left_child;
                        if(left_child!=null)left_child.parent=this;
                        par = null;
                        return true;                                                    
                    }
                
                    // this is a left child
                    else{
                        BSTree right_child = par.right;
                
                        // par is a right child
                        if(gr.right==par)gr.right = this;
                
                        // par is a left child
                        else gr.left = this;
                
                        this.parent = gr;
                        this.left = this.right;                        
                        this.right = right_child;
                        if(right_child!=null)right_child.parent = this;
                        par = null;
                        return true;                        
                    }
                }
            }
            
            else{

                if(this.parent.parent==null){
                    this.parent.right = null;
                    this.parent = null;
                    this.right = this.left;
                    this.left = null;
                    return true;
                }
                
                else{
                    BSTree par = this.parent;
                    BSTree gr = par.parent;
                
                    //this is a right child
                    if(par.right==this){                        
                        BSTree left_child = par.left;
                        // par is a right child
                        if(gr.right==par)gr.right = this;
                        // par is a left child
                        else gr.left = this;
                        this.parent = gr;
                        this.right = this.left;
                        this.left = left_child;
                        if(left_child!=null)left_child.parent=this;
                        par = null;
                        return true;                                                    
                    }
                
                    // this is a left child
                    else{
                        BSTree right_child = par.right;
                        // par is a right child
                        if(gr.right==par)gr.right = this;
                        // par is a left child
                        else gr.left = this;
                        this.parent = gr;                       
                        this.right = right_child;
                        if(right_child!=null)right_child.parent = this;
                        par = null;
                        return true;                        
                    }
                }
            }
        }
        
        // Reaching here means we don't really have to delete "this"
        BSTree e = new BSTree(z.address,z.size,z.key);

        while(temp!=null && (temp.key!=e.key || temp.address!=e.address || temp.size!=e.size)){
            if(compare(e, temp)){
                temp = temp.right;
            }
            else temp = temp.left;
        }

        if(temp==null)return false;

        // leaf is to be deleted
        if(temp.left==null && temp.right==null){
            if(temp.parent.left==temp)temp.parent.left = null;
            if(temp.parent.right==temp)temp.parent.right = null;            
            // temp.parent = null;
            temp = null;
            return true;
        }

        // a node with only one child
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

        // Reaching this line means the node to be deleted contains both left and right child
        // So we will copy the values of successor in the current node and try to delete its successor
        BSTree right_child = temp.right;
        BSTree succ = right_child;
        while(succ.left!=null)succ = succ.left;
        temp.key = succ.key;
        temp.address = succ.address;
        temp.size = succ.size;

        // Another which might occur is that successor to be deleted is "this" node

        // if the successor to be deleted is "this"
        if(succ.address==this.address && succ.size==this.size && succ.key==this.key && succ.left==this.left && succ.right==this.right &&  succ.parent==this.parent){
            // temp is the parent
            // gr is for grandfather

            // succ is the right child
            if(temp.right==succ){
                BSTree gr = temp.parent;
                if(gr.right==temp)gr.right = succ;
                else gr.left = succ;
                succ.parent = gr;
                succ.left = temp.left;
                if(temp.left!=null)temp.left.parent=succ;
                temp = null;
                return true;                
            }
            // succ is the left child
            else{
                BSTree par = succ.parent;
                BSTree gr = par.parent;
                succ.address = par.address;
                succ.key = par.key;
                succ.size = par.size;
                if(gr.right==par)gr.right = succ;
                else gr.left = succ;
                succ.parent = gr;
                succ.left = succ.right;
                succ.right = par.right;
                if(par.right!=null)par.right.parent = succ;
                par = null;
                return true;
            }
        }

        // Reaching here means we can safely delete successor as successor is not "this"
        //succ don't have left child
        
        // No right child
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
        // Exact == false means we need to find smallest node such that node.key >= key
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

    // getFirst will return the minimum element of whole tree i.e. 1st element of Inorder Traversal
    public BSTree getFirst()
    { 
        if(this==null)return null;
        BSTree temp = this;
        while(temp.parent!=null)temp = temp.parent;
        if(temp.right==null)return null;
        temp = temp.right;
        while(temp.left!=null)temp=temp.left;
        return temp;
    }

    // Will return the next element in inorder traversal
    public BSTree getNext()
    { 
        if(this==null)return null;
        
        // if getnext called on sentinel then return null
        if(this.parent==null)return null;

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

    // Function to check correct order of Binary Search Tree (isbst will initiate the process by calling check_bst)
    private boolean isbst(BSTree root){
        return check_bst(root, Long.MIN_VALUE, Long.MAX_VALUE, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Function to check correct order of BSTree
    // Will check if the current node meets the conditions and then will tighten the bound and check in its left and right subtree
    private boolean check_bst(BSTree root,long min_key, long max_key, long min_add, long max_add){
        if(root==null)return true;  
        if(root.key<min_key || (root.key==min_key && root.address<min_add))return false;
        if(root.key>max_key || (root.key==max_key && root.address>max_add))return false;

        // left subtree can contain same pairs if it exist, but right subtree can't
        // This is the reason of passing root.address + 1 in 2nd check_bst function
        return (check_bst(root.left,(long) min_key,(long)root.key,(long) min_add,(long) root.address) && check_bst(root.right,(long) root.key,(long) max_key,(long) root.address+1,(long) max_add));
    }

    // Function to check if there is a cycle in my tree or not
    private boolean check_cycle(BSTree root){
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


    public boolean sanity()
    { 
        if(this==null)return false;
        BSTree temp = this;
        // will try to go to root and also check for a cycle if any in the path of this to root
        if(this.parent!=null){
            BSTree slow = temp;
            BSTree fast = temp.parent;
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
        BSTree root = temp;
        // check if there is a cycle
        if(!check_cycle(root))return false;

        // Reaching this line ensures there is no cycle in my BSTree
        
        // check if we have a correct Binary tree or not i.e. order is satisfied or not
        Boolean flag = isbst(root);

        if(flag)return true;

        return false;
    }    
    
}