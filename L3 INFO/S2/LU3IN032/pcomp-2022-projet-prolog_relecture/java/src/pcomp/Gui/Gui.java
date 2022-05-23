package pcomp.Gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import pcomp.Gui.Question;
import pcomp.IO.InputParser;
import pcomp.IO.OutputParser;
import pcomp.prolog.parser.PrologParser;
import javax.swing.SwingConstants;

/**
 * Cette classe permet de crée des interfaces plus poussée que son homologue "Question" | Version V1.5
 * @author François-Xavier Drouard  
 */
public class Gui {

	private JFrame frame;

	/**
	 * Lance La fenetre. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					Question.warn("Erreur Critique: "+e.getMessage());
				}
			}
		});
	}

	/**
	 * Initialise et Lance la fenetre graphique.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Gère la fenetre écrite dans les differentes sections et gere l'interprete, le chargement et déchargement des fichiers et l'affichage des résultats .
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 866, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 862, 25);
		frame.getContentPane().add(toolBar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 23, 862, 536);
		frame.getContentPane().add(tabbedPane);
		
		JPanel acceuil = new JPanel();
		tabbedPane.addTab("Accueil", null, acceuil, null);
		acceuil.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Interpreteur Prolog Par");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(262, 205, 343, 48);
		acceuil.add(lblNewLabel);
		
		JLabel lblCamille = new JLabel("Camille Palisoc et");
		lblCamille.setHorizontalAlignment(SwingConstants.CENTER);
		lblCamille.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCamille.setBounds(262, 254, 343, 29);
		acceuil.add(lblCamille);
		
		JLabel lblFranoisxavierDrouard = new JLabel("Fran\u00E7ois-Xavier Drouard");
		lblFranoisxavierDrouard.setHorizontalAlignment(SwingConstants.CENTER);
		lblFranoisxavierDrouard.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblFranoisxavierDrouard.setBounds(262, 294, 343, 29);
		acceuil.add(lblFranoisxavierDrouard);
		
		JPanel interprete = new JPanel();
		tabbedPane.addTab("Interprete", null, interprete, null);
		interprete.setLayout(null);
		
		TextArea text = new TextArea();
		text.setBounds(0, 0, 857, 291);
		interprete.add(text);
		
		TextArea outtext = new TextArea();
		outtext.setEditable(false);
		outtext.setBounds(0, 342, 857, 150);
		interprete.add(outtext);
		
		JLabel labcon = new JLabel("OutPut");
		labcon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labcon.setBounds(51, 322, 55, 14);
		interprete.add(labcon);
		
		JButton gob = new JButton("Interpreter");
		gob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().equals("")) {
					outtext.setText("Veuillez ecrire du code dans la zone de texte au dessus!\n");
				}else {
					//outtext.setText(PrologParser.parseString(text.getText()).toString());
					Lanceur lance=new Lanceur(text.getText());
					outtext.setText(lance.run());
				}
			}
		});
		gob.setBounds(359, 297, 131, 35);
		interprete.add(gob);
	JButton save = new JButton(new ImageIcon( "./data/save.png" ));
	save.setToolTipText("Sauvegarder");
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if ((text.getText().equals(""))) {
				Question.warn("Aucun Text n'a été ecrit pour le moment!\n Merci d'ecrire quelquechose avant de save!\n");
				return;
			}
			else {
				try {
					OutputParser.write(frame, text.getText());
					Question.info("Fichier Enregistrer avec succes!");
				} catch (Exception e1) {
					Question.warn(e1.getMessage());
				}
			}
		}
	})
	;
	JButton importe = new JButton(new ImageIcon( "data/in.png" ));
	importe.setToolTipText("Importer");
	importe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				text.setText(InputParser.gettext(frame));
				Question.info("Fichier chargé avec Succes!");
			} catch (IOException e1) {
				Question.warn("Erreur: "+e1.getMessage()+" \n Veuillez recommencer");
			}
		}
	})
	;
	JButton newb = new JButton(new ImageIcon( "./data/new.png" ));
	newb.setToolTipText("Nouvelle Fenetre");
	newb.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				Gui lui = new Gui();
				lui.frame.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				Question.warn("Erreur : "+e1.getMessage());
			}
			
		}
	})
	;
	toolBar.add(newb);
	toolBar.add(importe);
	toolBar.add(save);
	}
}
