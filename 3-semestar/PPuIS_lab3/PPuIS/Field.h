#pragma once
#include "Habitants.h"

class Field{

public:

	Field(int width, int height, int playStyle) {
		vector<vector<shared_ptr<Animals>>> row(width);
		for (int i = 0; i < height; i++) {
			gameField.push_back(row);
		}
		Field::width = width;
		Field::height = height;
		Field::playStyle = playStyle;
	}

	void printField() {
		int numOfSpaces = 4;
		for (int i = 0; i < gameField.size(); i++) {
			for (int j = 0; j < gameField[i].size(); j++) {
				if (gameField[i][j].empty()) {
					std::cout << "[    ]";
				}
				else {
					std::cout << "[";
					for (int k = 0; k < gameField[i][j].size(); k++) {
						if (gameField[i][j][k]->getLifePoints() > 0) {
							std::cout << gameField[i][j][k]->getAnimalName();
							numOfSpaces--;
						}
					}
					for (int k = 0; k < numOfSpaces; k++) {
						std::cout << " ";
					}
					numOfSpaces = 4;
					std::cout << "]";
				}
			}
			std::cout << std::endl;
		}
	}

	void Inhabit(int numOct, int numBiv, int intPlan, bool sex) {
		for (int i = 0; i < numOct; i++) {
			shared_ptr<Animals> O = std::make_shared<Octopus>(sex);
			//weak_ptr<Animals> o = O;
			animalsOnField.push_back(O);
		}
		for (int i = 0; i < numBiv; i++) {
			shared_ptr<Animals> O = std::make_shared<Bivalvia>(sex);
			//weak_ptr<Animals> o = O;
			animalsOnField.push_back(O);
		}
		for (int i = 0; i < intPlan; i++) {
			shared_ptr<Animals> O = std::make_shared<Plankton>();
			//weak_ptr<Animals> o = O;
			animalsOnField.push_back(O);
		}
	}

	void InhabitField() {
		int x, y;
		for (int i = 0; i < animalsOnField.size(); i++) {
			y = rand() % height;
			x = rand() % width;
			for (int k = 0; k < gameField[y][x].size(); k++) {
				if (!gameField[y][x].empty()) {
					if (gameField[y][x][k]->getAnimalName() == animalsOnField[i]->getAnimalName()) {
						y = rand() % height;
						x = rand() % width;
						k = -1;
						continue;
					}
				}
				else {
					break;
				}
			}
			gameField[y][x].push_back(animalsOnField[i]);
		}
	}

	void playerTurn() {
		int choiceNum;
		int SIZE = animalsOnField.size();
		if (animalsOnField.empty()) {
			cout << u8"Hulna skončyłasia, usie pamiorli :(\n";
			return;
		}
		for (int j = 0; j < SIZE; j++) {
			damageEveryAnimal();
			if (animalsOnField.empty()) {
				cout << u8"Hulna skončyłasia, usie pamiorli :(\n";
				return;
			}
			int MOVEMENT = animalsOnField[j]->getMovementPoints();
			for (int i = 0; i < MOVEMENT; i++) {
				if (animalsOnField[j]->getLifePoints() <= 0) {
					cout << u8"Žyvioła " << animalsOnField[j]->getAnimalName() << u8" pamierła.\n";
					continue;
				}
				vector<int> position = getAnimalPosition(animalsOnField[j]);
				system("pause");
				system("cls");
				printField();
				cout << "Vy kirujecie: " << animalsOnField[j]->getAnimalName() << "(" << position[0] << ", " << position[1] << ")\n";
				cout << u8"Zastaŭšysia čas: " << animalsOnField[j]->getLifePoints() << "\n";
				//if (currentAnimal->getLifePoints() == 0) {
				//cout << animalsOnField[j].use_count();
				//}
				cout << u8"Zastaŭšajasia kolkaść padziej: " << animalsOnField[j]->getMovementPoints() - i << "\n";
				cout << u8"Što vy chočacie zrabić?\n1) Chadzić!\n2) Jeści!\n3) Razmnažacca!\n";
				if (playStyle) {
					srand(time(NULL));
					choiceNum = rand() % 3 + 1;
				}
				else {
					cin >> choiceNum;
				}
				switch (choiceNum) {
				case 1:
					playerTurnMove(animalsOnField[j]);
					break;
				case 2:
					playerTurnEat(animalsOnField[j]);
					//i = animalsOnField[j]->getMovementPoints();
					break;
				case 3:
					playerTurnDuplicate(animalsOnField[j]);
					//i = animalsOnField[j]->getMovementPoints();
					break;
				}
			}
		}
		checkDiedAnimals();
	}

