#include <iostream>
#include <string>
#include <cstring>

using namespace std;
int main()
{
	string commandLine;
	int indexSpace[100];
	int tokenCount;
	string command;
	cout << "Enter a Command Line or Enter \"QUIT\" to Exit: " << endl;
	getline(cin, commandLine);
	while(commandLine != "QUIT") 
	{
		char cstr[commandLine.size() + 1];
		strcpy(cstr, commandLine.c_str());
		int j = 0; //# of spaces
		for(int i = 0; i <= sizeof(cstr); i++)
		{
			if(cstr[i] == ' ')
			{
				indexSpace[j] = i;
				j++;
			}
			if(cstr[i] == '#')
			{
				break;
			}	
		}
		for(int r = j; r < 100; r++)
		{
			indexSpace[r] = commandLine.size() - 1;
		}
		tokenCount = j + 1;
		int x = 0;

		command = commandLine.substr(0, indexSpace[x]);
		cout << "Command: " << command << endl;

		for(int y = command.size(); y < sizeof(cstr); y++)
		{
			if(cstr[y] == '-')
			{
				cout << "Option: " << commandLine.substr(indexSpace[x] + 1, 	indexSpace[x+1] - indexSpace[x]) << endl;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x];
			}
			else if(cstr[y] == '\'')
			{
				x++;
				cout << "Special Character: " << cstr[y] << endl;
				cout << "Argument: " << commandLine.substr(y+1, indexSpace[x] - cstr[y]) << endl;
				cout << "Special Character: '" << endl; 
				tokenCount = tokenCount + 2;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x]; 
			}
			else if(cstr[y] == '|')
			{
				x++;
				cout << "Special Character: " << cstr[y] << endl;
				cout << "Command: " << commandLine.substr(indexSpace[x] + 1, indexSpace[x+1] - indexSpace[x]) << endl;
				x--;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x];
			}

			else if(cstr[y] == '#')
			{
				cout << "Special Character: " << cstr[y] << endl;
				tokenCount++;
				break;
			}

			else if(cstr[y] == '.')
			{
				cout << "Argument: " << commandLine.substr(y, (indexSpace[x + 1] - 	indexSpace[x])) << endl;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x]; 
			}
 
			else if(cstr[y] == ' ' && (cstr[y+1] == 'a' || cstr[y+1] == 'b' || cstr[y+1] == 'c' || cstr[y+1] == 'd' || cstr[y+1] == 'e' || cstr[y+1] == 'f' || cstr[y+1] == 'g' || cstr[y+1] == 'h' || cstr[y+1] == 'i' || cstr[y+1] == 'j' || cstr[y+1] == 'k' || cstr[y+1] == 'l' || cstr[y+1] == 'm' || cstr[y+1] == 'n' || cstr[y+1] == 'o' || cstr[y+1] == 'p' || cstr[y+1] == 'q' || cstr[y+1] == 'r' || cstr[y+1] == 's' || cstr[y+1] == 't' || cstr[y+1] == 'u' || cstr[y+1] == 'v' || cstr[y+1] == 'w' || cstr[y+1] == 'x' || cstr[y+1] == 'y' || cstr[y+1] == 'z' || cstr[y+1] == 'A' || cstr[y+1] == 'B' || cstr[y+1] == 'C' || cstr[y+1] == 'D' || cstr[y+1] == 'E' || cstr[y+1] == 'F' || cstr[y+1] == 'G' || cstr[y+1] == 'H' || cstr[y+1] == 'I' || cstr[y+1] == 'J' || cstr[y+1] == 'K' || cstr[y+1] == 'L' || cstr[y+1] == 'M' || cstr[y+1] == 'N' || cstr[y+1] == 'O' || cstr[y+1] == 'P' || cstr[y+1] == 'Q' || cstr[y+1] == 'R' || cstr[y+1] == 'S' || cstr[y+1] == 'T' || cstr[y+1] == 'U' || cstr[y+1] == 'V' || cstr[y+1] == 'W' || cstr[y+1] == 'X' || cstr[y+1] == 'Y' || cstr[y+1] == 'Z'))
			{
				cout << "Argument: " << commandLine.substr(y+1, indexSpace[x + 1] - 	indexSpace[x]) << endl;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x]; 
			}
			else if(cstr[y] == '>' || cstr[y] == '<')
			{
				x++;
				cout << "Special Character: " << cstr[y] << endl;
				cout << "Argument: " << commandLine.substr(indexSpace[x] + 1, indexSpace[x+1] - indexSpace[x]) << endl;
				x--;
				if(x <= j)
				{
					x++;
				}
				y = indexSpace[x];	
			}
		}
		cout << "-----------------------" << endl;
		cout << "Number of Tokens: " << tokenCount << endl;
		cout << endl;
		cout << "Enter a Command Line or Enter \"QUIT\" to Exit: " << endl;
		getline(cin, commandLine);
	}
	
	return 0;
}

