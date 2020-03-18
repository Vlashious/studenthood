#include "pch.h"
#include "twolinkedlist.h"
#include <iostream>

int main() {
	TwoLinkedList *List = new TwoLinkedList;
	TwoLinkedList *List2 = new TwoLinkedList;
	TwoLinkedList *List3;
	CreateList(List2, 70);
	CreateList(List, 60);
	AddItemInList(List, 0, 20);
	AddItemInList(List, 1, 10);
	AddItemInList(List, 1, 100);
	AddItemInList(List2, 0, 100);
	AddItemInList(List2, 0, 100);
	AddItemInList(List, 2, 70);
	TwoLinkedList *p = List;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	SortList(List);
	p = List;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	//DeleteItemInList(List, 0);
	p = List;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	std::cout << "Enter the index of element you want to search for: ";
	int nIndex;
	std::cin >> nIndex;
	if (SearchList(List, nIndex) == 1) {
		std::cout << "There is an element with " << nIndex << " index!";
	}
	else {
		std::cout << "There is no such an element :(";
	}
	std::cout << std::endl;
	p = List;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}

	p = List2;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	List3 = ListsUnion(List, List2);
	p = List3;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	delete List3;
	List3 = ListsIntersection(List, List2);
	p = List3;
	while (p) {
		std::cout << p->D.nDATA << " ";
		p = p->next;
	}
	std::cout << std::endl;
	return 0;
}