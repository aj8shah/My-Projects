#include <iostream>
#include <ftw.h>
#include <sys/stat.h>
#include <stdint.h>

using namespace std;

int directoriesCount = 0;
int filesCount = 0;
int bytesCount = 0;

int count_entry(const char* file, const struct stat* status, int flag)
{
	switch(flag)
	{
		case FTW_D: 
			directoriesCount++;
			break;
		case FTW_F: 
			filesCount++;
			break;
		default:
			break;
	}
	bytesCount = bytesCount + (intmax_t)status->st_size;
	return 0;
}		

int main(int argc, char *argv[])
{
	if(argc == 1)
	{
		cout << "Expecting at least one argument." << endl;
		return 1;
	}
	int ftw_flag = ftw(argv[1], count_entry, 1024);
	cout << "The total number of directories in directory <" << argv[1] << "> is: " << directoriesCount << endl;
	cout << "The total number of files in directory <" << argv[1] << "> is: " << filesCount << endl;
	cout << "The total number of bytes occupied by all files in directory <" << argv[1] << "> is: " << bytesCount << endl;
		
	return 0;
}