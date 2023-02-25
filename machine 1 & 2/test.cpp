#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

int solution_one(vector<int> list)
{
    int n = 0;
    for(size_t i = 0; i < list.size(); i++)
    {
        for(size_t j = i + 1; j < list.size(); j++)
        {
            if(list[i] > list[j])
            {
                n++;
                break;
            }
        }
    }
    return n;
}
void problem_one(void)
{
    vector<int> num_list {};
    int count, x;

    cout << "Enter count, integers separated by a space:" << '\n';
    cin >> count;

    while((count--) != 0)
    {
        cin >> x;
        num_list.push_back(x);
    }

    cout << solution_one(num_list) << '\n';
}


void problem_two(void)
{
    vector<int> arr;
    int target, count, x;

    cout << "Enter the desired sum, number of elements, each element:\n";
    cin >> target >> count;

    while((count--) != 0)
    {
        cin >> x;
        arr.push_back(x);
    }

    unordered_map<int, int> complements;
    bool pairs_found = false;
    for(size_t i = 0; i < arr.size(); i++)
    {
        int comp = target - arr[i];
        auto finder = complements.find(arr[i]);
        if(finder !=  complements.end())
        {
            cout << '(' << arr[finder->second] << ", " <<  arr[i] << ") found at ["
                 <<  finder->second + 1 << "] [" << i + 1 << "]";
            pairs_found = true;
        }
        complements[comp] = i;
    }

    if(pairs_found == false)
        cout << "No pairs found\n";
}
int main()
{
    cout << "Machine Problem 1: \n\n";
    problem_one();
    cout << "Machine Problem 2: \n\n";
    problem_two();
}

