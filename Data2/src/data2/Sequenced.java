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
public interface Sequenced<E extends Comparable<E>>{
    public Sequence<E> seq();
}
