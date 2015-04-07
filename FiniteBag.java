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
     * Returns a Boolean value true if the set is empty and false otherwise.
     *
     * @return   a Boolean value stating whether the set is empty
     */
    public boolean isEmptyHuh();

    /**
     * Determines whether or not a particular value appears at least
     * once in the FiniteBag
     *
     * @param b  the Bag to look for and compare to
     * @return   a Boolean stating whether or not the element is in the multiset
     */
    public boolean member(E e);

    /**
     * Returns the number of times an element appears in the multiset. If there
     * are three 2's, then multiplicity(2) returns 3.
     *
     * @param t  the element of type T to obtain the count of
     * @return   the number of the element in the multiset
     */
    public int multiplicity(E e);

    /**
     * Adds an element T to the multiset
     * 
     * @param t  the element of type T to add to the multiset
     * @return a new multiset with the element added
     */
    public FiniteBag<E> add(E e);

    /**
     * Adds some number of T's to the multiset
     * 
     * @param t  the element of type T to add to the multiset
     * @return a new multiset with the element added
     */
    public FiniteBag<E> add(E e, int num);

    /**
     * Removes an element T from the multiset if it's there. Only removes
     * one if there are multiple with the same value.
     *
     * @param t  the element of type T to remove from the multiset
     * @return the MultiSet with one less T of value t
     */
    public FiniteBag<E> remove(E e);

    /**
     * Removes some number of T's from the multiset if the t is present.
     *
     * @param t  the element of type T to remove from the multiset
     * @return the MultiSet with one less T of value t
     */
    public FiniteBag<E> remove(E e, int num);

    /**
     * Creates the union of two multisets -- the returned set has all the 
     * elements of both sets, including all repeats. In other words, the
     * multiplicity of each element in the new multiset is the sum of the
     * two multiplicities of that element, one from each set pre-union.
     *
     * @param s  the MultiSet to join this MultiSet with (of type T)
     * @return   a new MultiSet with the elements of both input MultiSets
     */
    public FiniteBag<E> union(FiniteBag<E> t);

    /**
     * Creates the intersection of two multisets -- the returned set has all the 
     * elements that are only in both sets. In other words, the
     * multiplicity of each element in the new multiset is the minimum of the
     * two multiplicities of that element, one from each set pre-intersection.
     *
     * @param s  the MultiSet to intersect with this MultiSet
     * @return   a new MultiSet with the elements only in both input MultiSets
     */
    public FiniteBag<E> inter(FiniteBag<E> t);

    /**
     * Creates the difference of two multisets, so that the returned multiset
     * has only those elements that are in this multiset but not the input set s.
     * The multiplicity of each element in the returned multiset is the difference
     * of the multiplicity of it in this multiset and in the input, where possible
     * resulting multiplicities are only 0 and the natural numbers. 
     *
     * @param s  the MultiSet to subtract from this MultiSet
     * @return   a new MultiSet with the elements of both input MultiSets
     */
    public FiniteBag<E> diff(FiniteBag<E> t);

    /**
     * Determines whether or not two multisets are equal, meaning that the
     * multiplicity of each element in the multisets are equal.
     *
     * @param s  the MultiSet to compare to this multiset
     * @return   the Boolean value true if they are equal, and false otherwise
     */
    public boolean equal(FiniteBag<E> t);

    /**
     * Determines whether this multiset is a subset of the input multiset.
     * This is true if each element in this multiset has multiplicities
     * less than or equal to the multiplicities of the elements in the input.
     * 
     *
     * @param s  the MultiSet to compare to this multiset (potential superset)
     * @return   the Boolean value true if this multiset is a subset, and false otherwise
     */
    public boolean subset(FiniteBag<E> t);

    /**
     * Finds the maximum element of type E in the multiset
     *
     * @return   the maximum value of type E in the multiset
     */
    public E max();

}
