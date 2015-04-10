package data2;

/**
 *
 * @author David Wallach
 * An implementation iteration abstraction. Sequence allows a user to retrieve
 * the desired objects within the FiniteBag without retrieving every other
 * object in the FiniteBag as well.
 */
public interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
    /**
     * Retrieve the current object of type E from the sequence.
     * 
     * @return The current object of type E in the sequence.
     */
    public E here();
    /**
     * Determines if there are any objects currently in the sequence.
     * 
     * @return A boolean value. true when the sequence is empty, otherwise false
     */
    public boolean isEmptyHuh();
    /**
     * Returns a sequence with original .here() object removed. .here() now
     * returns the object following the original .here().
     * 
     * @return A sequence moved forward by one object from the original sequence
     */
    public Sequence<E> next();
}