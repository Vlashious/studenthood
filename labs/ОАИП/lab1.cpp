#include "pch.h"
#include <iostream>
#include <stdio.h>
#include <io.h>
#include <fstream>
#include <string>
#include <regex>
using namespace std;

struct Student
{
	int nNUM; //номер студента в файле
	string szFIO; //ф и инициалы
	int nBirthDate, nGroupNum; //год рождения и номер группы
	int nPhysics, nMaths, nInfo, nChemistry; //оценки
	float avg_b; //средний балл
} stud;

void clear() { //очистка потока ввода
	int c;
	while ((c = getchar()) != '\n' && c != EOF);
}

char IN[] = "list.dat";
char OUT[] = "result.txt";

void GET_N_NEXT_STUD(int &NTotal) { //получить номер следующего студента 
	NTotal = 2;
	ifstream DAT(IN); //открыть файл для чтения
	string s;
	while (getline(DAT, s)) {
		NTotal++; //с каждой строкой прибавляем номер
	}
	DAT.close();
	NTotal /= 2;
}

void DATA_CREATE() { //создать новый файл для записи информации
	ofstream DAT(IN); //открыть или создать или перезаписать файл
	DAT.close();
	cout << "File " << IN << " successfully created.\n";
}

void DATA_EDIT() { //изменить информацию о студенте под номером введенным пользователем
	int NUM, nCounter = 0, I = 0, nStud;
	string s;
	cout << "Enter the number of the student you want to edit: ";
	cin >> NUM;
	nStud = NUM;
	NUM = (NUM - 1) * 2; //преобразование номера введенного пользователя для работы с массивом
	ifstream DAT(IN); //открыть файл для чтения
	while (getline(DAT, s)) {
		nCounter++; //узнать кол-во строк в файле
	}
	DAT.close();
	string *str = new string[nCounter]; //создать массив строк размером кол-ва строк в файле
	ifstream DAT_R(IN); //открыть файл для записи строк из файла в массив
	while (getline(DAT_R, s)) {
		str[I] = s; //запись
		I++;
	}
	DAT_R.close();
	str[NUM] = to_string(nStud) + ". "; //далее идёт склейка строк
	cout << "Last name and initials: ";
	str[NUM] = str[NUM] + "Last name and initials: ";
	clear();
	getline(cin, stud.szFIO);
	str[NUM] = str[NUM] + stud.szFIO;
	cout << "\nBirth date: ";
	str[NUM] = str[NUM] + " Birth date: ";
	cin >> stud.nBirthDate;
	str[NUM] = str[NUM] + to_string(stud.nBirthDate);
	cout << "\nGroup: ";
	str[NUM] = str[NUM] + ". Group: ";
	cin >> stud.nGroupNum;
	str[NUM] = str[NUM] + to_string(stud.nGroupNum);
	cout << "\nMarks for physics, maths, informatics and chemistry: ";
	cin >> stud.nPhysics >> stud.nMaths >> stud.nInfo >> stud.nChemistry;
	stud.avg_b = ((float)stud.nChemistry + (float)stud.nInfo + (float)stud.nMaths + (float)stud.nPhysics) / 4;
	str[NUM] = str[NUM] +
		". Physics: " + to_string(stud.nPhysics) +
		". Maths: " + to_string(stud.nMaths) +
		". Informatics: " + to_string(stud.nInfo) +
		". Chemistry: " + to_string(stud.nChemistry) +
		". GPA: " + to_string(stud.avg_b);
	ofstream DAT_RER(IN); //открыть файл для !перезаписи!
	for (int i = 0; i < nCounter; i++) {
		DAT_RER << str[i] << endl; //записываем строки в файл
	}
	DAT_RER.close();
}

void DATA_SHOW() { //функция показа содержимого файла
	string s;
	ifstream DAT(IN);
	while (getline(DAT, s)) {
		cout << s << endl;
	}
	DAT.close();
}

void DATA_ADD_STUD(int &NUM) { //добавление студента
	stud.nNUM = NUM;
	cout << "Last name and initials: ";
	clear();
	getline(cin, stud.szFIO);
	cout << "\nBirth date: ";
	cin >> stud.nBirthDate;
	cout << "\nGroup: ";
	cin >> stud.nGroupNum;
	cout << "\nMarks for physics, maths, informatics and chemistry: ";
	cin >> stud.nPhysics >> stud.nMaths >> stud.nInfo >> stud.nChemistry;
	stud.avg_b = ((float)stud.nChemistry + (float)stud.nInfo + (float)stud.nMaths + (float)stud.nPhysics) / 4;
	ofstream DAT(IN, ios_base::app); //открытие файла для добавления информации в конец файла
	DAT << stud.nNUM << ". Last name and initials: " << stud.szFIO
		<< " Birth date: " << stud.nBirthDate
		<< ". Group: " << stud.nGroupNum
		<< ". Physics: " << stud.nPhysics
		<< ". Maths: " << stud.nMaths
		<< ". Informatics: " << stud.nInfo
		<< ". Chemistry: " << stud.nChemistry
		<< ". GPA: " + to_string(stud.avg_b)
		<< endl << "----------" << endl;
	DAT.close();
	NUM++;
}

void DATA_OUT() { //индивидуальное задание
	string s;
	regex mask(".+Physics: [78].+Maths: (?:9|10).+");
	ifstream DAT_READ(IN);
	ofstream DAT_WRITE(OUT);
	while (getline(DAT_READ, s)) {
		cout << s << endl;
		DAT_WRITE << s << endl;
	}
	DAT_READ.close();
	DAT_WRITE.close();
	ifstream DAT_DOREAD(IN);
	ofstream DAT_DOWRITE(OUT, ios_base::app);
	cout << "Individual problem solved:\n";
	DAT_DOWRITE << "Individual problem solved:\n";
	while (getline(DAT_DOREAD, s)) {
		if (regex_match(s, mask)) {
			cout << s << endl << "----------" << endl;
			DAT_DOWRITE << s << endl << "----------" << endl;
		}
	}
}

void main() {
	int nSwitch; //переменная для свитча
	int nNextStud; //номер следующего студента
	GET_N_NEXT_STUD(nNextStud); //получить номер следующего студента при запуске программы
	while (true) {
		cout << "Create - 1\nView - 2\nAdd - 3\nEdit - 4\nIndividual Problem - 5\nExit - 0\n";
		cin >> nSwitch;
		switch (nSwitch) {
		case 0: //выход
			return;
		case 1:
			DATA_CREATE(); //создать файл если он не создан либо его перезапись
			nNextStud = 1;
			break;
		case 2:
			DATA_SHOW(); //показать имеющихся студентов
			break;
		case 3:
			DATA_ADD_STUD(nNextStud); // добавить следующего студента
			break;
		case 4:
			DATA_EDIT(); //изменить студента под номером, который введет пользователь
			break;
		case 5:
			DATA_OUT();
			break;
		default: cout << "Incorrect input!";
		}
	}
}