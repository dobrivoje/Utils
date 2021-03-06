package org.superbapps.utils.vaadin.MyWindows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.superbapps.utils.vaadin.Trees.ILayoutLockable;
import static org.superbapps.utils.vaadin.Views.View_Dashboard.NotificationsButton.ID;

/**
 *
 * @author д06ри
 */
public class WindowForm3 extends Window {

    protected TabSheet detailsWrapper = new TabSheet();
    protected VerticalLayout content = new VerticalLayout();

    protected Button actionButton;
    protected Button closeButton;

    //<editor-fold defaultstate="collapsed" desc="Konstruktori">
    private WindowForm3(String caption, Layout formLayout, String imageLocation, Button.ClickListener externalButtonClickListener) {
        this(caption, formLayout, imageLocation, "Save", externalButtonClickListener, false);
    }

    private WindowForm3(String caption, Layout formLayout, String imageLocation, String actionButtonCaption,
            Button.ClickListener externalButtonClickListener) {
        this(caption, formLayout, imageLocation, actionButtonCaption, externalButtonClickListener, false);
    }

    public WindowForm3(String caption, Layout formLayout, String imageLocation, String actionButtonCaption,
            Button.ClickListener externalButtonClickListener, boolean imageDefaultSize) {
        if (imageDefaultSize) {
            init(caption, formLayout, imageLocation, actionButtonCaption, externalButtonClickListener, -1, 150, null);
        } else {
            init(caption, formLayout, imageLocation, actionButtonCaption, externalButtonClickListener, -1, -1, null);
        }
    }

    /**
     * Implementacija Save-Close forme
     *
     * @param caption Form caption
     * @param formLayout Form to inject into this frame
     * @param imageLocation Left image on the frame
     * @param externalButtonClickListener Click listener to call upon action
     * button click
     * @param imgHeight Left image height
     * @param imgWidth Left image width
     */
    public WindowForm3(String caption, Layout formLayout, String imageLocation, Button.ClickListener externalButtonClickListener,
            int imgHeight, int imgWidth) {
        init(caption, formLayout, imageLocation, "Save", externalButtonClickListener, imgHeight, imgWidth, null);
    }

    /**
     * Typical Save-Close form
     *
     * @param caption Form caption
     * @param formLayout Form to inject into this frame
     * @param imageLocation Left image on the frame
     * @param externalButtonClickListener Click listener to call upon action
     * button click
     * @param imgHeight Left image height
     * @param imgWidth Left image width
     * @param readOnly If true, prevent adding action button on the form
     */
    public WindowForm3(String caption, Layout formLayout, String imageLocation, Button.ClickListener externalButtonClickListener,
            int imgHeight, int imgWidth, boolean readOnly) {
        init(caption, formLayout, imageLocation, "Save", externalButtonClickListener, imgHeight, imgWidth, null);
        actionButton.setVisible(!readOnly);
    }

    public WindowForm3(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit, String imageLocation, Button.ClickListener externalButtonClickListener,
            int imgHeight, int imgWidth, boolean readOnly, String buttonStyle, Button... additionalFooterButtons) {
        init(caption, formLayout, winHeight, winWidth, winUnit, imageLocation, "Save", externalButtonClickListener, imgHeight, imgWidth, buttonStyle,
                additionalFooterButtons);
        actionButton.setVisible(!readOnly);
    }

    /**
     * Typical Action-Close form
     *
     * @param caption Form caption
     * @param formLayout Form to inject into this frame
     * @param imageLocation Left image on the frame
     * @param actionButtonCaption Caption for the action button
     * @param externalButtonClickListener Click listener to call upon action
     * button click
     * @param imgHeight Left image height
     * @param imgWidth Left image width
     * @param readOnly If true, prevent adding action button on the form
     */
    public WindowForm3(String caption, Layout formLayout, String imageLocation, String actionButtonCaption,
            Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth, boolean readOnly) {
        init(caption, formLayout, imageLocation, actionButtonCaption, externalButtonClickListener, imgHeight, imgWidth, null);

        if (actionButton != null) {
            actionButton.setVisible(!readOnly);
        }
    }

