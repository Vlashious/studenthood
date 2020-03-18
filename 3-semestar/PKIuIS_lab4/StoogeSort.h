#pragma once
#include <utility>

using std::swap;

template <class Type>
void stoogesort(Type arr[], int l, int h)
{
	if (l >= h)
		return;

	// If first element is smaller than last, 
	// swap them 
	if (arr[l] > arr[h])
		swap(arr[l], arr[h]);

	// If there are more than 2 elements in 
	// the array 
	if (h - l + 1 > 2) {
		int t = (h - l + 1) / 3;

		// Recursively sort first 2/3 elements 
		stoogesort(arr, l, h - t);

		// Recursively sort last 2/3 elements 
		stoogesort(arr, l + t, h);

		// Recursively sort first 2/3 elements 
		// again to confirm 
		stoogesort(arr, l, h - t);
	}
}

template <class Type>
void stoogesort(Type& arr, int l, int h)
{
	if (l >= h)
		return;

	// If first element is smaller than last, 
	// swap them 
	if (arr[l] > arr[h])
		swap(arr[l], arr[h]);

	// If there are more than 2 elements in 
	// the array 
	if (h - l + 1 > 2) {
		int t = (h - l + 1) / 3;

		// Recursively sort first 2/3 elements 
		stoogesort(arr, l, h - t);

		// Recursively sort last 2/3 elements 
		stoogesort(arr, l + t, h);

		// Recursively sort first 2/3 elements 
		// again to confirm 
		stoogesort(arr, l, h - t);
	}
}