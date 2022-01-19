package datastructure.graph;

/**
 * 顶点类
 * <p>
 * 参考：http://www.cnblogs.com/ysocean/p/8032659.html
 */
public class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        wasVisited = false;
    }
}
