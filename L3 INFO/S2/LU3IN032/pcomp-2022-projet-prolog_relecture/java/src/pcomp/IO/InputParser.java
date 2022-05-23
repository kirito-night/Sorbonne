package pcomp.IO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import pcomp.Gui.Question;

public class InputParser {
	
	
	public static String gettext(JFrame frame) throws IOException {
		JFileChooser chooser = new JFileChooser( new File("."));
        chooser.setMultiSelectionEnabled(false);
        
        chooser.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                return;
            }
        });

        FileFilter txtFilter = new FXFileFilter("pl");
        
        chooser.setFileFilter(txtFilter);
        chooser.setDialogTitle("Choisissez un .pl");
        int choix = chooser.showOpenDialog(frame);
        if (choix == JFileChooser.APPROVE_OPTION) {
           File file = chooser.getSelectedFile();
            try
            {
              // Le fichier d'entrée   
              // Créer l'objet File Reader
              FileReader fr = new FileReader(file);  
              // Créer l'objet BufferedReader        
              BufferedReader br = new BufferedReader(fr);  
              StringBuffer sb = new StringBuffer();    
              String line;
              while((line = br.readLine()) != null)
              {
                // ajoute la ligne au buffer
                sb.append(line);      
                sb.append("\n");     
              }
              fr.close();    
              return sb.toString(); 
            }
            catch(IOException e)
            {
              throw new IOException("Lecture fichier:\n"+e.getMessage());
            }
        } else {
            throw new IOException("Aucun fichier choisie");
        }
	}
}
