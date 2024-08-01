package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class result {
    private String id_taker;
    private String id_quiz;
    private int numOfCorrect;
    private LocalDate date;
    private LocalTime time;
    private double point;

    public result() {
    }

    public result(String id_taker, String id_quiz, int numOfCorrect, LocalDate date, LocalTime time, double point) {
        this.id_taker = id_taker;
        this.id_quiz = id_quiz;
        this.numOfCorrect = numOfCorrect;
        this.date = date;
        this.time = time;
        this.point = point;
    }

    public String getId_taker() {
        return id_taker;
    }

    public void setId_taker(String id_taker) {
        this.id_taker = id_taker;
    }

    public String getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(String id_quiz) {
        this.id_quiz = id_quiz;
    }

    public int getNumOfCorrect() {
        return numOfCorrect;
    }

    public void setNumOfCorrect(int numOfCorrect) {
        this.numOfCorrect = numOfCorrect;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    
}
