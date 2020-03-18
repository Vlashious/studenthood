#include "Classes.h"
#include <io.h>
#include <Windows.h>

using namespace Knihi;

int main() {
	SetConsoleOutputCP(65001);
	Kniha kniha(100, u8"Jakub Kołas");
	kniha.itIs();
	cout << u8"Kolkaść staronak: " << kniha.getNumOfPages() << "\n";
	cout << u8"Aŭtar: " << kniha.getAuthor() << "\n";

	cout << endl;

	MastackajaLitaratura kniha1(200, u8"Maksym Bahdanovič", u8"Apovieść");
	kniha1.itIs();
	cout << u8"Kolkaść staronak: " << kniha1.getNumOfPages() << "\n";
	cout << u8"Aŭtar: " << kniha1.getAuthor() << "\n";
	cout << u8"Žanra knihi: " << kniha1.getGenre() << "\n";

	cout << endl;

	NavukovajaLitaratura kniha2(333, u8"Vital Hałubovič", "Historyja");
	kniha2.itIs();
	cout << u8"Kolkaść staronak: " << kniha2.getNumOfPages() << "\n";
	cout << u8"Aŭtar: " << kniha2.getAuthor() << "\n";
	cout << u8"Žanra knihi: " << kniha2.getGenre() << "\n";

	cout << endl;

	NavukovaMastackajaZanra kniha3(450, u8"Janka Kupała", u8"Paema", 300, u8"Natalla Hardzijenka", u8"Matematyka");
	kniha3.itIs();
	cout << u8"Kolkaść staronak: " << kniha3.getProtectedNumOfPages() << "\n";
	cout << u8"Aŭtar: " << kniha3.getProtectedAuthor() << "\n";
	cout << u8"Žanra knihi: " << kniha3.getProtectedGenre() << "\n";
	cout << u8"Jakaść knihi: " << kniha3.getQuality() << "\n";

	system("pause");

	return 0;
}