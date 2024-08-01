/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */
public class Question {
    String id, id_quiz, content, correct;
    String[] select;

    public Question() {
    }

    public Question(String id, String id_quiz, String content, String correct, String[] select) {
        this.id = id;
        this.id_quiz = id_quiz;
        this.content = content;
        this.correct = correct;
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(String id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String[] getSelect() {
        return select;
    }

    public void setSelect(String[] select) {
        this.select = select;
    }
    
    
}
