public class checkBSTree{
	public static void print(BSTree d){
        //A1List current=this.getFirst();
        int count = 0; 
        for (d =d.getFirst(); d != null; d = d.getNext()) {
            System.out.println(d.key+" "+d.address);
            count = count + 1;
        }
        System.out.println();
        System.out.println("size: "+count);
    }
	public static void main(String[] args){
		BSTree d=new BSTree();
		/*d=d.Insert(0,100,100);
		print(d);
		BSTree a=d.Find(20,false);
		if(a==null){
			System.out.println("null");
		}
		else{
			System.out.println(a.key);
		}
		d=d.Insert(-1,-1,-1);
		print(d);
		//Dictionary s=new Dictionary 
		BSTree b=d.Find(-1,true);
		if(b==null){
			System.out.println("null");
		}
		else{
			System.out.println(b.key);
		}
		//Dictionary c= new A1List(-1,-1,-1);
		//boolean res=d.Delete(b);
		//d.Delete(c);
		//System.out.println(res);
		//print(d);
		d=d.Insert(0,10,10);
		Dictionary c=new BSTree(-1,-1,-1);
		boolean res1=d.Delete(c);
		System.out.println(res1);
		print(d);*/
		d=d.Insert(10,10,10);
		d=d.Insert(5,10,10);
		d=d.Insert(100,5,5);
		Dictionary dic=new BSTree(100,5,5);
		//Dictionary c=d.Find(10,false);
		//System.out.println(c.key+" "+c.address);
		boolean b=d.Delete(dic);
		System.out.println(b);
		print(d);
		d=d.Insert(15,10,10);
		d=d.Insert(115,12,12);
		d=d.Insert(156,10,10);
		d=d.Insert(12,10,10);
		dic=new BSTree(12,10,10);
		//Dictionary c=d.Find(10,false);
		//System.out.println(c.key+" "+c.address);
		b=d.Delete(dic);
		System.out.println(b);
		print(d);
	}
	/*public static void main(String[] args){
		A1List a= new A1List();
		a.Insert(0,20,20);
		print(a);
		a.Insert(20,80,60);
		print(a);
	}*/
}