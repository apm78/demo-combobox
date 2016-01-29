package de.akquinet.trainings.vaadin.framework.demo_combobox;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Country
{

    private String name;
    private String shortName;

    public Country()
    {
        this(null, null);
    }

    public Country(String name, String shortName)
    {
        super();
        this.name = name;
        this.shortName = shortName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    @Override
    public String toString()
    {
        return "Country [name=" + name + ", shortName=" + shortName + "]";
    }

}
