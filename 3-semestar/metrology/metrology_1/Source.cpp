#include <iostream>
#include <Windows.h>

using std::cout;
using std::endl;
using std::cin;

int main() {

	SetConsoleOutputCP(65001);

	long long int M;

	cout << u8"Uviadzicie lik M (0 < M ≤ 20)\n";

	cin >> M;

	while (M <= 0 || M > 20) {
		cout << u8"0 < M ≤ 20\n";
		cin >> M;
	}

	for (int i = M - 2; i > 0; i -= 2) {
		M *= i;
	}

	cout << u8"Vynik: ";
	cout << M;
	cout << endl;

	system("pause");

	return 0;
}