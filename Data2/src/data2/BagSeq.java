package data2;

/**
 *
 * @author David Wallach
 */
public class BagSeq<E extends Comparable<E>> implements Sequence<E> {
    makeSeq<E> s;
    E iden;
    int multi;

    BagSeq (makeSeq<E> s, E iden, int multi) {
	this.s = s;
	this.iden = iden;
	this.multi = multi;
    }
    public E here() {
        return this.iden;
    }
    public boolean isEmptyHuh() {
        return false;
    }
    public Sequence<E> next() {
	if (this.multi > 1){
	    return new BagSeq(this.s, this.iden, this.multi - 1);
	} else {
	    return this.s;
	}
    }
    public Sequence<E> seq(){
	return this;
    }
}
