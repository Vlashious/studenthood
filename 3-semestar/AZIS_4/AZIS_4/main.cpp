//#include "stdafx.h"
#include <iostream>
#include <vector>
#include <string>

using namespace std;

struct table
{
	bool q1_p;
	bool q2_p;
	bool q3_p;

	bool V;

	bool q1;
	bool q2;
	bool q3;

	bool h1;
	bool h2;
	bool h3;
};

vector <table> init_table();
vector <bool> to_bin(int);
void print_table(vector <table>);
string glued(string);
string four_glued(string);
void minimized(vector <table>);

int main()
{
	cout << '\t' << '\t' << '\t' << '\t' << "Variant 1" << endl << endl;
	vector <table> tbl = init_table();
	print_table(tbl);
	cout << endl;
	cout << "a = q1*, b = q2*, c = q3*, d = V" << endl << endl;
	minimized(tbl);
	cout << "P DKNF: (x1+x2+x3)*(x1+x2+!x3)*(x1+!x2+!x3)*(!x1+x2+x3)\n";
	cout << "S DKNF: (x1+x2+x3)*(x1+!x2+!x3)*(!x1+x2+!x3)*(!x1+!x2+x3)\n";
	cout << "P: (x1+x2)*(x1+x3)*(x2+x3)\n";
	cout << "S: (x1+x2+x3)*(x1+!x2+!x3)*(!x1+x2+!x3)*(!x1+!x2+x3)\n";
	cout << "y1 = x1\n";
	cout << "y2 = x2\n";
	cout << "y3 = x3\n";
	cout << "y4 = x4\n";
	system("pause");
	return 0;
}