	void playerTurnMove(shared_ptr<Animals> currentAnimal) {
		int x1, y1;
		int x, y, l;
		vector<int> position = getAnimalPosition(currentAnimal);
		x = position[0];
		y = position[1];
		for (int i = 0; i < gameField[y][x].size(); i++) {
			if (gameField[y][x][i] == currentAnimal) {
				l = i;
				break;
			}
		}
		vector<vector<int>> possibleLocation = { {x - 1, y}, {x, y - 1}, {x, y + 1}, {x + 1, y} };
		int whereToGo;
		cout << u8"Vy znachodziciesia ŭ lakacyji (" << x << ", " << y << ")\n";
		cout << u8"Mahčymyja lakacyji: ";
		for (int i = 0; i < possibleLocation.size(); i++) {
			if (possibleLocation[i][0] >= 0 && possibleLocation[i][1] >= 0) {
				if (possibleLocation[i][0] < width && possibleLocation[i][1] < height) {
					if (gameField[possibleLocation[i][1]][possibleLocation[i][0]].size() < 4) {
						cout << i << ") (" << possibleLocation[i][0] << ", " << possibleLocation[i][1] << ")\n";
					}
				}
			}
		}
		cout << u8"Kudy pajści?\n";
		if (playStyle) {
			x1 = rand() % width;
			y1 = rand() % height;
		}
		else {
			cin >> whereToGo;
		}
		if (!playStyle) {
			if (currentAnimal->getAnimalName() == "P") {
				for (int i = 0; i < gameField[possibleLocation[whereToGo][1]][possibleLocation[whereToGo][0]].size(); i++) {
					if (gameField[possibleLocation[whereToGo][1]][possibleLocation[whereToGo][0]][i]->getAnimalName() == "P") {
						cout << u8"Vy nia možacie tudy pajśći!\n";
						return;
					}
				}
			}
		}
		else {
			if (currentAnimal->getAnimalName() == "P") {
				for (int i = 0; i < gameField[y1][x1].size(); i++) {
					if (gameField[y1][x1][i]->getAnimalName() == "P") {
						cout << u8"Vy nia možacie tudy pajśći!\n";
						return;
					}
				}
			}
		}
		if (!playStyle) {
			gameField[possibleLocation[whereToGo][1]][possibleLocation[whereToGo][0]].push_back(currentAnimal);
			gameField[y][x].erase(gameField[y][x].begin() + l);
		}
		else {
			gameField[y1][x1].push_back(currentAnimal);
			gameField[y][x].erase(gameField[y][x].begin() + l);
		}
	}

	void playerTurnEat(weak_ptr<Animals> currentAnimal) {
		vector<int> position = getAnimalPosition(currentAnimal);
		int x = position[0];
		int y = position[1];
		string isFeedingOn;
		if (currentAnimal.lock()->getAnimalName() == "O") {
			isFeedingOn = "B";
		}
		else if (currentAnimal.lock()->getAnimalName() == "B") {
			isFeedingOn = "P";
		}
		else if (currentAnimal.lock()->getAnimalName() == "P") {
			cout << u8"Planktonu nie patrebna ježa!\n";
			return;
		}
		for (int i = 0; i < gameField[y][x].size(); i++) {
			if (gameField[y][x][i]->getAnimalName() == isFeedingOn) {
				currentAnimal.lock()->Eat();
				for (int k = 0; k < animalsOnField.size(); k++) {
					weak_ptr<Animals> animalOnField(animalsOnField[k]);
					weak_ptr<Animals> GAMEFIELD(gameField[y][x][i]);
					if (*animalOnField.lock() == *GAMEFIELD.lock()) {
						animalsOnField.erase(animalsOnField.begin() + k);
						gameField[y][x].erase(gameField[y][x].begin() + i);
						break;
					}
				}
				return;
			}
		}
		cout << u8"Tut niama ježy!\n";\
	}

