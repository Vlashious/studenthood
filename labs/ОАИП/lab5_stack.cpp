#include "pch.h"
#include <iostream>
#include <time.h>

struct Stack {
	int nDATA;
	Stack *next;
} *begin, *odd, *even;

/*void Create(Stack *begin) {
	Stack *temp = begin;
	srand(time(NULL));
	int nNum;
	std::cout << "Enter number of elements in stack you want to create:";
	std::cin >> nNum;
	std::cout << std::endl;
	temp->nDATA = rand() % 21;
	temp->next = 0;
	for (int i = 1; i < nNum; i++) {
		Stack *t = new Stack;
		t->nDATA = rand() % 21;
		t->next = temp;
		temp = t;
	}
	*begin = *temp;
	return;
}
*/

Stack* Add(Stack *begin) {
	Stack *t = new Stack;
	t->nDATA = rand() % 21;
	t->next = begin;
	return t;
}

void View(Stack *begin) {
	Stack *t = begin;
	while (t) {
		std::cout << t->nDATA << "\n";
		t = t->next;
	}
}

/*void Add(Stack *begin) {
	Stack *t = begin;
	int nValue;
	while (t) {
		if (t->next == 0) break;
		t = t->next;
	}
	std::cout << "Value of new element: ";
	std::cin >> nValue;
	std::cout << std::endl;
	t->next = new Stack;
	t = t->next;
	t->nDATA = nValue;
	t->next = 0;
}
*/

void Delete(Stack **begin) {
	Stack *t = *begin;
	while (t) {
		*begin = (*begin)->next;
		delete t;
		t = *begin;
	}
}

void Individual(Stack *begin, Stack *odd, Stack *even) {
	int nPreventer1 = 0, nPreventer2 = 0;
	Stack *t = begin;
	while (t) {
		if (t->nDATA % 2 == 0) {
			even->nDATA = t->nDATA;
			nPreventer2 = 1;
			if (t->next != 0 && t->next->nDATA % 2 == 0) {
				even->next = new Stack;
				even = even->next;
			}
			else {
				even->next = 0;
				if (t->next != 0 && nPreventer1 == 1) {
					odd->next = new Stack;
					odd = odd->next;
				}
			}
		}
		else {
			odd->nDATA = t->nDATA;
			nPreventer1 = 1;
			if (t->next != 0 && t->next->nDATA % 2 != 0) {
				odd->next = new Stack;
				odd = odd->next;
			}
			else {
				odd->next = 0;
				if (t->next != 0 && nPreventer2 == 1) {
					even->next = new Stack;
					even = even->next;
				}
			}
		}
		t = t->next;
	}
}

void P_SORT(Stack **p) {
	Stack *t = 0, *t1, *r;
	*p = Add(*p);
	if ((*p)->next->next == NULL) return;
	do {
		for (t1 = *p; t1->next->next != t; t1 = t1->next) {
			if (t1->next->nDATA > t1->next->next->nDATA) {
				r = t1->next->next;
				t1->next->next = r->next;
				r->next = t1->next;
				t1->next = r;
			}
		}
		t = t1->next;
	} while ((*p)->next->next != t);
	Stack *temp = *p;
	*p = (*p)->next;
	delete temp;
}

void V_SORT(Stack **p) {
	Stack *t = NULL, *t1;
	int r;
	do {
		for (t1 = *p; t1->next != t; t1 = t1->next) {
			if (t1->nDATA > t1->next->nDATA) {
				r = t1->nDATA;
				t1->nDATA = t1->next->nDATA;
				t1->next->nDATA = r;
			}
		}
		t = t1;
	} while ((*p)->next != t);
}

int main() {
	int nSwitch, kek;
	while (1) {
		std::cout << "\n----------\nCreate - 1\nView - 2\nAdd - 3\nDelete - 4\nIndividual Task - 5\nSort - 6\nExit - 0\n----------\n";
		std::cin >> nSwitch;
		switch (nSwitch) {
		case 0:
			return 0;
		case 1:
			if (begin != 0) {
				std::cout << "\nDelete previous stack first!\n";
				break;
			}
			begin = new Stack;
			begin->next = 0;
			begin->nDATA = rand() % 21;
			std::cout << "Enter num of elements: ";
			std::cin >> kek;
			for (int i = 1; i < kek; i++) {
				begin = Add(begin);
			}
			break;
		case 2: {
			int nSwitch;
			std::cout << "\n----------\nAll elements - 1\nEven elements - 2\nOdd elements - 3\n----------\n";
			std::cin >> nSwitch;
			std::cout << std::endl;
			switch (nSwitch) {
			case 1:
				if (begin == 0) {
					std::cout << "\nCreate stack first!\n";
					break;
				}
				View(begin);
				break;
			case 2:
				if (even == 0) {
					std::cout << "\nCreate stack first!\n";
					break;
				}
				View(even);
				break;
			case 3:
				if (odd == 0) {
					std::cout << "\nCreate stack first!\n";
					break;
				}
				View(odd);
				break;
			default:
				std::cout << "\nWrong input!\n";
				break;
			}
			break;
		}
		case 3:
			if (begin == 0) {
				std::cout << "\nCreate stack first!\n";
				break;
			}
			begin = Add(begin);
			break;
		case 4:
			if (begin == 0) {
				std::cout << "\nCreate stack first!\n";
				break;
			}
			Delete(&begin);
			if (even != 0 && odd != 0) {
				Delete(&odd);
				Delete(&even);
			}
			break;
		case 5:
			if (even != 0 && odd != 0) {
				std::cout << "\nDelete existing stacks\n";
				break;
			}
			if (begin == 0) {
				std::cout << "\nCreate stack first!\n";
				break;
			}
			odd = new Stack;
			even = new Stack;
			Individual(begin, odd, even);
			break;
		case 6: {
			int nSwitch;
			std::cout << "\n----------\nPointer sort - 1\nValue exchange sort - 2\n----------\n";
			std::cin >> nSwitch;
			switch (nSwitch) {
			case 1:
				if (begin != 0) P_SORT(&begin);
				if (odd != 0) P_SORT(&odd);
				if (even != 0) P_SORT(&even);
				break;
			case 2:
				if (begin != 0) V_SORT(&begin);
				if (odd != 0) V_SORT(&odd);
				if (even != 0) V_SORT(&even);
				break;
			default:
				std::cout << "\nWrong input!\n";
				break;
			}
			break;
		}
		default:
			std::cout << "\nWrong input!\n";
			break;
		}
	}
	return 0;
}