#pragma once
#include <fstream>
#include <vector>
#include "Field.h"
class FileLoad {
public:
	FileLoad(std::string fileName) {
		file.open(fileName);
	}
	Field createFieldFromFile(int playStyle) {
		int x, y;
		int numOct, numBiv, numPlan;
		file >> x >> y >> numOct >> numBiv >> numPlan;
		Field newGame(x, y, playStyle);
		newGame.Inhabit(numOct, numBiv, numPlan, 0);
		newGame.Inhabit(numOct, numBiv, numPlan, 1);
		newGame.InhabitField();
		return newGame;
	}
	void closeFile() {
		file.close();
	}
protected:
	std::ifstream file;
};