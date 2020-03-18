#include "pch.h"
#include <iostream>
#include <vector>
#include <string>
#include <sstream>

using std::cout;
using std::cin;
using std::vector;
using std::endl;

struct float_ {
	vector<int> mantissa;
	vector<int> index;
};

const int SIZE = 16;
const vector<int> ZERO(SIZE, 0);
const vector<int> ONE = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1 };
const vector<int> ONES = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };

vector<int> ToBinarySMR(int);
void print(vector<int>);
vector<int> ToOneComplement(int);
vector<int> ToTwoComplement(int);
vector<int> Add(vector<int>, vector<int>);
vector<int> TwoToOne(vector<int>);
vector<int> OneToSMR(vector<int>);
vector<int> Shift(vector<int>);
vector<int> Multiply(vector<int>, vector<int>);
vector<int> Division(vector<int>, vector<int>);
float_ fAdd(int, int);

int main() {

	int x1, x2;
	vector<int> X1(SIZE, 0);
	vector<int> X2(SIZE, 0);
	vector<int> ANSWER(SIZE, 0);
	cin >> x1 >> x2;
	cout << "+X1 = +" << x1 << endl;
	cout << "Signed Magnitude Representation:";
	print(ToBinarySMR(x1));
	cout << "Ones' Complement:";
	print(ToOneComplement(x1));
	cout << "Two's Complement:";
	print(ToTwoComplement(x1));

	cout << "-X1 = -" << x1 << endl;
	cout << "Signed Magnitude Representation:";
	print(ToBinarySMR(-x1));
	cout << "Ones' Complement:";
	print(ToOneComplement(-x1));
	cout << "Two's Complement:";
	print(ToTwoComplement(-x1));

	cout << "+X2 = +" << x2 << endl;
	cout << "Signed Magnitude Representation:";
	print(ToBinarySMR(x2));
	cout << "Ones' Complement:";
	print(ToOneComplement(x2));
	cout << "Two's Complement:";
	print(ToTwoComplement(x2));

	cout << "-X2 = -" << x2 << endl;
	cout << "Signed Magnitude Representation:";
	print(ToBinarySMR(-x2));
	cout << "Ones' Complement:";
	print(ToOneComplement(-x2));
	cout << "Two's Complement:";
	print(ToTwoComplement(-x2));

	cout << "X1 + X2 = 31" << endl;
	cout << "Signed Magnitude Representation:";
	print(Add(ToBinarySMR(x1), ToBinarySMR(x2)));
	cout << "Ones' Complement:";
	print(Add(ToBinarySMR(x1), ToBinarySMR(x2)));
	cout << "Two's Complement:";
	print(Add(ToBinarySMR(x1), ToBinarySMR(x2)));

	cout << "-X1 + X2 = 13" << endl;
	cout << "Signed Magnitude Representation:";
	print(Add(ToTwoComplement(-x1), ToBinarySMR(x2)));
	cout << "Ones' Complement:";
	print(Add(ToTwoComplement(-x1), ToBinarySMR(x2)));
	cout << "Two's Complement:";
	print(Add(ToTwoComplement(-x1), ToBinarySMR(x2)));

	cout << "-X1 - X2 = -31" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = OneToSMR(TwoToOne(Add(ToTwoComplement(-x1), ToTwoComplement(-x2))));
	print(ANSWER);
	cout << "Ones' Complement:";
	ANSWER = TwoToOne(Add(ToTwoComplement(-x1), ToTwoComplement(-x2)));
	print(ANSWER);
	cout << "Two's Complement:";
	ANSWER = Add(ToTwoComplement(-x1), ToTwoComplement(-x2));
	print(ANSWER);

	cout << "X1 - X2 = -13" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = Add(ToTwoComplement(-x2), ToBinarySMR(x1));
	print(OneToSMR(TwoToOne(ANSWER)));
	cout << "Ones' Complement:";
	print(TwoToOne(ANSWER));
	cout << "Two's Complement:";
	print(ANSWER);

	cout << "X1 * X2 = 198" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = Multiply(ToBinarySMR(x1), ToBinarySMR(x2));
	print(ANSWER);
	cout << "Ones' Complement:";
	print(ANSWER);
	cout << "Two's Complement:";
	print(ANSWER);

	cout << "-X1 * X2 = -198" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = Multiply(ToBinarySMR(-x1), ToBinarySMR(x2));
	print(OneToSMR(TwoToOne(ANSWER)));
	cout << "Ones' Complement:";
	print(TwoToOne(ANSWER));
	cout << "Two's Complement:";
	print(ANSWER);

	cout << "-X1 * (-X2) = 198" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = Multiply(ToBinarySMR(-x1), ToBinarySMR(-x2));
	print(ANSWER);
	cout << "Ones' Complement:";
	print(ANSWER);
	cout << "Two's Complement:";
	print(ANSWER);

	cout << "X1 * (-X2) = -198" << endl;
	cout << "Signed Magnitude Representation:";
	ANSWER = Multiply(ToBinarySMR(-x2), ToBinarySMR(x1));
	print(OneToSMR(TwoToOne(ANSWER)));
	cout << "Ones' Complement:";
	print(TwoToOne(ANSWER));
	cout << "Two's Complement:";
	print(ANSWER);

	cout << "X1 / X2 = ";
	Division(ToBinarySMR(x1), ToBinarySMR(x2));

	cout << "-X1 / X2 = ";
	Division(ToBinarySMR(-x1), ToBinarySMR(x2));

	cout << "X1 / (-X2) = ";
	Division(ToBinarySMR(x1), ToBinarySMR(-x2));

	cout << "-X1 / (-X2) = ";
	Division(ToBinarySMR(-x1), ToBinarySMR(-x2));

	cout << "X1_float + X2_float =  " << endl;
	float_ ANS = fAdd(x1, x2);
	for (int i = 0; i < SIZE; i++) {
		cout << ANS.mantissa[i];
	}
	cout << "*2^";
	for (int i = 0; i < SIZE; i++) {
		cout << ANS.index[i];
	}
	return 0;
}

