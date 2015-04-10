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
public interface Sequence<E extends Comparable<E>> extends Sequenced<E>{
    public E here();
    public boolean isEmptyHuh();
    public Sequence<E> next();
}