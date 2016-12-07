package map.npc.answer;

import java.util.List;

public abstract class Answer {
	private String answerText;
	private List<String> possibleRequests;
	
	public String getAnswerText(){
		return answerText;
	}
	public List<String> getPossibleRequests(){
		return possibleRequests;
	}
}

