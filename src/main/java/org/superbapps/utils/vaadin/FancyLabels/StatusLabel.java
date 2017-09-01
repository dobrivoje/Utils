package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import java.util.HashMap;
import java.util.Map;
import org.superbapps.utils.common.Enums.Statuses;

/**
 *
 * @author д06ри
 */
public class StatusLabel extends Label {

    private final Map<Statuses, Statuses> color = new HashMap<>();
    private String iconCode;

    public StatusLabel() {
        setContentMode(ContentMode.HTML);
        setSizeUndefined();

        color.put(Statuses.OK, Statuses.OK_COLOR);
        color.put(Statuses.BLACK_LIST, Statuses.BLACK_LIST_COLOR);
        color.put(Statuses.IN_PROGRESS, Statuses.IN_PROGRESS_COLOR);
        color.put(Statuses.UNKNOWN, Statuses.UNKNOWN_COLOR);
        color.put(Statuses.NO_LICENCE, Statuses.NO_LICENCE_COLOR);
    }

    public StatusLabel(Statuses status, String property) {
        this();
        render(status);
        setValue(iconCode + " " + property);
    }

    private void render(Statuses status) {
        iconCode = "<span class=\"v-icon\" style=\"font-family: "
                + FontAwesome.CIRCLE.getFontFamily()
                + ";color:"
                + color.get(status).toString()
                + "\">&#x"
                + Integer
                .toHexString(FontAwesome.CIRCLE.getCodepoint())
                + ";</span>";
    }
}
