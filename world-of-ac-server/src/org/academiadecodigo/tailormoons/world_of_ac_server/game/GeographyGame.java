package org.academiadecodigo.tailormoons.world_of_ac_server.game;

import java.util.ArrayList;

public class GeographyGame implements Game {

    private GameManager gameManager;
    private String name = "Geography Game";
    private ArrayList<Question> questions = new ArrayList<>();

    public GeographyGame(GameManager gameManager) {
        this.gameManager = gameManager;
        setup();
    }

    public String getName() {
        return name;
    }

    private void setup() {
        questions.add(new Question(1, "In which country would you be if you were visiting the Taj Mahal?", "A - Pakistan", "B - India", "C - Vietnam", "D - China", "B"));
        questions.add(new Question(2, "In which Scandinavian country would you find fjords?", "A - Norway", "B - Sweden", "C - Finland", "D - Denmark", "A"));
        questions.add(new Question(3, "In which continent is the world’s longest river, the Nile?", "A - Europe", "B - Asia", "C - America", "D - Africa", "D"));
        questions.add(new Question(4,"Baku is the capital city of which eastern European country?", "A - Ukraine", "B - Azerbaijan", "C - Belarus", "D - Georgia", "B"));
        questions.add(new Question(5, "Machu Picchu can be found in which country?", "A - Chile", "B - Bolivia", "C - Peru", "D - Brazil", "C"));
    }

    public int gameLength() {
        return questions.size();
    }

    public String question(int questionNumber) {
        return questions.get(questionNumber).getQuestion();
    }

    @Override
    public String possibleAnswer(int questionNumber, int answer) {
        return questions.get(questionNumber).getAnswer(answer);
    }

    public String correctAnswer(int questionNumber) {
        return questions.get(questionNumber).getCorrectAnswer();
    }


}
