#include "Matrix.h"
float Matrix::Determinator() {
	float Det = 0;

	if (rows != columns) return NULL;

	switch (rows) {
	case 1:
		return matrix[0][0];
	case 2:
		return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
	case 3:
		return matrix[0][0] * matrix[1][1] * matrix[2][2] +
			matrix[0][1] * matrix[1][2] * matrix[2][0] +
			matrix[1][0] * matrix[2][1] * matrix[0][2] -
			matrix[2][0] * matrix[1][1] * matrix[0][2] -
			matrix[1][0] * matrix[0][1] * matrix[2][2] -
			matrix[0][0] * matrix[2][1] * matrix[1][2];
	case 4:
		return matrix[0][0] * (matrix[1][1] * matrix[2][2] * matrix[3][3] +
			matrix[1][2] * matrix[2][3] * matrix[3][1] +
			matrix[2][1] * matrix[3][2] * matrix[1][3] -
			matrix[3][1] * matrix[2][2] * matrix[1][3] -
			matrix[2][1] * matrix[1][2] * matrix[3][3] -
			matrix[2][3] * matrix[3][2] * matrix[1][1]) -
			matrix[0][1] * (matrix[1][0] * matrix[2][2] * matrix[3][3] +
				matrix[1][2] * matrix[2][3] * matrix[3][0] +
				matrix[2][0] * matrix[3][2] * matrix[1][3] -
				matrix[3][0] * matrix[2][2] * matrix[1][3] -
				matrix[2][0] * matrix[1][2] * matrix[3][3] -
				matrix[2][3] * matrix[3][2] * matrix[1][0]) +
			matrix[0][2] * (matrix[1][0] * matrix[2][1] * matrix[3][3] +
				matrix[1][1] * matrix[2][3] * matrix[3][0] +
				matrix[2][0] * matrix[3][1] * matrix[1][3] -
				matrix[3][0] * matrix[2][1] * matrix[1][3] -
				matrix[2][0] * matrix[1][1] * matrix[3][3] -
				matrix[2][3] * matrix[3][1] * matrix[1][0]) -
			matrix[0][3] * (matrix[1][0] * matrix[2][1] * matrix[3][2] +
				matrix[1][1] * matrix[2][2] * matrix[3][0] +
				matrix[2][0] * matrix[3][1] * matrix[1][2] -
				matrix[3][0] * matrix[2][1] * matrix[1][2] -
				matrix[2][0] * matrix[1][1] * matrix[3][2] -
				matrix[2][2] * matrix[3][1] * matrix[1][0]);
	}
	return Det;
}

float Matrix::Nor() { //just stupid func for normi. It is strange thing...
	float max = 0;
	for (int i = 0; i < matrix.size(); i++) {
		float buff = 0;
		for (int k = 0; k < matrix[i].size(); k++) {
			buff += matrix[i][k];
		}
		if (buff > max)
			max = buff;
	}
	return max;
}

Matrix::Matrix(int X, int Y) {
	int elem;
	vector<float> row;

	if (X != 0 && Y != 0) {
		cout << "Enter the elements:\n";
	}

	for (int k = 0; k < Y; k++) {
		for (int i = 0; i < X; i++) {
			cin >> elem;
			row.push_back(elem);
		}
		matrix.push_back(row);
		row.clear();
	}
	rows = X;
	columns = Y;
	det = Determinator();
	nor = Nor();
}


Matrix operator^(const Matrix &matrix_a, int value)
{
	Matrix pr;
	if (matrix_a.columns == matrix_a.rows)
	{
		for (int i = 0; i < value; i++) {
			if (i == 0)
				pr = matrix_a;

			else  pr *= matrix_a;
		}
		pr.det = pr.Determinator();
		pr.nor = pr.Nor();
		return pr;
	}
	else cout << "vozvedenie nevozmijno\n";
	return pr;
}

Matrix operator^=(Matrix &matrix_a, int value)
{
	Matrix old_a;
	if (matrix_a.columns == matrix_a.rows)
	{
		for (int i = 0; i < value; i++) {
			if (i == 0)
				old_a = matrix_a;

			else
				matrix_a = matrix_a * old_a;
		}
		return matrix_a;
	}
	else cout << "vozvedenie nevozmijno\n";
	return 0;
}

Matrix operator*(const Matrix &matrix_a, const  Matrix &matrix_b)
{
	vector<float> colum;
	float col = 0;
	Matrix pr;
	if (matrix_a.rows == matrix_b.columns)
	{
		for (int i = 0; i < matrix_a.columns; i++)
		{
			for (int j = 0; j < matrix_b.rows; j++)
			{
				for (int k = 0; k < matrix_a.rows; k++)
				{
					col += matrix_a.matrix[i][k] * matrix_b.matrix[k][j];
				}
				colum.push_back(col);
				col = 0;
			}
			pr.matrix.push_back(colum);
			colum.clear();
		}
		pr.columns = matrix_b.columns;
		pr.rows = matrix_a.rows;
		pr.det = pr.Determinator();
		pr.nor = pr.Nor();
		return pr;
	}
	else cout << "proizvedenie nevozmojno\n";
	return pr;
}

