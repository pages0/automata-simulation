/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discretefiniteautomata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author reilly
 */
public class DiscreteFiniteAutomata {
    
    private Boolean initialized;
    private String [] states;
    private char [] language;
    private String [][] transitions;
    private String startState;
    private String currentState;
    private String [] acceptStates;
    
    private HashMap <Character,Integer> languageMap;
    private HashMap <String,Integer> stateMap;
    
    /**
     * 
     * @param states A string array representing the states 
     * @param language A char array representing the language
     * @param transitions A String Array representing the state transitions
     * @param startState  The start state
     */
    public DiscreteFiniteAutomata(String [] states, char[] language,
            String[][]transitions,String startState,String [] acceptStates){
        this.initialized=true;
        this.states=states;
        this.language=language;
        this.transitions=transitions;
        this.startState=startState;
        this.currentState=startState;
        this.acceptStates=acceptStates;
        
        for (int i =0; i< language.length; i++){
            languageMap.put(new Character(language[i]), i);
            stateMap.put(states[i], i);
        }
        
    }
    
    /**
     * @precondition char is from the language
     * @param languageChar is a char from the language
     */
    public void readChar(char languageChar){
        Integer inputIndex = languageMap.get(new Character(languageChar));
        Integer stateIndex = stateMap.get(currentState);
        
        this.currentState= transitions[stateIndex][inputIndex];
    }
    
    public Boolean inAcceptState(){
        for (String acceptState : acceptStates) {
            if (currentState.equals(acceptStates)){
                return true;
            }
        }
        return false;
    }
            
    public void reset(){
        this.currentState=startState;
    }
    
    public Boolean AcceptString(String check){
        for (int i=0; i< acceptStates.length; i++ ){
            readChar(acceptStates[i].charAt(i));
        }
        return inAcceptState();
    }
            
}
