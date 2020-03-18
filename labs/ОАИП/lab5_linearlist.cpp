#include "pch.h"
#include <iostream>
#include <time.h>

struct Stack {
	int nDATA;
	Stack *next, *prev;
} *begin, *odd, *even, *end, *endODD, *endEVEN;

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

void Add(Stack **begin, Stack **end) {
	Stack *temp = *begin;
	while (temp) {
		if (temp->next == 0) {
			break;
		}
		temp = temp->next;
	}
	Stack *t = new Stack;
	t->nDATA = rand() % 21;
	t->prev = temp;
	temp->next = t;
	if ((*begin)->next == 0) (*begin)->next = t;
	t->next = 0;
	*end = t;
}

void View(Stack *begin, Stack *end) {
	int nSwitch;
	std::cout << "\n----------\nStraight order - 1\nInverse order - 2\n----------\n";
	std::cin >> nSwitch;
	std::cout << "\n";
	switch (nSwitch) {
	case 1: {
		Stack *t = begin;
		while (t) {
			std::cout << t->nDATA << "\n";
			t = t->next;
		}
		break;
	}
	case 2: {
		Stack *t = end;
		while (t) {
			std::cout << t->nDATA << "\n";
			t = t->prev;
		}
		break;
	}
	default:
		std::cout << "\nWrong input!\n";
		break;
	}
}

void Delete(Stack **begin) {
	Stack *t = *begin;
	while (t) {
		*begin = (*begin)->next;
		delete t;
		t = *begin;
	}
}

void Individual(Stack *begin, Stack **odd, Stack **endODD, Stack **even, Stack **endEVEN) {
	Stack *BEGIN = begin;
	int EVENarray = 0, ODDarray = 0, *e_array, *o_array;
	int i = 0, j = 0;
	while (BEGIN) {
		if (BEGIN->nDATA % 2 == 0) {
			EVENarray++;
			BEGIN = BEGIN->next;
		}
		else {
			ODDarray++;
			BEGIN = BEGIN->next;
		}
	}
	e_array = new int[EVENarray];
	o_array = new int[ODDarray];
	BEGIN = begin;
	while (BEGIN) {
		if (BEGIN->nDATA % 2 == 0) {
			e_array[i] = BEGIN->nDATA;
			i++;
			BEGIN = BEGIN->next;
		}
		else {
			o_array[j] = BEGIN->nDATA;
			j++;
			BEGIN = BEGIN->next;
		}
	}
	i = 0;
	j = 0;
	if (EVENarray != 0) {
		*even = new Stack;
		(*even)->nDATA = e_array[0];
		(*even)->prev = 0;
		(*even)->next = 0;
		*endEVEN = *even;
		if (EVENarray != 1) {
			(*even)->next = new Stack;
			Stack *t = (*even)->next;
			for (int i = 1; i < EVENarray; i++) {
				t->nDATA = e_array[i];
				t->prev = *endEVEN;
				if (i != EVENarray - 1) {
					t->next = new Stack;
					*endEVEN = t;
					t = t->next;
					continue;
				}
				t->next = 0;
				*endEVEN = t;
			}
		}
	}
	if (ODDarray != 0) {
		*odd = new Stack;
		(*odd)->nDATA = o_array[0];
		(*odd)->prev = 0;
		(*odd)->next = 0;
		*endODD = *odd;
		if (ODDarray != 1) {
			(*odd)->next = new Stack;
			Stack *t = (*odd)->next;
			for (int i = 1; i < ODDarray; i++) {
				t->nDATA = o_array[i];
				t->prev = *endODD;
				if (i != ODDarray - 1) {
					t->next = new Stack;
					*endODD = t;
					t = t->next;
					continue;
				}
				t->next = 0;
				*endODD = t;
			}
		}
	}
	delete o_array;
	delete e_array;
}

/*void P_SORT(Stack **p) {
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
*/

int main() {
	srand(time(NULL));
	int nSwitch, kek;
	while (1) {
		std::cout << "\n----------\nCreate - 1\nView - 2\nAdd - 3\nDelete - 4\nIndividual Task - 5\nExit - 0\n----------\n";//Sort - 6\nExit - 0\n----------\n";
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
			end = begin;
			begin->next = 0;
			begin->prev = 0;
			begin->nDATA = rand() % 21;
			std::cout << "Enter num of elements: ";
			std::cin >> kek;
			for (int i = 1; i < kek; i++) {
				Add(&begin, &end);
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
				View(begin, end);
				break;
			case 2:
				if (even == 0) {
					std::cout << "\nCreate stack first!\n";
					break;
				}
				View(even, endEVEN);
				break;
			case 3:
				if (odd == 0) {
					std::cout << "\nCreate stack first!\n";
					break;
				}
				View(odd, endODD);
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
			Add(&begin, &end);
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
			Individual(begin, &odd, &endODD, &even, &endEVEN);
			break;
		/*case 6: {
			int nSwitch;
			std::cout << "\n----------\nPointer sort - 1\nValue exchange sort - 2\n----------\n";
			std::cin >> nSwitch;
			switch (nSwitch) {
			case 1:
				//if (begin != 0) P_SORT(&begin);
				//if (odd != 0) P_SORT(&odd);
				//if (even != 0) P_SORT(&even);
				break;
			case 2:
				//if (begin != 0) V_SORT(&begin);
				//if (odd != 0) V_SORT(&odd);
				//if (even != 0) V_SORT(&even);
				break;
			default:
				std::cout << "\nWrong input!\n";
				break;
			}
			break;
		}
		*/
		default:
			std::cout << "\nWrong input!\n";
			break;
		}
	}
	return 0;
}