    public WindowForm3(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit, String imageLocation,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth, boolean readOnly) {
        init(caption, formLayout, winHeight, winWidth, winUnit, imageLocation, actionButtonCaption, externalButtonClickListener, imgHeight, imgWidth, null);

        if (actionButton != null) {
            actionButton.setVisible(!readOnly);
        }
    }

    public WindowForm3(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit, String imageLocation,
            String actionButtonCaption, String closeButtonCaption, Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth, boolean readOnly) {
        init(caption, formLayout, winHeight, winWidth, winUnit, imageLocation, actionButtonCaption, closeButtonCaption, externalButtonClickListener, imgHeight, imgWidth, null);

        if (actionButton != null) {
            actionButton.setVisible(!readOnly);
        }
    }

    public WindowForm3(String caption, Layout formLayout, int formHeight, int formWidth, String imageLocation,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener,
            int imgWidth, int imgHeight, boolean readOnly) {
        init(caption, formLayout, imageLocation, actionButtonCaption, externalButtonClickListener, imgHeight, imgWidth, null);

        if (actionButton != null) {
            actionButton.setVisible(!readOnly);
        }
    }

    /**
     * Manage lockable layout, meaning, lock all form fields if necessary
     * through interface.
     *
     * @param caption
     * @param layoutLockable Layout (form) fields to be locked (or not).
     * @param formHeight
     * @param formWidth
     * @param imageLocation
     * @param actionButtonCaption
     * @param externalButtonClickListener
     * @param imgWidth
     * @param imgHeight
     * @param readOnly
     */
    public WindowForm3(String caption, ILayoutLockable layoutLockable, int formHeight, int formWidth, String imageLocation,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener,
            int imgWidth, int imgHeight, boolean readOnly) {
        init(caption, layoutLockable, imageLocation, actionButtonCaption, externalButtonClickListener, imgHeight, imgWidth, null);

        if (actionButton != null) {
            actionButton.setVisible(!readOnly);
        }
    }

    private void init(String caption, Layout formLayout, String imageLocation, String actionButtonCaption,
            Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth, String buttonStyle,
            Button... additionalFooterButtons) {
        init(caption, formLayout, 70, 60, Unit.PERCENTAGE, imageLocation, actionButtonCaption, externalButtonClickListener,
                imgHeight, imgWidth, buttonStyle, additionalFooterButtons);
    }

    private void init(String caption, Layout formLayout, String imageLocation, String actionButtonCaption, String closeButtonCaption,
            Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth, String buttonStyle,
            Button... additionalFooterButtons) {
        init(caption, formLayout, 70, 60, Unit.PERCENTAGE, imageLocation, actionButtonCaption, closeButtonCaption, externalButtonClickListener,
                imgHeight, imgWidth, buttonStyle, additionalFooterButtons);
    }

    private void init(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit, String imageLocation,
            String actionButtonCaption, String closeButtonCaption, Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth,
            String buttonStyle, Button... additionalFooterButtons) {
        addStyleName("profile-window");
        setId(ID);
        Responsive.makeResponsive(this);

        setModal(true);
        addCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        setHeight(winHeight, winUnit);
        setWidth(winWidth, winUnit);

        if (actionButtonCaption != null && !actionButtonCaption.isEmpty()) {
            actionButton = new Button(actionButtonCaption);
        }

        closeButton = new Button(closeButtonCaption);

        content.setSizeFull();
        content.setMargin(new MarginInfo(true, false, true, false));
        setContent(content);

        detailsWrapper.setSizeFull();
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_ICONS_ON_TOP);
        detailsWrapper.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        content.addComponent(detailsWrapper);
        content.setExpandRatio(detailsWrapper, 1f);

