package 实验一;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Stack;

class Point {

	int adj,cost,biaoji;
	Point next;
}
class Bigpoint{
	String data;
	Point first;
	int sonnumber;
}
class Graph{
	Bigpoint []list=new Bigpoint[50];
	int node ,edg=-1;
}
class lianbiao{
	String ad;
	lianbiao next;
}

public class main1 {
	private static String[] we=new String[100];
	static Graph graph=new Graph();
	public static void main(String[] args) {
		String file,bridge;
		
		
		Scanner in=new Scanner(System.in);
		file=in.nextLine();
		//生成有向图
		createDirectedGraph(file);
		
		int i=1;
		//System.out.println(graph.node);
		//System.out.println(graph.edg);
//		while(graph.list[i]!=null)
//		{
//			System.out.println(graph.list[i].data);
//			i++;
//		}
		//随机游走
		randomWalk( graph);
		
		in.close();
		GraphVizTest gt=new GraphVizTest();
		gt.showDirectedGraph(graph);
		//桥接词两个
		bridge=queryBridgeWords(graph,"to","out");
		String[] wenben=new String[100];
		wenben=readfile1("C:\\Users\\lenovo\\Desktop\\读取文件2.txt",wenben);
		generateNewText(graph,wenben);
		
		//最短路径
		String[]ss=CalcShortestPath( graph,"new","strange");
		
		
		String[]s=CalcShortestPath( graph,"new");
		
		gt.showDirectedGraph(graph, ss);
	}
	
	public static void randomWalk(Graph graph)
	{
		int biao,shifoujixu=1,son;
		Bigpoint b;Point p;
		biao=(int)(1+Math.random()*(graph.node-1+1));
		
		b=graph.list[biao];
		p=b.first;
		System.out.println(b.data);
		
		
		Scanner in1=new Scanner(System.in);
		System.out.print("是否继续？");
		shifoujixu=in1.nextInt();
		System.out.println();
		while(shifoujixu!=0&&b.first!=null){
			
			son=(int)(1+Math.random()*(b.sonnumber-1+1));
			for(int i=1;i<son;i++){
				p=p.next;
			}
			if(p.biaoji==0){
				
				p.biaoji=1;
				biao=p.adj;b=graph.list[biao];p=b.first;System.out.println(b.data);
			}
			else{
				biao=p.adj;b=graph.list[biao];p=b.first;System.out.println(b.data);
				break;
			}
			System.out.print("是否继续？");
			shifoujixu=in1.nextInt();
			System.out.println();
		}
		System.out.println("已结束");
		in1.close();
		
	}
	public static int isinlist(String thisword,Graph graph)
	{
		int i=1;
		while(i<=graph.node&&!graph.list[i].data.equalsIgnoreCase(thisword)){
			i++;
		}
		
		if(i<=graph.node)return i;
		else return 0;
	}
	
