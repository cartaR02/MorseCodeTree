package cmsc256;

import java.util.Objects;

public class MorseCharacter implements Comparable<MorseCharacter> {

    private char character;
    private String code;

    public MorseCharacter(char character, String code){
        setCharacter(character);
        setCode(code);

    }

    public MorseCharacter(){
        setCharacter('e');
        setCode("Nulle");
    }



    public int getCodeLength(){
        return code.length();
    }

    public char getLetter(){
        return character;
    }


    //Getters and setters
    public void setCharacter(char character) {
        this.character = character;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getCharacter() {
        return character;
    }



    public String getCode() {
        return code;
    }

    public String toString(){
        return String.valueOf(getCharacter());
    }

    @Override
    public int compareTo(MorseCharacter o) {
        if(o == null) {
            throw new IllegalArgumentException();
        }
        if(code.length() == o.getCodeLength()) {
            return Character.compare(character, o.getCharacter());
        }

        return this.code.compareTo((o.getCode()));
    }

    @Override
    public int hashCode(){
        return Objects.hash(character, code);
    }

    public boolean equals(Object o){
        if(!(o instanceof MorseCharacter )){
            return false;
        }
        MorseCharacter other = (MorseCharacter) o;
        return this.character == other.character && this.code.equals(((MorseCharacter) o).getCode());
    }
}
