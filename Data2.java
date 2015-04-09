import java.util.*;

interface Sequenced<E>{
    public Sequence seq();
}
interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
    public E here();
    public boolean isEmptyHuh();
    public Sequence<E> next();
}
interface Indexed {
    public int size();
    public int read( int idx );
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

// - a Branch: (FiniteBag, int, FiniteBag)
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
	    // this.multi + "," +
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
	    return this.multi  + this.left.cardinality() + this.right.cardinality();
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
				  this.right);
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
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi + num,
				  this.right);
	} else if (this.iden.compareTo(e) > 0){
	    return new Branch<E> (this.left.add(e, num),
				  this.iden,
				  this.multi,
				  this.right);
	} else{
	    return new Branch<E> (this.left,
				  this.iden,
				  this.multi,
				  this.right.add(e, num));
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
        return this;
    }
}
class Data2 {
    public static FiniteBag randomIntBag(int numAdd, int rangeAdd){
	FiniteBag rFiniteBag = new Empty();
	for (int i=0; i<numAdd;i++){
	    Random newRandom = new Random();
	    int randInt = newRandom.nextInt(rangeAdd);
	    rFiniteBag = rFiniteBag.add(randInt);
	}
	return rFiniteBag;
    }
    public static FiniteBag randomBoolBag(int numAdd, int rangeAdd){
	FiniteBag rFiniteBag = new Empty();
	for (int i=0; i<numAdd;i++){
	    Random newRandom = new Random();
	    int randInt = newRandom.nextInt(rangeAdd);
	    if (randInt < (rangeAdd / 2)){
		rFiniteBag = rFiniteBag.add(true);
	    } else {
		rFiniteBag = rFiniteBag.add(false);
	    }
	}
	return rFiniteBag;
    }
    public static FiniteBag randomStringBag(int numAdd){
	FiniteBag rFiniteBag = new Empty();
	String[] stringArray;
	stringArray = new String[26];
	stringArray[0] = "a";
	stringArray[1] = "b";
	stringArray[2] = "c";
	stringArray[3] = "d";
	stringArray[4] = "e";
	stringArray[5] = "f";
	stringArray[6] = "g";
	stringArray[7] = "h";
        stringArray[8] = "i";
	stringArray[9] = "j";
	stringArray[10] = "k";
	stringArray[11] = "l";
	stringArray[12] = "m";
	stringArray[13] = "n";
	stringArray[14] = "o";
	stringArray[15] = "p";
	stringArray[16] = "q";
        stringArray[17] = "r";
	stringArray[18] = "s";
	stringArray[19] = "t";
	stringArray[20] = "u";
	stringArray[21] = "v";
	stringArray[22] = "w";
	stringArray[23] = "x";
	stringArray[24] = "y";
	stringArray[25] = "z";
	for (int i=0; i<numAdd;i++){
	    Random newRandom = new Random();
	    int randInt = newRandom.nextInt(25);
	    rFiniteBag = rFiniteBag.add(stringArray[randInt]);
	}
	return rFiniteBag;
    }
   public static String addTest(int numTest, int rangeTest){
       int passed = 0;
       int failed = 0;
       int check = 0;
       for (int i=0;i<numTest;i++){
	   FiniteBag IntBag = randomIntBag(i, rangeTest);
	   FiniteBag newIntBag = IntBag;
	   FiniteBag StringBag = randomStringBag(i);
	   FiniteBag newStringBag = StringBag;
	   FiniteBag BoolBag = randomBoolBag(i, rangeTest);
	   FiniteBag newBoolBag = BoolBag;
	   Random newRandom = new Random();
	   int num = newRandom.nextInt(rangeTest);
	   if (num < (rangeTest / 3)){
	       newStringBag = StringBag.add("Hello");
	       check = 1;
	   } else if (num < (2 * (rangeTest / 3))){
	       newIntBag = IntBag.add(num);
	       check = 2;
	   } else {
	       newBoolBag = BoolBag.add(true);
	       check = 3;
	       }
	   if (check == 1 && newStringBag.member("Hello")){
	       passed++;
	   } else if (check == 2 && newIntBag.member(num)){
	       passed++;
	   } else if (check == 3 && newBoolBag.member(true) &&
		      newBoolBag.multiplicity(true) == BoolBag.multiplicity(true) + 1){
	       passed++;
	   } else {
	       failed++;
	   }
       }
       return (passed+" tests passed. " + failed+" tests failed.");
   }
    public static String addXTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	int type = 0;
	for (int i=0;i<numTest;i++){
	    FiniteBag IntBag = randomIntBag(i, rangeTest);
	    FiniteBag newIntBag = IntBag;
	    FiniteBag StringBag = randomStringBag(i);
	    FiniteBag newStringBag = StringBag;
	    FiniteBag BoolBag = randomBoolBag(i, rangeTest);
	    FiniteBag newBoolBag = BoolBag;
	    Random newRandom = new Random();
	    int num = newRandom.nextInt(rangeTest);
	    if (num < (rangeTest / 3)){
		newStringBag = StringBag.add("c", num);
		type = 1;
	    } else if (num < (2 * (rangeTest / 3))){
		newIntBag = IntBag.add(num, num);
		type = 2;
	    } else {
		newBoolBag = BoolBag.add(true, num);
		type = 3;
		}
	    newStringBag = StringBag.add("c", num);
	    if (type == 1 &&
		newStringBag.member("c") &&
		newStringBag.multiplicity("c") == StringBag.multiplicity("c")+num){
		passed++;
	    } else if (type == 2 &&
		newIntBag.member(num) &&
		newIntBag.multiplicity(num) == IntBag.multiplicity(num)+num){
		passed++;
	    } else if (type == 3 &&
		newBoolBag.member(true) &&
		newBoolBag.multiplicity(true) == BoolBag.multiplicity(true)+num){
		passed++;
	    } else {
		failed++;
		if (type == 3){
		    System.out.println (BoolBag + "");
		    System.out.println (BoolBag.multiplicity(true) + ", " +
					num + ". " +
					newBoolBag.multiplicity(true));
		}
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String memberTest(int numTest, int rangeTest){
        int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    FiniteBag IntBag = randomIntBag(i, rangeTest);
	    FiniteBag newIntBag = IntBag;
	    FiniteBag StringBag = randomStringBag(i);
	    FiniteBag newStringBag = StringBag;
	    FiniteBag BoolBag = randomBoolBag(i, rangeTest);
	    FiniteBag newBoolBag = BoolBag;
	    Random newRandomY = new Random();
	    Random newRandomX = new Random();
	    int y = newRandomY.nextInt(rangeTest);
	    int x = newRandomX.nextInt(rangeTest);
	    String yString = "y";
	    String xString = "x";
	    boolean yBool = true;
	    boolean xBool = false;
	    if (x < (rangeTest/2)){
		y = x;
		/*	if (x < (rangeTest / 3)){
		    newStringBag = StringBag.add("Hello");
		    check = 1;
		} else if (x < (2 * (rangeTest / 3))){
		    y = x;
		    check = 2;
		} else {
		    newBoolBag = BoolBag.add(true);
		    check = 3;
		}*/
	    } else {
		IntBag = IntBag.add(y);
	    }
	    if (IntBag.add(x).member(y)){
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
	    FiniteBag setA = randomIntBag((i/2), rangeTest);
	    FiniteBag setB = randomIntBag((i/2), rangeTest);
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
	FiniteBag empty = new Empty();
	FiniteBag b0 = new Branch (empty, 0, 1, empty);
        FiniteBag b1 = new Branch (empty, 1, 1, empty);
	FiniteBag b3 = new Branch (empty, 3, 1, empty);
	FiniteBag b5 = new Branch (empty, 5, 1, empty);
	FiniteBag b8 = new Branch (empty, 8, 1, empty);
	FiniteBag b7 = new Branch (b5, 7, 1, b8);
	FiniteBag b6 = new Branch (b5, 6, 1, b8);
	FiniteBag b2 = new Branch (b1, 2, 1, b3);
	FiniteBag b2b = new Branch (b0, 2, 1, b3);
	FiniteBag b4 = new Branch (b2, 4, 1, b6);
	FiniteBag b4b = new Branch (b2b, 4, 1, b7);
	FiniteBag unBal0 = new Branch (empty, 0, 1, empty);
	FiniteBag unBal1 = new Branch (unBal0, 1, 1, empty);
	FiniteBag unBal2 = new Branch (unBal1, 2, 1, empty);
	FiniteBag unBal3 = new Branch (unBal2, 3, 1, empty);
	FiniteBag unBal4 = new Branch (unBal3, 4, 1, empty);
	FiniteBag unBalLeft = new Branch (unBal4, 5, 1, empty);
	FiniteBag unBal8 = new Branch (empty, 8, 1, empty);
	FiniteBag unBal7 = new Branch (empty, 7, 1, unBal8);
	FiniteBag unBal6 = new Branch (empty, 6, 1, unBal7);
	FiniteBag unBal = new Branch (unBal4, 5, 1, unBal6);
	FiniteBag string1 = new Branch (empty, "Hello", 1, empty);
	FiniteBag string2 = new Branch (empty,"World", 1, empty);
	FiniteBag string3 = new Branch (string1, "Hello", 5,empty);
	FiniteBag string = new Branch (string3, "Hello World", 1, string2);
	
	//TESTS
	System.out.println("" + unBal4.depth());
	System.out.println("" + unBal6.depth());
	System.out.println("" + unBal.depth());
	System.out.println("" + unBal3);
	System.out.println("" + unBal3.rotateRight());
	System.out.println("" + unBal6);
	System.out.println("" + unBal6.rotateLeft());
	//System.out.println("" + unBalLeft.rotateRight().rotateRight());
	System.out.println ("RANDOMLY GENERATED TESTS");
	//System.out.println (memberTest(25, 1000));
	//System.out.println (unionTest(25, 1000));
	//System.out.println (addTest(25, 1000));
	//System.out.println (addXTest(25, 1000));
	System.out.println ();
	System.out.println ("PREMADE TESTS");
	System.out.println ("Should be true. Is "+string.member("Hello"));
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



			    
