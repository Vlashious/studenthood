#pragma once
#include <iostream>
#include <string>

using std::cout;
using std::endl;
using std::string;

namespace Knihi {

	class Kniha {

	public:

		Kniha(int, string);

		virtual void itIs();

		string getAuthor();

		int getNumOfPages();

	protected:

		int numOfPages;
		string authorName;
	};

	class MastackajaLitaratura : public virtual Kniha {

	public:

		MastackajaLitaratura(int pages, string author, string genre) : Kniha(pages, author) {
			bookGenre = genre;
			cout << u8"Stvareńnie klasa Mastackaj Litaratury!\n";
		}

		void itIs() override;

		string getGenre();

	protected:

		string bookGenre;
	};

	class NavukovajaLitaratura : public virtual Kniha {

	public:

		NavukovajaLitaratura(int pages, string author, string genre) : Kniha(pages, author) {
			bookGenre = genre;
			cout << u8"Stvareńnie klasa Navukovaj Litaratury!\n";
		}

		void itIs() override;

		string getGenre();

	protected:

		string bookGenre;

	};

	class NavukovaMastackajaZanra : protected MastackajaLitaratura, private NavukovajaLitaratura {

	public:

		NavukovaMastackajaZanra(int pagesMast, string authorMast, string genreMast, int pagesNav, string authorNav, string genreNav) :
			MastackajaLitaratura(pagesMast, authorMast, genreMast),
			NavukovajaLitaratura(pagesNav, authorNav, genreNav),
			Kniha(pagesMast + pagesNav, authorMast + " dy " + authorNav) {
			bookQuality = (float)pagesNav / pagesMast;
			cout << u8"Stvareńnie klasa navukova-mastackaj litaratury!\n";
		}

		void itIs() override;

		int getProtectedNumOfPages();

		string getProtectedAuthor();

		string getProtectedGenre();

		float getQuality();

	private:
		float bookQuality;
	};

}