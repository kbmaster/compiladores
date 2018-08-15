import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class AST
{
	protected Object value;
	protected List<AST> children;

	public AST(Double v)
	{
		this.value=v;
		this.children=new ArrayList<AST>();
	}

	public AST(String v)
        {
                this.value=v;
                this.children=new ArrayList<AST>();
        }

	public AST(Boolean v)
        {
                this.value=v;
                this.children=new ArrayList<AST>();
        }
	
	public void add(AST child)
	{
		this.children.add(child);
	}

	public Boolean isLeaf()
	{
		return this.children.size()==0;
	}

	public int getAltura()
	{
		int h=0;

		for(AST node:this.children)
		h=Math.max(h,1+node.getAltura());
		
		return h;		
	}

	public int size()
	{
		if(this.isLeaf()) return 1;
		
		int leafs=0;
		for(AST n : this.children)
		leafs+=n.size();

		return leafs;
		
	}

	

	public  boolean compare(AST tree) throws Exception
	{
		if (tree== null) return false;
		if (!tree.value.equals(this.value))return false;

		for(int i = 0; i<this.children.size(); i++)
		{
			if(!this.children.get(i).compare(tree.children.get(i)))	return false;

		}
			 
		return true;
	}



	public String eval()
	{
		Double l,r;
		Boolean lb,rb;

		if(this.isLeaf()) return this.value.toString(); //leaf
		
		switch(this.value.toString())
		{
 			case("+"): 
			l = new Double ( this.children.get(0).eval());
			r = new Double ( this.children.get(1).eval());
			return String.valueOf(l+r);

			case("-"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l-r);

			case("*"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l*r);

			case("/"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l/r);

			case("%"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l%r);

			case("--"):
                        l = new Double (this.children.get(0).eval());
                        return String.valueOf(-l);

			case("()"):
                        return  this.children.get(0).eval();

			case("=="):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l.compareTo(r)==0);

			case("/="):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l.compareTo(r)!=0);

			case("<"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l<r);


			case(">"):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l>r);

			case("<="):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l<=r);

			case(">="):
                        l = new Double ( this.children.get(0).eval());
                        r = new Double ( this.children.get(1).eval());
                        return String.valueOf(l>=r);

			case("!"):
                        lb = new Boolean ( this.children.get(0).eval());
                        return String.valueOf(!lb);

			case("&&"):
                        lb = new Boolean ( this.children.get(0).eval());
                        rb = new Boolean ( this.children.get(1).eval());
                        return String.valueOf(lb && rb);

			case("||"):
                        lb = new Boolean ( this.children.get(0).eval());
                        rb = new Boolean ( this.children.get(1).eval());
                        return String.valueOf(lb || rb);

		}
              
		return "";
	}
	
	
	public void print() throws Exception
	{
		String tree="digraph BST {\n";
		int [] node={1};
		tree+= this.toGraphvitz(0,node);
		tree+="}";
		
		Path path= Paths.get("/tmp/tree.gvz");
                Files.write(path,tree.getBytes());
				
		//System.out.println(tree);

		String [] cmd =
		{
			"/bin/sh",
			"-c",
			"dot  /tmp/tree.gvz -Tx11"
		};
		
		//echo bla | dot -Tx11
		
		Process process = Runtime.getRuntime().exec(cmd);
		//this.readProcOutput(process);
	}


	public String toGraphvitz(int parent,int[] node) 
	{
		String ret="";
		ret+=node[0]+"[label=\"";
		ret+=(this.value.equals("\""))? "\\"+this.value : this.value; 
		ret+="\"]\n";
 
		if(parent!=0)ret+=parent+"->"+node[0]+"\n";
		
		if(this.isLeaf()) return ret;
		
		int tmparent=node[0];

		for(AST n : this.children)
		{
			node[0]++;
			ret+=n.toGraphvitz(tmparent,node);
		}

		return ret;
	}
	
}


