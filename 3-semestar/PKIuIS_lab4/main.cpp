#include "HeapSort.h"
#include "StoogeSort.h"
#include <vector>
#include <iostream>

using std::vector;

class HelpMe {
public:
	HelpMe(int val) {
		value = val;
	}
	int getValue() {
		return value;
	}
	bool operator > (const HelpMe& rhs) {
		return this->getValue() > rhs.value;
	}
	friend std::ostream& operator << (std::ostream& os, const HelpMe& HELP) {
		os << HELP.value;
		return os;
	}
private:
	int value;
};

template <class Type>
void print(Type arr, int n) {
	for (int i = 0; i < n; i++) {
		if (i > 0) std::cout << ", ";
		std::cout << arr[i];
	}
	std::cout << std::endl;
}

int main() {
	vector<int> IntV= { 1, 9, -5, 4, 2, 12, 0 };
	vector<float> FloatV = { 1.1, 0.2, -6.3, 2.2 };
	vector<HelpMe> HELP = {1, 9, -5, 4, 2, 12, 0};
	int ArrInt[] = {1, 9, -5, 4, 2, 12, 0};
	int ArrIntSize = sizeof(ArrInt) / sizeof(*ArrInt);
	float ArrFloat[] = { 1.1, 0.2, -6.2, 2.2 };
	int ArrFloatSize = sizeof(ArrFloat) / sizeof(*ArrFloat);


	print(IntV, IntV.size());
	heapSort<vector<int>>(IntV, IntV.size());
	print(IntV, IntV.size());
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(FloatV, FloatV.size());
	heapSort<vector<float>>(FloatV, FloatV.size());
	print(FloatV, FloatV.size());
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(ArrInt, ArrIntSize);
	heapSort<int>(ArrInt, ArrIntSize);
	print(ArrInt, ArrIntSize);
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(ArrFloat, ArrFloatSize);
	heapSort<float>(ArrFloat, ArrFloatSize);
	print(ArrFloat, ArrFloatSize);
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(HELP, HELP.size());
	heapSort<vector<HelpMe>>(HELP, HELP.size());
	print(HELP, HELP.size());
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;


	IntV = { 1, 9, -5, 4, 2, 12, 0 };
	FloatV = { 1.1, 0.2, -6.3, 2.2 };
	HELP = { 1, 9, -5, 4, 2, 12, 0 };
	int ArrInt1[] = { 1, 9, -5, 4, 2, 12, 0 };
	float ArrFloat1[] = { 1.1, 0.2, -6.2, 2.2 };

	print(IntV, IntV.size());
	stoogesort<vector<int>>(IntV, 0, IntV.size() - 1);
	print(IntV, IntV.size());
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(FloatV, FloatV.size());
	stoogesort<vector<float>>(FloatV, 0, FloatV.size() - 1);
	print(FloatV, FloatV.size());
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(ArrInt1, ArrIntSize);
	stoogesort<int>(ArrInt1, 0, ArrIntSize - 1);
	print(ArrInt1, ArrIntSize);
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(ArrFloat1, ArrFloatSize);
	stoogesort<float>(ArrFloat1, 0, ArrFloatSize - 1);
	print(ArrFloat1, ArrFloatSize);
	for (int i = 0; i < 50; std::cout << "-", i++);
	std::cout << std::endl;
	print(HELP, HELP.size());
	stoogesort<vector<HelpMe>>(HELP, 0, HELP.size() - 1);
	print(HELP, HELP.size());
	std::cout << std::endl;
	return 0;
}