package org.superbapps.utils.vaadin.Forms;

import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.v7.ui.TextArea;
import com.vaadin.v7.ui.TextField;
import java.util.Date;
import org.superbapps.utils.common.Enums.ErrorMessages;
import org.superbapps.utils.common.dates.formats.DateFormat;
import org.superbapps.utils.vaadin.Trees.ILayoutLockable;
import org.superbapps.utils.vaadin.Trees.IUpdateData;

public abstract class Form_CRUD2<T> extends FormLayout implements IUpdateData<T>, ILayoutLockable {

    public static final String APP_DATE_FORMAT = DateFormat.DATE_FORMAT_SRB.toString();
    public static final String FIELD_NOT_EMPTY_ERROR_MSG = ErrorMessages.FIELD_NOT_EMPTY_ERROR_MSG.toString();

    protected FieldGroup fieldGroup;
    protected BeanItem<T> beanItem;
    protected T bean;

    protected Button crudButton;
    protected Button.ClickListener clickListener;
    protected String btnCaption;
    protected boolean defaultCRUDButtonOnForm;
    protected boolean readOnly;

    /**
     * Listener to call upon form is submitted. This way, event is send to
     * calling object with collected data of T type.
     */
    protected IUpdateData<T> iUpdateDataListener;

    protected Form_CRUD2() {
        super.setSizeFull();
        super.setMargin(true);
        super.setSpacing(true);

        defaultCRUDButtonOnForm = false;
        readOnly = false;
        crudButton = new Button();
        crudButton.setWidth(120, Unit.PIXELS);
    }

    /**
     * Ukoliko neki od projekata zavise od ovog konstruktora<br>
     * imati na umu da je fieldGroup uvwk NULL !!!!
     *
     * @param beanItem
     * @deprecated
     */
    @Deprecated
    private Form_CRUD2(BeanItem<T> beanItem) {
        this();
        this.beanItem = beanItem;
        this.bean = beanItem.getBean();
    }

    /*
     * Ukoliko neki od projekata zavise od ovog konstruktora<br>
     * imati na umu da je fieldGroup uvwk NULL !!!!
     *
     * @param beanItem
     * @deprecated
        public Form_CRUD2(T bean) {
        this();
        this.bean = bean;
        this.beanItem = new BeanItem<>(bean);
        this.fieldGroup.setItemDataSource(beanItem);
    }
     */
    public Form_CRUD2(FieldGroup fieldGroup) {
        this();
        this.fieldGroup = fieldGroup;
    }

    public Button.ClickListener getClickListener() {
        return clickListener;
    }

    public void setBeanItem(Item item) {
        fieldGroup.setItemDataSource(item);
        beanItem = (BeanItem<T>) fieldGroup.getItemDataSource();
        this.bean = beanItem.getBean();
    }

    public void setBean(T bean) {
        this.bean = bean;
        this.beanItem = new BeanItem<>(bean);
        fieldGroup.setItemDataSource(beanItem);
    }

    //<editor-fold defaultstate="collapsed" desc="UpdateDataListener">
    public IUpdateData getUpdateDataListener() {
        return iUpdateDataListener;
    }

    public void setUpdateDataListener(IUpdateData iUpdateDataListener) {
        this.iUpdateDataListener = iUpdateDataListener;
    }
    //</editor-fold>

    /**
     *
     * @param lockAll - zaključavanje svih polja u formi ?
     */
    protected void lockFormFileds(boolean lockAll) {
        if (lockAll) {
            fieldGroup.getFields().stream().forEach((c) -> {
                c.setEnabled(false);
            });
        }
    }

    /**
     * Postavi vrednost bean-a "t" sakupljanjem vrednosti iz polja na formi.
     *
     * @param item
     */
    protected abstract void setBeanFromFields(T item);

    protected abstract void setFieldsFromBean(T item);

    protected final void setFormFieldsWidths(float width, Sizeable.Unit unit) {
        for (Component c : fieldGroup.getFields()) {

            c.setWidth(width, unit);

            if (c instanceof TextField) {
                ((TextField) c).setNullRepresentation("");
            }
            if (c instanceof DateField) {
                ((DateField) c).setConverter(Date.class);
                ((DateField) c).setDateFormat(APP_DATE_FORMAT);

            }
            if (c instanceof TextArea) {
                ((TextArea) c).setNullRepresentation("");
                ((TextArea) c).setRows(3);
            }
        }
    }

    /**
     * Add annotated UI elements to this form
     */
    protected void addBeansToForm() {
        for (Component c : fieldGroup.getFields()) {
            addComponent(c);
        }

        // addAdditionalBeansToForm();

        if (defaultCRUDButtonOnForm) {
            crudButton.setCaption(btnCaption);
            crudButton.addClickListener(clickListener);

            addComponents(crudButton);
        }
    }

    /**
     * Inicijalizacija svojstava polja na formi i/ili njihovih vrednosti.
     */
    protected abstract void initFields();
    
    /**
     * Koristiti u slučajevima kada su potrebne dodatne gui operacije nad
     * poljima forme.<br>
     * Primer : setEnabled... setValue... i slično..
     */
    protected void guiPostConstruct() {
        addAdditionalBeansToForm();
        addBeansToForm();
        setLayoutFieldsLocked(readOnly);
    }

    /**
     * Add non-annotated, additional UI elements to this form.
     */
    protected void addAdditionalBeansToForm() {
    }

    /**
     * Naznačiti koja polja su obavezna, čime će se na formi označiti
     * zvezdicama.
     */
    protected abstract void setRequiredFields();

    /**
     * Koristiti npr. za combobox komponente čiji se sadržaj dinamički menja
     * kako bi se uvek dobio što ažurniji sadržaj.
     */
    protected abstract void updateDynamicFields();

    @Override
    public void update(T item) {
        setFieldsFromBean(item);
    }

    @Override
    public final void setLayoutFieldsLocked(boolean readOnly) {
        fieldGroup.getFields().stream().forEach(f -> {
            f.setEnabled(!readOnly);
        });
    }
}