Matrix operator*=(Matrix &matrix_a, const  Matrix &matrix_b)
{
	vector<float> colum;
	float col = 0;
	Matrix pr;
	if (matrix_a.rows == matrix_b.columns)
	{
		for (int i = 0; i < matrix_a.columns; i++)
		{
			for (int j = 0; j < matrix_b.rows; j++)
			{
				for (int k = 0; k < matrix_a.rows; k++)
				{
					col += matrix_a.matrix[i][k] * matrix_b.matrix[k][j];
				}
				colum.push_back(col);
				col = 0;
			}
			pr.matrix.push_back(colum);
			colum.clear();
		}
		matrix_a.matrix = pr.matrix;
		matrix_a.rows = matrix_b.rows;
		matrix_a.det = matrix_a.Determinator();
		matrix_a.nor = matrix_a.Nor();
		return matrix_a;
	}
	else cout << "proizvedenie nevozmojno\n";
	return pr;
}

Matrix operator-(const Matrix& matrix_a, const Matrix& matrix_b) {
	Matrix m = matrix_a;
	if (matrix_a.rows != matrix_b.rows || matrix_a.columns != matrix_b.columns) return m;
	for (int i = 0; i < matrix_a.rows; i++) {
		for (int k = 0; k < matrix_a.columns; k++) {
			m.matrix[k][i] = matrix_a.matrix[k][i] - matrix_b.matrix[k][i];
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}
Matrix operator-=(Matrix& matrix_a, const Matrix& matrix_b) {
	if (matrix_a.rows != matrix_b.rows || matrix_a.columns != matrix_b.columns) return matrix_a;
	for (int i = 0; i < matrix_a.rows; i++) {
		for (int k = 0; k < matrix_a.columns; k++) {
			matrix_a.matrix[k][i] -= matrix_b.matrix[k][i];
		}
	}
	matrix_a.det = matrix_a.Determinator();
	matrix_a.nor = matrix_a.Nor();
	return  matrix_a;
}
Matrix operator*(const Matrix &matrix_a, int value) {
	Matrix pr = matrix_a;
	for (int i = 0; i < matrix_a.rows; i++) {
		for (int j = 0; j < matrix_a.columns; j++) {
			pr.matrix[j][i] *= value;
		}
	}
	pr.det = pr.Determinator();
	pr.nor = pr.Nor();
	return pr;
}
Matrix operator*=(Matrix &matrix_a, int value) {

	for (int i = 0; i < matrix_a.rows; i++) {
		for (int j = 0; j < matrix_a.columns; j++) {
			matrix_a.matrix[j][i] *= value;
		}
	}
	matrix_a.det = matrix_a.Determinator();
	matrix_a.nor = matrix_a.Nor();
	return matrix_a;
}

void Matrix::printMatrix() {
	for (int i = 0; i < matrix.size(); i++) {
		for (int k = 0; k < matrix[i].size(); k++) {
			cout << matrix[i][k] << " ";
		}
		cout << std::endl;
	}
}

Matrix operator/(const Matrix& left, const int& right) {
	Matrix m = left;
	for (int i = 0; i < m.rows; i++) {
		for (int k = 0; k < m.columns; k++) {
			m.matrix[i][k] /= right;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix operator/=(Matrix& left, const int& right) {
	for (int i = 0; i < left.rows; i++) {
		for (int k = 0; k < left.columns; k++) {
			left.matrix[i][k] /= right;
		}
	}
	left.det = left.Determinator();
	left.nor = left.Nor();
	return left;
}

Matrix operator+(const Matrix& left, const Matrix& right) {
	Matrix m = left;
	if (left.rows != right.rows || left.columns != right.columns) return m;
	for (int i = 0; i < left.rows; i++) {
		for (int k = 0; k < left.columns; k++) {
			m.matrix[i][k] = left.matrix[i][k] + right.matrix[i][k];
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix operator+=(Matrix& m, const Matrix& right) {
	if (m.rows != right.rows || m.columns != right.columns) return m;
	for (int i = 0; i < m.rows; i++) {
		for (int k = 0; k < m.columns; k++) {
			m.matrix[i][k] += right.matrix[i][k];
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix& operator++(Matrix& m) { //it is just preincrement
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k]++;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix operator++(Matrix& m, int) { //it is just postincrement
	Matrix oldValue(m); //it just function fot pre
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k]++;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix& operator--(Matrix& m) { //it is predecrement
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k]--;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

Matrix operator--(Matrix& m, int) { //you must know what is it
	Matrix oldValue(m);
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k]--;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

const Matrix operator+(const Matrix& m, const int& value) { //it is sum with value (value must be integer)
	Matrix buff = m;
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			buff.matrix[i][k] += value;
		}
	}
	buff.det = buff.Determinator();
	buff.nor = buff.Nor();
	return buff;
}

const Matrix operator+(const int& value, const Matrix& m) { //it is sum with value (value must be integer)
	Matrix buff = m;
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			buff.matrix[i][k] += value;
		}
	}
	buff.det = buff.Determinator();
	buff.nor = buff.Nor();
	return buff;
}

Matrix& operator+=(Matrix& m, const int& value) { //it is sum with value (value must be integer)
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k] += value;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();
	return m;
}

const Matrix operator-(const Matrix& m, const int& value) { //it is difference with value (value must be integer)
	Matrix buff = m;
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			buff.matrix[i][k] -= value;
		}
	}
	buff.det = buff.Determinator();
	buff.nor = buff.Nor();
	return buff;
}

Matrix& operator-=(Matrix& m, const int& value) { //it is difference with value (value must be integer)
	for (int i = 0; i < m.columns; i++) {
		for (int k = 0; k < m.rows; k++) {
			m.matrix[i][k] -= value;
		}
	}
	m.det = m.Determinator();
	m.nor = m.Nor();

	return m;
}
