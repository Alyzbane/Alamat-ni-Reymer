/*printing the inputted grade*/
#include <iostream>
#include <string>

#define N 5
using namespace std;

int main(void)
{
        string subj[N] = {"ITECC04", "ITECC05", "ANALGEO", "DIFCAL", "JOSERIZ"};
        int grades[N];
        int avrg = 0;

        string name, cor_sec;

        cout << "Input name: ";
        getline(cin, name);
        cout << "Input course & section: ";
        getline(cin, cor_sec);

        //getting the grades of subjects
        for(int i = 0; i < N; i++)
        {
                cout << subj[i] << " grade: ";
                cin >> grades[i];
                avrg += grades[i]; //add na ang mga grades
        }

        //kunin ang average
        avrg /= N;

        //printing the output
        cout << "-----------------------------------------" << endl;

        cout << "| Name: " << name << " \t|" << endl;

        cout << "| Course & Section: " << cor_sec
                << "\t \t|" <<  endl;

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
