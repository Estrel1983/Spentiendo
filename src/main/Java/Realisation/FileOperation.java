package Realisation;

import Data.Word;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileOperation {
    private static Path curDir;
    private static String deeplKey;
    static {
        curDir = Paths.get(System.getProperty("user.dir"));
    }
    //getting Deepl key
    static {
        Path deeplPath = curDir.resolve("deeplKey.txt");
        if (deeplPath.toFile().exists()){
            try (BufferedReader fr = new BufferedReader(new FileReader(deeplPath.toFile()))){
                if (fr.ready()){
                    deeplKey = fr.readLine();
                }
                else deeplKey = "EmptyFile";
            } catch (IOException e) {}
        }
        else deeplKey = "FileNotExist";
    }

    public static String getDeeplKey () {
        return deeplKey;
    }

    public static void writeWord (Word word) throws IOException {
        File file = new File(curDir + "/dictionary.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(word.stringWrFormater());
        bw.flush();
        bw.close();
    }
    public static ArrayList <Word> getWordList (boolean isMainDict) throws IOException {
        ArrayList <Word> wordList = new ArrayList<>();
        File file = new File(curDir + (isMainDict ? "/dictionary.txt" : "/oldDictionary.txt"));
        if (!file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(file), "UTF-8") )){
                bw.write("coche/машина/0\n");
                bw.write("casa/дом/0");
                bw.flush();
            }
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))){
            while (br.ready()){
                Word nextWord = new Word(br.readLine());
                if (!isMainDict)
                    nextWord.setRemembered();
                wordList.add(nextWord);
            }
        } catch (Exception e){
            throw e;
        }
        return wordList;
    }
    public static void putWordList (ArrayList <Word> wordList, boolean isMainDict) throws IOException{
        File file = new File(curDir + (isMainDict ? "/dictionary.txt" : "/oldDictionary.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(file, false), "UTF-8") );
        for (Word word : wordList){
            bw.write(word.stringWrFormater()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
