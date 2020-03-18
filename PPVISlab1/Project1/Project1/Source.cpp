#include "Matrix.h" 
int main () {
	
	int x = 0, y = 0;
	cout << "M1 rows and columns:\n";
	cin >> x >> y;
	Matrix m1(x, y);
	cout << "M2 rows and columns:\n";
	cin >> x >> y;
	Matrix m2(x, y);
	int k = 0;
	system("cls");
	while (true) {
		cout << "It is a menu, if you enter:\n\t1 - M1 + M2;\n\t2 - M1 + value (you have to enter the value);\n\t3 - M1++ and ++M2;\n\t";
		cout << "4 - M1 - M2;\n\t5 - M1 - value (you have to enter the value);\n\t6 - M1-- and --M2;\n\t7 - M1*M2;\n\t8 - M1*value (you have to enter the value);\n\t";
		cout << "9 - M1/value and M1 /= value (you have to enter the value);\n\t10 - M1^value (you have to enter value);\n\t11 - determinant M1 and M2;\n\t12 - Normi M1 and M2\n";
		cin >> k;
		if (k == 13)
			break;
		system("cls");
		std::cout << "M1 is \n";
		m1.printMatrix();
		std::cout << "\nM2 is \n";
		m2.printMatrix();
		std::cout << "\n\n";
		switch (k)
		{
			m1.printMatrix();
			cout << "\n";
			m2.printMatrix();
			cout << "\n";
			case 1: {
				Matrix m3, buff;
				m3 = m1 + m2;
				cout << "M1 + M2 = " << std::endl;
				m3.printMatrix();
				buff = m1;
				m1 += m2;
				cout << "\nM1 += M2 = \n";
				m1.printMatrix();
				m1 = buff;
				break;
			}
			case 2: {
				int  value = 0;
				std::cout << "Enter the value pls ";
				Matrix buff = m1;
				cin >> value;
				Matrix m3;
				m3 = m1 + value;
				std::cout << "M1 + value = \n";
				m3.printMatrix();
				break;
			}
			case 3: {
				Matrix buff1 = m1, buff2 = m2;
				m1++;
				++m2;
				cout << "M1++ = \n";
				m1.printMatrix();
				cout << "++M2 = \n";
				m2.printMatrix();
				m1 = buff1;
				m2 = buff2;
				break;
			}
			case 4: {
				Matrix m3;
				m3 = m1 - m2;
				cout << "M1 - M2 = \n";
				m3.printMatrix();
				break;
			}
			case 5: {
				Matrix m3;
				int k = 0;
				std::cout << "Enter the value \n";
				std::cin >> k;
				m3 = m1 - k;
				std::cout << "M1 - value = \n";
				m3.printMatrix();
				break;
			}
			case 6: {
				Matrix buff1 = m1, buff2 = m2;
				m1--;
				--m2;
				cout << "M1-- = \n";
				m1.printMatrix();
				cout << "\n--M2 = \n";
				m2.printMatrix();
				m1 = buff1;
				m2 = buff2;
				break;
			}
			case 7: {
				Matrix m3, buff;
				m3 = m1 * m2;
				cout << "M1*M2 = \n" << std::endl;
				m3.printMatrix();
				break;
			}
			case 8: {
				std::cout << "Enter the value \n";
				int k = 0;
				std::cin >> k;
				Matrix m3;
				m3 = m1 * k;
				cout << "M1*value = \n";
				m3.printMatrix();
				break;
			}
			case 9: {
				cout << "Enter the value pls \n";
				Matrix m3;
				int k = 0;
				std::cin >> k;
				m3 = m1/k;
				std::cout << "M1 /= k \n";
				m3.printMatrix();
				m3 = m1;
				m1 /= k;
				m1 = m3;
				break;
			}
			case 10: {
				Matrix m3;
				int value = 0;
				std::cout << "Enter the value \n";
				std::cin >> value;
				m3 = m1 ^ value;
				std::cout << "M1 ^ value\n";
				m3.printMatrix();
				break;
			}
			case 11: {
				std::cout << "The determinant of M1 is \n";
				std::cout << m1.det;
				std::cout << "\nThe determinant of M2 is \n";
				std::cout << m2.det;
				break;
			}
			case 12: {
				std::cout << "The normi of M1 is \n";
				std::cout << m1.nor;
				std::cout << "\nThe normi of M2 is \n";
				std::cout << m2.nor;
				break;
			}
		}
		std::cout << "\n\n\n";
		system("pause");
		system("cls");
	}
	return 0;
}