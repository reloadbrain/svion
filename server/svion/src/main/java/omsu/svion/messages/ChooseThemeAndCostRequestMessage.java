package omsu.svion.messages;

import omsu.svion.game.model.PlayerModel;
import omsu.svion.questions.Cost;
import omsu.svion.questions.Theme;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 12.05.14.
 */
public class ChooseThemeAndCostRequestMessage extends MessageFromServer {


    public ChooseThemeAndCostRequestMessage(List<PlayerModel> players, Map<Theme, List<Cost>> availableThemesAndCosts, boolean answer, Integer newRound, int currentQuestion, String answeringUser,Integer rightAnswer) {
        this.players = players;
        this.availableThemesAndCosts = availableThemesAndCosts;
        this.answer = answer;
        this.newRound = newRound;
        this.currentQuestion = currentQuestion;
        this.className = ChooseThemeAndCostRequestMessage.class.getCanonicalName();
        this.answeringUser = answeringUser;
        this.rightAnswer = rightAnswer;
    }
    public ChooseThemeAndCostRequestMessage() {
        this.className = ChooseThemeAndCostRequestMessage.class.getCanonicalName();
    }

    private List<PlayerModel> players;
    private Map<Theme,List<Cost>> availableThemesAndCosts;
    private boolean answer;
    private Integer newRound;
    private int currentQuestion;
    private String answeringUser;
    private Integer rightAnswer;

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnsweringUser() {
        return answeringUser;
    }

    public void setAnsweringUser(String answeringUser) {
        this.answeringUser = answeringUser;
    }

    public Map<Theme, List<Cost>> getAvailableThemesAndCosts() {
        return availableThemesAndCosts;
    }

    public boolean isAnswer() {
        return answer;
    }

    public Integer getNewRound() {
        return newRound;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public List<PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerModel> players) {
        this.players = players;
    }

    public void setAvailableThemesAndCosts(Map<Theme, List<Cost>> availableThemesAndCosts) {
        this.availableThemesAndCosts = availableThemesAndCosts;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setNewRound(Integer newRound) {
        this.newRound = newRound;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    
}
