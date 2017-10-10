package 实验一;

import java.io.IOException;

public class GraphVizTest {
    public  void showDirectedGraph(Graph graph) {
        GraphViz gViz=new GraphViz("C:\\Users\\lenovo\\Desktop\\eee","D:\\Java可视化\\bin\\dot.exe" );
        gViz.start_graph();
        int i1=1,i2=1;
        while(graph.list[i1]!=null)
        {
        	gViz.addln(graph.list[i1].data+";");
        	i2=graph.list[i1].sonnumber;
        	Point son=graph.list[i1].first;
        	for(int i=1;i<=i2;i++){
        		gViz.addln(graph.list[i1].data+"->"+graph.list[son.adj].data+"[label=\""+son.cost+"\"]"+";");
        		son=son.next;
        	}
        	i1++;
        }
//        gViz.addln("A;");
//        gViz.addln("B;");
//        gViz.addln("C;");
//        gViz.addln("D;");
//        gViz.addln("E;");
//        gViz.addln("A->B[label=\"100 times\"];");
//        gViz.addln("A->C;");
//        gViz.addln("C->B;");
//        gViz.addln("B->D;");
//        gViz.addln("C->E;");
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public  void showDirectedGraph(Graph graph,String[] ss) {
        GraphViz gViz=new GraphViz("C:\\Users\\lenovo\\Desktop\\eee2","D:\\Java可视化\\bin\\dot.exe" );
        gViz.start_graph();
        int i1=1,i2=1;
        while(graph.list[i1]!=null)
        {
        	gViz.addln(graph.list[i1].data+";");
        	i2=graph.list[i1].sonnumber;
        	Point son=graph.list[i1].first;
        	for(int i=1;i<=i2;i++){
        		gViz.addln(graph.list[i1].data+"->"+graph.list[son.adj].data+"[label=\""+son.cost+"\"]"+";");
        		son=son.next;
        	}
        	i1++;
        }
        int count=1;
        while(ss[count]!=null)
        {
			gViz.addln(ss[count-1]+"->"+ss[count]+"[color=\"red\", style=\"dashed\"]"+";");
			
			count++;
        }
        
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
