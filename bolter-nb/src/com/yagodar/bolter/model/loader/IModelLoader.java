/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yagodar.bolter.model.loader;

/**
 *
 * @author АППДКт78М
 */
public interface IModelLoader<T> {    
    public T load();
    public void save(T model);
}
