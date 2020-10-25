#include<iostream>
#define NODE 9

int graph[NODE][NODE] = { {0, 1, 1, 1, 1, 1},
{0, 0, 1, 0, 0, 0},
{0, 1, 0, 1, 0, 0},
{0, 0, 0, 0, 1, 0},
{0, 0, 0, 0, 0, 0},
{0, 0, 0, 0, 1, 0} };

void traverse(int i, bool visited[]) {
	visited[i] = true;
	for (int h = 0; h < NODE; h++) {
		if (graph[i][h]) {
			if (!visited[h])
				traverse(h, visited);
		}
	}
}

bool isYUPPA() {
	bool* vis = new bool[NODE]; 
	for (int k = 0; k < NODE; k++) {
		for (int j = 0; j < NODE; j++)
			vis[j] = false;
		traverse(k, vis);
		for (int j = 0; j < NODE; j++) {
			if (!vis[j])
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