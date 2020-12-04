package org.academiadecodigo.tailormoons.world_of_ac_server.game;

public class Question {
    private int questionNumber;
    private String question;
    private String correctAnswer;
    private String answerA;
    private String answerB;
    private String answerD;
    private String answerC;

    public Question(int questionNumber, String question, String answerA, String answerB, String answerC, String answerD, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer(int number) {
        String[] answers = new String[]{answerA, answerB, answerC, answerD};
        return answers[number];
    }
}
