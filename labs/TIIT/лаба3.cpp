#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using std::cout;
using std::cin;
using std::vector;
using std::string;
using std::endl;

struct operable_set {
	string s;
	int count;
};

void clear() {
	int c;
	while ((c = getchar()) != '\n' && c != EOF);
}

vector<vector<operable_set>> Convert(vector<vector<string>> sets) {
	operable_set elem;
	vector<operable_set> set;
	vector<vector<operable_set>> vector_of_sets;
	bool in_set;
	int num_of_brackets = 0;
	for (int i = 0; i < sets.size(); i++) {
		elem.count = 0;
		for (int k = 0; k < sets[i].size(); k++) {
			int kod = 0;
			in_set = false;
			elem.s = sets[i][k];
			if (elem.s[0] == '{') {
				for (int o = 0; o < elem.s.size(); o++) {
					if (elem.s[o] == '{') num_of_brackets++;
					if (elem.s[o] == '}') num_of_brackets--;
					kod += elem.s[o] * num_of_brackets;
				}
			}
			if (!set.empty()) {
				if (elem.s[0] != '{') {
					for (int l = 0; l < set.size(); l++) {
						if (elem.s == set[l].s) {
							in_set = true;
							break;
						}
					}
				}
				else {
					int kod1 = 0;
					for (int o = 0; o < set.size(); o++) {
						for (int l = 0; l < set[o].s.size(); l++) {
							if (set[o].s[l] == '{') num_of_brackets++;
							if (set[o].s[l] == '}') num_of_brackets--;
							kod1 += set[o].s[l] * num_of_brackets;
						}
						if (kod == kod1) {
							in_set = true;
							set[o].count++;
							kod1 = 0;
						}
						kod1 = 0;
					}
				}
			}
			if (in_set == false) {
				if (kod == 0) {
					for (int q = k; q < sets[i].size(); q++) {
						if (elem.s == sets[i][q]) elem.count++;
					}
				}
				else {
					elem.count++;
				}
				set.push_back(elem);
				elem.count = 0;
				elem.s = "";
			}
		}
		vector_of_sets.push_back(set);
		set.clear();
	}
	return vector_of_sets;
}

vector<operable_set> Symmetric_Difference(vector<vector<operable_set>> set) {
	operable_set elem;
	vector<operable_set> symmetric_diff;
	vector<int> count_diff;
	int n_maxcount;
	bool in_set = false, curly_elem = false;
	int kod = 0, kod1 = 0, num_of_brackets = 0;
	for (int i = 0; i < set.size(); i++) {
		if (i == set.size() - 1) {
			for (int k = 0; k < set[i].size(); k++) {
				for (unsigned m = 0; m < symmetric_diff.size(); m++) { //check for alrdy being in set FOR THE LAST SET
					if (set[i][k].s[0] == '{') {
						curly_elem = true;
						kod = 0;
						for (int b = 0; b < set[i][k].s.size(); b++) {
							if (set[i][k].s[b] == '{') num_of_brackets++;
							if (set[i][k].s[b] == '}') num_of_brackets--;
							kod += set[i][k].s[b] * num_of_brackets;
						}
					}
					if (!curly_elem) {
						if (set[i][k].s == symmetric_diff[m].s) {
							in_set = true;
							break;
						}
					}
					else {
						kod1 = 0;
						for (int z = 0; z < symmetric_diff[m].s.size(); z++) {
							if (symmetric_diff[m].s[z] == '{') num_of_brackets++;
							if (symmetric_diff[m].s[z] == '}') num_of_brackets--;
							kod1 += symmetric_diff[m].s[z] * num_of_brackets;
						}
						if (kod == kod1) {
							in_set = true;
							break;
						}
					}
				} //end of check
				if (!in_set) { //if elem is not in set
					symmetric_diff.push_back(set[i][k]);
				}
				in_set = false;
				curly_elem = false;
				kod = kod1 = 0;
			}
			break;
		} // for sets N - 1, N - num of sets
		for (int k = 0; k < set[i].size(); k++) {
			for (unsigned m = 0; m < symmetric_diff.size(); m++) { //check for alrdy being in symm_diff
				if (set[i][k].s[0] == '{') {
					curly_elem = true;
					kod = 0;
					for (int b = 0; b < set[i][k].s.size(); b++) {
						if (set[i][k].s[b] == '{') num_of_brackets++;
						if (set[i][k].s[b] == '}') num_of_brackets--;
						kod += set[i][k].s[b] * num_of_brackets;
					}
				}
				if (!curly_elem) {
					if (set[i][k].s == symmetric_diff[m].s) {
						in_set = true;
						break;
					}
				}
				else {
					kod1 = 0;
					for (int z = 0; z < symmetric_diff[m].s.size(); z++) {
						if (symmetric_diff[m].s[z] == '{') num_of_brackets++;
						if (symmetric_diff[m].s[z] == '}') num_of_brackets--;
						kod1 += symmetric_diff[m].s[z] * num_of_brackets;
					}
					if (kod == kod1) {
						in_set = true;
						break;
					}
				}
			} //end of check
			kod = kod1 = 0;
			curly_elem = false;
			if (!in_set) { //IF NOT IN SYMM_DIFF
				for (int q = i + 1; q < set.size(); q++) {
					for (int p = 0; p < set[q].size(); p++) {
						if (set[i][k].s[0] == '{') { // IF ELEMENT IS A SET ITSELF
							curly_elem = true;
							kod = 0;
							for (int b = 0; b < set[i][k].s.size(); b++) {
								if (set[i][k].s[b] == '{') num_of_brackets++;
								if (set[i][k].s[b] == '}') num_of_brackets--;
								kod += set[i][k].s[b] * num_of_brackets;
							}
						}
						if (!curly_elem) { //IF ELEMENT IS NOT A SET ITSELF
							if (set[i][k].s == set[q][p].s) {
								in_set = true;
								count_diff.push_back(fabs(set[i][k].count - set[q][p].count));
								break;
							}
							else if (set[i][k].s != set[q][set[q].size() - 1].s && !in_set) {
								count_diff.push_back(set[i][k].count);
							}
							in_set = false;
						}
						else { // IF ELEMENT IS A SET ITSELF
							kod1 = 0;
							for (int z = 0; z < set[q][p].s.size(); z++) {
								if (set[q][p].s[z] == '{') num_of_brackets++;
								if (set[q][p].s[z] == '}') num_of_brackets--;
								kod1 += set[q][p].s[z] * num_of_brackets;
							}
							if (kod == kod1) {
								in_set = true;
								count_diff.push_back(fabs(set[i][k].count - set[q][p].count));
								break;
							}
							if (kod != kod1 & in_set) {
								count_diff.push_back(set[i][k].count);
								in_set = false;
								break;
							}
						}
					}
				}
				if (!count_diff.empty()) {
					n_maxcount = count_diff[0];
					for (unsigned int i = 0; i < count_diff.size(); i++) {
						if (count_diff[i] > n_maxcount) n_maxcount = count_diff[i];
					}
				}
				else {
					n_maxcount = set[i][k].count;
				}
				elem.count = n_maxcount;
				elem.s = set[i][k].s;
				symmetric_diff.push_back(elem);
				count_diff.clear();
			}
			in_set = false;
			curly_elem = false;
			kod = kod1 = 0;
		}
	}
	return symmetric_diff;
}

