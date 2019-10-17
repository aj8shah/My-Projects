#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <fcntl.h>
#include <ctype.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int pipeCounter=0, commandCounter = 0, fd;
int pipeFlag=1;
int flag, len;
int numLines;
int envFlag;
int pipeFlag, noPipeFlag,  outRedirect, inRedirect;
int bangFlag;
int pid, status;

static char* args[512];
char *fileHistory;
char inBuffer[1024];
char *executeCommand[100];
char cwd[1024];
char dataHistory[1000][1000];
char currentDirectory[1000];
char retFile[3000];
char variableHistory[2000];
char *inputRedirectFile;
char *outputRedirectFile;
extern char** cEnvironment;

bool semiColon = false;
pid_t pid;

//-------------------------------------------------------------------------------------------

void clear();
void changeDirectory();
void parentDirectory();
static int command(int, int, int, char *executeCommand);
void prompt();
void sigintHandler(int sig_num);
void callEcho(char *echo_val);
void executeHistoryConstants();
static char* skipWhite(char* s);
void processFile();
void writeFile();
void executeBang();
void environment();
void setEnvironmentVariables();
void tokenCommand(char *com_exec);
void tokenRedirInOut(char *executeCommand);
void tokenRedirIn(char *executeCommand);
void tokenRedirOut(char *executeCommand);
char* skipComma(char* str);
static int divide(char *executeCommand, int, int, int);
void executePipe();
void executeSemiColon();

//-----------------------------------------------------------------------------------------

int main()
{   
	int status;
    	char ch[2]={"\n"};
    	getcwd(currentDirectory, sizeof(currentDirectory));
    	signal(SIGINT, sigintHandler);
    	while (1)
    	{
      		clear();
      		prompt();
      		fgets(inBuffer, 1024, stdin);
      		if(strcmp(inBuffer, ch)==0)
            	{
              		continue;
            	}
               	len = strlen(inBuffer);
      		inBuffer[len-1]='\0';
      		strcpy(variableHistory, inBuffer);
      		if(strcmp(inBuffer, "exit") == 0) 
            	{
              		flag = 1;
              		break;
            	}
      		for(int  i = 0;i < sizeof(inBuffer); i++)
      		{
        		if(inBuffer[i] == ';')
      			{
        			semiColon = true;
        			continue;
      			}
     		}
      		if(semiColon == true)
		{
        		executeSemiColon();
      		} 
		else 
		{
        		executePipe();
      		}
      		waitpid(pid,&status,0);
    	}  
  	if(flag==1)
      	{
      		printf("Exiting!\n");
      		exit(0);       
      		return 0;
      	}
	return 0;
}

void clear()
{
  	fd =0;
  	flag=0;
  	len=0;
  	numLines=0;
  	pipeCounter=0;
  	pipeFlag=0;
  	noPipeFlag=0;
  	outRedirect=0;
  	inRedirect=0;
  	inBuffer[0]='\0';
  	cwd[0] = '\0';
  	pid=0;
  	envFlag=0;
  	bangFlag=0;
}

void prompt()
{
	char shell[1000];
   	if (getcwd(cwd, sizeof(cwd)) != NULL)
        {
          	strcpy(shell, "myshell> ");
          	printf("%s", shell);
        }
   	else
       		perror("getcwd() error");
}

void executePipe()
{
	int i, n=1, input, first;
	input=0;
	first= 1;
	executeCommand[0]=strtok(inBuffer,"|");
	while ((executeCommand[n]=strtok(NULL,"|"))!=NULL)
      		n++;
	executeCommand[n]=NULL;
	pipeCounter=n-1;
	for(i=0; i<n-1; i++)
    	{
      		input = divide(executeCommand[i], input, first, 0);
      		first=0;
    	}
	input=divide(executeCommand[i], input, first, 1);
	input=0;
	return;
}

