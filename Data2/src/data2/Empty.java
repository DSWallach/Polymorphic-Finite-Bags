package data2;

/**
 *
 * @author David Wallach
 */
// - an empty bag: empty
public class Empty<E extends Comparable<E>> implements FiniteBag<E> {
    Empty (){}
    public String toString(){
	return ("e");
    }
    public int cardinality(){
	return 0;
    }
    public boolean isEmptyHuh(){
	return true;
    }
    public boolean member(E e){
	return false;
    }
    public int multiplicity(E e){
	return 0;
    }
    public int depth(){
	return 0;
    }
    public FiniteBag<E> add(E e){
	return new Branch<E> (this, e, 1, this);
    }
    public FiniteBag<E> add(E e, int num){
        return new Branch<E> (this, e, num, this);
    }
    public FiniteBag<E> remove(E e){
	return this;
    }
    public FiniteBag<E> remove(E e, int num){
	return this;
    }
    public FiniteBag<E> union(FiniteBag<E> t){
	return t;
    }
    public FiniteBag<E> inter(FiniteBag<E> t){
	return new Empty();
    }
    public FiniteBag<E> diff(FiniteBag<E> t){
	return new Empty();
    }
    public FiniteBag<E> rotateRight(){
	return this;
    }
    public FiniteBag<E> rotateLeft(){
	return this;
    }
    public FiniteBag<E> checkAVL(){
	return this;
    }
    public boolean equal(FiniteBag<E> t){
	return t.isEmptyHuh();
    }
    public boolean subset(FiniteBag<E> t){
	return true;
    }
    public E max(){
	return null;
    }
    public Sequence<E> seq(){
	return new EmptySeq();
    }
    public E here(){
	return this.seq().here();
    }
    public Sequence<E> next(){
	return this.seq().next();
    }
}
