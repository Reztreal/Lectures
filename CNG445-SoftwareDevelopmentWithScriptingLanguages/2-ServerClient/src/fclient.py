import threading
import ftprotocol
import random
import utils


class FClientHandler(threading.Thread):
    def __init__(self, client_socket, client_addr, questions, test_cases):
        '''
        This class handles the client after the connection is established.

        :type client_socket: socket.socket
        :type client_addr: tuple
        '''
        threading.Thread.__init__(self)
        self.client = ftprotocol.FTHost(client_addr[0], client_addr[1], client_socket)

        self.questions = questions
        self.test_cases = test_cases

    def get_testcases(self, q_id):
        return [item for item in self.test_cases if item[0] == q_id]

    def pack_a_question_to_send(self):
        questions = self.questions
        data_to_send = dict()
        data_to_send["question"] = ""
        data_to_send["input"] = list()
        data_to_send["output"] = list()

        # Get random question
        self.current_question = questions[random.randint(0, len(questions) - 1)]

        q_id = self.current_question[0]
        question = self.current_question[1]
        data_to_send["question"] = question
        data_to_send["input"] = list()
        data_to_send["output"] = list()

        # Get answers of the question
        testcases = self.get_testcases(q_id)
        for i in testcases:
            data_to_send["input"].append(i[1])
            data_to_send["output"].append(i[2])

        return utils.dict_to_str(data_to_send)

    def run(self):
        while True:
            # Send the question|input1#output1|input2#output2
            self.client.send(self.pack_a_question_to_send())

            # Wait for the answer
            r_data = self.client.recv()

            # If skip, skip this loop
            if r_data == "SKIP":
                continue

            if r_data == "EXIT":
                break

        print("Connection stopped :", self.client.addr)
