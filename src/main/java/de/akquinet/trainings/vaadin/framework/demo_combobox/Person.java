package de.akquinet.trainings.vaadin.framework.demo_combobox;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class Person
{

    private String name;
    private Integer age;
    private Country country;

    public Person()
    {

    }

    public Person(String name, Integer age, Country country)
    {
        super();
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "Person [name=" + name + ", age=" + age + ", country=" + country
                + "]";
    }

}
