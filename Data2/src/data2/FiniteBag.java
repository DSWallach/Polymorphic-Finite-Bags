/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data2;

/**
 *
 * @author David Wallach
 */
public interface FiniteBag<E extends Comparable<E>> extends Sequence<E> {

    /**
     * Returns the number of non-unique objects in a finite bag. Unlike Finite sets
     * FiniteBags can contain multiple objects that are equivalent. So a FiniteBag 
     * containing ("James", 1, 1, "Ted") would have a cardinality of four.
     *
     * @return   an integer equal to the total number of elements in the FiniteBag
     */
    public int cardinality();

    /**
     * Returns an integer value representing the greatest depth of the FiniteBag
     *
     * @return an integer equal to the greatest depth of the FiniteBag
     */
    public int depth();

    /**
     * Returns a Boolean value true if the set is empty and false otherwise.
     *
     * @return   a Boolean value stating whether the set is empty
     */
    public boolean isEmptyHuh();

    /**
     * Determines whether or not a particular value appears at least
     * once in the FiniteBag
     *
     * @param e  the object to look for and compare to
     * @return   a Boolean stating whether or not the element is in the FiniteBag
     */
    public boolean member(E e);

    /**
     * Returns the number of instances of an object in a FiniteBag. If there
     * are two 3's, then multiplicity(3) returns 2.
     *
     * @param e  the object of type E to obtain the count of
     * @return   the number of the element in the FiniteBag
     */
    public int multiplicity(E e);

    /**
     * Adds an object of type E to the FiniteBag
     * 
     * @param e  the object to add to the FiniteBag
     * @return a new FiniteBag with the element added
     */
    public FiniteBag<E> add(E e);
 
    /**
     * Adds some number (num) of T's to the FiniteBag
     * 
     * @param e  the element of type T to add to the FiniteBag
     * @return a new FiniteBag with the element added
     */
    public FiniteBag<E> add(E e, int num);

    /**
     * Removes an object of type E from the FiniteBag if it's there. Only removes
     * one if there are multiple with the same value.
     *
     * @param e  the element of type T to remove from the FiniteBag
     * @return the FiniteBag with one less E of value e
     */
    public FiniteBag<E> remove(E e);

    /**
     * Removes some number (num) objects of type E from the FiniteBag if the t is present.
     *
     * @param e  the object of type E to remove from the FiniteBag
     * @return the FiniteBag with one less E of value e
     */
    public FiniteBag<E> remove(E e, int num);

    /**
     * Returns the union of two FiniteBags. i.e. A new FiniteBag containing
     * all of the objects in each of the original FiniteBags. The multiplicity
     * of each object in the new FiniteBag is the sum of its multiplicities 
     * from the original two FiniteBags.
     *
     * @param t  the FiniteBag to join with this FiniteBag 
     * @return   a new FiniteBag containg the objects of both input FiniteBags
     */
    public FiniteBag<E> union(FiniteBag<E> t);

    /**
     * Returns the intersection of two FiniteBags. i.e. a new FiniteBag containing
     * the objects thatappear in both input FiniteBags. The multiplicities are
     * the lower value of the two multiplicities from the original FiniteBags.
     *
     * @param t  the FiniteBag to intersect with this FiniteBag
     * @return   a new FiniteBag contain only the objects that appear in both input FiniteBags
     */
    public FiniteBag<E> inter(FiniteBag<E> t);

    /**
     * Returns all of the object in the orignal FiniteBag, except for those that are
     * also contained within FinitBag t. If the multiplicity of the object in the 
     * original FiniteBag is greater than the multiplicity of the object in 
     * FinteBag t then the new multiplicity is the difference of the multiplicities.
     *
     * @param t  the FiniteBag to subtract from this FiniteBag
     * @return   a new FiniteBag without the objects that are in t
     */
    public FiniteBag<E> diff(FiniteBag<E> t);

    /**
     * Rotates the FiniteBag to the right. So what was in the left position 
     * on the branch is now in the head and the head is in the right position 
     * on the branch along with everything that was in the right position previously.
     *
     * @return a new FiniteBag with the same objects as the old one but rotated to the right
     */
    public FiniteBag<E> rotateRight();
    /** 
     * Performs the same function as rotateRight except it rotates the tree
     * to the left
     *
     * @return a new FiniteBag with the same elements as the old one but rotated to the left
     */
    public FiniteBag<E> rotateLeft();
    /**
     * Travels up the tree making sure that at any root the difference 
     * between the length of its left and right branches is never 
     * greater than 1. If it is checkAVL performs rotateRight() or
     * rotateLeft() on the tree depending on which branch is longer.
     *
     * @return a balanced AVL tree with the same elements as the original
     */
    public FiniteBag<E> checkAVL();
    /**
     * Returns a boolean based on whether or not two FiniteBags are equal. 
     * i.e. are the multiplicities of each object in the FiniteBags equal.
     *
     * @param t  the FiniteBag to compare to this FiniteBag
     * @return   the Boolean value true if they are equal, and false otherwise
     */
    public boolean equal(FiniteBag<E> t);

    /**
     * Returns a boolean based on whether or not this FiniteBag is a subset 
     * of FiniteBag t. It returns true when each object in this FiniteBag 
     * has multiplicities less than or equal to the multiplicities of the 
     * same objects in t.
     *
     * @param t  the FiniteBag to compare to this FiniteBag
     * @return   the Boolean value true if this FiniteBag is a subset, and false otherwise
     */
    public boolean subset(FiniteBag<E> t);

    /**
     * Finds the maximum type E object in the FiniteBag
     *
     * @return   the maximum value of type E objects in the FiniteBag
     */
    public E max();

}
