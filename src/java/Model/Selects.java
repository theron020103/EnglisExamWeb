/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */
public class Selects {
    String answer, option;
    boolean result;
    Question q;

    public Selects() {
    }

    public Selects(String answer, String option, boolean result, Question q) {
        this.answer = answer;
        this.option = option;
        this.result = result;
        this.q = q;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    
    
}
