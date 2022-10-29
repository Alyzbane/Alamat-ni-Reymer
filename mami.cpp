/*
 * Ano ginagawa ng program nato?
 * Taking a set of test scores and getting the average and sum of it
 *
 * Author: Prince Daniel D. Mampusti
 * */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

struct Calc
{
	int sum;
	double avrg;
};

void print(const vector<string>& ptr, 
		const vector<int>& grades, const Calc* savg);
void insert(vector<string>& subs, vector<int>& sets,const int& size);
int P_calculateSum(const vector<int>& gds);
double M_calculateAverage(int sum, int size);

int main()
{
	//use for storing all subject and grades
	vector<string> subj; 
	vector<int> stud;

	int n;

	//init a data struct for storing the calculated grades
	Calc *res = new Calc();

	cout << "How many subjects: ";
	cin >> n;
	if(n <= 0)
	{
	  cerr << ("Not a valid value ");	
	  terminate();
	}
	cin.ignore(); //remove the newline in the buffer

	insert(subj, stud, n);

	res->sum = P_calculateSum(stud);		
	res->avrg = M_calculateAverage(res->sum, stud.size());


	print(subj, stud, res);

	return 0;
}
int P_calculateSum(const vector<int>& gds)
{
	int sum = 0;
	for(int scores : gds)
	  sum += scores;

	if(sum < 0)
		cerr << "Invalid sum value: " << sum << endl;

	return sum;
}

double M_calculateAverage(int sum, int size)
{
	double n;

	n = sum / size;

	if(n < 0)
		cerr << "Invalid value of average" << n << endl;

	return n;
}

//insert the subjects and its grades
void insert(vector<string>& subs, vector<int>& sets,const int& size)
{
	string s;
	int n; 

	cout << "Input:\nSubjects Grades\n";

	for(int i = 0; i < size; i++)
	{
		cin >> s >> n;
		subs.push_back(s); //subject name
		sets.push_back(n);
	}
}

	//pass by reference and evaluate the  value of grades
void print(const vector<string>& ptr, const vector<int>& grades, const Calc* savg)
{
	cout << "\t\t------------------------------\n"
		"\t\tSubjects\tGrades\n";
	for(size_t i = 0; i < ptr.size(); i++)
	{
		cout << "\t\t" << ptr[i]
	   	     << "\t\t" << grades[i] << endl;
	}

	cout << "\n\t\tSum of the grades: " << savg->sum << endl;
	cout << "\t\tAverage of the grades: " << savg->avrg << endl;

}
