#include <iostream>
#include <string>

using namespace std;

struct Company
{
	string name;
	string est_date;
	string CEO;
	string location;

}MCompany;

int main()
{
	MCompany.name = "Kayamanan";
	MCompany.est_date = "6/6/1780";
	MCompany.CEO = "Prince Daniel D. Mampusti";
	MCompany.location = "JP, Kalayaan Hall,"
			    " 2/F Jose Laurel" 
			    " St, San Miguel, Manila,"
			    " Metro Manila";

	cout << MCompany.name << endl
     	     << MCompany.est_date << endl
	     << MCompany.CEO << endl
	     << MCompany.location << endl;

	

}
