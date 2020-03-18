#pragma once

#include <iostream>
#include <vector>
#include <string>
#include <Windows.h>
#include <time.h>
#include <iomanip>
#include <memory>
#include <fstream>

using std::string;
using std::vector;
using std::cout;
using std::cin;
using std::weak_ptr;
using std::shared_ptr;

const int PLANKTON_LIFE = 10;
const int OCTOPUS_LIFE = 16;
const int BIVALVIA_LIFE = 12;

const int PLANKTON_SIZE = 0;
const int OCTOPUS_SIZE = 2;
const int BIVALVIA_SIZE = 1;

const int PLANKTON_MOVE = 1;
const int OCTOPUS_MOVE = 3;
const int BIVALVIA_MOVE = 2;

class Animals {

public:

	friend bool operator==(Animals &lhs, Animals &rhs) {
		if (lhs.animalName == rhs.animalName && lhs.animalSex == rhs.animalSex && lhs.animalSize == rhs.animalSize && lhs.lifePoints == rhs.lifePoints) return true;
		else return false;
	}

	Animals(int movement = 0, int size = 0, int life = 0, string name = 0, bool sex = 0) {

		movementPoints = movement;
		animalSize = size;
		lifePoints = life;
		animalName = name;
		animalSex = sex;

	}

	int getMovementPoints() {

		return movementPoints;

	}

	int getAnimalSize() {

		return animalSize;

	}

	int getLifePoints() {

		return lifePoints;

	}

	string getAnimalName() {

		return animalName;

	}

	bool getAnimalSex() {

		return animalSex;

	}

	void Eat() {
		lifePoints += 5;
	}

	void setPlanktonLife() {
		lifePoints = PLANKTON_LIFE;
	}

	void Diyng() {
		lifePoints = lifePoints - 1 - animalSize;
	}

	virtual bool isPredator() {
		return false;
	}

	virtual Animals *Duplicate() const {
		return new Animals;
	}

protected:

	int movementPoints;
	int animalSize;
	int lifePoints;
	string animalName;
	bool animalSex;

};

class Octopus: public Animals {

public:

	Octopus(bool sex) : Animals(OCTOPUS_MOVE, OCTOPUS_SIZE, OCTOPUS_LIFE, "O", sex) {
		
	}

	bool isPredator() {
		return true;
	}

	virtual Octopus *Duplicate() const override {
		return new Octopus(rand());
	}

};

class Bivalvia : public Animals {

public:

	Bivalvia(bool sex) : Animals(BIVALVIA_MOVE, BIVALVIA_SIZE, BIVALVIA_LIFE, "B", sex) {
		
	}

	bool isPredator() {
		return false;
	}

	virtual Bivalvia *Duplicate() const override {
		return new Bivalvia(rand());
	}

};

class Plankton: public Animals{

public:
	
	Plankton() : Animals(PLANKTON_MOVE, PLANKTON_SIZE, PLANKTON_LIFE, "P", 0) {

	}

	virtual Plankton *Duplicate() const override {
		return new Plankton;
	}

};