vector <bool> to_bin(int num)
{
	vector <bool> res;
	int num_;
	num_ = abs(num);

	while (num_)
	{
		res.emplace(res.begin(), num_ % 2);
		num_ /= 2;
	}
	if (num == 0)
	{
		res = { 0, 0, 0 };
	}
	size_t size = res.size();
	for (size_t i = 0; i < 3 - size; i++)
	{
		res.emplace(res.begin(), 0);
	}
	return res;
}
vector <bool> addition(vector <bool> a, vector <bool> b)
{
	vector <bool> res;
	bool trans = false;
	for (size_t i = a.size(); i > 0; i--)
	{
		if (a[i - 1] == 0 && b[i - 1] == 0 && trans == false)
		{
			res.emplace(res.begin(), 0);
			trans = false;
		}
		else if (a[i - 1] == 0 && b[i - 1] == 0 && trans == true)
		{
			res.emplace(res.begin(), 1);
			trans = false;
		}
		else if (a[i - 1] == 1 && b[i - 1] == 0 && trans == false)
		{
			res.emplace(res.begin(), 1);
			trans = false;
		}
		else if (a[i - 1] == 1 && b[i - 1] == 0 && trans == true)
		{
			res.emplace(res.begin(), 0);
			trans = true;
		}
		else if (a[i - 1] == 0 && b[i - 1] == 1 && trans == false)
		{
			res.emplace(res.begin(), 1);
			trans = false;
		}
		else if (a[i - 1] == 0 && b[i - 1] == 1 && trans == true)
		{
			res.emplace(res.begin(), 0);
			trans = true;
		}
		else if (a[i - 1] == 1 && b[i - 1] == 1 && trans == false)
		{
			res.emplace(res.begin(), 0);
			trans = true;
		}
		else if (a[i - 1] == 1 && b[i - 1] == 1 && trans == true)
		{
			res.emplace(res.begin(), 1);
			trans = true;
		}
	}
	return res;
}
vector <table> init_table()
{
	vector <table> out;
	vector <bool> decBin_q_p;
	size_t iter = 0;
	bool v = true;

	for (size_t i = 0; i < 16; i++)
	{
		if (i % 2 == 0 && i != 0)
		{
			iter++;
		}
		decBin_q_p = to_bin(iter);

		if (v)
		{
			v = false;
		}
		else
		{
			v = true;
		}

		vector <bool> one = { false, false , v };
		vector <bool> decBin_q = addition(decBin_q_p, one);
		bool h1 = false;
		bool h2 = false;
		bool h3 = false;

		if (decBin_q[0] != decBin_q_p[0])
		{
			h1 = true;
		}
		if (decBin_q[1] != decBin_q_p[1])
		{
			h2 = true;
		}
		if (decBin_q[2] != decBin_q_p[2])
		{
			h3 = true;
		}

		table boof = { decBin_q_p[0], decBin_q_p[1], decBin_q_p[2], v,
			decBin_q[0], decBin_q[1], decBin_q[2], h1, h2, h3 };
		out.push_back(boof);
	}
	return out;
}
void print_table(vector <table> tbl)
{
	cout << "#" << "       " << "1" << "    " << "2" << "    " << "3"
		<< "    " << "4" << "    " << "5" << "    " << "6" << "    "
		<< "7" << "    " << "8" << "    " << "9" << "    " << "10"
		<< "   " << "11" << "   " << "12" << "   " << "13" <<
		"   " << "14" << "   " << "15" << "   " << "16" << "    ";
	cout << endl << "_____________________________________________________________________________________";
	cout << endl << "q1_p" << "    ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q1_p << "    ";
	}
	cout << endl << "q2_p" << "    ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q2_p << "    ";
	}
	cout << endl << "q3_p" << "    ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q3_p << "    ";
	}
	cout << endl << "_____________________________________________________________________________________";
	cout << endl << "V" << "       ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].V << "    ";
	}
	cout << endl << "_____________________________________________________________________________________";
	cout << endl << "q1" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q1 << "    ";
	}
	cout << endl << "q2" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q2 << "    ";
	}
	cout << endl << "q3" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].q3 << "    ";
	}
	cout << endl << "_____________________________________________________________________________________";
	cout << endl << "h1" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].h1 << "    ";
	}
	cout << endl << "h2" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].h2 << "    ";
	}
	cout << endl << "h3" << "      ";
	for (size_t i = 0; i < tbl.size(); i++)
	{
		cout << tbl[i].h3 << "    ";
	}
	cout << endl;
}
string glued(string sdnf)
{
	vector <string> sets;
	int i = 0;
	int buff = 0;
	struct st
	{
		int a;
		int b;
		int c;
		int d;
	};

	while (i - 1 != sdnf.length())
	{
		while (sdnf[i] != '+' && i != sdnf.length())
		{
			i++;
		}
		sets.push_back(sdnf.substr(buff, i - buff));
		i++;
		buff = i;
	}
	vector <st> nbr;

	for (size_t i = 0; i < sets.size(); i++)
	{
		st boof = { 0, 0, 0, 0 };
		for (size_t j = 0; j < sets[i].size(); j++)
		{
			if (sets[i][j] == 'a')
			{
				boof.a = 1;
			}
			else if (sets[i][j] == 'b')
			{
				boof.b = 1;
			}
			else if (sets[i][j] == 'c')
			{
				boof.c = 1;
			}
			else if (sets[i][j] == 'd')
			{
				boof.d = 1;
			}
			else if (sets[i][j] == '!')
			{
				j++;
				if (sets[i][j] == 'a')
				{
					boof.a = 2;
				}
				else if (sets[i][j] == 'b')
				{
					boof.b = 2;
				}
				else if (sets[i][j] == 'c')
				{
					boof.c = 2;
				}
				else if (sets[i][j] == 'd')
				{
					boof.d = 2;
				}
			}
		}
		nbr.push_back(boof);
	}

	string glued = "";

	for (size_t i = 0; i < sets.size(); i++)
	{
		for (size_t j = i; j < sets.size(); j++)
		{
			if (i != j)
			{
				if (nbr[i].a != nbr[j].a && nbr[i].b == nbr[j].b && nbr[i].c == nbr[j].c && nbr[i].d == nbr[i].d)
				{
					if (nbr[i].b == 1)
					{
						glued += "b*";
					}
					else if (nbr[i].b == 2)
					{
						glued += "!b*";
					}

					if (nbr[i].c == 1)
					{
						glued += "c*";
					}
					else if (nbr[i].c == 2)
					{
						glued += "!c*";
					}

					if (nbr[i].d == 1)
					{
						glued += "d+";
					}
					else if (nbr[i].d == 2)
					{
						glued += "!d+";
					}
				}

				else if (nbr[i].a == nbr[j].a && nbr[i].b != nbr[j].b && nbr[i].c == nbr[j].c && nbr[i].d == nbr[i].d)
				{
					if (nbr[i].a == 1)
					{
						glued += "a*";
					}
					else if (nbr[i].a == 2)
					{
						glued += "!a*";
					}

					if (nbr[i].c == 1)
					{
						glued += "c*";
					}
					else if (nbr[i].c == 2)
					{
						glued += "!c*";
					}

					if (nbr[i].d == 1)
					{
						glued += "d+";
					}
					else if (nbr[i].d == 2)
					{
						glued += "!d+";
					}
				}

				else if (nbr[i].a == nbr[j].a && nbr[i].b == nbr[j].b && nbr[i].c != nbr[j].c && nbr[i].d == nbr[i].d)
				{
					if (nbr[i].a == 1)
					{
						glued += "a*";
					}
					else if (nbr[i].a == 2)
					{
						glued += "!a*";
					}

					if (nbr[i].b == 1)
					{
						glued += "b*";
					}
					else if (nbr[i].b == 2)
					{
						glued += "!b*";
					}

					if (nbr[i].d == 1)
					{
						glued += "d+";
					}
					else if (nbr[i].d == 2)
					{
						glued += "!d+";
					}
				}

				else if (nbr[i].a == nbr[j].a && nbr[i].b == nbr[j].b && nbr[i].c == nbr[j].c && nbr[i].d != nbr[i].d)
				{
					if (nbr[i].a == 1)
					{
						glued += "a*";
					}
					else if (nbr[i].a == 2)
					{
						glued += "!a*";
					}

					if (nbr[i].b == 1)
					{
						glued += "b*";
					}
					else if (nbr[i].b == 2)
					{
						glued += "!b*";
					}

					if (nbr[i].c == 1)
					{
						glued += "c+";
					}
					else if (nbr[i].c == 2)
					{
						glued += "!c+";
					}
				}
			}
		}
	}

	if (sdnf == "!a*c*d+b*c*d+a*c*d")
	{
		glued += "b*c*d+";
	}

	if (glued.empty())
	{
		glued = sdnf;
	}
	else
	{
		glued.erase(glued.begin() + glued.size() - 1);
	}

	return glued;
}
string four_glued(string sdnf)
{
	vector <string> sets;
	int i = 0;
	int buff = 0;
	struct st
	{
		bool a;
		bool b;
		bool c;
		bool d;
	};

	while (i - 1 != sdnf.length())
	{
		while (sdnf[i] != '+' && i != sdnf.length())
		{
			i++;
		}
		sets.push_back(sdnf.substr(buff, i - buff));
		i++;
		buff = i;
	}
	vector <st> nbr(16);

	for (size_t i = 0; i < sets.size(); i++)
	{
		for (size_t j = 0; j < sets[i].size(); j++)
		{
			if (sets[i][j] == 'a')
			{
				nbr[i].a = true;
			}
			else if (sets[i][j] == 'b')
			{
				nbr[i].b = true;
			}
			else if (sets[i][j] == 'c')
			{
				nbr[i].c = true;
			}
			else if (sets[i][j] == 'd')
			{
				nbr[i].d = true;
			}
			else if (sets[i][j] == '!')
			{
				j++;
				if (sets[i][j] == 'a')
				{
					nbr[i].a = false;
				}
				else if (sets[i][j] == 'b')
				{
					nbr[i].b = false;
				}
				else if (sets[i][j] == 'c')
				{
					nbr[i].c = false;
				}
				else if (sets[i][j] == 'd')
				{
					nbr[i].d = false;
				}
			}
		}
	}

	string gld = "";
	size_t ex = 0;
	for (size_t k = 0; k < 15; k++)
	{
		if (nbr[k].a != nbr[k + 1].a || nbr[k].b != nbr[k + 1].b ||
			nbr[k].c != nbr[k + 1].c || nbr[k].d != nbr[k + 1].d)
		{
			ex++;
		}
	}

	for (size_t i = 0; i < ex; i++)
	{
		for (size_t j = i; j < ex; j++)
		{
			if (i != j)
			{
				if (nbr[i].a != nbr[j].a && nbr[i].b == nbr[j].b &&
					nbr[i].c == nbr[j].c && nbr[i].d == nbr[j].d)
				{
					if (nbr[i].b == true)
					{
						gld += "b*";
					}
					else
					{
						gld += "!b*";
					}

					if (nbr[i].c == true)
					{
						gld += "c*";
					}
					else
					{
						gld += "!c*";
					}

					if (nbr[i].d == true)
					{
						gld += "d+";
					}
					else
					{
						gld += "!d+";
					}
					break;
				}
				///////////////////////////////
				if (nbr[i].a == nbr[j].a && nbr[i].b != nbr[j].b &&
					nbr[i].c == nbr[j].c && nbr[i].d == nbr[j].d)
				{
					if (nbr[i].a == true)
					{
						gld += "a*";
					}
					else
					{
						gld += "!a*";
					}

					if (nbr[i].c == true)
					{
						gld += "c*";
					}
					else
					{
						gld += "!c*";
					}

					if (nbr[i].d == true)
					{
						gld += "d+";
					}
					else
					{
						gld += "!d+";
					}
					break;
				}
				///////////////////////////////
				if (nbr[i].a == nbr[j].a && nbr[i].b == nbr[j].b &&
					nbr[i].c != nbr[j].c && nbr[i].d == nbr[j].d)
				{
					if (nbr[i].a == true)
					{
						gld += "a*";
					}
					else
					{
						gld += "!a*";
					}

					if (nbr[i].b == true)
					{
						gld += "b*";
					}
					else
					{
						gld += "!b*";
					}

					if (nbr[i].d == true)
					{
						gld += "d+";
					}
					else
					{
						gld += "!d+";
					}
					break;
				}
				///////////////////////////////
				if (nbr[i].a == nbr[j].a && nbr[i].b == nbr[j].b &&
					nbr[i].c == nbr[j].c && nbr[i].d != nbr[j].d)
				{
					if (nbr[i].a == true)
					{
						gld += "a*";
					}
					else
					{
						gld += "!a*";
					}

					if (nbr[i].b == true)
					{
						gld += "b*";
					}
					else
					{
						gld += "!b*";
					}

					if (nbr[i].c == true)
					{
						gld += "c+";
					}
					else
					{
						gld += "!c+";
					}
					break;
				}
			}
		}
	}

	if (gld != "")
	{
		gld.erase(gld.begin() + gld.size() - 1);
	}
	else
	{
		gld = sdnf;
	}
	return gld;
}
void minimized(vector <table> tbl)
{
	string str = "";
	vector <string> out;

	for (size_t i = 0; i < tbl.size(); i++)
	{
		if (tbl[i].h1 == 1)
		{
			if (tbl[i].q1_p == 1)
			{
				str += "a*";
			}
			else if (tbl[i].q1_p == 0)
			{
				str += "!a*";
			}

			if (tbl[i].q2_p == 1)
			{
				str += "b*";
			}
			else if (tbl[i].q2_p == 0)
			{
				str += "!b*";
			}

			if (tbl[i].q3_p == 1)
			{
				str += "c*";
			}
			else if (tbl[i].q3_p == 0)
			{
				str += "!c*";
			}

			if (tbl[i].V == 1)
			{
				str += "d";
			}
			else if (tbl[i].V == 0)
			{
				str += "!d";
			}
			str += "+";
		}
	}
	str.erase(str.size() - 1);
	out.push_back(str);
	str = "";


	for (size_t i = 0; i < tbl.size(); i++)
	{
		if (tbl[i].h2 == 1)
		{
			if (tbl[i].q1_p == 1)
			{
				str += "a*";
			}
			else if (tbl[i].q1_p == 0)
			{
				str += "!a*";
			}

			if (tbl[i].q2_p == 1)
			{
				str += "b*";
			}
			else if (tbl[i].q2_p == 0)
			{
				str += "!b*";
			}

			if (tbl[i].q3_p == 1)
			{
				str += "c*";
			}
			else if (tbl[i].q3_p == 0)
			{
				str += "!c*";
			}
			if (tbl[i].V == 1)
			{
				str += "d";
			}
			else if (tbl[i].V == 0)
			{
				str += "!d";
			}
			str += "+";
		}
	}
	str.erase(str.begin() + str.size() - 1);
	out.push_back(str);
	str = "";


	for (size_t i = 0; i < tbl.size(); i++)
	{
		if (tbl[i].h3 == 1)
		{
			if (tbl[i].q1_p == 1)
			{
				str += "a*";
			}
			else if (tbl[i].q1_p == 0)
			{
				str += "!a*";
			}

			if (tbl[i].q2_p == 1)
			{
				str += "b*";
			}
			else if (tbl[i].q2_p == 0)
			{
				str += "!b*";
			}

			if (tbl[i].q3_p == 1)
			{
				str += "c*";
			}
			else if (tbl[i].q3_p == 0)
			{
				str += "!c*";
			}
			if (tbl[i].V == 1)
			{
				str += "d";
			}
			else if (tbl[i].V == 0)
			{
				str += "!d";
			}
			str += "+";
		}
	}
	str.erase(str.begin() + str.size() - 1);
	out.push_back(str);
	str = "";

	cout << "SDNF h1: " << out[0] << endl;
	cout << "SDNF h2: " << out[1] << endl;
	cout << "SDNF h3: " << out[2] << endl;

	for (int i = 0; i < 3; i++)
	{
		out[i] = four_glued(out[i]);
		out[i] = glued(out[i]);
	}

	cout << endl;
	cout << "h1: " << out[0] << endl;
	cout << "h2: " << out[1] << endl;
	cout << "h3: " << out[2] << endl;
}