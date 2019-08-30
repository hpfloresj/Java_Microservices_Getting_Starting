package com.hpfloresj.microservices.quizgame.bootstrap;

import com.hpfloresj.microservices.quizgame.domain.entity.Answer;
import com.hpfloresj.microservices.quizgame.domain.entity.Question;
import com.hpfloresj.microservices.quizgame.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class DataLoad {

    @Bean
    public CommandLineRunner loadData(QuestionRepository repository) {
        return (args) -> {
            // Fundamentals
            Question question1 = new Question("Which package contains Date/Time (JSR 310) API in Java 8?", null);
            Answer a11 = new Answer("A. java.time", true, question1);
            Answer a12 = new Answer("B. java.util.time", false, question1);
            Answer a13 = new Answer("C. java.timedate", false, question1);
            Answer a14 = new Answer("D. java.util.calendar", false, question1);
            question1.setAnswers(new ArrayList<Answer>(){{add(a11); add(a12); add(a13); add(a14);}});

            Question question2 = new Question("Which method can be used to check null on an Optional variable in Java 8", null);
            Answer a21 = new Answer("A. isPresent()", true, question2);
            Answer a22 = new Answer("B. isNullable()", false, question2);
            Answer a23 = new Answer("C. isPresentable()", false, question2);
            Answer a24 = new Answer("D. isNotNull", false, question2);
            question2.setAnswers(new ArrayList<Answer>(){{add(a21); add(a22); add(a23); add(a24);}});

            Question question3 = new Question("In Java 8 Interfaces, methods can be", null);
            Answer a31 = new Answer("A. abstract", false, question3);
            Answer a32 = new Answer("B. default", false, question3);
            Answer a33 = new Answer("C. all", true, question3);
            Answer a34 = new Answer("D. none", false, question3);
            question3.setAnswers(new ArrayList<Answer>(){{add(a31); add(a32); add(a33); add(a34);}});

            Question question4 = new Question("What is the return type of lambda expression?", null);
            Answer a41 = new Answer("A. String", false, question4);
            Answer a42 = new Answer("B. Object", false, question4);
            Answer a43 = new Answer("C. void", false, question4);
            Answer a44 = new Answer("D. Function", true, question4);
            question4.setAnswers(new ArrayList<Answer>(){{add(a41); add(a42); add(a43); add(a44);}});

            Question question5 = new Question("Which is aggregate operation in Java 8", null);
            Answer a51 = new Answer("A. filter", false, question5);
            Answer a52 = new Answer("B. map", false, question5);
            Answer a53 = new Answer("C. forEach", false, question5);
            Answer a54 = new Answer("D. All", true, question5);
            question5.setAnswers(new ArrayList<Answer>(){{add(a51); add(a52); add(a53); add(a54);}});

            Question question6 = new Question("What is the result of the following class?",
                    "1: public class _C {\n" +
                                "2: private static int $;\n" +
                                "3: public static void main(String[] main) {\n" +
                                "4: String a_b;\n" +
                                "5: System.out.print($);\n" +
                                "6: System.out.print(a_b);\n" +
                                "7: } }");
            Answer a61 = new Answer("A. Compiler error on line 1", false, question6);
            Answer a62 = new Answer("B. Compiler error on line 2", false, question6);
            Answer a63 = new Answer("C. Compiler error on line 4", false, question6);
            Answer a64 = new Answer("D. Compiler error on line 5", false, question6);
            Answer a65 = new Answer("E. Compiler error on line 6", true, question6);
            question6.setAnswers(new ArrayList<Answer>(){{add(a61); add(a62); add(a63); add(a64); add(a65);}});

            Question question7 = new Question("What is the result of the following class?","1:class Test {\t\n" +
                    "2:\tpublic static void main(String[] args) {\n" +
                    "3:\t\tfor(int i = 0; i< 20; i++) {\n" +
                    "4:\t\t\tSystem.out.println(i);\t\n" +
                    "5:\t\t}\t\n" +
                    "5:\t\tSystem.out.println(i);\n" +
                    "7:\t}\n" +
                    "8:}");
            Answer a71 = new Answer("A. Compiler error", true, question7);
            Answer a72 = new Answer("B. Compiler and execute, printing 0 to 19 and an additional 19", false, question7);
            Answer a73 = new Answer("C. Compiler and execute, printing 0 to 19, after happens an error in runtime", false, question7);
            Answer a74 = new Answer("D. None", false, question7);
            question7.setAnswers(new ArrayList<Answer>(){{add(a71); add(a72); add(a73); add(a74);}});

            Question question8 = new Question("What is the result of the following class?", "1:class Test {\t\n" +
                    "2:  static int i = 3;\n" +
                    "3:\n" +
                    "4:\tpublic static void main(String[] a) {\n" +
                    "5:\t\tfor(new Test().i = 10; new Test().i < 100; \n" +
                    "6:          new Test().i++) {\n" +
                    "7:\t\t\t\tSystem.out.println(i);\n" +
                    "8:\t\t}\n" +
                    "9:\t}\n" +
                    "10:} ");
            Answer a81 = new Answer("A. Compiler error on line 4", false, question8);
            Answer a82 = new Answer("B. Compiler error on line 5", false, question8);
            Answer a83 = new Answer("C. Compile and print 100 times the number 3", false, question8);
            Answer a84 = new Answer("D. Compile and print the numbers 10 to 99", true, question8);
            question8.setAnswers(new ArrayList<Answer>(){{add(a81); add(a82); add(a83); add(a84);}});

            Question question9 = new Question("What is the result of the following class?", "1:class Test {\t\n" +
                    "2:  int Test = 305;\n" +
                    "3:\n" +
                    "4:\tvoid Test() {\n" +
                    "5:\t\tSystem.out.println(Test);\n" +
                    "6:\t}\t\n" +
                    "7:\n" +
                    "8:\tpublic static void main(String[] args) {\n" +
                    "9\t\tnew Test();\n" +
                    "10:\t}\n" +
                    "11:} ");

            Answer a91 = new Answer("A. Compiler error on lines 2,4,5 and 6", false, question9);
            Answer a92 = new Answer("B. Compiler error on line 5", false, question9);
            Answer a93 = new Answer("C. Compiler error on lines 2,4 and 6", false, question9);
            Answer a94 = new Answer("D. Compile and not print nothing", true, question9);
            Answer a95 = new Answer("E. Compile and print 305", false, question9);
            question9.setAnswers(new ArrayList<Answer>(){{add(a91); add(a92); add(a93); add(a94); add(a95);}});

            Question question10 = new Question("Bytecode is in a file with extension?", null);
            Answer a101 = new Answer("A. .bytecode", false, question10);
            Answer a102 = new Answer("B. .bytes", false, question10);
            Answer a103 = new Answer("C. .class", true, question10);
            Answer a104 = new Answer("D. .exe", false, question10);
            Answer a105 = new Answer("E. .java", false, question10);
            question10.setAnswers(new ArrayList<Answer>(){{add(a101); add(a102); add(a103); add(a104); add(a105);}});

            repository.save(question1);
            repository.save(question2);
            repository.save(question3);
            repository.save(question4);
            repository.save(question5);
            repository.save(question6);
            repository.save(question7);
            repository.save(question8);
            repository.save(question9);
            repository.save(question10);
        };
    }
}
