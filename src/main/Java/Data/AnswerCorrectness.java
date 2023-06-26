package Data;

public class AnswerCorrectness {
    private String Spanish;
    private  String Translate;
    private boolean isCorrect;

    public String getSpanish() {
        return Spanish;
    }

    public void setSpanish(String spanish) {
        Spanish = spanish;
    }

    public String getTranslate() {
        return Translate;
    }

    public void setTranslate(String translate) {
        Translate = translate;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public AnswerCorrectness (Word word){
        this.Spanish = word.getSpanish();
        this.Translate = word.getTranslate();
    }
}
