package Realisation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Data.Word;

public class FileOperation {
    private static Path curDir;
    static {
        curDir = Paths.get(System.getProperty("user.dir"));
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
        File file = new File(curDir + (isMainDict ? "/dictionary.txt" : "oldDictionary.txt"));
        if (!file.exists()){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter (file) )){
                bw.write("coche/car/0\n");
                bw.write("casa/hause/0");
                bw.flush();
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            while (br.ready()){
                wordList.add(new Word(br.readLine()));
            }
        } catch (Exception e){
            throw e;
        }
        return wordList;
    }
    public static void putWordList (ArrayList <Word> wordList, boolean isMainDict) throws IOException{
        File file = new File(curDir + (isMainDict ? "/dictionary.txt" : "oldDictionary.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        for (Word word : wordList){
            if (!word.isRemembered())
            bw.write(word.stringWrFormater()+"\n");
        }
        bw.flush();
        bw.close();
    }
}