int main() {
	vector<vector<string>> sets;
	vector<string> elems_of_set;
	string elem, new_set;
	vector<vector<operable_set>> work_set;
	vector<operable_set> symmetric_difference;
	bool in_set = false;
	while (true) {
		int nswitch;
		cout << "1) Add set\n2) Symmetric DIfference\n3) Exit\n";
		cin >> nswitch;
		switch (nswitch) {
		case 1:
			cout << "New set: ";
			clear();
			std::getline(cin, new_set);
			for (int i = 0; i < new_set.size(); i++) {
				while (new_set[i] == ' ') i++;
				while (new_set[i] != '{' && in_set == false) {
					i++;
				}
				if (new_set[i] == '{' && in_set == false) {
					in_set = true;
					i++;
				}
				while (new_set[i] != ',' && new_set[i] != '}' && new_set[i] != '{' && new_set[i] != '<') {
					elem += new_set[i];
					i++;
				}
				if (new_set[i] == '<') {
					int num_of_brackets = 0;
					do {
						if (new_set[i] == '<') num_of_brackets++;
						if (new_set[i] == '>') num_of_brackets--;
						elem += new_set[i];
						i++;
					} while (num_of_brackets != 0);
				}
				if (in_set == true && new_set[i] == '{') {
					int num_of_brackets = 0;
					do {
						if (new_set[i] == '}') num_of_brackets--;
						if (new_set[i] == '{') num_of_brackets++;
						elem += new_set[i];
						i++;
					} while (num_of_brackets != 0);
				}
				elems_of_set.push_back(elem);
				elem = "";
			}
			sets.push_back(elems_of_set);
			new_set = "";
			elems_of_set.clear();
			in_set = false;
			break;
		case 2:
			work_set = Convert(sets);
			symmetric_difference = Symmetric_Difference(work_set);
			cout << "Symmetric Difference: {";
			for (unsigned int i = 0; i < symmetric_difference.size(); i++) {
				if (symmetric_difference[i].count == 0) {
					symmetric_difference.erase(symmetric_difference.begin() + i);
					i--;
				}
			}
			for (unsigned int i = 0; i < symmetric_difference.size(); i++) {
				for (int k = 0; k < symmetric_difference[i].count; k++) {
					cout << symmetric_difference[i].s;
					if (!(i == symmetric_difference.size() - 1 && k == symmetric_difference[i].count - 1)) cout << ", ";
				}
			}
			cout << "}\n";
			break;
		case 3:
			return 0;
		default:
			cout << "Incorrect input.\n";
			break;
		}
	}
	return 0;
}