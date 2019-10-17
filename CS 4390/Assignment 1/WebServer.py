from socket import*
import sys
def main():
    #import socket module
    serverSocket = socket(AF_INET, SOCK_STREAM)
    #Prepare a server socket
    serverHost = 'localhost'
    recvBuffer = 1024
    serverPort = 8080
    try:
        serverSocket.bind(('', serverPort))
        serverSocket.listen(1)
        print 'The server is ready to receive...'
    except error as msg:
        print('Bind failed. Error: ' + str(msg[0]) + 'Message: ' + msg[1])
        sys.exit()
    while True:
        #Establish the connection
        connectionSocket, addr = serverSocket.accept()
        try:
            message = connectionSocket.recv(recvBuffer)
            print(message)

            filename = message.split()[1]
            print filename
            f = open(filename[1:])

            outputdata = f.read() #read file and store it's contents into outputData
            print outputdata

            connectionSocket.send('\nHTTP/1.1 200 OK\n\n') #send one HTTP header line into socket
            connectionSocket.send(outputdata) #Send the content of the requested file to the client
            connectionSocket.close()
        except IOError:
            print 'IOERROR'
            #Send response message for file not found
            connectionSocket.send('\nHTTP/1.1 404 Not Found\n\n')
            connectionSocket.send('\nHTTP/1.1 404 Not Found\n\n')
    serverSocket.close()
if __name__ == '__main__':
    main()