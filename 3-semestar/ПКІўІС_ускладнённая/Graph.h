#pragma once
#include<vector>

template<class infoType>
class Graph {

public:

	Graph(int vertices = 0) {
		std::vector<infoType> row(vertices, 0);
		for (int i = 0; i < vertices; i++) {
			graph.push_back(row);
		}
	}

	Graph(const Graph& gr) {
		graph = gr.graph;
	}

	~Graph() {
		graph.clear();
		graph.erase(graph.begin(), graph.end());
	}

	bool empty() {
		if (graph.empty()) return true;
		else return false;
	}

	void clear() {
		graph.clear();
	}

	Graph& operator = (const Graph& gr) {

		if (this == &gr) {
			return *this;
		}

		graph = gr.graph;
		return *this;
	}

	bool operator == (const Graph& other) {
		if (graph == other.graph) return true;
		else return false;
	}

	bool operator != (const Graph& other) {
		if (graph != other.graph) return true;
		else return false;
	}

	bool operator > (const Graph& other) {
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < graph.size(); i++) {
			for (int k = 0; k < graph.size(); k++) {
				if (graph[i][k] == 1) s1++;
			}
		}
		for (int i = 0; i < other.graph.size(); i++) {
			for (int k = 0; k < other.graph.size(); k++) {
				if (other.graph[i][k] == 1) s1++;
			}
		}
		if (s1 > s2) return true;
		else return false;
	}

	bool operator < (const Graph& other) {
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < graph.size(); i++) {
			for (int k = 0; k < graph.size(); k++) {
				if (graph[i][k] == 1) s1++;
			}
		}
		for (int i = 0; i < other.graph.size(); i++) {
			for (int k = 0; k < other.graph.size(); k++) {
				if (other.graph[i][k] == 1) s1++;
			}
		}
		if (s1 < s2) return true;
		else return false;
	}

	bool operator >= (const Graph& other) {
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < graph.size(); i++) {
			for (int k = 0; k < graph.size(); k++) {
				if (graph[i][k] == 1) s1++;
			}
		}
		for (int i = 0; i < other.graph.size(); i++) {
			for (int k = 0; k < other.graph.size(); k++) {
				if (other.graph[i][k] == 1) s1++;
			}
		}
		if (s1 >= s2) return true;
		else return false;
	}

	bool operator <= (const Graph& other) {
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < graph.size(); i++) {
			for (int k = 0; k < graph.size(); k++) {
				if (graph[i][k] == 1) s1++;
			}
		}
		for (int i = 0; i < other.graph.size(); i++) {
			for (int k = 0; k < other.graph.size(); k++) {
				if (other.graph[i][k] == 1) s1++;
			}
		}
		if (s1 <= s2) return true;
		else return false;
	}

	friend std::ostream& operator << (std::ostream& out, const Graph& gr) {
		
		return out;
	}

	std::vector<std::vector<infoType>> getGraph() {
		return graph;
	}

private:

	std::vector<std::vector<infoType>> graph;

};