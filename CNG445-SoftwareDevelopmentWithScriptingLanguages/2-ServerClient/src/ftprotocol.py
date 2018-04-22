import socket


class FTSender:
    def __init__(self, socket):
        '''
        :type socket: socket.socket
        '''
        self.socket = socket

    def send(self, data):
        data_len = str(len(data))
        data_len = (16 - len(data_len)) * "0" + str(len(data))
        self.socket.send(data_len.encode("utf-8"))
        if type(data) == str:
            data = data.encode("utf-8")
        self.socket.send(data)


class FTReceiver:
    def __init__(self, socket):
        '''
        :type socket: socket.socket
        '''
        self.socket = socket

    def recv(self, with_decode=True):
        data = self.socket.recv(16).decode("utf-8")
        data_len = int(data)
        data = self.socket.recv(data_len)
        if with_decode:
            return data.decode("utf-8")
        return data


class FTHost:
    def __init__(self, addr=None, port=None, socket=None):
        '''
        :type addr: str
        :type port: int
        :type socket: socket.socket
        '''
        self.addr = addr
        self.port = port
        self.socket = socket

        self.sender = FTSender(self.socket)
        self.recver = FTReceiver(self.socket)

    def recv(self):
        return self.recver.recv()

    def send(self, data):
        self.sender.send(data)

    def connect(self):
        self.socket.connect((self.addr, self.port))
