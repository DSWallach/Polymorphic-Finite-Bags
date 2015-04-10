package data2;

/**
 *
 * @author David Wallach
 *
 */
public interface Sequenced<E extends Comparable<E>> {
    
    /**
     * Creates a sequence from a FiniteBag. If the FiniteBag is a Branch 
     * then the sequence will contain the Branch's .iden and .multi. If if
     * is a Empty the sequence will be empty.
     *
     * @return A sequence whose object is that of the original FiniteBag.
     */
    public Sequence<E> seq();
}
