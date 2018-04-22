from tkinter import *
from tkinter import messagebox
import mainwindow
import sys
import socket
import ftprotocol
import utils


class FQuestion(Frame):
    def __init__(self):
        Frame.__init__(self)

        self.ui = mainwindow.Ui_MainWindow()
        self.ui.setupUi(self)
        self.init_FQuestion()

    def init_FQuestion(self):
        # Button bindings
        self.ui.btn_submit.configure(command=self.submit)
        self.ui.btn_exit.configure(command=self.exit)
        self.ui.btn_skip.configure(command=self.skip)

        # Radio button bindings
        self.is_score_type_detailed = IntVar()
        self.ui.rbt_normal_score.configure(variable=self.is_score_type_detailed)
        self.ui.rbt_detailed_score.configure(variable=self.is_score_type_detailed)
        self.ui.rbt_normal_score.select()

        # Server connection
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client = ftprotocol.FTHost("0.0.0.0", 9999, self.socket)
        self.client.connect()

        # Receive first question
        self.receive_and_process_new_question()

    def receive_and_process_new_question(self):
        data = self.client.recv()
        self.current_question = utils.str_to_dict(data)

        self.set_question(self.current_question["question"])
        self.ui.txt_answer.insert(INSERT, "")

    def set_question(self, new_text):
        self.ui.lbl_question.configure(text=new_text)

    def get_answer(self):
        return self.ui.txt_answer.get("1.0", "end-1c")

    def skip(self):
        self.client.send("SKIP")
        self.receive_and_process_new_question()

    def show_overall_score(self):
        if self.get_answer() == self.current_question["output"][0]:
            if not self.is_score_type_detailed.get():
                res = self.show_messagebox("Overall Score", "Overall Score: 100%")
            else:
                res = self.show_messagebox("Overall Score with Details", "Test Case 1: " +
                                           self.current_question["input"][0] + " = " +
                                           self.current_question["output"][0] + " TRUE")
        else:
            if not self.is_score_type_detailed.get():
                res = self.show_messagebox("Overall Score", "Overall Score: 0%")
            else:
                res = self.show_messagebox("Overall Score with Details", "Test Case 1: " +
                                           self.current_question["input"][0] + " = " +
                                           self.current_question["output"][0] + " FALSE")
        return res

    def submit(self):
        res = self.show_overall_score()
        if res:
            self.client.send("NEXT")
            self.receive_and_process_new_question()
        else:
            self.exit()

    def exit(self):
        self.client.send("EXIT")
        self.destroy()

    def show_messagebox(self, title, msg):
        msg += "\nDo you want to continue?"
        res = messagebox.askyesno(title, msg)
        return res


if __name__ == "__main__":
    myApp = FQuestion()

    try:
        myApp.mainloop()
    except:
        sys.exit(1)
