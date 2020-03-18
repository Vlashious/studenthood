#include "pch.h"
#include <stdlib.h>
#include <iostream>
#include <time.h>

using namespace std;

void FILL_ARRAY(int *p_array, int N) {
	srand(time(NULL));
	int nSwitch;
	cout << "Random fill - 1\nFill by hand - 2\n";
	cin >> nSwitch;
	switch (nSwitch) {
	case 1:
		for (int i = 0; i < N; i++) {
			*(p_array + i) = rand() % 201 - 100;
		}
		break;
	case 2:
		for (int i = 0; i < N; i++) {
			cin >> *(p_array + i);
		}
		break;
	default: cout << "There is no such option!";
	}
}

void QUICK_SORT(int *p_array, int left, int right, int N) {
	int l = left, r = right;
	int x = p_array[(left + right) / 2];
	int y;
	do {
		while ((p_array[l] < x) && (l < right)) l++;
		while ((x < p_array[r]) && (r > left)) r--;
		if (l <= r) {
			y = p_array[l];
			p_array[l] = p_array[r];
			p_array[r] = y;
			l++;
			r--;
		}
	} while (l <= r);
	if (left < r) QUICK_SORT(p_array, left, r, N);
	if (l < right) QUICK_SORT(p_array, l, right, N);
}

int BINARY_SEARCH(int *p_array, int l, int r, int x) {
	if (r >= l) {
		int mid = l + (r - l) / 2;
		if (p_array[mid] == x) return mid;
		if (p_array[mid] > x) {
			return BINARY_SEARCH(p_array, l, mid - 1, x);
		}
		if (p_array[mid] < x) {
			return BINARY_SEARCH(p_array, mid + 1, r, x);
		}
		return -1;
	}
}

int main() {
	int N;
	cout << "Enter the size of array: ";
	cin >> N;
	int *p_array = new int[N];
	FILL_ARRAY(p_array, N);
	cout << "Your array: ";
	for (int i = 0; i < N; i++) {
		cout << *(p_array + i) << " ";
	}
	cout << endl;
	QUICK_SORT(p_array, 0, N - 1, N);
	cout << "Your sorted by QuickSort array: ";
	for (int i = 0; i < N; i++) {
		cout << *(p_array + i) << " ";
	}
	cout << endl;
	int TEMP;
	cout << "What number do you want to find: ";
	cin >> TEMP;
	int result = BINARY_SEARCH(p_array, 0, N - 1, TEMP);
	if (result == -1) cout << "There is no such number.";
	else cout << "You number has " << result << " index.";
	delete[]p_array;
	return 0;
}