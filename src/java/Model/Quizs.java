/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ACER
 */
public class Quizs {
    String code, name;
    boolean pub;

    public Quizs(String code, String name, boolean pub) {
        this.code = code;
        this.name = name;
        this.pub = pub;
    }

    public Quizs() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }
    
    
}
