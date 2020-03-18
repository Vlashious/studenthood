#include <iostream>

class A {
	int count;
public:
	A() : count(0) {}
	A(A& a) : count(a.count + 1) {}

	A& operator = (A& a) {
		return count *= 10, *this;
	}

	void print() { std::cout << count; }
};

int main() {
	A a, b = a, c = b = b, d = c = c = c;
	c.print();
	return 0;
}