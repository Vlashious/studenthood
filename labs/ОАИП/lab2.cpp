#include "pch.h"
#include <iostream>
using namespace std;

double rec(double n) {
	if (n >= 2) {
		return n * n / (n*n - 1)*rec(n - 2);
	}
	else {
		return 1;
	}
}

double rec_n(int n) {
	double dres = 1;
	for (int i = 2; i <= n; i += 2) dres *= (double)i*(double)i / ((double)i*(double)i - 1);
	return dres;
}

int main() {
	int n;
	while (true) {
		while (true) {
			cout << "Enter an even n (n>=2): ";
			cin >> n;
			if (n % 2 == 0 && n != 0 && n > 0) {
				break;
			}
			else cout << "This is an odd number, a negative number or zero" << endl;
		}
		cout << "Recursive function: " << rec(n) << endl;
		cout << "Non-recursive function: " << rec_n(n) << endl;
	}
}
