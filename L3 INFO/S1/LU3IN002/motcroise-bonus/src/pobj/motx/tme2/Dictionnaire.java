package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 * @author Zhenyue FU, Zhe WANG
 */
public class Dictionnaire {

    // stockage des mots
    private List<String> mots = new ArrayList<>();
    private EnsembleLettre[] cache = null;

    /**
     * Charger le dictionnaire à partir du fichier du chemin indique
     *
     * @param path chemin de fichier dictionnaire
     * @return le dictionnaire
     */
    public static Dictionnaire loadDictionnaire(String path) {
        Dictionnaire dic = new Dictionnaire();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                // Utiliser "line".
                dic.add(line);
            }
        } catch (IOException e) {
            // Problème d’acces au fichier.
            e.printStackTrace();
        }
        return dic;
    }

    /**
     * Ajoute un mot au Dictionnaire, en dernière position.
     *
     * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
     */
    public void add(String mot) {
        mots.add(mot.toLowerCase());
    }

    /**
     * Taille du dictionnaire, c'est-à-dire nombre de mots qu'il contient.
     *
     * @return la taille
     */
    public int size() {
        return mots.size();
    }

    /**
     * Accès au i-eme mot du dictionnaire.
     *
     * @param i l'index du mot recherché, compris entre 0 et size-1.
     * @return le mot à cet index
     */
    public String get(int i) {
        return mots.get(i);
    }

    /**
     * Rend une copie de ce Dictionnaire.
     *
     * @return une copie identique de ce Dictionnaire
     */
    public Dictionnaire copy() {
        Dictionnaire copy = new Dictionnaire();
        copy.mots.addAll(mots);
        copy.cache = cache;
        return copy;
    }

    /**
     * Retire les mots qui ne font pas exactement "len" caractères de long.
     * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de
     * filtrer pour ne pas perdre d'information.
     *
     * @param len la longueur voulue
     * @return le nombre de mots supprimés
     */
    public int filtreLongueur(int len) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        for (String mot : mots) {
            if (mot.length() == len) {
                cible.add(mot);
            } else {
                cpt++;
            }
        }
        mots = cible;
        if (cpt > 0) {
            cache = null;
        }
        return cpt;
    }

    /**
     * Retire les mots qui n'ont pas exactement caractère c a la position d'i
     *
     * @param c caractère sur la position i
     * @param i le i-eme caractère
     * @return le nombre de mots supprimés
     */
    public int filtreParLettre(char c, int i) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        EnsembleLettre el = charAt(i);
        if (!el.contains(c)) {
            cpt = mots.size();
            cache = null;
            mots = new ArrayList<>();
            return cpt;
        }
        for (String mot : mots) {
            if (mot.charAt(i) == c) {
                cible.add(mot);
            } else {
                cpt++;
            }
        }
        mots = cible;
        if (cpt > 0) {
            cache = null;
        }
        return cpt;
    }

    public int filtreParLettres(EnsembleLettre l, int i) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        if (l.size() == 0) {
            cpt = mots.size();
            cache = null;
            mots = new ArrayList<>();
            return cpt;
        }
        EnsembleLettre el = charAt(i);
        EnsembleLettre inter = EnsembleLettre.intersection(el, l);
        if (inter.size() == 0) {
            cpt = mots.size();
            cache = null;
            mots = new ArrayList<>();
            return cpt;
        }
        for (String mot : mots) {
            if (l.contains(mot.charAt(i))) {
                cible.add(mot);
            } else {
                cpt++;
            }

        }
        mots = cible;
        if (cpt > 0) {
            cache = null;
        }
        return cpt;
    }

    public List<String> getMots() {
        return mots;
    }

    @Override public String toString() {
        if (size() == 1) {
            return mots.get(0);
        } else {
            return "Dico size =" + size();
        }
    }

    public EnsembleLettre charAt(int index) {
        if (mots.isEmpty()) {
            return new EnsembleLettre();
        }
        if (cache == null) {
            cache = new EnsembleLettre[mots.get(0).length()];
        }
        if (cache[index] == null) {
            cache[index] = new EnsembleLettre();
            for (String s : mots) {
                cache[index].add(s.charAt(index));
            }
        }
        return cache[index];
    }

    public void fixer(String s) {
        mots = new ArrayList<>();
        mots.add(s);
        cache = null;
    }
}
