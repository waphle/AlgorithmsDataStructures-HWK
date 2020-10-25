#include<iostream>
using namespace std;
const int MAX = 1e6 + 5;
vector<int> Adj[MAX]; // adjacency list
bool visited[MAX];

int main()
{
	int n;
	cout << "Enter the number of nodes\n";
	cin >> n;
	int m;
	cout << "Enter the number of edges\n";
	cin >> m;
	int v, u;

	// initailing visited array with false
	memset(visited, false, sizeof(visited));

	cout << "Please enter the edges v u such that edge is going from v to u\n";
	for (int i = 0; i < m; i++)
	{
		cin >> v >> u;
		Adj[v].push_back(u);
	}
	for (int i = 1; i <= n; i++) //iterate over all nodes
	{
		// mark all neighbour of i node visited
		for (int j = 0; j < Adj[i].size(); j++)
		{
			visited[Adj[i][j]] = true;
		}
	}

	for (int i = 1; i <= n; i++)
	{
		if (visited[i] == false) // means indegre is zero
		{
			if (Adj[i].size() == n - 1)
			{
				cout << "Graph is YUCCA\n";
				return 0;
			}
		}
	}
	cout << "Graph is NOT YUCCA\n";
	return 0;
}