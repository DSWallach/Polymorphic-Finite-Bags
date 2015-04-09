import java.util.*;

// interface Sequenced<E>{
//     public Sequence seq();
// }
// interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
//     public E here();
//     public boolean isEmptyHuh();
//     public Sequence<E> next();
// }
// interface Indexed {
//     public int size();
//     public int read( int idx );
// }

// - an empty bag: empty
class Empty<E extends Comparable<E>> implements FiniteBag<E> {
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
	return t;
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
	throw new RuntimeException();
    }
    public Sequence<E> seq(){
	return this;
    }
    public E here(){
	throw new RuntimeException("Nothing is here.");
    }
    public Sequence<E> next(){
	return this;
    }
}

// - a Branch: (FiniteBag, E, FiniteBag)
class Branch<E extends Comparable<E>> implements FiniteBag<E> {
    E iden;
    int multi;
    FiniteBag left;
    FiniteBag right;

    Branch (FiniteBag left, E iden, int multi, FiniteBag right){
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
	return 1 + this.left.cardinality() + this.right.cardinality();
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
	if (this.iden.equals(e)){
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi + 1,
				  this.right).checkAVL();
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.add(e),
				  this.iden,
				  this.multi,
				  this.right).checkAVL();
	} else{
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.add(e)).checkAVL();
	}
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
	if (this.iden.equals(e) && this.multi == 1){
	    return this.left.union(this.right);
	} else if (this.iden.equals(e)){
	    return new Branch (this.left,
			       this.iden,
			       this.multi - 1,
			       this.right);
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.remove(e),
				  this.iden,
				  this.multi,
				  this.right);
	} else {
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.remove(e));
	}			       	   
    }
    public FiniteBag<E> remove(E e, int num){
	if (this.iden.equals(e) && num > this.multi){
	    return this.left.union(this.right);
	} else if (this.iden.equals(e)){
	    return new Branch (this.left,
			       this.iden,
			       this.multi - num,
			       this.right);
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.remove(e),
				  this.iden,
				  this.multi,
				  this.right);
	} else {
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.remove(e));
	}			       	   
    }
    public FiniteBag<E> union(FiniteBag<E> t){
	FiniteBag<E> newBag = t.union(this.left).union(this.right).add(this.iden);
	return newBag;
    }
    public FiniteBag inter(FiniteBag t){
	FiniteBag<E> newBag;
	if (!t.member(this.iden)){
	    newBag = this.left.union(this.right);
	    newBag.inter(t);
	} else {
	    newBag = new Branch<E> (this.left.inter(t),
				    this.iden,
				    this.multi,
				    this.right.inter(t));
	}
	return newBag;
    }
    public FiniteBag diff(FiniteBag<E> t){
	return this.remove(this.iden).diff(t).remove(this.iden);
    }
    public boolean equal(FiniteBag<E> t){
	return (t.subset(this) && this.subset(t));
    }
    public boolean subset(FiniteBag<E> t){
	if(!t.member(this.iden)){
	    return false;
        } else {
	    return this.remove(this.iden).subset(t);
	}
    }
    public E max(){
	throw new RuntimeException();
    }
    public Sequence<E> seq(){
	return this;
    }
    public E here(){
	throw new RuntimeException("Nothing is here.");
    }
    public Sequence<E> next(){
	return this;
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



			    
