#include "Field.h"
#include "File_Loader.h"
int main(int argc, char* argv[]) {

	SetConsoleOutputCP(65001);
	string fileName = argv[1];
	FileLoad file(fileName);

	srand(time(NULL));
	int choiceNum;
	string pl = argv[2];
	int playStyle = std::stoi(pl);
	Field game1 = file.createFieldFromFile(playStyle);
	file.closeFile();
	game1.printField();

	while (true) {
		cout << u8"Chočacie zhulać nastupnuju partyju?\n1) Tak!\n2) Nie!\n";
		cin >> choiceNum;
		switch (choiceNum) {
		case 1:
			game1.playerTurn();
			break;
		case 2:
			cout << u8"Prychodźcie jašče!\nDziakuj za hulnu.\n";
			system("pause");
			return 0;
		}
	}

	system("pause");

	return 0;
}