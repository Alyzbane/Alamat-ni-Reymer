#include <iostream>
#include <string>

#define N 5
#define LEN 2
using namespace std;


int main(void)
{
        string subj[N] = {"ITECC04", "ITECC05", "ANALGEO", "DIFCAL", "JOSERIZ"};
        int grades[N];
        int avrg = 0;

        string name[LEN], cor_sec[LEN];

   //get name and section
/*
kaya lang netong kunin ay Firstname Lastname i.e. Luna Aries
bawal --> Luna Aries Stars 
 name -->  [0]   [1]  [0]  <--- cor_sec
dagdagan mo nalng yung index ng name tas pati dun sa cout
*/
        cout << "Input name: ";
        cin >> name[0]  >> name[1];
        cout << "Input course & section: ";
        cin >> cor_sec[0] >> cor_sec[1];

   //get all of the grades
        for(int i = 0; i < N; i++)
        {
                cout << subj[i] << " grade: ";
                cin >> grades[i];
                avrg += grades[i]; //add na ang mga grades
        }

        //kunin ang average
        avrg /= N;

        cout << "-----------------------------------------" << endl;

/*
if the name output ay ganto
Name: Luna Asd  |
hindi pantay to | dun sa iba dagdagan mo yung \t or space sa cout ng name
*/
        cout << "| Name: " <<  name[0] << " " << name[1] << "\t\t|" <<  endl;
        cout << "| Course & Section: " << cor_sec[0] << " " << cor_sec[1] << "\t \t|" <<  endl;

        cout << "| Subject\t\t\tGrades \t|" << endl;

        for(int j = 0; j < N; j++)
                cout << "| " <<  subj[j] << "\t\t\t"
                        << grades[j] << " \t|" << endl;

        cout << "| Average " << avrg << "\t\t\t \t|" << endl;
        cout << "-----------------------------------------" << endl;

        if (avrg >= 75 )
           cout << " Well done!" << endl;
        else
           cout << " Better luck next time =(" << endl;

}
