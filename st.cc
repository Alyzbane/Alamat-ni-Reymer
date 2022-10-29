#include <iostream>
#include <string>

using namespace std;

struct Identity {
	string name;
	string citizenship;
	int age;
	string favorite_color;
}Pperson;

int main()
{

	Pperson.name = "Prince Daniel D. Mampusti";
	Pperson.citizenship = "Filipino";
	Pperson.age = 19;
	Pperson. favorite_color = "Crimson Blood";

	cout << Pperson.name << endl
	     << Pperson.citizenship << endl
	     << Pperson.age << endl
	     << Pperson.favorite_color << endl;
}
