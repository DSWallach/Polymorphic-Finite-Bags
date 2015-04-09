import java.math.*;
interface Sequenced<E extends Comparable<E>>{
    public Sequence<E> seq();
}
interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
    public E here();
    public boolean isEmptyHuh();
    public Sequence<E> next();
}
class makeSeq<E extends Comparable<E>> implements Sequence<E>{
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
class BagSeq<E extends Comparable<E>> implements Sequence<E> {
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
    public Sequence next() {
	if (this.multi > 1){
	    return new BagSeq(this.s, this.iden, this.multi - 1);
	} else {
	    return this.s;
	}
    }
    public Sequence seq(){
	return this;
    }
}
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

// - a Branch: (FiniteBag, E, multi, FiniteBag)
class Branch<E extends Comparable<E>> implements FiniteBag<E> {
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
    public FiniteBag<E> inter(FiniteBag<E> t){
	if (!t.member(this.iden)){
	    FiniteBag newBag = this.left.union(this.right);
	    return newBag.inter(t);
	} else {
	    return new Branch<E> (this.left.inter(t),
				  this.iden,
				  Math.min(this.multi, t.multiplicity(this.iden)),
				  this.right.inter(t));
	}
    }
    public FiniteBag<E> diff(FiniteBag<E> t){
	if (t.member(this.iden)){
	    return this.remove(this.iden,
			       t.multiplicity(this.iden)).diff(t).remove(this.iden,
									 t.multiplicity(this.iden));
	} else {
	    return new Branch<E> (this.left.diff(t),
				  this.iden,
				  this.multi,
				  this.right.diff(t));
	}
    }
    public boolean equal(FiniteBag<E> t){
	return t.subset(this) && this.subset(t);
    }
    public boolean subset(FiniteBag<E> t){
	if(!t.member(this.iden)){
	    return false;
        } else {
	    return this.remove(this.iden).subset(t);
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
	return new BagSeq<E>(new makeSeq<E>(this.left.seq(), this.right.seq()), this.iden, this.multi);
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



			    
