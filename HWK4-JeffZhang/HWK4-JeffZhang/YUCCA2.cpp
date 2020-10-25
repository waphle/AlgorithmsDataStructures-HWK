#include<iostream>
#define NODE 9

int graph[NODE][NODE] = { {0, 1, 1, 0},
						  {1, 0, 1, 1},
						  {0, 0, 0, 0},
						  {1, 0, 1, 0} };

void traverse(int u, bool visited[]) {
	visited[u] = true; //mark v as visited
	for (int v = 0; v < NODE; v++) {
		if (graph[u][v]) {
			if (!visited[v])
				traverse(v, visited);
		}
	}
}

bool isYUPPA() {
	bool* vis = new bool[NODE]; //for all vertex u as start point, check whether all nodes are visible or not
	for (int u = 0; u < NODE; u++) {
		for (int i = 0; i < NODE; i++)
			vis[i] = false;   //initialize as no node is visited
		traverse(u, vis);
		for (int i = 0; i < NODE; i++) {
			if (!vis[i]) //if there is a node, not visited by traversal, graph is not connected
				return false;
		}
	}
	return true;
}
int main() {
	if (isYUPPA())
		printf("The Graph is YUCCA.");
	else
		printf("The Graph is NOT YUCCA.");
}