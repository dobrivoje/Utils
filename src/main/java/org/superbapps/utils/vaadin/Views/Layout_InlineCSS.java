/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.Views;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 *
 * @author д06ри
 */
public class Layout_InlineCSS extends CssLayout {

    public Layout_InlineCSS() {
        super();
    }

    @Override
    protected String getCss(Component c) {
        return "display: inline-block;";
    }

}
