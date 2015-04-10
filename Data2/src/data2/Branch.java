package data2;

/** 
 *
 * @author David Wallach
 */
import java.math.*;
// - a Branch: (FiniteBag, E, multi, FiniteBag)
public class Branch<E extends Comparable<E>> implements FiniteBag<E> {
    E iden;
    int multi;
    FiniteBag<E> left;
    FiniteBag<E> right;

    Branch (FiniteBag<E> left, E iden, int multi, FiniteBag<E> right){
	this.left = left;
	this.iden = iden;
	this.multi = multi;
	this.right = right;
    }
    public String toString() {
        return "(" +
            this.left + "," +
            this.iden + "," +
            this.right + ")";
    }
    public int cardinality(){
	return this.multi + this.left.cardinality() + this.right.cardinality();
    }
    public boolean isEmptyHuh(){
	return false;
    }
    public boolean member(E e){
	if (this.iden.equals(e)){
	    return true;
	} else {
	    return (this.left.member(e) || this.right.member(e));
	}
    }
    public int multiplicity(E e){
	if (this.iden.equals(e)){
	    return this.multi + this.left.multiplicity(e) + this.right.multiplicity(e);
	} else {
	    return this.left.multiplicity(e) + this.right.multiplicity(e);
	}
    }
    public int depth(){
	int depth = 0;
	if (this.right.depth() > this.left.depth()) {
	    depth = 1 + this.right.depth();
	} else {
	    depth = 1 + this.left.depth();
	}
	return depth;
    }
    public FiniteBag<E> add(E e){
        return this.add(e,1);
    }
    public FiniteBag<E> add(E e, int num){
	if (this.iden.equals(e)){
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi + num,
				  this.right).checkAVL();
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.add(e, num),
				  this.iden,
				  this.multi,
				  this.right).checkAVL();
	} else{
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.add(e, num)).checkAVL();
	}
    }
    public FiniteBag<E> remove(E e){
        return this.remove(e, 1);
    }
    public FiniteBag<E> remove(E e, int num) {
        if (this.iden.equals(e) && num > this.multi) {
            return this.left.union(this.right).checkAVL();
        } else if (this.iden.equals(e)) {
            return new Branch(this.left,
                    this.iden,
                    this.multi - num,
                    this.right).checkAVL();
        } else if (this.iden.compareTo(e) > 0) {
            return new Branch<E>(this.left.remove(e),
                    this.iden,
                    this.multi,
                    this.right).checkAVL();
        } else {
            return new Branch<E>(this.left,
                    this.iden,
                    this.multi,
                    this.right.remove(e)).checkAVL();
        }
    }
    public FiniteBag<E> union(FiniteBag<E> t) {
        return t.union(this.left).union(this.right).add(this.iden, this.multi);
    }
    public FiniteBag<E> inter(FiniteBag<E> t) {
        if (!t.member(this.iden)) {
            return this.left.union(this.right).inter(t);
        } else {
            return new Branch(this.left.inter(t),
                    this.iden,
                    Math.min(this.multi, t.multiplicity(this.iden)),
                    this.right.inter(t));
        }
    }
    public FiniteBag<E> diff(FiniteBag<E> t){
	if (t.member(this.iden)){
	    if (t.multiplicity(this.iden) < this.multi){
		return new Branch (this.left.diff(t),
				   this.iden,
				   this.multi-t.multiplicity(this.iden),
				   this.right.diff(t));
	    } else {
		return this.left.diff(t).union(this.right.diff(t));
	    }
	} else {
	    return new Branch (this.left.diff(t),
			       this.iden,
			       this.multi,
			       this.right.diff(t));
	}
    }
    public boolean equal(FiniteBag<E> t){
	return t.subset(this) && this.subset(t);
    }
    public boolean subset(FiniteBag<E> t){
	if (this.diff(t).isEmptyHuh()){
	    return true;
	} else {
	    return false;
	}
    }
    public E max(){
	if (this.right.isEmptyHuh()){
	    return this.iden;
	} else {
	    return this.right.max();
	}
    }
    public Sequence<E> seq(){
	return new BagSeq<E>(new makeSeq<E>(this.left.seq(),
					    this.right.seq()),
			     this.iden,
			     this.multi);
    }
    public E here(){
	return this.seq().here();
    }
    public Sequence<E> next(){
	return this.seq().next();
    }
    public FiniteBag<E> rotateRight(){
	Branch<E> newIden = (Branch<E>)this.left;
	return new Branch (newIden.left,
			   newIden.iden,
			   newIden.multi,
			   new Branch (newIden.right,
				       this.iden,
				       this.multi,
				       this.right));
    }
    public FiniteBag<E> rotateLeft(){
	Branch<E> newIden = (Branch<E>)this.right;
	return new Branch (new Branch (this.left,
				       this.iden,
				       this.multi,
				       newIden.left),
			   newIden.iden,
			   newIden.multi,
			   newIden.right);
    }
    public FiniteBag<E> checkAVL(){
	FiniteBag rotated;
        if (this.left.depth() - this.right.depth() > 1){
	    rotated = this.rotateRight();
	} else if (this.right.depth() - this.left.depth() > 1) {
	    rotated = this.rotateLeft();
	} else {
	    rotated = this;
	}
	return rotated;
    }
}


