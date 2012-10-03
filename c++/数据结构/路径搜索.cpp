#include <iostream.h>
#include <fstream.h>

const int MAX_N = 100;              // 最多的节点数目
const char* INPUT_FILE = "Graph.txt";


struct Graph {
    int NodeCount;                  // 节点的数目
    int AdjMatrix[MAX_N][MAX_N];    // 邻接矩阵,如果图中i,j相邻则G[i][j]>0，否则G[i][j]=0
};

typedef int Path[MAX_N];            // 用来存储路径
    
typedef bool Mark[MAX_N];          // 标记访问过的节点


void PrintPath(Path& path, int length) {        // 打印路径
    for (int i = 0; i < length; i++) {
        cout << path[i] << " ";
    }
    cout << endl;
}

// 深度优先搜索
// G 输入的图，
// path用来记录路径
// visited 用来标记搜索过的节点，初始化全部为false
// v 当前的节点
// T 目的节点
// length 目前已经得到的路径的长度
void SearchAllPath(const Graph& G, Path& path, Mark& visited, int v, int des, int length) {
    if (visited[v]) return;
    path[length-1] = v;
    if (v == des) {
        PrintPath(path, length);
    } else {
        visited[v] = true;
        for (int i = 0; i < G.NodeCount; i++) 
            if (G.AdjMatrix[v][i] != 0 && !visited[i]) {                
                SearchAllPath(G, path, visited, i, des, length+1);
            }
        visited[v] = false;
    }
}


void ReadData(Graph& G, int& src, int& des)  //读入数据
{
    ifstream fin(INPUT_FILE);
    fin >> G.NodeCount >> src >> des;      // 读入节点数目，起点终点，节点从0开始标号
    for (int i = 0; i < G.NodeCount; i++) 
        for (int j = 0; j < G.NodeCount; j++) {
            fin >> G.AdjMatrix[i][j];
        }
}

int main()
{
    
    Graph G;
    int src, des;          // 起点和终点    
    Path path;              // 存储路径
    Mark visited;          // 访问标记
    ReadData(G, src, des);  //读入数据

    for (int i = 0; i < G.NodeCount; i++) visited[i] = false;  // 初始化
    SearchAllPath(G, path, visited, src, des, 1);  
    
    return 0;
}
