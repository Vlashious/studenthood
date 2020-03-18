#include <iostream>
#include "Graph.h"

int main() {

	Graph<int> graph1(4), graph2(5);

	graph2.empty();

	graph1 == graph2;
	graph1 != graph2;
	graph1 > graph2;
	graph1 >= graph2;
	graph1 = graph2;

	std::cout << graph1;

	return 0;
}