void executeSemiColon() 
{
  	int i, n=1, input, first;
  	input = 0;
  	first = 1;
  	executeCommand[0]=strtok(inBuffer,";");
  	while ((executeCommand[n]=strtok(NULL,";"))!=NULL)
      		n++;
  	executeCommand[n]=NULL;
  	commandCounter=n-1;
  	for(i=0; i<n-1; i++)
    	{
      		input = divide(executeCommand[i], input, first, 0);
      		input = 0;
    	}
    	input = divide(executeCommand[i], input, first, 1);
    	input=0;
    	return;
}

//Could not figure this method out, taken from github
static int divide(char *executeCommand, int input, int first, int last)
{
    	char *new_executeCommand1;  
    	new_executeCommand1=strdup(executeCommand);
        int m=1;
        args[0]=strtok(executeCommand," ");       
        while((args[m]=strtok(NULL," "))!=NULL)
              m++;
        args[m]=NULL;
        if (args[0] != NULL) 
        {
		if (strcmp(args[0], "exit") == 0) 
                    	exit(0);
            	if (strcmp(args[0], "echo") != 0) 
                {
                      	executeCommand = skipComma(new_executeCommand1);
                      	int m=1;
                      	args[0]=strtok(executeCommand," ");       
                      	while((args[m]=strtok(NULL," "))!=NULL)
                          	m++;
                      	args[m]=NULL;

                }
            	if(strcmp("cd",args[0])==0)
                {
                   	changeDirectory();
                    	return 1;
                }
            	else if(strcmp("pwd",args[0])==0)
                {
                   	parentDirectory();
                    	return 1;
                }
    	}
        return command(input, first, last, new_executeCommand1);
}

char* skipComma(char* str)
{
  	int i=0, j=0;
  	char temp[1000];
  	while(str[i++]!='\0')
        {
		if(str[i-1]!='"')
                	temp[j++]=str[i-1];
        }
        temp[j]='\0';
        str = strdup(temp);
  	return str;
}

void changeDirectory()
{
	char *h="/home";   
	if(args[1]==NULL)
        	chdir(h);
	else if ((strcmp(args[1], "~")==0) || (strcmp(args[1], "~/")==0))
        	chdir(h);
	else if(chdir(args[1])<0)
    		printf("bash: cd: %s: No such file or directory\n", args[1]);
}

void parentDirectory()
{
	if (getcwd(cwd, sizeof(cwd)) != NULL)
        {
         	printf("%s\n", cwd );
        }
	else
       		perror("getcwd() error");
}

//Could not figure this method out, taken from github
static int command(int input, int first, int last, char *executeCommand)
{
  	int mypipefd[2], ret, input_fd, output_fd;
  	ret = pipe(mypipefd);
  	if(ret == -1)
      	{
        	perror("pipe");
        	return 1;
      	}
  	pid = fork();
  	if (pid == 0) 
  	{
    		if (first==1 && last==0 && input==0) 
    		{
      			dup2( mypipefd[1], 1 );
    		} 
    		else if (first==0 && last==0 && input!=0) 
    		{
      			dup2(input, 0);
      			dup2(mypipefd[1], 1);
    		} 
    		else 
    		{
      			dup2(input, 0);
    		}
    		if (strchr(executeCommand, '<') && strchr(executeCommand, '>')) 
            	{
              		inRedirect=1;
              		outRedirect=1;
             		tokenRedirInOut(executeCommand);
            	}
   		else if (strchr(executeCommand, '<')) 
        	{
          		inRedirect=1;
          		tokenRedirIn(executeCommand);
        	}
   		else if (strchr(executeCommand, '>')) 
        	{
          		outRedirect=1;
          		tokenRedirOut(executeCommand);
        	}
    		if(outRedirect == 1)
                {                    
                       	output_fd= creat(outputRedirectFile, 0644);
                        if (output_fd < 0)
                        {
                          	fprintf(stderr, "Failed to open %s for writing\n", outputRedirectFile);
                          	return(EXIT_FAILURE);
                        }
                        dup2(output_fd, 1);
                        close(output_fd);
                        outRedirect=0;
                }
    		if(inRedirect  == 1)
                {
                       	input_fd=open(inputRedirectFile,O_RDONLY, 0);
                      	if (input_fd < 0)
                        {
                          	fprintf(stderr, "Failed to open %s for reading\n", inputRedirectFile);
                          	return(EXIT_FAILURE);
                        }
                        dup2(input_fd, 0);
                        close(input_fd);
                        inRedirect=0;
             	}
     		if (strcmp(args[0], "export") == 0)
                {
                  	setEnvironmentVariables();
                  	return 1;
                }
    		if (strcmp(args[0], "echo") == 0)
              	{
              		callEcho(executeCommand);
              	} 
    		else if (strcmp(args[0], "history") == 0)
             	{
              		executeHistoryConstants();
              	} 
    		else if(execvp(args[0], args)<0) printf("%s: command not found\n", args[0]);
              		exit(0);
 	}
  	else 
  	{
     		waitpid(pid, 0, 0);  
   	} 
  	if (last == 1)
    		close(mypipefd[0]);
  	if (input != 0) 
    		close(input);
  	close(mypipefd[1]);
  	return mypipefd[0];
}

