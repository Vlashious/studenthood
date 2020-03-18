#include "pch.h"
#include <iostream>
#include <math.h>
#include <vector>
//#include <string>

using std::cout;
using std::cin;
using std::endl;
using std::vector;

struct Info {
	int key;
	char FIOpass[40];
};

struct Tree {
	Info info;
	Tree *left, *right;
} *root, *worktree;

void Get_Array(Tree *t, vector<Info> *n) {
	if (t->left != NULL) Get_Array(t->left, n);
	n->push_back(t->info);
	if (t->right != NULL)Get_Array(t->right, n);
}

void Sort_Array(vector<Info> *n) {
	for (int i = 0; i < (*n).size(); i++) {
		for (int k = i + 1; k < (*n).size(); k++) {
			if ((*n)[i].key > (*n)[k].key) {
				Info t = (*n)[i];
				n[i] = n[k];
				(*n)[k] = t;
			}
		}
	}
}

void Add(Tree **t, int key, char *s) {
	Tree *p = *t;
	Tree *prev_p = NULL;
	if (*t == NULL) {
		*t = new Tree;
		(*t)->info.key = key;
		snprintf((*t)->info.FIOpass, 40, "%s", s);
		(*t)->left = (*t)->right = NULL;
		return;
	}
	while (p) {
		if (p->info.key == key) {
			cout << "Dublicate!";
			return;
		}
		else if (p->info.key > key) {
			prev_p = p;
			p = p->left;
		}
		else {
			prev_p = p;
			p = p->right;
		}
	}
	p = new Tree;
	p->info.key = key;
	snprintf(p->info.FIOpass, 40, "%s", s);
	p->left = p->right = NULL;
	if (prev_p->info.key > p->info.key) {
		prev_p->left = p;
	}
	else {
		prev_p->right = p;
	}
}

void Balance(Tree **t, int n, int k, vector<Info> *v) {
	if (n == k) {
		*t = NULL;
		return;
	}
	else {
		int m = (n + k) / 2;
		*t = new Tree;
		(*t)->info = (*v)[m];
		Balance(&(*t)->left, n, m, v);
		Balance(&(*t)->right, m + 1, k, v);
	}
}

void Delete(Tree *t) {
	if (t != NULL) {
		Delete(t->left);
		Delete(t->right);
		delete t;
	}
}

Tree * Delete_Info(Tree *t, int key) {
	Tree *Del, *Prev_Del, *R, *Prev_R;
	Del = root;
	Prev_Del = NULL;
	while (Del != NULL && Del->info.key != key) {
		Prev_Del = Del;
		if (Del->info.key > key) Del = Del->left;
		else Del = Del = Del->right;
	}
	if (Del == NULL) {
		cout << "There is no such key :(\n";
		return root;
	}
	if (Del->right == NULL) R = Del->left;
	else if (Del->left == NULL) R = Del->right;
	else {
		Prev_R = Del;
		R = Del->left;
		while (R->right != NULL) {
			Prev_R = R;
			R = R->right;
		}
		if (Prev_R == Del) R->right = Del->right;
		else {
			R->right = Del->right;
			Prev_R->right = R->left;
			R->left = Prev_R;
		}
	}
	if (Del == root) root = R;
	else if (Del->info.key < Prev_Del->info.key) {
		Prev_Del->left = R;
	}
	else Prev_Del->right = R;
	delete Del;
	return root;
}

void Show(Tree *t, int n) {
	std::string s;
	if (t) {
		Show(t->right, n + 1);
		for (int i = 0; i < n; i++) {
			s += "   ";
		}
		cout << s.c_str() << t->info.key <<  endl << s.c_str() << t->info.FIOpass << endl;
		Show(t->left, n + 1);
	}
}

void Make_Work_Tree(Tree **t, int r, vector<Info> *v) {
	if (r < 0 || r >= v->size()) {
		*t = NULL;
		return;
	}
	else {
		*t = new Tree;
		(*t)->info = (*v)[r];
		if (r < trunc(v->size() / 2)) (*t)->right = NULL;
		if (r > trunc(v->size() / 2)) (*t)->left = NULL;
		if (r <= trunc(v->size()/2)) Make_Work_Tree(&(*t)->left, r - 1, v);
		if (r < trunc(v->size()/2)) return;
		Make_Work_Tree(&(*t)->right, r + 1, v);
	}
}

void Individual_Stack(Tree *t, double value) {
	double delta1, delta2, delta3;
	delta1 = fabs(t->info.key - value);
	if (t->left) delta2 = fabs(t->left->info.key - value);
	if (t->right) delta3 = fabs(t->right->info.key - value);
	if (t->right && t->left) {
		if (delta1 < delta2 && delta1 < delta3) {
			cout << "Average value is: " << value << endl;
			cout << "Nearest value is: " << t->info.key << ", " << t->info.FIOpass;
			return;
		}
		else if (delta2 < delta3) {
			Individual_Stack(t->left, value);
			return;
		}
		else {
			Individual_Stack(t->right, value);
			return;
		}
	}
	if (t->left) {
		if (delta1 < delta2) {
			cout << "Average value is: " << value << endl;
			cout << "Nearest value is: " << t->info.key << ", " << t->info.FIOpass;
			return;
		}
		else {
			Individual_Stack(t->left, value);
			return;
		}
	}
	if (t->right) {
		if (delta1 < delta3) {
			cout << "Average value is: " << value << endl;
			cout << "Nearest value is: " << t->info.key << ", " << t->info.FIOpass;
			return;
		}
		else {
			Individual_Stack(t->right, value);
			return;
		}
	}
}

int main() {
	vector<Info> n;
	int nSwitch;
	int key;
	char* s = new char[40];
	double value = 0;
	while (true) {
		cout << "1) Add\n2) Balance\n3) Delete tree\n4) Delete info\n5) View\n6) Individual\n0) Exit\n";
		cin >> nSwitch;
		switch (nSwitch) {
		case 0:
			return 0;
		case 1:
			cout << "Key: "; cin >> key;
			cout << "Passport and FIO: ";
			while (cin.get() != '\n');
			gets_s(s, 40);
			Add(&root, key, s);
			break;
		case 2:
			n.clear();
			Get_Array(root, &n);
			Sort_Array(&n);
			Delete(root);
			root = NULL;
			Balance(&root, 0, n.size(), &n);
			break;
		case 3:
			Delete(root);
			n.clear();
			root = NULL;
			break;
		case 4:
			cout << "Key to delete: ";
			cin >> key;
			root = Delete_Info(root, key);
			break;
		case 5:
			Show(root, 0);
			break;
		case 6:
			n.clear();
			Get_Array(root, &n);
			Sort_Array(&n);
			Delete(worktree);
			worktree = NULL;
			Make_Work_Tree(&worktree, trunc(n.size() / 2), &n);
			for (int i = 0; i < n.size(); i++) {
				value += n[i].key;
			}
			value /= n.size();
			Individual_Stack(worktree, value);
			break;
		}
	}
	return 0;
}