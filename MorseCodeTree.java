package cmsc256;

import bridges.base.Array;
import bridges.base.BSTElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MorseCodeTree {
    private BSTElement<String, MorseCharacter> root;
    private int numberOFNodes;
    private int height;
    private HashMap<Character, String> hasshy;

    public static void main(String[] args) {
        MorseCodeTree codeTree = new MorseCodeTree();

        Bridges bridge = new Bridges(3, "cartaR02", "767022609852");
        bridge.setTitle("Morse Code");
        bridge.setDescription("morese");


        codeTree.root = codeTree.createTree();
        //codeTree.root.setLeft();;
       bridge.setDataStructure(codeTree.root);
        try {
            bridge.visualize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RateLimitException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Pre Order: " + codeTree.preOrderTraversal(codeTree.root));
        System.out.println("Post Order: " + codeTree.postOrderTraversal(codeTree.root));
        System.out.println("In Order: " + codeTree.inOrderTraversal(codeTree.root));
      //  System.out.println("Printing Height " + codeTree.getHeight());
        System.out.println("Height: " + codeTree.getHeight());
        System.out.println("Number of Nodes: " + codeTree.getNumberOfNodes());
        System.out.println(codeTree.height);
        String toBeDecoded = ".--.";
        System.out.println("String to be decoded " + toBeDecoded + " " + codeTree.decode(toBeDecoded));
        Character tempChar = 'C';

        System.out.println("Charcter to be encoded: " + tempChar + " "  + codeTree.encode(tempChar));
    }
    public MorseCodeTree(){

        root = new BSTElement<>();
        numberOFNodes++;
        hasshy = new HashMap<Character, String>();
    }
    public MorseCodeTree(BSTElement rt){
        root = rt;
        numberOFNodes++;
        hasshy = new HashMap<Character, String>();
    }

    public void setRoot(BSTElement<String, MorseCharacter> root) {
        this.root = root;
    }

    @Override
    public String toString() {

        return super.toString();
    }

    public BSTElement<String, MorseCharacter> createTree(){


        BSTElement<String, MorseCharacter> tree = new BSTElement<>("Root","Root", new MorseCharacter('w',"-."));
        ArrayList<MorseCharacter> array = new ArrayList<>();
        tree.setLabel("Root");
        try{

            int testingNum = 0;
            File file = new File("codefile.dat");
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                boolean doesExist = false;
                String line = scan.nextLine();
                String[] pieces = line.split(" ");
                char character = pieces[0].charAt(0);
                String code = pieces[1];
                MorseCharacter morseCharacter = new MorseCharacter(character, code);
                //TODO
                // Sort by length of morse code
                // Does not matter if the dots or dash
                for (int i =0; i < array.size(); i++){
                        if(array.get(i).getCharacter() == morseCharacter.getCharacter()){
                            doesExist = true;
                        }
                }
                if(!doesExist){
                    array.add(morseCharacter);
                }
                hasshy.put(morseCharacter.getCharacter(), morseCharacter.getCode());

            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }

        //Sorts the array for the length of the code so that it can put it in the tree correctyl

        //Test printing

//System.out.println(hasshy);

        for(int i = 0; i < array.size(); i ++){
            for(int j = i +1; j < array.size(); j++){

                MorseCharacter temp;
                if(array.get(i).getCodeLength() > array.get(j).getCodeLength()){
                    temp = array.get(i);
                    array.set(i,array.get(j));
                    array.set(j,temp);

                }

            }
        }



        //Test printing


        for (MorseCharacter morseCharacter : array) {
            tree = addNew(tree, morseCharacter, morseCharacter.getCode());
            // System.out.println(array.get(i).getCode() + " "  + array.get(i).getCharacter());
        }
        //System.out.println(preOrderTraversal(tree));
        return tree;
    }

    public bridges.base.BSTElement<String, MorseCharacter> add(BSTElement<String, MorseCharacter> rt){


        return addNew(rt, rt.getValue(), rt.getValue().getCode());
    }

    private bridges.base.BSTElement<String, MorseCharacter> addNew(BSTElement<String, MorseCharacter> rt,  MorseCharacter charact, String coding){
        height =0;
    if(rt == null) {
        BSTElement<String, MorseCharacter> hehe = new BSTElement<>(String.valueOf(charact.getCharacter()),charact.getCode(), charact);
        hehe.setLabel(String.valueOf(charact.getCharacter()));
        return hehe;
    }

    if(coding.length()>0) {
        if (String.valueOf(coding.charAt(0)).equals(".")) {
            numberOFNodes++;
            rt.setLeft(addNew(rt.getLeft(), charact, coding.substring(1)));
        }else{
            numberOFNodes++;

            rt.setRight(addNew(rt.getRight(), charact, coding.substring(1)));
    }
        height++;
    }

/*
        String morseCode = charact.getCode();
        BSTElement<String, MorseCharacter> tempIterator = rt;

        for(int i = 0; i < morseCode.length(); i++){
            char code = morseCode.charAt(i);
            //System.out.println(morseCode + " then where im at " + morseCode.charAt(i));
            if(code == '.'){
                //If there is no left then create one and desende down
                if(tempIterator.getLeft() == null){
                    BSTElement<String, MorseCharacter> newTemp = new BSTElement<>(String.valueOf(charact.getCharacter()), charact.getCode(),charact);
                    newTemp.setLabel(String.valueOf(charact.getCharacter()));
                    tempIterator.setLeft(newTemp);
                }
                    //If it's not emptier than move down
                    tempIterator = tempIterator.getLeft();
            }
            else if(code == '-'){
                if(tempIterator.getRight() == null){
                    BSTElement<String, MorseCharacter> newTemp = new BSTElement<>(String.valueOf(charact.getCharacter()), charact.getCode(),charact);
                    newTemp.setLabel(String.valueOf(charact.getCharacter()));
                    tempIterator.setRight(newTemp);
                }
                    tempIterator = tempIterator.getRight();
                }
            }





 */

         return rt;
    }

    public int getHeight(){
       // return getNewHeight(root);
        return height;
    }

    private int getNewHeight(BSTElement<String, MorseCharacter> rt){
        if(rt == null){
            return 0;
        }else{
            int left = getNewHeight(rt.getLeft());
            int right = getNewHeight(rt.getRight());

            if(left > right){
                return left + 2;

            }else{
                return right + 2;
            }
        }
    }

    public int getNumberOfNodes(){
        return numberOFNodes--;
    }

    public boolean isEmpty(){

        /*
        if(root == null || root.getValue() == null){
            return false;
        }
        if(root.getLeft() == null && root.getRight()==null){
            return false;
        }
        return true;


         */
        return numberOFNodes == 0;

    }

    public void clear(){
        root = null;
        numberOFNodes = 0;
        hasshy = new HashMap<Character, String>();
    }
    public Character decode(String coded){

        if(coded == null){
            throw new IllegalArgumentException();
        }

        return decodeHelp(root, coded);
    }
private Character decodeHelp(BSTElement<String, MorseCharacter>rt, String coding){
      if(rt == null){
          return '\0';
      }
        if(coding.length() > 0){
            if(coding.charAt(0) == '.'){
                return decodeHelp(rt.getLeft(), coding.substring(1));
            }else{
                return decodeHelp(rt.getRight(), coding.substring(1));
            }
        }

        return rt.getValue().getCharacter();

}

    public String encode(Character c) {
        if(c == null){
            throw new IllegalArgumentException();
        }
       if(!hasshy.containsKey(c)){
           throw new IllegalArgumentException();
       }
        return hasshy.get(c);

}


   // System.out.println(hasshy.containsKey(c));


    private String newEncode(BSTElement<String, MorseCharacter> rt, char character){

        if(rt == null){
            return "wjhat";
        }
        MorseCharacter temp = rt.getValue();
            if(temp.getCharacter() == character){
                return temp.getCode();
            }else if (temp.getCharacter() > character){
                return newEncode(rt.getLeft(), character);
            }else{
                return newEncode(rt.getRight(), character);
        }
    }

    public String inOrderTraversal(BSTElement<String, MorseCharacter> rt){
        if (rt == null){
            return "";
        }
        String leftRT = inOrderTraversal(rt.getLeft());
        String RT = rt.getValue().getCode();
        String rightRT = inOrderTraversal(rt.getRight());

        return leftRT + " " + RT + " " + rightRT;
    }

    public String levelOrderTraversal(BSTElement<String, MorseCharacter > rt){
        return "levle order";
    }

    public String postOrderTraversal(BSTElement<String, MorseCharacter> rt){
        if (rt == null) {
            return "";
        }
        String leftRT = postOrderTraversal(rt.getLeft());
        String rightRT = postOrderTraversal(rt.getRight());
        String RT = rt.getValue().getCode();


        return leftRT + " " + rightRT + " " + RT;
    }

    public String preOrderTraversal(BSTElement<String, MorseCharacter> rt){
        if(rt == null){
            return "";
        }
        String rtStr = rt.getValue().getCode();
        String rtLeft = preOrderTraversal(rt.getLeft());
        String rtRight = preOrderTraversal(rt.getRight());



        return rtStr + " " + rtLeft + " " + rtRight;
    }

    public BSTElement<String,MorseCharacter> getRoot(){
        return root;
    }
}
