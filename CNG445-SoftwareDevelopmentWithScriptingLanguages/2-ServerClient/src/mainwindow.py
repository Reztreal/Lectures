from tkinter import *


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        '''

        :type MainWindow: Frame
        :return:
        '''
        # Frame.__init__(MainWindow)
        MainWindow.master.title("Question")

        MainWindow.master.rowconfigure(0, weight=1)
        MainWindow.master.columnconfigure(0, weight=1)
        MainWindow.grid(sticky=W + E + N + S)

        # ------> ROW 0
        # COLUMN 0 to 3
        self.lbl_question = Label(MainWindow, text="Question will be written here...")
        self.lbl_question.grid(row=0, column=0, rowspan=1, columnspan=5)

        # ------> ROW 1 to 8
        # COLUMN 0 to 3
        self.txt_answer = Text(MainWindow, height=10, width=65)
        self.txt_answer.grid(row=1, column=0, rowspan=8, columnspan=5)

        # ------> ROW 9
        # COLUMN 0
        self.rbt_normal_score = Radiobutton(MainWindow, text="Overall Score Only", value=0)
        self.rbt_normal_score.grid(row=9, column=0, rowspan=1, columnspan=1)

        # COLUMN 1
        self.rbt_detailed_score = Radiobutton(MainWindow, text="Overall Score With Details", value=1)
        self.rbt_detailed_score.grid(row=9, column=1, rowspan=1, columnspan=1)

        # COLUMN 2
        self.btn_submit = Button(MainWindow, text="Submit")
        self.btn_submit.grid(row=9, column=2, rowspan=1, columnspan=1)

        # COLUMN 3
        self.btn_skip = Button(MainWindow, text="Skip")
        self.btn_skip.grid(row=9, column=3, rowspan=1, columnspan=1)

        # COLUMN 4
        self.btn_exit = Button(MainWindow, text="Exit")
        self.btn_exit.grid(row=9, column=4, rowspan=1, columnspan=1)
