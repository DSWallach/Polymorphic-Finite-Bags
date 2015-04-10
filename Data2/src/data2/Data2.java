package data2;

/**
 *
 * @author David Wallach
 */
import java.util.*;
import java.lang.Math.*;

public class Data2 {
    public static void printTree(FiniteBag e, int level) {
        Branch root;
        if (e.isEmptyHuh()) {
            return;
        } else {
            root = (Branch) e;
        }
        printTree(root.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|----" + root.iden + "(" + root.multi + ")");
        } else {
            System.out.println(root.iden + "(" + root.multi + ")");
        }
        printTree(root.left, level + 1);
    }
    public static FiniteBag worstCaseBag(int numAdd) {
        FiniteBag rFiniteBag = new Empty();
        for (int i = numAdd; i < 2*numAdd; i++) {
            rFiniteBag = rFiniteBag.add(i);
        }
        return rFiniteBag;
    }
    public static FiniteBag randomIntBag(int numAdd, int rangeAdd) {
        FiniteBag rFiniteBag = new Empty();
        for (int i = 0; i < numAdd; i++) {
            Random newRandom = new Random();
            int randInt = newRandom.nextInt(rangeAdd);
            rFiniteBag = rFiniteBag.add(randInt);
        }
        return rFiniteBag;
    }
    public static FiniteBag randomBoolBag(int numAdd, int rangeAdd) {
        FiniteBag rFiniteBag = new Empty();
        for (int i = 0; i < numAdd; i++) {
            Random newRandom = new Random();
            int randInt = newRandom.nextInt(rangeAdd);
            if (randInt < (rangeAdd / 2)) {
                rFiniteBag = rFiniteBag.add(true);
            } else {
                rFiniteBag = rFiniteBag.add(false);
            }
        }
        return rFiniteBag;
    }
    public static FiniteBag randomStringBag(int numAdd) {
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
        for (int i = 0; i < numAdd; i++) {
            Random newRandom = new Random();
            int randInt = newRandom.nextInt(25);
            rFiniteBag = rFiniteBag.add(stringArray[randInt]);
        }
        return rFiniteBag;
    }
    public static FiniteBag unBalIntAdd(FiniteBag t, int e) {
        if (t.isEmptyHuh()) {
            return new Branch(new Empty(),
                    e,
                    1,
                    new Empty());
        } else {
            Branch bT = (Branch<Integer>) t;
            if (bT.iden.equals(e)) {
                return new Branch(bT.left,
                        bT.iden,
                        bT.multi + 1,
                        bT.right);
            } else if (bT.iden.compareTo(e) > 0) {
                return new Branch(unBalIntAdd(bT.left, e),
                        bT.iden,
                        bT.multi,
                        bT.right);
            } else {
                return new Branch(bT.left,
                        bT.iden,
                        bT.multi,
                        unBalIntAdd(bT.right, e));
            }
        }
    }
    public static String balRemoveTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        FiniteBag IntBag = worstCaseBag(3 * (numTest / 2));
        for (int i = 0; i < numTest; i++) {
            IntBag = IntBag.remove(i);
            Branch IntBranch = (Branch) IntBag;
            if (IntBranch.left.depth() - IntBranch.right.depth() > 1) {
                failed++;
                printTree(IntBag, 0);
                System.out.println("======================");
            } else if (IntBranch.right.depth() - IntBranch.left.depth() > 1) {
                failed++;
                printTree(IntBag, 0);
                System.out.println("======================");
            } else {
                passed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String cardinalityTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i, rangeTest);
            if (IntBag.cardinality() == i) {
                passed++;
            } else {
                failed++;
                System.out.println(IntBag + "");
                System.out.println(IntBag.cardinality() + ", " + i);
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String emptyTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        boolean empty;
        FiniteBag IntBag = new Empty();
        for (int i = 0; i < numTest; i++) {
            Random newRandom = new Random();
            int num = newRandom.nextInt(rangeTest);
            if (num < (rangeTest / 2)) {
                empty = true;
                IntBag = new Empty();
            } else {
                empty = false;
                IntBag = IntBag.add(i);
            }
            if (empty && IntBag.isEmptyHuh()) {
                passed++;
            } else if (!empty && !IntBag.isEmptyHuh()) {
                passed++;
            } else {
                failed++;
                printTree(IntBag, 0);
                System.out.println("" + empty);
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String memberTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i, rangeTest);
            Random newRandomY = new Random();
            Random newRandomX = new Random();
            int y = newRandomY.nextInt(rangeTest);
            int x = newRandomX.nextInt(rangeTest);
            String yString = "y";
            String xString = "x";
            boolean yBool = true;
            boolean xBool = false;
            if (x < (rangeTest / 2)) {
                y = x;
            } else {
                IntBag = IntBag.add(y);
            }
            if (IntBag.add(x).member(y)) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String unionTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            Random newRandomX = new Random();
            Random newRandomY = new Random();
            int x = newRandomX.nextInt(rangeTest);
            int y = newRandomY.nextInt(rangeTest);
            FiniteBag BagA = randomIntBag((i / 2), rangeTest);
            FiniteBag BagB = randomIntBag((i / 2), rangeTest);
            if (x < (rangeTest / 2)) {
                BagA = BagA.add(x, y);
            } else {
                BagB = BagB.add(x, y);
            }
            if (BagA.union(BagB).multiplicity(x) == 
                    (BagA.multiplicity(x) + BagB.multiplicity(x))) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String multiplicityTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag StringBag = randomStringBag(i);
            FiniteBag newStringBag = StringBag.add("e", i);
            if (newStringBag.multiplicity("e") - StringBag.multiplicity("e") == i) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String depthTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        FiniteBag IntBag = new Empty();
        for (int i = 0; i < numTest; i++) {
            IntBag = unBalIntAdd(IntBag, i);
            if (IntBag.depth() == i + 1) {
                passed++;
            } else {
                failed++;
                printTree(IntBag, 0);
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String addTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        int type = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i, rangeTest);
            FiniteBag newIntBag = IntBag;
            FiniteBag StringBag = randomStringBag(i);
            FiniteBag newStringBag = StringBag;
            FiniteBag BoolBag = randomBoolBag(i, rangeTest);
            FiniteBag newBoolBag = BoolBag;
            Random newRandom = new Random();
            int num = newRandom.nextInt(rangeTest);
            if (num < (rangeTest / 3)) {
                newStringBag = StringBag.add("c", num);
                type = 1;
            } else if (num < (2 * (rangeTest / 3))) {
                newIntBag = IntBag.add(num, num);
                type = 2;
            } else {
                newBoolBag = BoolBag.add(true, num);
                type = 3;
            }
            if (type == 1
                    && newStringBag.multiplicity("c") == StringBag.multiplicity("c") + num) {
                passed++;
            } else if (type == 2
                    && newIntBag.multiplicity(num) == IntBag.multiplicity(num) + num) {
                passed++;
            } else if (type == 3
                    && newBoolBag.multiplicity(true) == BoolBag.multiplicity(true) + num) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String removeTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        int type = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i, rangeTest);
            FiniteBag newIntBag = IntBag;
            FiniteBag StringBag = randomStringBag(i);
            FiniteBag newStringBag = StringBag;
            FiniteBag BoolBag = randomBoolBag(i, rangeTest);
            FiniteBag newBoolBag = BoolBag;
            Random newRandom = new Random();
            int num = newRandom.nextInt(rangeTest);
            if (num < (rangeTest / 3)) {
                newStringBag = StringBag.remove("c", num);
                type = 1;
            } else if (num < (2 * (rangeTest / 3))) {
                newIntBag = IntBag.remove(num, num);
                type = 2;
            } else {
                newBoolBag = BoolBag.remove(true, num);
                type = 3;
            }
            if (type == 1
                    && newStringBag.multiplicity("c") - StringBag.multiplicity("c") <= num) {
                passed++;
            } else if (type == 2
                    && newIntBag.multiplicity(num) - StringBag.multiplicity(num) <= num) {
                passed++;
            } else if (type == 3
                    && newBoolBag.multiplicity(true) - BoolBag.multiplicity(true) <= num) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String interTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag1 = randomIntBag(i, rangeTest);
            FiniteBag IntBag2 = IntBag1.union(worstCaseBag(i));
            FiniteBag IntBag3 = IntBag1.union(worstCaseBag(i+numTest));
            if (IntBag2.inter(IntBag3).equal(IntBag1)) {
                passed++;
            } else {
                failed++;
                printTree(IntBag1, 0);
                System.out.println("==================================");
                printTree(IntBag2.inter(IntBag3), 0);
                System.out.println("==================================");
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String diffTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag0 = randomIntBag(i, rangeTest);
            FiniteBag IntBag1 = randomIntBag(i, rangeTest);
            FiniteBag IntBag2 = IntBag0.union(IntBag1);
            if (IntBag2.diff(IntBag0).equal(IntBag1)) {
                passed++;
            } else {
                failed++;
                printTree(IntBag1, 0);
                System.out.println("=============");
                printTree(IntBag2, 0);
                System.out.println("================" + IntBag1.diff(IntBag2));
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String equalTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        int check = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag1 = randomIntBag(i, rangeTest);
            FiniteBag IntBag2 = IntBag1;
            Random newRandom = new Random();
            int num = newRandom.nextInt(rangeTest);
            if (num < (rangeTest / 3)) {
                IntBag2 = IntBag2.add(num);
                check = 1;
            } else if (num < 2 * (rangeTest / 3)) {
                IntBag1 = IntBag1.add(i);
                IntBag2 = IntBag2.add(i, 2);
                check = 1;
            } else {
                check = 2;
            }
            if (IntBag1.equal(IntBag2) && check == 2) {
                passed++;
            } else if (!IntBag1.equal(IntBag2) && check == 1) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String subsetTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        int type = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i, rangeTest);
            FiniteBag newIntBag;
            Random newRandom = new Random();
            int num = newRandom.nextInt(rangeTest);
            if (num < (rangeTest / 4)) {
                type = 1;
                newIntBag = IntBag.add(num, num);
            } else if (num < (rangeTest / 2)) {
                type = 1;
                newIntBag = IntBag.add(num, 2);
                IntBag = IntBag.add(num);
            } else if (num < 3 * (rangeTest / 4)) {
                type = 2;
                newIntBag = IntBag;
            } else {
                type = 2;
                IntBag = IntBag.add(i, i);
                newIntBag = IntBag.remove(i, i);
            }
            if (type == 1 && !newIntBag.subset(IntBag)) {
                passed++;
            } else if (type == 2 && newIntBag.subset(IntBag)) {
                passed++;
            } else {
                failed++;
                printTree(IntBag, 0);
                System.out.println("==================================================" + type);
                printTree(newIntBag, 0);
                System.out.println("==================================================" + newIntBag.diff(IntBag));
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String maxTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
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
        FiniteBag IntBag = new Empty();
        FiniteBag StringBag = new Empty();
        for (int i = 0; i < numTest; i++) {
        Random newRandom = new Random();
        int num = newRandom.nextInt(rangeTest);
            IntBag = IntBag.add(i);
            StringBag = StringBag.add(stringArray[i]);
            if (IntBag.max().compareTo(i) == 0 &&
                    StringBag.max().compareTo(stringArray[i]) == 0) {
                passed++;
            } else {
                failed++;
                printTree(IntBag, 0);
                System.out.println("========================" + i + ", " + IntBag.max());
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String balanceTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        FiniteBag IntBag = new Empty();
        for (int i = 0; i < numTest; i++) {
            IntBag = IntBag.add(i);
            Branch IntBranch = (Branch)IntBag;
            if (Math.abs(IntBranch.left.depth() - IntBranch.right.depth()) <= 1) {
                passed++;
            } else {
                failed++;
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    public static String sequenceTest(int numTest, int rangeTest) {
        int passed = 0;
        int failed = 0;
        for (int i = 0; i < numTest; i++) {
            FiniteBag IntBag = randomIntBag(i + 1, rangeTest);
            Branch IntBranch = (Branch) IntBag;
            Sequence here = IntBag.seq();
            if (here.next().isEmptyHuh()) {
                if (IntBranch.left.isEmptyHuh() && IntBranch.right.isEmptyHuh()) {
                    passed++;
                } else {
                    failed++;
                }
            } else {
                if (IntBranch.multi > 1) {
                    if (here.here().compareTo(here.next().here()) == 0) {
                        passed++;
                    } else {
                        failed++;
                    }
                } else {
                    if (IntBranch.left.isEmptyHuh()) {
                        Branch right = (Branch) IntBranch.right;
                        if (here.next().here().compareTo(right.iden) == 0) {
                            passed++;
                        } else {
                            failed++;
                        }
                    } else {
                        Branch left = (Branch) IntBranch.left;
                        if (here.next().here().compareTo(left.iden) == 0) {
                            passed++;
                        } else {
                            failed++;
                        }
                    }
                }
            }
        }
        return (passed + " tests passed. " + failed + " tests failed.");
    }
    //If has next returns true then there should be a next thing and if it returns false there cant be 
    public static void main(String args[]) {
        
        //RANDOM TESTS
        System.out.println("RANDOMLY GENERATED TESTS");
        System.out.println(balRemoveTest(50, 1000) + " Remove Balance Test");
        System.out.println(cardinalityTest(50, 1000) + " FiniteBag.cardinality()");
        System.out.println(emptyTest(50, 1000) + " FiniteBag.isEmptyHuh()");
        System.out.println(memberTest(50, 1000) + " FiniteBag.member(E e)");
        System.out.println(unionTest(50, 1000) + " FiniteBag.union(FiniteBag<E>)");
        System.out.println(multiplicityTest(50, 1000) + " FiniteBag.multiplicity(E e)");
        System.out.println(depthTest(25, 1000) + " FiniteBag.depth()");
        System.out.println(addTest(75, 1000) + " FiniteBag.add(E e, int num)");
        System.out.println(removeTest(75, 1000) + " FiniteBag.remove(E e, int num)");
        System.out.println(interTest(25, 1000) + " FiniteBag.inter(FiniteBag<E>)");
        System.out.println(diffTest(25, 1000) + " FiniteBag.diff(FiniteBag<E>)");
        System.out.println(equalTest(75, 1000) + " FiniteBag.equal(FiniteBag<E>)");
        System.out.println(subsetTest(25, 1000) + " FiniteBag.subset(FiniteBag<E>)");
        System.out.println(maxTest(25, 1000) + " FiniteBag.max()");
        System.out.println(balanceTest(50, 1000) + " Add Balance Test");
        System.out.println(sequenceTest(50, 1000) + " Sequence Test");    
    }
}
