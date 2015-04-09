import java.util.*;
class testData2 {
    public static void printTree(FiniteBag e, int level){
	Branch root;
	if(e.isEmptyHuh()){
	    return;
	} else {
	    root = (Branch)e;
	}
	printTree(root.right, level+1);
	if(level!=0){
	    for(int i=0;i<level-1;i++) {
		System.out.print("|\t");
	    }
            System.out.println("|----"+root.iden+"("+root.multi+")");
	} else {
	    System.out.println(root.iden);
	}
	printTree(root.left, level+1);
    }
    public static FiniteBag worstCaseBag(int numAdd, int rangeAdd){
	FiniteBag rFiniteBag = new Empty();
	for (int i=0; i<numAdd;i++){
	    rFiniteBag = rFiniteBag.add(i);
	}
	return rFiniteBag;
    }
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
    public static String cardinalityTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	FiniteBag IntBag = new Empty();
	for (int i=0;i<numTest;i++){
	    IntBag = IntBag.add(i);
	    if (IntBag.cardinality() == i+1){
		passed++;
	    } else {
		failed++;
		System.out.println (IntBag + "");
		System.out.println (IntBag.cardinality() + ", " + i);
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String emptyTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	boolean empty;
	FiniteBag IntBag = new Empty();
	for (int i=0;i<numTest;i++){
	    Random newRandom = new Random();
	    int num = newRandom.nextInt(rangeTest);
	    if (num < (rangeTest / 2)){
		empty = true;
		IntBag = new Empty();
	    } else {
		empty = false;
		IntBag = IntBag.add(i);
	    }
	    if (empty && IntBag.isEmptyHuh()){
		passed++;
	    } else if (!empty && !IntBag.isEmptyHuh()){
		passed++;
	    } else {
		failed++;
		printTree(IntBag, 0);
		System.out.println(""+empty);
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
    public static String multiplicityTest(int numTest, int rangeTest){	
	int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    FiniteBag StringBag = randomStringBag(i);
	    FiniteBag newStringBag = StringBag.add("e", i);
	    if (newStringBag.multiplicity("e") - StringBag.multiplicity("e") == i){
		passed++;
	    } else {
		failed++;
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    /*public static String depthTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	FiniteBag emptyBag = new Empty();
	FiniteBag IntBag = new Branch(emptyBag, 0, 1, emptyBag);
	for (int i=1;i<numTest+1;i++){
	    IntBag = new Branch (IntBag.left,
				 IntBag.iden,
				 IntBag.multi,
				 
	    if (IntBag.isEmptyHuh() && IntBag.depth() == 0){
		passed++;
	    } 
	}
	return (passed+" tests passed. " + failed+" tests failed.");
	}*/
    public static String addTest(int numTest, int rangeTest){
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
		newStringBag = StringBag.add("Hello");
		type = 1;
	    } else if (num < (2 * (rangeTest / 3))){
		newIntBag = IntBag.add(num);
		type = 2;
	    } else {
		newBoolBag = BoolBag.add(true);
		type = 3;
	    }
	    if (type == 1 && newStringBag.member("Hello")){
		passed++;
	    } else if (type == 2 && newIntBag.member(num)){
		passed++;
	    } else if (type == 3 && newBoolBag.member(true) &&
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
		} else if (type ==2){
		    System.out.println (IntBag + "");
		    System.out.println (IntBag.multiplicity(num) + ", " +
					num + ". " +
					newIntBag.multiplicity(num));
		} else {
		    printTree(StringBag,0);
		    System.out.println ("==================================================");
		    printTree(newStringBag, 0);
		    System.out.println (StringBag.multiplicity("c") + ", " +
					num + ". " +
					newStringBag.multiplicity("c"));
		}
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String removeTest(int numTest, int rangeTest){
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
		newStringBag = StringBag.remove("c");
		type = 1;
	    } else if (num < (2 * (rangeTest / 3))){
		newIntBag = IntBag.remove(num);
		type = 2;
	    } else {
		newBoolBag = BoolBag.remove(true);
		type = 3;
	    }
	    if (type == 1 && newStringBag.multiplicity("c") - StringBag.multiplicity("c") <= 1){
		passed++;
	    } else if (type == 2 && newIntBag.multiplicity(num) - StringBag.multiplicity(num) <= 1){
		passed++;
	    } else if (type == 3 &&
		       newBoolBag.multiplicity(true)- BoolBag.multiplicity(true) <= 1){
		passed++;
	    } else {
		failed++;
		if (type == 3){
		    System.out.println (BoolBag + "");
		    System.out.println (BoolBag.multiplicity(true) + ", " +
					num + ". " +
					newBoolBag.multiplicity(true));
		} else if (type ==2){
		    System.out.println (IntBag + "");
		    System.out.println (IntBag.multiplicity(num) + ", " +
					num + ". " +
					newIntBag.multiplicity(num));
		} else {
		    printTree(StringBag,0);
		    System.out.println ("==================================================");
		    printTree(newStringBag, 0);
		    System.out.println (StringBag.multiplicity("c") + ", " +
					num + ". " +
					newStringBag.multiplicity("c"));
		}
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String removeXTest(int numTest, int rangeTest){
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
		newStringBag = StringBag.remove("c", num);
		type = 1;
	    } else if (num < (2 * (rangeTest / 3))){
		newIntBag = IntBag.remove(num, num);
		type = 2;
	    } else {
		newBoolBag = BoolBag.remove(true, num);
		type = 3;
	    }
	    if (type == 1 &&
	        newStringBag.multiplicity("c") - StringBag.multiplicity("c") <= num){
		passed++;
	    } else if (type == 2 &&
		       newIntBag.multiplicity(num) - StringBag.multiplicity(num) <= num){
		passed++;
	    } else if (type == 3 &&
		       newBoolBag.multiplicity(true)- BoolBag.multiplicity(true) <= num){
		passed++;
	    } else {
		failed++;
		if (type == 3){
		    System.out.println (BoolBag + "");
		    System.out.println (BoolBag.multiplicity(true) + ", " +
					num + ". " +
					newBoolBag.multiplicity(true));
		} else if (type ==2){
		    System.out.println (IntBag + "");
		    System.out.println (IntBag.multiplicity(num) + ", " +
					num + ". " +
					newIntBag.multiplicity(num));
		} else {
		    printTree(StringBag,0);
		    System.out.println ("==================================================");
		    printTree(newStringBag, 0);
		    System.out.println (StringBag.multiplicity("c") + ", " +
					num + ". " +
					newStringBag.multiplicity("c"));
		}
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
    public static String interTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    FiniteBag IntBag1 = randomIntBag(i, rangeTest);
	    FiniteBag IntBag2 = IntBag1.add(i);
	    if (IntBag1.inter(IntBag2).equal(IntBag1)){
		passed++;
	    } else {
		failed++;
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static String diffTest(int numTest, int rangeTest){
	int passed = 0;
	int failed = 0;
	for (int i=0;i<numTest;i++){
	    FiniteBag IntBag1 = randomIntBag(i, rangeTest);
	    FiniteBag IntBag2 = IntBag1.add(i);
	    if (IntBag2.diff(IntBag1).equal(new Branch (new Empty(),
							i,
							1,
							new Empty()))){
		passed++;
	    } else {
		failed++;
		printTree(IntBag1, 0);
		System.out.println("========================================================");
		printTree(IntBag2, 0);
		System.out.println("========================================================");
		printTree(IntBag1.diff(IntBag2), 0);
	    }
	}
	return (passed+" tests passed. " + failed+" tests failed.");
    }
    public static void main (String args[]){
	FiniteBag rand = randomStringBag(100);
	FiniteBag worst = worstCaseBag(10, 1000);
	FiniteBag worst2 = worstCaseBag(9, 1000);

	//RANDOM TESTS
	// System.out.println (worst.here()+"");
	// System.out.println (worst.next().here()+"");
	// System.out.println (worst.max()+"");
	System.out.println ("RANDOMLY GENERATED TESTS");
	System.out.println (diffTest(25, 1000)+" FiniteBag.diff(FiniteBag<E>)");
	System.out.println (interTest(25, 1000)+" FiniteBag.inter(FiniteBag<E>)");
	System.out.println (multiplicityTest(25, 1000)+" FiniteBag.multiplicity(E e)");
	System.out.println (emptyTest(25, 1000)+" FiniteBag.isEmptyHuh()");
	System.out.println (memberTest(25, 1000)+" FiniteBag.member(E e)");
	System.out.println (unionTest(25, 1000)+" FiniteBag.union(FiniteBag<E>)");
	System.out.println (addTest(25, 1000)+" FiniteBag.add(E e)");
	System.out.println (addXTest(25, 1000)+" FiniteBag.add(E e, int num)");
	System.out.println (removeTest(25, 1000)+" FiniteBag.remove(E e)");
	System.out.println (removeXTest(25, 1000)+" FiniteBag.remove(E e, int num)");
	System.out.println (cardinalityTest(25, 1000)+" FiniteBag.cardinality()");
    }
}
