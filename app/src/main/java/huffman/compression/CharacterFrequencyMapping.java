package huffman.compression;

public class CharacterFrequencyMapping implements Comparable<CharacterFrequencyMapping> {
    private Character character;
    private Integer frequency;

    public CharacterFrequencyMapping(Character c, Integer f) {
        this.character = c;
        this.frequency = f;
    }

    public CharacterFrequencyMapping(Character c) {
        this.character = c;
        this.frequency = 0;
    }

    public CharacterFrequencyMapping() {
        throw new UnsupportedOperationException();
    }

    public Character getCharacter() {
        return character;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setC(Character c) {
        this.character = c;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CharacterFrequencyMapping other = (CharacterFrequencyMapping) obj;
        if (frequency == null) {
            if (other.frequency != null)
                return false;
        } else if (!frequency.equals(other.frequency))
            return false;
        return true;
    }

    // @Override
    // public int compareTo(Object obj) {
    // if (this == obj)
    // return 0;
    // if (obj == null)
    // throw new NullPointerException();
    // if (getClass() != obj.getClass())
    // throw new ClassCastException();
    // CharacterFrequency other = (CharacterFrequency) obj;

    // }

    @Override
    public int compareTo(CharacterFrequencyMapping o) {
        return this.frequency.compareTo(o.frequency);
    }

}
