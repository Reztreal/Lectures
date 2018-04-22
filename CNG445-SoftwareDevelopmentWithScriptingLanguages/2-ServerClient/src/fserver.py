import socket
from fclient import FClientHandler
import fdatabase


class FServer:
    def __init__(self):
        self.server_addr = "0.0.0.0"
        self.server_port = 9999

        self.db = fdatabase.FDatabase()

    # ---> Running Mode
    def run(self):
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind((self.server_addr, self.server_port))

        server_socket.listen(5)

        while True:
            client_socket, client_addr = server_socket.accept()
            client_thread = FClientHandler(client_socket, client_addr, self.db.get_questions(),
                                           self.db.get_all_testcases())
            client_thread.start()

            print("New connection :", client_addr)

    # ---> Administrator Mode
    def add_question(self, description):
        self.db.insert_question(description)

    def update_question(self, q_id, desc, active):
        self.db.update_question(q_id, desc, active)

    def add_testcase(self, q_id, inp, out):
        self.db.insert_testcase(out, inp, q_id)

    def update_testcase(self, q_id, old_inp, inp, out):
        self.db.update_testcase(q_id, old_inp, inp, out)

    def delete_testcase(self, q_id, inp):
        self.db.delete_testcase(q_id, inp)

    def run_admin_mode(self):
        choice = -1

        while choice != 0:
            choice = self.print_menu()

            if choice == 1:
                print("------> Add a new question")
                qdesc = input("# Enter question description\n: ")
                self.add_question(qdesc)
                print("New question is added to db.")

            elif choice == 2:
                print("------> Update an existing question")
                q_id = input("Enter corresponding question id\n: ")
                if not self.db.is_question_exists(q_id):
                    print("There is no question with this id.")
                    continue
                desc = input("# Enter question description (Empty to keep it same)\n: ")
                active = input("# Enter 1 for active, 0 for deactive (Empty to keep it same)\n: ")
                self.update_question(q_id, desc, active)
                print("Question is updated.")

            elif choice == 3:
                print("------> Add a new test case")
                q_id = input("Enter corresponding question id\n: ")
                if not self.db.is_question_exists(q_id):
                    print("There is no question with this id.")
                    continue
                inp = input("Enter input for the testcase\n: ")
                out = input("Enter output for the testcase\n: ")
                self.add_testcase(q_id, inp, out)
                print("New testcase is added to db.")

            elif choice == 4:
                print("------> Update an existing testcase")
                q_id = input("# Enter question id\n: ")
                old_inp = input("# Enter input in the following format (arg1, arg2, ..., argn)\n: ")
                if not self.db.is_testcase_exists(q_id, old_inp):
                    print("There is no testcase with this key.")
                    continue
                inp = input("# Enter new input (Empty to keep it same\n: ")
                out = input("# Enter new output (Empty to keep it same\n: ")
                self.update_testcase(q_id, old_inp, inp, out)
                print("Testcase is updated.")

            elif choice == 5:
                print("------> Delete an existing testcase")
                q_id = input("# Enter question id\n: ")
                inp = input("# Enter input in the following format (arg1, arg2, ..., argn)\n: ")
                if not self.db.is_testcase_exists(q_id, inp):
                    print("There is no testcase with this key.")
                    continue
                self.delete_testcase(q_id, inp)
                print("Existing testcase is deleted.")

        self.db.conn.close()

    def print_menu(self):
        print("------> Admin Panel")
        print("[1] Add a new question")
        print("[2] Update an existing question")
        print("[3] Add a new test case")
        print("[4] Update an existing testcase")
        print("[5] Delete an existing testcase")
        print("[0] Exit")

        choice = input("Enter your choice : ")
        while not choice.isdigit() or (5 < int(choice) < 0):
            choice = input("Please enter a valid option : ")

        return int(choice)


if __name__ == "__main__":
    mode = input("Enter 1 for admin mode. Enter anything to run the server : ")

    server = FServer()
    if mode == "1":
        server.run_admin_mode()
    else:
        server.run()
