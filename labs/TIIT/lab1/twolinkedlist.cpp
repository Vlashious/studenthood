#include "twolinkedlist.h"
#include "pch.h"

struct Data {
	int nDATA;
};
struct TwoLinkedList {
	Data D;
	TwoLinkedList *prev, *next;
};

void CreateList(TwoLinkedList *List, int new_DATA) {
	TwoLinkedList *NewItem = new TwoLinkedList;
	NewItem->D.nDATA = new_DATA;
	NewItem->prev = 0;
	NewItem->next = 0;
	*List = *NewItem;
}

void AddItemInList(TwoLinkedList *List, int PrevItem, int new_DATA) {
	TwoLinkedList *Item = 0;
	int nCounter = -1;
	while (List) {
		nCounter++;
		if (nCounter == PrevItem) {
			Item = List;
			break;
		}
		List = List->next;
	}
	TwoLinkedList *NewItem = new TwoLinkedList;
	NewItem->next = Item->next;
	NewItem->prev = Item;
	if (Item->next != 0) Item->next->prev = NewItem;
	Item->next = NewItem;
	NewItem->D.nDATA = new_DATA;
}

void DeleteItemInList(TwoLinkedList *List, int ItemToDelete) {
	TwoLinkedList *temp, *p;
	int nCounter = -1, nCounterAll = -1;
	p = List;
	while (p) {
		nCounterAll++;
		p = p->next;
	}
	p = List;
	while (p) {
		nCounter++;
		if (nCounter == ItemToDelete) {
			if (nCounter == 0) {
				if ((p->next == 0) && (p->prev == 0)) {
					delete p;
					p = 0;
				}
				temp = p->next;
				temp->prev = 0;
				delete p;
				p = temp;
				*p = *temp;
				*List = *p;
				return;
			}
			if (nCounter == nCounterAll) {
				temp = p->prev;
				temp->next = 0;
				delete p;
				return;
			}
			p->prev->next = p->next;
			p->next->prev = p->prev;
			delete p;
			return;
		}
		p = p->next;
	}
}

void SortList(TwoLinkedList *List) {
	TwoLinkedList *p = List;
	int nCounter = -1;
	while (p) {
		nCounter++;
		p = p->next;
	}
	p = List;
	for (int k = 0; k <= nCounter; k++) {
		for (int i = 0; i < nCounter; i++) {
			if (p->next->D.nDATA < p->D.nDATA) {
				int temp;
				temp = p->D.nDATA;
				p->D.nDATA = p->next->D.nDATA;
				p->next->D.nDATA = temp;
				p = p->next;
				continue;
			}
			p = p->next;
		}
		p = List;
	}
}

int SearchList(TwoLinkedList *List, int NumOfElemToSearch) {
	TwoLinkedList *p = List;
	int nCounter = -1;
	while (p) {
		nCounter++;
		if (nCounter == NumOfElemToSearch) return 1;
		p = p->next;
	}
	if (p == 0) return -1;
}

TwoLinkedList * ListsUnion(TwoLinkedList *List1, TwoLinkedList *List2) {
	TwoLinkedList *List = 0;
	TwoLinkedList *p1, *p2;
	int *FirstArray, i = 0, *FinalArray, *ntemp, j = 0;
	int nCounter1 = 0, nCounter2 = 0;
	p1 = List1;
	while (p1) {
		nCounter1++;
		p1 = p1->next;
	}
	p2 = List2;
	while (p2) {
		nCounter2++;
		p2 = p2->next;
	}
	int NumOfElementsTotal = nCounter1 + nCounter2;
	p1 = List1;
	p2 = List2;
	FirstArray = new int[NumOfElementsTotal];
	while (p1) {
		FirstArray[i] = p1->D.nDATA;
		p1 = p1->next;
		i++;
	}
	while (p2) {
		FirstArray[i] = p2->D.nDATA;
		p2 = p2->next;
		i++;
	}
	for (int i = 0; i < NumOfElementsTotal; i++) {
		for (int k = 0; k < NumOfElementsTotal - 1; k++) {
			if (FirstArray[k] > FirstArray[k + 1]) {
				int temp = FirstArray[k];
				FirstArray[k] = FirstArray[k + 1];
				FirstArray[k + 1] = temp;
			}
		}
	}
	int FirstNumOfElements = NumOfElementsTotal - 1;
	for (int i = 0; i < FirstNumOfElements; i++) {
		if (FirstArray[i] == FirstArray[i + 1]) {
			NumOfElementsTotal--;
		}
	}
	ntemp = new int[NumOfElementsTotal];
	ntemp[0] = FirstArray[0];
	j++;
	for (int i = 1; i < NumOfElementsTotal + 1; i++) {
		if (FirstArray[i] == FirstArray[i - 1]) {
			continue;
		}
		ntemp[j] = FirstArray[i];
		j++;
	}
	List = new TwoLinkedList;
	TwoLinkedList *WhatToReturn = List;
	List->prev = 0;
	List->next = new TwoLinkedList;
	List->D.nDATA = ntemp[0];
	for (int i = 1; i < NumOfElementsTotal; i++) {
		TwoLinkedList *p = List->next;
		p->prev = List;
		p->next = new TwoLinkedList;
		p->D.nDATA = ntemp[i];
		List = List->next;
	}
	List->next = 0;
	return WhatToReturn;
}

TwoLinkedList * ListsIntersection(TwoLinkedList *List1, TwoLinkedList *List2) {
	TwoLinkedList *List = 0, *p1, *p2;
	int *FirstArray, *FinalArray;
	int k = 0;
	int nCounter = 0;
	p1 = List1;
	p2 = List2;
	while (p1) {
		while (p2) {
			if (p1->D.nDATA == p2->D.nDATA) {
				if (p2->next != 0) {
					while (p2->D.nDATA == p2->next->D.nDATA) {
						p2 = p2->next;
						if (p2->next == 0) break;
					}
				}
				nCounter++;
			}
			p2 = p2->next;
		}
		p1 = p1->next;
		p2 = List2;
	}
	SortList(List1);
	SortList(List2);
	p1 = List1;
	p2 = List2;
	FirstArray = new int[nCounter];
	while (p1) {
		if (p1->next != 0) {
			if (p1->D.nDATA == p1->next->D.nDATA) {
				while (p1->D.nDATA == p1->next->D.nDATA) {
					p1 = p1->next;
					if (p1->next == 0) break;
				}
			}
		}
		while (p2) {
			if (p2->D.nDATA == p2->next->D.nDATA) {
				while (p2->D.nDATA == p2->next->D.nDATA) {
					p2 = p2->next;
					if (p2->next == 0) break;
				}
			}
			if (p1->D.nDATA == p2->D.nDATA) {
				FirstArray[k] = p1->D.nDATA;
				k++;
			}
			p2 = p2->next;
		}
		p1 = p1->next;
		p2 = List2;
	}
	List = new TwoLinkedList;
	TwoLinkedList *WhatToReturn = List;
	List->prev = 0;
	List->next = new TwoLinkedList;
	k = 0;
	List->D.nDATA = FirstArray[k];
	k++;
	for (int i = 1; i < nCounter; i++) {
		TwoLinkedList *p = List->next;
		p->prev = List;
		if (i != nCounter - 1) {
			p->next = new TwoLinkedList;
		}
		else if (i == nCounter - 1) {
			p->next = 0;
		}
		p->D.nDATA = FirstArray[k];
		k++;
	}
	return WhatToReturn;
}