void callEcho(char *echo_val)
{
	int i=0, index=0;
  	envFlag=0;
  	char new_args[1024],env_val[1000], *str[10];
  	str[0]=strtok(echo_val," ");
  	str[1]=strtok(NULL, "");
  	strcpy(env_val, args[1]);
  	if(str[1]==NULL)
        {
          	printf("\n");
          	return;
        } 
  	if (strchr(str[1], '$')) 
        {
   		envFlag=1;
        }
  	memset(new_args, '\0', sizeof(new_args));
  	i=0; 
  	while(str[1][i]!='\0')
    	{
      		if(str[1][i]=='"')
      		{
      			index=0;     
      			while(str[1][i]!='\0')
          		{
          			if(str[1][i]!='"')
                		{
                			new_args[index]=str[1][i];
                 			flag=1;
                			index++;
                		}
          			i++;
         		}             
      		}
      		else if(str[1][i]==39)
      		{
      			index=0;
      			while(str[1][i]!='\0')
          		{
          			if(str[1][i]!=39)
                		{
                			new_args[index]=str[1][i];
                 			flag=1;
                			index++;
                		}
          			i++;
          		}
      		}
      		else if(str[1][i]!='"')
        	{
          		new_args[index]=str[1][i];
          		index++;
          		i++;
        	}
      		else i++;
	}
	new_args[index]='\0';
	if((strcmp(args[1], new_args)==0)&&(envFlag==0))
    		printf("%s\n", new_args);
	else 
	{
     		strcpy(args[1], new_args);
      		if(envFlag==1)
                {
                	environment();
                }
      		else if(envFlag==0)
                {
                  	printf("%s\n", new_args ); 
                }
    	}  
}

void executeHistoryConstants()
{
  	int num, i, start_index;
  	if(bangFlag==1)
        {
         	for(i=0; i<numLines; i++)
            		printf("%s\n", dataHistory[i] ); 
        }
  	else if(args[1]==NULL)
      	{
        	for(i=0; i<numLines-1; i++)
            		printf("%s\n", dataHistory[i] );
        	printf(" %d %s\n", numLines, variableHistory );
      	}
  	else
      	{
        	if(args[1]!=NULL)
        	num = atoi(args[1]);
        	if(num>numLines) 
        	{
          		for(i=0; i<numLines-1; i++)
            			printf("%s\n", dataHistory[i] );
          		printf(" %d %s\n", numLines, variableHistory );
        	}
        	start_index=numLines-num;
        	for(i=start_index; i<numLines-1; i++)
            		printf("%s\n", dataHistory[i] );
          	printf(" %d %s\n", numLines, variableHistory );
      	} 
}

static char* skipWhite(char* s)
{
  	while (isspace(*s)) ++s;
  	return s;
}

void tokenCommand(char *com_exec)
{
	int m=1;
	args[0]=strtok(com_exec," ");       
	while((args[m]=strtok(NULL," "))!=NULL)
       		m++;
}

