#include <iostream>
#include <string>
using namespace std;

int main(int argc, char** argv)
{

	string argument = argv[1];
	string lines[3];
	int temp;
	string tempStr;

	getline(cin, tempStr);
	lines[0] = tempStr;

	getline(cin, tempStr);
	lines[1] = tempStr;

	getline(cin, tempStr);
	lines[2] = tempStr;

	if(argument == "-d")
	{
		cout << lines[0] << endl;
	}
	else if(argument == "-f")
	{
		cout << lines[1] << endl;
	}
	else if(argument == "-b")
	{
		cout << lines[2] << endl;
	}

	return 0;
}