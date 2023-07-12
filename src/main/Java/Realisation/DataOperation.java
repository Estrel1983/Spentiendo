package Realisation;

import Data.AnswerCorrectness;
import Data.Word;

import java.io.IOException;
import java.util.ArrayList;

public class DataOperation {
    private static int WordCounter;
    private static ArrayList <Word> WordLearningList;
    private static ArrayList <Word> WordFamiliarList;
    private static Word curWord;
    static {
        try {
            WordLearningList = FileOperation.getWordList(true);
            WordFamiliarList = FileOperation.getWordList(false);
            WordCounter = 0;
        } catch (IOException e) {
            //TODO;
        }
    }
    private static Word getRandomWord (){
        Word RandomWord = WordCounter==19 ? WordFamiliarList.get((int)Math.random() * WordFamiliarList.size()) :
                                            WordLearningList.get((int) (Math.random() * WordLearningList.size()));
        if (WordCounter!=19){
            WordLearningList.remove(RandomWord);
        }
        WordCounter = WordCounter == 19 ? 0 : WordCounter + 1;
        return RandomWord;
    }
    private static void setCurWord(){
        curWord = getRandomWord();
    }
    public static String getCurTransl(){
                if (curWord == null)
            curWord = getRandomWord();
        return curWord.getTranslate();
    }

    public static void putNewWord (String spanish, String translation){
        if (spanish.isEmpty() || translation.isEmpty()){
            //TODO Сделать эксепшн
            return;
        }
            WordLearningList.add(new Word(spanish, translation));

    }

    public static AnswerCorrectness isCorrectTranslation (String spanish){
        AnswerCorrectness curAnswer = new AnswerCorrectness(curWord);
        if (spanish.isEmpty()) {
            //TODO Сделать эксепшн
            return null;
        }
        if (spanish.equals(curWord.getSpanish())){
            curAnswer.setCorrect(true);
            if (curWord.isRemembered()){
                setCurWord();
                return curAnswer;
            }
            curWord.setNCorrectAnswers(true);
            if (curWord.isRemembered())
                WordFamiliarList.add(curWord);
            else
                WordLearningList.add(curWord);
            setCurWord();
            return curAnswer;
        }
        curAnswer.setCorrect(false);
        curWord.setNCorrectAnswers(false);
        WordLearningList.add(curWord);
        setCurWord();
        return curAnswer;
    }
    public static void makeRemembered(){
        if (!curWord.isRemembered()) {
            curWord.setRemembered();
            WordFamiliarList.add(curWord);
            setCurWord();
        }
        else setCurWord();
    }
    public static void saveAll(){
        if (!curWord.isRemembered())
            WordLearningList.add(curWord);
        try {
            FileOperation.putWordList(WordLearningList, true);
            FileOperation.putWordList(WordFamiliarList, false);
        } catch (IOException e){
            System.out.println("Something is wrong. Progress can't be saved");
        }
    }

}
