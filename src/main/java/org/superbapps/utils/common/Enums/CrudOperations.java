/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.common.Enums;

/**
 *
 * @author д06ри
 */
public enum CrudOperations {

    CREATE("Create"),
    READ("Read"),
    UPDATE("Update"),
    DELETE("Delete"),
    //
    BUTTON_CAPTION_SAVE("Save"),
    BUTTON_CAPTION_UPDATE("Update"),
    BUTTON_CAPTION_NEW("Insert New Data"),
    BUTTON_CAPTION_CANCEL("Cancel");

    private final String name;

    private CrudOperations(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
