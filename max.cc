#include <iostream>

using namespace std;

int max_num(int x, int y)
{
	return (x > y) ? x : y;
}

int main()
{
	int x, y, max;

	cout << "Input two numbers: ";
	cin >> x >> y;

	max = max_num(x,y);
	cout << "Maximum num: " 
	     << max << endl;
}
