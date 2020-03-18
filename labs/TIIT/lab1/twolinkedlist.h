#ifndef twolinkedlist_H
#define twolinkedlist_H

struct Data {
	int nDATA;
};
struct TwoLinkedList {
	Data D;
	TwoLinkedList *prev, *next;
};

void CreateList(TwoLinkedList *List, int new_DATA);

void AddItemInList(TwoLinkedList *List, int PrevItem, int new_DATA);

void DeleteItemInList(TwoLinkedList *List, int ItemToDelete);

void SortList(TwoLinkedList *List);

int SearchList(TwoLinkedList *List, int NumOfElemToSearch);

TwoLinkedList * ListsUnion(TwoLinkedList *List1, TwoLinkedList *List2);

TwoLinkedList * ListsIntersection(TwoLinkedList *List1, TwoLinkedList *List2);

#endif