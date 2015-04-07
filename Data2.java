import java.util.*;

interface Sequenced<E>{
    public Sequence seq();
}
interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
    public E here();
    public boolean isEmptyHuh();
    public Sequence<E> next();
}
// - an empty set: empty
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

// - a Branch: (FiniteBag, int, FiniteBag)
class Branch<E extends Comparable<E>> implements FiniteBag<E> {
    E iden;
    int multi;
    FiniteBag left;
    FiniteBag right;

    Branch (FiniteBag left, E iden, int occurs, FiniteBag right){
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
	    return 1 + this.left.multiplicity(e) + this.right.multiplicity(e);
	} else {
	    return this.left.multiplicity(e) + this.right.multiplicity(e);
	}
    }
    public FiniteBag<E> add(E e){
	if (this.iden.equals(e)){
	    return this;
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.add(e),
				  this.iden,
				  this.multi,
				  this.right);
	} else{
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.add(e));
	}
    }
    public FiniteBag<E> add(E e, int num){
	if (this.iden.equals(e)){
	    return this;
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.add(e),
				  this.iden,
				  this.multi,
				  this.right);
	} else{
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.add(e));
	}
    }
    public FiniteBag<E> remove(E e){
	if (this.iden.equals(e)){
	    return this.left.union(this.right);
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
	if (this.iden.equals(e)){
	    return this.left.union(this.right);
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
}
class Data2 {
        public static FiniteBag randomFiniteBag(int numAdd, int rangeAdd){
	FiniteBag rFiniteBag = new Empty();
	for (int i=0; i<numAdd;i++){
	    Random newRandom = new Random();
	    int randInt = newRandom.nextInt(rangeAdd);
	    rFiniteBag = rFiniteBag.add(randInt);
	}
	return rFiniteBag;
    }
    public static String memberTest(int numTest, int rangeTest){
        int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    Random newRandomY = new Random();
	    Random newRandomX = new Random();
	    int y = newRandomY.nextInt(rangeTest);
	    int x = newRandomX.nextInt(rangeTest);
	    FiniteBag newSet = randomFiniteBag(i, rangeTest);
	    if (x < (rangeTest/2)){
		y = x;
	    } else {
		newSet = newSet.add(y);
	    }
	    if (newSet.add(x).member(y)){
		passed++;
	    } else {
		failed++;
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String unionTest(int numTest, int rangeTest){
        int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    Random newRandom = new Random();
	    int x = newRandom.nextInt(rangeTest);
	    FiniteBag setA = randomFiniteBag((i/2), rangeTest);
	    FiniteBag setB = randomFiniteBag((i/2), rangeTest);
	    if (x < (rangeTest/2)){
		setA = setA.add(x);
	    } else {
		setB = setB.add(x);
	    }
	    if (setA.union(setB).member(x)){
		passed++;
	    } else {
		failed++;
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
	}
    public static void main (String args[]){
	//PREMADE TEST SETS
	FiniteBag<Integer> empty = new Empty<Integer>();
	FiniteBag<Integer> b0 = new Branch<Integer> (empty, 0, 1, empty);
        FiniteBag<Integer> b1 = new Branch<Integer> (empty, 1, 1, empty);
	FiniteBag<Integer> b3 = new Branch<Integer> (empty, 3, 1, empty);
	FiniteBag<Integer> b5 = new Branch<Integer> (empty, 5, 1, empty);
	FiniteBag<Integer> b8 = new Branch<Integer> (empty, 8, 1, empty);
	FiniteBag<Integer> b7 = new Branch<Integer> (b5, 7, 1, b8);
	FiniteBag<Integer> b6 = new Branch<Integer> (b5, 6, 1, b8);
	FiniteBag<Integer> b2 = new Branch<Integer> (b1, 2, 1, b3);
	FiniteBag<Integer> b2b = new Branch<Integer> (b0, 2, 1, b3);
	FiniteBag<Integer> b4 = new Branch<Integer> (b2, 4, 1, b6);
	FiniteBag<Integer> b4b = new Branch<Integer> (b2b, 4, 1, b7);
	FiniteBag<String> string = new Branch<String> (empty, "Hello World", 1, empty);
	FiniteBag<Boolean> bool = new Branch<Boolean> (empty, true, 1, empty);
	
	//TESTS
	System.out.println ("RANDOMLY GENERATED TESTS");
	System.out.println (memberTest(50, 10000));
	System.out.println (unionTest(50, 10000));	
	System.out.println ();
	System.out.println ("PREMADE TESTS");
	System.out.println ("Should be 0. Is "+empty.cardinality());
	System.out.println ("Should be 7. Is "+b4.cardinality());
	System.out.println ("Should be true.  Is "+empty.isEmptyHuh());
	System.out.println ("Should be false. Is "+b4.isEmptyHuh());
	System.out.println ("Should be false. Is "+b3.member(5));
	System.out.println ("Should be true.  Is "+b4.member(6));
        System.out.println ("Should contain {1,2,3,4,5,6,7,8}. Contains "+b4.add(7));
	System.out.println ("Should contain {1,2,3,5,6,8}. Contains "+b4.remove(4));
	System.out.println ("Should contain {1,2,3,5,6,8}. Contains "+b2.union(b6));
	System.out.println ("Should contain {1,2,3,5,6,8}. Contains "+b6.union(b2));
        System.out.println ("Should contain {2,3,4,5,8}. Contains "+b4b.inter(b4));
        System.out.println ("Should contain {2,3,4,5,8}. Contains "+b4.inter(b4b));
	System.out.println ("Should contain {0,7}. Contains "+b4.diff(b4b));
        System.out.println ("Should contain {1,6}. Contains "+b4b.diff(b4));
        System.out.println ("Should be true.  Is "+b4.equal(b4));
        System.out.println ("Should be false. Is "+b4b.equal(b4));
	System.out.println ("Should be true.  Is "+b2.subset(b4));
	System.out.println ("Should be false. Is "+b4.subset(b2));
    }
}



			    
