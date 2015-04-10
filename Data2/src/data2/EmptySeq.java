package data2;

/**
 *
 * @author David Wallach
 */
public class EmptySeq<E extends Comparable<E>> implements Sequence<E> {
        
    EmptySeq () {}
    public E here() {
        return null;
    }
    public boolean isEmptyHuh() {
        return true;
    }
    public Sequence<E> next() {
        return null;
    }
    public Sequence<E> seq(){
	return this;
    }
}
