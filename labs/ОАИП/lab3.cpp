#include "pch.h"
#include <stdio.h>
#include <iostream>
#include <string>
#include <io.h>
using namespace std;

struct TIMETABLE {
	int nFlightNum;
	char szPlaneType[30];
	char szEndPoint[30];
	char szFlightTime[30];
} table;

char file[] = "data_flights.dat";
char file_out[] = "flights_res.txt";

FILE *p_datf;// , *p_outf;

void clear() { //очистка потока ввода
	int c;
	while ((c = getchar()) != '\n' && c != EOF);
}

void Quick_Sort(TIMETABLE *p_sortArray, int left, int right, int *key) {
	int l = left, r = right;
	int x = key[(left + right) / 2];
	TIMETABLE xTABLE;
	int y;
	do {
		while ((key[l] < x) && (l < right)) l++;
		while ((x < key[r]) && (r > left)) r--;
		if (l <= r) {
			y = key[l];
			key[l] = key[r];
			key[r] = y;
			xTABLE = p_sortArray[l];
			p_sortArray[l] = p_sortArray[r];
			p_sortArray[r] = xTABLE;
			l++;
			r--;
		}
	} while (l <= r);
	if (left < r) Quick_Sort(p_sortArray, left, r, key);
	if (l < right) Quick_Sort(p_sortArray, l, right, key);
}

