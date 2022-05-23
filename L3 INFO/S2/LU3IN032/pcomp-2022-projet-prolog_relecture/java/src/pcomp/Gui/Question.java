package pcomp.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import javax.swing.*; 
import java.awt.*;
/**
 * Cette classe permet de crée des interfaces simpliste | Version Personel V1.2.1
 * @author François-Xavier Drouard  
 */
public class Question {
    /**
	 * attribut correspondant a la fenetre principale
	 */
    protected JFrame q;
    /**
	 * attribut correspondant au texte pour pouvoir le modifier et non le recréér
	 */
    protected JEditorPane pane;
    /**
	 * Constructeur d'un buffer de fenetre
	 * @param s : le titre de la fenetre
	 */
    private Question(String s){  
        q=new JFrame(s);
        pane=new JEditorPane();
        pane.setContentType("text/plain");
        q.setSize(1920, 1080); 
        q.setLocationRelativeTo(null);
    } 
    /**
	 * Modification de la taille de la fenetre
	 * @param x : nombre colonne
	 * @param y : nombre ligne
	 */
    public void setSize(int x, int y){
        q.setSize(x, y);
        q.setLocationRelativeTo(null);
    }
    /**
	 * Permet de demander a l'utilisateur une valeur
	 * @param question : La question
	 * @return La reponse de l'utilisateur
	 */
    public static String getrep(String question, String defaultval, String titre){
        Toolkit.getDefaultToolkit().beep();
        Question temp=new Question(titre);
        temp.q.setTitle(titre);
        String rep="";
        String tmp=JOptionPane.showInputDialog(temp.q,question);
        temp.close();
        if (tmp==null) {return defaultval;}
        rep=rep+tmp;
        if (rep.length()==0) {return defaultval;}
        return rep;
    }
    /**
	 * Ferme le buffer de la fenetre du Question. imperatif avant la fin du main!
	 */
    public void close(){
        q.dispose();
    }
    /**
	 * Permet de vider le texte d'une fenetre editor
	 */
    public void wipe(){
        pane.selectAll();
        pane.replaceSelection("");
    }
    /**
	 * Permet d'afficher un texte sur une fenetre
	 * @param text : Le texte a afficher
	 */
    public void print(String text){
        pane.setText(text);  
        q.setContentPane(pane);  
        q.setVisible(true);  
    }  
    /**
	 * Affiche une fenetre d'avertissement et un beep
	 * @param text : Le message
	 */
    public static void warn(String text){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("ATTENTION!");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        dialog.dispose();
    }
    /**
	 * Affiche une information classique
	 * @param text : Le message
	 */
    public static void info(String text){
        Question temp=new Question("Information Importante!");
        JOptionPane.showMessageDialog(temp.q,text);
        temp.q.setAlwaysOnTop(true);
        temp.q.dispose();
    }
    
    /**
	 * Permet de demander a l'utilisateur s'il veux continuer ou non
	 * @param question : La question
	 * @return Si oui ou non
	 */
    public static boolean choix(String text) {
    	int dialogButton = JOptionPane.YES_NO_OPTION;
    	int dialogResult = JOptionPane.showConfirmDialog (null, text,"Choix",dialogButton);
    	if(dialogResult == JOptionPane.YES_OPTION){
    		  return true;
    	}else {
    		return false;
    	}
    }
} 
