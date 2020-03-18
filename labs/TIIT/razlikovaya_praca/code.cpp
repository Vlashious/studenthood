#include <vector>
#include <fstream>
#include <string>
#include <iostream>

using std::vector;
using std::fstream;
using std::string;
using std::cout;
using std::cin;

vector<string> files = { "graph1.txt", "graph2.txt", "graph3.txt" , "graph4.txt" , "graph5.txt" };

vector<vector<int>> Get_Graph(int num, int &count) {
	count = 0;
	fstream file(files[num]);
	vector<int> nums, elems;
	vector<vector<int>> graph;
	int t;
	while (!file.eof()) {
		file >> t;
		nums.push_back(t);
		count++;
	}
	t = 0;
	count = sqrt(count);
	for (int i = 0; i < count; i++) {
		for (int k = 0; k < count; k++) {
			elems.push_back(nums[t]);
			t++;
		}
		graph.push_back(elems);
		elems.clear();
	}
	return graph;
}

void Show_Graph(vector<vector<int>> *graph) {
	for (int i = 0; i < (*graph).size(); i++) {
		for (int k = 0; k < (*graph).size(); k++) {
			printf("%5d", (*graph)[i][k]);
		}
		cout << std::endl;
	}
}

int main() {
	vector<vector<int>> graph;
	int num_of_graph, size;
	int minindex;
	int min;
	int temp;
	int start;
	vector<int> min_distances;
	vector<int> visited_nodes;
	for (int l = 0; l < files.size(); l++) {
		cout << "Graph " << l + 1 << ":\n";
		graph = Get_Graph(l, size);
		Show_Graph(&graph);
		min_distances.assign(size, 10000);
		visited_nodes.assign(size, 1);
		cout << "Enter the starting point: ";
		cin >> start;
		start -= 1;
		min_distances[start] = 0;
		do {
			minindex = 10000;
			min = 10000;
			for (int i = 0; i < size; i++) {
				if ((visited_nodes[i] == 1) && (min_distances[i] < min)) {
					min = min_distances[i];
					minindex = i;
				}
			}
			if (minindex != 10000) {
				for (int i = 0; i < size; i++) {
					if (graph[minindex][i] > 0) {
						temp = min + graph[minindex][i];
						if (temp < min_distances[i]) {
							min_distances[i] = temp;
						}
					}
				}
				visited_nodes[minindex] = 0;
			}
		} while (minindex < 10000);
		//cout << std::endl;
		//Path restore!
		vector<int> ver;
		string alphabet = "ABCDEFGHIJKLMNOPRST";
		for (int q = 0; q < size; q++) {
			int end = q; //- 1;
			ver.push_back(end + 1);
			//int k = 1;
			int weight = min_distances[end];
			while (end != start) {
				for (int i = 0; i < size; i++) {
					if (graph[end][i] != 0) {
						int temp = weight - graph[end][i];
						if (temp == min_distances[i]) {
							weight = temp;
							end = i;
							ver.push_back(i + 1);
							//k++;
						}
					}
				}
			}
			for (int i = ver.size() - 1; i > -1; i--) {
				cout << "->" << alphabet[ver[i] - 1];
			}
			cout << std::endl;
			ver.clear();
		}
		graph.clear();
		min_distances.clear();
		visited_nodes.clear();
	}
	return 0;
}