void tokenRedirInOut(char *executeCommand)
{
  	char *io_token[100];
  	char *new_executeCommand1;  
  	new_executeCommand1=strdup(executeCommand);
  	int m=1;
  	io_token[0]=strtok(new_executeCommand1,"<");       
  	while((io_token[m]=strtok(NULL,">"))!=NULL)
        	m++;
  	io_token[1]=skipWhite(io_token[1]);
  	io_token[2]=skipWhite(io_token[2]);
  	inputRedirectFile=strdup(io_token[1]);
  	outputRedirectFile=strdup(io_token[2]);
  	tokenCommand(io_token[0]);
}

void tokenRedirIn(char *executeCommand)
{
  	char *i_token[100];
  	char *new_executeCommand1;  
  	new_executeCommand1=strdup(executeCommand);
  	int m=1;
  	i_token[0]=strtok(new_executeCommand1,"<");       
  	while((i_token[m]=strtok(NULL,"<"))!=NULL)
        	m++;
  	i_token[1]=skipWhite(i_token[1]);
  	inputRedirectFile=strdup(i_token[1]);
  	tokenCommand(i_token[0]);
}

void tokenRedirOut(char *executeCommand)
{
  	char *o_token[100];
  	char *new_executeCommand1;  
  	new_executeCommand1=strdup(executeCommand);
  	int m=1;
  	o_token[0]=strtok(new_executeCommand1,">");       
  	while((o_token[m]=strtok(NULL,">"))!=NULL)
         	m++;
  	o_token[1]=skipWhite(o_token[1]);
  	outputRedirectFile=strdup(o_token[1]); 
  	tokenCommand(o_token[0]);   
}

void sigintHandler(int sig_num)
{
	signal(SIGINT, sigintHandler);
   	fflush(stdout);
}
  
void executeBang()
{
  	char bang_val[1000];
  	char *tokenise_bang[100], *num_ch[10];
  	int i, n=1, num, index=0;
  	i=1;
  	if(inBuffer[i]=='!')
        {
           	strcpy(bang_val, dataHistory[numLines-1]);
        }
  	else if(inBuffer[i]=='-')
    	{	
        	n=1;
        	num_ch[0]=strtok(inBuffer,"-");
        	while ((num_ch[n]=strtok(NULL,"-"))!=NULL)
              		n++;
        	num_ch[n]=NULL;
        	num = atoi(num_ch[1]);

        	index = numLines-num;
        	strcpy(bang_val, dataHistory[index]);       
    	}
  	else 
    	{
      		num_ch[0]=strtok(inBuffer,"!");
      		num = atoi(num_ch[0]);
      		strcpy(bang_val, dataHistory[num-1]);
    	}
  	tokenise_bang[0]=strtok(bang_val," ");
  	while ((tokenise_bang[n]=strtok(NULL,""))!=NULL)
              n++;
  	tokenise_bang[n]=NULL;
  	strcpy(bang_val, tokenise_bang[1]);
  	printf("%s\n", bang_val );
  	strcpy(inBuffer, bang_val);
}

void environment()
{
	int i =1, index=0;
  	char env_val[1000], *value;
  	while(args[1][i]!='\0')
        {
       		env_val[index]=args[1][i];
                index++;
                i++;
        }
  	env_val[index]='\0';
  	value=getenv(env_val);

  	if(!value)
      		printf("\n");
  	else 
		printf("%s\n", value);
}

void setEnvironmentVariables()
{  
	int n=1;
	char *left_right[100];
	if(args[1]==NULL)
      	{
        	char** env;
          	for (env = cEnvironment; *env != 0; env++)
          	{
            		char* value = *env;
            		printf("declare -x %s\n", value);    
          	}  
         	return; 
      	}
	left_right[0]=strtok(args[1],"=");
	while ((left_right[n]=strtok(NULL,"="))!=NULL)
      		n++;
	left_right[n]=NULL;
	setenv(left_right[0], left_right[1], 0);
}