	void playerTurnDuplicate(weak_ptr<Animals> currentAnimal) {
		int x, y;
		int x1, y1;
		vector<int> position = getAnimalPosition(currentAnimal);
		x = position[0];
		y = position[1];
		if (gameField[y][x].size() == 4) {
			cout << "Niama miesca!\n";
			return;
		}
		string name = currentAnimal.lock()->getAnimalName();
		if (name == "O") {
			for (int i = 0; i < gameField[y][x].size(); i++) {
				if (gameField[y][x][i]->getAnimalName() == name) {
					bool canReproduce = (gameField[y][x][i]->getAnimalSex() != currentAnimal.lock()->getAnimalSex());
					if (canReproduce) {
						shared_ptr<Animals> childishAnimal(currentAnimal.lock()->Duplicate());
						weak_ptr<Animals> childAnimal(childishAnimal);
						animalsOnField.push_back(childAnimal.lock());
						gameField[y][x].push_back(childAnimal.lock());
						return;
					}
				}
			}
		}
		else if (name == "B") {
			for (int i = 0; i < gameField[y][x].size(); i++) {
				if (gameField[y][x][i]->getAnimalName() == name) {
					bool canReproduce = (gameField[y][x][i]->getAnimalSex() != currentAnimal.lock()->getAnimalSex());
					if (canReproduce) {
						//weak_ptr<Animals> childAnimal(currentAnimal.lock()->Duplicate());
						shared_ptr<Animals> childishAnimal(currentAnimal.lock()->Duplicate());
						weak_ptr<Animals> childAnimal(childishAnimal);
						animalsOnField.push_back(childAnimal.lock());
						gameField[y][x].push_back(childAnimal.lock());
						return;
					}
				}
			}
		}
		else if (name == "P") {
			int choiceNum;
			vector<vector<int>> possibleLocation = { {y, x - 1}, {y - 1, x}, {y + 1, x}, {y, x + 1} };
			cout << u8"Mahčymyja lakacyji dla razmnažeńnia:\n";
			for (int i = 0; i < possibleLocation.size(); i++) {
				if (possibleLocation[i][0] >= 0 && possibleLocation[i][1] >= 0 && possibleLocation[i][0] < width && possibleLocation[i][1] < height) {
					cout << i << ") " << "(" << possibleLocation[i][1] << ", " << possibleLocation[i][0] << ")\n";
				}
			}
			cout << u8"Kudy razmnožycca?";
			if (playStyle) {
				x1 = rand() % width;
				y1 = rand() % height;
			}
			else {
				cin >> choiceNum;
			}
			if (!playStyle) {
				if (!gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]].empty()) {
					for (int i = 0; i < gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]].size(); i++) {
						if (gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->getAnimalName() == name) {
							//cout << u8"Było žyćcia: " << gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->getLifePoints();
							gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->setPlanktonLife();
							//cout << u8"Stała žyćcia: " << gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->getLifePoints();
							return;
						}
					}
				}
			}
			else {
				if (!gameField[y1][x1].empty()) {
					for (int i = 0; i < gameField[y1][x1].size(); i++) {
						if (gameField[y1][x1][i]->getAnimalName() == name) {
							//cout << u8"Było žyćcia: " << gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->getLifePoints();
							gameField[y1][x1][i]->setPlanktonLife();
							//cout << u8"Stała žyćcia: " << gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]][i]->getLifePoints();
							return;
						}
					}
				}
			}
			//weak_ptr<Animals> childAnimal(currentAnimal.lock()->Duplicate());
			if (!playStyle) {
				shared_ptr<Animals> childishAnimal(currentAnimal.lock()->Duplicate());
				weak_ptr<Animals> childAnimal(childishAnimal);
				animalsOnField.push_back(childAnimal.lock());
				gameField[possibleLocation[choiceNum][0]][possibleLocation[choiceNum][1]].push_back(childAnimal.lock());
				return;
			}
			else {
				shared_ptr<Animals> childishAnimal(currentAnimal.lock()->Duplicate());
				weak_ptr<Animals> childAnimal(childishAnimal);
				animalsOnField.push_back(childAnimal.lock());
				gameField[y1][x1].push_back(childAnimal.lock());
			}
		}
		
	}

protected:

	int width, height;
	vector<vector<vector<shared_ptr<Animals>>>> gameField;
	vector<shared_ptr<Animals>> animalsOnField;
	bool playStyle;

	vector<int> getAnimalPosition(weak_ptr<Animals> currentAnimal) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < gameField[i][j].size(); k++) {
					if (currentAnimal.lock() == gameField[i][j][k]) {
						return { j, i, k };
					}
				}
			}
		}
	}

	void checkDiedAnimals() {
		for (int j = 0; j < animalsOnField.size(); j++) {
			if (animalsOnField[j]->getLifePoints() <= 0) {
				vector<int> position = getAnimalPosition(animalsOnField[j]);
				cout << animalsOnField[j]->getAnimalName() << u8" pamior u lakacyji (" << position[0] << ", " << position[1] << ")\n";
				animalsOnField.erase(animalsOnField.begin() + j);
				gameField[position[1]][position[0]].erase(gameField[position[1]][position[0]].begin() + position[2]);
				j--;
			}
		}
	}

	void damageEveryAnimal() {
		for (int i = 0; i < animalsOnField.size(); i++) {
			animalsOnField[i]->Diyng();
		}
	}
};