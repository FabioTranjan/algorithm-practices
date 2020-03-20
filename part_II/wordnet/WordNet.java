import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {
    private Map<String, List<Integer>> synsets_ids;
    private Map<Integer, String> synsets;
    private Digraph hypernyms;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        if (synsetsFile == null || hypernymsFile == null)
            throw new IllegalArgumentException();

        synsets = new HashMap<>();
        synsets_ids = new HashMap<>();
        loadSynsets(synsetsFile);

        hypernyms = new Digraph(synsets.size());
        loadHypernyms(hypernymsFile);

        sap = new SAP(hypernyms);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsets_ids.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();

        return synsets_ids.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();

        List<Integer> v = synsets_ids.get(nounA);
        List<Integer> w = synsets_ids.get(nounB);

        return sap.length(v, w);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();

        List<Integer> v = synsets_ids.get(nounA);
        List<Integer> w = synsets_ids.get(nounB);

        int ancestor = sap.ancestor(v, w);
        return synsets.get(ancestor);
    }

    private void loadSynsets(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String[] words = parts[1].split(" ");

                int id = Integer.parseInt(parts[0]);
                for (String word : words) {
                    List<Integer> ids = synsets_ids.get(word);

                    if (ids == null)
                        ids = new ArrayList<>();

                    ids.add(id);
                    synsets_ids.put(word, ids);
                }

                synsets.put(id, parts[1]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHypernyms(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (int i = 1; i < parts.length; i++) {
                    hypernyms.addEdge(Integer.parseInt(parts[0]),
                                      Integer.parseInt(parts[i]));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(wordnet.distance("A", "ASCII_character"));
        System.out.println(wordnet.sap("A", "ASCII_character"));
    }
}