int main() {

	/*char b[30];
	string temp;
	getline(cin, temp);
	strcpy_s(b, sizeof(b), temp.c_str());*/


	//p_outf = fopen(file_out, "w");
	int *key, *add_key;
	TIMETABLE *p_table, *p_sortArray;
	string szUserRequest;
	int kol, nSortArrayElements, j = 0, m;
	while (true) {
		int nSwitch;
		cout << "Create - 1\nView - 2\nAdd - 3\nLinear Search - 4\nChoice Sort - 5\nQuickSort - 6\nBinary Search - 7\nExit - 0\n";
		cin >> nSwitch;
		switch (nSwitch) {
		case 0:
			//fclose(p_outf);
			return 0;
			break;
		case 1: //create
			p_datf = fopen(file, "wb");
			fclose(p_datf);
			cout << "File " << file << " created!\n";
			break;
		case 2: //view
			if ((p_datf = fopen(file, "rb")) == NULL) {
				puts("\nOpen ERROR!");
				return 0;
			}
			while (1) {
				if (!fread(&table, sizeof(TIMETABLE), 1, p_datf)) break;
				printf("Flight number: %i\nPlane type: %s\nEnd point: %s\nFlight time: %s\n----------\n", table.nFlightNum, table.szPlaneType, table.szEndPoint, table.szFlightTime);
			}
			fclose(p_datf);
			break;
		case 3: //add
		{
			string STR;
			p_datf = fopen(file, "ab");
			printf("\nFlight number : ");
			cin >> table.nFlightNum;
			printf("\nPlane type: ");
			clear();
			getline(cin, STR);
			strcpy_s(table.szPlaneType, sizeof(table.szPlaneType), STR.c_str());
			printf("\nEnd point: ");
			getline(cin, STR);
			strcpy_s(table.szEndPoint, sizeof(table.szEndPoint), STR.c_str());
			printf("\nFlight time: ");
			getline(cin, STR);
			strcpy_s(table.szFlightTime, sizeof(table.szFlightTime), STR.c_str());
			fwrite(&table, sizeof(TIMETABLE), 1, p_datf);
			fclose(p_datf);
			break;
		}
		case 4: //linear search
			printf("Enter the destination point you want to find flights you need: ");
			clear();
			getline(cin, szUserRequest);
			p_datf = fopen(file, "rb");
			kol = _filelength(_fileno(p_datf)) / sizeof(TIMETABLE);
			p_table = new TIMETABLE[kol];
			for (int i = 0; i < kol; i++) {
				fread(p_table + i, sizeof(TIMETABLE), 1, p_datf);
			}
			fclose(p_datf);
			printf("\n\t----------\n\tAVAILABLE FLIGHTS\n\t----------\n");
			for (int i = 0; i < kol; i++) {
				if (szUserRequest == p_table[i].szEndPoint) {
					printf("Flight number: %i\nPlane type: %s\nEnd point: %s\nFlight time: %s\n----------\n", p_table[i].nFlightNum, p_table[i].szPlaneType, p_table[i].szEndPoint, p_table[i].szFlightTime);
				}
			}
			p_table = NULL;
			break;
		case 5:
			printf("Enter the destination point you want to find flights you need: ");
			clear();
			getline(cin, szUserRequest);
			p_datf = fopen(file, "rb");
			kol = _filelength(_fileno(p_datf)) / sizeof(TIMETABLE);
			p_table = new TIMETABLE[kol];
			for (int i = 0; i < kol; i++) {
				fread(p_table + i, sizeof(TIMETABLE), 1, p_datf);
			}
			fclose(p_datf);
			nSortArrayElements = 0;
			for (int i = 0; i < kol; i++) {
				if (szUserRequest == p_table[i].szEndPoint) {
					nSortArrayElements++;
				}
			}
			p_sortArray = new TIMETABLE[nSortArrayElements];
			for (int i = 0; i < kol; i++) {
				if (szUserRequest == p_table[i].szEndPoint) {
					p_sortArray[j] = p_table[i];
					j++;
				}
			}
			j = 0;
			for (int i = 0; i < nSortArrayElements - 1; i++) {
				m = i;
				for (int k = i + 1; k < nSortArrayElements; k++) {
					string HRS;
					HRS = p_sortArray[k].szFlightTime[0];
					HRS += p_sortArray[k].szFlightTime[1];
					int nHRS1 = stoi(HRS);
					HRS = p_sortArray[m].szFlightTime[0];
					HRS += p_sortArray[m].szFlightTime[1];
					int nHRS2 = stoi(HRS);
					if (nHRS1 < nHRS2) {
						m = k;
						TIMETABLE R;
						R = p_sortArray[m];
						p_sortArray[m] = p_sortArray[i];
						p_sortArray[i] = R;
					}
					else if (nHRS1 == nHRS2) {
						string MNS;
						MNS = p_sortArray[k].szFlightTime[3];
						MNS += p_sortArray[k].szFlightTime[4];
						int nMNS1 = stoi(MNS);
						MNS = p_sortArray[m].szFlightTime[3];
						MNS += p_sortArray[m].szFlightTime[4];
						int nMNS2 = stoi(MNS);
						if (nMNS1 < nMNS2) {
							m = k;
							TIMETABLE R;
							R = p_sortArray[m];
							p_sortArray[m] = p_sortArray[i];
							p_sortArray[i] = R;
						}
					}
				}
			}
			for (int i = 0; i < nSortArrayElements; i++) {
				printf("Flight number: %i\nPlane type: %s\nEnd point: %s\nFlight time: %s\n----------\n", p_sortArray[i].nFlightNum, p_sortArray[i].szPlaneType, p_sortArray[i].szEndPoint, p_sortArray[i].szFlightTime);
			}
			p_table = NULL;
			p_sortArray = NULL;
			break;
		case 6:
			printf("Enter the destination point you want to find flights you need: ");
			clear();
			getline(cin, szUserRequest);
			p_datf = fopen(file, "rb");
			kol = _filelength(_fileno(p_datf)) / sizeof(TIMETABLE);
			p_table = new TIMETABLE[kol];
			for (int i = 0; i < kol; i++) {
				fread(p_table + i, sizeof(TIMETABLE), 1, p_datf);
			}
			fclose(p_datf);
			nSortArrayElements = 0;
			for (int i = 0; i < kol; i++) {
				if (szUserRequest == p_table[i].szEndPoint) {
					nSortArrayElements++;
				}
			}
			p_sortArray = new TIMETABLE[nSortArrayElements];
			for (int i = 0; i < kol; i++) {
				if (szUserRequest == p_table[i].szEndPoint) {
					p_sortArray[j] = p_table[i];
					j++;
				}
			}
			j = 0;
			key = new int[nSortArrayElements];
			add_key = new int[nSortArrayElements];
			for (int i = 0; i < nSortArrayElements; i++) {
				string S;
				S = p_sortArray[i].szFlightTime[0];
				S += p_sortArray[i].szFlightTime[1];
				S += p_sortArray[i].szFlightTime[3];
				S += p_sortArray[i].szFlightTime[4];
				key[i] = stoi(S);
			}
			Quick_Sort(p_sortArray, 0, nSortArrayElements - 1, key);
			for (int i = 0; i < nSortArrayElements; i++) {
				printf("Flight number: %i\nPlane type: %s\nEnd point: %s\nFlight time: %s\n----------\n", p_sortArray[i].nFlightNum, p_sortArray[i].szPlaneType, p_sortArray[i].szEndPoint, p_sortArray[i].szFlightTime);
			}
			break;
		default: cout << "Incorrect input!";
		}
	}
	//fclose(p_outf);
	return 0;
}