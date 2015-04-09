import java.util.*;
class Data2 {
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
	FiniteBag rand = randomStringBag(100);
	FiniteBag worst = worstCaseBag(100, 1000);
	//RANDOM TESTS
	printTree(worst, 0);
	printTree(rand, 0);
	System.out.println ("RANDOMLY GENERATED TESTS");
	System.out.println (memberTest(25, 1000));
	System.out.println (unionTest(25, 1000));
	System.out.println (addTest(25, 1000));
	System.out.println (addXTest(25, 1000));
	System.out.println (removeTest(25, 1000));
	System.out.println (removeXTest(25, 1000));
    }
}
