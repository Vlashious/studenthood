#include "Classes.h"

using namespace Knihi;

	Kniha::Kniha(int pages, string author) {
		numOfPages = pages;
		authorName = author;
		cout << u8"Stvareńnie klasa zvyčajnaj knihi!\n";
	}

	void Kniha::itIs() {
		cout << u8"Heta zvyčajnaja kniha!\n";
	}

	string Kniha::getAuthor() {
		return authorName;
	}

	int Kniha::getNumOfPages() {
		return numOfPages;
	}

	void MastackajaLitaratura::itIs() {
		cout << u8"Heta mastackaja litaratura!\n";
	}

	string MastackajaLitaratura::getGenre() {
		return bookGenre;
	}

	void NavukovajaLitaratura::itIs() {
		cout << u8"Heta navukovaja litaratura!\n";
	}

	string NavukovajaLitaratura::getGenre() {
		return bookGenre;
	}

	void NavukovaMastackajaZanra::itIs() {
		cout << u8"Heta kniha navukova-papularnaha žanru!\n";
	}

	int NavukovaMastackajaZanra::getProtectedNumOfPages() {
		return numOfPages;
	}

	string NavukovaMastackajaZanra::getProtectedAuthor() {
		return authorName;
	}

	string NavukovaMastackajaZanra::getProtectedGenre() {
		return MastackajaLitaratura::bookGenre + " dy " + NavukovajaLitaratura::bookGenre;
	}

	float NavukovaMastackajaZanra::getQuality() {
		return bookQuality;
	}