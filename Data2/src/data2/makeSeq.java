package data2;

/**
 *
 * @author David Wallach
 */
public class makeSeq<E extends Comparable<E>> implements Sequence<E>{
    Sequence<E> left;
    Sequence<E> right;

    makeSeq(Sequence<E> left, Sequence<E> right){
	this.left = left;
	this.right = right;
    }
    public E here(){
	if (!this.left.isEmptyHuh()){
	    return this.left.here();
	} else {
	    return this.right.here();
	}
    }
    public boolean isEmptyHuh(){
	return this.left.isEmptyHuh() && this.right.isEmptyHuh();
    }
    public Sequence<E> next(){
	if (!this.left.isEmptyHuh()){
	    return new makeSeq<E>(this.left.next(), this.right);
	} else {
	    return this.right.next();
	}
    }
    public Sequence<E> seq(){
	return this;
    }

}