package Realisation;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;

public class APIOperations {
    private static Translator translator;
    public APIOperations (){
        if (translator == null){
            translator = new Translator(FileOperation.getDeeplKey());
        }
    }
    public String getTranslation (String textForTr, String language){
        String Answer = null;
        String lang;
        try {
            switch (language){
                case "Рус":
                    lang = "ru";
                    break;
                case "Eng":
                default:
                    lang = "en-GB";
            }
            TextResult tr = translator.translateText(textForTr, "es", lang);
            Answer =  tr.getText();
        } catch (DeepLException e) {
            System.out.println("ошибка" + " " + e.getLocalizedMessage());
        } catch (InterruptedException e) {
            System.out.println("ошибка" + e.getMessage());
        }
        return Answer;
    }
}
