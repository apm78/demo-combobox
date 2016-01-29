package de.akquinet.trainings.vaadin.framework.demo_combobox;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;
import java.util.Arrays;
import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
@Theme("demo_combo_box_theme")
public class DemoComboBoxUI extends UI
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        final List<Country> countryList = Arrays.asList(new Country("Germany",
                "de"), new Country("USA", "us"), new Country("Finland", "fi"));

        final Person person = new Person("John Doe", 42, countryList.get(0));

        final TextField nameField = new TextField("Name");
        nameField.setRequired(true);
        nameField.setRequiredError("The name is obligatory!");
        final TextField ageField = new TextField("Age");
        ageField.setNullRepresentation("");
        final ComboBox comboBox = new ComboBox("Country");
        comboBox.setInputPrompt("select country");
        comboBox.setNullSelectionAllowed(false);
        final BeanItemContainer<Country> container = new BeanItemContainer<>(
                Country.class, countryList);
        comboBox.setContainerDataSource(container);
        comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        comboBox.setItemCaptionPropertyId("name");

        final FormLayout form = new FormLayout();
        form.addComponents(nameField, ageField, comboBox);
        layout.addComponent(form);

        final BeanItem<Person> item = new BeanItem<>(person);
        final FieldGroup fieldGroup = new FieldGroup(item);
        fieldGroup.bind(nameField, "name");
        fieldGroup.bind(ageField, "age");
        fieldGroup.bind(comboBox, "country");

        final Button commitBtn = new Button("save", new Button.ClickListener()
        {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event)
            {
                try
                {
                    fieldGroup.commit();
                }
                catch (CommitException e)
                {
                    Notification.show("Failed to save!", Type.WARNING_MESSAGE);
                    e.printStackTrace();
                }
            }
        });

        final Button discardBtn = new Button("discard",
                new Button.ClickListener()
                {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event)
                    {
                        fieldGroup.discard();
                    }
                });

        final Button showPerson = new Button("show person's values",
                new Button.ClickListener()
                {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event)
                    {
                        Notification.show("Name: " + person.getName()
                                + ", Age: " + person.getAge() + ", Country: "
                                + person.getCountry().getName());
                    }
                });

        final HorizontalLayout hl = new HorizontalLayout(commitBtn, discardBtn,
                showPerson);
        hl.setSpacing(true);
        layout.addComponent(hl);
    }

    @WebServlet(urlPatterns = "/*", name = "DemoComboBoxUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoComboBoxUI.class, productionMode = false)
    public static class DemoComboBoxUIServlet extends VaadinServlet
    {

        private static final long serialVersionUID = 1L;
    }
}