vector<int> ToBinarySMR(int x) {
	vector<int> binNumber(SIZE, 0);
	if (x < 0) binNumber[0] = 1;
	int pos = SIZE - 1;
	do {
		if (x % 2 == 0) {
			pos--;
			x /= 2;
		}
		else {
			binNumber[pos] = 1;
			pos--;
			x /= 2;
		}
	} while (x != 0);
	return binNumber;
}

vector<int> ToOneComplement(int x) {
	vector<int> binNumber = ToBinarySMR(x);
	if (x > 0) return binNumber;
	for (int i = SIZE - 1; i > 0; i--) {
		if (binNumber[i] == 0) {
			binNumber[i] = 1;
		}
		else {
			binNumber[i] = 0;
		}
	}
	return binNumber;
}

vector<int> Add(vector<int> X1, vector<int> X2) {
	vector<int> ANSWER(SIZE, 0);
	int inMind = 0;
	for (int i = SIZE - 1; i >= 0; i--) {
		if (X1[i] + X2[i] + inMind == 0) {
			ANSWER[i] = 0;
		}
		else if (X1[i] + X2[i] + inMind == 1 && inMind == 1) {
			ANSWER[i] = 1;
			inMind = 0;
		}
		else if (X1[i] + X2[i] + inMind == 1 && inMind != 1) {
			ANSWER[i] = 1;
		}
		else if (X1[i] + X2[i] + inMind == 2 && inMind == 1) {
			ANSWER[i] = 0;
		}
		else if (X1[i] + X2[i] + inMind == 2 && inMind != 1) {
			ANSWER[i] = 0;
			inMind = 1;
		}
		else if (X1[i] + X2[i] + inMind == 3) {
			ANSWER[i] = 1;
		}
	}
	return ANSWER;
}

vector<int> ToTwoComplement(int x) {
	vector<int> binNumber = ToBinarySMR(x);
	if (x > 0) return binNumber;
	for (int i = 1; i < SIZE; i++) {
		if (binNumber[i] == 0) binNumber[i] = 1;
		else binNumber[i] = 0;
	}
	binNumber = Add(ONE, binNumber);
	return binNumber;
}

vector<int> TwoToOne(vector<int> x) {
	if (x[0] == 1) {
		x = Add(x, ONES);
		return x;
	}
	else return x;
}

vector<int> OneToSMR(vector<int> x) {
	if (x[0] == 1) {
		for (int i = 1; i < SIZE; i++) {
			x[i] = !x[i];
		}
		return x;
	}
	else return x;
}

vector<int> SMRtoTwo(vector<int> x) {
	for (int i = 0; i < SIZE; i++) {
		x[i] = !x[i];
	}
	x[0] = 1;
	x = Add(x, ONE);
	return x;
}

void print(vector<int> x) {
	cout << endl;
	for (int i = 0; i < SIZE; i++) {
		cout << x[i];
	}
	cout << endl;
}

vector<int> Shift(vector<int> x) {
	x[1] = x[0];
	x.erase(x.begin());
	x.push_back(0);
	return x;
}

vector<int> unShift(vector<int> x) {
	x.emplace(x.begin(), 0);
	x.erase(x.begin() + 1);
	return x;
}

vector<int> Multiply(vector<int> x1, vector<int> x2) {
	bool isNegative = true;
	if (x1[0] == x2[0]) isNegative = false;
	vector<int> ANSWER(SIZE, 0);
	for (int i = SIZE - 1; i > 0; i--) {
		if (x2[i] == 1) {
			ANSWER = Add(ANSWER, x1);
		}
		x1 = Shift(x1);
	}
	if (isNegative) ANSWER[0] = 1;
	else ANSWER[0] = 0;
	return ANSWER;
}

bool isGreater(vector<int> x1, vector<int> x2) {
	std::stringstream ss1, ss2;
	std::string X1, X2;
	for (int i = 0; i < SIZE; i++) {
		ss1 << x1[i];
	}
	X1 = ss1.str();
	for (int i = 0; i < SIZE; i++) {
		ss2 << x2[i];
	}
	X2 = ss2.str();
	if (X1 >= X2) return true;
	else return false;
}

vector<int> Division(vector<int> x1, vector<int> x2) {
	vector<int> ANSWER;
	bool isNegative = true;
	if (x1[0] == x2[0]) isNegative = false;
	x1[0] = x2[0] = 0;
	for (int i = 0; i < SIZE; i++) {
		if (!isGreater(x1, x2)) {
			ANSWER.push_back(0);
			x1 = Shift(x1);
		}
		else {
			ANSWER.push_back(1);
			x1 = Add(x1, SMRtoTwo(x2));
			x1 = Shift(x1);
		}
	}
	if (isNegative) cout << "-";
	else cout << "+";
	cout << "0,";
	for (int i = 1; i < 6; i++) {
		cout << ANSWER[i];
	}
	cout << endl;
	return ANSWER;
}

float_ fAdd(int x1, int x2) {
	float_ X1, X2;
	float_ ANSWER;
	X1.mantissa = ToBinarySMR(x1);
	X1.index = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0 };
	X2.mantissa = ToBinarySMR(x2);
	X2.index = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1 };
	while (!isGreater(X1.index, X2.index)) {
		X1.mantissa = unShift(X1.mantissa);
		X1.index = Add(X1.index, ONE);
	}
	ANSWER.mantissa = Add(X1.mantissa, X2.mantissa);
	ANSWER.index = X1.index;
	return ANSWER;
}