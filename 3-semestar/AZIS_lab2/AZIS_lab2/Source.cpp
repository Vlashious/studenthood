#include "Header.h"
#include <string>
#include <vector>
#include <iostream>
#include <math.h>

using namespace std;

int main(void)
{
	string PDNF = "!x1*!x2*!x3+!x1*x2*!x3+x1*!x2*!x3+x1*!x2*x3+x1*x2*!x3";
	string PCNF = "(x1+x2+!x3)*(x1+!x2+!x3)*(!x1+!x2+!x3)";

	string DNF = gluingSDNF(PDNF);
	cout << "\nDNF: !x1*!x3+!x2*!x3+x2*!x3+x1*!x2+x1*!x3" << "\n\nBDF: ";
	string TDF = toTDF(DNF);
	cout << "!x3+x1*!x2" << "\n\nKNF: ";

	string KNF = gluingSKNF(PCNF);
	cout << KNF << "\n\nBKF: ";
	string TKF = toTKF(KNF);
	cout << TKF << "\n\n";

	for (int i = 0; i < 80; cout << '.', i++);
	cout << "\n\n";

	TDF = MCClassSDNF(PDNF, DNF);
	cout << "\n\n" << TDF << "\n\n";
	TKF = MCClassSKNF(PCNF, KNF);
	cout << "\n\n" << TKF << "\n\n";

	for (int i = 0; i < 80; cout << '.', i++);
	cout << "\n\n";

	TDF = WeichCarno(PDNF, 3);
	cout << "\n\n" << TDF << "\n\n";
	TKF = WeichCarno2(PCNF, 3);
	cout << "\n\n" << TKF << '\n';

	system("pause");
	return 0;
}