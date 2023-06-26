package Data;

public class Word {
    private int NCorrectAnswers;
    private boolean IsRemembered;
    private String Spanish;
    private String Translate;

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

    public int getNCorrectAnswers() {
        return NCorrectAnswers;
    }

    public boolean isRemembered() {
        return IsRemembered;
    }
    public void setRemembered (){
        this.IsRemembered = true;
    }

    public void setNCorrectAnswers(boolean isCorrectAnswer) {
        if (isCorrectAnswer) {
            this.NCorrectAnswers++;
            if (this.NCorrectAnswers > 3)
                this.IsRemembered = true;
        }
        else
            this.NCorrectAnswers = 0;

    }

    public String stringWrFormater (){
        return Spanish + "/" + Translate + "/" + NCorrectAnswers;
    }
    public Word (String fromFile){
        this.Spanish = fromFile.split("/")[0];
        this.Translate =  fromFile.split("/")[1];
        this.NCorrectAnswers = Integer.parseInt(fromFile.split("/")[2]);
    }
    public Word (String Spanish, String Translate){
        this.Spanish = Spanish;
        this.Translate = Translate;
        this.NCorrectAnswers = 0;
        this.IsRemembered = false;
    }
}