        detailsWrapper.addComponent(buildFormTab(caption, formLayout, imageLocation, imgWidth, imgHeight));
        content.addComponent(buildFooter(externalButtonClickListener, buttonStyle, additionalFooterButtons));
    }

    private void init(String caption, Layout formLayout, int winHeight, int winWidth, Unit winUnit, String imageLocation,
            String actionButtonCaption, Button.ClickListener externalButtonClickListener, int imgHeight, int imgWidth,
            String buttonStyle, Button... additionalFooterButtons) {

        init(caption, formLayout, winHeight, winWidth, winUnit, imageLocation,
                actionButtonCaption, "Zatvori", externalButtonClickListener, imgHeight, imgWidth, buttonStyle, additionalFooterButtons);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="buildFormTab">
    protected final Component buildFormTab(String caption, Layout formLayout, String imageLocation, int imageWidth, int imageHeight) {
        formLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        formLayout.setSizeUndefined();

        HorizontalLayout centralLayout = new HorizontalLayout();
        centralLayout.setCaption(caption);
        centralLayout.setIcon(VaadinIcons.USER);
        centralLayout.setSpacing(true);
        centralLayout.setMargin(true);
        centralLayout.addStyleName("profile-form");

        VerticalLayout picLayout = new VerticalLayout();
        picLayout.setSizeFull();
        picLayout.setSpacing(true);

        if (imageLocation == null) {
            imageLocation = "img/profile-pic-300px.jpg";
        }

        Image profilePic = new Image(null, new ThemeResource(imageLocation));

        if (imageWidth < 0 && imageHeight < 0) {
            profilePic.setWidth(85, Unit.PERCENTAGE);
            profilePic.setHeight(85, Unit.PERCENTAGE);
        } else {
            if (imageWidth > 0) {
                profilePic.setWidth(imageWidth, Unit.PIXELS);
            }

            if (imageHeight > 0) {
                profilePic.setHeight(imageHeight, Unit.PIXELS);
            }
        }

        picLayout.addComponent(profilePic);

        centralLayout.addComponent(picLayout);
        centralLayout.addComponent(formLayout);
        centralLayout.setExpandRatio(formLayout, 1);

        return centralLayout;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Footer">
    private Component buildFooter(Button.ClickListener externalButtonClickListener, String buttonStyle, Button... additionalFooterButons) {
        HorizontalLayout footerLayout = new HorizontalLayout();

        footerLayout.setSpacing(true);
        footerLayout.setMargin(true);
        footerLayout.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footerLayout.setWidth(100, Unit.PERCENTAGE);

        closeButton.setWidth(120, Unit.PIXELS);
        closeButton.addStyleName(ValoTheme.BUTTON_DANGER);
        closeButton.addClickListener((Button.ClickEvent event) -> {
            close();
        });
        closeButton.focus();

        actionButton.setWidth(120, Unit.PIXELS);

        if (additionalFooterButons != null) {
            for (Button addButton : additionalFooterButons) {
                addButton.setWidth(120, Unit.PIXELS);
                if (buttonStyle != null) {
                    addButton.addStyleName(buttonStyle);
                }
            }
        }

        if (externalButtonClickListener != null) {
            actionButton.addClickListener(externalButtonClickListener);
        }

        // dodaj opcione dugmiće pre dugmića sa ovog prozora..
        if (additionalFooterButons != null) {
            footerLayout.addComponents(additionalFooterButons);
        }

        footerLayout.addComponent(actionButton);
        footerLayout.addComponent(closeButton);

        footerLayout.setExpandRatio(actionButton, 1.0f);

        if (additionalFooterButons != null) {
            for (Button b : additionalFooterButons) {
                footerLayout.setComponentAlignment(b, Alignment.MIDDLE_RIGHT);
                footerLayout.setExpandRatio(b, 1.0f);
            }
        }

        footerLayout.setComponentAlignment(actionButton, Alignment.MIDDLE_RIGHT);
        footerLayout.setComponentAlignment(closeButton, Alignment.MIDDLE_RIGHT);

        return footerLayout;
    }
    //</editor-fold>
}