	public static void createDirectedGraph(String file){
		try{
			           
			   FileReader fr = new FileReader(file);
	            
	           int ch = 0;int biao=1;int prebiao=0;int nbiao=1;
	           char word;
	           String thisword="";
	           graph.list[0]=new Bigpoint();
	            while((ch=fr.read())!=-1){//fr.read()读取一个字节，判断此字节的值为-1表示读到文件末尾了。
	            	word=(char)ch;
	                
	                if((('a'<=word) &&(word<='z') )||(('A'<=word) && (word<='Z'))){
	                	//System.out.println(word);
	                	thisword+=word;
	                }
	                else{
	                	if(thisword!=""){
	                		thisword=thisword.toLowerCase();
	                		if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ies"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-3)+"y";
	                		}
	                		else if(thisword.length()>3&&thisword.substring(thisword.length()-2,thisword.length()).equalsIgnoreCase("es"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-2);
	                		}
	                		else if(thisword.length()>3&&thisword.substring(thisword.length()-1,thisword.length()).equalsIgnoreCase("s"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-1);
	                		}
	                		else if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ied"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-3)+"y";
	                		}
	                		else if(thisword.length()>3&&thisword.substring(thisword.length()-2,thisword.length()).equalsIgnoreCase("ed"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-2);
	                		}
	                		else if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ing"))
	                		{
	                			thisword=thisword.substring(0,thisword.length()-3);
	                		}
			                		if(isinlist(thisword,graph)==0){
			                			graph.node++;
			                			//System.out.println(graph.node);
			                			 nbiao=biao;
						                	graph.list[nbiao]=new Bigpoint();
						                	graph.list[nbiao].data=thisword;
						                	Point p=new Point();
						                	p.adj=nbiao;p.cost++;graph.edg++;
						                	
						                	
						                	if(graph.list[prebiao].first!=null){
						                		Point sons=graph.list[prebiao].first;
						                		while(sons.next!=null){sons=sons.next;}
						                		sons.next=p;graph.list[prebiao].sonnumber++;
						                	}
						                	else{
						                		graph.list[prebiao].first=p;
						                		graph.list[prebiao].sonnumber++;
						                	}
						                	thisword="";
						                	prebiao=nbiao;
						                	biao++;
			                		}
			                		else
			                		{
				                			nbiao=isinlist(thisword,graph);
				                			if(graph.list[prebiao].first==null){
				                				Point p=new Point();
					                			p.adj=nbiao;p.cost++;graph.edg++;
						                		graph.list[prebiao].first=p;
						                		graph.list[prebiao].sonnumber++;
						                	}
						                	else{
						                		Point sons=graph.list[prebiao].first;
						                		Point presons=graph.list[prebiao].first;
						                		do{
						                			if(sons.adj==nbiao){Point p=sons;p.cost++;graph.edg++;break;}
						                			else{presons=sons;sons=sons.next;}
						                			}while(sons!=null);
						                		if(sons==null){
						                			Point p=new Point();
						                			p.adj=nbiao;p.cost++;graph.edg++;
						                			presons.next=p;graph.list[prebiao].sonnumber++;
						                		}
						                	}
				                			thisword="";
				                			prebiao=nbiao;
				                			}
			                		}
	                	else{continue;}
	                }
	           }
	            fr.close();
	            thisword=thisword.toLowerCase();
	            if(thisword!=""){
	            	if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ies"))
            		{
            			thisword=thisword.substring(0,thisword.length()-3)+"y";
            		}
            		else if(thisword.length()>3&&thisword.substring(thisword.length()-2,thisword.length()).equalsIgnoreCase("es"))
            		{
            			thisword=thisword.substring(0,thisword.length()-2);
            		}
            		else if(thisword.length()>3&&thisword.substring(thisword.length()-1,thisword.length()).equalsIgnoreCase("s"))
            		{
            			thisword=thisword.substring(0,thisword.length()-1);
            		}
            		else if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ied"))
            		{
            			thisword=thisword.substring(0,thisword.length()-3)+"y";
            		}
            		else if(thisword.length()>3&&thisword.substring(thisword.length()-2,thisword.length()).equalsIgnoreCase("ed"))
            		{
            			thisword=thisword.substring(0,thisword.length()-2);
            		}
            		else if(thisword.length()>3&&thisword.substring(thisword.length()-3,thisword.length()).equalsIgnoreCase("ing"))
            		{
            			thisword=thisword.substring(0,thisword.length()-3);
            		}
	            	
            		if(isinlist(thisword,graph)==0){
            			graph.node++;
            			//System.out.println(graph.node);
            			 nbiao=biao;
		                	graph.list[nbiao]=new Bigpoint();
		                	graph.list[nbiao].data=thisword;
		                	Point p=new Point();
		                	p.adj=nbiao;p.cost++;graph.edg++;
		                	
		                	
		                	if(graph.list[prebiao].first!=null){
		                		Point sons=graph.list[prebiao].first;
		                		while(sons.next!=null){sons=sons.next;}
		                		sons.next=p;graph.list[prebiao].sonnumber++;
		                	}
		                	else{
		                		graph.list[prebiao].first=p;
		                		graph.list[prebiao].sonnumber++;
		                	}
		                	thisword="";
		                	prebiao=nbiao;
		                	biao++;
            		}
            		else
            		{
                			nbiao=isinlist(thisword,graph);
                			if(graph.list[prebiao].first==null){
                				Point p=new Point();
	                			p.adj=nbiao;p.cost++;graph.edg++;
		                		graph.list[prebiao].first=p;
		                		graph.list[prebiao].sonnumber++;
		                	}
		                	else{
		                		Point sons=graph.list[prebiao].first;
		                		Point presons=graph.list[prebiao].first;
		                		do{
		                			if(sons.adj==nbiao){Point p=sons;p.cost++;graph.edg++;break;}
		                			else{presons=sons;sons=sons.next;}
		                			}while(sons!=null);
		                		if(sons==null){
		                			Point p=new Point();
		                			p.adj=nbiao;p.cost++;graph.edg++;
		                			presons.next=p;graph.list[prebiao].sonnumber++;
		                		}
		                	}
                			thisword="";
                			prebiao=nbiao;
                			}
            		}
	        }catch(IOException e){
	           e.printStackTrace();
	       }

	}
	

	public static String[] readfile1(String file,String wenjian[])
		{
			try{
				FileReader ff=new FileReader(file);
				
				int ch,i=0;
				char word;
				String thisword="";
				while((ch=ff.read())!=-1){//fr.read()读取一个字节，判断此字节的值为-1表示读到文件末尾了。
	            	word=(char)ch;
	            	
	            	 if((('a'<=word) &&(word<='z') )||(('A'<=word) && (word<='Z'))){
		                	//System.out.println(word);
		                	thisword+=word;
	            	 }
	            	 else{
	            		 if(thisword!="")
	            		 {            			
							wenjian[i]=thisword;i++;thisword="";
	            		 }
	            		 else{continue;}
	            	 }
					}
				if(thisword!="")
       		 {            			
					wenjian[i]=thisword;i++;thisword="";
       		 }
				
				ff.close();
				}catch(IOException e){
		           e.printStackTrace();
			}
		
			
			return wenjian;
		}
	static String queryBridgeWords(Graph graph,String word1,String word2)
		{
			String bridge = null;int i=1,k=0,j,jihao=0,a=0;
			for(j=1;j<=graph.node;j++)
			{
				if(word2.equalsIgnoreCase(graph.list[j].data))
				{
					k=1;
					break;
				}
			}
			for(j=1;j<=graph.node;j++)
			{
				if(word1.equalsIgnoreCase(graph.list[j].data))
				{
					
					jihao=1;break;
				}
			}
			if(k!=1&&jihao!=1)
			{
				System.out.println("No "+ word1+ " and " +word2+" in the graph!");
				return word2;
			}
			else if(k!=1&&jihao==1)
			{
				System.out.println("No "+ word2+" in the graph!"); return word2;
			}
			else if(k==1&&jihao!=1)
			{
				System.out.println("No "+ word1+" in the graph!"); return word2;
			}
			while(i<=graph.node&&k==1&&jihao==1)
			{
				if (word1.equalsIgnoreCase(graph.list[i].data))
				{
					
					Point sons=graph.list[i].first;
					Point child=graph.list[i].first;
					while(child!=null)
					{
						if(word2.equalsIgnoreCase(graph.list[child.adj].data)){System.out.println("No bridge words from "+word1+ " to "+word2+"!");return word2;}
						child=child.next;
					}
					while(sons!=null){
	        			
	        				Point person=graph.list[sons.adj].first;
	        				while(person!=null)
	        				{
		        				if(word2.equalsIgnoreCase(graph.list[person.adj].data))
		        				{
		        					bridge=graph.list[sons.adj].data;
		        					
		        					//System.out.println("The bridge from "+word1 +" to "+ word2 +" is :"+bridge);
									we[a]=bridge;
									a++;
		        					person=person.next;
		        					break;
		        				}
		        				else 
		        				{
		        					person=person.next;
		        				}
	        				}
	        		
	        			sons=sons.next;
	        		}
					if(bridge==null){System.out.println("No bridge words from "+word1+ " to "+ word2+"!");return word2;}
					else {					
						if(a!=1){										
							System.out.print("The bridge from "+word1 +" to "+ word2 +" are :"+we[0] );
						for(i=1;i<a-1;i++)
						{
							System.out.print(","+we[i]);
						}
						System.out.println(" and "+we[i]+".");
					}
						else{
							System.out.println("The bridge from "+word1 +" to "+ word2 +" is :"+we[0] +".");
						}
					}	
					return bridge;
					}
				else{i++;}
			}
			return bridge;
		}
	static void Insert(lianbiao phead,String data1,String data2)
		 {
			 lianbiao node=phead;	
			 lianbiao swap;
			 while(node.ad 

	!=data1)
			 {
				 
				 node=node.next;
			 }
			 lianbiao pnew=new lianbiao();
			 pnew.ad 

	=data2;
			 swap=node.next;
			 node.next=pnew;
			 pnew.next=swap;
		 }
	static String generateNewText(Graph graph,String inputText[])
		 {
			 
			 int i=0,a=0;
			 lianbiao head=new lianbiao();
			 lianbiao tail=head;
			 tail.next=null;
			 while(inputText[i]!=null)
			 {
				lianbiao p=new lianbiao();
				p.ad=inputText[i];
				tail.next=p;
				p.next=null;
				tail=p;i++;
			 }
			
			 while(inputText[a+1]!=null)
			 {
				 String xin=queryBridgeWords(graph,inputText[a],inputText[a+1]);
				 
					 if(xin!=inputText[a+1])
					 {
						 Insert(head,inputText[a],xin);
					 }
					 a++;
			 }
			 
			 head=head.next;
			 while(head!=null)
			 {
				 System.out.print(head.ad+" ");
				 head=head.next;
			 }
			 System.out.println();
			return null;
			
				 
		 }

	
	static String[] CalcShortestPath(Graph graph,String word1,String word2)
	{
		int m,n;
		int[][] jz=new int [graph.node+1][graph.node+1];int[][] p=new int [graph.node+1][graph.node+1];
		int[][] di=new int [graph.node+1][graph.node+1];int i,j,k;
		for(i=1;i<=graph.node;i++)
		{
			for(j=1;j<=graph.node;j++)
			{
				if(j!=i)
				{jz[i][j]=100;}
				else{
					jz[i][j]=100;
				}
			}
		}
		int a=1;		
		while(!word1.equalsIgnoreCase(graph.list[a].data))
		{
			if(a==graph.node)
			{
				System.out.println("无路径");
				return null;
			}
			a++;
		}	
		int b=1;
		while(!word2.equalsIgnoreCase(graph.list[b].data))
		{
			if(b==graph.node)
			{
				System.out.println("无路径");
				return null;
			}
			b++;
		}
		for(i=1;i<=graph.node;i++)
		{
			Point sons=graph.list[i].first;
			while(sons!=null)
			{
				jz[i][sons.adj]=sons.cost;
				sons=sons.next;
			}
		}
		for(i=1;i<=graph.node;i++)
		{
			for(j=1;j<=graph.node;j++)
			{	
					p[i][j]=j;
			}
		}

		for(k=1;k<=graph.node;k++)
		{
			for(i=1;i<=graph.node;i++)
			{
				for(j=1;j<=graph.node;j++)
				{
					if(jz[i][k]+jz[k][j]<jz[i][j])
					{					
						jz[i][j]=jz[i][k]+jz[k][j];
						p[i][j]=p[i][k];
					}
				}
			}
		}	
	
		if(jz[a][b]==100){
			System.out.println("无路径 ");
			return null;
		}
		else{
			String[] ss=new String[100];int count=0;
			System.out.print(word1+" -> ");ss[count]=word1;count++;
			i=p[a][b];
			while(i!=b)
			{
				System.out.print(graph.list[i].data+" -> ");
				ss[count]=graph.list[i].data;count++;
				i=p[i][b];
				
			}
			System.out.println(word2);ss[count]=graph.list[i].data;
			System.out.println("长度为 "+jz[a][b]);
				return ss;
		}
	}

	static String[] CalcShortestPath(Graph graph,String word1)
	{
		String[] ss=new String[100];
		int a=1;		
		while(!word1.equalsIgnoreCase(graph.list[a].data))
		{
			if(a==graph.node)
			{
				System.out.println("无路径");
				return null;
			}
			a++;
		}	
		for(int i=1;i<=graph.node;i++)
		{
			if(i!=a)
				{CalcShortestPath(graph,graph.list[a].data,graph.list[i].data);
		}
	}return ss;
	}
}

